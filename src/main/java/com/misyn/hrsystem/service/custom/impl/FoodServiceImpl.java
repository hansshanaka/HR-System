/*
 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.FoodDAO;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.FoodService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 */
@Service
public class FoodServiceImpl implements FoodService{
    
    private static final Logger LOGGER = Logger.getLogger(FoodServiceImpl.class);
    
    @Autowired
    private FoodDAO foodDAO;

    /**
     * Insert food details
     * @param food
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO insert(FoodDTO food) throws JDBCTemplateException {
       return foodDAO.saveMaster(food);
    }

    /**
     * update food details
     * @param food
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO update(FoodDTO food) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * delete Food
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO delete(FoodDTO foodDTO) throws JDBCTemplateException {
        boolean result = false;
        try {
            result = foodDAO.deleteMaster(foodDTO.getFood_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? foodDTO : null;
    }

    /**
     * get pending food list
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getPenFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return foodDAO.getPenFoodList(foodDTO);
    }

    /**
     * get all pending food list
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getPenAllFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return foodDAO.getPenAllFoodList(foodDTO);
    }

    /**
     * authorize food this method gets list of food, list cann't save at once
     * following for loop is getting responsibility going through the list and pass object to DAO
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO authorizeFood(FoodDTO foodDTO) throws JDBCTemplateException {
        FoodDTO food= null;

        try {
            
            Integer[] foodIDArray = foodDTO.getFoodID();
            for(int i = 0; i < foodIDArray.length; i++) { 
                food = foodDAO.authorizeFood(foodDTO,foodIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return food;
    }

    /**
     * Reject Food this method gets list of food, list cann't save at once
     * following for loop is getting responsibility going through the list and pass object to DAO
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO rejectFood(FoodDTO foodDTO) throws JDBCTemplateException {
        FoodDTO food= null;
        
        try {            
            Integer[] foodIDArray = foodDTO.getFoodID();
            for(int i = 0; i < foodIDArray.length; i++) {                               
                food = foodDAO.rejectFood(foodDTO,foodIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return food;
    }

    /**
     * get authorize food
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getAuthFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return foodDAO.getAuthFoodList(foodDTO);
    }

    /**
     * generate report, get food 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getRepFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return foodDAO.getRepFoodList(foodDTO);
    }
    
}
