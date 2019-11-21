package com.project.sopmmobileapp.model.dtos;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Synchronized;


@NoArgsConstructor
@AllArgsConstructor
@Parcel
public class OptionItemDto extends BaseObservable {

    @SerializedName("option")
    private String option;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
