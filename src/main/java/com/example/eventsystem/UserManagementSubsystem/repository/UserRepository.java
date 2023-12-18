package com.example.eventsystem.UserManagementSubsystem.repository;

import com.example.eventsystem.UserManagementSubsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}
