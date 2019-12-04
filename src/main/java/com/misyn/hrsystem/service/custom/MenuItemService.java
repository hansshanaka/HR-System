/*
 * 
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.MenuItemDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Menu Item service interface contains all methods related to  Menu Item Function
 */
public interface MenuItemService{
    
    public List<MenuItemDTO> getAllMenuItemList() throws JDBCTemplateException;
    
    public List<MenuItemDTO> getMenuItemList(String role_id) throws JDBCTemplateException;
    
    public List<MenuItemDTO> getUserSubMenuItemList(String role_id) throws JDBCTemplateException;
}
