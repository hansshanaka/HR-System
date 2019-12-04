/*
 * Project DTO
 */
package com.misyn.hrsystem.dto.custom;

/**
 *
 * @author Shanaka
 * Project Object
 */
public class ProjectDTO {
    
    private int project_id;
    private String project_name;
    private String description;
    private String client_name;
    private String start_date;
    private String qa_date;
    private String uat_date;
    private String delivary_date;
    private String dev_emp;
    private String qa_emp;
    private String ui_emp;
    private String input_date;
    private String user_code;
    
    private String[] devEmp;
    private String[] qaEmp;
    private String[] uiEmp;
    
    private int dep_code;
    private String dev_emp_nam;
    private String qa_emp_nam;
    private String ui_emp_nam;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getQa_date() {
        return qa_date;
    }

    public void setQa_date(String qa_date) {
        this.qa_date = qa_date;
    }

    public String getUat_date() {
        return uat_date;
    }

    public void setUat_date(String uat_date) {
        this.uat_date = uat_date;
    }

    public String getDelivary_date() {
        return delivary_date;
    }

    public void setDelivary_date(String delivary_date) {
        this.delivary_date = delivary_date;
    }

    public String getDev_emp() {
        return dev_emp;
    }

    public void setDev_emp(String dev_emp) {
        this.dev_emp = dev_emp;
    }

    public String getQa_emp() {
        return qa_emp;
    }

    public void setQa_emp(String qa_emp) {
        this.qa_emp = qa_emp;
    }

    public String getUi_emp() {
        return ui_emp;
    }

    public void setUi_emp(String ui_emp) {
        this.ui_emp = ui_emp;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String[] getDevEmp() {
        return devEmp;
    }

    public void setDevEmp(String[] devEmp) {
        this.devEmp = devEmp;
    }

    public String[] getQaEmp() {
        return qaEmp;
    }

    public void setQaEmp(String[] qaEmp) {
        this.qaEmp = qaEmp;
    }

    public String[] getUiEmp() {
        return uiEmp;
    }

    public void setUiEmp(String[] uiEmp) {
        this.uiEmp = uiEmp;
    }

    public int getDep_code() {
        return dep_code;
    }

    public void setDep_code(int dep_code) {
        this.dep_code = dep_code;
    }

    public String getDev_emp_nam() {
        return dev_emp_nam;
    }

    public void setDev_emp_nam(String dev_emp_nam) {
        this.dev_emp_nam = dev_emp_nam;
    }

    public String getQa_emp_nam() {
        return qa_emp_nam;
    }

    public void setQa_emp_nam(String qa_emp_nam) {
        this.qa_emp_nam = qa_emp_nam;
    }

    public String getUi_emp_nam() {
        return ui_emp_nam;
    }

    public void setUi_emp_nam(String ui_emp_nam) {
        this.ui_emp_nam = ui_emp_nam;
    }


    
}
