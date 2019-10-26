package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;
import com.project.sopmmobileapp.model.dtos.LoginResponse;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginDao {

    String BASE_USER_PATH = "/user";
    String LOGIN_PATH = BASE_USER_PATH + "/login";

    @POST(LOGIN_PATH)
    Single<Response<LoginResponse>> login(@Body Credentials credentials);

}
