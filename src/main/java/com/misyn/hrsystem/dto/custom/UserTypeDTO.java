/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Type Object
 */
public class UserTypeDTO implements Serializable{
    
    private String user_type_id;
    private String type_name;

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }



    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
    
    
}
