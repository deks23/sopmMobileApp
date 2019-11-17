package com.project.sopmmobileapp.model.converters;

import org.joda.time.LocalDateTime;

public class DataConverter {

    public static String toString (LocalDateTime localDateTime){
        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime.getDayOfMonth());
        sb.append("-");
        sb.append(localDateTime.getMonthOfYear());
        sb.append("-");
        sb.append(localDateTime.getYear());
        return sb.toString();
    }
}
