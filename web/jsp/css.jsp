<%-- 
    Document   : css
    Created on : Nov 11, 2016, 9:21:48 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.ico" type="image/vnd.microsoft.icon" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min-3.1.1.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css">
<!-- jquery-ui theme -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css">

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/navbar.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/table.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.customs.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.customs.css">
<style type="text/css">
    .ui-widget-header .ui-icon {
        background-image: url("${pageContext.servletContext.contextPath}/images/ui-icons_444444_256x240.png");
    }
    .ui-icon, .ui-widget-content .ui-icon {
        background-image: url("${pageContext.servletContext.contextPath}/images/ui-icons_444444_256x240.png");
    }
    .overlay {
        background: rgba(0, 0, 0, 0.05) url("${pageContext.servletContext.contextPath}/images/loading.gif") no-repeat center;
    }
    .ajv-input[valid-status="valid"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/valid.svg");
        transition: 3s ease-out;
    }
    .ajv-input[valid-status="invalid"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/invalid.svg");
    }
    .ajv-input[valid-status="pending"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/loading.gif");
    }
</style>