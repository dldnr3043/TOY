package com.myproject.toy.login.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.toy.login.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}