/*
 DTO Travel
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Taxi Object
 */
public class TaxiDTO {

    private int taxi_id;
    private String project_name;
    private double amount;
    private String start;
    private String end;
    private double distance;
    private String image;
    private String input_date;
    private String auth_status;
    private String auth_date;
    private String auth_user;
    private String user_code;
    
     private Integer[] taxiID;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTaxi_id() {
        return taxi_id;
    }

    public void setTaxi_id(int taxi_id) {
        this.taxi_id = taxi_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getAuth_status() {
        return auth_status;
    }

    public void setAuth_status(String auth_status) {
        this.auth_status = auth_status;
    }

    public String getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(String auth_date) {
        this.auth_date = auth_date;
    }

    public String getAuth_user() {
        return auth_user;
    }

    public void setAuth_user(String auth_user) {
        this.auth_user = auth_user;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public Integer[] getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(Integer[] taxiID) {
        this.taxiID = taxiID;
    }
    
    
    

}
