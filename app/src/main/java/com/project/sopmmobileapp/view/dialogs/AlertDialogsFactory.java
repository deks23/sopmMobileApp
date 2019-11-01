package com.project.sopmmobileapp.view.dialogs;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import com.project.sopmmobileapp.view.activities.MainActivity;
import com.project.sopmmobileapp.view.fragments.FragmentTags;
import com.project.sopmmobileapp.view.fragments.LoginFragment;

import java.util.Objects;

public class AlertDialogsFactory {

    public static AlertDialog createLogoutAlertDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        return builder.setTitle("Confirm you log out")
                .setMessage("Are you sure, You want log out?")
                .setPositiveButton("Yes", (arg0, arg1) -> {
                    ((MainActivity) Objects.requireNonNull(activity)).clearBackStack();
                    ((MainActivity) Objects.
                            requireNonNull(activity)).setBaseForBackStack(new LoginFragment(),
                            FragmentTags.LoginFragment);
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create();
    }

    public static AlertDialog createExitAlertDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        return builder.setTitle("Confirm you exit")
                .setMessage("Are you sure, You want exit?")
                .setPositiveButton("Yes", (arg0, arg1) -> {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create();
    }
}
