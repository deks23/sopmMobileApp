package com.project.sopmmobileapp.model.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Credentials {

    @SerializedName("userName")
    private String username;

    @SerializedName("password")
    private String password;

}
