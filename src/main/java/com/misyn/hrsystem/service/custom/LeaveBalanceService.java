/*
 * 
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.LeaveBalanceDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 *  Leave Balance service interface contains all methods related to Leave Balance Function
 */
public interface LeaveBalanceService extends BaseService<LeaveBalanceDTO>{
    
    public List<LeaveBalanceDTO> getLeaveBalanceList(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException;
    
    public List<LeaveBalanceDTO> getLeaveBalDetail(LeaveBalanceDTO leaveBalanceDTO, int leaveID)throws JDBCTemplateException;
    
    public int checkExistLBal(String uCode, int LeaveType)throws JDBCTemplateException;
    
}
