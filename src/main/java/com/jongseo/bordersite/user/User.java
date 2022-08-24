package com.jongseo.bordersite.user;

import com.jongseo.bordersite.border.Border;
import com.jongseo.bordersite.config.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true,length = 50)
    private String email;

    @Column(length = 50)
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Border> borders = new ArrayList<>();

    public User hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }
    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }


}
