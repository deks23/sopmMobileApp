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
import com.project.sopmmobileapp.view.adapters.AdapterAllSurveysListItem;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;

import butterknife.ButterKnife;
import lombok.Getter;

@Getter
public class AllSurveysFragment extends Fragment implements BackWithLogOutDialog {

    private AdapterAllSurveysListItem adapterAllSurveysListItem;

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

        adapterAllSurveysListItem = new AdapterAllSurveysListItem(getContext());
        recycler.setAdapter(adapterAllSurveysListItem);

        return mainView;
    }

    public void addSurvey(DetailsSurvey detailsSurvey) {
        this.adapterAllSurveysListItem.addSurvey(detailsSurvey);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.adapterAllSurveysListItem.onDestroy();
    }

}
