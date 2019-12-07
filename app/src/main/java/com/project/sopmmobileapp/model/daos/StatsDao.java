package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.response.StatsResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatsDao {

    String SURVEY_ID = "suveyId";
    String SURVEY_PATH_VARIABLE = "{" + SURVEY_ID + "}";
    String GET_STATS_BY_SURVEY = "/results/get/" + SURVEY_PATH_VARIABLE;

    @GET(GET_STATS_BY_SURVEY)
    Single<Response<StatsResponse>> getStats(@Path(SURVEY_ID) Long surveyId);

}
