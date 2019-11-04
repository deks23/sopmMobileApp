package com.project.sopmmobileapp.model.adapters;

import androidx.databinding.BaseObservable;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;

import java.util.Arrays;
import java.util.Objects;


public class SpinnerGenderAdapter extends BaseObservable {

    private String[] genders;
    private int index;
    private String gender;

    public SpinnerGenderAdapter() {

        String[] genders = (String[]) Arrays.asList(VoteApplication.getContext().getResources().getStringArray(R.array.gender_array)).toArray();
        gender = Objects.requireNonNull(genders)[0];
    }

    public String[] getGenders() {
        return genders;
    }

    public void setGenders(String[] countries) {
        this.genders = countries;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getGender() {
        return genders[index];
    }

    public void setGender(String country) {
        this.gender = country;
    }
}
