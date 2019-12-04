/*
 * UserCategoryService Implementation
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.UserCategoryDAO;
import com.misyn.hrsystem.dto.custom.UserCategoryDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.UserCategoryService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * Save user category update user category
 */
@Service
public class UserCategoryServiceImpl implements UserCategoryService{
    
    private final static Logger LOGGER = Logger.getLogger(UserCategoryServiceImpl.class);
    
    @Autowired
    private UserCategoryDAO userCategoryDAO;

    /**
     * 
     * @param userCat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserCategoryDTO insert(UserCategoryDTO userCat) throws JDBCTemplateException {
        UserCategoryDTO userCatDTO = null;
        try {
           userCatDTO = userCategoryDAO.saveMaster(userCat);
                   
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userCatDTO;
    }

    /**
     * 
     * @param userCat
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserCategoryDTO update(UserCategoryDTO userCat) throws JDBCTemplateException {
                UserCategoryDTO userCategoryDTO = null;
        try {
            userCategoryDTO = userCategoryDAO.updateMaster(userCat);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return userCategoryDTO;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserCategoryDTO delete(UserCategoryDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param userCategoryDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserCategoryDTO> getUserCategoryList(UserCategoryDTO userCategoryDTO) throws JDBCTemplateException {
        return userCategoryDAO.getUserCategoryList(userCategoryDTO);
    }

    /**
     * 
     * @param categoryDTO
     * @param catCode
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<UserCategoryDTO> getCategoryDetail(UserCategoryDTO categoryDTO, int catCode) throws JDBCTemplateException {
        return userCategoryDAO.getCategDetail(categoryDTO, catCode);
    }
    
}
