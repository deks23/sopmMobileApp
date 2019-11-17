package com.project.sopmmobileapp.view.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.converters.DataConverter;
import com.project.sopmmobileapp.model.response.SurveyResponse;



public class HolderMySurveyView extends RecyclerView.ViewHolder {

    private TextView question;
    private TextView category;
    private TextView state;
    private TextView createDate;
    private TextView finishDate;

    public HolderMySurveyView(@NonNull View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.question);
        category = itemView.findViewById(R.id.category);
        state = itemView.findViewById(R.id.state);
        createDate= itemView.findViewById(R.id.create_date);
        finishDate = itemView.findViewById(R.id.finish_date);
    }

    public void setFields(SurveyResponse surveyResponse) {
        question.setText(surveyResponse.getQuestion());
        category.setText(surveyResponse.getCategory().getCategoryName());
        state.setText(surveyResponse.getState());
        createDate.setText(DataConverter.toString(surveyResponse.getCreateDate()));
        finishDate.setText(DataConverter.toString(surveyResponse.getFinishTime()));
    }


}
