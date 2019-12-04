/*
 * ActivityLogDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dto.custom.ActivityLogDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Activity Log DAO interface contains all methods related to Activity Log Function
 */
public interface ActivityLogDAO {
    
    void doLog(String userID, String ipAddress, String description);
    
    public List<ActivityLogDTO> getLog(ActivityLogDTO logDTO)throws JDBCTemplateException;
}
