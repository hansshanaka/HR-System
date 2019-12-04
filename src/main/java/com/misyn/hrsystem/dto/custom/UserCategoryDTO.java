/*
 * User Category DTO
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;

/**
 *
 * @author Shanaka
 * User Category Object
 */
public class UserCategoryDTO implements Serializable{
    
    private int category_id = 0;
    private String category_name = "";
    
    

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String Category_name) {
        this.category_name = Category_name;
    }
    
    
    
}
