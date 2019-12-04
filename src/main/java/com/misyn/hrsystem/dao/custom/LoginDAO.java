/*
 * LoginDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;

/**
 *
 * @author Shanaka
 * Login DAO interface contains all methods related to Login Function
 */
public interface LoginDAO {
    
    public UserDTO checkLogin(UserDTO loginDTO)throws JDBCTemplateException;
    
}
