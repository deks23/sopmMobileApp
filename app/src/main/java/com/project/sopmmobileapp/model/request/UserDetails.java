package com.project.sopmmobileapp.model.request;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.parceler.Parcel;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Parcel
@ToString
public class UserDetails extends BaseObservable {

    @SerializedName("birthday")
    DateTime birthday;

    @SerializedName("gender")
    String gender;

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
