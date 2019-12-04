/*
 * UserCategory DAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Category DAO interface contains all methods related to User Category Function
 */
public interface UserCategoryDAO extends BaseDAO<UserCategoryDTO>{
    
    public List<UserCategoryDTO> getUserCategoryList(UserCategoryDTO userCategoryDTO) throws JDBCTemplateException;
    
    public List<UserCategoryDTO> getCategDetail(UserCategoryDTO categoryDTO, int catCode)throws JDBCTemplateException;
    
}
