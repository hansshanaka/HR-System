
package com.misyn.hrsystem.util;

/**
 *
 * @author Shanaka
 * All queries include here as constance
 * some queries are called by one or many DAO class
 */
public class QueryConstants {

    public final static String INSERT_USER_TYPE = "INSERT INTO user_type (user_type_id,type_name) VALUES (?,?)";

    public final static String UPDATE_USER_TYPE = "UPDATE user_type SET "
            + "type_name=? "
            + "WHERE user_type_id=?";

    public final static String DELETE_USER_TYPE = "DELETE FROM user_type WHERE user_type_id=?";

    public final static String SELECT_USER_ROLE = "SELECT * FROM user_role WHERE user_type_id=?";

    public final static String SELECT_USER_ROLE_DETAILS = "SELECT r.user_role_id,r.role_name,r.user_type_id,IFNULL(p.menu_option_id,0)as menuOptionID \n"
            + "FROM user_role r LEFT JOIN role_option p ON r.user_role_id = p.user_role_id\n"
            + "WHERE r.user_role_id =?";

//    public final static String SELECT_USER_ROLE_DETAILS = "SELECT * FROM user_role WHERE user_role_id = ?";
    public final static String INSERT_USER_ROLE = "INSERT INTO user_role (user_role_id,role_name,user_type_id) VALUES (?,?,?)";

    public final static String UPDATE_USER_ROLE = "UPDATE user_role SET role_name = ? WHERE user_role_id= ?";

    public final static String INSERT_ROLE_OPTION = "INSERT INTO role_option(menu_option_id,user_role_id) values(?,?)";

    public final static String DELETE_ROLE_OPTION = "DELETE FROM role_option WHERE user_role_id= ?";

    public final static String INSERT_USER_DEPARTMENT = "INSERT INTO user_department(department_name) values(?)";

    public final static String SELECT_USER_DEPARTMENT_DETAILS = "SELECT * FROM user_department WHERE department_code = ? ";

    public final static String UPDATE_USER_DEPARTMENT = "UPDATE user_department SET "
            + "department_name=? "
            + "WHERE department_code=?";

    public final static String INSERT_USER_CATEGORY = "INSERT INTO user_category(category_name) values(?)";

    public final static String SELECT_USER_CATEGORY_DETAILS = "SELECT * FROM user_category WHERE category_id = ? ";

    public final static String UPDATE_USER_CATEGORY = "UPDATE user_category SET "
            + "category_name=? "
            + "WHERE category_id=?";

    public final static String INSERT_USER_STATUS = "INSERT INTO user_status(emp_status) values(?)";

    public final static String UPDATE_USER_STATUS = "UPDATE user_status SET "
            + "emp_status=? "
            + "WHERE status_id=?";

    public final static String SELECT_USER_STATUS_DETAILS = "SELECT * FROM user_status WHERE status_id = ? ";

    public final static String INSERT_EMPLOYEE = "INSERT INTO employee_mst "
            + "(user_code,title,name,use_name,address,gender,dob,photo,email,mobile_no,land_no,emg_con_no,nic,marital_status,"
            + "join_date,confirm_date,resign_date,input_date,department_code,category_id,user_type_id,user_role_id,status_id,salary)"
            + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?)";

    public final static String UPDATE_EMPLOYEE = "UPDATE employee_mst SET\n"
            + "title=?,name=?,use_name=?,address=?,gender=?,dob=?,photo=?,email=?,mobile_no=?,land_no=?,emg_con_no=?,\n"
            + "nic=?,marital_status=?,join_date=?,confirm_date=?,resign_date=?,input_date=NOW(),department_code=?,category_id=?,user_type_id=?,\n"
            + "user_role_id=?,status_id=?,salary=?,email=? \n"
            + "WHERE user_code=?";

    public final static String SELECT_EMPLOYEE_MENU_OPTION = "SELECT * FROM menu_option WHERE menu_option_id IN (SELECT DISTINCT mo.menu_parent_id FROM role_option ro, menu_option mo \n"
            + "WHERE ro.menu_option_id = mo.menu_option_id \n"
            + "AND ro.user_role_id = ?)";

    public final static String SELECT_EMPLOYEE_SUB_MENU_OPTION = "SELECT mo.*\n"
            + "FROM menu_option AS mo,role_option AS ro\n"
            + "WHERE mo.menu_option_id = ro.menu_option_id AND menu_parent_id = ? AND ro.user_role_id = ?\n"
            + "GROUP BY mo.menu_option_id";

