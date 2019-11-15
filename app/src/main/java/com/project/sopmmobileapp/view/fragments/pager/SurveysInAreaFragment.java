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
import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.view.adapters.AdapterInAreaSurveysListItem;

import butterknife.ButterKnife;

public class SurveysInAreaFragment extends Fragment {


    private AdapterInAreaSurveysListItem adapterInAreaSurveysListItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.near_survey_list_fragment, container, false);

        ButterKnife.bind(this, mainView);
        RecyclerView recycler = mainView.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        adapterInAreaSurveysListItem = new AdapterInAreaSurveysListItem(getContext());
        recycler.setAdapter(adapterInAreaSurveysListItem);

        return mainView;
    }

    public void addSurvey(DetailsSurvey detailsSurvey) {
        this.adapterInAreaSurveysListItem.addSurvey(detailsSurvey);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.adapterInAreaSurveysListItem.onDestroy();
    }
}
