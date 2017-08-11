<%-- 
    Document   : css
    Created on : Nov 11, 2016, 9:21:48 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String version = application.getInitParameter("pluginVersion");
%>
<!--<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.ico" type="image/vnd.microsoft.icon" />-->
<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.ico?<%=version%>" type="image/vnd.microsoft.icon"/>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min-3.1.1.css">
<!-- Optional theme -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css">
<!-- jquery-ui theme -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css">

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css?<%=version%>">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/custom.css?<%=version%>">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/navbar.css?<%=version%>">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/table.css?<%=version%>">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.customs.css?<%=version%>">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.customs.css?<%=version%>">
<style type="text/css">
    .ui-widget-header .ui-icon {
        background-image: url("${pageContext.servletContext.contextPath}/images/ui-icons_444444_256x240.png");
    }
    .ui-icon, .ui-widget-content .ui-icon {
        background-image: url("${pageContext.servletContext.contextPath}/images/ui-icons_444444_256x240.png");
    }
    .overlay {
        background: rgba(0, 0, 0, 0.05) url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>") no-repeat center;
    }
    .overlay-pagination {
        background: rgba(0, 0, 0, 0.00) url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>") no-repeat 10px 7px / contain;
        background-size: 20px;
    }
    .ui-tablehelp-overlay {
        background: rgba(0, 0, 0, 0.05) url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>") no-repeat center;
        background-size: 50px;
    }
    .ui-autocomplete-wait {
        background: url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>") 6px center no-repeat #fff;
        background-size: 20px;
    }
    .ui-ajv-input[valid-status="valid"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/valid.svg");
        transition: 0.2s ease-out;
    }
    .ui-ajv-input[valid-status="invalid"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/invalid.svg");
        transition: 0.2s ease-out;
    }
    .ui-ajv-input[valid-status="pending"][ajv-icon="true"] {
        background-image: url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>");
        transition: 0.2s ease-out;
    }
    .uf-file {
        background: rgba(0, 0, 0, 0.00) url("${pageContext.servletContext.contextPath}/images/24px_png_doc.png?<%=version%>") no-repeat -2px -1px;
    }
    .uf-viewer-placeholder {
        background: rgba(0, 0, 0, 0.1) url("${pageContext.servletContext.contextPath}/images/loading.gif?<%=version%>") no-repeat center;
    }
</style>