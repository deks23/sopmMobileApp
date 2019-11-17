package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.model.response.Categories;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SurveyDao {
    String GET_ALL_ACTIVE_SURVEYS = "/survey/getAll";
    String GET_ALL_CATEGORIES = "/survey/getCategories";
    String ADD_NEW_SURVEY = "/survey/addNew";
    String GET_MOST_POPULAR = "/survey/getMostPopular";
    String GET_SURVEY_BY_ID = "/survey/get/{surveyId}";


    @POST(ADD_NEW_SURVEY)
    Single<Response<BaseResponse>> addSurvey(@Body CreateSurvey createSurvey);

    @GET(GET_ALL_CATEGORIES)
    Single<Response<Categories>> getCategories();

    @GET(GET_MOST_POPULAR)
    Single<Response<BaseResponse>> getMostPopularSurveys();
}
