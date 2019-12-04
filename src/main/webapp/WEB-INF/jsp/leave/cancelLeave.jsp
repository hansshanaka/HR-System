<%-- 
    Document   : cancelLeave
    Created on : Oct 7, 2016, 5:37:17 PM
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

            function deleteLeave(leave_id) {
                document.cancelLeave.action = "${pageContext.request.contextPath}/leave/deleteLeave/"+leave_id;
                document.cancelLeave.submit();
            }
        </script>

    </head>

    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                            <h1 class="page-header text-overflow">Check/Cancel Authorized Leave</h1>
                        </div>
                        <div class="panel">
                        <form:form id="cancelLeave" name="cancelLeave" class="form-horizontal">
                                
                                <div class="panel-body">
                                    
                                <div  class="text-center btn-danger" id="res" style="display: none">
                                    <div class="msg msg_danger" id="errorMsg"></div>
                                </div>
                                    
                                    <div class="form-group">
                                        <label for="user_code" class="col-sm-3 control-label">User Code</label>
                                        <div class="col-sm-6">
                                            <select name="userCodeList" id="userCodeList" class="form-control">
                                            <c:forEach var="item" items="${empList}">
                                                <c:if test="${item.user_code != 'admin'}">
                                                <option value="${item.user_code}">${item.user_code}</option>
                                                </c:if>
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
                                                aria-pressed="false" onclick="searchAuthLeave();">Search
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
                                                
                                                <th class="min-tablet">Start Date</th>
                                                <th class="min-tablet">End Date</th>                                  
                                                <th class="min-tablet">No of days</th>                                  
                                                <th class="min-tablet">Leave Type</th>                                  
                                                <th class="min-tablet">Status</th>                                  
                                                <th class="min-tablet">User Code</th>                                  
                                                <th class="min-tablet">Action</th>
                                                
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

        <script src="${pageContext.request.contextPath}/resources/js/custom/leave/cancel-leave-datatables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

        <!--Common Function -->
        <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>
        
                 <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>

        <script>
            
              <c:if test="${successMessage!=null && successMessage!=''}">
                                                     customNiftyNoty('success', '${successMessage}');
            </c:if>
            <c:if test="${errorMessage!=null && errorMessage!=''}">
                                                     customNiftyNoty('danger', '${errorMessage}');
            </c:if>
                
                  $("#userCodeList").chosen({
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
                
                
        </script>


    </body>
</html>
