/*
 * 
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dto.custom.MenuItemDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Menu Item DAO interface contains all methods related to Menu Item Function
 */
public interface MenuItemDAO {
    
    public List<MenuItemDTO> getMenuItemDTOList(String role_id) throws JDBCTemplateException;
    
    public List<MenuItemDTO> getSubMenuItemDTOList(int menuId,String role_id) throws JDBCTemplateException;
    
    public List<MenuItemDTO> getAllMenuItemList() throws JDBCTemplateException;
    
    public List<MenuItemDTO> getAllSubMenuItemDTOList(int menuId) throws JDBCTemplateException;
    
    public List<MenuItemDTO> getUserSubMenuItemList(String role_id) throws JDBCTemplateException;
    
}
