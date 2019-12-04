<%-- 
    Document   : home
    Created on : Apr 5, 2016, 9:17:28 PM
    Author     : Shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

        <!--SCRIPT-->
        <!--=================================================-->

        <!--Page Load Progress Bar [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/plugins/pace/pace.min.js"></script>
    </head>

    <body>
        <div id="container" class="effect mainnav-lg">

            <!--NAVBAR-->
            <!--===================================================-->
            <jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
                <!--===================================================-->
                <!--END NAVBAR-->

                <div class="boxed" id="content_aria">

                    <!--CONTENT CONTAINER-->
                    <!--===================================================-->
                    <div id="content-container">
                        <!--Page Title-->
                        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                        <div id="page-title">
                            <h1>Welcome to Human Resource Management System (HRS)</h1>
                        </div>

                        <!--End page title-->

                        <!--Page content-->

                        <div class="panel" >
                            <!--Input Size-->

                            <!--End Input Size-->

                        </div>
                        <!--End page content-->
                    </div>

                    <!--END CONTENT CONTAINER-->

                    <!--MAIN NAVIGATION-->

                <jsp:include page="/WEB-INF/jsp/common/sideMenu.jsp"></jsp:include>

                    <!--END MAIN NAVIGATION-->
                </div>

                <!-- FOOTER -->

            <jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>

                <!-- END FOOTER -->

                <!-- SCROLL TOP BUTTON -->

                <button id="scroll-top" class="btn"><i class="fa fa-chevron-up"></i></button>

            </div>

            <!-- END OF CONTAINER -->

            <!--jQuery [ REQUIRED ]-->
            <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>

        <!--BootstrapJS [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <!--Nifty Admin [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/nifty.min.js"></script>

        <!--Morris.js [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/morris-js/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/plugins/morris-js/raphael-js/raphael.min.js"></script>

        <script>
            function reloadFile(path) {
                $("#progress").show();

                $.ajax({
                    url: path,
                    type: "GET",
                    success: function (response) {
                        $("#progress").hide();
                        $("#content_aria").html(response);
                    },
                    error: function () {
                        $("#progress").hide();
                        alert("An Error Occurred");
                    }
                });
            }

//            $(document).ready(function () {
//                window.location="${pageContext.request.contextPath}/home";
//            });
        </script>
    </body>
</html>

