package com.project.sopmmobileapp.model.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String gender;
    private Date birthday;
    private boolean loggedIn;
    private boolean needData;
    private List<Survey> surveys;
}
