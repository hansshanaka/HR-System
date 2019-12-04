/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

import com.misyn.hrsystem.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author Shanaka
 * Login Object
 */
public class LoginDTO implements Serializable{
    
    private String userId = AppConstant.STRING_EMPTY;
    private String loginPassword = AppConstant.STRING_EMPTY;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    
    
}
