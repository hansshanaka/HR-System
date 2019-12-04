<%--
  Created by IntelliJ IDEA.
  User: Shanaka
  Date: Apr 5, 2016, 9:25:06 PM
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${TITLE}</title>

    <!--STYLESHEET-->
    <!--=================================================-->

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

    <div class="boxed">

        <!--CONTENT CONTAINER-->
        <!--===================================================-->
        <div id="content-container">

            <!--Page Title-->
            <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
            <div id="page-title">
                <h1 class="page-header text-overflow"></h1>

                <!--Searchbox-->
                <div class="searchbox">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search..">
            <span class="input-group-btn">
            <button class="text-muted" type="button"><i class="fa fa-search"></i></button>
            </span></div>
                </div>
            </div>

            <!--End page title-->

            <!--Page content-->

            <div class="panel">
                <!--Input Size-->

                <h1>Not found 404</h1>

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
</body>
</html>

