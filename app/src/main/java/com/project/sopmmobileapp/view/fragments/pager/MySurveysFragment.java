package com.project.sopmmobileapp.view.fragments.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.databinding.LoginFragmentBinding;
import com.project.sopmmobileapp.databinding.MySurveysFragmentBinding;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.adapters.AdapterMySurveyListItem;
import com.project.sopmmobileapp.view.fragments.CreateSurveyFragment;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;

@Getter
public class MySurveysFragment extends Fragment implements BackWithLogOutDialog {

    private AdapterMySurveyListItem adapterMySurveyListItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MySurveysFragmentBinding mySurveysFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.my_surveys_fragment,
                container, false);

        View mainView = mySurveysFragmentBinding.getRoot();
        RecyclerView recyclerView = mainView.findViewById(R.id.my_surveys_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapterMySurveyListItem = new AdapterMySurveyListItem(getContext());
        recyclerView.setAdapter(adapterMySurveyListItem);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.add_survey_button)
    public void addSurvey() {
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .putFragment(new CreateSurveyFragment(),
                        FragmentTags.CreateSurveyFragment);
    }
}
