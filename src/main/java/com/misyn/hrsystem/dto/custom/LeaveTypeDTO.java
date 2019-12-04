/*
 * ToLeaveTypeDTO
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Leave Type DTO
 */
public class LeaveTypeDTO {
    
    private int ltype_id;
    private String ltype_name;
    private int days;

    public int getLtype_id() {
        return ltype_id;
    }

    public void setLtype_id(int ltype_id) {
        this.ltype_id = ltype_id;
    }

    public String getLtype_name() {
        return ltype_name;
    }

    public void setLtype_name(String ltype_name) {
        this.ltype_name = ltype_name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
    
    
}
