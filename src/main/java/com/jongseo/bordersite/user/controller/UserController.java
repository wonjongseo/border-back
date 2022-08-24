package com.jongseo.bordersite.user.controller;

import com.jongseo.bordersite.border.repository.BorderRepository;
import com.jongseo.bordersite.user.User;
import com.jongseo.bordersite.user.dto.JoinDto;
import com.jongseo.bordersite.user.dto.LoginDto;
import com.jongseo.bordersite.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String test () {
        return "hello";
    }
    @PostMapping("/join")
    public ResponseEntity<User> join (@RequestBody JoinDto joinDto) {

        try {
            System.out.println("OK");
            User joinedUser = userService.join(joinDto);
            return new ResponseEntity<>(joinedUser, HttpStatus.OK);

        } catch (RuntimeException e) {
            System.out.println("NO");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login" )
    public ResponseEntity<String> login (@RequestBody LoginDto loginDto, HttpServletRequest request) {

        try {
            User user = userService.login(loginDto);
            HttpSession httpSession = request.getSession();

            httpSession.setAttribute("email", user.getEmail());
            return new ResponseEntity<>(user.getName(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }








    }

}
