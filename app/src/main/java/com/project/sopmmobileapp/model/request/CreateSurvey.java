package com.project.sopmmobileapp.model.request;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Parcel
public class CreateSurvey {

    @SerializedName("question")
    private String question;

    @SerializedName("answerOptions")
    private List<String> answerOptions;

    @SerializedName("category")
    private long category;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("finishTime")
    private Date finishTime;


    public CreateSurvey() {
        question = "";
        answerOptions = Collections.singletonList("");
        category = 0;
        latitude = 0.0;
        longitude = 0.0;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }


}
