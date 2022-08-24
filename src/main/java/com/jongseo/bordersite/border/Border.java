package com.jongseo.bordersite.border;

import com.jongseo.bordersite.config.entity.BaseEntity;
import com.jongseo.bordersite.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Setter
@Getter
public class Border extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "border_id")
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private String title;

    @Column(length = 20)
    private String category;

    @Lob
    private String content;

//    @Column
//    @CreatedDate
//    private String createAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
//    @Column
//    @LastModifiedDate
//    private String modifiedAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

//    @Column
//    @CreatedDate
//    private LocalDateTime createAt;
//
//    @Column
//        @LastModifiedDate
//    private LocalDateTime modifiedAt;

}

