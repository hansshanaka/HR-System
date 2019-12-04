/*
 * EmployeeDTO
 */
package com.misyn.hrsystem.dto.custom;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Shanaka
 * Employee Object
 */
public class EmployeeDTO implements Serializable{
    
    private int emp_id;
    private String user_code;
    private String title;
    private String name;
    private String use_name;
    private String address;
    private String gender;
    private String dob;
    private String photo;
    private String email;
    private String marriage_cert;
    private String mobile_no;
    private String land_no;
    private String emg_con_no;
    private String nic;
    private String marital_status;
    private String spouse_name;
    private int no_of_children;
    private String join_date;
    private String confirm_date;
    private String resign_date;
    private String input_date;
    private int category_id;
    private int status_id;
    private int department_code;    
    private String user_role_id;
    private String user_type_id;
    private int input_user_id;
    
    private String role_name;
    private String category_name;
    private String department_name;
    private String emp_status;
    private BigDecimal salary;
    
    private UserCategoryDTO userCategory;
    private UserDepartmentDTO userDepartment;
    private UserStatusDTO userStatus;
    private UserRoleDTO userRole;

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    
    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    public String getMarriage_cert() {
        return marriage_cert;
    }

    public void setMarriage_cert(String marriage_cert) {
        this.marriage_cert = marriage_cert;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getLand_no() {
        return land_no;
    }

    public void setLand_no(String land_no) {
        this.land_no = land_no;
    }

    public String getEmg_con_no() {
        return emg_con_no;
    }

    public void setEmg_con_no(String emg_con_no) {
        this.emg_con_no = emg_con_no;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getSpouse_name() {
        return spouse_name;
    }

    public void setSpouse_name(String spouse_name) {
        this.spouse_name = spouse_name;
    }

    public int getNo_of_children() {
        return no_of_children;
    }

    public void setNo_of_children(int no_of_children) {
        this.no_of_children = no_of_children;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(String confirm_date) {
        this.confirm_date = confirm_date;
    }

    public String getResign_date() {
        return resign_date;
    }

    public void setResign_date(String resign_date) {
        this.resign_date = resign_date;
    }

    public String getInput_date() {
        return input_date;
    }

    public void setInput_date(String input_date) {
        this.input_date = input_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }



    public int getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(int department_code) {
        this.department_code = department_code;
    }

    public int getInput_user_id() {
        return input_user_id;
    }

    public void setInput_user_id(int input_user_id) {
        this.input_user_id = input_user_id;
    }

    public String getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(String user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public UserCategoryDTO getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategoryDTO userCategory) {
        this.userCategory = userCategory;
    }

    public UserDepartmentDTO getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(UserDepartmentDTO userDepartment) {
        this.userDepartment = userDepartment;
    }

    public UserStatusDTO getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusDTO userStatus) {
        this.userStatus = userStatus;
    }

    public UserRoleDTO getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDTO userRole) {
        this.userRole = userRole;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }



    
    
    
    
    
    
}
