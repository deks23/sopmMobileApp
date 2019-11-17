package com.project.sopmmobileapp.model.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Category {

    private Long id;
    private String categoryName;

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
