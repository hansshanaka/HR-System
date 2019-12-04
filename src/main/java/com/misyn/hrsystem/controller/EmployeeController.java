/*
 * Employee Controller class
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.SalaryDTO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserCategoryService;
import com.misyn.hrsystem.service.custom.UserDepartmentService;
import com.misyn.hrsystem.service.custom.UserRoleService;
import com.misyn.hrsystem.service.custom.UserStatusService;
import com.misyn.hrsystem.service.custom.UserTypeService;
import com.misyn.hrsystem.util.AppConstant;
import static com.misyn.hrsystem.util.AppConstant.USER_TYPE_LIST;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_SALARY_VIEW;
import com.misyn.hrsystem.util.Cryptography;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka Employee Controller contains all methods related to Employee
 * Function
 */
@Controller
@RequestMapping("/employee/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger(UserStatusController.class);

    private static final String USER_DTO = "userDTO";

    Cryptography cryp = new Cryptography();

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserDepartmentService userDepartmentService;

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private Environment environment;

    /**
     *
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "empDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView employeeDetailView(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {

        String recordtype = "i";
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_VIEW);
        model.addObject(AppConstant.RECORD_TYPE, recordtype);
        model.addObject(AppConstant.USER_STATUS_LIST, initializeStatusList());
        return model;
    }

    //------Attendance Upload View
    @RequestMapping(value = "attendance", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView uploadAttendanceView(@ModelAttribute("attendanceDTO") AttendanceDTO attendanceDTO) {

        String recordtype = "i";
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW);
        model.addObject(AppConstant.RECORD_TYPE, recordtype);
        return model;
    }

    //--- Check Attendance (Attendance View)
    @RequestMapping(value = "viewAttendance", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView employeeAttendabceView(@ModelAttribute("attendanceDTO") AttendanceDTO attendanceDTO) {

        String recordtype = "i";
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_ATTENDENCE_VIEW);
        model.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        model.addObject(AppConstant.RECORD_TYPE, recordtype);
        return model;
    }

    /**
     * Return List of attendance
     *
     * @param attendanceDTO
     * @param user_code
     * @param year
     * @param month
     * @param day
     * @return
     */
    @RequestMapping(value = "viewEmpAttendances", method = {RequestMethod.GET})
    @ResponseBody
    public String viewUserAttendance(@ModelAttribute AttendanceDTO attendanceDTO,
            @RequestParam("user_code") String user_code,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<AttendanceDTO> empAttendanceList = null;

        try {
            if (attendanceDTO.getUser_code().equals("Please Select One")) {
                attendanceDTO.setUser_code("");
            }
            empAttendanceList = employeeService.getAttendance(attendanceDTO);

            for (AttendanceDTO attendance : empAttendanceList) {
                list.add(new String[]{attendance.getEmp_name(), Integer.toString(attendance.getYear()),
                    Integer.toString(attendance.getMonth()),
                    Integer.toString(attendance.getDay()), attendance.getOn_time(), attendance.getOff_time(),
                    Integer.toString(attendance.getYear())});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

    /**
     * user List return as option fields
     *
     * @return
     */
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
     * @param request
     * @param userType
     * @param roleID
     * @param userRoleDTO
     * @return
     */
    @RequestMapping(value = "getUserRole", method = RequestMethod.GET)
    @ResponseBody
    public String viewUserRoleList(HttpServletRequest request, @RequestParam("typeId") String userType,
            @RequestParam("role_id") String roleID,
            @ModelAttribute UserRoleDTO userRoleDTO) {

        String option = "";
        StringBuilder stringBuilder = new StringBuilder();
        List<UserRoleDTO> userRoleList = null;
        stringBuilder.append("<option value=\"0\"> Please Select</option>");

        try {
            userRoleList = userRoleService.getUserRoleList(userType, userRoleDTO);

            for (UserRoleDTO userRole : userRoleList) {
                stringBuilder.append("<option value='" + userRole.getUser_role_id() + "' " + (userRole.getUser_role_id().equalsIgnoreCase(roleID) ? "selected" : "") + ">"
                        + userRole.getRole_name() + "</option>");

            }
            option = stringBuilder.toString();

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return option;
    }

    /**
     *
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "addEmployeeView", method = RequestMethod.POST)
    public ModelAndView addEmpView(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        String recordtype = "i";
        String empCode = "";
        EmployeeDTO emp = new EmployeeDTO();
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_EMPLOYEE_ADD_VIEW);
        try {
            //get Latest employee Code
            empCode = generateID();
            emp.setUser_code(empCode);

            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
            modelAndView.addObject(USER_TYPE_LIST, initializeUserTypeList());
            modelAndView.addObject(AppConstant.USER_STATUS_LIST, initializeStatusList());
            modelAndView.addObject(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());
            modelAndView.addObject(AppConstant.EMPLOYEE, emp);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     * user Category List return as option fields
     *
     * @return
     */
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

    /**
     * user type list return as option fields
     *
     * @return
     */
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

    /**
     * user Department List return as option fields
     *
     * @return
     */
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

    /**
     * Save employee
     *
     * @param empDTO
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "saveEmployee", method = RequestMethod.POST)
    public String insertEmployee(@ModelAttribute EmployeeDTO empDTO, ModelMap model,
            @RequestParam CommonsMultipartFile file,
            HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        EmployeeDTO employeeDTO = null;
        String recordtype = "i";
        String empCode = "";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        //---get Save Path From Propoty File
        String path = environment.getProperty("employee.path");

        String savePath = "/MIHRSystem/resources/Employee";
        long time = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

        try {
            if (empDTO != null) {
                empCode = generateID();

                empDTO.setUser_code(empCode);

                //--File Write (Employee Image save in physical location)
                byte barr[] = file.getBytes();

                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + "/" + time + fileExtension));
                bout.write(barr);
                bout.flush();
                bout.close();

                empDTO.setPhoto(savePath + "/" + time + fileExtension);

                employeeDTO = employeeService.insert(empDTO);

                if (employeeDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.USER_STATUS_LIST, initializeStatusList());
        }
        return url;
    }

    /**
     *
     * @param userDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute UserDTO userDTO, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserDTO upUserDTO = null;
        String recordtype = "i";
        String empCode = "";

        try {
            if (userDTO != null && !userDTO.getUser_code().equalsIgnoreCase("")) {

                userDTO.setPassword(cryp.encrypt(userDTO.getPassword()));
                upUserDTO = employeeService.updateUser(userDTO);

                if (upUserDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_RESET_PASSWORD_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_RESET_PASSWORD_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_RESET_PASSWORD_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.USER_STATUS_LIST, initializeStatusList());
        }
        return url;
    }

    /**
     *
     * @return @throws JDBCTemplateException
     */
    public String generateID() throws JDBCTemplateException {
        String empID = "mysin";
        int count = 0;

        count = employeeService.getCount();
        count++;

        return empID + "" + count;
    }

    /**
     *
     * @param userCode
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "getEmploys", method = RequestMethod.GET)
    @ResponseBody
    public String getEmploys(@RequestParam("empCode") String userCode, @ModelAttribute EmployeeDTO employeeDTO) {

        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<EmployeeDTO> empList = null;

        try {
            empList = employeeService.getEmploys(employeeDTO, userCode);

            for (EmployeeDTO employee : empList) {
                if (!employee.getUser_code().equals("admin")) {
                    list.add(new String[]{employee.getUser_code(), employee.getName(), employee.getRole_name(), employee.getCategory_name(),
                        employee.getDepartment_name(), employee.getEmp_status()});
                }
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return json;
    }

    /**
     * edit employee
     *
     * @param employeeDTO
     * @param empCode
     * @return
     */
    @RequestMapping(value = "editEmploye/{empID}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView editEmploye(EmployeeDTO employeeDTO, @PathVariable("empID") String empCode) {

        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_EMPLOYEE_ADD_VIEW);

        List<EmployeeDTO> empList = null;
        EmployeeDTO emp = new EmployeeDTO();

        try {
            empList = employeeService.getEmployee(employeeDTO, empCode);

            emp = empList.get(0);

            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.EMPLOYEE_LIST, empList);
            modelAndView.addObject(AppConstant.EMPLOYEE, emp);
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
            modelAndView.addObject(USER_TYPE_LIST, initializeUserTypeList());
            modelAndView.addObject(AppConstant.USER_STATUS_LIST, initializeStatusList());
            modelAndView.addObject(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     *
     * @param employee
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "updateEmp", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute EmployeeDTO employee, ModelMap model,
            @RequestParam CommonsMultipartFile file,
            HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        EmployeeDTO employeeDTO = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        //---get Save Path From Propoty File
        String path = environment.getProperty("employee.path");

        String savePath = "/MIHRSystem/resources/Employee";
        long time = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
        try {
            if (employee != null) {

                //--File Write
                byte barr[] = file.getBytes();

                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + "/" + time + fileExtension));
                bout.write(barr);
                bout.flush();
                bout.close();
                if (!filename.equalsIgnoreCase("")) {
                    employee.setPhoto(savePath + "/" + time + fileExtension);
                }

                employeeDTO = employeeService.update(employee);
                if (employeeDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
                model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
                model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
                model.addAttribute("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
                model.addAttribute(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());
                model.addAttribute(AppConstant.USER_STATUS_LIST, initializeStatusList());
            } catch (JDBCTemplateException ex) {
                LOGGER.error(ex);
            }
        }
        return url;
    }

    /**
     *
     * @param date
     * @param multiFiles
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadAttendence", method = RequestMethod.POST)
    public String uploadAttendence(@RequestParam("date") String date, @RequestParam("attFile") MultipartFile[] multiFiles, ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        String recordtype = "i";
        String filePath = "";
        AttendanceDTO resAttendanceDTO = null;
        File file = null;
        int count = 0;

        //----split date
        String[] datePart = date.split("/");
        int month = Integer.parseInt(datePart[0]);
        int day = Integer.parseInt(datePart[1]);
        int year = Integer.parseInt(datePart[2]);

        try {
            count = employeeService.checkPendingTemp();

            if (count == 0) {

                //-----Read File and data add to DTO
                //------variables-----------------//
                HSSFRow row = null;
                String cellValues[] = null;
                int cellType = 1;
                HSSFCell cell = null;
                int startRow = 1;
                int columnCount = 5;
                int rowCount = 0;
                HSSFWorkbook wb = null;
                HSSFSheet sheet = null;
                long size = 0;

                List<AttendanceDTO> attendensList = new LinkedList<>();
                file = File.createTempFile("temp", ".xls");
                multiFiles[0].transferTo(file);

                try {
//                InputStream myxls = new FileInputStream("D:/Attendance.xls");
                    InputStream myxls = new FileInputStream(file);

                    wb = new HSSFWorkbook(myxls);
                    sheet = wb.getSheetAt(0);

                } catch (IOException ex) {
                    LOGGER.error(ex);
                }

                cellValues = new String[columnCount + 1];

                int x;
                String DateValue = "";
                for (x = startRow; x <= 65635; x++) {
                    row = sheet.getRow(x);
                    if (row == null) {
                        break;//error message sheet empty
                    }
                    cell = row.getCell(0);
                    if (cell == null) {
                        break;//error message sheet empty
                    }

                    int leng = 0;
                    String cellValue = "";
                    int i;

                    for (i = 0; i <= columnCount; i++) {

                        cell = row.getCell((int) (i));

                        try {
                            cellType = cell.getCellType();
                        } catch (Exception e1) {
                            cellType = -1;
                        }

                        if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                            cellValues[i] = "";
                            if (i == 0) {
                                break;
                            } else {
                                cellValues[i] = "0";
                            }
                        }

                        if (cellType == HSSFCell.CELL_TYPE_STRING) {
                            cellValues[i] = cell.getStringCellValue();
                        } else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                            cellValues[i] = String.valueOf(cell.getNumericCellValue());
                        }
                    }

                    AttendanceDTO attendanceDTO = new AttendanceDTO();

                    if (Integer.parseInt(cellValues[0]) != year) {
                        errorMessage = "Invalide Year";
                        url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;

                    } else if (Integer.parseInt(cellValues[1]) != month) {
                        errorMessage = "Invalide Month";
                        url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;

                    } else if (Integer.parseInt(cellValues[2]) != day) {
                        errorMessage = "Invalide Day";
                        url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;

                    } else {

                        //---------values set to object
                        attendanceDTO.setYear(Integer.parseInt(cellValues[0]));
                        attendanceDTO.setMonth(Integer.parseInt(cellValues[1]));
                        attendanceDTO.setDay(Integer.parseInt(cellValues[2]));
                        attendanceDTO.setOn_time(cellValues[3].trim());
                        attendanceDTO.setOff_time(cellValues[4].trim());
                        attendanceDTO.setUser_code(cellValues[5].trim());
                        attendanceDTO.setInput_user(userCode);

                        resAttendanceDTO = employeeService.uploadAttendence(year, month, attendanceDTO);

                        if (resAttendanceDTO != null) {
                            successMessage = "Data Submited for authorization";
                            url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;
                        } else {
                            errorMessage = AppConstant.ERROR_MESSAGE;
                            url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;
                        }
                    }
                }

            } else {
                errorMessage = "Data Pending for Authorization";
                url = AppURL.PATH_EMPLOYEE_ATTENDENCE_UPLOAD_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
        }

        return url;
    }

    /**
     *
     * @param employeeDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "viewSalary", method = RequestMethod.GET)
    public ModelAndView getSalaryView(@ModelAttribute EmployeeDTO employeeDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        List<EmployeeDTO> empList = null;
        EmployeeDTO emp = new EmployeeDTO();

        try {
            empList = employeeService.getEmployeeDetail(employeeDTO, userCode);
            emp = empList.get(0);
        } catch (Exception e) {
        }

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_SALARY_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, empList);
        modelAndView.addObject(AppConstant.EMPLOYEE, emp);

        activityService.doLog(userCode, ip, userCode + " Access Salary Details view");
        return modelAndView;
    }

    /**
     * user List return as option fields
     *
     * @return
     */
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

    //------Salary Input View
    @RequestMapping(value = "inputSalaryView", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView salaryInputView(@ModelAttribute("attendanceDTO") SalaryDTO salaryDTO) {

        String recordtype = "i";
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_SALARY_INPUT_VIEW);
        model.addObject(AppConstant.RECORD_TYPE, recordtype);
        return model;
    }

    /**
     *
     * @param salaryDTO
     * @return
     */
    @RequestMapping(value = "getTmpSalary", method = {RequestMethod.GET})
    @ResponseBody
    public String getSalaryTmp(@ModelAttribute SalaryDTO salaryDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<SalaryDTO> empsalaryList = null;

        try {
            empsalaryList = employeeService.getSalaryTmpDetails(salaryDTO);

            for (SalaryDTO salary : empsalaryList) {
                list.add(new String[]{salary.getUser_name(), Integer.toString(salary.getYear()),
                    Integer.toString(salary.getMonth()), Double.toString(salary.getAttend_days()),
                    Double.toString(salary.getLeave_days())});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

    /**
     *
     * @param salary
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "salaryInputSave", method = RequestMethod.POST)
    public String authSalary(@ModelAttribute SalaryDTO salary, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        SalaryDTO salaryDTO = null;
        String recordtype = "e";
        String inputUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            salary.setInput_user(inputUser);
            salaryDTO = employeeService.saveSalaryInput(salary);

            if (salaryDTO != null) {
                successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_SALARY_INPUT_VIEW;
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_SALARY_INPUT_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            modelMap.addAttribute(AppConstant.RECORD_TYPE, recordtype);
        }
        return url;
    }

    /**
     * Return check attendance View
     * @param attendanceDTO
     * @return 
     */
    @RequestMapping(value = "checkAttendance", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView checkAttendaceView(@ModelAttribute("attendanceDTO") AttendanceDTO attendanceDTO) {

        String recordtype = "i";
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_CHECK_ATTENDENCE_VIEW);
        model.addObject(AppConstant.RECORD_TYPE, recordtype);
        return model;
    }

    /**
     * Check Attendance
     * @param attendanceDTO
     * @param user_code
     * @param year
     * @param day
     * @param month
     * @return 
     */
    @RequestMapping(value = "checkEmpAttendances", method = {RequestMethod.GET})
    @ResponseBody
    public String checkUserAttendance(@ModelAttribute AttendanceDTO attendanceDTO,HttpServletRequest request,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<AttendanceDTO> empAttendanceList = null;

        try {
            String User = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
            attendanceDTO.setUser_code(User);
            empAttendanceList = employeeService.getAttendance(attendanceDTO);

            for (AttendanceDTO attendance : empAttendanceList) {
                list.add(new String[]{Integer.toString(attendance.getYear()),
                    Integer.toString(attendance.getMonth()),
                    Integer.toString(attendance.getDay()), attendance.getOn_time(), attendance.getOff_time(),
                    Integer.toString(attendance.getYear())});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

}
