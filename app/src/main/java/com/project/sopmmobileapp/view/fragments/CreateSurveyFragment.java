package com.project.sopmmobileapp.view.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.CreateSurveyFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.di.clients.GpsClient;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.dtos.OptionItemDto;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.adapters.AdapterOptions;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithRemoveFromStack;
import com.project.sopmmobileapp.view.fragments.pager.MainViewPagerFragment;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CreateSurveyFragment extends Fragment implements BackWithRemoveFromStack {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.error_message)
    TextView errorMessage;

    @State(ABundler.class)
    CreateSurvey createSurvey = new CreateSurvey();

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.finish_date_value)
    TextView mDateDisplay;

    @BindView(R.id.options_recycler_view)
    RecyclerView recyclerView;
    @State(ABundler.class)
    List<OptionItemDto> options = new ArrayList<>();

    @Inject
    SurveyClient surveyClient;

    @Inject
    GpsClient gpsClient;

    private AdapterOptions adapterOptions;

    private int mYear;
    private int mMonth;
    private int mDay;
    private static final int DATE_DIALOG_ID = 0;
    private Location mLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        CreateSurveyFragmentBinding createSurveyFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.create_survey_fragment, container, false);

        View mainView = createSurveyFragmentBinding.getRoot();
        createSurveyFragmentBinding.setSurvey(this.createSurvey);
        ButterKnife.bind(this, mainView);
        VoteApplication.getClientsComponent().inject(this);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapterOptions = new AdapterOptions(getContext(),options);
        recyclerView.setAdapter(adapterOptions);
        return mainView;
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @OnClick(R.id.finishDatePickerButton)
    public void enterFinishDate() {
        Objects.requireNonNull(onCreateDialog(DATE_DIALOG_ID)).show();
    }

    @OnClick(R.id.add_option_bt)
    public void addOption() {
        adapterOptions.addOption();
        options =  adapterOptions.getOptionsItems();
    }

    @OnClick(R.id.remove_option_bt)
    public void removeOption() {
        adapterOptions.removeOption();
        options =  adapterOptions.getOptionsItems();
    }

    @OnClick(R.id.create_survey)
    public void addSurvey() {
        //TODO VALIDATION ON { data only from future, not empty fields, question must have ?,
        // Survey have to min 2 options
//        Location locationTask = gpsClient.getOptionalLocation().get();
//        locationTask.addOnSuccessListener(Objects.requireNonNull(getActivity()), location -> {
//                    if (location != null) {
//                        mLocation = location;
//                    }
//                });


        createSurvey.setAnswerOptions(adapterOptions.getOptions());
        createSurvey.setCategory(categorySpinner.getSelectedItemPosition() + 1);
        createSurvey.setFinishTime(LocalDateTime.parse(mYear + "-" + mMonth + "-" + mDay).withYear(mYear));
        if (mLocation != null) {
            createSurvey.setLatitude(mLocation.getLatitude());
            createSurvey.setLongitude(mLocation.getLongitude());
        } else {
//            Toast.makeText(getContext(), "GPS Location is empty, used deafualt value",
//                    Toast.LENGTH_SHORT).show();
            createSurvey.setLatitude(51.747164);
            createSurvey.setLongitude(19.455942);
        }

        Disposable disposable = this.surveyClient.addSurvey(this.createSurvey)
                .subscribe((BaseResponse authenticationResponse) -> {
                    Log.i(FragmentTags.CreateSurveyFragment, "CreateSurvey sent");
                    if (authenticationResponse.isStatus()) {
                        ((MainActivity) Objects.
                                requireNonNull(getActivity()))
                                .putFragment(new MainViewPagerFragment(),
                                        FragmentTags.MainViewPagerFragment);
                    }
                }, (Throwable e) -> {
                    if (e instanceof BadRequestException) {
                        Log.i(FragmentTags.CreateSurveyFragment, "CreateSurveyException", e);
                        this.errorMessage.setText(getString(R.string.server_error));
                    }
                });

    }

    @OnClick(R.id.cancel_bt)
    public void cancel() {
        ((MainActivity) Objects.
                requireNonNull(getActivity())).onBackPressed();
    }

    private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()
                        .append(mDay).append("-")
                        .append(mMonth).append("-")
                        .append(mYear));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear + 1;
                    mDay = dayOfMonth;
                    clearErrorMessage();
                    updateDisplay();
                }
            };

    private Dialog onCreateDialog(int id) {
        if (id == DATE_DIALOG_ID) {
            return new DatePickerDialog(MainActivity.instance, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                    mDateSetListener,
                    mYear, mMonth, mDay);
        }
        return null;
    }

    private void clearErrorMessage() {
        if (errorMessage.getText() != "") {
            errorMessage.setText("");
        }
    }
}
