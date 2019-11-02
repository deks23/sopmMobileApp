package com.project.sopmmobileapp.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.UserDetailsFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.di.clients.UserDetailsClient;
import com.project.sopmmobileapp.model.dtos.request.UserDetails;
import com.project.sopmmobileapp.view.activities.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

public class UserDetailsFragment extends Fragment {

    @BindView(R.id.error_message)
    TextView errorMessage;

    @BindView(R.id.showMyDate)
    TextView mDateDisplay;
    @Inject
    UserDetailsClient userDetailsClient;

    @BindView(R.id.spinner)
    Spinner spinner;

    @State(ABundler.class)
    UserDetails userDetails = new UserDetails();
    private int mYear;
    private int mMonth;
    private int mDay;
    private static final int DATE_DIALOG_ID = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        UserDetailsFragmentBinding userDetailsFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.user_details_fragment,
                container, false);
        View mainView = userDetailsFragmentBinding.getRoot();
        userDetailsFragmentBinding.setUserDetails(userDetails);
        ButterKnife.bind(this, mainView);

        VoteApplication.getClientsComponent().inject(this);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
        return mainView;
    }


    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @OnClick(R.id.save_details_button)
    public void saveData() {
        userDetails.setGender(getResources().
                getStringArray(R.array.gender_array)[spinner.getSelectedItemPosition()]);
        userDetails.setBirthday(new GregorianCalendar(mYear, mMonth, mDay).getTime());
        Log.i(FragmentTags.UserDetailsFragment, userDetails.toString());
        if (userDetails.getBirthday().after(new Date())) {
            errorMessage.setText(R.string.birthday_error);
        }
        //TODO Sed request Data
        //TODO Serve bad answer from server
    }

    @OnClick(R.id.myDatePickerButton)
    public void enterBirthday() {
        Objects.requireNonNull(onCreateDialog(DATE_DIALOG_ID)).show();
    }

    private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    private Dialog onCreateDialog(int id) {
        if (id == DATE_DIALOG_ID) {
            return new DatePickerDialog(MainActivity.instance,
                    mDateSetListener,
                    mYear, mMonth, mDay);
        }
        return null;
    }
}
