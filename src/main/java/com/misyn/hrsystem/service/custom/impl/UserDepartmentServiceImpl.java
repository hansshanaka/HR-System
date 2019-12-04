/*
 * User Department Service Impl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.UserDepartmentDAO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.UserDepartmentService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 */
@Service
public class UserDepartmentServiceImpl implements UserDepartmentService{
    
    private static final Logger LOGGER = Logger.getLogger(UserDepartmentServiceImpl.class);
    
    @Autowired
    private UserDepartmentDAO userDepartmentDAO;

    /**
     * Save user Department
     * @param userDep
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDepartmentDTO insert(UserDepartmentDTO userDep) throws JDBCTemplateException {
        UserDepartmentDTO userDepDTO = null;
        try {
           userDepDTO = userDepartmentDAO.saveMaster(userDep);
                   
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userDepDTO;
    }

    /**
     * Update user Department 
     * @param userDep
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDepartmentDTO update(UserDepartmentDTO userDep) throws JDBCTemplateException {
        UserDepartmentDTO userDepartmentDTO = null;
        try {
            userDepartmentDTO = userDepartmentDAO.updateMaster(userDep);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userDepartmentDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDepartmentDTO delete(UserDepartmentDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param userDepartmentDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserDepartmentDTO> getUserDepartmentList(UserDepartmentDTO userDepartmentDTO) throws JDBCTemplateException {
        return userDepartmentDAO.getUserDepartment(userDepartmentDTO);
    }

    /**
     * 
     * @param departmentDTO
     * @param depCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserDepartmentDTO> getDepartmentDetail(UserDepartmentDTO departmentDTO, int depCode) throws JDBCTemplateException {
        return userDepartmentDAO.getDepDetail(departmentDTO, depCode);
    }
    
}
