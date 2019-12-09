package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.StatsFragment;
import com.project.sopmmobileapp.view.holders.HolderMySurveyView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AdapterMySurveyListItem extends RecyclerView.Adapter<HolderMySurveyView> {

    private  SurveysResponse surveysResponse = new SurveysResponse();

    private Context context;

    private View view;

    @Inject
    SurveyClient surveyClient;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AdapterMySurveyListItem(Context context) {
        this.context = context;
        VoteApplication.getClientsComponent().inject(this);
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
        holder.setFields(this.surveysResponse.get(position));
        setActionOnClicStatistics(surveysResponse.get(position));

    }

    @Override
    public int getItemCount() {
        return surveysResponse.size();
    }


    public void addSurvey(SurveyResponse surveyResponse) {
        surveysResponse.add(surveyResponse);
        super.notifyDataSetChanged();
    }

    private void getMySurveys() {

        Disposable disposable = surveyClient.getMySurveys()
                .subscribe(this::updateSurveys, e -> Log.e(FragmentTags.MainViewPagerFragment, e.getMessage(), e));
        compositeDisposable.add(disposable);
    }

    private void updateSurveys(SurveysResponse surveysResponse) {
        this.surveysResponse = surveysResponse;
        super.notifyDataSetChanged();
    }

    private void setActionOnClicStatistics( SurveyResponse surveyResponse){
        this.view.setOnClickListener((view) -> {
            StatsFragment fragment = new StatsFragment(context,surveyResponse);
            ((MainActivity) this.context).changeFragment(fragment, FragmentTags.StatsFragment);
        });
    }
}
