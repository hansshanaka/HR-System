/*
 * LeaveTypeServiceImpl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.LeaveTypeDAO;
import com.misyn.hrsystem.dto.custom.LeaveTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.LeaveTypeService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * insert, get and update leave type
 */
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService{
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private LeaveTypeDAO leaveTypeDAO;

    /**
     * get Leave Type list
     * @param leaveTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveTypeDTO> getLeaveTypeList(LeaveTypeDTO leaveTypeDTO) throws JDBCTemplateException {
        return leaveTypeDAO.getLeaveTypeList(leaveTypeDTO);
    }

    /**
     * Save leave Type
     * @param lTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveTypeDTO insert(LeaveTypeDTO lTypeDTO) throws JDBCTemplateException {
        LeaveTypeDTO leaveTypeDTO = null;
        
        try {
            leaveTypeDTO = leaveTypeDAO.saveMaster(lTypeDTO);
            
        } catch (Exception e) {
            LOGGER.error(e);
        }        
        return leaveTypeDTO;
    }
    

    /**
     * Update Leave Type
     * @param leaveType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveTypeDTO update(LeaveTypeDTO leaveType) throws JDBCTemplateException {
        LeaveTypeDTO leaveTypeDTO = null;
        try {
            leaveTypeDTO = leaveTypeDAO.updateMaster(leaveType);
        } catch (Exception e) {
            LOGGER.error(e);
        }        
        return leaveTypeDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveTypeDTO delete(LeaveTypeDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
