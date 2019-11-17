package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("user")
    private UserResponse user;

    @SerializedName("option")
    private OptionResponse option;

    @SerializedName("createDate")
    private LocalDateTime createDate;
}
