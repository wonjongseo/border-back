package com.jongseo.bordersite.user.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LoginDto {
    private  String email;
    private String password;
}
