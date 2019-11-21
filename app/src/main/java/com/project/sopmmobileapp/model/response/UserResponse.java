package com.project.sopmmobileapp.model.response;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;
import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Parcel
public class UserResponse extends BaseObservable {

    @SerializedName("id")
    private Long id;

    @SerializedName("username")
    private String username;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthDate")
    private LocalDateTime birthDate;
}
