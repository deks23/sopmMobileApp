package com.project.sopmmobileapp.model.request;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;
import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Parcel
@ToString
public class UserDetails extends BaseObservable {

    @SerializedName("birthday")
    LocalDateTime birthday;

    @SerializedName("gender")
    String gender;

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
