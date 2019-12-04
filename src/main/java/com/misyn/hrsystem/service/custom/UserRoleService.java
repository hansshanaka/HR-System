/*
 * 
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Role interface contains all methods related to User Role Function
 */
public interface UserRoleService extends BaseService<UserRoleDTO>{
    
    public List<UserRoleDTO> getUserRoleList(String id,UserRoleDTO userRoleDTO)throws JDBCTemplateException;
    
    public List<UserRoleDTO> getUserRoleDetails(UserRoleDTO userRoleDTO,String userRoleID)throws JDBCTemplateException;
    
    public List<UserRoleDTO> getAllRoleList(UserRoleDTO userRoleDTO)throws JDBCTemplateException;
    
}
