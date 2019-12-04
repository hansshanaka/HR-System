/*
 * 
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Role DAO interface contains all methods related to User Role DAO Function
 */
public interface UserRoleDAO extends BaseDAO<UserRoleDTO>{
    
    public List<UserRoleDTO> getUserRoleList(String id,UserRoleDTO userRoleDTO)throws JDBCTemplateException;
    
    public UserRoleDTO insertRoleOption(UserRoleDTO userRoleDTO, int menuId)throws JDBCTemplateException;
    
    public List<UserRoleDTO> getUserRoleDetails(UserRoleDTO userRoleDTO, String roleID)throws JDBCTemplateException;
    
    public List<UserRoleDTO> getAllRoleList(UserRoleDTO userRoleDTO)throws JDBCTemplateException;
    
}
