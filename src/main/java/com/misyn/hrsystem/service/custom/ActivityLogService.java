/*
 * ActivityLogService
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.configuration.ActivityLogMessages;
import com.misyn.hrsystem.dto.custom.ActivityLogDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Activity Log service interface contains all methods related to Activity Log Function
 */
public interface ActivityLogService {

    void doLog(String userID, String ipAddress, String description);

    ActivityLogMessages getLogMessages();
    
    public List<ActivityLogDTO> getLog(ActivityLogDTO logDTO)throws JDBCTemplateException;

}
