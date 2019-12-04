/*
 * User Department DTO
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Department Object
 */
public class UserDepartmentDTO implements Serializable{
    
    private int department_code = 0;
    private String department_name = "";

    public int getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(int department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    
    
    
}
