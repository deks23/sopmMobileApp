package com.project.sopmmobileapp.model.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Vote {

    private Long id;
    private User user;

    private Option option;
    private LocalDateTime createDate;



}
