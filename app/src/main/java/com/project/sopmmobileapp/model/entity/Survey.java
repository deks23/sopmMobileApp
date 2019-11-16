package com.project.sopmmobileapp.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Survey {

    private Long id;

    private User owner;
    private String question;
    private LocalDateTime finishTime;

    private Category category;
    private double longitude;
    private double latitude;
    private long counter;
    private LocalDateTime createDate;

    private List<Option> options;
    private List<Vote> answers;


}
