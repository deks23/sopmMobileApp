package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.LoginFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.ABundler;
import com.project.sopmmobileapp.model.di.clients.LoginClient;
import com.project.sopmmobileapp.model.exceptions.BadRequestException;
import com.project.sopmmobileapp.model.exceptions.LoginException;
import com.project.sopmmobileapp.model.request.Credentials;
import com.project.sopmmobileapp.model.response.LoginResponse;
import com.project.sopmmobileapp.model.store.CredentialsStore;
import com.project.sopmmobileapp.model.store.TokenStore;
import com.project.sopmmobileapp.model.validators.PasswordValidator;
import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithExitDialog;
import com.project.sopmmobileapp.view.fragments.pager.MainViewPagerFragment;

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

public class LoginFragment extends Fragment implements BackWithExitDialog {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    LoginClient loginClient;

    @State(ABundler.class)
    Credentials credentials = new Credentials();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        LoginFragmentBinding loginFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.login_fragment,
                container, false);

        View mainView = loginFragmentBinding.getRoot();
        loginFragmentBinding.setCredentials(this.credentials);
        ButterKnife.bind(this, mainView);

        VoteApplication.getClientsComponent().inject(this);
        return mainView;
    }


    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @OnClick(R.id.login_button)
    void login() {
        if (PasswordValidator.valid(this.credentials)) {
            Disposable disposable = this.loginClient.login(this.credentials)
                    .subscribe((LoginResponse authenticationResponse) -> {
                        Log.i(FragmentTags.LoginFragment, "Logged in");


                        TokenStore.saveToken(authenticationResponse.getToken());
                        CredentialsStore.saveCredentials(this.credentials);
                        if (authenticationResponse.isNeedData()) {
                            ((MainActivity) Objects.
                                    requireNonNull(getActivity()))
                                    .putFragment(new UserDetailsFragment(),
                                            FragmentTags.UserDetailsFragment);
                        } else {
                            ((MainActivity) Objects.requireNonNull(getActivity())).setBaseForBackStack(new MainViewPagerFragment(),
                                    FragmentTags.MainViewPagerFragment);
                        }

                    }, (Throwable e) -> {
                        if (e instanceof LoginException) {
                            Log.i(FragmentTags.LoginFragment, "LoginException", e);
                            this.errorMessage.setText(getString(R.string.login_error));
                        } else if (e instanceof BadRequestException) {
                            Log.i(FragmentTags.LoginFragment, "Server error", e);
                            this.errorMessage.setText(getString(R.string.server_error));
                        }
                    });

            this.compositeDisposable.add(disposable);
        } else {
            this.errorMessage.setText(getString(PasswordValidator.getErrorMessageCode()));
        }
    }

    @OnClick(R.id.sign_button)
    void goOnRegisterFragment() {
        ((MainActivity) Objects.
                requireNonNull(getActivity())).
                putFragment(new RegisterFragment(),
                        FragmentTags.RegisterFragment);
    }
}
