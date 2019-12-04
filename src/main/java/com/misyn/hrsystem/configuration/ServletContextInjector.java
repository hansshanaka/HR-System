/*
 * 
 */
package com.misyn.hrsystem.configuration;

import javax.servlet.ServletContext;

/**
 *
 * @author Shanaka
 * Set title inject it to sevlet
 */
public class ServletContextInjector {
    
    private ServletContext servletContext;
    
    public void setServletContext(ServletContext sc){
        this.servletContext = sc;
        this.servletContext.setAttribute("TITLE", "MI-Synergy Human Resource System (HRS)");
    }
    
}
