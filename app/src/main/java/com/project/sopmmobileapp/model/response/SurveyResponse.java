package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("owner")
    private UserResponse owner;

    @SerializedName("question")
    private String question;

    @SerializedName("finishTime")
    private LocalDateTime finishTime;

    @SerializedName("category")
    private Category category;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("counter")
    private int counter;

    @SerializedName("createDate")
    private LocalDateTime createDate;

    @SerializedName("options")
    private List<OptionResponse> options;

    @SerializedName("answers")
    private List<AnswerResponse> answers;

    public String getState(){
        LocalDateTime now = LocalDateTime.now();
        if(finishTime.isBefore(now)){
            return "ACTIVE";
        }
        return "FINISHED";
    }

}
