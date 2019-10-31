package com.project.sopmmobileapp.model.dtos.response;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
//TODO CHANGE SERVE REQUES ABOUT SIDE OF SERVER
public class LoginResponse extends BaseObservable {


    @SerializedName("username")
    private String username;

    @SerializedName("jwt")
    private String token;

    @SerializedName("needData")
    private boolean needData;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public boolean isNeedData() {
        return needData;
    }

    public void setNeedData(boolean needData) {
        this.needData = needData;
    }
}
