/*
 * Employee DAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Employee DAO interface contains all methods related to Employee Function
 */
public interface EmployeeDAO extends BaseDAO<EmployeeDTO> {

    public int getCount() throws JDBCTemplateException;

    public EmployeeDTO insertUser(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public UserDTO updateUser(UserDTO userDTO) throws JDBCTemplateException;

    public List<EmployeeDTO> getEmploys(EmployeeDTO employeeDTO, String status) throws JDBCTemplateException;

    public List<EmployeeDTO> getEmployee(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException;

    public List<EmployeeDTO> getEmployeeDetail(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException;

    public List<EmployeeDTO> getAllEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public AttendanceDTO uploadAttendence(int year, int month, AttendanceDTO attendanceDTO) throws JDBCTemplateException;

    public int checkPendingTemp() throws JDBCTemplateException;

    public List<AttendanceDTO> getAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException;

    public List<AttendanceDTO> getPendingAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException;

    public AttendanceDTO authorizeAttendance(AttendanceDTO attendanceDTO,String user) throws JDBCTemplateException;

    public int rejectAttendence() throws JDBCTemplateException;

    public List<EmployeeDTO> getDevEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public List<EmployeeDTO> getUIEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public List<EmployeeDTO> getQAEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public List<EmployeeDTO> getReportEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException;

    public void insertSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException;

    public List<SalaryDTO> getSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException;

    public List<SalaryDTO> getLeaveCount(SalaryDTO salaryDTO) throws JDBCTemplateException;

    public List<SalaryDTO> getSalaryTmpDetails(SalaryDTO salaryDTO) throws JDBCTemplateException;

    public SalaryDTO saveSalaryInput(SalaryDTO salaryDTO) throws JDBCTemplateException;

    public List<SalaryDTO> getReportSalary(String user_code, int fromYear, int fromMonth, int toYear, int toMonth) throws JDBCTemplateException;

    public List<AttendanceDTO> getReportAtten(String user_code, int fromYear, int fromMonth, int toYear, int toMonth, int day) throws JDBCTemplateException;

}
