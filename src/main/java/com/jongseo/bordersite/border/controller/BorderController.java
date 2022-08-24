package com.jongseo.bordersite.border.controller;

import com.jongseo.bordersite.border.dto.BorderDto;
import com.jongseo.bordersite.border.dto.BorderUserDto;
import com.jongseo.bordersite.border.service.BorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BorderController {

    private final BorderService borderService;



    @GetMapping("/borders/all")
    public List<BorderUserDto> getBorders () {

        List<BorderUserDto> list = borderService.getBorders();

        return list;
    }

    @DeleteMapping("/borders")
    public ResponseEntity<Long> deleteBorders(@RequestParam Long id) {
        System.out.println("id = " + id);
        try {
            Long deletedId = borderService.deleteBorder(id);

            return new ResponseEntity<>(deletedId,HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/borders")
    public ResponseEntity<BorderUserDto> updateBorder(@RequestParam Long id, @RequestBody BorderDto borderDto, HttpServletRequest request) {
        System.out.println("id");
        HttpSession httpSession = request.getSession();
        String loggedInEmail = (String) httpSession.getAttribute("email");

        if (loggedInEmail == null) {
            // must login
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {



            BorderUserDto borderUserDto = borderService.updateBorder(id, loggedInEmail, borderDto);
            return new ResponseEntity<>(borderUserDto, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/borders/search")

    public List<BorderUserDto> getBordersByKeyword(@RequestParam String keyword) {

        return         borderService.getBordersByKeyword(keyword);

    }


    @GetMapping("/borders/my")
    public ResponseEntity<List<BorderUserDto>> getMyBorders(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String loggedInEmail =(String)httpSession.getAttribute("email");

        if (loggedInEmail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<BorderUserDto> list = borderService.getBordersByEmail(loggedInEmail);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    @GetMapping("/borders/lt")
//    public ResponseEntity<List<BorderUserDto>> getBordersByIt() {
//
//
//        List<BorderUserDto> list = borderService.getBordersByIt();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }





    @PostMapping("/borders")
    public Long postBorders(@RequestBody BorderDto borderDto , HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        String email = (String) httpSession.getAttribute("email");

        System.out.println("email = " + email);

        if(email == null ){
            System.out.println("must login");
            return null;
        }
        
       return borderService.createBorder(borderDto, email).getId();
    }

    @GetMapping("/borders/japan")
    public List<BorderUserDto> getListByJapan ( ) {
        return borderService.getBordersByCatrgories("日本語授業");
    }

    @GetMapping("/borders/it")
    public List<BorderUserDto> getListByIT ( ) {
        return borderService.getBordersByCatrgories("IT授業");
    }


}