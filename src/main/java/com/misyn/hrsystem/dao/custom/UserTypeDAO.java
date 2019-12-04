/*
 * 
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Type DAO interface contains all methods related to User Type DAO Function
 */
public interface UserTypeDAO extends BaseDAO<UserTypeDTO>{
    
    public List<UserTypeDTO> getUserTypeList(UserTypeDTO userTypeDTO) throws JDBCTemplateException;
    
}
