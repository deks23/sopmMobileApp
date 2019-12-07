package com.project.sopmmobileapp.model.dtos;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import retrofit2.http.GET;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionItemDto {

    @SerializedName("option")
    private String option;

}
