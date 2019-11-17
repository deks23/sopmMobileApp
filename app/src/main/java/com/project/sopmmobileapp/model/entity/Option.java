package com.project.sopmmobileapp.model.entity;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Option {

    private Survey survey;
    private String value;
    private LocalDateTime createDate;
    private OptionResult result;

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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

    public OptionResult getResult() {
        return result;
    }

    public void setResult(OptionResult result) {
        this.result = result;
    }
}
