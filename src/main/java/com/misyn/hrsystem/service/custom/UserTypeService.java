/*
 * 
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * User type interface contains all methods related to User type Function
 */
public interface UserTypeService extends BaseService<UserTypeDTO>{
    
    public List<UserTypeDTO> getUserTypeList(UserTypeDTO userTypeDTO) throws JDBCTemplateException;
    
}
