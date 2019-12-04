/*
 * LeaveController
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.LeaveBalanceDTO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.dto.custom.LeaveTypeDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.LeaveBalanceService;
import com.misyn.hrsystem.service.custom.LeaveService;
import com.misyn.hrsystem.service.custom.LeaveTypeService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_ADD_EMPLOYEE_CHECK_LEAVE_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_APPROVED_LEAVE_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_CANCEL_LEAVE_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_EMPLOYEE_LEAVE_VIEW;
import com.misyn.hrsystem.util.MailMail;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka Leave Controller contains all methods related to Leave
 * Function
 */
@Controller
@RequestMapping("/leave/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class LeaveController {

    private static final Logger LOGGER = Logger.getLogger(LeaveController.class);

    private static final String USER_DTO = "userDTO";

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

    MailMail mm = (MailMail) context.getBean("mailMail");

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @Autowired
    private Environment environment;

    /**
     *
     * @param leaveDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "Appleave", method = RequestMethod.GET)
    public ModelAndView getLeaveView(@ModelAttribute LeaveDTO leaveDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_LEAVE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        modelAndView.addObject(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());

        activityService.doLog(userCode, ip, userCode + " Access Apply Leave view");
        return modelAndView;
    }

    /**
     *
     * @param leaveTypeDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "leaveType", method = RequestMethod.GET)
    public ModelAndView getLeaveTypeView(@ModelAttribute LeaveTypeDTO leaveTypeDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_LEAVE_TYPE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Apply Leave view");

        return modelAndView;

    }

    /**
     *
     * @param leaveBalenceDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "leaveBalance", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getLeaveBalanceView(@ModelAttribute LeaveBalanceDTO leaveBalenceDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_LEAVE_BALENCE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        modelAndView.addObject(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());

        activityService.doLog(userCode, ip, userCode + " Access Apply Balence view");
        return modelAndView;
    }

    /**
     *
     * @param leaveDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "cancelLeaveView", method = {RequestMethod.GET})
    public ModelAndView getCancelLeaveView(@ModelAttribute LeaveDTO leaveDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_CANCEL_LEAVE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());

        activityService.doLog(userCode, ip, userCode + " Access Check/Cancel Authorized view");
        return modelAndView;
    }

    /**
     *
     * @param leaveDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "checkLeave", method = {RequestMethod.GET})
    public ModelAndView getCheckLeaveView(@ModelAttribute LeaveDTO leaveDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ADD_EMPLOYEE_CHECK_LEAVE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Check Leave view");
        return modelAndView;
    }

    /**
     *
     * @param leaveBalenceDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "addLeaveBalanceView", method = RequestMethod.POST)
    public ModelAndView getAddLeaveBalanceView(@ModelAttribute LeaveBalanceDTO leaveBalenceDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_ADD_EMPLOYEE_LEAVE_BALENCE_VIEW);
        try {
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
            modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     *
     * @param leaveTypeDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "approvedLeave", method = RequestMethod.GET)
    public ModelAndView getApprovedLeaveView(@ModelAttribute LeaveTypeDTO leaveTypeDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_EMPLOYEE_APPROVED_LEAVE_VIEW);
        modelAndView.addObject(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Apply Leave view");

        return modelAndView;

    }

    /**
     *
     * @param leaveBalenceDTO
     * @param lID
     * @return
     */
    @RequestMapping(value = "editLeaveBalance/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView editStatus(LeaveBalanceDTO leaveBalenceDTO, @PathVariable("id") int lID) {
        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_ADD_EMPLOYEE_LEAVE_BALENCE_VIEW);

        List<LeaveBalanceDTO> LeaveBalList = null;
        LeaveBalanceDTO leavBal = new LeaveBalanceDTO();
        try {
            LeaveBalList = leaveBalanceService.getLeaveBalDetail(leaveBalenceDTO, lID);
            leavBal = LeaveBalList.get(0);

            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.LEAVE_BAL_DETAIL, LeaveBalList);
            modelAndView.addObject(AppConstant.LEAVE_BAL, leavBal);
            modelAndView.addObject(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
            modelAndView.addObject(AppConstant.EMPLOYEE_LIST, initializeUserList());

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     *
     * @param request
     * @param leaveTypeDTO
     * @return
     */
    @RequestMapping(value = "leaveTypeList", method = RequestMethod.GET)
    @ResponseBody
    public String viewLeaveList(HttpServletRequest request, @ModelAttribute LeaveTypeDTO leaveTypeDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveTypeDTO> leaveTypeList = null;

        try {
            leaveTypeList = leaveTypeService.getLeaveTypeList(leaveTypeDTO);

            for (LeaveTypeDTO leaveType : leaveTypeList) {

                list.add(new String[]{Integer.toString(leaveType.getLtype_id()), leaveType.getLtype_name(), Integer.toString(leaveType.getDays())});

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
     * @param request
     * @param leaveBalenceDTO
     * @return
     */
    @RequestMapping(value = "leaveBalenceList", method = RequestMethod.GET)
    @ResponseBody
    public String viewLeaveBalList(HttpServletRequest request, @ModelAttribute LeaveBalanceDTO leaveBalenceDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveBalanceDTO> leaveBalList = null;

        try {
            leaveBalList = leaveBalanceService.getLeaveBalanceList(leaveBalenceDTO);

            for (LeaveBalanceDTO leaveBalence : leaveBalList) {

                list.add(new String[]{leaveBalence.getUser_code(), leaveBalence.getLtype_name(), Double.toString(leaveBalence.getDays()), Integer.toString(leaveBalence.getLeave_balance_id())});

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
     * @param request
     * @param leaveDTO
     * @return
     */
    @RequestMapping(value = "getLeaveList", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaveList(HttpServletRequest request, @ModelAttribute LeaveDTO leaveDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveDTO> leaveList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        String button = "<div class=\"form-btn table-btn-grp\">\n"
                + "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"cancelLeave()\">Cancel</button>\n"
                + "</div>";

        try {
            leaveDTO.setUser_code(userCode);

            leaveList = leaveService.getLeaveList(leaveDTO);

            for (LeaveDTO leave : leaveList) {

                list.add(new String[]{leave.getStart_date(), leave.getEnd_date(), Double.toString(leave.getNo_of_days()),
                    leave.getLtype_name(), (leave.getAuth_state().equals("P") ? "Pendding" : "Approved"),
                    (leave.getAuth_state().equals("P") ? button : ""),
                    "<input type=\"hidden\" id=\"leave_id\" name=\"leave_id\" value=" + Integer.toString(leave.getLeave_id()) + ">"
                    + "<input type=\"hidden\" id=\"no_of_days\" name=\"no_of_days\" value=" + Double.toString(leave.getNo_of_days()) + ">"
                    + "<input type=\"hidden\" id=\"ltype_id\" name=\"ltype_id\" value=" + Integer.toString(leave.getLtype_id()) + ">"});

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
     * @param leaveTypeDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "saveLeaveType", method = RequestMethod.POST)
    public String insertLType(@ModelAttribute LeaveTypeDTO leaveTypeDTO, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveTypeDTO leaveType = null;
        String recordtype = "i";

        try {
            if (leaveTypeDTO != null) {

                leaveType = leaveTypeService.insert(leaveTypeDTO);

                if (leaveType != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
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
     * @param leaveDTO
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "saveLeave", method = RequestMethod.POST)
    public String insertLeave(@ModelAttribute LeaveDTO leaveDTO, ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leave = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String userEmail = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_email();
        int lBal = 0;

        try {
            if (leaveDTO != null) {

                leaveDTO.setUser_code(userCode);

                lBal = leaveService.checkLeaveBalance(leaveDTO);

                if (lBal != 0 && lBal >= leaveDTO.getNo_of_days()) {

                    //---set Leave Balance to Leave DTO
                    leaveDTO.setLeave_balance(lBal - leaveDTO.getNo_of_days());
                    if (leaveDTO.getLtype_id() != 2) {
                        String sD = leaveDTO.getStart_date();
                        String[] Sparts = sD.split(",");
                        String s1 = Sparts[0];
                        String stDate = Sparts[1];
                        leaveDTO.setStart_date(stDate);

                        String eD = leaveDTO.getEnd_date();
                        String[] Eparts = sD.split(",");
                        String e1 = Eparts[0];
                        String etDate = Eparts[1];
                        leaveDTO.setEnd_date(etDate);
                    }
                    leave = leaveService.insert(leaveDTO);

                    if (leave != null) {
                        //--update Leave Balance
                        leave = leaveService.updateLeaveBalance(leaveDTO);

                        successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                        url = AppURL.PATH_EMPLOYEE_LEAVE_VIEW;

                        //--send email to apply user
                        String emailPath = environment.getProperty("MailAddress");
                        mm.sendMail(emailPath,
                                userEmail,
                                "MI Synergy HR",
                                "Dear " + userCode + "\n\n"
                                + "The leave applied requires authorization.\n"
                                + "Your Leave Balance of the category you applied: " + leaveDTO.getLeave_balance());

                    } else {
                        errorMessage = AppConstant.ERROR_MESSAGE;
                        url = AppURL.PATH_EMPLOYEE_LEAVE_VIEW;
                    }
                } else {
                    errorMessage = "Unable to save your Leave Balance is " + lBal;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_LEAVE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
        }
        return url;
    }

    /**
     *
     * @param leaveType
     * @param model
     * @return
     */
    @RequestMapping(value = "editLeaveType", method = RequestMethod.POST)
    public String editLeaveType(@ModelAttribute LeaveTypeDTO leaveType, ModelMap model) {

        LeaveTypeDTO leaveTypeDTO = null;
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        try {
            if (leaveType != null) {
                leaveTypeDTO = leaveTypeService.update(leaveType);
                if (leaveTypeDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_LEAVE_TYPE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param leaveBalanceDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "saveLeaveBalance", method = RequestMethod.POST)
    public String insertLBalance(@ModelAttribute LeaveBalanceDTO leaveBalanceDTO, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveBalanceDTO leaveBalance = null;
        String recordtype = "i";
        int chekRes = 0;

        try {

            if (leaveBalanceDTO != null) {

                chekRes = leaveBalanceService.checkExistLBal(leaveBalanceDTO.getUser_code(), leaveBalanceDTO.getLtype_id());

                if (chekRes == 0) {
                    leaveBalance = leaveBalanceService.insert(leaveBalanceDTO);

                    if (leaveBalance != null) {
                        successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                        url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
                    } else {
                        errorMessage = AppConstant.ERROR_MESSAGE;
                        url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
                    }

                } else {
                    errorMessage = "Record is exist";
                    url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
            model.addAttribute(AppConstant.EMPLOYEE_LIST, initializeUserList());
        }
        return url;
    }

    /**
     *
     * @param leaveBalance
     * @param model
     * @return
     */
    @RequestMapping(value = "editLeaveBalance", method = RequestMethod.POST)
    public String editLeaveBalance(@ModelAttribute LeaveBalanceDTO leaveBalance, ModelMap model) {

        LeaveBalanceDTO leaveBalanceDTO = null;
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        try {
            if (leaveBalance != null) {
                leaveBalanceDTO = leaveBalanceService.update(leaveBalance);

                if (leaveBalanceDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_LEAVE_BALENCE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.LEAVE_TYPE_LIST, initializeLeaveTypeList());
            model.addAttribute(AppConstant.EMPLOYEE_LIST, initializeUserList());
        }
        return url;
    }

    /**
     *
     * @param leave
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "cancelLeave", method = RequestMethod.POST)
    public String cancelLeave(@ModelAttribute LeaveDTO leave, ModelMap model, HttpServletRequest request) {

        LeaveDTO leaveDTO = null;
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        int lBal = 0;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leave != null) {
                leave.setUser_code(userCode);
                lBal = leaveService.checkLeaveBalance(leave);

                //---set Leave Balance to Leave DTO
                leave.setLeave_balance(lBal + leave.getNo_of_days());

                leaveDTO = leaveService.deleteLeave(leave);

                if (leaveDTO != null) {

                    //--update Leave Balance
                    leaveDTO = leaveService.updateLeaveBalance(leave);

                    successMessage = AppConstant.DELETE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_ADD_EMPLOYEE_CHECK_LEAVE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_ADD_EMPLOYEE_CHECK_LEAVE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_ADD_EMPLOYEE_CHECK_LEAVE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     * Leave Type List generate as option field
     *
     * @return
     */
    public List<LeaveTypeDTO> initializeLeaveTypeList() {

        List<LeaveTypeDTO> leaveType = new ArrayList<>();
        LeaveTypeDTO leaveTypeDTO = new LeaveTypeDTO();
        try {
            leaveType = leaveTypeService.getLeaveTypeList(leaveTypeDTO);

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        LeaveTypeDTO leaveTypeList = new LeaveTypeDTO();
        leaveTypeList.setLtype_id(0);
        leaveTypeList.setLtype_name("Please Select One");
        leaveType.add(0, leaveTypeList);

        return leaveType;
    }

    /**
     * User List generate as option field
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

    /**
     *
     * @param request
     * @param leaveDTO
     * @return
     */
    @RequestMapping(value = "getAuthLeaveList", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthLeaveList(HttpServletRequest request, @ModelAttribute LeaveDTO leaveDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveDTO> leaveList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {

            leaveList = leaveService.getAuthLeaveList(leaveDTO);

            for (LeaveDTO leave : leaveList) {

                list.add(new String[]{leave.getStart_date(), leave.getEnd_date(), Double.toString(leave.getNo_of_days()),
                    leave.getLtype_name(), (leave.getAuth_state().equals("A") ? "Approved" : ""), leave.getUser_code(),
                    (leave.getInput_state().equals("I")
                    ? "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                    + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"deleteLeave(" + leave.getLeave_id() + ")\">Cancel</button>"
                    : "")});

            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

    /**
     * Delete leave
     *
     * @param leaveDTO
     * @param leave_id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteLeave/{leave_id}", method = RequestMethod.POST)
    public String deleteLeave(@ModelAttribute LeaveDTO leaveDTO, @PathVariable("leave_id") int leave_id,
            ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leave = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leaveDTO != null) {

                leave = leaveService.delete(leaveDTO);

                if (leave != null) {
                    successMessage = AppConstant.SUBMIT_AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_CANCEL_LEAVE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_CANCEL_LEAVE_VIEW;
                }

            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMPLOYEE_CANCEL_LEAVE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.EMPLOYEE_LIST, initializeUserList());
        }
        return url;
    }

    /**
     *
     * @param request
     * @param leaveDTO
     * @return
     */
    @RequestMapping(value = "getApproveLeaveList", method = RequestMethod.GET)
    @ResponseBody
    public String getApproveLeaveList(HttpServletRequest request, @ModelAttribute LeaveDTO leaveDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveDTO> leaveList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        try {
            leaveDTO.setUser_code(userCode);
            leaveList = leaveService.getApprovedLeaveList(leaveDTO);

            for (LeaveDTO leave : leaveList) {

                list.add(new String[]{leave.getStart_date(), leave.getEnd_date(), Double.toString(leave.getNo_of_days()),
                    leave.getLtype_name(), (leave.getAuth_state().equals("A") ? "Approved" : "")});

            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

}
