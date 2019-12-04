/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

import com.misyn.hrsystem.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Object
 */
public class UserDTO implements Serializable {

    private String user_id = AppConstant.STRING_EMPTY;
    private String user_name = AppConstant.STRING_EMPTY;
    private String password = AppConstant.STRING_EMPTY;
    private String user_code = AppConstant.STRING_EMPTY;
    private String user_type = AppConstant.STRING_EMPTY;
    private String user_role = AppConstant.STRING_EMPTY;
    private String ip_address = AppConstant.STRING_EMPTY;
    private String user_email = AppConstant.STRING_EMPTY;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    
    

}
