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
import com.project.sopmmobileapp.view.fragments.SurveyFragment;
import com.project.sopmmobileapp.view.fragments.VoteFragment;
import com.project.sopmmobileapp.view.holders.HolderAllSurvey;
import com.project.sopmmobileapp.view.holders.HolderMySurveyView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdapterAllSurveysListItem extends RecyclerView.Adapter<HolderAllSurvey> {

    private SurveysResponse surveysResponse = new SurveysResponse();

    private Context context;

    private View view;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SurveyClient surveyClient;

    public AdapterAllSurveysListItem(Context context) {
        this.context = context;
        VoteApplication.getClientsComponent().inject(this);
        this.getSurveys();
    }

    @NonNull
    @Override
    public HolderAllSurvey onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.all_survey_list_item,
                        viewGroup, false);
        this.view = view;
        return new HolderAllSurvey(view);
    }


    public void addSurvey(SurveyResponse surveyResponse) {
        surveysResponse.add(surveyResponse);
        super.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAllSurvey holder, int position) {
        holder.setFields(this.surveysResponse.get(position));
        setActionOnClickSurvey(this.surveysResponse.get(position));
    }


    public void getSurveys() {
        Disposable disposable = surveyClient.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateSurveys, e -> Log.e(FragmentTags.MainViewPagerFragment, e.getMessage(), e));
        compositeDisposable.add(disposable);
    }

    @Override
    public int getItemCount() {
        return surveysResponse.size();
    }

    public void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    private void updateSurveys(SurveysResponse surveysResponse) {
        this.surveysResponse = surveysResponse;
        super.notifyDataSetChanged();
    }

    private void setActionOnClickSurvey(SurveyResponse detailsSurvey) {
        this.view.setOnClickListener((view) -> {
            VoteFragment fragment = new VoteFragment(detailsSurvey);
            ((MainActivity) this.context).putFragment(fragment, FragmentTags.VoteFragment);
        });

    }

}
