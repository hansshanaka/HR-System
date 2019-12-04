/*
 * LeaveDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.LeaveTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Leave Type DAO interface contains all methods related to Leave type Function
 */
public interface LeaveTypeDAO extends BaseDAO<LeaveTypeDTO>{
    
    public List<LeaveTypeDTO> getLeaveTypeList(LeaveTypeDTO leaveTypeDTO) throws JDBCTemplateException;
    
}
