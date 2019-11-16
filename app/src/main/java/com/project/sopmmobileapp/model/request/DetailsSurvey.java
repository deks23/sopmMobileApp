package com.project.sopmmobileapp.model.request;

import com.project.sopmmobileapp.model.entity.Category;
import com.project.sopmmobileapp.model.entity.Option;
import com.project.sopmmobileapp.model.entity.Vote;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailsSurvey {

    private String question;
    private String state;

    private Category category;
    private double longitude;
    private double latitude;

    private long counter;
    private Date finishTime;
    private Date createDate;

    private List<Option> options;
    private List<Vote> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Vote> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Vote> answers) {
        this.answers = answers;
    }
}
