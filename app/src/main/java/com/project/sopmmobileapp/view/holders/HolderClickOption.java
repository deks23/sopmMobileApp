package com.project.sopmmobileapp.view.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.di.clients.VoteClient;
import com.project.sopmmobileapp.model.response.OptionResponse;

import javax.inject.Inject;

public class HolderClickOption extends RecyclerView.ViewHolder {

    @Inject
    VoteClient voteClient;
    private OptionResponse optionResponse;
    private TextView optionValue;


    public HolderClickOption(@NonNull View itemView) {
        super(itemView);
        optionValue = itemView.findViewById(R.id.option_value);;
    }


    public void setFields(OptionResponse optionResponse) {
        if (optionResponse != null) {
            this.optionResponse = optionResponse;
            optionValue.setText(optionResponse.getValue());
        }
    }


}
