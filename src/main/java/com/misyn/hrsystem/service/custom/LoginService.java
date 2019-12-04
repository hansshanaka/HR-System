/*
 * LoginService
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;

/**
 *
 * @author Shanaka
 * Login service interface contains all methods related to Login Function
 */
public interface LoginService {
    
    public UserDTO checkLogin(UserDTO loginDTO)throws JDBCTemplateException;
    
}
