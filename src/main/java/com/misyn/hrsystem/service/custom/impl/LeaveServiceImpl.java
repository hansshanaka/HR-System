/*
 * LeaveServiceImpl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.EmployeeDAO;
import com.misyn.hrsystem.dao.custom.LeaveDAO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.LeaveService;
import com.misyn.hrsystem.util.MailMail;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * contains apply leave, authorize leave, cancel leave, reject leave
 */
@Service
@PropertySource(value = {"classpath:system.properties"})
public class LeaveServiceImpl implements LeaveService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

    MailMail mm = (MailMail) context.getBean("mailMail");

    @Autowired
    private LeaveDAO leaveDAO;

    @Autowired
    private Environment environment;

    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * save Leave details contains in leave DTO
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO insert(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.saveMaster(leaveDTO);
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO update(LeaveDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Delete Leave when cancel or reject
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO delete(LeaveDTO leaveDTO) throws JDBCTemplateException {
        boolean result = false;
        try {
            result = leaveDAO.deleteMaster(leaveDTO.getLeave_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? leaveDTO : null;
    }

    /**
     * check leave balance
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int checkLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.checkLeaveBalance(leaveDTO);
    }

    /**
     * update leave Balance
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO updateLeaveBalance(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.updateLeaveBalance(leaveDTO);
    }

    /**
     * get Leave List
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getLeaveList(leaveDTO);
    }

    /**
     * delete Leave when reject       
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO deleteLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        boolean result = false;
        try {
            result = leaveDAO.deleteLeave(leaveDTO.getLeave_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? leaveDTO : null;
    }

    /**
     * get Pending Leave
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getPendingLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getPendingLeave(leaveDTO);
    }

    /**
     * authorize leave one or more leave can authorize
     * leave details get as a list for loop is responsible to save data 
     * send email to applied user
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO authorizeLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        LeaveDTO leave = null;
        String emp_code = "";
        List<EmployeeDTO> empList = null;
        EmployeeDTO employeeDTO = new EmployeeDTO();

        try {

            Integer[] leaveIDArray = leaveDTO.getLeaveID();
            for (int i = 0; i < leaveIDArray.length; i++) {

                leave = leaveDAO.authorizeLeave(leaveDTO,leaveIDArray[i]);
                emp_code = leaveDAO.getUserCode(leaveIDArray[i]);
                empList = employeeDAO.getEmployee(employeeDTO, emp_code);
                if (empList.size() > 0) {
                    employeeDTO = empList.get(0);

                    //--send email to apply user
                    String emailPath = environment.getProperty("MailAddress");
                    mm.sendMail(emailPath,
                            employeeDTO.getEmail(),
                            "MI Synergy HR",
                            "Dear " + emp_code + "\n\n "
                            + "Leave applied authorized successfully");
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return leave;
    }

    /**
     * get authorize leave 
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getAuthLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getAuthLeaveList(leaveDTO);
    }

    /**
     * get pending leave
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getPendingCancelLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getPendingCancelLeave(leaveDTO);
    }

    /**
     * get authorize cancel leave
     * when cancel a leave, leave balance will update (increment leave count)
     * of applied upser
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO authorizeCancelLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        LeaveDTO leave = null;
        int lBal = 0;
        int noDays = 0;

        try {
            Integer[] leaveIDArray = leaveDTO.getLeaveID();
            for (int i = 0; i < leaveIDArray.length; i++) {

                lBal = leaveDAO.getLeaveBalLID(leaveIDArray[i]);
                noDays = leaveDAO.getNoOfDaysLID(leaveIDArray[i]);

                //---set Leave Balance to Leave DTO
                leaveDTO.setLeave_balance(lBal + noDays);

                leave = leaveDAO.authorizeCancelLeave(leaveDTO, leaveIDArray[i]);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return leave;
    }

    /**
     * Reject leave cancel
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO rejectCancelLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        LeaveDTO leave = null;
        try {
            Integer[] leaveIDArray = leaveDTO.getLeaveID();
            for (int i = 0; i < leaveIDArray.length; i++) {
                leave = leaveDAO.rejectCancelLeave(leaveDTO, leaveIDArray[i]);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return leave;
    }

    /**
     * Reject Leave
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public LeaveDTO rejectLeave(LeaveDTO leaveDTO) throws JDBCTemplateException {
        LeaveDTO leave = null;
        int lBal = 0;
        int noDays = 0;

        try {
            Integer[] leaveIDArray = leaveDTO.getLeaveID();
            for (int i = 0; i < leaveIDArray.length; i++) {

                lBal = leaveDAO.getLeaveBalLID(leaveIDArray[i]);
                noDays = leaveDAO.getNoOfDaysLID(leaveIDArray[i]);

                //---set Leave Balance to Leave DTO
                leaveDTO.setLeave_balance(lBal + noDays);

                leave = leaveDAO.rejectLeave(leaveDTO, leaveIDArray[i]);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return leave;
    }

    /**
     * Get Approved Leave List
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getApprovedLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getApprovedLeaveList(leaveDTO);
    }

    /**
     * Generate Leave Report
     * @param leaveDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<LeaveDTO> getReportLeaveList(LeaveDTO leaveDTO) throws JDBCTemplateException {
        return leaveDAO.getReportLeaveList(leaveDTO);
    }

    /**
     * Get applied user related to leave ID
     * @param leaveID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public String getUserCode(int leaveID) throws JDBCTemplateException {
        return leaveDAO.getUserCode(leaveID);
    }

}
