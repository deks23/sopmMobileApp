package com.project.sopmmobileapp.view.fragments.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.view.adapters.AdapterInAreaSurveysListItem;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SurveysInAreaFragment extends Fragment implements BackWithLogOutDialog {


    private AdapterInAreaSurveysListItem adapterInAreaSurveysListItem;

    @Inject
    GpsClient gpsClient;

    @Inject
    SurveyClient surveyClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.near_survey_list_fragment, container, false);
        VoteApplication.getClientsComponent().inject(this);

        ButterKnife.bind(this, mainView);
        RecyclerView recycler = mainView.findViewById(R.id.near_surveys_recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);


        adapterInAreaSurveysListItem = new AdapterInAreaSurveysListItem(getContext(),gpsClient,surveyClient);
        recycler.setAdapter(adapterInAreaSurveysListItem);

        return mainView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.adapterInAreaSurveysListItem.onDestroy();
    }
}
