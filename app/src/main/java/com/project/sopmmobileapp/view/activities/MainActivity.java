package com.project.sopmmobileapp.view.activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.view.dialogs.AlertDialogsFactory;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithExitDialog;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithLogOutDialog;
import com.project.sopmmobileapp.view.fragments.Iback.BackWithRemoveFromStack;
import com.project.sopmmobileapp.view.fragments.LoginFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import lombok.Getter;

@Getter
public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getName();
    private static String CURRENT_FRAGMENT_TAG = "currentFragment";


    public static MainActivity instance = null;
    private Fragment currentFragment;

    @BindView(R.id.layout_on_fragments)
    public FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().
                    getFragment(savedInstanceState, CURRENT_FRAGMENT_TAG);
        } else {
            this.currentFragment = new LoginFragment();
            this.setBaseForBackStack(this.currentFragment, TAG);
        }
        instance = this;
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,
                CURRENT_FRAGMENT_TAG,
                currentFragment);
    }

    private void setSdkPolicy() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBackPressed() {
        updateCurrentFragment();
        if (currentFragment instanceof BackWithLogOutDialog) {
            AlertDialogsFactory.createLogoutAlertDialog(this).show();
        } else if (currentFragment instanceof BackWithExitDialog) {
            AlertDialogsFactory.createExitAlertDialog(this).show();
        } else if (currentFragment instanceof BackWithRemoveFromStack) {
            AlertDialogsFactory.createSaveAlertDialog(this).show();
        } else if (canBack()) {
            popBackStack();
        }
    }

    public void popBackStack() {
        try {
            super.getSupportFragmentManager().popBackStackImmediate();
            updateCurrentFragment();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private boolean canBack() {
        return super.getSupportFragmentManager().getBackStackEntryCount() > 1;
    }

    public void setBaseForBackStack(final Fragment fragment, String TAG) {
        this.clearBackStack();

        super.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_on_fragments, fragment)
                .addToBackStack(TAG)
                .commit();
        this.currentFragment = fragment;
    }

    public void clearBackStack() {
        while (super.getSupportFragmentManager().popBackStackImmediate()) ;
    }

    public void changeFragment(final Fragment fragment, final String TAG) {
        super.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_on_fragments, fragment)
                .addToBackStack(TAG)
                .commit();
    }

    public void putFragment(final Fragment fragment, final String TAG) {
        try {
            FragmentTransaction ft = super.getSupportFragmentManager().beginTransaction();
            if (currentFragment.isVisible()) {
                ft.detach(currentFragment);
            }

            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.replace(R.id.layout_on_fragments, fragment, TAG);
                ft.addToBackStack(TAG);
                ft.commit();
            }
            updateCurrentFragment();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    public void onPause() {
        super.onPause();
        instance = null;
    }

    private void updateCurrentFragment() {
        List<Fragment> fragments = super.getSupportFragmentManager().getFragments();
        currentFragment = fragments.get(fragments.size() - 1);
    }
}
