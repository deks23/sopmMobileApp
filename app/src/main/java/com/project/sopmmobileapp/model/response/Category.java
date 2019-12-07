package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Parcel
@ToString
public class Category {


    @SerializedName("id")
    private Long id;

    @SerializedName("categoryName")
    private String categoryName;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
