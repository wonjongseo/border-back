package com.jongseo.bordersite.border.repository;

import com.jongseo.bordersite.border.Border;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BorderRepository extends JpaRepository<Border, Long> {




}
