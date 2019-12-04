/*
 * UserStatusDTO
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Status Object
 */
public class UserStatusDTO implements Serializable{
    
    private int status_id = 0;
    private String emp_status = "";

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }


    
    
    
}
