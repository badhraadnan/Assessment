package com.assessment.webApplicationBackend.repo;

import com.assessment.webApplicationBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
    Optional<User> findByUsername(String username);
}
