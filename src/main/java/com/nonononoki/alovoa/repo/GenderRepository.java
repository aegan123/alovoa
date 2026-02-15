package com.nonononoki.alovoa.repo;

import com.nonononoki.alovoa.entity.user.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}

