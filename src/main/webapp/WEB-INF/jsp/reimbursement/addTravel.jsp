<%-- 
    Document   : Travel
    Created on : Oct 9, 2016, 8:49:04 PM
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
            document.addTravel.action = "${pageContext.request.contextPath}/home";
            document.addTravel.submit();
            }
        </script>

 
    </head>

    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                            <h1 class="page-header text-overflow">Insert Travel Reimbursement Details</h1>                      
                        </div>
                        
                        <div class="panel">
                        <form:form id="addTravel" name="addTravel" class="form-horizontal" enctype="multipart/form-data"  method="post">

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
                                        <label for="amount" class="col-sm-3 control-label">Bill Amount<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="Bill Amount" class="form-control input-lg"
                                                   id="amount" name="amount" value="">
                                        </div>
                                </div> 
                                    
                                <div class="form-group">
                                        <label for="file" class="col-sm-3 control-label">Bill Image<span
                                            class="text-danger">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="file" placeholder="file" class="form-control input-lg"
                                            id="file" name="file" value="" onchange="ValidateSingleInput(this);">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                        <label for="start" class="col-sm-3 control-label">From<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="From" class="form-control input-lg"
                                                   id="start" name="start" value="">
                                        </div>
                                </div> 
                                
                                <div class="form-group">
                                        <label for="end" class="col-sm-3 control-label">To<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="To" class="form-control input-lg"
                                                   id="end" name="end" value="">
                                        </div>
                                </div> 
                                
                                <div class="form-group">
                                        <label for="distance" class="col-sm-3 control-label">Distance(Km)<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" placeholder="Distance" class="form-control input-lg"
                                                   id="distance" name="distance" value="">
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
                                
                              <c:choose>
                              <c:when test="${recordType=='i'}">

                                <button class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                    type="submit" value="Save" >Save </button>
                              </c:when>
                              <c:when test="${recordType=='e'}">
                                <button type="submit" class="btn btn-info btn-lg btn-labeled fa fa-save fa-lg"
                                    id="edit" aria-pressed="false" value="Edit">
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

    <script src="${pageContext.request.contextPath}/resources/js/custom/reimbursement/add-travel-form-validator.js"></script>

    <!--chosen.jquery.js [ OPTIONAL ]-->
    <script src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.js"></script>

    <!--Common Function -->
    <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

    <!--------------Tree---------------->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/treeview.js"></script>



    <script>

                                                $(document).on("click", ":submit", function (e) {
                                                    var val = $(this).val();
                                                    var selected = document.getElementById("file").files.length > 0;
                                                        if (selected) {
                                                    $("#addTravel").submit(function (event) {
                                                        var action = "";
                                                        if (val === "Save") {
                                                            action = "${pageContext.request.contextPath}/travelReimb/saveTravel";
                                                            $("#addTravel").attr('action', action);
                                                        } else if (val === "Edit") {
                                                            action = "${pageContext.request.contextPath}/travelReimb/editTravel";
                                                            $("#addTravel").attr('action', action);
                                                        }  else {
                                                            alert("System Error" + val);
                                                            event.preventDefault();
                                                        }
                                                    });
                                                    }else {
                                                            e.preventDefault();
                                                            alert("Please Select Sacaned Image")
                                                        }
                                                });

        <c:if test="${successMessage!=null && successMessage!=''}">
                                                customNiftyNoty('success', '${successMessage}');
        </c:if>
        <c:if test="${errorMessage!=null && errorMessage!=''}">
                                                customNiftyNoty('danger', '${errorMessage}');
        </c:if>
    </script>
  

</body>
</html>


