package com.example.eventsystem.EventManagementSubsystem.populator;

import com.example.eventsystem.EventManagementSubsystem.dto.CategoryDto;
import com.example.eventsystem.EventManagementSubsystem.entity.Category;
import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator extends GenericPopulator<Category, CategoryDto> {

    @Override
    public CategoryDto populate(Category category) {
        var categoryDto = new CategoryDto();
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
}
