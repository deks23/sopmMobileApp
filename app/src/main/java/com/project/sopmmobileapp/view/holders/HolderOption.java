package com.project.sopmmobileapp.view.holders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.view.adapters.AdapterOptions;

public class HolderOption extends RecyclerView.ViewHolder {

    private TextInputEditText option;

    public HolderOption(@NonNull View itemView, AdapterOptions adapterOptions) {
        super(itemView);
        option = itemView.findViewById(R.id.option);
        option.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapterOptions.getOptionsItems()
                        .get(getAdapterPosition())
                        .setOption(editable
                                .toString());
            }
        });
    }

    public TextInputEditText getOption() {
        return this.option;
    }

    public void setOption(String optionText) {
        option.setText(optionText);
    }
}