/*
 * 
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * System Variables get from properties file
 */
public class SystemVariables {
    
    private static String reportPath;

    public static String getReportPath() {
        return reportPath;
    }

    public static void setReportPath(String reportPath) {
        SystemVariables.reportPath = reportPath;
    }
    
    
    
}
