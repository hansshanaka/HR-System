/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.misyn.hrsystem.dto.custom.ActivityLogDTO;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.FoodService;
import com.misyn.hrsystem.service.custom.LeaveService;
import com.misyn.hrsystem.service.custom.MedicalService;
import com.misyn.hrsystem.service.custom.ProjectService;
import com.misyn.hrsystem.service.custom.TravelService;
import com.misyn.hrsystem.service.custom.UserCategoryService;
import com.misyn.hrsystem.service.custom.UserDepartmentService;
import com.misyn.hrsystem.service.custom.UserRoleService;
import com.misyn.hrsystem.service.custom.UserStatusService;
import com.misyn.hrsystem.service.custom.UserTypeService;
import com.misyn.hrsystem.util.AppConstant;
import static com.misyn.hrsystem.util.AppConstant.USER_TYPE_LIST;
import static com.misyn.hrsystem.util.AppURL.PATH_ACTIVTY_LOG_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_ATTENDANCE_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_LEAVE_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_FOOD_REIMBU_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_MEDICAL_REIMBU_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_PROJECT_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_SALARY_REP_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_TAXI_REIMBU_REP_VIEW;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka Report Controller contains all methods related to Report
 * Function
 */
@Controller
@RequestMapping("/report/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class ReportController {

    private static final Logger LOGGER = Logger.getLogger(LeaveController.class);

    private static final String USER_DTO = "userDTO";

    String reportName = "";

    JasperReport jasperReport;
    JasperPrint jasperPrint;
    JasperDesign jasperDesign;
    HashMap parameterMap = new HashMap();

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private Environment environment;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private MedicalService medicalService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserDepartmentService userDepartmentService;

    /**
     * Activity view
     * @param aLogDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewActivity", method = RequestMethod.GET)
    public ModelAndView getActivityRepView(@ModelAttribute ActivityLogDTO aLogDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ACTIVTY_LOG_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Activity Log Report view");
        return modelAndView;
    }

    /**
     * 
     * @param employeeDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewEmployeeRep", method = RequestMethod.GET)
    public ModelAndView getEmpRepView(@ModelAttribute EmployeeDTO employeeDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
        modelAndView.addObject(USER_TYPE_LIST, initializeUserTypeList());
        modelAndView.addObject(AppConstant.USER_STATUS_LIST, initializeStatusList());
        modelAndView.addObject(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());
        modelAndView.addObject(AppConstant.USER_ROLE_LIST, initializeUserRoleList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Employee Report view");
        return modelAndView;
    }

    /**
     * 
     * @param foodDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewFoodReimRep", method = RequestMethod.GET)
    public ModelAndView getFoodRepView(@ModelAttribute FoodDTO foodDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_FOOD_REIMBU_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Food Reimbursment Report view");
        return modelAndView;
    }

    /**
     * 
     * @param taxiDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewTravelReimRep", method = RequestMethod.GET)
    public ModelAndView getTravelRepView(@ModelAttribute TaxiDTO taxiDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_TAXI_REIMBU_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Travel Reimbursment Report view");
        return modelAndView;
    }

    /**
     * 
     * @param medicalDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewMedicalReimRep", method = RequestMethod.GET)
    public ModelAndView getMedicalRepView(@ModelAttribute MedicalDTO medicalDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_MEDICAL_REIMBU_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Medical Reimbursment Report view");
        return modelAndView;
    }

    /**
     * 
     * @param leaveDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewEmpLeaveRep", method = RequestMethod.GET)
    public ModelAndView getEmpLeaveRepView(@ModelAttribute LeaveDTO leaveDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_LEAVE_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Employee Leave Report view");
        return modelAndView;
    }

    /**
     * 
     * @param projectDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewProjectRep", method = RequestMethod.GET)
    public ModelAndView getProjectRepView(@ModelAttribute ProjectDTO projectDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_PROJECT_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Project Report view");
        return modelAndView;
    }

    /**
     * 
     * @param activeDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "activityLogPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printActivityLogRepPdf(@ModelAttribute ActivityLogDTO activeDTO,
            HttpServletResponse response, HttpServletRequest request) {

        ArrayList<ActivityLogDTO> ActivityLogList = new ArrayList<>();
        ActivityLogDTO logDTO = new ActivityLogDTO();
        logDTO.setDate("");
        logDTO.setTime("");
        logDTO.setUser_code("");
        logDTO.setIp("");
        logDTO.setDescription("");

        try {
            reportName = "ActivityReport";
            String sourceFileName = environment.getProperty("report.path") + "/activityReport.jasper";

            if (activeDTO.getUser_code().equals("Please Select One")) {
                activeDTO.setUser_code("");
            }

            List<ActivityLogDTO> logList = activityService.getLog(activeDTO);
            //--iterate List
            for (int i = 0; i < logList.size(); i++) {
                logList.get(i).setActivity_log_id(i + 1);
            }

            if (logList.isEmpty()) {
                logList.add(logDTO);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(logList));

            exportPDF(jasperPrint, baos);

            String fileName = "ActivityLogReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param leaveDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "empLeavePdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printEmployeeLeavePdf(@ModelAttribute LeaveDTO leaveDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<LeaveDTO> leaveList = null;
        LeaveDTO leave = new LeaveDTO();
        leave.setStart_date("");
        leave.setEnd_date("");
        leave.setApp_user("");
        leave.setInput_date("");
        leave.setAuth_user("");
        leave.setAuth_date("");

        try {
            reportName = "EmployeeLeaveReport";
            String sourceFileName = environment.getProperty("report.path") + "/empLeaveReport.jasper";

            if (leaveDTO.getUser_code().equals("Please Select One")) {
                leaveDTO.setUser_code("");
            }

            leaveList = leaveService.getReportLeaveList(leaveDTO);

            //--iterate List
            for (int i = 0; i < leaveList.size(); i++) {
                leaveList.get(i).setLeave_id(i + 1);
            }

            if (leaveList.isEmpty()) {
                leaveList.add(leave);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(leaveList));

            exportPDF(jasperPrint, baos);

            String fileName = "EmployeeLeaveReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param projectDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "projectPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printProjectPdf(@ModelAttribute ProjectDTO projectDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<ProjectDTO> projectList = null;

        List<ProjectDTO> proList = new ArrayList<>();
        ProjectDTO pro = null;
        ProjectDTO project = new ProjectDTO();

        int tem_pro_id = 0;

        try {
            reportName = "ProjectReport";
            String sourceFileName = environment.getProperty("report.path") + "/projectDetail.jasper";

            if (projectDTO.getUser_code().equals("Please Select One")) {
                projectDTO.setUser_code("");
            }

            projectList = projectService.getRepProjectList(projectDTO);

            //--iterate List
            for (int i = 0; i < projectList.size(); i++) {

                if (projectList.get(i).getProject_id() == tem_pro_id) {
                    tem_pro_id = projectList.get(i).getProject_id();

                    pro.setDev_emp_nam(pro.getDev_emp_nam() + "," + projectList.get(i).getDev_emp_nam());

                } else {
                    if (pro == null) {
                        pro = new ProjectDTO();
                        pro = projectList.get(i);
                        tem_pro_id = projectList.get(i).getProject_id();
                    } else {
                        proList.add(pro);

                        pro = new ProjectDTO();
                        pro = projectList.get(i);
                        tem_pro_id = projectList.get(i).getProject_id();

                    }
                }
            }

            if (proList.isEmpty()) {
                project.setProject_name("");
                project.setDescription("");
                project.setClient_name("");
                project.setStart_date("");
                project.setQa_date("");
                project.setUat_date("");
                project.setDelivary_date("");
                project.setDev_emp_nam("");
                proList.add(project);
            } else {
                proList.add(pro);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(proList));

            exportPDF(jasperPrint, baos);

            String fileName = "projectReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param foodDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "foodRepPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printFoodRepPdf(@ModelAttribute FoodDTO foodDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<FoodDTO> foodList = null;

        FoodDTO food = new FoodDTO();
        food.setProject_name("");
        food.setAmount(0.00);
        food.setUser_code("");
        food.setInput_date("");
        food.setAuth_user("");
        food.setAuth_date("");

        try {
            reportName = "FoodReimbursementReport";
            String sourceFileName = environment.getProperty("report.path") + "/foodReport.jasper";

            if (foodDTO.getUser_code().equals("Please Select One")) {
                foodDTO.setUser_code("");
            }

            foodList = foodService.getRepFoodList(foodDTO);

            //--iterate List
            for (int i = 0; i < foodList.size(); i++) {
                foodList.get(i).setFood_id(i + 1);
            }
            if (foodList.isEmpty()) {
                foodList.add(food);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(foodList));

            exportPDF(jasperPrint, baos);

            String fileName = "FoodReimbursementReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param taxiDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "travelRepPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printTravelRepPdf(@ModelAttribute TaxiDTO taxiDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<TaxiDTO> taxiList = null;

        TaxiDTO taxi = new TaxiDTO();
        taxi.setProject_name("");
        taxi.setStart("");
        taxi.setEnd("");
        taxi.setUser_code("");
        taxi.setInput_date("");
        taxi.setAuth_user("");
        taxi.setAuth_date("");

        try {
            reportName = "TravelReimbursementReport";
            String sourceFileName = environment.getProperty("report.path") + "/taxiReport.jasper";

            if (taxiDTO.getUser_code().equals("Please Select One")) {
                taxiDTO.setUser_code("");
            }

            taxiList = travelService.getRepTravelList(taxiDTO);

            //--iterate List
            for (int i = 0; i < taxiList.size(); i++) {
                taxiList.get(i).setTaxi_id(i + 1);
            }

            if (taxiList.isEmpty()) {
                taxiList.add(taxi);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(taxiList));

            exportPDF(jasperPrint, baos);

            String fileName = "TravelReimbursementReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param medicalDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "medicalRepPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printMedicalRepPdf(@ModelAttribute MedicalDTO medicalDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<MedicalDTO> medicalList = null;

        MedicalDTO medical = new MedicalDTO();
        medical.setDescription("");
        medical.setUser_code("");
        medical.setInput_date("");
        medical.setAuth_user("");
        medical.setAuth_date("");

        try {
            reportName = "MedicalReimbursementReport";
            String sourceFileName = environment.getProperty("report.path") + "/medicalReport.jasper";

            if (medicalDTO.getUser_code().equals("Please Select One")) {
                medicalDTO.setUser_code("");
            }

            medicalList = medicalService.getRepMedicalList(medicalDTO);

            //--iterate List
            for (int i = 0; i < medicalList.size(); i++) {
                medicalList.get(i).setMedical_id(i + 1);
            }

            if (medicalList.isEmpty()) {
                medicalList.add(medical);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(medicalList));

            exportPDF(jasperPrint, baos);

            String fileName = "MedicalReimbursementReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * 
     * @param employeeDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "employeePdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printEmployeePdf(@ModelAttribute EmployeeDTO employeeDTO,
            HttpServletResponse response, HttpServletRequest request) {

        List<EmployeeDTO> empList = null;
        EmployeeDTO employee = new EmployeeDTO();

        try {
            reportName = "EmployeeReport";
            String sourceFileName = environment.getProperty("report.path") + "/employeeReport.jasper";

            if (employeeDTO.getUser_code().equals("Please Select One")) {
                employeeDTO.setUser_code("");
            }
            if (employeeDTO.getUser_type_id().equals("0")) {
                employeeDTO.setUser_type_id("");
            }
            if (employeeDTO.getUser_role_id().equals("0")) {
                employeeDTO.setUser_role_id("");
            }

            empList = employeeService.getReportEmployees(employeeDTO);

            if (empList.isEmpty()) {
                employee.setUser_code("");
                employee.setName("");
                employee.setUse_name("");
                employee.setAddress("");
                employee.setGender("");
                employee.setDob("");
                employee.setEmail("");
                employee.setMobile_no("");
                employee.setLand_no("");
                employee.setEmg_con_no("");
                employee.setNic("");
                employee.setMarital_status("");
                employee.setJoin_date("");
                employee.setConfirm_date("");
                employee.setResign_date("");
                employee.setDepartment_name("");
                employee.setCategory_name("");
                employee.setUser_type_id("");
                employee.setRole_name("");
                employee.setEmp_status("");
                employee.setSalary(new BigDecimal("0.00"));
                empList.add(employee);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(empList));

            exportPDF(jasperPrint, baos);

            String fileName = "EmployeeReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static class CompareEmployee implements Comparator<EmployeeDTO> {

        @Override
        public int compare(EmployeeDTO emp1, EmployeeDTO emp2) {
            return emp1.getUser_code().compareTo(emp2.getUser_code());
        }
    }

    public void exportPDF(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jp));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }

    private void writeReportToResponseStream(HttpServletResponse response, ByteArrayOutputStream baos) throws IOException {

        ServletOutputStream outputStream = response.getOutputStream();
        baos.writeTo(outputStream);
        outputStream.flush();

    }

    public List<EmployeeDTO> initializeUserList() {

        List<EmployeeDTO> employee = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            employee = employeeService.getAllEmployees(employeeDTO);

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        EmployeeDTO empList = new EmployeeDTO();
        empList.setUser_code("Please Select One");
        employee.add(0, empList);

        return employee;
    }

    public List<UserCategoryDTO> initializeCategoryList() {

        List<UserCategoryDTO> userDep = new ArrayList<>();
        UserCategoryDTO userCategoryDTO = new UserCategoryDTO();
        try {
            userDep = userCategoryService.getUserCategoryList(userCategoryDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        UserCategoryDTO userCatList = new UserCategoryDTO();
        userCatList.setCategory_id(0);
        userCatList.setCategory_name("Please Select");
        userDep.add(0, userCatList);

        return userDep;
    }

    public List<UserTypeDTO> initializeUserTypeList() {

        List<UserTypeDTO> userType = new ArrayList<>();
        UserTypeDTO userTypeDTO = new UserTypeDTO();
        try {
            userType = userTypeService.getUserTypeList(userTypeDTO);
        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        UserTypeDTO userTypeList = new UserTypeDTO();
        userTypeList.setUser_type_id("0");
        userTypeList.setType_name("Please Select One");
        userType.add(0, userTypeList);

        return userType;
    }

    public List<UserDepartmentDTO> initializeDepartmentList() {

        List<UserDepartmentDTO> userDep = new ArrayList<>();
        UserDepartmentDTO userDepartmentDTO = new UserDepartmentDTO();
        try {
            userDep = userDepartmentService.getUserDepartmentList(userDepartmentDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        UserDepartmentDTO userDepList = new UserDepartmentDTO();
        userDepList.setDepartment_code(0);
        userDepList.setDepartment_name("Please Select");
        userDep.add(0, userDepList);

        return userDep;

    }

    public List<UserStatusDTO> initializeStatusList() {

        List<UserStatusDTO> userStat = new ArrayList<>();
        UserStatusDTO userStatusDTO = new UserStatusDTO();
        try {
            userStat = userStatusService.getUserStatusList(userStatusDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        UserStatusDTO userStatList = new UserStatusDTO();
        userStatList.setStatus_id(0);
        userStatList.setEmp_status("Please Select");
        userStat.add(0, userStatList);

        return userStat;
    }

    /**
     * 
     * @return 
     */
    public List<UserRoleDTO> initializeUserRoleList() {

        List<UserRoleDTO> userRole = new ArrayList<>();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        try {
            userRole = userRoleService.getAllRoleList(userRoleDTO);
        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        UserRoleDTO userRoleList = new UserRoleDTO();
        userRoleList.setUser_role_id("0");
        userRoleList.setRole_name("Please Select One");
        userRole.add(0, userRoleList);

        return userRole;
    }

    /**
     * 
     * @param salaryDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "viewSalaryRep", method = RequestMethod.GET)
    public ModelAndView getSalaryRepView(@ModelAttribute SalaryDTO salaryDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_SALARY_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Employee Salary Report view");
        return modelAndView;
    }

    /**
     * 
     * @param salaryDTO
     * @param response
     * @param request 
     */
    @RequestMapping(value = "empSalaryPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public void printEmployeeSalaryPdf(@ModelAttribute SalaryDTO salaryDTO,
            HttpServletResponse response, HttpServletRequest request) {

        SalaryDTO salary = new SalaryDTO();
        List<SalaryDTO> salaryList = null;
        int fromYear = 0;
        int fromMonth = 0;
        int toYear = 0;
        int toMonth = 0;

        try {
            reportName = "EmployeeSalaryReport";
            String sourceFileName = environment.getProperty("report.path") + "/empAttenSalary.jasper";

            if (salaryDTO.getUser_code().equals("Please Select One")) {
                salaryDTO.setUser_code("");
            }
            if (!salaryDTO.getStart_date().equals("")) {
                String[] fromParts = salaryDTO.getStart_date().split("-");
                fromYear = Integer.parseInt(fromParts[0]);
                fromMonth = Integer.parseInt(fromParts[1]);
            }

            if (!salaryDTO.getEnd_date().equals("")) {
                String[] toParts = salaryDTO.getStart_date().split("-");
                toYear = Integer.parseInt(toParts[0]);
                toMonth = Integer.parseInt(toParts[1]);
            }

            salaryList = employeeService.getReportSalary(salaryDTO.getUser_code(), fromYear, fromMonth, toYear, toMonth);

            //--iterate List
            for (int i = 0; i < salaryList.size(); i++) {
                salaryList.get(i).setSalary_id(i + 1);
            }

            if (salaryList.isEmpty()) {
                salary.setUser_name("");
                salaryList.add(salary);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(salaryList));

            exportPDF(jasperPrint, baos);

            String fileName = "EmployeeSalaryReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @RequestMapping(value = "attendanceRep", method = RequestMethod.GET)
    public ModelAndView getAttendanceRepView(@ModelAttribute AttendanceDTO attendanceDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ATTENDANCE_REP_VIEW);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Employee Attendance Report view");
        return modelAndView;
    }

    /**
     * 
     * @param attendanceDTO
     * @param response
     * @param request
     * @param day 
     */
    @RequestMapping(value = "empAttendancePdf", method = RequestMethod.POST)
    public void printEmployeeAttendancePdf(@ModelAttribute AttendanceDTO attendanceDTO,
            HttpServletResponse response, HttpServletRequest request, @RequestParam("day") int day) {

        List<AttendanceDTO> attenList = null;
        int fromYear = 0;
        int fromMonth = 0;
        int toYear = 0;
        int toMonth = 0;

        AttendanceDTO atten = new AttendanceDTO();

        try {
            reportName = "EmployeeAttendanceReport";
            String sourceFileName = environment.getProperty("report.path") + "/employeeAttendance.jasper";

            if (attendanceDTO.getUser_code().equals("Please Select One")) {
                attendanceDTO.setUser_code("");
            }
            if (!attendanceDTO.getStart_date().equals("")) {
                String[] fromParts = attendanceDTO.getStart_date().split("-");
                fromYear = Integer.parseInt(fromParts[0]);
                fromMonth = Integer.parseInt(fromParts[1]);
            }

            if (!attendanceDTO.getEnd_date().equals("")) {
                String[] toParts = attendanceDTO.getStart_date().split("-");
                toYear = Integer.parseInt(toParts[0]);
                toMonth = Integer.parseInt(toParts[1]);
            }

            attenList = employeeService.getReportAtten(attendanceDTO.getUser_code(), fromYear, fromMonth, toYear, toMonth, attendanceDTO.getDay());

            //--iterate List
            for (int i = 0; i < attenList.size(); i++) {

            }

            if (attenList.isEmpty()) {
                atten.setEmp_name("");
                atten.setOn_time("");
                atten.setOff_time("");
                attenList.add(atten);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            jasperPrint = JasperFillManager.fillReport(sourceFileName, parameterMap, new JRBeanCollectionDataSource(attenList));

            exportPDF(jasperPrint, baos);

            String fileName = "EmployeeAttendanceReport.pdf";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            writeReportToResponseStream(response, baos);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

}
