/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shanaka
 * Menu Item Object
 */
public class MenuItemDTO implements Serializable{
    
    private int menuId = 0;
    private String menuName ="";
    private String menuUrl = "";
    private int menuParentId = 0;
    
    private List<MenuItemDTO> subMenuItemDTO = new ArrayList<MenuItemDTO>();

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

   

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public int getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(int menuParentId) {
        this.menuParentId = menuParentId;
    }

  

    public List<MenuItemDTO> getSubMenuItemDTO() {
        return subMenuItemDTO;
    }

    public void setSubMenuItemDTO(List<MenuItemDTO> subMenuItemDTO) {
        this.subMenuItemDTO = subMenuItemDTO;
    }

  
    
    
}
