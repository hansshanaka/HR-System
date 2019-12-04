/*
 * 
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Category interface contains all methods related to User Category Function
 */
public interface UserCategoryService extends BaseService<UserCategoryDTO>{
    
    public List<UserCategoryDTO> getUserCategoryList(UserCategoryDTO userCategoryDTO)throws JDBCTemplateException;
    
    public List<UserCategoryDTO> getCategoryDetail(UserCategoryDTO categoryDTO, int catCode)throws JDBCTemplateException;
    
}
