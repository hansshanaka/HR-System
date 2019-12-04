/*

 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Food service interface contains all methods related to Food reimbursement Function
 */
public interface FoodService extends BaseService<FoodDTO>{
    
    public List<FoodDTO> getPenFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public List<FoodDTO> getPenAllFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public FoodDTO authorizeFood(FoodDTO foodDTO)throws JDBCTemplateException;
    
    public FoodDTO rejectFood(FoodDTO foodDTO)throws JDBCTemplateException;
    
    public List<FoodDTO> getAuthFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    public List<FoodDTO> getRepFoodList(FoodDTO foodDTO) throws JDBCTemplateException;
    
    
    
}
