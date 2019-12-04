/*
 * 
 */
package com.misyn.hrsystem.service;

import com.misyn.hrsystem.exception.JDBCTemplateException;

/**
 *
 * @author Shanaka
 * @param <T>
 * Class contains basic methods that help for all services
 */
public interface BaseService<T> {
    
    public T insert(T t) throws JDBCTemplateException;
    
    public T update(T t) throws JDBCTemplateException;
    
    public T delete(T t) throws JDBCTemplateException;
    
}
