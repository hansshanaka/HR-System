/*
 * 
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.EmployeeDAO;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.AppConstant.AND;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_ATTENDENCE_MST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_EMPLOYEE;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_USER;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYEE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYS_ALL;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_ATTENDENCE_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_EMPLOYEE_SALARY_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_SALARY_MST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_ALL_EMPLOYEES;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_DEV_EMPLOYEES;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYEE_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_LEAVE_COUNT_SALARY_TMP;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_PENDDING_ATTENDENCE;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_QA_EMPLOYEES;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_REPORT_EMPLOYEE_ATTENDANCE_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_REPORT_EMPLOYEE_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_REPORT_EMPLOYEE_SALARY_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_SALARY_TMP_ALL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_SALARY_TMP_DETAIL;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_UI_EMPLOYEES;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_EMPLOYEE;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_USER;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save Employees Details deal with employee MST data base table and employee_tmp table
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO saveMaster(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE);
                    statement.setString(1, employeeDTO.getUser_code());
                    statement.setString(2, employeeDTO.getTitle());
                    statement.setString(3, employeeDTO.getName());
                    statement.setString(4, employeeDTO.getUse_name());
                    statement.setString(5, employeeDTO.getAddress());
                    statement.setString(6, employeeDTO.getGender());
                    statement.setString(7, employeeDTO.getDob());
                    statement.setString(8, employeeDTO.getPhoto());
                    statement.setString(9, employeeDTO.getEmail());
                    statement.setString(10, employeeDTO.getMobile_no());
                    statement.setString(11, employeeDTO.getLand_no());
                    statement.setString(12, employeeDTO.getEmg_con_no());
                    statement.setString(13, employeeDTO.getNic());
                    statement.setString(14, employeeDTO.getMarital_status());
                    statement.setString(15, employeeDTO.getJoin_date());
                    statement.setString(16, employeeDTO.getConfirm_date());
                    statement.setString(17, employeeDTO.getResign_date());
                    statement.setInt(18, employeeDTO.getDepartment_code());
                    statement.setInt(19, employeeDTO.getCategory_id());
                    statement.setString(20, employeeDTO.getUser_type_id());
                    statement.setString(21, employeeDTO.getUser_role_id());
                    statement.setInt(22, employeeDTO.getStatus_id());
                    statement.setBigDecimal(23, employeeDTO.getSalary());

                    return statement;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? employeeDTO : null;
    }

    /**
     * 
     * @param emp
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO updateMaster(EmployeeDTO emp) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_EMPLOYEE,
                    emp.getTitle(),
                    emp.getName(),
                    emp.getUse_name(),
                    emp.getAddress(),
                    emp.getGender(),
                    emp.getDob(),
                    emp.getPhoto(),
                    emp.getEmail(),
                    emp.getMobile_no(),
                    emp.getLand_no(),
                    emp.getEmg_con_no(),
                    emp.getNic(),
                    emp.getMarital_status(),
                    emp.getJoin_date(),
                    emp.getConfirm_date(),
                    emp.getResign_date(),
                    emp.getDepartment_code(),
                    emp.getCategory_id(),
                    emp.getUser_type_id(),
                    emp.getUser_role_id(),
                    emp.getStatus_id(),
                    emp.getSalary(),                    
                    emp.getEmail(),
                    emp.getUser_code());

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? emp : null;
    }

    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int getCount() throws JDBCTemplateException {

        String query = "SELECT COUNT(*) FROM employee_mst";
        return jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int count = 0;
                if (rs.next()) {
                    count = rs.getInt("COUNT(*)");
                }
                return count;
            }
        });
    }
/**
 * 
 * @param employeeDTO
 * @return
 * @throws JDBCTemplateException 
 */
    @Override
    public EmployeeDTO insertUser(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_USER);

                    statement.setString(1, employeeDTO.getUse_name());
                    statement.setString(2, employeeDTO.getNic());
                    statement.setString(3, employeeDTO.getUser_type_id());
                    statement.setString(4, employeeDTO.getUser_role_id());
                    statement.setString(5, employeeDTO.getEmail());
                    statement.setString(6, employeeDTO.getUser_code());

                    return statement;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? employeeDTO : null;
    }

    /**
     * 
     * @param employeeDTO
     * @param status
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmploys(EmployeeDTO employeeDTO, String status) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_EMPLOYS_ALL, new BeanPropertyRowMapper(EmployeeDTO.class), status);

    }

    /**
     * 
     * @param employeeDTO
     * @param code
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmployee(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_EMPLOYEE, new BeanPropertyRowMapper(EmployeeDTO.class), code);
    }

    /**
     * 
     * @param year
     * @param month
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public AttendanceDTO uploadAttendence(int year, int month, AttendanceDTO attendanceDTO) throws JDBCTemplateException {

        int result = 0;
        String mesg = "";
        String query_insrt_hst = "INSERT INTO attendence_hst (SELECT * FROM attendence_tmp order by attendence_id desc limit 1)";

        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement statement = connection.prepareStatement(INSERT_ATTENDENCE_TMP);

                    statement.setInt(1, attendanceDTO.getYear());
                    statement.setInt(2, attendanceDTO.getMonth());
                    statement.setInt(3, attendanceDTO.getDay());
                    statement.setString(4, attendanceDTO.getOn_time());
                    statement.setString(5, attendanceDTO.getOff_time());
                    statement.setString(6, attendanceDTO.getUser_code());
                    statement.setString(7, attendanceDTO.getInput_user());
                    statement.setString(8, "P");
                    statement.setString(9, "");
                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? attendanceDTO : null;
    }

    /**
     * 
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkPendingTemp() throws JDBCTemplateException {
        String query = "SELECT COUNT(*) FROM attendence_tmp";
        return jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int count = 0;
                if (rs.next()) {
                    count = rs.getInt("COUNT(*)");
                }
                return count;

            }
        });
    }

    /**
     * 
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<AttendanceDTO> getAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException {

        ArrayList arguments = new ArrayList();

        String SELECT_EMPLOYEE_ATTENDANCE = "SELECT am.*,em.use_name AS emp_name \n"
                + "FROM attendence_mst am, employee_mst em\n"
                + "WHERE am.user_code = em.user_code ";

        SELECT_EMPLOYEE_ATTENDANCE = (!attendanceDTO.getUser_code().equals("")
                ? SELECT_EMPLOYEE_ATTENDANCE.concat(AND+ "am.user_code = ?") : SELECT_EMPLOYEE_ATTENDANCE);

        SELECT_EMPLOYEE_ATTENDANCE = (attendanceDTO.getYear() != 0
                ? SELECT_EMPLOYEE_ATTENDANCE.concat(AND + "am.year = ?") : SELECT_EMPLOYEE_ATTENDANCE);

        SELECT_EMPLOYEE_ATTENDANCE = (attendanceDTO.getMonth() != 0
                ? SELECT_EMPLOYEE_ATTENDANCE.concat(AND + "am.month = ?") : SELECT_EMPLOYEE_ATTENDANCE);
        
        SELECT_EMPLOYEE_ATTENDANCE = (attendanceDTO.getDay() != 0
                ? SELECT_EMPLOYEE_ATTENDANCE.concat(AND + "am.day = ?") : SELECT_EMPLOYEE_ATTENDANCE);

        if (!attendanceDTO.getUser_code().equals("")) {
            arguments.add(attendanceDTO.getUser_code());
        }
        if (attendanceDTO.getYear() != 0) {
            arguments.add(attendanceDTO.getYear());
        }
        if (attendanceDTO.getMonth() != 0) {
            arguments.add(attendanceDTO.getMonth());
        }
        if (attendanceDTO.getDay() != 0) {
            arguments.add(attendanceDTO.getDay());
        }

        return jdbcTemplate.query(SELECT_EMPLOYEE_ATTENDANCE, arguments.toArray(), new BeanPropertyRowMapper(AttendanceDTO.class));
    }

    /**
     * 
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<AttendanceDTO> getPendingAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_PENDDING_ATTENDENCE, new BeanPropertyRowMapper(AttendanceDTO.class));
    }

    /**
     * 
     * @param attendanceDTO
     * @param user
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public AttendanceDTO authorizeAttendance(AttendanceDTO attendanceDTO,String user) throws JDBCTemplateException {
        int result = 0;
        String del_mst = "DELETE FROM attendence_mst WHERE year=? and month=? and day=?";
        String query_insrt_hst = "INSERT INTO attendence_hst(SELECT * FROM attendence_mst)";
        String query_del_tmp = "DELETE FROM attendence_tmp";
        String query_update_MST = "UPDATE attendence_mst SET auth_status=?,auth_user=? WHERE year=? and month=? and day=? ";
        try {
            result = jdbcTemplate.update(del_mst, attendanceDTO.getYear(), attendanceDTO.getMonth(), attendanceDTO.getDay());
            result = jdbcTemplate.update(INSERT_ATTENDENCE_MST);
            result = jdbcTemplate.update(query_update_MST,"A",user, attendanceDTO.getYear(), attendanceDTO.getMonth(), attendanceDTO.getDay());
            result = jdbcTemplate.update(query_del_tmp);
            result = jdbcTemplate.update(query_insrt_hst);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? attendanceDTO : null;
    }

    /**
     * 
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int rejectAttendence() throws JDBCTemplateException {
        int result = 0;
        String status_chg_tem = "UPDATE attendence_tmp set auth_status='R'";
        String query_insrt_hst = "INSERT INTO attendence_hst(SELECT * FROM attendence_tmp)";
        String query_del_tmp = "TRUNCATE attendence_tmp";

        try {
            result = jdbcTemplate.update(status_chg_tem);

            if (result != 0) {
                jdbcTemplate.update(query_insrt_hst);
                jdbcTemplate.update(query_del_tmp);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result;
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getAllEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEES, new BeanPropertyRowMapper(EmployeeDTO.class));
    }

    /**
     * 
     * @param userDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO) throws JDBCTemplateException {

        int result = 0;
        try {
            result = jdbcTemplate.update(UPDATE_USER,
                    userDTO.getPassword(),
                    userDTO.getUser_code());

        } catch (DataAccessException e) {
            LOGGER.error(e);
        }

        return result > 0 ? userDTO : null;
    }

    /**
     * 
     * @param employeeDTO
     * @param code
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmployeeDetail(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_EMPLOYEE_DETAIL, new BeanPropertyRowMapper(EmployeeDTO.class), code);
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getDevEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_DEV_EMPLOYEES, new BeanPropertyRowMapper(EmployeeDTO.class));
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getUIEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_UI_EMPLOYEES, new BeanPropertyRowMapper(EmployeeDTO.class));
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getQAEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_QA_EMPLOYEES, new BeanPropertyRowMapper(EmployeeDTO.class));
    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getReportEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {

        ArrayList arguments = new ArrayList();

        String query_emp_list = SELECT_REPORT_EMPLOYEE_DETAIL;

        query_emp_list = (!employeeDTO.getUser_code().equals("")
                ? query_emp_list.concat(AND + "e.user_code = ?") : query_emp_list);

        query_emp_list = (employeeDTO.getStatus_id() != 0
                ? query_emp_list.concat(AND + "e.status_id = ?") : query_emp_list);

        query_emp_list = (!employeeDTO.getUser_type_id().equals("")
                ? query_emp_list.concat(AND + "e.user_type_id = ?") : query_emp_list);

        query_emp_list = (!employeeDTO.getUser_role_id().equals("")
                ? query_emp_list.concat(AND + "e.user_role_id = ?") : query_emp_list);

        query_emp_list = (employeeDTO.getDepartment_code() != 0
                ? query_emp_list.concat(AND + "e.department_code = ?") : query_emp_list);

        query_emp_list = (employeeDTO.getCategory_id() != 0
                ? query_emp_list.concat(AND + "e.category_id = ?") : query_emp_list);

        if (!employeeDTO.getUser_code().equalsIgnoreCase("")) {
            arguments.add(employeeDTO.getUser_code());
        }
        if (employeeDTO.getStatus_id() != 0) {
            arguments.add(employeeDTO.getStatus_id());
        }
        if (!employeeDTO.getUser_type_id().equalsIgnoreCase("")) {
            arguments.add(employeeDTO.getUser_type_id());
        }
        if (!employeeDTO.getUser_role_id().equalsIgnoreCase("")) {
            arguments.add(employeeDTO.getUser_role_id());
        }
        if (employeeDTO.getDepartment_code() != 0) {
            arguments.add(employeeDTO.getDepartment_code());
        }
        if (employeeDTO.getCategory_id() != 0) {
            arguments.add(employeeDTO.getCategory_id());
        }

        return jdbcTemplate.query(query_emp_list, arguments.toArray(), new BeanPropertyRowMapper(EmployeeDTO.class));

    }

    /**
     * 
     * @param salaryDTO
     * @throws JDBCTemplateException 
     */
    @Override
    public void insertSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException {
        jdbcTemplate.update(INSERT_EMPLOYEE_SALARY_TMP,
                salaryDTO.getAttend_days(),
                salaryDTO.getLeave_days(),
                salaryDTO.getYear(),
                salaryDTO.getMonth(),
                salaryDTO.getSalary(),
                salaryDTO.getUser_code());
    }

    /**
     * 
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_SALARY_TMP_DETAIL, new BeanPropertyRowMapper(SalaryDTO.class));
    }

    /**
     * 
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getLeaveCount(SalaryDTO salaryDTO) throws JDBCTemplateException {
         return jdbcTemplate.query(SELECT_LEAVE_COUNT_SALARY_TMP, new BeanPropertyRowMapper(SalaryDTO.class));
    }

    /**
     * 
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getSalaryTmpDetails(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_SALARY_TMP_ALL, new BeanPropertyRowMapper(SalaryDTO.class));
    }

    /**
     * 
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public SalaryDTO saveSalaryInput(SalaryDTO salaryDTO) throws JDBCTemplateException {
        int result = 0;
        
        
        String query_del_tmp = "DELETE FROM salary_tmp";
        String query_update_mst = "UPDATE salary_mst SET input_user=? ";
        try {
            result = jdbcTemplate.update(INSERT_SALARY_MST);
            result = jdbcTemplate.update(query_del_tmp);
            result = jdbcTemplate.update(query_update_mst,salaryDTO.getInput_user());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? salaryDTO : null;
    }

    /**
     * 
     * @param user_code
     * @param fromYear
     * @param fromMonth
     * @param toYear
     * @param toMonth
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getReportSalary(String user_code, int fromYear, int fromMonth, int toYear, int toMonth) throws JDBCTemplateException {
        ArrayList arguments = new ArrayList();

        String query_salary_list = SELECT_REPORT_EMPLOYEE_SALARY_DETAIL;

        query_salary_list = (!user_code.equals("")
                ? query_salary_list.concat(AND + "sm.user_code = ?") : query_salary_list);

        query_salary_list = (fromYear != 0
                ? query_salary_list.concat(AND + "sm.year BETWEEN ?") : query_salary_list);

        query_salary_list = (toYear != 0
                ? query_salary_list.concat(AND + " ?") : query_salary_list);

        query_salary_list = (fromMonth != 0
                ? query_salary_list.concat(AND + "sm.month BETWEEN ?") : query_salary_list);
        
        query_salary_list = (toMonth != 0
                ? query_salary_list.concat(AND + " ?") : query_salary_list);

        if (!user_code.equalsIgnoreCase("")) {
            arguments.add(user_code);
        }
        if (fromYear != 0) {
            arguments.add(fromYear);
        }
        if (toYear != 0) {
            arguments.add(toYear);
        }
        if (fromMonth != 0) {
            arguments.add(fromMonth);
        }
        if (toMonth != 0) {
            arguments.add(toMonth);
        }
        
        return jdbcTemplate.query(query_salary_list, arguments.toArray(), new BeanPropertyRowMapper(SalaryDTO.class));

    }

    /**
     * 
     * @param user_code
     * @param fromYear
     * @param fromMonth
     * @param toYear
     * @param toMonth
     * @param day
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<AttendanceDTO> getReportAtten(String user_code, int fromYear, int fromMonth, int toYear, int toMonth, int day) throws JDBCTemplateException {
        ArrayList arguments = new ArrayList();

        String query_salary_list = SELECT_REPORT_EMPLOYEE_ATTENDANCE_DETAIL;

        query_salary_list = (!user_code.equals("")
                ? query_salary_list.concat(AND + "am.user_code = ?") : query_salary_list);

        query_salary_list = (fromYear != 0
                ? query_salary_list.concat(AND + "am.year BETWEEN ?") : query_salary_list);

        query_salary_list = (toYear != 0
                ? query_salary_list.concat(AND + " ?") : query_salary_list);

        query_salary_list = (fromMonth != 0
                ? query_salary_list.concat(AND + "am.month BETWEEN ?") : query_salary_list);
        
        query_salary_list = (toMonth != 0
                ? query_salary_list.concat(AND + " ?") : query_salary_list);
        
        query_salary_list = (day != 0
                ? query_salary_list.concat(AND + "am.day = ?") : query_salary_list);

        if (!user_code.equalsIgnoreCase("")) {
            arguments.add(user_code);
        }
        if (fromYear != 0) {
            arguments.add(fromYear);
        }
        if (toYear != 0) {
            arguments.add(toYear);
        }
        if (fromMonth != 0) {
            arguments.add(fromMonth);
        }
        if (toMonth != 0) {
            arguments.add(toMonth);
        }
        if (day != 0) {
            arguments.add(day);
        }
        
        return jdbcTemplate.query(query_salary_list, arguments.toArray(), new BeanPropertyRowMapper(AttendanceDTO.class));
    }

}
