/*
 * LeaveBalanceDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.LeaveBalanceDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * LeaveBalance DAO interface contains all methods related to Leave Balance Function
 */
public interface LeaveBalanceDAO extends BaseDAO<LeaveBalanceDTO>{
    
    public List<LeaveBalanceDTO> getLeaveBalanceList(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException;
    
    public List<LeaveBalanceDTO> getLeaveBalDetail(LeaveBalanceDTO leaveBalanceDTO, int leaveID)throws JDBCTemplateException;
    
    public int checkExistLBal(String uCode, int LeaveType)throws JDBCTemplateException;
    
}
