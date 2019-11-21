package com.project.sopmmobileapp.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.di.clients.VoteClient;
import com.project.sopmmobileapp.model.dtos.CreateVoteDto;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.response.BaseResponse;
import com.project.sopmmobileapp.model.response.OptionResponse;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.pager.MainViewPagerFragment;
import com.project.sopmmobileapp.view.holders.HolderClickOption;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class AdapterClickOptions extends RecyclerView.Adapter<HolderClickOption> {

    private Context context;

    private List<OptionResponse> options;

    private View view;

    private VoteClient voteClient;


    public AdapterClickOptions(Context context, List<OptionResponse> options,
                               VoteClient voteClient ) {
        this.context = context;
        this.options = options;
        this.voteClient = voteClient;
    }

    @NonNull
    @Override
    public HolderClickOption onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.view = LayoutInflater.from(context).inflate(R.layout.option_click_item, viewGroup, false);
        return new HolderClickOption(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClickOption holder, int position) {
        holder.setFields(this.options.get(position));
        setActionOnClickOption(this.options.get(position));
    }

    @Override
    public int getItemCount() {
        return options.size();
    }


    public List<OptionResponse> getOptions() {
        return options;
    }

    private void setActionOnClickOption(OptionResponse option) {
        this.view.setOnClickListener((view) -> {
            CreateVoteDto createVoteDto = new CreateVoteDto(option.getId());
            Disposable disposable = voteClient.createVote(createVoteDto)
                    .subscribe((BaseResponse authenticationResponse) -> {
                        Log.i(FragmentTags.VoteFragment, "Vote in");
                        Toast.makeText(context.getApplicationContext(),
                                "You voted",
                                Toast.LENGTH_SHORT).show();
                        MainActivity.instance.setBaseForBackStack(new MainViewPagerFragment(),
                                FragmentTags.MainViewPagerFragment);
                    }, (Throwable e) -> {
                        if (e instanceof BadRequestException) {
                            Log.i(FragmentTags.LoginFragment, "Server error", e);
                        }
                    });
        });

    }
}
