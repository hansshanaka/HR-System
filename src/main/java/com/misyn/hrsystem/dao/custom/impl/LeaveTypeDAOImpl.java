/*
 * LeaveDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.LeaveTypeDAO;
import com.misyn.hrsystem.dto.custom.LeaveTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_LEAVE_TYPE;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_LEAVE_TYPE;
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
 * Save Leave type Details 
 * deal with leave_type table in DB
 */
@Repository
public class LeaveTypeDAOImpl implements LeaveTypeDAO{
    
    private static final Logger LOGGER = Logger.getLogger(LeaveTypeDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param leaveTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveTypeDTO> getLeaveTypeList(LeaveTypeDTO leaveTypeDTO) throws JDBCTemplateException {
        return jdbcTemplate.query("SELECT * FROM leave_type", new BeanPropertyRowMapper(LeaveTypeDTO.class));
    }

    /**
     * 
     * @param LeaveTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveTypeDTO saveMaster(LeaveTypeDTO LeaveTypeDTO) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement statement = conn.prepareStatement(INSERT_LEAVE_TYPE);
                    statement.setString(1, LeaveTypeDTO.getLtype_name());
                    statement.setInt(2, LeaveTypeDTO.getDays());
                    
                    return statement;
                }
            });
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? LeaveTypeDTO : null;
    }

    /**
     * 
     * @param leaveTypeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveTypeDTO updateMaster(LeaveTypeDTO leaveTypeDTO) throws JDBCTemplateException {
         int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_LEAVE_TYPE,
                    leaveTypeDTO.getLtype_name(),
                    leaveTypeDTO.getDays(),
                    leaveTypeDTO.getLtype_id());
                    
        } catch (Exception e) {
            LOGGER.error(e);
        }
        
        return result > 0 ?leaveTypeDTO : null;
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
