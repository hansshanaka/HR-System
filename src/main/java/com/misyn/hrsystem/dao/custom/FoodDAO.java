/*
 * 
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Food DAO interface contains all methods related to Food Function
 */
public interface FoodDAO extends BaseDAO<FoodDTO>{
    
    public List<FoodDTO> getPenFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public List<FoodDTO> getPenAllFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public FoodDTO authorizeFood(FoodDTO FoodDTO,int foodID)throws JDBCTemplateException;
    
    public FoodDTO rejectFood(FoodDTO foodDTO,int foodID)throws JDBCTemplateException;
    
    public List<FoodDTO> getAuthFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public List<FoodDTO> getRepFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
}
