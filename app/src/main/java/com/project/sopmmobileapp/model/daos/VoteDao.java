package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.dtos.CreateVoteDto;
import com.project.sopmmobileapp.model.response.BaseResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VoteDao {

    String CREATE_VOTE = "/vote/create";

    @POST(CREATE_VOTE)
    Single<Response<BaseResponse>> createVote(@Body CreateVoteDto createVoteDto);
}
