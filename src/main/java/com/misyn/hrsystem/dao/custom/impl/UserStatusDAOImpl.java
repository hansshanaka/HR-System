/*
 * User Status DAO Impl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.UserStatusDAO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER_STATUS;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_STATUS_DETAILS;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER_STATUS;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author Shanaka
 * Save user status Details 
 * deal with user_status table in DB
 */
@Repository
public class UserStatusDAOImpl implements UserStatusDAO{
    
    private static final Logger LOGGER = Logger.getLogger(UserStatusDAOImpl.class);
    
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param userStatusDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserStatusDTO> getUserStatus(UserStatusDTO userStatusDTO) throws JDBCTemplateException {
        return jdbcTemplate.query("SELECT * FROM user_status", new BeanPropertyRowMapper(UserStatusDTO.class));
    }

    /**
     * 
     * @param userStatusDTO
     * @param statusCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserStatusDTO> getStatDetail(UserStatusDTO userStatusDTO, int statusCode) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_STATUS_DETAILS, new BeanPropertyRowMapper(UserStatusDTO.class),statusCode);
    }

    /**
     * 
     * @param userStat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserStatusDTO saveMaster(UserStatusDTO userStat) throws JDBCTemplateException {
       int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER_STATUS);
                    statement.setString(1, userStat.getEmp_status());
                    return statement;
                }

            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? userStat : null;
    }

    /**
     * 
     * @param userStatus
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserStatusDTO updateMaster(UserStatusDTO userStatus) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER_STATUS,
                        userStatus.getEmp_status(),
                        userStatus.getStatus_id());
            
        } catch (DataAccessException e) {
            LOGGER.equals(e);
        }
        return result > 0 ? userStatus : null;
    }

    /**
     * 
     * @param id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
