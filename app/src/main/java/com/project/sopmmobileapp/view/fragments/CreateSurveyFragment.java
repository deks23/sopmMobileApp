package com.project.sopmmobileapp.view.fragments;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.di.clients.SurveyClient;
import com.project.sopmmobileapp.model.request.CreateSurvey;
import com.project.sopmmobileapp.view.adapters.AdapterMySurveyListItem;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class CreateSurveyFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    SurveyClient surveyClient;

    private CreateSurvey createSurvey = new CreateSurvey();

    private final AdapterMySurveyListItem adapterMySurveyListItem;

    public CreateSurveyFragment(AdapterMySurveyListItem adapterMySurveyListItem) {
        this.adapterMySurveyListItem = adapterMySurveyListItem;
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        CreatePostFragmentBinding createPostFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.create_post_fragment,
//                container, false);
//        createPostFragmentBinding.setCreatePostDto(this.createPostDto);
//        View mainView = createPostFragmentBinding.getRoot();
//        ButterKnife.bind(this, mainView);
//        BlogApplication.getClientsComponent().inject(this);
//
//        return mainView;
//    }
}
