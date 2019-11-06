package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sopmmobileapp.R;

import butterknife.ButterKnife;

public class MySurveysFragment extends Fragment {

    private MySurveysListItemAdapter mySurveysListItemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.my_surveys_fragment, container, false);

        ButterKnife.bind(this, mainView);

        RecyclerView recycler = mainView.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        mySurveysListItemAdapter = new MySurveysListItemAdapter(getContext());
        recycler.setAdapter(mySurveysListItemAdapter);

        return mainView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mySurveysListItemAdapter.onDestroy();
    }

    public void addPost(DetailsPostDto detailsPostDto) {
        this.mySurveysListItemAdapter.addPost(detailsPostDto);
    }

    public void deletePost(String postId) {
        this.mySurveysListItemAdapter.deletePost(postId);
    }

    public void editPost(DetailsPostDto detailsPostDto) {
        this.adapterAllPostsListItem.editPost(detailsPostDto);
    }
}
