package com.backend.bank.model;

import com.backend.bank.dto.CategoryDTO;
import com.backend.bank.dto.SavingDTO;

import java.math.BigDecimal;
import java.util.Date;

public class CategoryModel {
    private Integer id;
    private String categoryName;

    public CategoryModel() {

    }

    public CategoryModel(CategoryDTO categoryDTO) {
        setId(categoryDTO.getId());
        setCategoryName(categoryDTO.getCategoryName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
