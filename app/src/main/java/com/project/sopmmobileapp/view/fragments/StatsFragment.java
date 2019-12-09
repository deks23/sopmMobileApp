package com.project.sopmmobileapp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.StatisticsFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.di.clients.StatsClient;
import com.project.sopmmobileapp.model.response.StatResponse;
import com.project.sopmmobileapp.model.response.StatsResponse;
import com.project.sopmmobileapp.model.response.SurveyResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class StatsFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @State(ABundler.class)
    SurveyResponse surveyResponse;

    @State(ABundler.class)
    List<StatResponse> statResponse;

    private Pie pie;

    @BindView(R.id.any_chart_view)
    AnyChartView anyChartView;

    @BindView(R.id.question_content)
    TextView questionContent;
    @Inject
    StatsClient statsClient;

    private Context context;

    public StatsFragment() {
        this.context = VoteApplication.getContext();
    }

    public StatsFragment(Context context, SurveyResponse surveyResponse) {
        this.context = context;
        this.surveyResponse = surveyResponse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        StatisticsFragmentBinding statisticsFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.statistics_fragment,
                container, false);
        View mainView = statisticsFragmentBinding.getRoot();
        ButterKnife.bind(this, mainView);
        VoteApplication.getClientsComponent().inject(this);
        if (statResponse == null) {
            downloadData();
        }
        questionContent.setText(surveyResponse.getQuestion());
        pie = AnyChart.pie();
        anyChartView.setChart(pie);
        return mainView;
    }


    public void downloadData() {
        Disposable disposable = statsClient.getStatsFromSurvey(surveyResponse.getId())
                .subscribe(this::prepareData, e -> Log.e(FragmentTags.StatsFragment, e.getMessage(), e));
        compositeDisposable.add(disposable);
    }


    public void prepareData(StatsResponse statsResponse) {
        this.statResponse = statsResponse.getStatsResponse();
        List<DataEntry> data = new ArrayList<>();
        for (StatResponse statResponse : statResponse) {
            data.add(new ValueDataEntry(statResponse
                    .getValueOption(), statResponse
                    .getVoteResponse().getNumberOfVotes()));
        }
        pie.data(data);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}
