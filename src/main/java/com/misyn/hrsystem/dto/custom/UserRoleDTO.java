/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Role Object
 */
public class UserRoleDTO implements Serializable {

    private String user_role_id;
    private String role_name;
    private String user_type_id;
    private Integer[] menuItem;
    
    private String userTypeList;
    private int menuOptionID;
    private MenuItemDTO menuItemDTO;
    

    public String getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(String user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public MenuItemDTO getMenuItemDTO() {
        return menuItemDTO;
    }

    public void setMenuItemDTO(MenuItemDTO menuItemDTO) {
        this.menuItemDTO = menuItemDTO;
    }

    public String getUserTypeList() {
        return userTypeList;
    }

    public void setUserTypeList(String userTypeList) {
        this.userTypeList = userTypeList;
    }

    public Integer[] getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Integer[] menuItem) {
        this.menuItem = menuItem;
    }

    public int getMenuOptionID() {
        return menuOptionID;
    }

    public void setMenuOptionID(int menuOptionID) {
        this.menuOptionID = menuOptionID;
    }

 

}
