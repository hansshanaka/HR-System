/*
 * 
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.FoodService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_ADD_FOOD_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_AUTH_FOOD_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_FOOD_VIEW;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Shanaka
 * Food Controller contains all methods related to Food Function
 */
@Controller
@RequestMapping("/foodReimb/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class FoodController {

    private static final Logger LOGGER = Logger.getLogger(LeaveController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private Environment environment;

    /**
     * 
     * @param foodDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "insertFood", method = RequestMethod.GET)
    public ModelAndView getFoodInsertView(@ModelAttribute FoodDTO foodDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ADD_FOOD_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Insert Food Details view");
        return modelAndView;
    }

    /**
     * 
     * @param foodDTO
     * @param model
     * @param file
     * @param request
     * @return 
     */
    @RequestMapping(value = "saveFood", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertFoodDetails(@ModelAttribute FoodDTO foodDTO, ModelMap model, @RequestParam CommonsMultipartFile file,
            HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        FoodDTO food = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        //---get Save Path From Propoty File
        String path = environment.getProperty("food.path");

        String savePath = "/MIHRSystem/resources/Reimbursement/Food";
        long time = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

        try {
            if (foodDTO != null) {

                //--File Write
                byte barr[] = file.getBytes();

                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + "/" + time + fileExtension));
                bout.write(barr);
                bout.flush();
                bout.close();

                foodDTO.setImage(savePath + "/" + time + fileExtension);
                foodDTO.setUser_code(userCode);

                food = foodService.insert(foodDTO);
                if (food != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_ADD_FOOD_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_ADD_FOOD_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_ADD_FOOD_VIEW;
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
     * @param foodDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkFood", method = RequestMethod.GET)
    public ModelAndView getCheckFoodView(@ModelAttribute FoodDTO foodDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_FOOD_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Access Check Food Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param foodDTO
     * @return 
     */
    @RequestMapping(value = "pendingFoodList", method = RequestMethod.GET)
    @ResponseBody
    public String getFoodList(HttpServletRequest request, @ModelAttribute FoodDTO foodDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<FoodDTO> foodList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        String button = "<div class=\"form-btn table-btn-grp\">\n"
                + "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"cancelLeave()\">Cancel</button>\n"
                + "</div>";

        try {
            foodDTO.setUser_code(userCode);

            foodList = foodService.getPenFoodList(foodDTO);

            for (FoodDTO food : foodList) {

                list.add(new String[]{
                    food.getProject_name(),
                    Double.toString(food.getAmount()),
                    "<img src=" + food.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (food.getAuth_status().equals("P") ? "Pendding" : ""),
                    "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                    + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"deleteFood(" + food.getFood_id() + ")\">Cancel</button>"
                });

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
     * @param foodDTO
     * @param food_id
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "deleteFood/{food_id}", method = RequestMethod.POST)
    public String deleteFood(@ModelAttribute FoodDTO foodDTO, @PathVariable("food_id") int food_id,
            ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        FoodDTO food = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (food_id != 0) {
                food = foodService.delete(foodDTO);

                if (food != null) {
                    successMessage = AppConstant.DELETE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_CHECK_FOOD_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_CHECK_FOOD_VIEW;
                }

            } else {
                errorMessage = AppConstant.ERROR_MESSAGE;
                url = AppURL.PATH_CHECK_FOOD_VIEW;
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
     * @param foodDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkAuthFood", method = RequestMethod.GET)
    public ModelAndView getCheckAuthFoodView(@ModelAttribute FoodDTO foodDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_AUTH_FOOD_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Check Auth Food Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param foodDTO
     * @return 
     */
    @RequestMapping(value = "AuthFoodList", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthFoodList(HttpServletRequest request, @ModelAttribute FoodDTO foodDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<FoodDTO> foodList = null;
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            foodDTO.setUser_code(userCode);
            foodList = foodService.getAuthFoodList(foodDTO);

            for (FoodDTO food : foodList) {

                list.add(new String[]{
                    food.getProject_name(),
                    Double.toString(food.getAmount()),
                    "<img src=" + food.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (food.getAuth_status().equals("A") ? "Authorized" : ""), food.getInput_date(), food.getAuth_date()});

            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

}
