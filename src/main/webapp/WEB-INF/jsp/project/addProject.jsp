<%-- 
    Document   : addProject
    Created on : Oct 10, 2016, 9:52:14 PM
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tree/tree.css">
              
        <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.css"
              rel="stylesheet">
        
                <!--Bootstrap Table [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/datatables/media/css/dataTables.bootstrap.css"
              rel="stylesheet">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_table/custom.css">
        <style>
            ul, ol {
                list-style: none;
            }
            #accordion1 li.panel{
                margin-bottom: 0px;
            }
        </style>


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
            document.addFood.action = "${pageContext.request.contextPath}/home";
            document.addFood.submit();
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
                                <h1 class="page-header text-overflow">Insert Project Details</h1>
                            </c:when>
                            <c:when test="${recordType=='e'}">
                                <h1 class="page-header text-overflow">Edit Project Details</h1>
                            </c:when>
                        </c:choose>
                            
                        </div>
                        <div class="panel">
                        <form:form id="addPro" name="addPro" class="form-horizontal" method="post">

                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="project_name" class="col-sm-3 control-label">Project Name<span
                                        class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="Project Name" class="form-control input-lg"
                                               id="project_name" name="project_name" value="">
                                        </div>
                                </div> 
                                      
                                <div class="form-group">
                                        <label for="description" class="col-sm-3 control-label">Description</label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="Description" class="form-control input-lg"
                                                   id="description" name="description" value="">
                                        </div>
                                </div> 
                                
                                <div class="form-group">
                                        <label for="client_name" class="col-sm-3 control-label">Client Name<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="Client Name" class="form-control input-lg"
                                                   id="client_name" name="client_name" value="">
                                        </div>
                                </div> 
                                <div class="form-group">
                                    <label for="sdate" class="col-sm-3 control-label">Start Date<span
                                            class="text-danger">*</span></label>
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
                                    <label for="qa_date" class="col-sm-3 control-label">Deploy For QA</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate sdate">
                                                <input type="text" path="qa_date" id="qa_date" name="qa_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="QA Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="uat_date" class="col-sm-3 control-label">Deploy For UAT</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate sdate">
                                                <input type="text" path="uat_date" id="uat_date" name="uat_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="UAT Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="delivary_date" class="col-sm-3 control-label">Deploy For Live</label>
                                    <div class="col-sm-6">
                                        <div id="demo-dp-component">
                                            <div class="input-group date pickadate sdate">
                                                <input type="text" path="delivary_date" id="delivary_date" name="delivary_date"
                                                       class="form-control datePicker_custom"
                                                       placeholder="Dilivery Date"/>
                                                <span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>

                                    <div class="col-sm-6 text-right">
                                        <span class="text-danger">*</span> Mandatory Fields
                                    </div>
                                </div> 
                            </div>
                            
                            <div class="tree">
                                <ul>
                                    <li>
                                        <span><i class="icon-calendar"></i> Assign Development Employees </span>
                                            <ul>
                                                <c:forEach var="dev" items="${devList}">
                                                    <li>
                                                        <span>
                                                            <i class="icon-minus-sign"></i> 
                                                                <input name="devEmp" type="checkbox" id="${dev.user_code}" value="${dev.user_code}"/>
                                                        </span> &ndash; ${dev.use_name}
                                                            </li>
                                                </c:forEach>
                                            </ul>  
                                    </li>
                                </ul>
                            </div>
                            
                            <div class="tree">
                                <ul>
                                    <li>
                                        <span><i class="icon-calendar"></i>Assign UI Employee</span>
                                        <ul>
                                            <c:forEach var="ui" items="${uiList}">
                                                <li>
                                                    <span>
                                                        <i class="icon-minus-sign"></i> 
                                                        <input name="qaEmp" type="checkbox" id="${ui.user_code}" value="${ui.user_code}"/>
                                                    </span> &ndash; ${ui.use_name}
                                                </li>
                                            </c:forEach>
                                        </ul> 
                                    </li>
                                </ul>                                
                            </div>
                            
                            <div class="tree">
                                <ul>
                                    <li>
                                        <span><i class="icon-calendar"></i>Assign QA Employee</span>
                                        <ul>
                                            <c:forEach var="qa" items="${qaList}">
                                                <li>
                                                    <span>
                                                        <i class="icon-minus-sign"></i> 
                                                        <input name="uiEmp" type="checkbox" id="${qa.user_code}" value="${qa.user_code}"/>
                                                    </span> &ndash; ${qa.use_name}
                                                </li>
                                            </c:forEach>
                                        </ul> 
                                    </li>
                                </ul>
                            </div>

                       

                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">
                                
                              <c:choose>
                              <c:when test="${recordType=='i'}">

                                <button class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                    type="submit" value="Save" onclick="return save();">Save </button>
                              </c:when>
                              <c:when test="${recordType=='e'}">
                                <!--<input type="hidden" id="status_id" name="status_id" value=""/>-->
                                <button type="submit" class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                    id="edit" aria-pressed="false" onclick="return edit();" value="Edit">
                                                Edit</button>
                              </c:when>                
                              </c:choose> 
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

    <script src="${pageContext.request.contextPath}/resources/js/custom/project/add-project-form-validator.js"></script>

    <!--chosen.jquery.js [ OPTIONAL ]-->
    <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

    <!--Common Function -->
    <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

    <!--------------Tree---------------->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/treeview.js"></script>
    
        <!--DataTables [ OPTIONAL ]-->        
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/datatables/media/js/dataTables.bootstrap.js"></script>

    
    <!--Bootstrap Datepicker [ OPTIONAL ]-->
    <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
        



    <script>

                                                $(document).on("click", ":submit", function (e) {
                                                    var val = $(this).val();
                                                    $("#addPro").submit(function (event) {
                                                        var action = "";
                                                        if (val === "Save") {
                                                            action = "${pageContext.request.contextPath}/project/saveProject";
                                                            $("#addPro").attr('action', action);
                                                        } else if (val === "Edit") {
                                                            action = "${pageContext.request.contextPath}/project/editProject";
                                                            $("#addPro").attr('action', action);
                                                        }  else {
                                                            alert("" + val);
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
            
            $(function () {
                $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
                $('.tree li.parent_li > span').on('click', function (e) {
                var children = $(this).parent('li.parent_li').find(' > ul > li');
                if (children.is(":visible")) {
                children.hide('fast');
                $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
                } else {
                    children.show('fast');
                    $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
                }
                    e.stopPropagation();
                });
                });
            
                    var optionsStartDate = {
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: "linked",
        startDate: 'Today',
        timePickerIncrement: 30,
        todayHighlight: true,
        clearBtn: true,
    };
            
        $(".sdate").datepicker(optionsStartDate).on('change', function () {});  
        
            var table;
            $(window).on('load', function () {
            table = $('#demo-dt-basic').DataTable();
            });
    </script>
  

</body>
</html>


