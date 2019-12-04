/*
 * LeaveBalence
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Leave Balance
 */
public class LeaveBalanceDTO {
    
    private int leave_balance_id;
    private double days;
    private int ltype_id;
    private String user_code;
    private String ltype_name;

    public int getLeave_balance_id() {
        return leave_balance_id;
    }

    public void setLeave_balance_id(int leave_balance_id) {
        this.leave_balance_id = leave_balance_id;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
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

    public String getLtype_name() {
        return ltype_name;
    }

    public void setLtype_name(String ltype_name) {
        this.ltype_name = ltype_name;
    }
    
    
    
}
