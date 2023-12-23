package com.example.eventsystem.UserManagementSubsystem.repository;

import com.example.eventsystem.UserManagementSubsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findAllByUserId(Long userId);

    @Query("select c from Card as c left join User as u on(c.user.email = u.email) where u.email = ?1")
    List<Card> findAllByUserEmail(String email);
}
