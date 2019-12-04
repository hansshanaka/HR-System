/*

 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.TravelService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_ADD_TRAVEL_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_AUTH_TRAVEL_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_TRAVEL_VIEW;
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
 * @author Shanaka Travel Controller contains all methods related to Travel
 * Function
 */
@Controller
@RequestMapping("/travelReimb/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class TravelController {

    private static final Logger LOGGER = Logger.getLogger(LeaveController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private Environment environment;

    /**
     * insert Travel view
     *
     * @param foodDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "insertTravel", method = RequestMethod.GET)
    public ModelAndView getTravelInsertView(@ModelAttribute FoodDTO foodDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ADD_TRAVEL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Insert Travel Details view");
        return modelAndView;
    }

    /**
     * Save travel
     *
     * @param taxiDTO
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "saveTravel", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertTravelDetails(@ModelAttribute TaxiDTO taxiDTO, ModelMap model, @RequestParam CommonsMultipartFile file,
            HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        TaxiDTO taxi = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        //---get Save Path From Propoty File
        String path = environment.getProperty("travel.path");

        String savePath = "/MIHRSystem/resources/Reimbursement/Travel";
        long time = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

        try {
            if (taxiDTO != null) {

                //--File Write
                byte barr[] = file.getBytes();

                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + "/" + time + fileExtension));
                bout.write(barr);
                bout.flush();
                bout.close();

                taxiDTO.setImage(savePath + "/" + time + fileExtension);
                taxiDTO.setUser_code(userCode);

                taxi = travelService.insert(taxiDTO);
                if (taxi != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_ADD_TRAVEL_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_ADD_TRAVEL_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_ADD_TRAVEL_VIEW;
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
     * @param taxiDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkTravel", method = RequestMethod.GET)
    public ModelAndView getCheckFoodView(@ModelAttribute TaxiDTO taxiDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_TRAVEL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Check Travel Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param taxiDTO
     * @return 
     */
    @RequestMapping(value = "pendingTravelList", method = RequestMethod.GET)
    @ResponseBody
    public String getTaxiList(HttpServletRequest request, @ModelAttribute TaxiDTO taxiDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<TaxiDTO> taxiList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        String button = "<div class=\"form-btn table-btn-grp\">\n"
                + "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"cancelLeave()\">Cancel</button>\n"
                + "</div>";

        try {
            taxiDTO.setUser_code(userCode);

            taxiList = travelService.getPenTravelList(taxiDTO);

            for (TaxiDTO taxi : taxiList) {

                list.add(new String[]{
                    taxi.getProject_name(),
                    Double.toString(taxi.getAmount()),
                    taxi.getStart(), taxi.getEnd(), Double.toString(taxi.getDistance()),
                    "<img src=" + taxi.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (taxi.getAuth_status().equals("P") ? "Pendding" : ""),
                    "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                    + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"deleteTaxi(" + taxi.getTaxi_id() + ")\">Cancel</button>"
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
     * @param taxiDTO
     * @param taxi_id
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "deleteTravel/{taxi_id}", method = RequestMethod.POST)
    public String deleteTaxi(@ModelAttribute TaxiDTO taxiDTO, @PathVariable("taxi_id") int taxi_id,
            ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        TaxiDTO taxi = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();

        try {
            if (taxi_id != 0) {
                taxi = travelService.delete(taxiDTO);

                if (taxi != null) {
                    successMessage = AppConstant.DELETE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_CHECK_TRAVEL_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_CHECK_TRAVEL_VIEW;
                }

            } else {
                errorMessage = AppConstant.ERROR_MESSAGE;
                url = AppURL.PATH_CHECK_TRAVEL_VIEW;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
        }
        activityService.doLog(userCode, ip, userCode + " Delete pending Travel ID: " + taxi_id);
        return url;
    }

    /**
     * 
     * @param taxiDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkAuthTravel", method = RequestMethod.GET)
    public ModelAndView getCheckAuthFoodView(@ModelAttribute TaxiDTO taxiDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_AUTH_TRAVEL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Check Auth Travel Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param taxiDTO
     * @return 
     */
    @RequestMapping(value = "AuthTravelList", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthTaxiList(HttpServletRequest request, @ModelAttribute TaxiDTO taxiDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<TaxiDTO> taxiList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            taxiDTO.setUser_code(userCode);

            taxiList = travelService.getAuthTravelList(taxiDTO);

            for (TaxiDTO taxi : taxiList) {

                list.add(new String[]{
                    taxi.getProject_name(),
                    Double.toString(taxi.getAmount()),
                    taxi.getStart(), taxi.getEnd(), Double.toString(taxi.getDistance()),
                    "<img src=" + taxi.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (taxi.getAuth_status().equals("A") ? "Authorized" : ""), taxi.getInput_date(), taxi.getAuth_date()});

            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }
}
