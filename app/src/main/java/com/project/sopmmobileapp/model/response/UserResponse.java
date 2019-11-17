package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("username")
    private String username;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthDate")
    private LocalDateTime birthDate;
}
