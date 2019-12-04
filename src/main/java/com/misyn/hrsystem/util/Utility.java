/*
 * Utility
 */
package com.misyn.hrsystem.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.apache.log4j.Logger;

/**
 *
 * @author Shanaka
 * This class responsible for get date and time as needed 
 */
public class Utility implements Serializable{

    private static final Logger log = Logger.getLogger(Utility.class);
    
    private static Utility utility = null;

    public static Utility getInstance() {
        if (utility == null) {
            utility = new Utility();
        }
        return utility;
    }

    /**
     * Returns formatted date/time
     *
     * @param <tt>fmt</tt> Date/time format
     * @return Date/time as <tt>String</tt>
     */
    private static String javaDate(String fmt) {
        String dd;
        TimeZone gmt530 = TimeZone.getTimeZone("GMT");
        gmt530.setRawOffset((11 * 30) * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        formatter.setTimeZone(gmt530);
        dd = formatter.format(new java.util.Date());
        return dd;
    }

    /**
     * Return the date in YYYY-MM-DD format
     *
     * @return Formatted date as <tt>String</tt>
     */
    public static String sysDate() {
        return javaDate("yyyy-MM-dd");
    }
    
        /**
     * Return the date in the given format
     *
     * @param <tt>dateFormat</tt> Date format
     * @return Formatted date <tt>String</tt>
     */
    public static String sysDate(String dateFormat) {
        return javaDate(dateFormat);
    }

    /**
     *
     * Return the time in HH:MM:SS format
     *
     * @return Formatted time as <tt>String</tt>
     */
    public static String sysTime() {
        return javaDate("HH:mm:ss");
    }

}
