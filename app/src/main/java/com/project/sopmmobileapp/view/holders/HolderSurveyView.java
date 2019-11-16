package com.project.sopmmobileapp.view.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.request.DetailsSurvey;

public class HolderSurveyView extends RecyclerView.ViewHolder {

    private TextView question;
    private TextView category;
    private TextView state;

    public HolderSurveyView(@NonNull View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.question);
        category = itemView.findViewById(R.id.category);
        state = itemView.findViewById(R.id.state);
    }

    public void setFields(DetailsSurvey detailsSurvey){
        question.setText(detailsSurvey.getQuestion());
        category.setText(detailsSurvey.getCategory().getCategoryName());
        state.setText(detailsSurvey.getState());
    }
}
