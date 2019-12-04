/*
 * LeaveService
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.LeaveTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Leave type service interface contains all methods related to Leave type Function
 */
public interface LeaveTypeService extends BaseService<LeaveTypeDTO>{
    
    public List<LeaveTypeDTO> getLeaveTypeList(LeaveTypeDTO leaveTypeDTO) throws JDBCTemplateException;
    
    
    
}
