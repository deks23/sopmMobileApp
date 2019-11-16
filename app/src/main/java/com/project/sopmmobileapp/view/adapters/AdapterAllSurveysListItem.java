package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.view.holders.HolderSurveyView;

public class AdapterAllSurveysListItem extends AbstractAdapterSurveyLIstItem {

    public AdapterAllSurveysListItem(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public HolderSurveyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_survey_list_item, viewGroup, false);
        this.view = view;
        return new HolderSurveyView(view);
    }


    public void addSurvey(DetailsSurvey detailsSurvey) {
        super.detailsPostDtoList.add(detailsSurvey);
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSurveyView myHolder, int position) {
        super.onBindViewHolder(myHolder, position);
    }


    @Override
    public int getItemCount() {
        return detailsPostDtoList.size();
    }

    public void onDestroy() {
        super.compositeDisposable.dispose();
    }
}
