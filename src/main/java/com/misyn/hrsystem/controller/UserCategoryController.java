/*
 * User Category Controller
 */
package com.misyn.hrsystem.controller;

import com.google.gson.Gson;
import com.misyn.hrsystem.dto.custom.GridDTO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.service.custom.MenuItemService;
import com.misyn.hrsystem.service.custom.UserCategoryService;
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
 * User Category Controller contains all methods related to User Category Function
 */
@Controller
@RequestMapping("/userCategory/")
@SessionAttributes({"UserCategoryDTO", "menuItemDTO"})
public class UserCategoryController {

    private static final Logger LOGGER = Logger.getLogger(UserDepartmentController.class);

    private static final String USER_DTO = "userDTO";

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private UserCategoryService userCategorySevice;

    /**
     * 
     * @param userCategoryDTO
     * @param request
     * @return 
     */
    @RequestMapping(value = "addUserCategory", method = RequestMethod.POST)
    public ModelAndView addUserCategory(@ModelAttribute("userCategoryDTO") UserCategoryDTO userCategoryDTO, HttpServletRequest request) {

        String recordtype = "i";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_CATEGORY_ADD_VIEW);
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        try {
            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     * 
     * @param userCat
     * @param model
     * @return 
     */
    @RequestMapping(value = "saveUserCategory", method = RequestMethod.POST)
    public String insertUserDepart(@ModelAttribute UserCategoryDTO userCat, ModelMap model) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;

        UserCategoryDTO userCategoryDTO = null;
        String recordtype = "i";
        try {
            if (userCat != null) {
                userCategoryDTO = userCategorySevice.insert(userCat);
                if (userCategoryDTO != null) {
                    successMessage = AppConstant.SAVE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_CATEGORY_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_CATEGORY_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_CATEGORY_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
            model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
            model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
            model.addAttribute(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
        }
        return url;
    }

    /**
     * 
     * @param catCode
     * @param userCatDTO
     * @return 
     */
    @RequestMapping(value = "getCategory", method = RequestMethod.GET)
    @ResponseBody
    public String getUserDep(@RequestParam("catCode") int catCode, @ModelAttribute UserCategoryDTO userCatDTO) {

        Gson gson = new Gson();
        String json = AppConstant.STRING_EMPTY;
        GridDTO gridDTO = new GridDTO();

        List<String[]> list = new ArrayList<>();
        List<UserCategoryDTO> userCatList = null;
        try {
            userCatList = userCategorySevice.getCategoryDetail(userCatDTO, catCode);

            for (UserCategoryDTO userCategory : userCatList) {
                list.add(new String[]{Integer.toString(userCategory.getCategory_id()), userCategory.getCategory_name()});
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
     * @param userCatDTO
     * @param catCode
     * @param request
     * @return 
     */
    @RequestMapping(value = "editCategory/{categoryID}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView editCat(UserCategoryDTO userCatDTO, @PathVariable("categoryID") int catCode, HttpServletRequest request) {
        String recordtype = "e";
        ModelAndView modelAndView = new ModelAndView(AppURL.PATH_USER_CATEGORY_ADD_VIEW);
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        List<UserCategoryDTO> userCaList = null;
        try {
            userCaList = userCategorySevice.getCategoryDetail(userCatDTO, catCode);

            modelAndView.addObject(AppConstant.USER_CATEGORY_DETAIL, userCaList);
            modelAndView.addObject(AppConstant.RECORD_TYPE, recordtype);
            modelAndView.addObject("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return modelAndView;
    }

    /**
     * 
     * @param userCat
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "editUserCategory", method = RequestMethod.POST)
    public String updateUserDepart(@ModelAttribute UserCategoryDTO userCat, ModelMap model, HttpServletRequest request) {

        String successMessage = AppConstant.STRING_EMPTY;
        String errorMessage = AppConstant.STRING_EMPTY;
        String url = AppURL.URL_PAGE_NOT_FOUND;
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER_DTO);

        UserCategoryDTO userCategoryDTO = null;
        String recordtype = "i";
        try {
            if (userCat != null) {
                userCategoryDTO = userCategorySevice.update(userCat);
                if (userCategoryDTO != null) {
                    successMessage = AppConstant.UPDATE_SUCCESS_MESSAGE;
                    url = AppURL.PATH_USER_CATEGORY_VIEW;
                } else {
                    errorMessage = AppConstant.ERROR_MESSAGE;
                    url = AppURL.PATH_USER_CATEGORY_VIEW;
                }
            } else {
                errorMessage = AppConstant.FIELD_ERROR_MESSAGE;
                url = AppURL.PATH_USER_TYPE_VIEW;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                model.addAttribute(AppConstant.SUCCESS_MESSAGE_PARA, successMessage);
                model.addAttribute(AppConstant.ERROR_MESSAGE_PARA, errorMessage);
                model.addAttribute(AppConstant.RECORD_TYPE, recordtype);
                model.addAttribute(AppConstant.USER_CATEGORY_LIST, initializeCategoryList());
                model.addAttribute("menuItemList", menuItemService.getMenuItemList(user.getUser_role()));

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
    public List<UserCategoryDTO> initializeCategoryList() {

        List<UserCategoryDTO> userDep = new ArrayList<>();
        UserCategoryDTO userCategoryDTO = new UserCategoryDTO();
        try {
            userDep = userCategorySevice.getUserCategoryList(userCategoryDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        UserCategoryDTO userCatList = new UserCategoryDTO();
        userCatList.setCategory_id(0);
        userCatList.setCategory_name("Please Select");
        userDep.add(0, userCatList);

        return userDep;

    }

}
