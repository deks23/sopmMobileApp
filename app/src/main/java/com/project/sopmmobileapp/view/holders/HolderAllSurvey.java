package com.project.sopmmobileapp.view.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.response.SurveyResponse;

public class HolderAllSurvey extends RecyclerView.ViewHolder {

    private TextView question;
    private TextView category;
    private TextView state;
    private TextView voters;

    public HolderAllSurvey(@NonNull View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.question);
        category = itemView.findViewById(R.id.category);
        state = itemView.findViewById(R.id.state);
        voters = itemView.findViewById(R.id.votersv);
    }

    public void setFields(SurveyResponse surveyResponse) {
        question.setText(surveyResponse.getQuestion());
        category.setText(surveyResponse.getCategory().getCategoryName());
        state.setText(surveyResponse.getState());
        voters.setText(String.valueOf(surveyResponse.getAnswers().size()));
    }
}
