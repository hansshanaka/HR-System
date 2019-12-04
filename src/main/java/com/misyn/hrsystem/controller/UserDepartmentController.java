/*
 * User Department Controller
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.dto.custom.UserDepartmentDTO;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserDepartmentService;
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
 * @author Shanaka User Department Controller contains all methods related to
 * User Department Function
 */
@Controller
@RequestMapping("/userDepartment/")
@SessionAttributes({"UserDepartmentDTO", "menuItemList"})
public class UserDepartmentController {

    private static final Logger LOGGER = Logger.getLogger(UserDepartmentController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserDepartmentService userDepartmentService;

    /**
     *
     * @param userDepartmentDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "addUserDepartment", method = RequestMethod.POST)
    public ModelAndView addUserDepartment(@ModelAttribute("userDepartmentDTO") UserDepartmentDTO userDepartmentDTO, HttpServletRequest request) {

        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_DEPARTMENT_ADD_VIEW);
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        try {
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     *
     * @param userDep
     * @param model
     * @return
     */
    @RequestMapping(value = "saveUserDepartment", method = RequestMethod.POST)
    public String insertUserDepart(@ModelAttribute UserDepartmentDTO userDep, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserDepartmentDTO userDepartmentDTO = null;
        String recordtype = "i";
        try {
            if (userDep != null) {
                userDepartmentDTO = userDepartmentService.insert(userDep);
                if (userDepartmentDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_DEPARTMENT_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_DEPARTMENT_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_DEPARTMENT_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.USER_DEPARTMENT_LIST, initializeDepartmentList());
        }
        return url;
    }

    /**
     *
     * @param depCode
     * @param userDepDTO
     * @return
     */
    @RequestMapping(value = "getDepartment", method = RequestMethod.GET)
    @ResponseBody
    public String getUserDep(@RequestParam("depCode") int depCode, @ModelAttribute UserDepartmentDTO userDepDTO) {

        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<UserDepartmentDTO> userDepList = null;
        try {
            userDepList = userDepartmentService.getDepartmentDetail(userDepDTO, depCode);

            for (UserDepartmentDTO userDepartment : userDepList) {
                list.add(new String[]{Integer.toString(userDepartment.getDepartment_code()), userDepartment.getDepartment_name()});
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
     * @param userDepDTO
     * @param depCode
     * @param request
     * @return
     */
    @RequestMapping(value = "editDepart/{departmetID}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView editDep(UserDepartmentDTO userDepDTO, @PathVariable("departmetID") int depCode, HttpServletRequest request) {

        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_DEPARTMENT_ADD_VIEW);
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        List<UserDepartmentDTO> userDepList = null;
        try {
            userDepList = userDepartmentService.getDepartmentDetail(userDepDTO, depCode);

            modelAndView.addObject(AppConstant.USER_DEPARTMENT_Detail, userDepList);
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     *
     * @param userDep
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "editUserDepartment", method = RequestMethod.POST)
    public String updateUserDepart(@ModelAttribute UserDepartmentDTO userDep, ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        UserDepartmentDTO userDepartmentDTO = null;
        String recordtype = "i";
        try {
            if (userDep != null) {
                userDepartmentDTO = userDepartmentService.update(userDep);
                if (userDepartmentDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_DEPARTMENT_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_DEPARTMENT_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_DEPARTMENT_VIEW;
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

}
