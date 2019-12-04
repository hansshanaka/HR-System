/*
 * AuthorizeController
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.AttendanceDTO;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.LeaveDTO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.FoodService;
import com.misyn.hrsystem.service.custom.LeaveService;
import com.misyn.hrsystem.service.custom.MedicalService;
import com.misyn.hrsystem.service.custom.TravelService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka Authorize Controller contains all methods related to
 * Authorize Function
 */
@Controller
@RequestMapping("/authorize/")
@SessionAttributes({"userDTO"})
@PropertySource(value = {"classpath:system.properties"})
public class AuthorizeController {

    private static final Logger LOGGER = Logger.getLogger(AuthorizeController.class);

    private static final String USER_DTO = "userDTO";

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

    MailMail mm = (MailMail) context.getBean("mailMail");

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private MedicalService medicalService;

    @Autowired
    private Environment environment;

    /**
     * Return authorize attendance view
     *
     * @return
     */
    @RequestMapping(value = "authorizeAttendance", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView authAttendenceView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW);
        return model;
    }

    /**
     * Return authorize authorize Leave view
     *
     * @return
     */
    @RequestMapping(value = "authorizeLeave", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authLeaveView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_LEAVE_AUTHORIZE_VIEW);
        return model;
    }

    /**
     * Return authorize Cancel Leave view
     *
     * @return
     */
    @RequestMapping(value = "authorizeCancelLeave", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authCancelLeaveView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW);
        return model;
    }

    /**
     * Return pending attendance List
     *
     * @param request
     * @param attenDTO
     * @return
     */
    @RequestMapping(value = "pendingAttenList", method = RequestMethod.GET)
    @ResponseBody
    public String viewPenAttendList(HttpServletRequest request, @ModelAttribute AttendanceDTO attenDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<AttendanceDTO> attendenceList = null;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            attendenceList = employeeService.getPendingAttendance(attenDTO);

            for (AttendanceDTO attendance : attendenceList) {
                list.add(new String[]{Integer.toString(attendance.getYear()),
                    Integer.toString(attendance.getMonth()),
                    Integer.toString(attendance.getDay()),
                    attendance.getOn_time(),
                    attendance.getOff_time(),
                    attendance.getUser_code()});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

    /**
     * Auth attendance according to the year and month
     *
     * @param attenDTO
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "authorizeAtten", method = {RequestMethod.GET, RequestMethod.POST})
    public String authAttendence(@ModelAttribute AttendanceDTO attenDTO, ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        AttendanceDTO mast_atten = null;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        List<AttendanceDTO> attnList = new ArrayList<>();

        try {
            //---check attendance in tempory table befor authorize
            attnList = employeeService.getPendingAttendance(attenDTO);

            if (!attnList.isEmpty()) {
                //--Authorize attendance
                mast_atten = employeeService.authorizeAttendance(employeeService.getPendingAttendance(attenDTO).get(0), userCode);
                if (mast_atten != null) {
                    successMessage = "Authorized Successfully";
                    url = AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                url = AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW;
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
     * @param attenDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "rejectAtten", method = {RequestMethod.GET, RequestMethod.POST})
    public String rejectAttendence(@ModelAttribute AttendanceDTO attenDTO, ModelMap model) {
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        int result = 0;
        try {
            result = employeeService.rejectAttendence();
            if (result != 0) {
                successMessage = "Rejected Successfully";
                url = AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW;
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                url = AppURL.PATH_EMPLOYEE_AUTHORIZE_VIEW;
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
     * @param request
     * @param leaveDTO
     * @return
     */
    @RequestMapping(value = "pendingLeaveList", method = RequestMethod.GET)
    @ResponseBody
    public String penLeaveList(HttpServletRequest request, @ModelAttribute LeaveDTO leaveDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveDTO> leaveList = null;

        String button = "<div class=\"form-btn table-btn-grp\">\n"
                + "<input type=\"checkbox\" class=\"scheckboxcb sub\" name=\"\" value=\"\">"
                + "</div>";

        try {
            leaveList = leaveService.getPendingLeave(leaveDTO);

            for (LeaveDTO leave : leaveList) {
                list.add(new String[]{
                    "<input type=\"checkbox\" class=\"scheckboxleave sub\" id=" + leave.getLeave_id() + " name=\"leaveID\" value=" + leave.getLeave_id() + ">", leave.getStart_date(),
                    leave.getEnd_date(),
                    Double.toString(leave.getNo_of_days()),
                    leave.getLtype_name(),
                    leave.getUser_code()});
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
     * @param leave
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "authLeaveSave", method = RequestMethod.POST)
    public String authLeave(@ModelAttribute LeaveDTO leave, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leaveDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leave != null) {

                leave.setAuth_id(authUser);
                leaveDTO = leaveService.authorizeLeave(leave);

                if (leaveDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;

                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                    url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param leave
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectLeave", method = RequestMethod.POST)
    public String rejectLeave(@ModelAttribute LeaveDTO leave, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leaveDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leave != null) {

                leave.setAuth_id(authUser);
                leaveDTO = leaveService.rejectLeave(leave);

                if (leaveDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                    url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                url = AppURL.PATH_LEAVE_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param request
     * @param leaveDTO
     * @return
     */
    @RequestMapping(value = "pendingCancelLeaveList", method = RequestMethod.GET)
    @ResponseBody
    public String penCancelLeaveList(HttpServletRequest request, @ModelAttribute LeaveDTO leaveDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<LeaveDTO> leaveList = null;

        try {
            leaveList = leaveService.getPendingCancelLeave(leaveDTO);

            for (LeaveDTO leave : leaveList) {
                list.add(new String[]{
                    "<input type=\"checkbox\" class=\"scheckboxleave sub\" id=" + leave.getLeave_id() + " name=\"leaveID\" value=" + leave.getLeave_id() + ">", leave.getStart_date(),
                    leave.getEnd_date(),
                    Double.toString(leave.getNo_of_days()),
                    leave.getLtype_name(),
                    leave.getUser_code()});
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
     * @param leave
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "authCancelLeave", method = RequestMethod.POST)
    public String authCancelLeave(@ModelAttribute LeaveDTO leave, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leaveDTO = null;
        String recordtype = "i";

        String auth_user = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leave != null) {

                leave.setAuth_id(auth_user);

                leaveDTO = leaveService.authorizeCancelLeave(leave);

                if (leaveDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param leave
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectCancelLeave", method = RequestMethod.POST)
    public String rejCancelLeave(@ModelAttribute LeaveDTO leave, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        LeaveDTO leaveDTO = null;
        String recordtype = "i";

        String auth_user = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (leave != null) {

                leave.setAuth_id(auth_user);

                leaveDTO = leaveService.rejectCancelLeave(leave);

                if (leaveDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_CANCEL_LEAVE_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     * authorized food view
     *
     * @return
     */
    @RequestMapping(value = "authorizeFood", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authFoodView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_FOOD_AUTHORIZE_VIEW);
        return model;
    }

    /**
     * get pending food List
     *
     * @param request
     * @param foodDTO
     * @return
     */
    @RequestMapping(value = "pendingFoodList", method = RequestMethod.GET)
    @ResponseBody
    public String penFoodList(HttpServletRequest request, @ModelAttribute FoodDTO foodDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<FoodDTO> foodList = null;

        try {
            foodList = foodService.getPenAllFoodList(foodDTO);

            for (FoodDTO food : foodList) {
                list.add(new String[]{
                    "<input type=\"checkbox\" class=\"scheckboxleave sub\" id=" + food.getFood_id() + " name=\"foodID\" value=" + food.getFood_id() + ">", food.getProject_name(),
                    Double.toString(food.getAmount()),
                    "<img src=" + food.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    food.getUser_code()});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

    /**
     * auth food Save
     *
     * @param food
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "authFoodSave", method = RequestMethod.POST)
    public String authFood(@ModelAttribute FoodDTO food, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        FoodDTO foodDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (food != null) {

                food.setAuth_user(authUser);
                foodDTO = foodService.authorizeFood(food);

                if (foodDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                    url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     * Reject Food
     *
     * @param food
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectFood", method = RequestMethod.POST)
    public String rejectFood(@ModelAttribute FoodDTO food, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        FoodDTO foodDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (food != null) {

                food.setAuth_user(authUser);
                foodDTO = foodService.rejectFood(food);

                if (foodDTO != null) {
                    successMessage = AppConstant.REJECT_SUCCESS_MESSAGE;
                    url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                    url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                url = AppURL.PATH_FOOD_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "authorizeTravel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authTravelView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_TRAVEL_AUTHORIZE_VIEW);
        return model;
    }

    /**
     *
     * @param request
     * @param taxiDTO
     * @return
     */
    @RequestMapping(value = "pendingTravelList", method = RequestMethod.GET)
    @ResponseBody
    public String penTravelList(HttpServletRequest request, @ModelAttribute TaxiDTO taxiDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<TaxiDTO> taxiList = null;

        try {
            taxiList = travelService.getPenAllTravelList(taxiDTO);

            for (TaxiDTO taxi : taxiList) {
                list.add(new String[]{
                    "<input type=\"checkbox\" class=\"scheckboxleave sub\" id=" + taxi.getTaxi_id() + " name=\"taxiID\" value=" + taxi.getTaxi_id() + ">", taxi.getProject_name(), taxi.getStart(), taxi.getEnd(), Double.toString(taxi.getDistance()),
                    Double.toString(taxi.getAmount()),
                    "<img src=" + taxi.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    taxi.getUser_code()});
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
     * @param taxi
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "authTravelSave", method = RequestMethod.POST)
    public String authTaxi(@ModelAttribute TaxiDTO taxi, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        TaxiDTO taxiDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (taxi != null) {

                taxi.setAuth_user(authUser);
                taxiDTO = travelService.authorizeTravel(taxi);

                if (taxiDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                    url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param taxi
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectTravel", method = RequestMethod.POST)
    public String rejectTaxi(@ModelAttribute TaxiDTO taxi, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        TaxiDTO taxiDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (taxi != null) {

                taxi.setAuth_user(authUser);
                taxiDTO = travelService.rejectTaxi(taxi);

                if (taxiDTO != null) {
                    successMessage = AppConstant.REJECT_SUCCESS_MESSAGE;
                    url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                    url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                url = AppURL.PATH_TRAVEL_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "authorizeMedical", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authMedicalView() {
        ModelAndView model = new ModelAndView(AppURL.PATH_MEDICAL_AUTHORIZE_VIEW);
        return model;
    }

    @RequestMapping(value = "pendingMedicalList", method = RequestMethod.GET)
    @ResponseBody
    public String penMedicalList(HttpServletRequest request, @ModelAttribute MedicalDTO medicalDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<MedicalDTO> medicalList = null;

        try {
            medicalList = medicalService.getPenAllMedicalList(medicalDTO);

            for (MedicalDTO medical : medicalList) {
                list.add(new String[]{
                    "<input type=\"checkbox\" class=\"scheckboxleave sub\" id=" + medical.getMedical_id() + " name=\"medicalID\" value=" + medical.getMedical_id() + ">", medical.getDescription(), Double.toString(medical.getAmount()),
                    "<img src=" + medical.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    medical.getUser_code()});
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
     * @param medical
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "authMedicalSave", method = RequestMethod.POST)
    public String authMedical(@ModelAttribute MedicalDTO medical, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        MedicalDTO medicalDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (medical != null) {

                medical.setAuth_user(authUser);
                medicalDTO = medicalService.authorizeMedical(medical);

                if (medicalDTO != null) {
                    successMessage = AppConstant.AUTH_SUCCESS_MESSAGE;
                    url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                    url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_AUTHORIZE;
                url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     *
     * @param medical
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectMedical", method = RequestMethod.POST)
    public String rejectMedical(@ModelAttribute MedicalDTO medical, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        MedicalDTO medicalDTO = null;
        String recordtype = "i";
        String authUser = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (medical != null) {

                medical.setAuth_user(authUser);
                medicalDTO = medicalService.rejectMedical(medical);

                if (medicalDTO != null) {
                    successMessage = AppConstant.REJECT_SUCCESS_MESSAGE;
                    url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                    url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
                }
            } else {
                errorMessage = AppConstant.ERROR_MESSAGE_NO_DATA_REJCT;
                url = AppURL.PATH_MEDICAL_AUTHORIZE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
        }
        return url;
    }
}
