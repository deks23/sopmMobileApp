package com.project.sopmmobileapp.model.response;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;
import com.project.sopmmobileapp.model.entity.Category;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse extends BaseObservable {

    @SerializedName("cateryList")
    private List<Category> categoryList;
}
