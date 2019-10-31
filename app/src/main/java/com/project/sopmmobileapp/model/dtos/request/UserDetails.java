package com.project.sopmmobileapp.model.dtos.request;

import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

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


    @SerializedName("age")
    int age;

    @SerializedName("gender")
    String gender;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, int value) {
        view.setText(Integer.toString(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view) {
        try {
            return Integer.parseInt(view.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
