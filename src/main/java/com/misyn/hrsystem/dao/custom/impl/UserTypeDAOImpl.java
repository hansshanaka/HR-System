/*
 * 
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.UserTypeDAO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_USER_TYPE;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER_TYPE;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER_TYPE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save user type Details 
 * deal with user_type table in DB
 */
@Repository
public class UserTypeDAOImpl implements UserTypeDAO{
    
    private static final Logger LOGGER = Logger.getLogger(MenuItemDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param userTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserTypeDTO> getUserTypeList(UserTypeDTO userTypeDTO) throws JDBCTemplateException {        
        return jdbcTemplate.query("SELECT * FROM user_type", new BeanPropertyRowMapper(UserTypeDTO.class));
        
    }

    /**
     * 
     * @param userType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserTypeDTO saveMaster(UserTypeDTO userType) throws JDBCTemplateException {
        
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER_TYPE, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, userType.getUser_type_id());
                    statement.setString(2, userType.getType_name());
                    return statement;
                }
            });
                    
        } catch (Exception e) {
            LOGGER.error(e);
        }        
        return result > 0 ? userType : null;        
    }

    /**
     * 
     * @param userTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserTypeDTO updateMaster(UserTypeDTO userTypeDTO) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER_TYPE,
                    userTypeDTO.getType_name(),
                    userTypeDTO.getUser_type_id());
                    
        } catch (DataAccessException e) {
            LOGGER.error(e);
        }
        
        return result>0 ?userTypeDTO:null;
    }

    /**
     * 
     * @param id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        try {
            return jdbcTemplate.update(DELETE_USER_TYPE,(String) id) > 0;
        } catch (DataAccessException e) {
            LOGGER.error(e);
            throw new JDBCTemplateException(e);
        }
    }


   
  
    
}
