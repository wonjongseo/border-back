package com.jongseo.bordersite.user.service;

import com.jongseo.bordersite.user.User;
import com.jongseo.bordersite.user.dto.JoinDto;
import com.jongseo.bordersite.user.dto.LoginDto;
import com.jongseo.bordersite.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public User join(JoinDto joinDto) throws RuntimeException {
        Optional<User> isUser = userRepository.findByEmail(joinDto.getEmail());

        if(isUser.isPresent()){
            System.out.println("dup");
            throw new RuntimeException();
        }

        User user = new User();
        user.setEmail(joinDto.getEmail());
        user.setPassword(joinDto.getPassword());
        user.setName(joinDto.getName());

        user.hashPassword(passwordEncoder);
        userRepository.save(user);
        return user;
    }

    public User login(LoginDto loginDto) throws  RuntimeException {
        Optional<User> isUser = userRepository.findByEmail(loginDto.getEmail());

        if(isUser.isEmpty()){
            System.out.println("Not found user");
            throw  new RuntimeException("NOT FOUND USER");
        }

        User user = isUser.get();
        if (!user.checkPassword(loginDto.getPassword(), passwordEncoder)) {
            System.out.println("Not Match Password");
            throw  new RuntimeException("Not Match Password");
        }


        return user;
    }



}
