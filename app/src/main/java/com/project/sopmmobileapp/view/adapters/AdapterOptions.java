package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.dtos.OptionItemDto;
import com.project.sopmmobileapp.view.holders.HolderOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterOptions extends RecyclerView.Adapter<HolderOption> {

    private List<OptionItemDto> options;
    private Context context;

    private View view;

    public AdapterOptions(Context context, List<OptionItemDto> options) {
        this.context = context;
        this.options = options;
    }

    @NonNull
    @Override
    public HolderOption onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.option_item, viewGroup, false);
        this.view = view;
        if (options == null) {
            this.options = new ArrayList<>();
        }
        return new HolderOption(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderOption holder, int position) {
        if (!TextUtils.isEmpty(options.get(position).getOption())) {
            holder.getOption().setText(options.get(position).getOption());
        }
        //TODO Use if how want inform user about invalided text
//        if (!TextUtils.isEmpty(options.get(position).getOption())) {
//            holder.option.setError(options.get(position).getOption());
//        } else {
//            holder.option.setError(null);
//        }
    }


    public List<String> getOptions() {
        return options.stream().map(OptionItemDto::getOption).collect(Collectors.toList());
    }

    public List<OptionItemDto> getOptionsItems() {
        return options;
    }


    @Override
    public int getItemCount() {
        return options.size();
    }

    public void addOption() {
        options.add(new OptionItemDto(""));
        super.notifyDataSetChanged();
    }

    public void removeOption() {
        if (!options.isEmpty()) {
            options.remove(options.size() - 1);
        }
        super.notifyDataSetChanged();
    }
}
