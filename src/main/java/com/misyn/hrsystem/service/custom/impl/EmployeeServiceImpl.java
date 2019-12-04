/*
 * Employee Service Impl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.EmployeeDAO;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.util.Cryptography;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Cryptography cryp = new Cryptography();

    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * Insert Employee
     * @param employee
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO insert(EmployeeDTO employee) throws JDBCTemplateException {

        EmployeeDTO employeeDTO = null;
        try {
            employeeDTO = employeeDAO.saveMaster(employee);

            employeeDTO.setNic(cryp.encrypt(employeeDTO.getNic()));
            employeeDTO = employeeDAO.insertUser(employee);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return employeeDTO;

    }

    /**
     * Update employee
     * @param employee
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO update(EmployeeDTO employee) throws JDBCTemplateException {
        EmployeeDTO employeeDTO = null;
        try {
            employeeDTO = employeeDAO.updateMaster(employee);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return employeeDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO delete(EmployeeDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * Get employee count from MSTER table
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int getCount() throws JDBCTemplateException {
        return employeeDAO.getCount();

    }

    /**
     * 
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public EmployeeDTO insertUser(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get employees by passing employee status
     * @param employeeDTO
     * @param status
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmploys(EmployeeDTO employeeDTO, String status) throws JDBCTemplateException {
        return employeeDAO.getEmploys(employeeDTO, status);
    }

    /**
     * Get employee details by passing employee code
     * @param employeeDTO
     * @param code
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmployee(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException {
        return employeeDAO.getEmployee(employeeDTO, code);
    }

    /**
     * Upload employee attendance pass year and month
     * @param year
     * @param month
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public AttendanceDTO uploadAttendence(int year, int month, AttendanceDTO attendanceDTO) throws JDBCTemplateException {

        return employeeDAO.uploadAttendence(year, month, attendanceDTO);
    }

    /**
     * return 1 if there are pending data in temporary table else pass 0
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkPendingTemp() throws JDBCTemplateException {
        return employeeDAO.checkPendingTemp();
    }

    /**
     * Pass list object of attendance passing DTO contains search parameters(Year,month)
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<AttendanceDTO> getAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException {
        return employeeDAO.getAttendance(attendanceDTO);
    }

    /**
     * Pass list object of attendance passing DTO contains search parameters(Year,month)
     * @param attendanceDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<AttendanceDTO> getPendingAttendance(AttendanceDTO attendanceDTO) throws JDBCTemplateException {
        return employeeDAO.getPendingAttendance(attendanceDTO);
    }

    /**
     * auth attendance
     * @param attendanceDTO
     * @param user
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public AttendanceDTO authorizeAttendance(AttendanceDTO attendanceDTO,String user) throws JDBCTemplateException {
        return employeeDAO.authorizeAttendance(attendanceDTO,user);
    }

    /**
     * reject attendance
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int rejectAttendence() throws JDBCTemplateException {
        return employeeDAO.rejectAttendence();
    }

    /**
     * get employee list
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getAllEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return employeeDAO.getAllEmployees(employeeDTO);
    }

    /**
     * update user
     * @param userDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO) throws JDBCTemplateException {
        return employeeDAO.updateUser(userDTO);
    }

    /**
     * get employee passing user code
     * @param employeeDTO
     * @param code
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getEmployeeDetail(EmployeeDTO employeeDTO, String code) throws JDBCTemplateException {
        return employeeDAO.getEmployeeDetail(employeeDTO, code);
    }

    /**
     * get development employees
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getDevEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return employeeDAO.getDevEmployees(employeeDTO);
    }

    /**
     * get UI employees
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getUIEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return employeeDAO.getUIEmployees(employeeDTO);
    }

    /**
     * get QA employees
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getQAEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return employeeDAO.getQAEmployees(employeeDTO);
    }

    /**
     * get employees for generate report
     * @param employeeDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<EmployeeDTO> getReportEmployees(EmployeeDTO employeeDTO) throws JDBCTemplateException {
        return employeeDAO.getReportEmployees(employeeDTO);
    }

    /**
     * insert sync data (attendance,leave,employees) to salary tmp
     * @param salaryDTO
     * @throws JDBCTemplateException 
     */
    @Override
    public void insertSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException {
        employeeDAO.insertSalaryTmp(salaryDTO);
    }

    /**
     * get data from salary TMP
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getSalaryTmp(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return employeeDAO.getSalaryTmp(salaryDTO);
    }

    /**
     * get employee leave count by sync emp mst and leave mst
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getLeaveCount(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return employeeDAO.getLeaveCount(salaryDTO);
    }

    /**
     * get salary details with name of the employee related to user_code
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<SalaryDTO> getSalaryTmpDetails(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return employeeDAO.getSalaryTmpDetails(salaryDTO);
    }

    /**
     * Insert Salary MST when user click on authorize salary
     * @param salaryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public SalaryDTO saveSalaryInput(SalaryDTO salaryDTO) throws JDBCTemplateException {
        return employeeDAO.saveSalaryInput(salaryDTO);
    }

    /**
     * Generate report salary, pass details
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
        return employeeDAO.getReportSalary(user_code,fromYear,fromMonth,toYear,toMonth);
    }

    /**
     * Generate report attendance, pass details
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
    public List<AttendanceDTO> getReportAtten(String user_code, int fromYear, int fromMonth, int toYear, int toMonth,int day) throws JDBCTemplateException {
        return employeeDAO.getReportAtten(user_code,fromYear,fromMonth,toYear,toMonth,day);
    }

}
