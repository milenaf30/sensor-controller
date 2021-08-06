package com.milena.sensorcontroller.common.date;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {

    public static Date now() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static Date yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
