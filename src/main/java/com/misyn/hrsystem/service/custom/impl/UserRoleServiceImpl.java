/*
 * 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.UserRoleDAO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.UserRoleService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * Inset User Role 
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = Logger.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public List<UserRoleDTO> getUserRoleList(String id, UserRoleDTO userRoleDTO) throws JDBCTemplateException {
        return userRoleDAO.getUserRoleList(id, userRoleDTO);
    }

    /**
     * Save User Role
     * @param userRole
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO insert(UserRoleDTO userRole) throws JDBCTemplateException {
        UserRoleDTO userRoleDTO = null;
        try {
            userRoleDTO = userRoleDAO.saveMaster(userRole);

            Integer[] menuItemArray = userRole.getMenuItem();
            for(int i = 0; i < menuItemArray.length; i++) {
                userRoleDTO = userRoleDAO.insertRoleOption(userRole, menuItemArray[i]);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userRoleDTO;
    }

    /**
     * Update User Role
     * @param userRole
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO update(UserRoleDTO userRole) throws JDBCTemplateException {
        UserRoleDTO userRoleDTO = null;
        boolean deleteStatus = false;
        try {
            userRoleDTO = userRoleDAO.updateMaster(userRole);

            if (userRoleDTO != null) {
                deleteStatus = userRoleDAO.deleteMaster(userRole.getUser_role_id());
            }

            if (deleteStatus) {
                Integer[] menuItemArray = userRole.getMenuItem();
                for(int i = 0; i < menuItemArray.length; i++) {
                    userRoleDTO = userRoleDAO.insertRoleOption(userRole, menuItemArray[i]);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return userRoleDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO delete(UserRoleDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param userRoleDTO
     * @param userRoleID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserRoleDTO> getUserRoleDetails(UserRoleDTO userRoleDTO, String userRoleID) throws JDBCTemplateException {
        return userRoleDAO.getUserRoleDetails(userRoleDTO, userRoleID);
    }

    /**
     * 
     * @param userRoleDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserRoleDTO> getAllRoleList(UserRoleDTO userRoleDTO) throws JDBCTemplateException {
        return userRoleDAO.getAllRoleList(userRoleDTO);
    }

}
