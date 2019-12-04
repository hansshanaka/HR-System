<%-- 
    Document   : approvedLeave
    Created on : Oct 9, 2016, 5:32:38 AM
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


        <!--Page Load Progress Bar [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.js"></script>

        <script type="application/javascript">
            var contextPath = "${pageContext.request.contextPath}";
            var recordtype = "${recordtype}";
        </script>

        <script type="application/javascript">

            function closePage() {
            document.appLeaveForm.action = "${pageContext.request.contextPath}/home";
            document.appLeaveForm.submit();
            }
        </script>

    </head>

    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                            <h1 class="page-header text-overflow">Approved Leave</h1>                    
                    </div>
                    <div class="panel">
                    <form:form id="appLeaveForm" name="appLeaveForm" class="form-horizontal" method="POST">
                            <div  class="text-center" id="res" style="display: none">
                                <div class="msg msg_danger" id="errorMsg"></div>
                            </div>

                            <div class="panel-body">

                                <div class="form-group">
                                    <label for="leave_type" class="col-sm-3 control-label">Select Leave Type</label>
                                    <div class="col-sm-6">                                      
                                        <select name="ltype_id" id="ltype_id" class="form-control input-lg" >                                                    
                                        <c:forEach var="item" items="${leaveTypeList}">                                            
                                                <option value="${item.ltype_id}">${item.ltype_name}</option>                                         
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="sdate" class="col-sm-3 control-label">Start Date</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate sdate">
                                                <input type="text" path="start_date" id="start_date" name="start_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Start Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="edate" class="col-sm-3 control-label">End Date</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate edate">
                                                <input type="text" path="end_date" id="end_date" name="end_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="End Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-sm-9 col-sm-offset-3">
                                        <button type="button" class="btn btn-info btn-lg btn-labeled fa fa-search fa-lg"
                                                aria-pressed="false" onclick="searchAppLeave();">Search
                                        </button>
                                   
                                        <button class="btn btn-default btn-active-mint btn-lg btn-labeled fa fa-times fa-lg"
                                                type="button" onclick="closePage();">Close</button>

                                    </div>
                                </div>
                            </div>
                            
                              <div class="panel">
                                <div class="panel-body">
                                    <table id="demo-dt-basic" class="table table-striped table-bordered" cellspacing="0"
                                           style="cursor:pointer" width="80%">
                                        <thead>
                                            <tr>
                                                <th class="min-tablet">Start Date</th>
                                                <th class="min-tablet">End Date</th>                                  
                                                <th class="min-tablet">No of days</th>                                  
                                                <th class="min-tablet">Leave Type</th>                                  
                                                <th class="min-tablet">Status</th>
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

        <!--DataTables [ OPTIONAL ]-->        
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/dataTables.bootstrap.js"></script>

        
        <script src="${pageContext.request.contextPath}/resources/js/custom/leave/approved-leave-datatables.js"></script>

        <!--chosen.jquery.js [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

        <!--Common Function -->
        <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

        <!--------------Tree---------------->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/treeview.js"></script>
        
         <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
        
        <script>

                
                  $("#ltype_id").chosen({
        no_results_text: "Oops, nothing found!",
        width: "100%"
    }).on('change', function (evt, params) {
        // alert("HI....");
    });
    
    var optionsStartDate = {
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: "linked",
        todayHighlight: true,
        clearBtn: true,
    };
    var optionsEndDate = {
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: "linked",
        todayHighlight: true,
        clearBtn: true,
    };

    $(".sdate").datepicker(optionsStartDate).on('change', function () {

    });
    
    $(".edate").datepicker(optionsEndDate).on('change', function () {

    });
    
                            function dateSelect() {

                            var fromDate = parseInt($('#fromYear').val());
                            var toDate = parseInt($('#toYear').val());
                            alert(fromDate);

                            if (fromDate > toDate) {
                                document.getElementById("res").style.display = 'block';
                                document.getElementById("errorMsg").innerHTML = "Start Date should not pass To Date";
                                window.location.hash = '#errorMsg';
                                return false;
                          
                            } else {
                                document.getElementById("errorMsg").innerHTML = "";
                                return true;
                            }

                        }
        </script>




    </body>
</html>