//    public final static String SELECT_EMPLOYEE_ALL_SUB_MENU_OPTION = "SELECT mo.*\n"
//            + "FROM menu_option AS mo,\n"
//            + "role_option AS ro RIGHT JOIN employee_mst AS emp ON emp.user_role_id = ro.user_role_id\n"
//            + "WHERE mo.menu_option_id = ro.menu_option_id AND menu_parent_id = ?\n"
//            + "GROUP BY mo.menu_option_id";
    public final static String SELECT_EMPLOYEE_ALL_SUB_MENU_OPTION = "SELECT mo.*\n"
            + "FROM menu_option AS mo\n"
            + "WHERE mo.menu_parent_id = ?\n"
            + "GROUP BY mo.menu_option_id";

    public final static String SELECT_USER_SUB_MENU_OPTION = "SELECT menu_option_id AS menuId FROM role_option WHERE user_role_id = ?";

//    public final static String INSERT_USER = "INSERT INTO user_mst "
//            + "(user_name,password,user_code,category_id,status_id,department_code,user_role_id,user_type_id) "
//            + "values(?,?,?,?,?,?,?,?)";
//    public final static String INSERT_USER = "INSERT INTO user_mst "
//            + "(user_name,password,user_code,emp_id) "
//            + "values(?,?,?,(SELECT max(emp_id) FROM employee_mst))";
    public final static String INSERT_USER = "INSERT INTO user_mst "
            + "(user_name,password,user_type,user_role,user_email,user_code) "
            + "values(?,?,?,?,?,?)";

    public final static String SELECT_EMPLOYS_ALL = "SELECT e.user_code,e.name,r.role_name,c.category_name, d.department_name,s.emp_status  \n"
            + "FROM employee_mst e,user_category c,user_department d,user_status s,user_role r\n"
            + "WHERE e.category_id = c.category_id and e.department_code = d.department_code \n"
            + "and e.user_role_id = r.user_role_id and e.status_id = s.status_id and e.status_id =?";

    public final static String SELECT_EMPLOYEE = "SELECT * FROM employee_mst WHERE user_code =?";

    public final static String SELECT_EMPLOYEE_DETAIL = "SELECT e.*,r.role_name,c.category_name, d.department_name,s.emp_status\n"
            + "FROM employee_mst e ,user_category c,user_department d,user_status s,user_role r\n"
            + "WHERE e.category_id = c.category_id and e.department_code = d.department_code \n"
            + "and e.user_role_id = r.user_role_id and e.status_id = s.status_id and\n"
            + " user_code =?";

    public final static String SELECT_ALL_EMPLOYEES = "SELECT e.user_code,e.name,r.role_name,c.category_name, d.department_name,s.emp_status \n"
            + "FROM employee_mst e,user_category c,user_department d,user_status s,user_role r\n"
            + "WHERE e.category_id = c.category_id and e.department_code = d.department_code \n"
            + "and e.user_role_id = r.user_role_id and e.status_id = s.status_id";

    public final static String SELECT_DEV_EMPLOYEES = "SELECT e.user_code,e.name,e.use_name\n"
            + "FROM employee_mst e\n"
            + "WHERE e.department_code =2";

    public final static String SELECT_UI_EMPLOYEES = "SELECT e.user_code,e.name,e.use_name\n"
            + "FROM employee_mst e\n"
            + "WHERE e.department_code =6";

    public final static String SELECT_QA_EMPLOYEES = "SELECT e.user_code,e.name,e.use_name\n"
            + "FROM employee_mst e\n"
            + "WHERE e.department_code =7";

    public final static String INSERT_ATTENDENCE_TMP = "INSERT INTO attendence_tmp"
            + "(year,month,day,on_time,off_time,user_code,input_date,input_user,auth_status,auth_user)"
            + " values(?,?,?,?,?,?,NOW(),?,?,?) ";

