package com.project.sopmmobileapp.view.adapters;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.SurveyFragment;
import com.project.sopmmobileapp.view.holders.HolderMySurveyView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractAdapterSurveyListItem extends RecyclerView.Adapter<HolderMySurveyView> {

    protected SurveysResponse surveysResponse = new SurveysResponse();

    protected Context context;

    protected View view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    protected SurveyClient surveyClient;

    protected AbstractAdapterSurveyListItem(Context context) {
        this.context = context;
        VoteApplication.getClientsComponent().inject(this);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderMySurveyView holder, int position) {
        holder.setFields(this.surveysResponse.get(position));
        setActionOnClickSurvey(this.surveysResponse.get(position));
    }

    @Override
    public int getItemCount() {
        return this.surveysResponse.size();
    }

    private void setActionOnClickSurvey(SurveyResponse detailsSurvey) {
        this.view.setOnClickListener((view) -> {
            SurveyFragment fragment = new SurveyFragment(detailsSurvey);
            ((MainActivity) this.context).putFragment(fragment, FragmentTags.SurveyFragment);
        });

    }
}
