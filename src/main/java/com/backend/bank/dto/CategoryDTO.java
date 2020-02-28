package com.backend.bank.dto;

import com.backend.bank.model.CategoryModel;
import com.backend.bank.model.SavingModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoryName;


    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<TransactionDTO> transactionDTO;*/

    public CategoryDTO(){}

    public CategoryDTO(CategoryModel categoryModel) {
        setId(categoryModel.getId());
        setCategoryName(categoryModel.getCategoryName());
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
