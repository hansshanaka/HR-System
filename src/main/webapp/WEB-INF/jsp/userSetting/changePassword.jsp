<%-- 
    Document   : changePassword
    Created on : Sep 11, 2016, 10:14:13 AM
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
                document.chPassword.action = "${pageContext.request.contextPath}/home";
                document.chPassword.submit();
            }
        </script>

    </head>

    <body>
        <div id="container" class="effect mainnav-lg">
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <div class="boxed">
                    <div id="content-container">
                        <div id="page-title">
                            <h1 class="page-header text-overflow">User Password Reset</h1>
                        </div>
                        <div class="panel">
                            <form id="chPassword" name="chPassword" class="form-horizontal" method="post">
                               
                                <div class="panel-body">
                                    <input type="hidden" id="user_code" name="user_code" value="${userDTO.user_code}" />
                                    <div class="form-group">
                                        <label for="password" class="col-sm-3 control-label">New Password<span
                                                class="text-danger">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="password" placeholder="password" class="form-control input-lg"
                                                   id="password" name="password">
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
                                            <button type="submit" class="btn btn-info btn-labeled fa fa-save fa-2x focus"
                                                    id="save" aria-pressed="false" onclick="return Reset();" value="Save">
                                                Save</button>

                                            <button class="btn btn-default btn-active-mint btn-labeled fa fa-times fa-2x"
                                               id="close" type="button" onclick="closePage();" style="">Close</button>


                                        </div>
                                    </div>
                                </div>
                                
                            </form>
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

        <script src="${pageContext.request.contextPath}/resources/js/custom/user/passwordReset-datatables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/custom/user/user-password-reset-validator.js"></script>

        <!--Common Function -->
        <script src="${pageContext.request.contextPath}/resources/js/custom/common-function.js"></script>

        <script>
            
        $(document).on("click", ":submit", function (e) {
        var val = $(this).val();
        $("#chPassword").submit(function (event) {
            var action = "";
            if (val === "Save") {
                    action = "${pageContext.request.contextPath}/userSetting/updateUser";
                    $("#chPassword").attr('action', action);                
            } else {
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
                

                
                
        </script>


    </body>
</html>


