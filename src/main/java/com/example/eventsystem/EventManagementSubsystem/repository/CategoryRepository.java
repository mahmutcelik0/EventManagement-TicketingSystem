package com.example.eventsystem.EventManagementSubsystem.repository;

import com.example.eventsystem.EventManagementSubsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    boolean existsCategoryByCategoryName(String categoryName);
}