//    public final static String INSERT_ATTENDENCE_MST = "INSERT INTO attendence_mst"
//            + "(year,month,day,on_time,off_time,user_code,input_date,input_user,auth_status,auth_user)"
//            + " values(?,?,?,?,?,?,?,?,?,?) ";
    public final static String INSERT_ATTENDENCE_MST = "INSERT INTO attendence_mst(SELECT * FROM attendence_tmp)";

    public final static String SELECT_PENDDING_ATTENDENCE = "SELECT * FROM attendence_tmp";

    public final static String CHECK_LOGIN = "SELECT * FROM user_mst WHERE user_name =? AND password = ? ";

    public final static String UPDATE_USER = "UPDATE user_mst SET password =? WHERE user_code=? ";

    public final static String INSERT_ACTIVITY_LOG = "INSERT INTO activity_log(date,time,user_code,ip,description) "
            + "                     values(?,?,?,?,?)";

    public final static String INSERT_LEAVE_TYPE = "INSERT INTO leave_type "
            + "(ltype_name,days) values(?,?)";

    public final static String UPDATE_LEAVE_TYPE = "UPDATE leave_type SET "
            + "ltype_name=?,days=? WHERE ltype_id=?";

    public final static String INSERT_LEAVE = "INSERT INTO emp_leave_tmp \n"
            + "(start_date,end_date,no_of_days,input_date,auth_state,auth_id,auth_date,input_state,ltype_id,user_code) \n"
            + "values(?,?,?,NOW(),?,?,?,?,?,?)";

    public final static String INSERT_LEAVE_BALANCE = "INSERT INTO leave_balance "
            + "(days,ltype_id,user_code) values(?,?,?)";

    public final static String UPDATE_LEAVE_BALANCE = "UPDATE leave_balance SET "
            + "days=? WHERE leave_balance_id = ?";

    public final static String SELECT_LEAVE_BALANCE_DETAIL = "SELECT * FROM leave_balance "
            + "WHERE leave_balance_id = ?";

    public final static String SELECT_LEAVE_BALANCE = "SELECT lb.*,lt.ltype_name \n"
            + "FROM leave_balance lb, leave_type lt \n"
            + "WHERE lb.ltype_id = lt.ltype_id AND user_code = ?";

    public final static String SELECT_LEAVE_LIST = "SELECT el.*,lt.ltype_name\n"
            + "FROM emp_leave_tmp el, leave_type lt\n"
            + "WHERE el.ltype_id = lt.ltype_id AND el.input_state = 'I' AND el.user_code = ? AND \n"
            + "el.start_date >= ? AND el.end_date <= ? ";

    public final static String SELECT_APPROVED_LEAVE_LIST = "SELECT el.*,lt.ltype_name\n"
            + "FROM emp_leave_mst el, leave_type lt\n"
            + "WHERE el.ltype_id = lt.ltype_id ";

    public final static String SELECT_AUTH_LEAVE_LIST = "SELECT el.*,lt.ltype_name\n"
            + "FROM emp_leave_mst el, leave_type lt\n"
            + "WHERE el.ltype_id = lt.ltype_id AND user_code = ?";

    public final static String DELETE_LEAVE_TMP = "DELETE FROM emp_leave_tmp WHERE leave_id=?";

    public final static String DELETE_LEAVE_MST = "DELETE FROM emp_leave_mst WHERE leave_id=?";

    public final static String SELECT_TMP_LEAVE_LIST = "SELECT el.*,lt.ltype_name\n"
            + "FROM emp_leave_tmp el, leave_type lt\n"
            + "WHERE el.ltype_id = lt.ltype_id AND el.input_state ='I' ";

    public final static String SELECT_TMP_CANCEL_LEAVE_LIST = "SELECT el.*,lt.ltype_name\n"
            + "FROM emp_leave_tmp el, leave_type lt\n"
            + "WHERE el.ltype_id = lt.ltype_id AND el.input_state ='C' AND el.auth_state ='P' ";

    public final static String SELECT_TMP_LEAVE_SAVE_HST = "INSERT INTO emp_leave_hst (SELECT * FROM emp_leave_tmp WHERE leave_id = ?)";

    public final static String SELECT_MST_LEAVE_SAVE_HST = "INSERT INTO emp_leave_hst (SELECT * FROM emp_leave_mst WHERE leave_id = ?)";

    public final static String SELECT_TMP_LEAVE_SAVE_MST = "INSERT INTO emp_leave_mst (SELECT * FROM emp_leave_tmp WHERE leave_id = ?)";

    public final static String UPDATE_TMP_AUTH_DETAIL = "UPDATE emp_leave_tmp SET auth_state = ?, auth_id = ?, "
            + "auth_date = NOW() WHERE leave_id = ?";

    public final static String UPDATE_TMP_DETAIL = "UPDATE emp_leave_tmp SET auth_state = ? WHERE leave_id = ?";

    public final static String UPDATE_MST_AUTH_DETAIL = "UPDATE emp_leave_mst SET input_state = ? WHERE leave_id = ?";

    public final static String UPDATE_MST_DETAIL = "UPDATE emp_leave_mst SET input_state = ?, auth_id = ?, "
            + "auth_date = NOW() WHERE leave_id = ?";

    public final static String SELECT_MST_LEAVE_SAVE_TMP = "INSERT INTO emp_leave_tmp (SELECT * FROM emp_leave_mst WHERE leave_id = ?)";

    public final static String UPDATE_LEAVE_BALANCE_SELECTING_LID = "UPDATE leave_balance SET days=? "
            + "WHERE user_code = (SELECT user_code FROM emp_leave_tmp WHERE leave_id = ?) AND\n"
            + "ltype_id = (SELECT ltype_id FROM emp_leave_tmp WHERE leave_id = ?)";

    public final static String SELECT_LEAVE_BAL_LEAVE_ID = "SELECT days FROM leave_balance WHERE "
            + "user_code = (SELECT user_code FROM emp_leave_tmp WHERE leave_id = ?) \n"
            + "AND ltype_id = (SELECT ltype_id FROM emp_leave_tmp WHERE leave_id = ?)";

    public final static String SELECT_NO_OF_DAYS = "SELECT no_of_days AS days FROM emp_leave_tmp WHERE leave_id = ?";

    public final static String SELECT_USER_CODE_MST_LEAVE = "SELECT elm.user_code\n"
            + "FROM emp_leave_mst elm\n"
            + "WHERE elm.leave_id = ?";

    
    //-----Reimbursment
    public final static String INSERT_FOOD_TMP = "INSERT INTO food_tmp \n"
            + "(project_name,amount,image,input_date,auth_status,auth_user,auth_date,user_code) \n"
            + "values(?,?,?,NOW(),?,?,?,?)";

    public final static String SELECT_FOOD_PENDING_LIST = "SELECT * FROM food_tmp WHERE user_code = ?";

    public final static String SELECT_FOOD_AUTH_LIST = "SELECT * FROM food_mst WHERE user_code = ?";

    public final static String SELECT_TMP_FOOD_SAVE_HST = "INSERT INTO food_hst (SELECT * FROM food_tmp WHERE food_id = ?)";

    public final static String DELETE_FOOD_TMP = "DELETE FROM food_tmp WHERE food_id=?";

    //---Food Auth
    public final static String SELECT_FOOD_ALL_PENDING_LIST = "SELECT * FROM food_tmp";

    public final static String UPDATE_TMP_AUTH_FOOD_DETAIL = "UPDATE food_tmp SET auth_status = ?, auth_user = ?, "
            + "auth_date = NOW() WHERE food_id = ?";

    public final static String SELECT_TMP_FOOD_SAVE_MST = "INSERT INTO food_mst (SELECT * FROM food_tmp WHERE food_id = ?)";

    public final static String SELECT_MST_FOOD_SAVE_HST = "INSERT INTO food_hst (SELECT * FROM food_mst WHERE food_id = ?)";

    //--Taxi
    public final static String INSERT_TAXI_TMP = "INSERT INTO taxi_tmp \n"
            + "(project_name,amount,start,end,distance,image,input_date,auth_status,auth_date,auth_user,user_code) \n"
            + "values(?,?,?,?,?,?,NOW(),?,?,?,?)";

    public final static String SELECT_TAXI_PENDING_LIST = "SELECT * FROM taxi_tmp WHERE user_code = ?";

    public final static String SELECT_TAXI_AUTH_LIST = "SELECT * FROM taxi_mst WHERE user_code = ?";

    public final static String SELECT_TMP_TAXI_SAVE_HST = "INSERT INTO taxi_hst (SELECT * FROM taxi_tmp WHERE taxi_id = ?)";

    public final static String DELETE_TAXI_TMP = "DELETE FROM taxi_tmp WHERE taxi_id=?";

    //---Travel Auth
    public final static String SELECT_TAXI_ALL_PENDING_LIST = "SELECT * FROM taxi_tmp";

    public final static String UPDATE_TMP_AUTH_TAXI_DETAIL = "UPDATE taxi_tmp SET auth_status = ?, auth_user = ?, "
            + "auth_date = NOW() WHERE taxi_id = ?";

    public final static String SELECT_TMP_TAXI_SAVE_MST = "INSERT INTO taxi_mst (SELECT * FROM taxi_tmp WHERE taxi_id = ?)";

    public final static String SELECT_MST_TAXI_SAVE_HST = "INSERT INTO taxi_hst (SELECT * FROM taxi_mst WHERE taxi_id = ?)";

    //--Medical
    public final static String INSERT_MEDICAL_TMP = "INSERT INTO medical_tmp \n"
            + "(description,amount,image,auth_status,auth_date,auth_user,input_date,user_code) \n"
            + "values(?,?,?,?,?,?,NOW(),?)";

    public final static String SELECT_MEDICAL_PENDING_LIST = "SELECT * FROM medical_tmp WHERE user_code = ?";

    public final static String SELECT_MEDICAL_AUTH_LIST = "SELECT * FROM medical_mst WHERE user_code = ?";

    public final static String SELECT_TMP_MEDICAL_SAVE_HST = "INSERT INTO medical_hst (SELECT * FROM medical_tmp WHERE medical_id = ?)";

    public final static String DELETE_MEDICAL_TMP = "DELETE FROM medical_tmp WHERE medical_id=?";

    //---Medical Auth
    public final static String SELECT_MEDICAL_ALL_PENDING_LIST = "SELECT * FROM medical_tmp";

    public final static String UPDATE_TMP_AUTH_MEDICAL_DETAIL = "UPDATE medical_tmp SET auth_status = ?, auth_user = ?, "
            + "auth_date = NOW() WHERE medical_id = ?";

    public final static String SELECT_TMP_MEDICAL_SAVE_MST = "INSERT INTO medical_mst (SELECT * FROM medical_tmp WHERE medical_id = ?)";

    public final static String SELECT_MST_MEDICAL_SAVE_HST = "INSERT INTO medical_hst (SELECT * FROM medical_mst WHERE medical_id = ?)";

    //---Project
    public final static String INSERT_PROJECT = "INSERT INTO project "
            + "(project_name,description,client_name,start_date,qa_date,uat_date,delivary_date,dev_emp,qa_emp,ui_emp,input_date,user_code) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),?)";

    public final static String SELECT_USER_PROJECT_LIST = "SELECT * \n"
            + "FROM project \n"
            + "WHERE dev_emp LIKE ? OR qa_emp LIKE ? OR ui_emp LIKE ?";

    public final static String INSERT_PROJECT_DEV_EMP = "INSERT INTO project_emp(dev_emp,ui_emp,qa_emp,project_id) values(?,?,?,?)";

    public final static String INSERT_PROJECT_UI_EMP = "INSERT INTO project_emp(dev_emp,ui_emp,qa_emp,project_id) values(?,?,?,?)";

    public final static String INSERT_PROJECT_QA_EMP = "INSERT INTO project_emp(dev_emp,ui_emp,qa_emp,project_id) values(?,?,?,?)";

    public final static String INSERT_PROJECT_EMP_DETAILS = "INSERT INTO project_emp_detail(emp_code,dep_code,project_id) values(?,?,?)";

    //---Reports
    public final static String SELECT_LOG_LIST = "SELECT al.*\n"
            + "FROM activity_log al\n"
            + "WHERE al.date >= ? AND al.date <= ?";

    public final static String SELECT_REPORT_LEAVE_LIST = "SELECT el.*,lt.ltype_name,em2.use_name AS app_user,em1.use_name AS auth_user\n"
            + "FROM leave_type lt, emp_leave_mst el\n"
            + "LEFT JOIN employee_mst em1 ON el.auth_id = em1.user_code\n"
            + "LEFT JOIN employee_mst em2 ON el.user_code = em2.user_code\n"
            + "WHERE el.ltype_id = lt.ltype_id";

