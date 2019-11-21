package com.project.sopmmobileapp.model.response;

import com.google.gson.annotations.SerializedName;


import org.joda.time.LocalDateTime;
import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Parcel
public class OptionResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("value")
    private String value;

    @SerializedName("createDate")
    private LocalDateTime createDate;

    @SerializedName("result")
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
