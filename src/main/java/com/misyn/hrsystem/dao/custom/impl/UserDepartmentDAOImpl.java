/*
 * User Department DAO Impl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.UserDepartmentDAO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER_DEPARTMENT;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_DEPARTMENT_DETAILS;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER_DEPARTMENT;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author Shanaka
 * Save user Department Details 
 * deal with user_department table in DB
 */
@Repository
public class UserDepartmentDAOImpl implements UserDepartmentDAO{
    
    private static final Logger LOGGER = Logger.getLogger(UserDepartmentDAOImpl.class);
    
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param userDep
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDepartmentDTO saveMaster(UserDepartmentDTO userDep) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER_DEPARTMENT);
                    statement.setString(1, userDep.getDepartment_name());
                    return statement;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? userDep : null;
    }

    /**
     * 
     * @param userDep
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDepartmentDTO updateMaster(UserDepartmentDTO userDep) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER_DEPARTMENT,
                        userDep.getDepartment_name(),
                        userDep.getDepartment_code());
            
        } catch (DataAccessException e) {
            LOGGER.equals(e);
        }
        return result > 0 ? userDep : null;
    }

    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param userDepartmentDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserDepartmentDTO> getUserDepartment(UserDepartmentDTO userDepartmentDTO) throws JDBCTemplateException {
        return jdbcTemplate.query("SELECT * FROM user_department", new BeanPropertyRowMapper(UserDepartmentDTO.class));
    }

    /**
     * 
     * @param departmentDTO
     * @param depCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserDepartmentDTO> getDepDetail(UserDepartmentDTO departmentDTO, int depCode) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_DEPARTMENT_DETAILS, new BeanPropertyRowMapper(UserDepartmentDTO.class),depCode);
    }
    
}
