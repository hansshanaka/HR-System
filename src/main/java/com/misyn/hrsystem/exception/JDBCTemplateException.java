/*
 * 
 */
package com.misyn.hrsystem.exception;

/**
 *
 * @author Shanaka
 * DB Exception 
 */
public class JDBCTemplateException extends Exception{
    
    public JDBCTemplateException(Throwable cause){
        super(cause);
    }
    
    public JDBCTemplateException(String message){
        super(message);
    }
    
    @Override
    public String getMessage(){
        return "JDBCTemplateException occured due to error in DAO layer";
    }
    
}
