/*
 * 
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.UserRoleDAO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_ROLE_OPTION;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER_ROLE;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_ROLE_OPTION;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_ROLE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_ROLE_DETAILS;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER_ROLE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 * Save user role Details 
 * deal with user_role table in DB
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    private static final Logger LOGGER = Logger.getLogger(UserRoleDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param id
     * @param userRoleDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserRoleDTO> getUserRoleList(String id, UserRoleDTO userRoleDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_ROLE, new BeanPropertyRowMapper(UserRoleDTO.class), id);
    }

    /**
     * 
     * @param userRole
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO saveMaster(UserRoleDTO userRole) throws JDBCTemplateException {

        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE);
                    statement.setString(1, userRole.getUser_role_id());
                    statement.setString(2, userRole.getRole_name());
                    statement.setString(3, userRole.getUserTypeList());

                    return statement;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? userRole : null;
    }

    /**
     * 
     * @param userRole
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO updateMaster(UserRoleDTO userRole) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER_ROLE,
                    userRole.getRole_name(),
                    userRole.getUser_role_id());

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? userRole : null;
    }

    /**
     * 
     * @param id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(DELETE_ROLE_OPTION,
                    id);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? true : false;

    }

    /**
     * 
     * @param userRole
     * @param menuId
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserRoleDTO insertRoleOption(UserRoleDTO userRole, int menuId) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_ROLE_OPTION);
                    statement.setInt(1, menuId);
                    statement.setString(2, userRole.getUser_role_id());

                    return statement;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? userRole : null;
    }

    /**
     * 
     * @param userRoleDTO
     * @param roleID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserRoleDTO> getUserRoleDetails(UserRoleDTO userRoleDTO, String roleID) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_ROLE_DETAILS, new BeanPropertyRowMapper(UserRoleDTO.class), roleID);
    }

    /**
     * 
     * @param userRoleDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserRoleDTO> getAllRoleList(UserRoleDTO userRoleDTO) throws JDBCTemplateException {
        return jdbcTemplate.query("SELECT * FROM user_role", new BeanPropertyRowMapper(UserRoleDTO.class));
    }

}
