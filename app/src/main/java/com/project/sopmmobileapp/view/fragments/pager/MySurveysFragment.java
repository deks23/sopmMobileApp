package com.project.sopmmobileapp.view.fragments.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.view.adapters.AdapterMySurveyListItem;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;

import butterknife.BindView;
import lombok.Getter;

@Getter
public class MySurveysFragment extends Fragment implements BackWithLogOutDialog {

    private AdapterMySurveyListItem adapterMySurveyListItem;

    @BindView(R.id.add_survey_button)
    Button addSurvey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.my_surveys_fragment, container, false);

        RecyclerView recycler = mainView.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        adapterMySurveyListItem = new AdapterMySurveyListItem(getContext());
        recycler.setAdapter(adapterMySurveyListItem);

        return mainView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
