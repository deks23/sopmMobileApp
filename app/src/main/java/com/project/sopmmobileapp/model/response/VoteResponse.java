package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Parcel
public class VoteResponse {

    @SerializedName("id")
    Long voteId;

    @SerializedName("numberOfVotes")
    Long numberOfVotes;

}
