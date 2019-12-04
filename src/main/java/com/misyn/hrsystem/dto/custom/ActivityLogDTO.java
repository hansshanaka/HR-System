/*
 * Activity Log DTO
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Activity Object
 */
public class ActivityLogDTO {
    
    private int activity_log_id;
    private String date;
    private String time;
    private String user_code;
    private String ip;
    private String description;
    private String start_date ="";
    private String end_date = "";

    public int getActivity_log_id() {
        return activity_log_id;
    }

    public void setActivity_log_id(int activity_log_id) {
        this.activity_log_id = activity_log_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    
    
    
}
