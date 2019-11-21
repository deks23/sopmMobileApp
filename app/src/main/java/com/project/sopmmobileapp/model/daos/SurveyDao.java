package com.project.sopmmobileapp.model.daos;

import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.model.request.NeightborhoodRequest;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.model.response.Categories;
import com.project.sopmmobileapp.model.response.SurveysResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface SurveyDao {
    String GET_ALL_ACTIVE_SURVEYS = "/survey/getAll";
    String GET_ALL_CATEGORIES = "/survey/getCategories";
    String ADD_NEW_SURVEY = "/survey/addNew";
    String GET_MOST_POPULAR = "/survey/getMostPopular";
    String GET_SURVEY_BY_ID = "/survey/get/{surveyId}";
    String GET_SURVEYS_FROM_NEIGHBOTHOOD = "/survey/getFromNeighborhood";
    String GET_NOT_ANSWERED = "/survey/getNotAnswered";
    String GET_USER_SURVEYS = "/survey/getUserSurveys";


    @POST(ADD_NEW_SURVEY)
    Single<Response<BaseResponse>> addSurvey(@Body CreateSurvey createSurvey);

    @GET(GET_ALL_CATEGORIES)
    Single<Response<Categories>> getCategories();

    @GET(GET_MOST_POPULAR)
    Single<Response<SurveysResponse>> getMostPopularSurveys();

    @GET(GET_USER_SURVEYS)
    Single<Response<SurveysResponse>> getMySurveys();

    @PATCH(GET_SURVEYS_FROM_NEIGHBOTHOOD)
    Single<Response<SurveysResponse>> getSurveysFromNeighborhood(@Body NeightborhoodRequest neightborhoodRequest);

    @GET(GET_NOT_ANSWERED)
    Single<Response<SurveysResponse>> getNotAnswered();
    @GET(GET_ALL_ACTIVE_SURVEYS)
    Single<Response<SurveysResponse>> getAll();


}
