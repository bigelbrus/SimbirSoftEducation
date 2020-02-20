package com.example.simbirsoftapp.data.mapper;

import com.example.simbirsoftapp.data.model.CategoryModel;
import com.example.simbirsoftapp.domain.Category;

import javax.inject.Inject;

public class CategoryModelDataMapper {

    @Inject
    public CategoryModelDataMapper(){}

    public CategoryModel transform(Category category){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setLogo(category.getLogo());
        categoryModel.setText(category.getText());
        return categoryModel;
    }
}
