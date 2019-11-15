package com.project.sopmmobileapp.view.adapters;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.SurveyFragment;
import com.project.sopmmobileapp.view.holders.HolderSurveyView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractAdapterSurveyLIstItem extends RecyclerView.Adapter<HolderSurveyView> {

    protected List<DetailsSurvey> detailsPostDtoList = new ArrayList<>();

    protected Context context;

    protected View view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    protected SurveyClient surveyClient;

    protected AbstractAdapterSurveyLIstItem(Context context) {
        this.context = context;
        VoteApplication.getClientsComponent().inject(this);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderSurveyView holder, int position) {
        holder.setFields(this.detailsPostDtoList.get(position));
        setActionOnClickSurvey(this.detailsPostDtoList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.detailsPostDtoList.size();
    }

    private void setActionOnClickSurvey(DetailsSurvey detailsSurvey) {
        this.view.setOnClickListener((view) -> {
            SurveyFragment fragment = new SurveyFragment(detailsSurvey);
            ((MainActivity) this.context).putFragment(fragment, FragmentTags.SurveyFragment);
        });

    }
}
