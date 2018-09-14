/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Class with helper methods for date and time
 *
 * @author Charles Hamilton
 */
public class DateTime {

    /**
     * Converts the time from one time zone to another
     *
     * @param fromCalendar the original date and time
     * @param to the time zone to convert it to
     * @return Calendar
     */
    public static Calendar convertTimeZone(Calendar fromCalendar, TimeZone to) {
        TimeZone fromTimeZone = fromCalendar.getTimeZone();
        TimeZone toTimeZone = to;

        fromCalendar.setTimeZone(fromTimeZone);
        fromCalendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
        if (fromTimeZone.inDaylightTime(fromCalendar.getTime())) {
            fromCalendar.add(Calendar.MILLISECOND, fromCalendar.getTimeZone().getDSTSavings() * -1);
        }

        fromCalendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
        if (toTimeZone.inDaylightTime(fromCalendar.getTime())) {
            fromCalendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }

        System.out.println(fromCalendar.getTime());
        return fromCalendar;
    }
}
