package com.jongseo.bordersite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BorderSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorderSiteApplication.class, args);
    }

}
