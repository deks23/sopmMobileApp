package com.project.sopmmobileapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NeightborhoodRequest {

    private static final int DISTANCE_KM = 10;
    private int radius = DISTANCE_KM ;
    private double longitude;
    private double latitude;
}
