/*
 * SalaryDTO
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Salary Object
 */
public class SalaryDTO {

    private int salary_id;
    private double attend_days;
    private double leave_days;
    private int year;
    private int month;
    private double salary;
    private String input_date;
    private String input_user;
    private String user_code;
    private String user_name;
    private String start_date;
    private String end_date;

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }

    public double getAttend_days() {
        return attend_days;
    }

    public void setAttend_days(double attend_days) {
        this.attend_days = attend_days;
    }

    public double getLeave_days() {
        return leave_days;
    }

    public void setLeave_days(double leave_days) {
        this.leave_days = leave_days;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getInput_user() {
        return input_user;
    }

    public void setInput_user(String input_user) {
        this.input_user = input_user;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
