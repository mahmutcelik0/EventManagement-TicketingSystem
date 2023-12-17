package com.example.eventsystem.EventManagementSubsystem.service;

import com.example.eventsystem.EventManagementSubsystem.dto.CategoryDto;
import com.example.eventsystem.EventManagementSubsystem.entity.Category;
import com.example.eventsystem.EventManagementSubsystem.populator.CategoryPopulator;
import com.example.eventsystem.EventManagementSubsystem.repository.CategoryRepository;
import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryPopulator categoryPopulator;

    public List<CategoryDto> getAllCategories() throws NotFoundException {
        var categories = categoryRepository.findAll();
        if (CollectionUtils.isEmpty(categories)) throw new NotFoundException("Category list is empty");
        return categoryPopulator.populateAll(categories);
    }

    public ResponseEntity<?> addNewCategory(CategoryDto categoryDto) {
        if(categoryRepository.existsCategoryByCategoryName(categoryDto.getCategoryName())){
            return new ResponseEntity<>("Kategori mevcutta var", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryRepository.save(new Category(categoryDto.getCategoryName()));
        return new ResponseEntity<>("Kategori kaydedildi",HttpStatus.OK);
    }
}
