/*
 * ActivityLogDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.ActivityLogDAO;
import com.misyn.hrsystem.dto.custom.ActivityLogDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.AppConstant.AND;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_ACTIVITY_LOG;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LOG_LIST;
import com.misyn.hrsystem.util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save activity Details deal with activity data base table
 */
@Repository
public class ActivityLogDAOImpl implements ActivityLogDAO{
    
    private static final Logger LOGGER = Logger.getLogger(UserStatusDAOImpl.class);
    
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param userID
     * @param ipAddress
     * @param description 
     */
    @Override
    public void doLog(String userID, String ipAddress, String description) {
        
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_ACTIVITY_LOG);
                    statement.setString(1, Utility.sysDate("yyyy-MM-dd"));
                    statement.setString(2, Utility.sysDate("HH:mm:ss"));
                    statement.setString(3, userID);
                    statement.setString(4, ipAddress);
                    statement.setString(5, description);
                    return statement;
                }

            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * 
     * @param logDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<ActivityLogDTO> getLog(ActivityLogDTO logDTO) throws JDBCTemplateException {
        
        String query_log_list = SELECT_LOG_LIST;

        ArrayList arguments = new ArrayList();

        query_log_list = (!logDTO.getUser_code().equals("")
                ? query_log_list.concat(AND + "al.user_code = ?") : query_log_list);

        arguments.add(logDTO.getStart_date());
        arguments.add(logDTO.getEnd_date());

        if (!logDTO.getUser_code().equals("")) {
            arguments.add(logDTO.getUser_code());
        }
        
        return jdbcTemplate.query(query_log_list, arguments.toArray(), new BeanPropertyRowMapper(ActivityLogDTO.class)); 
    }
    
}
