/*
 * LeaveDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.LeaveDAO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.AppConstant.AND;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_LEAVE_MST;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_LEAVE_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_LEAVE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_APPROVED_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_AUTH_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LEAVE_BAL_LEAVE_ID;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MST_LEAVE_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MST_LEAVE_SAVE_TMP;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_NO_OF_DAYS;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_REPORT_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_CANCEL_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_LEAVE_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_LEAVE_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_LEAVE_SAVE_MST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_CODE_MST_LEAVE;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_LEAVE_BALANCE_SELECTING_LID;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_MST_AUTH_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_MST_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_TMP_AUTH_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_TMP_DETAIL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Save LEave Details deals,update leave Balance, authorize leave
 * deal with leave_tmp,leave_mst tables in DB
 */
@Repository
public class LeaveDAOImpl implements LeaveDAO {

    private static final Logger LOGGER = Logger.getLogger(LeaveDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO saveMaster(LeaveDTO leaveDTO) throws JDBCTemplateException {
        int result = 0;
        String query_insrt_hst = "INSERT INTO emp_leave_hst (SELECT * FROM emp_leave_tmp order by leave_id desc limit 1)";

        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_LEAVE, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, leaveDTO.getStart_date());
                    statement.setString(2, leaveDTO.getEnd_date());
                    statement.setDouble(3, leaveDTO.getNo_of_days());
                    statement.setString(4, "P");
                    statement.setString(5, "");
                    statement.setString(6, "1900-01-01 00:00:00");
                    statement.setString(7, "I");
                    statement.setInt(8, leaveDTO.getLtype_id());
                    statement.setString(9, leaveDTO.getUser_code());
                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO updateMaster(LeaveDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object leaveID) throws JDBCTemplateException {
        int result = 0;

        try {
            //---Update Details MST table
            result = jdbcTemplate.update(UPDATE_MST_AUTH_DETAIL, "C", (Integer) leaveID);
            //---Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_LEAVE_SAVE_HST, (Integer) leaveID);
            //---Save Detals TMP Table
            result = jdbcTemplate.update(SELECT_MST_LEAVE_SAVE_TMP, (Integer) leaveID);
            //---Update Details TMP table
            result = jdbcTemplate.update(UPDATE_TMP_DETAIL, "P", (Integer) leaveID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_HST, (Integer) leaveID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? true : false;
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException {
        String query = "SELECT days FROM leave_balance WHERE user_code = ? AND ltype_id = ? ";

        return jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int bal = 0;
                if (rs.next()) {
                    bal = rs.getInt("days");
                }
                return bal;
            }
        }, leaveDTO.getUser_code(), leaveDTO.getLtype_id());
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO updateLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException {
        int result = 0;
        String query_update_leave_bal = "UPDATE leave_balance SET days=? WHERE user_code = ? AND ltype_id = ?";
        try {
            result = jdbcTemplate.update(query_update_leave_bal,
                    leaveDTO.getLeave_balance(),
                    leaveDTO.getUser_code(),
                    leaveDTO.getLtype_id());

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {

        String query_leave_list = SELECT_LEAVE_LIST;

        ArrayList arguments = new ArrayList();

        query_leave_list = (!leaveDTO.getAuth_state().equals("")
                ? query_leave_list.concat(AND + "auth_state = ?") : query_leave_list);

        arguments.add(leaveDTO.getUser_code());
        arguments.add(leaveDTO.getStart_date());
        arguments.add(leaveDTO.getEnd_date());

        if (!leaveDTO.getAuth_state().equals("")) {
            arguments.add(leaveDTO.getAuth_state());
        }

        return jdbcTemplate.query(query_leave_list, arguments.toArray(), new BeanPropertyRowMapper(LeaveDTO.class));

    }

    /**
     * 
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteLeave(int leaveID) throws JDBCTemplateException {
        try {
            return jdbcTemplate.update(DELETE_LEAVE_TMP, leaveID) > 0;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new JDBCTemplateException(e);
        }
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getPendingLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_TMP_LEAVE_LIST, new BeanPropertyRowMapper(LeaveDTO.class));
    }

    /**
     * 
     * @param leaveDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO authorizeLeave(LeaveDTO leaveDTO, int leaveID) throws JDBCTemplateException {

        int result = 0;

        try {
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_HST, leaveID);
            //---Get Details from Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_DETAIL, "A", leaveDTO.getAuth_id(), leaveID);
            //---Save Detals Maseter Table
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_MST, leaveID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_LEAVE_TMP, leaveID);
            //---Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_LEAVE_SAVE_HST, leaveID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getAuthLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {

        String query_leave_list = SELECT_APPROVED_LEAVE_LIST;

        ArrayList arguments = new ArrayList();

        query_leave_list = (!leaveDTO.getUser_code().equals("")
                ? query_leave_list.concat(AND + "el.user_code = ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getStart_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.start_date = ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getEnd_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.end_date = ?") : query_leave_list);

        if (!leaveDTO.getUser_code().equals("")) {
            arguments.add(leaveDTO.getUser_code());
        }
        if (!leaveDTO.getStart_date().trim().equals("")) {
            arguments.add(leaveDTO.getStart_date());
        }
        if (!leaveDTO.getEnd_date().trim().equals("")) {
            arguments.add(leaveDTO.getEnd_date());
        }

        return jdbcTemplate.query(query_leave_list, arguments.toArray(), new BeanPropertyRowMapper(LeaveDTO.class));

    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getPendingCancelLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_TMP_CANCEL_LEAVE_LIST, new BeanPropertyRowMapper(LeaveDTO.class));
    }

    /**
     * 
     * @param leaveDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO authorizeCancelLeave(LeaveDTO leaveDTO, int leaveID) throws JDBCTemplateException {
        int result = 0;

        try {

            //--Delete MST Table
            result = jdbcTemplate.update(DELETE_LEAVE_MST, leaveID);
            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_DETAIL, "C", leaveDTO.getAuth_id(), leaveID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_HST, leaveID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_LEAVE_TMP, leaveID);

            //---Update Leave Balance
            result = jdbcTemplate.update(UPDATE_LEAVE_BALANCE_SELECTING_LID, leaveDTO.getLeave_balance(), leaveID, leaveID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param LeaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int getLeaveBalLID(int LeaveID) throws JDBCTemplateException {

        return jdbcTemplate.query(SELECT_LEAVE_BAL_LEAVE_ID, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int bal = 0;
                if (rs.next()) {
                    bal = rs.getInt("days");
                }
                return bal;
            }
        }, LeaveID, LeaveID);
    }

    /**
     * 
     * @param LeaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int getNoOfDaysLID(int LeaveID) throws JDBCTemplateException {

        return jdbcTemplate.query(SELECT_NO_OF_DAYS, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int bal = 0;
                if (rs.next()) {
                    bal = rs.getInt("days");
                }
                return bal;
            }
        }, LeaveID);
    }

    /**
     * 
     * @param leaveDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO rejectCancelLeave(LeaveDTO leaveDTO, int leaveID) throws JDBCTemplateException {
        int result = 0;

        try {

            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_DETAIL, "R", leaveDTO.getAuth_id(), leaveID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_HST, leaveID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_LEAVE_TMP, leaveID);
            //---Update Leave MST table
            result = jdbcTemplate.update(UPDATE_MST_DETAIL, "I", leaveDTO.getAuth_id(), leaveID);
            //--Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_LEAVE_SAVE_HST, leaveID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param leaveDTO
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO rejectLeave(LeaveDTO leaveDTO, int leaveID) throws JDBCTemplateException {
        int result = 0;

        try {

            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_DETAIL, "R", leaveDTO.getAuth_id(), leaveID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_LEAVE_SAVE_HST, leaveID);
            //---Update Leave Balance
            result = jdbcTemplate.update(UPDATE_LEAVE_BALANCE_SELECTING_LID, leaveDTO.getLeave_balance(), leaveID, leaveID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_LEAVE_TMP, leaveID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? leaveDTO : null;
    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getApprovedLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {
        String query_leave_list = SELECT_AUTH_LEAVE_LIST;

        ArrayList arguments = new ArrayList();

        query_leave_list = (leaveDTO.getLtype_id() != 0
                ? query_leave_list.concat(AND + "el.ltype_id = ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getStart_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.start_date >= ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getEnd_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.end_date <= ?") : query_leave_list);

        arguments.add(leaveDTO.getUser_code());

        if (leaveDTO.getLtype_id() != 0) {
            arguments.add(leaveDTO.getLtype_id());
        }
        if (!leaveDTO.getStart_date().trim().equals("")) {
            arguments.add(leaveDTO.getStart_date());
        }
        if (!leaveDTO.getEnd_date().trim().equals("")) {
            arguments.add(leaveDTO.getEnd_date());
        }

        return jdbcTemplate.query(query_leave_list, arguments.toArray(), new BeanPropertyRowMapper(LeaveDTO.class));

    }

    /**
     * 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getReportLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {

        String query_leave_list = SELECT_REPORT_LEAVE_LIST;

        ArrayList arguments = new ArrayList();

        query_leave_list = (!leaveDTO.getUser_code().equals("")
                ? query_leave_list.concat(AND + "el.user_code = ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getStart_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.start_date = ?") : query_leave_list);

        query_leave_list = (!leaveDTO.getEnd_date().trim().equals("")
                ? query_leave_list.concat(AND + "el.end_date = ?") : query_leave_list);

        if (!leaveDTO.getUser_code().equals("")) {
            arguments.add(leaveDTO.getUser_code());
        }
        if (!leaveDTO.getStart_date().trim().equals("")) {
            arguments.add(leaveDTO.getStart_date());
        }
        if (!leaveDTO.getEnd_date().trim().equals("")) {
            arguments.add(leaveDTO.getEnd_date());
        }

        return jdbcTemplate.query(query_leave_list, arguments.toArray(), new BeanPropertyRowMapper(LeaveDTO.class));
    }

    /**
     * 
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public String getUserCode(int leaveID) throws JDBCTemplateException {

        return jdbcTemplate.query(SELECT_USER_CODE_MST_LEAVE, new ResultSetExtractor<String>() {

            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String code = "";
                if (rs.next()) {
                    code = rs.getString("user_code");
                }
                return code;
            }
        },leaveID);
    }

}
