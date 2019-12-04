/*
 * MedicalController
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.ActivityLogService;
import com.misyn.hrsystem.service.custom.MedicalService;
import com.misyn.hrsystem.util.AppConstant;
import com.misyn.hrsystem.util.AppURL;
import static com.misyn.hrsystem.util.AppURL.PATH_ADD_MEDICAL_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_AUTH_MEDICAL_VIEW;
import static com.misyn.hrsystem.util.AppURL.PATH_CHECK_MEDICAL_VIEW;
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
 * Medical Controller contains all methods related to Medical Function
 */
@Controller
@RequestMapping("/medicalReimb/")
@SessionAttributes("userDTO")
@PropertySource(value = {"classpath:system.properties"})
public class MedicalController {

    private static final Logger LOGGER = Logger.getLogger(LeaveController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private ActivityLogService activityService;

    @Autowired
    private MedicalService medicalService;

    @Autowired
    private Environment environment;

    /**
     * Medical insert view
     * @param medicalDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "insertMedical", method = RequestMethod.GET)
    public ModelAndView getMedicalInsertView(@ModelAttribute MedicalDTO medicalDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_ADD_MEDICAL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Insert Medical Details view");
        return modelAndView;
    }

    /**
     * save medical
     * @param medicalDTO
     * @param model
     * @param file
     * @param request
     * @return 
     */
    @RequestMapping(value = "saveMedical", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertMedicalDetails(@ModelAttribute MedicalDTO medicalDTO, ModelMap model, @RequestParam CommonsMultipartFile file,
            HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        MedicalDTO medical = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        //---get Save Path From Propoty File
        String path = environment.getProperty("medical.path");

        String savePath = "/MIHRSystem/resources/Reimbursement/Medical";
        long time = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

        try {
            if (medicalDTO != null) {

                //--File Write
                byte barr[] = file.getBytes();

                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + "/" + time + fileExtension));
                bout.write(barr);
                bout.flush();
                bout.close();

                medicalDTO.setImage(savePath + "/" + time + fileExtension);
                medicalDTO.setUser_code(userCode);

                medical = medicalService.insert(medicalDTO);
                if (medical != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_ADD_MEDICAL_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_ADD_MEDICAL_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_ADD_MEDICAL_VIEW;
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
     * @param medicalDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkMedical", method = RequestMethod.GET)
    public ModelAndView getCheckMedicalView(@ModelAttribute MedicalDTO medicalDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_MEDICAL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Check Medical Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param medicalDTO
     * @return 
     */
    @RequestMapping(value = "pendingMedicalList", method = RequestMethod.GET)
    @ResponseBody
    public String getMedicalList(HttpServletRequest request, @ModelAttribute MedicalDTO medicalDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<MedicalDTO> medicalList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        String button = "<div class=\"form-btn table-btn-grp\">\n"
                + "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"cancelLeave()\">Cancel</button>\n"
                + "</div>";

        try {
            medicalDTO.setUser_code(userCode);

            medicalList = medicalService.getPenMedicalList(medicalDTO);

            for (MedicalDTO medical : medicalList) {

                list.add(new String[]{
                    medical.getDescription(),
                    Double.toString(medical.getAmount()),
                    "<img src=" + medical.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (medical.getAuth_status().equals("P") ? "Pendding" : ""),
                    "<button type=\"button\" id=\"canBut\" name=\"canBut\" "
                    + "class=\"btn btn-danger btn-labeled fa fa-close fa-2x focus\" onclick=\"deleteMedical(" + medical.getMedical_id() + ")\">Cancel</button>"
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
     * @param medicalDTO
     * @param medical_id
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "deleteMedical/{medical_id}", method = RequestMethod.POST)
    public String deleteFood(@ModelAttribute MedicalDTO medicalDTO, @PathVariable("medical_id") int medical_id,
            ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        MedicalDTO medical = null;
        String recordtype = "i";
        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            if (medical_id != 0) {
                medical = medicalService.delete(medicalDTO);

                if (medical != null) {
                    successMessage = AppConstant.DELETE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_CHECK_MEDICAL_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_CHECK_MEDICAL_VIEW;
                }

            } else {
                errorMessage = AppConstant.ERROR_MESSAGE;
                url = AppURL.PATH_CHECK_MEDICAL_VIEW;
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
     * @param medicalDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "checkAuthMedical", method = RequestMethod.GET)
    public ModelAndView getCheckAuthMedicalView(@ModelAttribute MedicalDTO medicalDTO, HttpServletRequest request) {

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();
        String ip = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getIp_address();
        String recordtype = "i";

        ModelAndView modelAndView = new ModelAndView(PATH_CHECK_AUTH_MEDICAL_VIEW);
        modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);

        activityService.doLog(userCode, ip, userCode + " Accessed Authorized Medical Details view");
        return modelAndView;
    }

    /**
     * 
     * @param request
     * @param medicalDTO
     * @return 
     */
    @RequestMapping(value = "authMedicalList", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthMedicalList(HttpServletRequest request, @ModelAttribute MedicalDTO medicalDTO) {
        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<MedicalDTO> medicalList = null;

        String userCode = ((UserDTO) request.getSession().getAttribute(USER_DTO)).getUser_code();

        try {
            medicalDTO.setUser_code(userCode);

            medicalList = medicalService.getAuthMedicalList(medicalDTO);

            for (MedicalDTO medical : medicalList) {

                list.add(new String[]{
                    medical.getDescription(),
                    Double.toString(medical.getAmount()),
                    "<img src=" + medical.getImage() + " alt=\"Bill Image\" width=\"42\" height=\"42\">",
                    (medical.getAuth_status().equals("A") ? "Authorized" : ""), medical.getInput_date(),
                    medical.getAuth_date()});
            }
            gridDTO.setData(list);
            json = gson.toJson(gridDTO);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return json;
    }

}
