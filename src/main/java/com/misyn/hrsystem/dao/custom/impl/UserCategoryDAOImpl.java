/*
 * UserCategoryDAO Impl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.UserCategoryDAO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER_CATEGORY;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_CATEGORY_DETAILS;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER_CATEGORY;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 */
@Repository
public class UserCategoryDAOImpl implements UserCategoryDAO{
    
    private static final Logger LOGGER = Logger.getLogger(UserDepartmentDAOImpl.class);
    
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param userCat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserCategoryDTO saveMaster(UserCategoryDTO userCat) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER_CATEGORY);
                    statement.setString(1, userCat.getCategory_name());
                    return statement;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? userCat : null;
    }

    /**
     * 
     * @param userCat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserCategoryDTO updateMaster(UserCategoryDTO userCat) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER_CATEGORY,
                        userCat.getCategory_name(),
                        userCat.getCategory_id());
            
        } catch (DataAccessException e) {
            LOGGER.equals(e);
        }
        return result > 0 ? userCat : null;
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

    /**
     * 
     * @param userCategoryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserCategoryDTO> getUserCategoryList(UserCategoryDTO userCategoryDTO) throws JDBCTemplateException {
        return jdbcTemplate.query("SELECT * FROM user_category", new BeanPropertyRowMapper(UserCategoryDTO.class));
    }

    /**
     * 
     * @param categoryDTO
     * @param catCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserCategoryDTO> getCategDetail(UserCategoryDTO categoryDTO, int catCode) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_CATEGORY_DETAILS, new BeanPropertyRowMapper(UserCategoryDTO.class),catCode);
    }
    
}
