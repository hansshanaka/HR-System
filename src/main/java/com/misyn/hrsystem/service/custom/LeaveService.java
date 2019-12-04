/*
 * LeaveService
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Leave service interface contains all methods related to Leave Function
 */
public interface LeaveService extends BaseService<LeaveDTO>{
    
    public int checkLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public LeaveDTO updateLeaveBalance(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public List<LeaveDTO> getLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public LeaveDTO deleteLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public List<LeaveDTO> getPendingLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO authorizeLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public List<LeaveDTO> getAuthLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public List<LeaveDTO> getPendingCancelLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO authorizeCancelLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO rejectCancelLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO rejectLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public List<LeaveDTO> getApprovedLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public List<LeaveDTO> getReportLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public String getUserCode(int leaveID) throws JDBCTemplateException;

    
}
