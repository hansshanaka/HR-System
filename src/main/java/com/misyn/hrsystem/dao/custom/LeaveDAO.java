/*
 * LeaveDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Leave DAO interface contains all methods related to Leave Function
 */
public interface LeaveDAO extends BaseDAO<LeaveDTO>{
    
    public int checkLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public LeaveDTO updateLeaveBalance(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public List<LeaveDTO> getLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public boolean deleteLeave(int leaveID)throws JDBCTemplateException;
    
    public List<LeaveDTO> getPendingLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO authorizeLeave(LeaveDTO leaveDTO,int leaveID)throws JDBCTemplateException;
    
    public List<LeaveDTO> getAuthLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public List<LeaveDTO> getPendingCancelLeave(LeaveDTO leaveDTO)throws JDBCTemplateException;
    
    public LeaveDTO authorizeCancelLeave(LeaveDTO leaveDTO,int leaveID)throws JDBCTemplateException;
    
    public int getLeaveBalLID(int LeaveID) throws JDBCTemplateException;
    
    public int getNoOfDaysLID(int LeaveID) throws JDBCTemplateException;
    
    public LeaveDTO rejectCancelLeave(LeaveDTO leaveDTO,int leaveID)throws JDBCTemplateException;
    
    public LeaveDTO rejectLeave(LeaveDTO leaveDTO,int leaveID)throws JDBCTemplateException;
    
    public List<LeaveDTO> getApprovedLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public List<LeaveDTO> getReportLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException;
    
    public String getUserCode(int leaveID) throws JDBCTemplateException;
    
}
