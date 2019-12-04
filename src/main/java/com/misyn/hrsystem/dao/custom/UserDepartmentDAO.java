/*
 * User Department DAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Department DAO interface contains all methods related to User Department Function
 */
public interface UserDepartmentDAO extends BaseDAO<UserDepartmentDTO>{
    
    public List<UserDepartmentDTO> getUserDepartment(UserDepartmentDTO userDepartmentDTO) throws JDBCTemplateException;
    
    public List<UserDepartmentDTO> getDepDetail(UserDepartmentDTO departmentDTO, int depCode)throws JDBCTemplateException;
    
}
