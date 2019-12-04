/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import com.misyn.hrsystem.util.Cryptography;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka User Setting Controller contains all methods related to User
 * Setting Function
 */
@Controller
@RequestMapping("/userSetting/")
@SessionAttributes({"menuItemList", "userDTO"})
public class UserSettingController {

    private static final Logger LOGGER = Logger.getLogger(UserStatusController.class);

    private static final String USER_DTO = "userDTO";

    Cryptography cryp = new Cryptography();

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * @param employeeDTO
     * @return
     */
    @RequestMapping(value = "resetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView employeeRPasswordView(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {

        ModelAndView model = new ModelAndView(AppURL.PATH_EMP_PASSWORD_RESET);
        return model;
    }

    /**
     * 
     * @param userDTO
     * @param model
     * @return 
     */
    @RequestMapping(value = "updateUser", method = {RequestMethod.POST, RequestMethod.GET})
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
                    url = AppURL.PATH_EMP_PASSWORD_RESET;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_EMP_PASSWORD_RESET;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_EMP_PASSWORD_RESET;
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
    @RequestMapping(value = "viewProfile", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView employeeProfileView(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_EMP_VIEW_PROFILE);

        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        List<EmployeeDTO> empList = null;
        EmployeeDTO emp = new EmployeeDTO();

        try {
            empList = employeeService.getEmployeeDetail(employeeDTO, user.getUser_code());

            emp = empList.get(0);

            modelAndView.addObject("menuItemList");
            modelAndView.addObject(AppConstant.EMPLOYEE_LIST, empList);
            modelAndView.addObject(AppConstant.EMPLOYEE, emp);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

}
