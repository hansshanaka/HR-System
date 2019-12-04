/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.misyn.hrsystem.dto.custom.LoginDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.LoginService;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.Cryptography;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka
 * Login Controller contains all methods related to Login Function
 */
@Controller
@SessionAttributes({"menuItemList", "userDTO"})
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    
    private static final String USER_DTO = "userDTO";

    Cryptography cryp = new Cryptography();

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private ActivityLogService activityService;

    /**
     * 
     * @param request
     * @param model
     * @return 
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        UserDTO userDTO = (UserDTO) (session != null ? session.getAttribute(USER_DTO) : null);
        String view = AppConstant.STRING_EMPTY;
        if (userDTO == null){
            view = "login";               
        } else {
            view = "home";
        }
        return view;
    }

    /**
     * 
     * @param loginDTO
     * @param result
     * @param request
     * @return 
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView loginValidate(@Valid LoginDTO loginDTO, BindingResult result,HttpServletRequest request) {

        UserDTO checkUser = new UserDTO();
        ModelAndView modelAndView = new ModelAndView();

        UserDTO userDTO = new UserDTO();
        userDTO.setUser_name(loginDTO.getUserId());
        userDTO.setPassword(loginDTO.getLoginPassword());

        try {
            checkUser = loginService.checkLogin(userDTO);
            
            checkUser.setPassword(cryp.decrypt(checkUser.getPassword()));
            checkUser.setIp_address(request.getRemoteAddr());
                 

            if (!checkUser.getUser_id().equals("") && userDTO.getUser_name().equalsIgnoreCase(checkUser.getUser_name()) && userDTO.getPassword().equalsIgnoreCase(checkUser.getPassword())) {
                modelAndView.setViewName("home");
                modelAndView.addObject("userDTO", checkUser);                
                modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(checkUser.getUser_role()));
                activityService.doLog(checkUser.getUser_code(), checkUser.getIp_address(), checkUser.getUser_code()+" Login to the System");
                

            } else {
                modelAndView.setViewName("login");
                modelAndView.addObject("err", "Invalid User Name or Password");
                activityService.doLog(loginDTO.getUserId(), checkUser.getIp_address(), "Invalid User Name or Password");
                return modelAndView;
            }

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }

        return modelAndView;

    }

    /**
     * 
     * @param request
     * @return 
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView loginView = new ModelAndView("login");
        request.getSession().invalidate();
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        
         activityService.doLog(userCode, ip, userCode+" Log out");
        return loginView;

    }

    /**
     * 
     * @param request
     * @return 
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView homeView = new ModelAndView("home");

        return homeView;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView home1(HttpServletRequest request) {
        ModelAndView homeView = new ModelAndView("home");

        return homeView;
    }
}
