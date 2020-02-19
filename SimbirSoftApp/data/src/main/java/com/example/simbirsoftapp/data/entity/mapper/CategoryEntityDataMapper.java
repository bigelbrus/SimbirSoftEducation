package com.example.simbirsoftapp.data.entity.mapper;

import com.example.simbirsoftapp.data.entity.CategoryEntity;
import com.example.simbirsoftapp.domain.Category;

public class CategoryEntityDataMapper {

    CategoryEntityDataMapper(){}

    public Category transform(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setText(categoryEntity.getText());
        category.setLogo(categoryEntity.getLogo());
        category.setId(categoryEntity.getId());
        return category;
    }
}
