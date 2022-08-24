package com.jongseo.bordersite.border.service;

import com.jongseo.bordersite.border.Border;
import com.jongseo.bordersite.border.dto.BorderDto;
import com.jongseo.bordersite.border.dto.BorderUserDto;
import com.jongseo.bordersite.border.repository.BorderRepository;
import com.jongseo.bordersite.user.User;
import com.jongseo.bordersite.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BorderService {

    private final BorderRepository borderRepository;
    private final UserRepository userRepository;

    public List<BorderUserDto> getBordersByEmail(String email) {
        System.out.println("inborder my");
        System.out.println("email = " + email);

        List<Border> borders = borderRepository.findAll();

        List<BorderUserDto> list = new ArrayList<>();
        for (Border border : borders) {
            if (!border.getUser().getEmail().equals(email)) {
                continue;


            }
            BorderUserDto borderUserDto= BorderUserDto.builder()
                    .id(border.getId())
                    .title(border.getTitle())
                    .content(border.getContent())
                    .category(border.getCategory())
                    .email(border.getUser().getEmail())
                    .name(border.getUser().getName())
                    .createdAt(border.getCreateAt())
                    .build();
            list.add(borderUserDto);

        }

        return list;

    }
    public List<BorderUserDto> getBorders() {
        List<Border> borders = borderRepository.findAll();

        List<BorderUserDto> list = new ArrayList<>();

        for (Border border : borders) {

            BorderUserDto borderUserDto= BorderUserDto.builder()
                    .id(border.getId())
                    .title(border.getTitle())
                    .content(border.getContent())
                    .category(border.getCategory())
                    .email(border.getUser().getEmail())
                    .name(border.getUser().getName())
                    .createdAt(border.getCreateAt())
                    .build();

            list.add(borderUserDto);
        }

        return list;
    }

    public List<BorderUserDto> getBordersByKeyword(String keyword) {
        List<Border> borders = borderRepository.findAll();

        List<BorderUserDto> list = new ArrayList<>();

        for (Border border : borders) {
            if(border.getContent().contains(keyword)){
                BorderUserDto borderUserDto= BorderUserDto.builder()
                        .id(border.getId())
                        .title(border.getTitle())
                        .content(border.getContent())
                        .category(border.getCategory())
                        .email(border.getUser().getEmail())
                        .name(border.getUser().getName())
                        .createdAt(border.getCreateAt())
                        .build();

                list.add(borderUserDto);
            }
        }

        return list;
    }


    @Transactional
    public BorderUserDto updateBorder(Long id,String loggedInEmail, BorderDto borderDto) throws RuntimeException {

        Border border = borderRepository.findById(id).orElseThrow(() -> new RuntimeException());

        if(!border.getUser().getEmail().equals(loggedInEmail)){
            throw new RuntimeException("NO AUTH");
        }
        System.out.println("borderDto = " + borderDto);

        border.setContent(borderDto.getContent());
        border.setTitle(borderDto.getTitle());
        border.setCategory(borderDto.getCategory());
//        border.setModifiedAt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

        Border updatedBoard = borderRepository.save(border);

        BorderUserDto borderUserDto= BorderUserDto.builder()
                .id(updatedBoard.getId())
                .title(updatedBoard.getTitle())
                .content(updatedBoard.getContent())
                .category(updatedBoard.getCategory())
                .email(updatedBoard.getUser().getEmail())
                .name(updatedBoard.getUser().getName())
                .createdAt(updatedBoard.getCreateAt())
                .build();


        return borderUserDto;



    }
    @Transactional
    public Long deleteBorder(Long id) throws RuntimeException
    {
        try{
            System.out.println(" deleting");
            borderRepository.deleteById(id);

            return id;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("NONO");
        }

    }
    @Transactional
    public Border createBorder(BorderDto borderDto, String email) {
        Border border = new Border();

        borderDto.getContent().replace("\r\n", "<br/>");
        border.setCategory(borderDto.getCategory());
        border.setTitle(borderDto.getTitle());
        border.setContent(borderDto.getContent());


        border.setUser(    userRepository.findByEmail(email).get());

        borderRepository.save(border);

        return border;
    }

    public  List<BorderUserDto> getBordersByCatrgories(String category) {

        List<Border> borders = borderRepository.findAll();

        List<BorderUserDto> list = new ArrayList<>();
        for (Border border : borders) {
            if(!border.getCategory().equals(category)) {
                continue;
            }
            BorderUserDto borderUserDto= BorderUserDto.builder()
                    .id(border.getId())
                    .title(border.getTitle())
                    .content(border.getContent())
                    .category(border.getCategory())
                    .email(border.getUser().getEmail())
                    .name(border.getUser().getName())
                    .createdAt(border.getCreateAt())
                    .build();
            list.add(borderUserDto);

        }

        return list;

    }


}
