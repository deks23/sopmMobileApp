package com.project.sopmmobileapp.model.dtos.request;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Parcel
@ToString
public class UserDetails extends BaseObservable {


    @SerializedName("birthday")
    Date birthday;

    @SerializedName("gender")
    String gender;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
