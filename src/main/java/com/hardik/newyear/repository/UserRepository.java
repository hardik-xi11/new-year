package com.hardik.newyear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hardik.newyear.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByEmail(String email);
}
