package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.RegisterLayoutBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.di.clients.RegisterClient;
import com.project.sopmmobileapp.model.dtos.request.RegisterCredentialsRequest;
import com.project.sopmmobileapp.model.dtos.response.BaseResponse;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.exceptions.UserIsTakenException;
import com.project.sopmmobileapp.model.validators.PasswordValidator;
import com.project.sopmmobileapp.view.activities.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RegisterFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    RegisterClient registerClient;

    @State(ABundler.class)
    RegisterCredentialsRequest registerCredentialsRequest = new RegisterCredentialsRequest();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        RegisterLayoutBinding registerLayoutBinding = DataBindingUtil.inflate(inflater,
                R.layout.register_layout,
                container, false);
        View mainView = registerLayoutBinding.getRoot();
        registerLayoutBinding.setRegisterCredentialsRequest(this.registerCredentialsRequest);
        ButterKnife.bind(this, mainView);
        VoteApplication.getClientsComponent().inject(this);

        return mainView;
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @OnClick(R.id.sign_button)
    void register() {
        if (PasswordValidator.valid(this.registerCredentialsRequest)) {
            Disposable disposable = this.registerClient.register(PasswordValidator.
                    toCredential(this.registerCredentialsRequest))
                    .subscribe((BaseResponse authenticationResponse) -> {
                        Log.i(FragmentTags.RegisterFragment, "Logged in");
                        Toast.makeText(this.getContext(), R.string.register_successful,
                                Toast.LENGTH_SHORT).show();
                        ((MainActivity) Objects.
                                requireNonNull(getActivity())).
                                setBaseForBackStack(new LoginFragment(),
                                        FragmentTags.LoginFragment);
                    }, (Throwable e) -> {
                        if (e instanceof UserIsTakenException) {
                            Log.i(FragmentTags.RegisterFragment, "User is taken", e);
                            ;
                            this.errorMessage.setText(getString(R.string.user_is_taken));
                        } else if (e instanceof BadRequestException) {
                            Log.i(FragmentTags.RegisterFragment, "Server error", e);
                            this.errorMessage.setText(getString(R.string.server_error));
                        }
                    });
            this.compositeDisposable.add(disposable);
        } else {
            this.errorMessage.setText(getString(PasswordValidator.getErrorMessageCode()));
        }
    }
}
