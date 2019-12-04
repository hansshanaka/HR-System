/*
 * 
 */
package com.misyn.hrsystem.dao;

import com.misyn.hrsystem.exception.JDBCTemplateException;

/**
 *
 * @author Shanaka
 * Class contains basic methods that help for all DAO
 */
public interface BaseDAO<T> {
    
    public T saveMaster(T t) throws JDBCTemplateException;
    
    public T updateMaster(T t)throws JDBCTemplateException;
    
    public boolean deleteMaster(Object id)throws JDBCTemplateException;
    
}
