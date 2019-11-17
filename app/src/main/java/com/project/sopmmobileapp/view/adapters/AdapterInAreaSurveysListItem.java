package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.request.NeightborhoodRequest;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.model.response.SurveysResponse;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.holders.HolderAllSurvey;
import com.project.sopmmobileapp.view.holders.HolderMySurveyView;

import java.util.Locale;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdapterInAreaSurveysListItem extends RecyclerView.Adapter<HolderAllSurvey> {

    private SurveysResponse surveysResponse = new SurveysResponse();

    private Context context;

    private View view;

    private CompositeDisposable compositeDisposable;

    private SurveyClient surveyClient;

    private GpsClient gpsClient;

    public AdapterInAreaSurveysListItem(Context context, GpsClient gpsClient, SurveyClient surveyClient) {
        this.gpsClient = gpsClient;
        this.surveyClient = surveyClient;
        this.context = context;
        compositeDisposable = new CompositeDisposable();
        this.getSurveys();
    }

    @NonNull
    @Override
    public HolderAllSurvey onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.view = LayoutInflater.from(context).inflate(R.layout.all_survey_list_item,
                viewGroup, false);
        this.getSurveys();
        return new HolderAllSurvey(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderAllSurvey holder, int position) {
        holder.setFields(this.surveysResponse.get(position));

    }

    @Override
    public int getItemCount() {
        return surveysResponse.size();
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

    public void getSurveys() {
        Optional<Location> optionalLocation = gpsClient.getOptionalLocation();
        Location location = null;
        if (optionalLocation.isPresent()) {
            location = optionalLocation.get();
        }
        NeightborhoodRequest neightborhoodRequest = new NeightborhoodRequest();
        if (location == null) {
            neightborhoodRequest.setLongitude(50.0);
            neightborhoodRequest.setLatitude(50.0);
        } else {
            neightborhoodRequest.setLongitude(location.getLongitude());
            neightborhoodRequest.setLatitude(location.getLatitude());
        }
        Disposable disposable = surveyClient.getSurveysFromNeighborhood(neightborhoodRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateSurveys, e -> Log.e(FragmentTags.MainViewPagerFragment, e.getMessage(), e));
        compositeDisposable.add(disposable);
    }

    private void updateSurveys(SurveysResponse surveysResponse) {
        this.surveysResponse = surveysResponse;
        super.notifyDataSetChanged();
    }
}
