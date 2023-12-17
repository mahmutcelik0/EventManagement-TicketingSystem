package com.example.eventsystem.EventManagementSubsystem.api;


import com.example.eventsystem.EventManagementSubsystem.dto.CategoryDto;
import com.example.eventsystem.EventManagementSubsystem.service.CategoryService;
import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventApi {
    @Autowired
    private CategoryService categoryService;

//    @GetMapping
//    public List<>

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories() throws NotFoundException {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addNewCategory(categoryDto);
    }

}
