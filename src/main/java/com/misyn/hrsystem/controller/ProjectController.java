/*
 * ProjectController
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.EmployeeDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.EmployeeService;
import com.misyn.hrsystem.service.custom.ProjectService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_ADD_POJECT_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_POJECT_VIEW;
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
 * Project Controller contains all methods related to Project Function
 */
@Controller
@RequestMapping("/project/")
@SessionAttributes("userDTO")
public class ProjectController {

    private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    /**
     * 
     * @param projectDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "addProject", method = RequestMethod.GET)
    public ModelAndView getAddProjectView(@ModelAttribute ProjectDTO projectDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ADD_POJECT_VIEW);
        modelAndView.addObject(AppConstant.DEV_LIST, initializeDevUserList());
        modelAndView.addObject(AppConstant.UI_LIST, initializeUIUserList());
        modelAndView.addObject(AppConstant.QA_LIST, initializeQAUserList());
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed ADD Project Insert view");
        return modelAndView;
    }

    /**
     * DEv User List generate as option list
     * @return 
     */
    public List<EmployeeDTO> initializeDevUserList() {

        List<EmployeeDTO> devEmployee = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            devEmployee = employeeService.getDevEmployees(employeeDTO);

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        return devEmployee;
    }

    /**
     * UI User List generate as option list
     * @return 
     */
    public List<EmployeeDTO> initializeUIUserList() {

        List<EmployeeDTO> uiEmployee = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            uiEmployee = employeeService.getUIEmployees(employeeDTO);

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        return uiEmployee;
    }

    /**
     * 
     * @return 
     */
    public List<EmployeeDTO> initializeQAUserList() {

        List<EmployeeDTO> qaEmployee = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            qaEmployee = employeeService.getQAEmployees(employeeDTO);

        } catch (JDBCTemplateException e) {
            LOGGER.error(e);
        }
        return qaEmployee;
    }

    /**
     * Save project
     * @param project
     * @param modelMap
     * @param request
     * @return 
     */
    @RequestMapping(value = "saveProject", method = RequestMethod.POST)
    public String insertProject(@ModelAttribute ProjectDTO project, ModelMap modelMap, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        ProjectDTO projectDTO = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (project != null) {
                project.setUser_code(userCode);
                projectDTO = projectService.insert(project);

                if (projectDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_ADD_POJECT_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_ADD_POJECT_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_ADD_POJECT_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            modelMap.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            modelMap.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            modelMap.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            modelMap.addAttribute(AppConstant.DEV_LIST, initializeDevUserList());
            modelMap.addAttribute(AppConstant.UI_LIST, initializeUIUserList());
            modelMap.addAttribute(AppConstant.QA_LIST, initializeQAUserList());
        }
        return url;
    }

    /**
     * 
     * @param projectDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkProject", method = RequestMethod.GET)
    public ModelAndView getCheckProjectView(@ModelAttribute ProjectDTO projectDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_POJECT_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Check Project Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param projectDTO
     * @return 
     */
    @RequestMapping(value = "userProjectList", method = RequestMethod.GET)
    @ResponseBody
    public String getuserProjectList(HttpServletRequest request, @ModelAttribute ProjectDTO projectDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<ProjectDTO> projectList = null;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            projectDTO.setUser_code(userCode);
            projectList = projectService.getUserProjectList(projectDTO);

            for (ProjectDTO project : projectList) {

                list.add(new String[]{
                    project.getProject_name(),
                    project.getDescription(), project.getClient_name(), project.getStart_date(), project.getQa_date(), project.getUat_date(),
                    project.getDelivary_date()});

            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

}
