package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.request.UserDetails;
import com.project.sopmmobileapp.model.response.BaseResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserDetailsDao {
    String BASE_USER_PATH = "/user";
    String UPDATE_DATE_PATH = "/updateData";

    @POST(BASE_USER_PATH + UPDATE_DATE_PATH)
    Single<Response<BaseResponse>> updateData(@Body UserDetails credentialsRequest);
}
