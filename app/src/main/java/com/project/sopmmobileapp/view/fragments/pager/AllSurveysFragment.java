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
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.request.DetailsSurvey;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.view.adapters.AdapterAllSurveysListItem;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;
import lombok.Getter;

@Getter
public class AllSurveysFragment extends Fragment implements BackWithLogOutDialog {

    private AdapterAllSurveysListItem adapterAllSurveysListItem;

    @Inject
    SurveyClient surveyClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.all_surveys_list_fragment, container, false);

        ButterKnife.bind(this, mainView);
        RecyclerView recycler = mainView.findViewById(R.id.all_surveys_recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        VoteApplication.getClientsComponent().inject(this);
        adapterAllSurveysListItem = new AdapterAllSurveysListItem(getContext(),surveyClient);
        recycler.setAdapter(adapterAllSurveysListItem);

        return mainView;
    }

    public void addSurvey(SurveyResponse surveyResponse) {
        this.adapterAllSurveysListItem.addSurvey(surveyResponse);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.adapterAllSurveysListItem.onDestroy();
    }

}
