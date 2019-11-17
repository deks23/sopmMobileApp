package com.project.sopmmobileapp.view.fragments;

import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;

public class SurveyFragment extends Fragment {


    private SurveyResponse surveyResponse;

    public SurveyFragment(SurveyResponse surveyResponse){
        this.surveyResponse = surveyResponse;
    }
}
