/*
 * 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.UserTypeDAO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.UserTypeService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * User Type Save update
 */
@Service
public class UserTypeServiceImpl implements UserTypeService{
    
    private static final Logger LOGGER = Logger.getLogger(UserTypeServiceImpl.class);
    
    @Autowired
    private UserTypeDAO userTypeDAO;

    /**
     * 
     * @param userTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserTypeDTO> getUserTypeList(UserTypeDTO userTypeDTO) throws JDBCTemplateException {
        return userTypeDAO.getUserTypeList(userTypeDTO);
    }

    /**
     * 
     * @param userType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserTypeDTO insert(UserTypeDTO userType) throws JDBCTemplateException {
        UserTypeDTO userTypeDTO = null;
        
        try {
            userTypeDTO = userTypeDAO.saveMaster(userType);
            
        } catch (Exception e) {
            LOGGER.error(e);
        }        
        return userTypeDTO;
    }

    /**
     * 
     * @param userType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserTypeDTO update(UserTypeDTO userType) throws JDBCTemplateException {
        
        UserTypeDTO userTypeDTO = null;
        try {
            userTypeDTO = userTypeDAO.updateMaster(userType);
        } catch (Exception e) {
            LOGGER.error(e);
        }        
        return userTypeDTO;
    }

    /**
     * 
     * @param userType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserTypeDTO delete(UserTypeDTO userType) throws JDBCTemplateException {
        
        boolean result = false;
        try {
            result = userTypeDAO.deleteMaster(userType.getUser_type_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? userType : null;
    }
    
}
