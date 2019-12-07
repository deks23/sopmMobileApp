package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Parcel
@ToString
public class StatResponse {

    @SerializedName("id")
    Long idOption;

    @SerializedName("value")
    String valueOption;

    @SerializedName("result")
    VoteResponse voteResponse;
}
