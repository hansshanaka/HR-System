/*
 * Leave DTO
 */
package com.misyn.hrsystem.dto.custom;



/**
 *
 * @author Shanaka
 * Leave DTO
 */
public class LeaveDTO {
    
    private int leave_id;
    private String start_date;
    private String end_date;
    private double no_of_days;
    private String input_date;
    private String auth_state;
    private String auth_id;
    private String auth_date;
    private int ltype_id;
    private String user_code;
    private double leave_balance;
    private String ltype_name;
    private String input_state;
    
    private Integer[] leaveID;
    private String app_user;
    private String auth_user;
            
    

    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
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

    public double getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(double no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getAuth_state() {
        return auth_state;
    }

    public void setAuth_state(String auth_state) {
        this.auth_state = auth_state;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(String auth_date) {
        this.auth_date = auth_date;
    }

    public int getLtype_id() {
        return ltype_id;
    }

    public void setLtype_id(int ltype_id) {
        this.ltype_id = ltype_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public double getLeave_balance() {
        return leave_balance;
    }

    public void setLeave_balance(double leave_balance) {
        this.leave_balance = leave_balance;
    }

    public String getLtype_name() {
        return ltype_name;
    }

    public void setLtype_name(String ltype_name) {
        this.ltype_name = ltype_name;
    }

    public Integer[] getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Integer[] leaveID) {
        this.leaveID = leaveID;
    }

    public String getInput_state() {
        return input_state;
    }

    public void setInput_state(String input_state) {
        this.input_state = input_state;
    }

    public String getApp_user() {
        return app_user;
    }

    public void setApp_user(String app_user) {
        this.app_user = app_user;
    }

    public String getAuth_user() {
        return auth_user;
    }

    public void setAuth_user(String auth_user) {
        this.auth_user = auth_user;
    }


    
    
    
}
