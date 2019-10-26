package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RegisterDao {
    String BASE_USER_PATH = "/user";
    String REGISTER_PATH = BASE_USER_PATH + "/register";

    @POST(REGISTER_PATH)
    Call<BaseResponse> register(Credentials credentials);

}
