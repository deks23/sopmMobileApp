package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

public class UserDetailsFragment extends Fragment {

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    UserDetailsClient userDetailsClient;

    @BindView(R.id.spinner)
    Spinner spinner;

    @State(ABundler.class)
    UserDetails userDetails = new UserDetails();

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
        Log.i(FragmentTags.UserDetailsFragment, userDetails.toString());
        //TODO Validation data
        //TODO Sed request Data
        //TODO Serve bad answer from server
    }


}