//    public final static String SELECT_PROJECT_REPORT_LIST = "SELECT p.*,em1.use_name AS dev_emp,\n"
//            + "em2.use_name AS ui_emp, em3.use_name AS qa_emp\n"
//            + "FROM project p, project_emp pm\n"
//            + "LEFT JOIN employee_mst em1 on pm.dev_emp = em1.user_code\n"
//            + "LEFT JOIN employee_mst em2 on pm.ui_emp = em2.user_code\n"
//            + "LEFT JOIN employee_mst em3 on pm.qa_emp = em3.user_code\n"
//            + "WHERE p.project_id = pm.project_id";
    public final static String SELECT_PROJECT_REPORT_LIST = "SELECT p.*,em1.use_name AS dev_emp_nam, pmd.dep_code \n"
            + "FROM project p, project_emp_detail pmd\n"
            + "LEFT JOIN employee_mst em1 on pmd.emp_code = em1.user_code\n"
            + "WHERE p.project_id = pmd.project_id";

    public final static String SELECT_FOOD_REPORT_LIST = "SELECT fm.project_name,fm.amount,fm.input_date,fm.auth_date,em1.use_name AS user_code,"
            + "em2.use_name AS auth_user\n"
            + "FROM food_mst fm \n"
            + "LEFT JOIN employee_mst em1 ON em1.user_code = fm.user_code\n"
            + "LEFT JOIN employee_mst em2 ON em2.user_code = fm.auth_user";

    public final static String SELECT_TAXI_REPORT_LIST = "SELECT tm.project_name,tm.amount,tm.start,tm.end,tm.distance,"
            + "tm.input_date,tm.auth_date,em1.use_name AS user_code,em2.use_name AS auth_user\n"
            + "FROM taxi_mst tm\n"
            + "LEFT JOIN employee_mst em1 ON em1.user_code = tm.user_code\n"
            + "LEFT JOIN employee_mst em2 ON em2.user_code = tm.auth_user";

    public final static String SELECT_MEDICAL_REPORT_LIST = "SELECT mm.description,mm.amount,mm.input_date,mm.auth_date,em1.use_name AS user_code,"
            + "em2.use_name AS auth_user\n"
            + "FROM medical_mst mm\n"
            + "LEFT JOIN employee_mst em1 ON em1.user_code = mm.user_code\n"
            + "LEFT JOIN employee_mst em2 ON em2.user_code = mm.auth_user";

    public final static String SELECT_REPORT_EMPLOYEE_DETAIL = "SELECT e.*,r.role_name,c.category_name, d.department_name,s.emp_status\n"
            + "FROM employee_mst e, user_category c,user_department d,user_status s,user_role r\n"
            + "WHERE e.category_id = c.category_id and e.department_code = d.department_code \n"
            + "and e.user_role_id = r.user_role_id and e.status_id = s.status_id";

    //-----Insert Salary Temp
    public final static String INSERT_EMPLOYEE_SALARY_TMP = "INSERT INTO salary_tmp (attend_days,leave_days,year,month,salary,input_date,user_code)\n"
            + "VALUES (?,?,?,?,?,NOW(),?)";

    public final static String SELECT_SALARY_TMP_DETAIL = "SELECT COUNT(am.user_code) AS attend_days,am.`year`,am.`month`,em.salary,em.user_code\n"
            + "FROM employee_mst em \n"
            + "LEFT JOIN attendence_mst am ON em.user_code = am.user_code\n"
            + "WHERE em.status_id = '1' AND am.`year` = YEAR(NOW()) AND am.`month` = MONTH(NOW()) \n"
            + "GROUP BY am.`year`,am.`month`,am.user_code";

    public final static String SELECT_LEAVE_COUNT_SALARY_TMP = "SELECT elm.user_code, SUM(elm.no_of_days) AS leave_days\n"
            + "FROM employee_mst em \n"
            + "LEFT JOIN emp_leave_mst elm ON em.user_code = elm.user_code\n"
            + "WHERE YEAR(elm.start_date) = YEAR(NOW()) AND MONTH(elm.end_date) = MONTH(NOW())\n"
            + "GROUP BY elm.user_code";

    public final static String SELECT_SALARY_TMP_ALL = "SELECT st.* , em.use_name AS user_name\n"
            + "FROM salary_tmp st, employee_mst em\n"
            + "WHERE st.user_code = em.user_code";

    public final static String INSERT_SALARY_MST = "INSERT INTO salary_mst(SELECT * FROM salary_tmp)";

    public final static String SELECT_REPORT_EMPLOYEE_SALARY_DETAIL = "SELECT sm.*, em.name AS user_name \n"
            + "FROM salary_mst sm, employee_mst em\n"
            + "WHERE sm.user_code = em.user_code AND em.status_id = 1";

    public final static String SELECT_REPORT_EMPLOYEE_ATTENDANCE_DETAIL = "SELECT am.*, em.name AS emp_name\n"
            + "FROM attendence_mst am, employee_mst em\n"
            + "WHERE am.user_code = em.user_code AND em.status_id = 1";
}
