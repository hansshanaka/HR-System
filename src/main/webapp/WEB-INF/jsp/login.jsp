<%-- 
    Document   : login
    Created on : Mar 31, 2016, 1:24:48 PM
    Author     : Shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MI Synergy -HR</title>
        
        <!--Browser Icon -->
        <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/img/icon.png" />

        <!--Bootstrap Stylesheet [ REQUIRED ]-->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

        <!--Nifty Stylesheet [ REQUIRED ]-->
        <link href="${pageContext.request.contextPath}/resources/css/nifty.min.css" rel="stylesheet">

        <!--Font Awesome [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/plugins/font-awesome/css/font-awesome.min.css"
              rel="stylesheet">

        <!--Switchery [ OPTIONAL ]-->
        <link href="${pageContext.request.contextPath}/resources/css/themes/type-c/theme-light.css" rel="stylesheet">

        <!--SCRIPT-->
    </head>
    <body>
        <div id="container" class="cls-container">
            <!-- BACKGROUND IMAGE -->
            <div id="bg-overlay" class="bg-img img-balloon"></div>
            <!-- HEADER -->

            <div class="cls-header cls-header-lg">
                <div class="cls-brand"><a class="box-inline" href="index.html">
                        <!-- <img alt="Nifty Admin" src="img/logo.png" class="brand-icon"> -->
                        <span class="brand-title">Human Resource Management System<span class="text-thin">(HRS)</span></span> </a></div>
            </div>

            <!-- LOGIN FORM -->
            <div class="cls-content">
                <div class="cls-content-sm panel">
                    <div class="panel-body">
                        <p class="pad-btm">Sign In to your account</p>
                        <c:if test="${not empty err}">
                            <div>
                                <div class="alert alert-danger">${err}</div>
                            </div>
                        </c:if>
                        <form:form id="login" name="login" method="POST" modelAttribute="loginDTO" action="login.do">

                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                    <input type="text" class="form-control" placeholder="Username" name="userId" id="userId">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-asterisk"></i></div>
                                    <input type="password" class="form-control" placeholder="Password" name="loginPassword"
                                           id="loginPassword">
                                </div>
                            </div>
                            <div class="row">                                
                                <div class="col-xs-4">
                                    <div class="form-group text-right">
                                        <button class="btn btn-success text-uppercase" type="submit">Sign In</button>
                                    </div>
                                </div>
                            </div>
                           
                        </form:form>
                    </div>
                </div>
            
            </div>
        </div>

        <!-- END OF CONTAINER -->

        <!--JAVASCRIPT-->

        <!--jQuery [ REQUIRED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>

        <!--BootstrapJS [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <!--Nifty Admin [ RECOMMENDED ]-->
        <script src="${pageContext.request.contextPath}/resources/js/nifty.min.js"></script>
        
        <!--Bootstrap Validator [ OPTIONAL ]-->
        <script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-validator/bootstrapValidator.js"></script>
        
        <script src="${pageContext.request.contextPath}/resources/js/custom/login-form-validator.js"></script>
        
    </body>
</html>

