package com.SpringBoot.AuthManagement.repository;

import com.SpringBoot.AuthManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean findByEmail(String email);
}
