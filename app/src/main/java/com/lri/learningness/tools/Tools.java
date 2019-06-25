package com.lri.learningness.tools;

import java.text.DateFormat;
import java.util.Date;

public class Tools {
    public static String dateToString(Date date) {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
    }
}
