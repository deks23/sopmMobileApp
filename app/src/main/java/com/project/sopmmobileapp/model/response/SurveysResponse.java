package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SurveysResponse {

    @SerializedName("surveyList")
    private List<SurveyResponse> surveyList;

    public SurveysResponse() {
        surveyList = new ArrayList<>();
    }

    public void add(SurveyResponse surveyResponse) {
        surveyList.add(surveyResponse);
    }

    public SurveyResponse get(int index) {
        return surveyList.get(index);
    }

    public SurveyResponse remove(int index) {
        return surveyList.remove(index);
    }

    public int size() {
        return surveyList.size();
    }
}
