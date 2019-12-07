package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.VoteFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.converters.DataConverter;
import com.project.sopmmobileapp.model.di.clients.VoteClient;
import com.project.sopmmobileapp.model.response.SurveyResponse;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.adapters.AdapterClickOptions;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithRemoveFromStack;
import com.project.sopmmobileapp.view.fragments.pager.MainViewPagerFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

public class VoteFragment extends Fragment implements BackWithRemoveFromStack {

    @BindView(R.id.error_message)
    TextView errorMessage;

    @BindView(R.id.options_vote_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.category_value)
    TextView categoryValue;

    @BindView(R.id.create_at_value)
    TextView createAtValue;

    @BindView(R.id.finish_date_value)
    TextView finishDateValue;
    @BindView(R.id.question)
    TextView question;

    @Inject
    VoteClient voteClient;

    @State(ABundler.class)
    SurveyResponse surveyResponse = new SurveyResponse();

    private AdapterClickOptions adapterClickOptions;

    public VoteFragment() {
    }

    public VoteFragment(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        VoteFragmentBinding createSurveyFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.vote_fragment, container, false);

        View mainView = createSurveyFragmentBinding.getRoot();
        ButterKnife.bind(this, mainView);
        VoteApplication.getClientsComponent().inject(this);
        categoryValue.setText(surveyResponse.getCategory().getCategoryName());
        createAtValue.setText(DataConverter.toString(surveyResponse.getCreateDate()));
        finishDateValue.setText(DataConverter.toString(surveyResponse.getFinishTime()));
        question.setText(surveyResponse.getQuestion());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapterClickOptions = new AdapterClickOptions(getContext(), surveyResponse.getOptions(), voteClient);
        recyclerView.setAdapter(adapterClickOptions);

        return mainView;
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void clearErrorMessage() {
        if (errorMessage.getText() != "") {
            errorMessage.setText("");
        }
    }

    @OnClick(R.id.cancel_bt)
    public void cancel() {
        MainViewPagerFragment fragment = new MainViewPagerFragment();
        ((MainActivity) Objects.requireNonNull(getActivity())).setBaseForBackStack(fragment,
                FragmentTags.MainViewPagerFragment);
    }
}
