package com.jongseo.bordersite.border.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorderUserDto  {
    private Long id;
    private String title;

    private String content;
    private String category;
    private String email;
    private String name;
    private LocalDateTime createdAt;

}
