/*
 * User Status Controller
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserStatusDTO;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserStatusService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import java.util.ArrayList;
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
 * @author Shanaka
 * User Status Controller contains all methods related to User Status Function
 */
@Controller
@RequestMapping("/userStatus/")
@SessionAttributes({"UserStatusDTO", "menuItemList"})
public class UserStatusController {

    private static final Logger LOGGER = Logger.getLogger(UserStatusController.class);
    
    private static final String USER_DTO = "userDTO";

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserStatusService userStatusService;

    /**
     * 
     * @param userStatusDTO
     * @return 
     */
    @RequestMapping(value = "addUserStatusView", method = RequestMethod.POST)
    public ModelAndView addUserStatus(@ModelAttribute("userStatusDTO") UserStatusDTO userStatusDTO) {

        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_STATUS_ADD_VIEW);
        try {
            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     * 
     * @param userStatus
     * @param model
     * @return 
     */
    @RequestMapping(value = "saveUserStatus", method = RequestMethod.POST)
    public String insertUserStatus(@ModelAttribute UserStatusDTO userStatus, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserStatusDTO userStatusDTO = null;
        String recordtype = "i";
        try {
            if (userStatus != null) {
                userStatusDTO = userStatusService.insert(userStatus);
                if (userStatusDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_STATUS_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_STATUS_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_STATUS_VIEW;
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
     * @param statCode
     * @param userStatDTO
     * @return 
     */
    @RequestMapping(value = "getStatus", method = RequestMethod.GET)
    @ResponseBody
    public String getUserDep(@RequestParam("statCode") int statCode, @ModelAttribute UserStatusDTO userStatDTO) {

        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<UserStatusDTO> userStatList = null;
        try {
            userStatList = userStatusService.getStatusDetail(userStatDTO, statCode);

            for (UserStatusDTO userStatus : userStatList) {
                list.add(new String[]{Integer.toString(userStatus.getStatus_id()), userStatus.getEmp_status()});
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
     * @param userStatDTO
     * @param statCode
     * @return 
     */
    @RequestMapping(value = "editStatus/{statusID}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView editStatus(UserStatusDTO userStatDTO, @PathVariable("statusID") int statCode) {
        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_STATUS_ADD_VIEW);

        List<UserStatusDTO> userStatList = null;
        try {
            userStatList = userStatusService.getStatusDetail(userStatDTO, statCode);

            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.USER_STATUS_DETAIL, userStatList);
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     * 
     * @param userStat
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "editUserStatus", method = RequestMethod.POST)
    public String updateUserStatus(@ModelAttribute UserStatusDTO userStat, ModelMap model,HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);        

        UserStatusDTO userSatusDTO = null;
        String recordtype = "i";
        try {
            if (userStat != null) {
                userSatusDTO = userStatusService.update(userStat);
                if (userSatusDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_STATUS_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_STATUS_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_STATUS_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
                model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
                model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
                model.addAttribute("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
                model.addAttribute(AppConstant.USER_STATUS_LIST, initializeStatusList());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return url;
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
