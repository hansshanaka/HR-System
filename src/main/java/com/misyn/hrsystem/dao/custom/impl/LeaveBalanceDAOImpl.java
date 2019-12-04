/*
 * 
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.LeaveBalanceDAO;
import com.misyn.hrsystem.dto.custom.LeaveBalanceDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_LEAVE_BALANCE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LEAVE_BALANCE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LEAVE_BALANCE_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_LEAVE_BALANCE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 */
@Repository
public class LeaveBalanceDAOImpl implements LeaveBalanceDAO{
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;
    
    private static final String AND = " AND ";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveBalanceDTO> getLeaveBalanceList(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
       
       String leave_bal_que = SELECT_LEAVE_BALANCE;
               
       ArrayList arguments = new ArrayList();
       
       leave_bal_que = (leaveBalanceDTO.getLtype_id() != 0 ? 
               leave_bal_que.concat(AND + "lb.ltype_id = ?") : leave_bal_que);
        
       arguments.add(leaveBalanceDTO.getUser_code());
       
       if(leaveBalanceDTO.getLtype_id() != 0){
           arguments.add(leaveBalanceDTO.getLtype_id());
       }
       
       return jdbcTemplate.query(leave_bal_que,arguments.toArray(), new BeanPropertyRowMapper(LeaveBalanceDTO.class));
    }

    /**
     * 
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveBalanceDTO saveMaster(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement statement = conn.prepareStatement(INSERT_LEAVE_BALANCE);
                    statement.setDouble(1, leaveBalanceDTO.getDays());
                    statement.setInt(2, leaveBalanceDTO.getLtype_id());
                    statement.setString(3, leaveBalanceDTO.getUser_code());
                    
                    return statement;
                }
            });
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? leaveBalanceDTO : null;
    }

    /**
     * 
     * @param leaveBalanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveBalanceDTO updateMaster(LeaveBalanceDTO leaveBalanceDTO) throws JDBCTemplateException {
         int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_LEAVE_BALANCE,
                    leaveBalanceDTO.getDays(),
                    leaveBalanceDTO.getLeave_balance_id());
                    
        } catch (Exception e) {
            LOGGER.error(e);
        }
        
        return result > 0 ? leaveBalanceDTO : null;
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
     * @param leaveBalanceDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveBalanceDTO> getLeaveBalDetail(LeaveBalanceDTO leaveBalanceDTO, int leaveID) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_LEAVE_BALANCE_DETAIL, new BeanPropertyRowMapper(LeaveBalanceDTO.class),leaveID);
    }

    /**
     * 
     * @param uCode
     * @param LeaveType
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkExistLBal(String uCode, int LeaveType) throws JDBCTemplateException {
                
        String query = "SELECT COUNT(*) FROM leave_balance WHERE user_code = ? AND ltype_id = ?";
             
        return jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int count = 0;
                if (rs.next()) {
                    count = rs.getInt("COUNT(*)");
                }
                return count;
            }
        },uCode,LeaveType);
    }



}
