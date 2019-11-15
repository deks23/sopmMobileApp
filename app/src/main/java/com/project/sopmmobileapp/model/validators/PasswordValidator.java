package com.project.sopmmobileapp.model.validators;


import com.project.sopmmobileapp.R;
import com.project.sopmmobileapp.model.request.Credentials;
import com.project.sopmmobileapp.model.request.RegisterCredentials;

import java.util.HashSet;
import java.util.Set;

public class PasswordValidator {

    private static int errorMessageCode;

    public static boolean valid(RegisterCredentials registerCredentials) {
        String password = registerCredentials.getPassword();
        String repeatPassword = registerCredentials.getRepeatPassword();
        String username = registerCredentials.getUsername();
        Set<Boolean> validateSet = new HashSet<>();
        validateSet.add(checkIsEmptyFields(username, password, repeatPassword));
        validateSet.add(checkIsNotTheSamePasswords(password, repeatPassword));
        return !validateSet.contains(true);
    }

    public static boolean valid(Credentials credentials) {
        String password = credentials.getPassword();
        String username = credentials.getUsername();
        Set<Boolean> validateSet = new HashSet<>();
        validateSet.add(checkIsEmptyFields(username, password));
        return !validateSet.contains(true);
    }

    public static Credentials toCredential(RegisterCredentials registerCredentials) {
        Credentials credential = new Credentials();
        credential.setUsername(registerCredentials.getUsername());
        credential.setPassword(registerCredentials.getPassword());
        return credential;
    }


    private static boolean checkIsEmptyFields(String username, String password, String repeatPassword) {
        if (password.isEmpty() || repeatPassword.isEmpty() || username.isEmpty()) {
            errorMessageCode = R.string.empty_fields;
            return true;
        }
        return false;
    }

    private static boolean checkIsEmptyFields(String username, String password) {
        if (password.isEmpty() || username.isEmpty()) {
            errorMessageCode = R.string.empty_fields;
            return true;
        }
        return false;
    }

    private static boolean checkIsNotTheSamePasswords(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)){
            errorMessageCode = R.string.uncorrect_password;
            return true;
        }
        return false;
    }

    public static int getErrorMessageCode() {
        return errorMessageCode;
    }
}
