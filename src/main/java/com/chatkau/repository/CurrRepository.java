package com.chatkau.repository;

import com.chatkau.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrRepository extends JpaRepository<Curriculum, Long> {

    List<Curriculum> findByUserId(Long userId);
}
