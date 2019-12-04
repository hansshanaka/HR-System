<%-- 
    Document   : checkAttendance
    Created on : Oct 28, 2016, 6:06:03 PM
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

    </head>

    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                            <h1 class="page-header text-overflow">View Attendance</h1>                           
                        </div>
                        <div class="panel">
                        <form:form id="checkAtten" name="checkAtten" class="form-horizontal" method="post">

                            <div class="panel-body">   

                                <div class="form-group">
                                    <label for="year" class="col-sm-3 control-label">Year</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickyear">
                                                <input type="text" path="year" id="year" name="year"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Year"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="Month" class="col-sm-3 control-label">Month</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickmonth">
                                                <input type="text" path="month" id="month" name="month"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Month"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="Day" class="col-sm-3 control-label">Day</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickday">
                                                <input type="text" path="day" id="day" name="day"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Day"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-sm-9 col-sm-offset-3">
                                        <button type="button" class="btn btn-success btn-labeled fa fa-search fa-2x focus"
                                                aria-pressed="false" onclick="return checkAttendance();">Search
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="panel">
                                <div class="panel-body">
                                    <table id="demo-dt-basic" class="table table-striped table-bordered" cellspacing="0"
                                           style="cursor:pointer" width="80%">
                                        <thead>
                                            <tr>
                                                <th class="min-tablet">Year</th>
                                                <th class="min-tablet">Month</th>                                  
                                                <th class="min-tablet">Day</th>                                  
                                                <th class="min-tablet">On Time</th>                                  
                                                <th class="min-tablet">Off Time</th>                                  
                                            </tr>
                                        </thead>
                                    </table>
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

        <!--<script src="${pageContext.request.contextPath}/resources/js/custom/employee/view-attendance-form-validator.js"></script>-->
        <script src="${pageContext.request.contextPath}/resources/js/custom/employee/checkAttendance-datatables.js"></script>

        <!--DataTables [ OPTIONAL ]-->        
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/dataTables.bootstrap.js"></script>
        <!--chosen.jquery.js [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

        <!--Common Function -->
        <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

        <!--------------Tree---------------->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/treeview.js"></script>

        <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>


        <script>


                                                    var optionsYear = {
                                                        format: 'yyyy',
                                                        autoclose: true,
                                                        clearBtn: true,
                                                        viewMode: "years",
                                                        minViewMode: "years"
                                                    };

                                                    var optionsMonth = {
                                                        format: 'mm',
                                                        autoclose: true,
                                                        clearBtn: true,
                                                        viewMode: "months",
                                                        minViewMode: "months"
                                                    };
                                                    var optionsDay = {
                                                        format: 'dd',
                                                        autoclose: true,
                                                        clearBtn: true,
                                                        viewMode: "dates",
                                                        minViewMode: "dates"
                                                    };



                                                    $(".pickyear").datepicker(optionsYear).on('change', function () {

                                                    });


                                                    $(".pickmonth").datepicker(optionsMonth).on('change', function () {

                                                    });
                                                    $(".pickday").datepicker(optionsDay).on('change', function () {

                                                    });


            <c:if test="${successMessage!=null && successMessage!=''}">
                                                    customNiftyNoty('success', '${successMessage}');
            </c:if>
            <c:if test="${errorMessage!=null && errorMessage!=''}">
                                                    customNiftyNoty('danger', '${errorMessage}');
            </c:if>


                                                    $("#user_code").chosen({
                                                        no_results_text: "Oops, nothing found!",
                                                        width: "100%"
                                                    }).on('change', function (evt, params) {
                                                    });
        </script>


    </body>
</html>





