/*
 * User Status Service
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * User Status interface contains all methods related to User Status Function
 */
public interface UserStatusService extends BaseService<UserStatusDTO>{
    
    public List<UserStatusDTO> getUserStatusList(UserStatusDTO userStatusDTO)throws JDBCTemplateException;
    
    public List<UserStatusDTO> getStatusDetail(UserStatusDTO statusDTO, int statCode)throws JDBCTemplateException;
    
}
