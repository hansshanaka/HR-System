/*
 * 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.MenuItemDAO;
import com.misyn.hrsystem.dto.custom.MenuItemDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.MenuItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * provide system menus according to the user role
 */
@Service("MenuItemService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class MenuItemServiceImpl implements MenuItemService{
    
   @Autowired
    private MenuItemDAO menuItemDAO;

   /**
    * get Menu Item List
    * @param role_id
    * @return
    * @throws JDBCTemplateException 
    */
    @Override
    public List<MenuItemDTO> getMenuItemList(String role_id) throws JDBCTemplateException{
        
        List<MenuItemDTO> menuList = menuItemDAO.getMenuItemDTOList(role_id);
        for(MenuItemDTO menuItemDTO:menuList){
            List<MenuItemDTO> subMenuList = menuItemDAO.getSubMenuItemDTOList(menuItemDTO.getMenuId(),role_id);
            menuItemDTO.setSubMenuItemDTO(subMenuList);
        }
        return menuList;
    }

    /**
     * Get All Menu Item
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MenuItemDTO> getAllMenuItemList() throws JDBCTemplateException {
        List<MenuItemDTO> menuList = menuItemDAO.getAllMenuItemList();
        for(MenuItemDTO menuItemDTO:menuList){
            List<MenuItemDTO> subMenuList = menuItemDAO.getAllSubMenuItemDTOList(menuItemDTO.getMenuId());
            menuItemDTO.setSubMenuItemDTO(subMenuList);
        }
        return menuList;
    }
/**
 * 
 * @param role_id
 * @return
 * @throws JDBCTemplateException 
 */
    @Override
    public List<MenuItemDTO> getUserSubMenuItemList(String role_id) throws JDBCTemplateException {
        return menuItemDAO.getUserSubMenuItemList(role_id);
    }
    
   
}
