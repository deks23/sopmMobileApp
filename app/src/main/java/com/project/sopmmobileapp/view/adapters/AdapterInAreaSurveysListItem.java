package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.view.holders.HolderSurveyView;

import java.util.List;

public class AdapterInAreaSurveysListItem extends AbstractAdapterSurveyListItem {


    public AdapterInAreaSurveysListItem(Context context) {
        super(context);
        this.getSurveys();
    }

    @NonNull
    @Override
    public HolderSurveyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSurveyView holder, int position) {
        super.onBindViewHolder(holder, position);
        //  setActionOnClickEdit(super.detailsPostDtoList.get(position));
        //   setActionOnClickDelete(super.detailsPostDtoList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void onDestroy() {
        super.compositeDisposable.dispose();
    }

    public void getSurveys(){

    }

    private void updateSurveys(List<DetailsSurvey> detailsSurvey){
        super.detailsPostDtoList = detailsSurvey;
        super.notifyDataSetChanged();
    }

    public void addSurvey(DetailsSurvey detailsPostDto) {
        super.detailsPostDtoList.add(detailsPostDto);
        super.notifyDataSetChanged();
    }
}
