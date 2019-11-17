package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.holders.HolderMySurveyView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdapterMySurveyListItem extends AbstractAdapterSurveyListItem {

    public AdapterMySurveyListItem(Context context) {
        super(context);
        this.getMySurveys();
    }

    @NonNull
    @Override
    public HolderMySurveyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.view = LayoutInflater.from(context).inflate(R.layout.my_survey_list_item, viewGroup, false);
        return new HolderMySurveyView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderMySurveyView holder, int position) {
        super.onBindViewHolder(holder, position);
        setActionOnClicStatistics(super.surveysResponse.get(position));

    }

    @Override
    public int getItemCount() {
        return surveysResponse.size();
    }

    public void onDestroy() {
        super.compositeDisposable.dispose();
    }

    public void addSurvey(SurveyResponse surveyResponse) {
        super.surveysResponse.add(surveyResponse);
        super.notifyDataSetChanged();
    }

    private void getMySurveys() {

        Disposable disposable = super.surveyClient.getMySurveys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateSurveys, e -> Log.e(FragmentTags.MainViewPagerFragment, e.getMessage(), e));
        super.compositeDisposable.add(disposable);
    }

    private void updateSurveys(SurveysResponse surveysResponse) {
        this.surveysResponse = surveysResponse;
        super.notifyDataSetChanged();
    }

    private void setActionOnClicStatistics( SurveyResponse surveyResponse){

    }
}
