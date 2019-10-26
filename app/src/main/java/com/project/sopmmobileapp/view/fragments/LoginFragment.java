package com.project.sopmmobileapp.view.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.project.sopmmobileapp.MainActivity;
import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.applications.VoteApplication;
import com.project.sopmmobileapp.databinding.LoginFragmentBinding;
import com.project.sopmmobileapp.model.bundlers.CredentialBundler;
import com.project.sopmmobileapp.model.di.clients.LoginClient;
import com.project.sopmmobileapp.model.dtos.BaseResponse;
import com.project.sopmmobileapp.model.dtos.Credentials;
import com.project.sopmmobileapp.model.exceptions.UserNotFoundException;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginFragment extends Fragment {

    private final static String TAG = LoginFragment.class.getName();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    LoginClient loginClient;

    @State(CredentialBundler.class)
    Credentials credentials = new Credentials();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        LoginFragmentBinding loginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment,
                container, false);
        loginFragmentBinding.setCredentials(this.credentials);
        View mainView = loginFragmentBinding.getRoot();
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
        Disposable disposable = this.loginClient.login(this.credentials)
                .subscribe((BaseResponse authenticationResponse) -> {
                    Log.i(TAG, "Logged in");
//                    TokenStore.saveToken(authenticationResponse.getToken());
//                    CredentialsStore.saveCredentials(this.credentials);
                    ((MainActivity) getActivity()).setBaseForBackStack(new MainViewPagerFragment());
                }, (Throwable e) -> {
                    if (e instanceof UserNotFoundException) {
                        Log.i(TAG, "User not found", e);
                        this.errorMessage.setText(getString(R.string.login_error));
                    } else {
                        Log.i(TAG, "Server error", e);
                        this.errorMessage.setText(getString(R.string.server_error));
                    }
                });

        this.compositeDisposable.add(disposable);
    }
}
