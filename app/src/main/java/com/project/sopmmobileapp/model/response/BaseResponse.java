package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BaseResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("responseMessage")
    private String responseMessage;

    public boolean isStatus() {
        return status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}