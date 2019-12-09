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
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.VoteFragment;
import com.project.sopmmobileapp.view.holders.HolderAllSurvey;

import java.util.Optional;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AdapterInAreaSurveysListItem extends RecyclerView.Adapter<HolderAllSurvey> {

    SurveysResponse surveysResponse = new SurveysResponse();

    private Context context;

    private View view;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SurveyClient surveyClient;

    @Inject
    GpsClient gpsClient;

    public AdapterInAreaSurveysListItem(Context context) {
        this.context = context;
        VoteApplication.getClientsComponent().inject(this);
        this.getSurveys();
    }

    @NonNull
    @Override
    public HolderAllSurvey onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.view = LayoutInflater.from(context).inflate(R.layout.all_survey_list_item,
                viewGroup, false);
        ButterKnife.bind(this, view);
        return new HolderAllSurvey(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderAllSurvey holder, int position) {
        holder.setFields(this.surveysResponse.get(position));
        setActionOnClickSurvey(this.surveysResponse.get(position));
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
        neightborhoodRequest.setLatitude(51.747164);
        neightborhoodRequest.setLongitude(19.455942);
        } else {
            neightborhoodRequest.setLongitude(location.getLongitude());
            neightborhoodRequest.setLatitude(location.getLatitude());
        }
        Disposable disposable = surveyClient.getSurveysFromNeighborhood(neightborhoodRequest)
                .subscribe(this::updateSurveys, e -> Log.e(FragmentTags.MainViewPagerFragment, e.getMessage(), e));
        compositeDisposable.add(disposable);
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
