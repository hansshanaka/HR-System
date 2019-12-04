
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.configuration.ActivityLogMessages;
import com.misyn.hrsystem.dao.custom.ActivityLogDAO;
import com.misyn.hrsystem.dto.custom.ActivityLogDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * ActivityLogServiceImpl
 */
@Service
public class ActivityLogServiceImpl implements ActivityLogService{
    
    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);
    
    @Autowired
    private ActivityLogDAO activityLogDao;

    /**
     * write to log file by passing following parameters
     * @param userID
     * @param ipAddress
     * @param description 
     */
    @Override
    public void doLog(String userID, String ipAddress, String description) {
        activityLogDao.doLog(userID, ipAddress, description);
    }

    /**
     * This method does not call its override from super class
     * @return 
     */
    @Override
    public ActivityLogMessages getLogMessages() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * This method return Log details as object List
     * @param logDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<ActivityLogDTO> getLog(ActivityLogDTO logDTO) throws JDBCTemplateException {
        return activityLogDao.getLog(logDTO);
    }
    
}
