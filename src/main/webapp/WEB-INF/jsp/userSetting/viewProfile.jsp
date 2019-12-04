<%-- 
    Document   : viewProfile
    Created on : Sep 18, 2016, 5:43:35 PM
    Author     : Shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MI Synergy -HR</title>

        <!--STYLESHEET-->
        <!--=================================================-->
        
        <!--Browser Icon -->
        <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/img/icon.png" />

        <!--Open Sans Font [ OPTIONAL ] -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700&amp;subset=latin" rel="stylesheet">

        <!--Bootstrap Stylesheet [ REQUIRED ]-->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">

        <!--Nifty Stylesheet [ REQUIRED ]-->
        <link href="${pageContext.request.contextPath}/resources/css/nifty.min.css" rel="stylesheet">

        <!--Font Awesome [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/font-awesome/css/font-awesome.min.css"
              rel="stylesheet">

        <!--Switchery [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/css/themes/type-c/theme-light.css" rel="stylesheet">

        <!--Bootstrap Table [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/datatables/media/css/dataTables.bootstrap.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/plugins/datatables/extensions/Responsive/css/dataTables.responsive.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.css"
              rel="stylesheet">

        <!----Tree------->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/treeview.css">

        <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.css"
              rel="stylesheet">

        <!--SCRIPT-->
        <!--=================================================-->

        <!--Page Load Progress Bar [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.js"></script>

        <script type="application/javascript">
            var contextPath = "${pageContext.request.contextPath}";
            var recordtype = "${recordtype}";
        </script>

        <script type="application/javascript">

            function closePage() {
            document.addEmp.action = "${pageContext.request.contextPath}/employee/empDetail";
            document.addEmp.submit();
            }
        </script>

    </head>
    
    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                         <h1 class="page-header text-overflow">Employee Profile</h1>

                    </div>
                    <div class="panel">
                    <form:form id="addEmp" name="addEmp" class="form-horizontal" method="post" >

                        <input type="hidden" id="user_code" name="user_code" />
                        
                            <div class="panel-body">
                               
                                            
                                <div class="form-group">
                                    <label for="title" class="col-sm-3 control-label">User Code<span
                                            class="text-danger"></span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="User Code" class="form-control input-lg"
                                               id="user_code" name="user_code" value="${emp.user_code}" disabled="true" />
                                    </div>
                                </div>  
                                
                                <div class="form-group">
                                    <label for="title" class="col-sm-3 control-label">Title</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="" class="form-control input-lg"
                                               id="title" name="title" value="" disabled="true"/>                                   
                                    </div>
                                </div>   
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">Full Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Full Name" class="form-control input-lg"
                                               id="name" name="name" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="use_name" class="col-sm-3 control-label">Use Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Use Name" class="form-control input-lg"
                                               id="use_name" name="use_name" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="address" class="col-sm-3 control-label">Address</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Address" class="form-control input-lg"
                                               id="address" name="address" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="gender" class="col-sm-3 control-label">Gender</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="" class="form-control input-lg"
                                               id="gender" name="gender" value="" disabled="true"/>                                        
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="DOB" class="col-sm-3 control-label">Date of Birth</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="dob" id="dob" name="dob"
                                            class="form-control dinput-lg" placeholder="" disabled="true"/>                                           
                                        
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="photograph" class="col-sm-3 control-label">Photograph</label>
                                    <div class="col-sm-6">
<!--                                        <input type="text" placeholder="Photograph" class="form-control input-lg"
                                               id="photo" name="photo" value="" disabled="true">-->
                                    <img src="${emp.photo}" id="photo" name="photo" alt="Profile Pic" height="92" width="100" disabled="true">          
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="birth_certificate" class="col-sm-3 control-label">Email</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Birth Certificate" class="form-control input-lg"
                                            id="email" name="email" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="mobile_no" class="col-sm-3 control-label">Mobile Number</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Mobile Number" class="form-control input-lg"
                                               id="mobile_no" name="mobile_no" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="land_no" class="col-sm-3 control-label">Land Number</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Land Number" class="form-control input-lg"
                                               id="land_no" name="land_no" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="emergency_con_no" class="col-sm-3 control-label">Emergency No</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Emergency No" class="form-control input-lg"
                                               id="emg_con_no" name="emg_con_no" value="" disabled="true">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="nic" class="col-sm-3 control-label">NIC</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="NIC" class="form-control input-lg"
                                               id="nic" name="nic" value="" disabled="true">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="marital_status" class="col-sm-3 control-label">Marital Status</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="NIC" class="form-control input-lg"
                                            id="marital_status" name="marital_status" value="" disabled="true">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="join_date" class="col-sm-3 control-label">Join Date</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="join_date" id="join_date" name="join_date"
                                           class="form-control input-lg" placeholder="Join Date" disabled="true"/>                                          
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="confirm_date" class="col-sm-3 control-label">Confirm Date</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="confirm_date" id="confirm_date" name="confirm_date"
                                            class="form-control input-lg" placeholder="Confirm Date" disabled="true"/>                                        
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="resign_date" class="col-sm-3 control-label">Resign Date</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="resign_date" id="resign_date" name="resign_date" 
                                            class="form-control input-lg" placeholder="Resign Date" disabled="true"/>                                         
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="userType" class="col-sm-3 control-label">User Department</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="department_code" id="department_code" name="department_code" 
                                               class="form-control input-lg" placeholder="" value="${emp.department_name}" disabled="true"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="User Category" class="col-sm-3 control-label">User Category</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="category_id" id="category_id" name="category_id" 
                                            class="form-control input-lg" placeholder="" value="${emp.category_name}" disabled="true"/>
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="User Type" class="col-sm-3 control-label">User Type</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="user_type_id" id="user_type_id" name="user_type_id" 
                                            class="form-control input-lg" placeholder="" value="${emp.user_type_id}" disabled="true"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="user_role_id" class="col-sm-3 control-label">User Role</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="user_role_id" id="user_role_id" name="user_role_id" 
                                            class="form-control input-lg" placeholder="" value="${emp.role_name}" disabled="true"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="User Status" class="col-sm-3 control-label">User Status</label>
                                    <div class="col-sm-6">
                                        <input type="text" path="status_id" id="status_id" name="status_id" 
                                            class="form-control input-lg" placeholder="" value="${emp.emp_status}" disabled="true"/>
                                    </div>
                                </div>

                        </form:form>
                        <!--===================================================-->
                        <!--End Input Size-->

                    </div>
                    <!--===================================================-->
                    <!--End page content-->

                </div>
                <!--===================================================-->
                <!--END CONTENT CONTAINER-->

                <!--MAIN NAVIGATION-->
                <!--===================================================-->
                <jsp:include page="/WEB-INF/jsp/common/sideMenu.jsp"></jsp:include>
                    <!--===================================================-->
                    <!--END MAIN NAVIGATION-->

                    <!--ASIDE-->
                    <!--===================================================-->
                    <aside id="aside-container">
                        <div id="aside">
                            <div class="nano">
                                <div class="nano-content">

                                    <!--Nav tabs-->
                                    <!--================================-->
                                    <ul class="nav nav-tabs nav-justified">
                                        <li class="active"><a href="#demo-asd-tab-1" data-toggle="tab"> <i
                                                    class="fa fa-comments"></i> <span class="badge badge-purple">7</span> </a></li>
                                        <li><a href="#demo-asd-tab-2" data-toggle="tab"> <i class="fa fa-info-circle"></i> </a></li>
                                        <li><a href="#demo-asd-tab-3" data-toggle="tab"> <i class="fa fa-wrench"></i> </a></li>
                                    </ul>
                                    <!--================================-->
                                    <!--End nav tabs-->

                                    <!-- Tabs Content -->
                                    <!--================================-->

                                </div>
                            </div>
                        </div>
                    </aside>
                    <!--===================================================-->
                    <!--END ASIDE-->

                </div>

                <!-- FOOTER -->
                <!--===================================================-->
            <jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
                <!--===================================================-->
                <!-- END FOOTER -->

                <!-- SCROLL TOP BUTTON -->
                <!--===================================================-->
                <button id="scroll-top" class="btn"><i class="fa fa-chevron-up"></i></button>
                <!--===================================================-->

            </div>
            <!--===================================================-->
            <!-- END OF CONTAINER -->

            <!--jQuery [ REQUIRED ]-->
            <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>

        <!--BootstrapJS [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <!--Nifty Admin [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/nifty.min.js"></script>

        <!--Bootstrap Validator [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-validator/bootstrapValidator.js"></script>

        <!--Bootbox Modals [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootbox/bootbox.min.js"></script>

        <!--Morris.js [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/morris-js/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/morris-js/raphael-js/raphael.min.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/custom/user/user-role-form-validator.js"></script>

        <!--chosen.jquery.js [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

        <!--Common Function -->
        <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

        <!--------------Tree---------------->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/treeview.js"></script>

        <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
        
        <!--Validation add Employee-->
        <script src="${pageContext.request.contextPath}/resources/js/custom/employee/employee-form-validator.js"></script>


        <script type="text/javascript">
            function getUserRole() {                
                        
                var userTypeId = $("#user_type_id").val();                
                $.ajax({
                    url: '${pageContext.request.contextPath}/employee/getUserRole?typeId=' + userTypeId,
                    type: "GET",
                    success: function (data) {
                            document.getElementById("user_role_id").innerHTML= data;

                    },
                    error: function () {
                        alert("Error : unable to load");
                    }
                });
            }
        </script>
        
        <c:forEach var="EmployeeDTO" items="${empList}">
            <script type="text/javascript">            
                    $('#user_code').val('${EmployeeDTO.user_code}');                   
                    $('#title').val('${EmployeeDTO.title}'); 
                    
                    $('#name').val('${EmployeeDTO.name}');
                    $('#use_name').val('${EmployeeDTO.use_name}');
                    $('#address').val('${EmployeeDTO.address}');
                    $('#gender').val('${EmployeeDTO.gender}');
                    $('#dob').val('${EmployeeDTO.dob}');
                    $('#photo').val('${EmployeeDTO.photo}');
                    $('#email').val('${EmployeeDTO.email}');
                    $('#mobile_no').val('${EmployeeDTO.mobile_no}');
                    $('#land_no').val('${EmployeeDTO.land_no}');
                    $('#emg_con_no').val('${EmployeeDTO.emg_con_no}');
                    $('#nic').val('${EmployeeDTO.nic}');
                    $('#marital_status').val('${EmployeeDTO.marital_status}');
                    $('#spouse_name').val('${EmployeeDTO.spouse_name}');
                    $('#join_date').val('${EmployeeDTO.join_date}');
                    $('#confirm_date').val('${EmployeeDTO.confirm_date}');
                    $('#category_id').val(${EmployeeDTO.category_name});
                    $('#status_id').val(${EmployeeDTO.emp_status});
                    $('#department_code').val(${EmployeeDTO.department_name});
                    $('#user_role_id').val('${EmployeeDTO.role_name}');
                    $('#user_type_id').val('${EmployeeDTO.user_type_id}');
                    
                    getUserRole();
                    
            </script>              
        </c:forEach> 
            
       
    </body>
</html>



