<%-- 
    Document   : addEmployee
    Created on : Jun 15, 2016, 12:07:38 AM
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
                        <c:choose>
                            <c:when test="${recordType=='i'}">
                                <h1 class="page-header text-overflow">Create Employee</h1>
                            </c:when>
                            <c:when test="${recordType=='e'}">
                                <h1 class="page-header text-overflow">Edit Employee</h1>
                            </c:when>
                        </c:choose>

                    </div>
                    <div class="panel">
                    <form:form id="addEmp" name="addEmp" class="form-horizontal" enctype="multipart/form-data" method="post" >

                        <!--<input type="hidden" id="user_code" name="user_code" />-->
                        
                            <div class="panel-body">                                
                                        
                                <div class="form-group">
                                    <label for="title" class="col-sm-3 control-label">User Code<span
                                            class="text-danger"></span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="User Code" class="form-control input-lg"
                                               id="user_code" name="user_code" value="${emp.user_code}" readonly>
                                    </div>
                                </div>  
                                
                                <div class="form-group">
                                    <label for="title" class="col-sm-3 control-label">Title<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="title" id="title" class="form-control">
                                            <option value="">Please Select</option>
                                            <option value="Mr" ${emp.title == 'Mr' ? 'selected' : ''}>Mr</option>
                                            <option value="Ms" ${emp.title == 'Ms' ? 'selected' : ''}>Ms</option>
                                            <option value="Mss" ${emp.title == 'Mss' ? 'selected' : ''}>Mss</option>
                                            
                                        </select>
                                    </div>
                                </div>   
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">Full Name<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Full Name" class="form-control input-lg"
                                               id="name" name="name" value="${emp.name}">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="use_name" class="col-sm-3 control-label">Use Name<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Use Name" class="form-control input-lg"
                                               id="use_name" name="use_name" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="address" class="col-sm-3 control-label">Address<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Address" class="form-control input-lg"
                                               id="address" name="address" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="gender" class="col-sm-3 control-label">Gender<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="gender" id="gender" class="form-control">
                                            <option value="">Please Select</option>
                                            <option value="Male" ${emp.gender == 'Male' ? 'selected' : ''}>Male</option>
                                            <option value="Female" ${emp.gender == 'Female' ? 'selected' : ''}>Female</option>                                          
                                        </select>
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="DOB" class="col-sm-3 control-label">Date of Birth<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate">
                                                <input type="text" path="dob" id="dob" name="dob"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Date of Birth"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="photograph" class="col-sm-3 control-label">Photograph</label>
                                    <div class="col-sm-6">
                                    <c:choose>
                                    <c:when test="${recordType=='e'}">
                                        <img src="${emp.photo}" id="photo" name="photo" alt="Profile Pic" height="92" width="100" disabled="true">   
                                        <!--<input type="hidden" name="photo" id="photo" value=""/>-->
                                    </c:when>
                                    </c:choose>
                                        <input type="file" placeholder="Photograph" class="form-control input-lg"
                                            id="file" name="file" value="" onchange="ValidateSingleInput(this);">                                     
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="Email" class="col-sm-3 control-label">Email<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Email" class="form-control input-lg"
                                               id="email" name="email" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="mobile_no" class="col-sm-3 control-label">Mobile Number<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Mobile Number" class="form-control input-lg"
                                               id="mobile_no" name="mobile_no" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="land_no" class="col-sm-3 control-label">Land Number</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Land Number" class="form-control input-lg"
                                               id="land_no" name="land_no" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="emergency_con_no" class="col-sm-3 control-label">Emergency No</label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Emergency No" class="form-control input-lg"
                                               id="emg_con_no" name="emg_con_no" value="">
                                    </div>
                                </div> 

                                <div class="form-group">
                                    <label for="nic" class="col-sm-3 control-label">NIC<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="NIC" class="form-control input-lg"
                                               id="nic" name="nic" value="">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="marital_status" class="col-sm-3 control-label">Marital Status<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="marital_status" id="marital_status" class="form-control">
                                            <option value="">Please Select</option>
                                            <option value="single" ${emp.marital_status == 'single' ? 'selected' : ''}>Single</option> 
                                            <option value="married" ${emp.marital_status == 'married' ? 'selected' : ''}>Married</option>                                                                                       
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="join_date" class="col-sm-3 control-label">Join Date<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickJoinDate">
                                                <input type="text" path="join_date" id="join_date" name="join_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Join Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="confirm_date" class="col-sm-3 control-label">Confirm Date</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickJoinDate">
                                                <input type="text" path="confirm_date" id="confirm_date" name="confirm_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Confirm Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="resign_date" class="col-sm-3 control-label">Resign Date</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickJoinDate">
                                                <input type="text" path="resign_date" id="resign_date" name="resign_date" 
                                                       class="form-control datePicker_custom" placeholder="Resign Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="userType" class="col-sm-3 control-label">User Department<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="department_code" id="department_code" class="form-control">
                                            <c:forEach var="item" items="${userDepList}">
                                                 <option value="${item.department_code}" ${item.department_code == emp.department_code ? 'selected="selected"' : ''}>${item.department_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="User Category" class="col-sm-3 control-label">User Category<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="category_id" id="category_id" class="form-control">
                                            <c:forEach var="item" items="${userCatList}">
                                                <option value="${item.category_id}" ${item.category_id == emp.category_id ? 'selected="selected"' : ''}>${item.category_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="User Type" class="col-sm-3 control-label">User Type<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="user_type_id" id="user_type_id" class="form-control" onchange="getUserRole()">
                                            <c:forEach var="item" items="${userTypeList}">
                                                <c:if test="${item.user_type_id != 'Admin'}">
                                                 <option value="${item.user_type_id}" ${item.user_type_id == emp.user_type_id ? 'selected="selected"' : ''}>${item.type_name}</option>
                                            </c:if> 
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="user_role_id" class="col-sm-3 control-label">User Role<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="user_role_id" id="user_role_id" class="form-control">                                               
                                                <option value="${emp.user_role_id}">${emp.role_name}</option>                                            
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="User Status" class="col-sm-3 control-label">User Status<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="status_id" id="status_id" class="form-control">
                                            <c:forEach var="item" items="${userStatList}">                                                
                                                <option value="${item.status_id}" ${item.status_id == emp.status_id ? 'selected="selected"' : ''}>${item.emp_status}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                        
                                <div class="form-group">
                                    <label for="salary" class="col-sm-3 control-label">Salary<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="salary" class="form-control input-lg"
                                               id="salary" name="salary" value="">
                                    </div>
                                </div>
                                

                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>

                                    <div class="col-sm-6 text-right">
                                        <span class="text-danger">*</span> Mandatory Fields
                                    </div>
                                </div>
                            </div>

                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-sm-9 col-sm-offset-3">

                                        <button class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                                id="save" type="submit" value="Save" >Save </button>     
                                        <button type="submit" class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                                id="edit" aria-pressed="false"  style="display:none;" value="Edit">
                                            Edit</button>
                                        <button class="btn btn-default btn-active-mint btn-lg btn-labeled fa fa-times fa-lg"
                                                type="button" onclick="closePage();">Close</button>
                                    </div>
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



        <script>

                                                    $(document).on("click", ":submit", function (e) {
                                                        var val = $(this).val();
                                                        $("#addEmp").submit(function (event) {
                                                            var action = "";
                                                            if (val === "Save") {
                                                                action = "${pageContext.request.contextPath}/employee/saveEmployee";
                                                                $("#addEmp").attr('action', action);
                                                            } else if (val === "Edit") {
                                                                action = "${pageContext.request.contextPath}/employee/updateEmp";
                                                                $("#addEmp").attr('action', action);
                                                            } else {
                                                                alert("System Error" + val);
                                                                event.preventDefault();
                                                            }
                                                        });
                                                        
                                                    });

            <c:if test="${successMessage!=null && successMessage!=''}">
                                                    customNiftyNoty('success', '${successMessage}');
            </c:if>
            <c:if test="${errorMessage!=null && errorMessage!=''}">
                                                    customNiftyNoty('danger', '${errorMessage}');
            </c:if>
                
                <c:choose>
                    <c:when test="${recordType=='i'}">
                    </c:when>
                    <c:when test="${recordType=='e'}">
                        document.getElementById("save").style.display="none";
                        document.getElementById("edit").style.display="inline";
                    </c:when>
                </c:choose>

                                                    $("#title").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

                                                    $("#gender").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

                                                    $("#marital_status").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

                                                    $("#category_id").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

                                                    $("#user_type_id").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

//                                                    $("#user_role_id").chosen({
//                                                        no_results_text: "Oops, nothing found!",
//                                                        width: "100%"
//                                                    }).on('change', function (evt, params) {
//                                                    });

                                                    $("#status_id").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });

                                                    $("#department_code").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });


                                                    var optionsDate = {
                                                        format: 'yyyy-mm-dd',
                                                        weekStart: 1,
                                                        autoclose: true,
                                                        startDate: '-40y',
                                                        endDate: '-16y',
                                                        clearBtn: true,
                                                    };
                                                    var joinDate = {
                                                        format: 'yyyy-mm-dd',
                                                        weekStart: 1,
                                                        autoclose: true,
                                                        todayBtn: "linked",
                                                        startDate: '2006-02-06',
                                                        endDate: 'Today',
                                                        timePickerIncrement: 30,
                                                        todayHighlight: true,
                                                        clearBtn: true,
                                                    };

                                                    $(".pickadate").datepicker(optionsDate).on('change', function () {
//        $('#addEmp').bootstrapValidator('revalidateField', 'dob');
                                                    });
                                                    
            $(".pickJoinDate").datepicker(joinDate).on('change', function () {});

        </script>

        <script type="text/javascript">
            function getUserRole() {                
                        
                var userTypeId = $("#user_type_id").val(); 
                var roleID = '${emp.user_role_id}';
                $.ajax({
                    url: '${pageContext.request.contextPath}/employee/getUserRole?typeId='+userTypeId+'&role_id='+roleID,
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
                    $('#salary').val('${EmployeeDTO.salary}');
//                    $('#category_id').val(${EmployeeDTO.category_id});
//                    $('#status_id').val(${EmployeeDTO.status_id});
//                    $('#department_code').val(${EmployeeDTO.department_code});
//                    $('#user_role_id').val('${EmployeeDTO.user_role_id}');
//                    $('#user_type_id').val('${EmployeeDTO.user_type_id}');
                    
                    getUserRole();
                    
            </script>              
        </c:forEach> 
            
       
    </body>
</html>


