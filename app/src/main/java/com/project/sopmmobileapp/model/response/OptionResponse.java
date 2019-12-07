package com.project.sopmmobileapp.model.response;

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
public class OptionResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("value")
    private String value;

    @SerializedName("createDate")
    private LocalDateTime createDate;

    @SerializedName("result")
    private String result;

}
