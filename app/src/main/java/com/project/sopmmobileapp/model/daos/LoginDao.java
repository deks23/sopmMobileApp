package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginDao {

    String BASE_USER_PATH = "/user";
    String LOGIN_PATH = BASE_USER_PATH + "/login";

    @POST(LOGIN_PATH)
    Call<BaseResponse> login(@Body Credentials credentials);

}
