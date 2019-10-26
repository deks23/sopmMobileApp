package com.project.sopmmobileapp.model.bundlers;

import android.os.Bundle;

import com.project.sopmmobileapp.model.dtos.Credentials;

import org.parceler.Parcels;

import icepick.Bundler;

public class CredentialBundler implements Bundler<Credentials> {

    @Override
    public void put(String s, Credentials credentials, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(credentials));
    }

    @Override
    public Credentials get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
