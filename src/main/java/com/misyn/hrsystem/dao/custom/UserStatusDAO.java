/*
 * User Status DAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Status DAO interface contains all methods related to User Status DAO Function
 */
public interface UserStatusDAO extends BaseDAO<UserStatusDTO>{
    
    public List<UserStatusDTO> getUserStatus(UserStatusDTO userStatusDTO) throws JDBCTemplateException;
    
    public List<UserStatusDTO> getStatDetail(UserStatusDTO userStatusDTO, int statusCode)throws JDBCTemplateException;
    
}
