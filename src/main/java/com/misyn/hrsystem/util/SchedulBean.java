
package com.misyn.hrsystem.util;

import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.service.custom.EmployeeService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author Shanaka
 * This class contains, spring scheduler
 * Every month 29 11.59 P.M run this following methods 
 * The methods are responsible for get data employee mst table, attendance mst and leave mst then syn data 
 * create object list for employee including salary attendance and leave.
 */

public class SchedulBean {

    private static final Logger LOGGER = Logger.getLogger(SchedulBean.class);


    @Autowired
    private EmployeeService employeeService;

//    @Scheduled(fixedRate = 60000)
    @Scheduled(cron = "0 59 23 29 * ?") //-----Provide Time(59 23) and date(29)
//    @Scheduled(cron = "0 46 8 * * *")
    public void printMessage() {

        List<SalaryDTO> salaryList = null;
        List<SalaryDTO> leaveList = null;

        SalaryDTO salaryDTO = new SalaryDTO();

        try {

            /**
             * This Method support to get employee details by sync attendance and salary 
             */
            salaryList = employeeService.getSalaryTmp(salaryDTO);

            //---Get employee Leave count
            leaveList = employeeService.getLeaveCount(salaryDTO);

            //---This time create single object per user by adding leave count
            for (int j = 0; salaryList.size() > j; j++) {
                for (int i = 0; leaveList.size() > i; i++) {
                    if (leaveList.get(i).getUser_code().equals(salaryList.get(j).getUser_code())) {
                        salaryList.get(j).setLeave_days(leaveList.get(i).getLeave_days());
                    }

                }
            }

            //---Insert Details to salary tmp
            for (SalaryDTO dTO : salaryList) {
                employeeService.insertSalaryTmp(dTO);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
