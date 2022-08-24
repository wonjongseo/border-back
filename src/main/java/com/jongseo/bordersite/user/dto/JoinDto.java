package com.jongseo.bordersite.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class JoinDto {

    private  String email;
    private  String name;
    private String password;
    private String password2;
}
