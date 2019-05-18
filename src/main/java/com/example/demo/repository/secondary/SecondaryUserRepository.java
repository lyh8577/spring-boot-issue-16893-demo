package com.example.demo.repository.secondary;

import com.example.demo.domain.secondary.SecondaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Long> {
}
