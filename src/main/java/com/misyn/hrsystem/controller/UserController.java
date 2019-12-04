/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserCategoryService;
import com.misyn.hrsystem.service.custom.UserDepartmentService;
import com.misyn.hrsystem.service.custom.UserStatusService;
import com.misyn.hrsystem.service.custom.UserTypeService;
import com.misyn.hrsystem.util.AppConstant;
import static com.misyn.hrsystem.util.AppConstant.USER_TYPE_LIST;
import com.misyn.hrsystem.util.AppURL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Shanaka
 * User Controller contains all methods related to User Function
 */
@Controller
@RequestMapping("/user/")
@SessionAttributes("userDTO")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private UserDepartmentService userDepartmentService;

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private EmployeeService employeeService;

    private static final String ATTR_NAME_MODULE_LIST = "moduleList";

    /**
     * 
     * @param userDTO
     * @return 
     */
    @RequestMapping(value = "userType", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewListForm(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView model = new ModelAndView(AppURL.PATH_USER_TYPE_VIEW);
        return model;
    }

    /**
     * 
     * @param request
     * @param userTypeDTO
     * @return 
     */
    @RequestMapping(value = "userTypeList", method = RequestMethod.GET)
    @ResponseBody
    public String viewUserList(HttpServletRequest request, @ModelAttribute UserTypeDTO userTypeDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<UserTypeDTO> userTypeList = null;

        try {
            userTypeList = userTypeService.getUserTypeList(userTypeDTO);

            for (UserTypeDTO userType : userTypeList) {
                if (!userType.getUser_type_id().equals("Admin")) {
                    list.add(new String[]{userType.getUser_type_id(), userType.getType_name()});
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
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "addUserTypeView", method = RequestMethod.POST)
    public String viewNewSalutation(ModelMap model) {
        return AppURL.PATH_USER_TYPE_ADD_VIEW;
    }

    /**
     * 
     * @param userType
     * @param modelMap
     * @return 
     */
    @RequestMapping(value = "saveUserType", method = RequestMethod.POST)
    public String insertUserType(@ModelAttribute UserTypeDTO userType, ModelMap modelMap) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserTypeDTO userTypeDTO = null;

        try {
            if (userType != null) {
                userTypeDTO = userTypeService.insert(userType);
                if (userTypeDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                }

            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_TYPE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, errorMessage);
        }

        return url;
    }

    /**
     * 
     * @param userType
     * @param model
     * @return 
     */
    @RequestMapping(value = "editUserType", method = RequestMethod.POST)
    public String editUserType(@ModelAttribute UserTypeDTO userType, ModelMap model) {

        UserTypeDTO typeDTO = null;
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        try {
            if (userType != null) {
                typeDTO = userTypeService.update(userType);
                if (typeDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_TYPE_VIEW;
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
     * @param userType
     * @param model
     * @return 
     */
    @RequestMapping(value = "deleteUserType", method = RequestMethod.POST)
    public String deleteUserType(@ModelAttribute UserTypeDTO userType, ModelMap model) {

        UserTypeDTO typeDTO = null;
        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        try {
            if (userType != null) {
                typeDTO = userTypeService.delete(userType);
                if (typeDTO != null) {
                    successMessage = AppConstant.DELETE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_TYPE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_TYPE_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, errorMessage);
        }
        return url;
    }

    /**
     * 
     * @param userDTO
     * @return 
     */
    @RequestMapping(value = "userRole", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getUserRoleView(@ModelAttribute("userDTO") UserDTO userDTO) {

        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_ROLE_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        modelAndView.addObject(USER_TYPE_LIST, initializeUserTypeList());
        return modelAndView;
    }

    /**
     * 
     * @param userDTO
     * @return 
     */
    @RequestMapping(value = "resetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getUserRPasswordView(@ModelAttribute("userDTO") UserDTO userDTO) {

        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_RESET_PASSWORD_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        return modelAndView;
    }

    /**
     * 
     * @param employeeDTO
     * @return 
     */
    @RequestMapping(value = "getAllEmploys", method = RequestMethod.GET)
    @ResponseBody
    public String getEmploys(@ModelAttribute EmployeeDTO employeeDTO) {

        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<EmployeeDTO> empList = null;

        try {
            empList = employeeService.getAllEmployees(employeeDTO);

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
     * 
     * @param userDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "addUserRoleView", method = RequestMethod.POST)
    public ModelAndView addUserRoleView(@ModelAttribute("userDTO") UserDTO userDTO, HttpServletRequest request) {
        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_ROLE_ADD_VIEW);
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);
        try {

            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
            modelAndView.addObject("allMenuItemList", menuItemService.getAllMenuItemList());
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_TYPE_LIST, initializeUserTypeList());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return modelAndView;
    }

    /**
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

    //---------User Department View
    @RequestMapping(value = "userDepartment", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userDepartmentView() {
        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_DEPARTMENT_VIEW);
        try {
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());
            modelAndView.addObject("menuItemList");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
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

    //---------User Category View
    @RequestMapping(value = "userCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userCategoryView() {
        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_CATEGORY_VIEW);
        try {
            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
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

    //---------User Satus View
    @RequestMapping(value = "userStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userStatusView() {
        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_STATUS_VIEW);
        try {
            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject(AppConstant.USER_STATUS_LIST, initializeStatusList());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
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

}
