/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.MenuItemDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserRoleDTO;
import com.misyn.hrsystem.dto.custom.UserTypeDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserRoleService;
import com.misyn.hrsystem.service.custom.UserTypeService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka User Role Controller contains all methods related to User
 * Role Function
 */
@Controller
@RequestMapping(value = "/userRole/")
@SessionAttributes({"UserRoleDTO", "menuItemList", "userDTO"})
public class UserRoleController {

    private static final Logger LOGGER = Logger.getLogger(UserRoleController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private UserRoleService UserRoleService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private ActivityLogService activityService;

    /**
     *
     * @param request
     * @param userType
     * @param userRoleDTO
     * @return
     */
    @RequestMapping(value = "userRoleList", method = RequestMethod.GET)
    @ResponseBody
    public String viewUserRoleList(HttpServletRequest request, @RequestParam("typeId") String userType, @ModelAttribute UserRoleDTO userRoleDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<UserRoleDTO> userRoleList = null;

        try {
            userRoleList = UserRoleService.getUserRoleList(userType, userRoleDTO);

            for (UserRoleDTO userRole : userRoleList) {
                list.add(new String[]{userRole.getUser_role_id(), userRole.getRole_name(), userRole.getUser_type_id()});
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
     * @param userRole
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "saveUserRole", method = RequestMethod.POST)
    public String insertUserRole(@ModelAttribute UserRoleDTO userRole, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserRoleDTO userRoleDTO = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();

        try {
            if (userRole.getUserTypeList() != null && userRole.getMenuItem() != null) {
                userRoleDTO = UserRoleService.insert(userRole);
                if (userRoleDTO != null) {
                    activityService.doLog(userCode, ip, userCode + "Saved User Role Succesfully " + userRole.getUser_role_id());
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_ROLE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_ROLE_VIEW;
                }
            } else {
                if (userRole.getUser_type_id() == null) {
                    errorMessage = "Please Select User Type";
                    url = AppURL.PATH_USER_ROLE_VIEW;
                }
                if (userRole.getMenuItem() == null) {
                    errorMessage = "Please Select Menu Option(s)";
                    url = AppURL.PATH_USER_ROLE_VIEW;
                }

            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            modelMap.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            modelMap.addAttribute(AppConstant.USER_TYPE_LIST, initializeUserTypeList());
        }
        return url;
    }

    /**
     * 
     * @param userRoleID
     * @param userRoleDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "editUserRole/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView userRoleEdit(@PathVariable("roleId") String userRoleID, UserRoleDTO userRoleDTO, HttpServletRequest request) {

        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_ROLE_ADD_VIEW);
        List<UserRoleDTO> userRoleList = null;
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        //--Objects
        UserRoleDTO userRole = null;
        MenuItemDTO menuOptionDTO = null;
        List<MenuItemDTO> menuItemList = new ArrayList<MenuItemDTO>();

        try {
            userRoleList = UserRoleService.getUserRoleDetails(userRoleDTO, userRoleID);

            // set data to two objects
            Iterator<UserRoleDTO> urIterat = userRoleList.iterator();
            while (urIterat.hasNext()) {
                userRole = urIterat.next();

                //add to menu item object then set it to list           
                menuOptionDTO = new MenuItemDTO();
                menuOptionDTO.setMenuId(userRole.getMenuOptionID());

                menuItemList.add(menuOptionDTO);

            }

            modelAndView.addObject(AppConstant.USER_ROLE_DETAIL_LIST, userRoleList);
            modelAndView.addObject(AppConstant.USER_ROLE_DETAIL, userRole);
            modelAndView.addObject(AppConstant.MENU_ITEM, menuItemList);
            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
            modelAndView.addObject("userMenuItemList", menuItemService.getUserSubMenuItemList(userRoleID));
            modelAndView.addObject("allMenuItemList", menuItemService.getAllMenuItemList());
            modelAndView.addObject(AppConstant.USER_TYPE_LIST, initializeUserTypeList());
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return modelAndView;
    }

    /**
     * 
     * @param userRole
     * @param modelMap
     * @return 
     */
    @RequestMapping(value = "updateUserRole", method = RequestMethod.POST)
    public String updateUserRole(@ModelAttribute UserRoleDTO userRole, ModelMap modelMap) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserRoleDTO userRoleDTO = null;
        String recordtype = "i";

        try {
            if (userRole != null) {
                userRoleDTO = UserRoleService.update(userRole);
                if (userRoleDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_ROLE_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_ROLE_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_ROLE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            modelMap.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            modelMap.addAttribute(AppConstant.USER_TYPE_LIST, initializeUserTypeList());
        }
        return url;
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

}
