/*
 * User Status Service Impl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.UserStatusDAO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.UserStatusService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * Save and update User Status
 */
@Service
public class UserStatusServiceImpl implements UserStatusService{
    
    private static final Logger LOGGER = Logger.getLogger(UserStatusServiceImpl.class);
    
    @Autowired
    private UserStatusDAO userStatusDAO;
    

    /**
     * 
     * @param userStatusDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserStatusDTO> getUserStatusList(UserStatusDTO userStatusDTO) throws JDBCTemplateException {
        return userStatusDAO.getUserStatus(userStatusDTO);
    }

    /**
     * 
     * @param statusDTO
     * @param statCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserStatusDTO> getStatusDetail(UserStatusDTO statusDTO, int statCode) throws JDBCTemplateException {
        return userStatusDAO.getStatDetail(statusDTO, statCode);
    }

    /**
     * 
     * @param userStat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserStatusDTO insert(UserStatusDTO userStat) throws JDBCTemplateException {
        UserStatusDTO userStaDTO = null;
        try {
           userStaDTO = userStatusDAO.saveMaster(userStat);
                   
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userStaDTO;
    }

    /**
     * 
     * @param userStat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserStatusDTO update(UserStatusDTO userStat) throws JDBCTemplateException {
        UserStatusDTO userStatusDTO = null;
        try {
            userStatusDTO = userStatusDAO.updateMaster(userStat);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userStatusDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserStatusDTO delete(UserStatusDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
