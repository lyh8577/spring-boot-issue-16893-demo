package com.example.demo.repository.primary;

import com.example.demo.domain.primary.PrimaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryUserRepository extends JpaRepository<PrimaryUser, Long> {
}
