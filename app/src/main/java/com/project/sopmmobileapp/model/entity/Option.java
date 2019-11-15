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

}
