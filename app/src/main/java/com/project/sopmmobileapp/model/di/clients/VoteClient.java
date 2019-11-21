package com.project.sopmmobileapp.model.di.clients;

import android.util.Log;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.daos.VoteDao;
import com.project.sopmmobileapp.model.dtos.CreateVoteDto;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.view.fragments.FragmentTags;

import java.net.HttpURLConnection;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.Retrofit;

import static io.reactivex.Single.error;
import static io.reactivex.Single.just;

public class VoteClient extends BaseClient {

    @Named("auth")
    @Inject
    Retrofit retrofit;

    private VoteDao voteDao;

    public VoteClient() {
        VoteApplication.getRetrofitComponent().inject(this);
        this.voteDao = retrofit.create(VoteDao.class);
    }

    public Single<BaseResponse> createVote(CreateVoteDto createVoteDto) {
        Log.i(FragmentTags.VoteFragment, createVoteDto.toString());
        return async(this.voteDao.createVote(createVoteDto)
                .flatMap(authenticationResponse -> {
                    if (authenticationResponse.isSuccessful()) {
                        return just(Objects.requireNonNull(authenticationResponse.body()));
                    }
                    if (authenticationResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        return error(new BadRequestException());
                    }
                    return error(new RuntimeException(Objects.requireNonNull(authenticationResponse.errorBody()).toString()));
                }));
    }
}
