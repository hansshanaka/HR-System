/*
 * MedicalDTO
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Medical Object
 */
public class MedicalDTO {
    
    private int medical_id;
    private String description;
    private double amount;
    private String image;
    private String auth_status;
    private String auth_date;
    private String auth_user;
    private String input_date;
    private String user_code;
    
     private Integer[] medicalID;

    public int getMedical_id() {
        return medical_id;
    }

    public void setMedical_id(int medical_id) {
        this.medical_id = medical_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public Integer[] getMedicalID() {
        return medicalID;
    }

    public void setMedicalID(Integer[] medicalID) {
        this.medicalID = medicalID;
    }
    
    
    
    
}
