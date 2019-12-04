/*
 * User Department Service
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Department interface contains all methods related to User Department Function
 */
public interface UserDepartmentService extends BaseService<UserDepartmentDTO>{
    
    public List<UserDepartmentDTO> getUserDepartmentList(UserDepartmentDTO userDepartmentDTO)throws JDBCTemplateException;
    
    public List<UserDepartmentDTO> getDepartmentDetail(UserDepartmentDTO departmentDTO, int depCode)throws JDBCTemplateException;
    
}
