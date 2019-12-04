/*
 * 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.LoginDAO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * User system login check
 */
@Service
public class LoginServiceImpl implements LoginService{
    
    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);
    
    @Autowired
    private LoginDAO loginDAO;

    /**
     * check real user try to login to the system
     * @param loginDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDTO checkLogin(UserDTO loginDTO) throws JDBCTemplateException {
        UserDTO user = new UserDTO();
        try {            
            user = loginDAO.checkLogin(loginDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return user;
    }
    
}
