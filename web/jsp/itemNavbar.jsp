<%-- 
    Document   : itemNavbar
    Created on : Nov 11, 2016, 9:51:32 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-static-top navbar-default navbar-header-full" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">
            <img alt="Brand" src="${pageContext.servletContext.contextPath}/images/logo.png" style="height:40px;margin-top:-10px;float:left;">
            АС ІСКРА
        </a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <!--<li><a href="${pageContext.servletContext.contextPath}">Головна</a></li>-->
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Довідники<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/dictionaries/software', 'popupSoftware', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100,left=' + (($(window).width() / 2) - (900 / 2))).focus();">Розроблене власне програмне забезпечення</a></li>
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/dictionaries/employees', 'popupEmployees', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100,left=' + (($(window).width() / 2) - (900 / 2))).focus();">Співробітники бюро розробки</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="${pageContext.servletContext.contextPath}/tables/tmp">Задачі на розробку</a></li>
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/dictionaries/test', 'popup', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100,left=' + (($(window).width() / 2) - (900 / 2))).focus();">test</a></li>
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/dictionaries/softscrollable', 'popup', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100, left=' + (($(window).width() / 2) - (900 / 2))).focus();">scrollable example</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Електронний журнал<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/journal', 'popupEjournal', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100,left=' + (($(window).width() / 2) - (900 / 2))).focus();">Журнал</a></li>
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/journal/ejrdok', 'tabEjRdok').focus();">Розпорядчі документи</a></li>
                    <!--<li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/journal/ejrdok', 'popupEjRdok', 'location=0,toolbar=0,menubar=0,scrollbars=1,fullscreen=1').focus();">Розпорядчі документи</a></li>-->
                    <li><a href="#" onClick="window.open('${pageContext.servletContext.contextPath}/journal/ejkiz', 'popupEjKiz', 'location=0,toolbar=0,menubar=0,scrollbars=1,width=900,height=600,top=100,left=' + (($(window).width() / 2) - (900 / 2))).focus();">Вироби</a></li>
                </ul>
            </li>
        </ul>
    </div> 
</nav>
