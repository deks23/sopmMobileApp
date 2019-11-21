package com.project.sopmmobileapp.model.dtos;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class CreateVoteDto extends BaseObservable {

    @SerializedName("optionId")
    private int optionId;

    public CreateVoteDto(Long id) {
        optionId = Math.toIntExact(id);
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = Math.toIntExact(optionId);
    }
}
