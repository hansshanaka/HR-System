<%-- 
    Document   : SideMenu
    Created on : Apr 5, 2016, 10:02:06 PM
    Author     : Shanaka
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="mainnav-container">
    <div id="mainnav">
        <!--Menu-->
        <!--================================-->
        <div id="mainnav-menu-wrap">
            <div class="nano">
                <div class="nano-content">
                    <ul id="mainnav-menu" class="list-group">
                        <c:forEach var="menuItemDTO" items="${menuItemList}">
                            <li class="active-sub">
                                <a href="#">
                                    <i class="fa fa-edit"></i>
                                    <span class="menu-title">${menuItemDTO.menuName} </span>
                                    <i class="arrow"></i>
                                </a>
                                <c:set var="subMenuItemList" value="${menuItemDTO.subMenuItemDTO}" scope="request"/>
                                <c:set var="collapseInClass" value="" scope="request"/>

                                <c:if test="${menuItemDTO.menuId==menuParentId}">
                                    <c:set var="collapseInClass" value=" in " scope="request"/>
                                </c:if>
                                <!--Submenu-->
                                <ul class="collapse ${collapseInClass}">
                                    <c:forEach var="subMenuItem" items="${subMenuItemList}">
                                        <c:set var="activeLinkClass" value="" scope="request"/>
                                        <c:if test="${subMenuItem.menuId==subMenuId}">
                                            <c:set var="activeLinkClass" value=" active-link " scope="request"/>
                                        </c:if>
                                        <li class="${activeLinkClass}">
<!--                                            <a onclick="reloadFile('${pageContext.request.contextPath}/${subMenuItem.menuUrl}')">
                                                ${subMenuItem.menuName}</a>-->
                                                <a href="${pageContext.request.contextPath}/${subMenuItem.menuUrl}"> ${subMenuItem.menuName}</a>
                                            
                                        </li>
                                    </c:forEach>

                                </ul>
                            </li>
                        </c:forEach>
                        <!--Menu list item-->

                        <li class="list-divider"></li>

                    </ul>
                </div>
            </div>
        </div>
        <!--================================-->
        <!--End menu-->

    </div>
</nav>
