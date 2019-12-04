/*
 *
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.LeaveBalanceDAO;
import com.misyn.hrsystem.dto.custom.LeaveBalanceDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.LeaveBalanceService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * provide and save data related to Leave balance for each user
 */
@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService{
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private LeaveBalanceDAO leaveBalanceDAO;

    /**
     * Get leave Balance List
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
     @Override
    public List<LeaveBalanceDTO> getLeaveBalanceList(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
        return leaveBalanceDAO.getLeaveBalanceList(leaveBalanceDTO);
    }

    /**
     * Save Leave Balance related to employee
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveBalanceDTO insert(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
        return leaveBalanceDAO.saveMaster(leaveBalanceDTO);
    }

    /**
     * Update Leave Balance 
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveBalanceDTO update(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
        return leaveBalanceDAO.updateMaster(leaveBalanceDTO);
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveBalanceDTO delete(LeaveBalanceDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get leave Balance Details
     * @param leaveBalanceDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveBalanceDTO> getLeaveBalDetail(LeaveBalanceDTO leaveBalanceDTO, int leaveID) throws JDBCTemplateException {
        return leaveBalanceDAO.getLeaveBalDetail(leaveBalanceDTO, leaveID);
    }

    /**
     * check leave balance
     * @param uCode
     * @param LeaveType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkExistLBal(String uCode, int LeaveType) throws JDBCTemplateException {
         return leaveBalanceDAO.checkExistLBal(uCode, LeaveType);
    }


    
}
