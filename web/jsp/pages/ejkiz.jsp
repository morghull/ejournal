<%-- 
    Document   : ejkiz
    Created on : Jun 8, 2017, 10:13:12 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
        <title>Електронний журнал: вироби</title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
        <div style="padding:5px;">
            <div class="btn-toolbar" role="toolbar" aria-label="toolBar">
                <div class="btn-group" role="group" aria-label="iudGroup">
                    <button id="btnAdd" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_add.png" alt="Mountain View" style="width:24px;height:24px;">
                    </button>
                    <button id="btnEdit" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_edit.png" alt="Mountain View" style="width:24px;height:24px;">
                    </button>
                    <button id="btnDel" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_del.png" alt="Mountain View" style="width:24px;height:24px;">
                    </button>
                </div>
                <div style="text-align: center;"><h4>Електронний журнал: вироби</h4></div>
            </div>
        </div>
    </body>
</html>
