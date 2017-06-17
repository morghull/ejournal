<%-- 
    Document   : software
    Created on : Nov 11, 2016, 11:22:58 AM
    Author     : u27brvz04
--%>
<%@page import="java.sql.*"%>
<%@page import="pgsqlDataController.PgsqlData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
        <title>Розроблене власне програмне забезпечення</title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
        <%
            Integer selectedId = null;
            if (request.getParameter("cur_id") != null && !request.getParameter("cur_id").equals("")) {
                selectedId = Integer.parseInt(request.getParameter("cur_id"));
            }
//getting data
            PgsqlData data = new PgsqlData();
            ResultSet pageRows = data.getRows("software");
        %>
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
                <div style="text-align: center;"><h4>Розроблене власне програмне забезпечення</h4></div>
            </div>
        </div>
        <div class="dictionary-content">
            <table class="singleline">
                <thead>
                    <tr>
                        <td style="width:10%;">№ п\п</td>
                        <td style="width:60%;">Назва програмного забезпечення</td>
                        <td style="width:20%;">Системна назва</td>
                        <td style="width:10%;">Назва схеми</td>
                    </tr>
                </thead>
                <tbody>
                    <%while (pageRows.next()) {%>
                    <tr class="clickable-row<%=((selectedId != null && selectedId == pageRows.getInt("softid")) ? " active\" id=\"current-row\"" : "\"")%>>
                        <td class="text-center"><%= pageRows.getString("numb")%></td>
                        <td class="text-left"><%= pageRows.getString("softname")%></td>
                        <td class="text-left"><%= ("".equals(pageRows.getString("sysname"))) ? "&nbsp" : pageRows.getString("sysname")%></td>
                        <td class="text-left"><%= ("".equals(pageRows.getString("schemaname")) || pageRows.getString("schemaname") == null) ? "&nbsp" : pageRows.getString("schemaname")%></td>
                        <td class="hidden"><%= pageRows.getInt("softid")%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>

        <div id="dialog-form" title="title" class="hidden">
            <p class="validateTips"></p>
            <form>
                <fieldset>
                    <table class="table-unbordered table-layout">
                        <tbody>
                            <tr>
                                <td style="min-width:130px;width:30%" class="text-left"><label for="softname">Назва</label></td>
                                <td style="width:100%"><input type="text" name="softname" id="softname" value="" class="width100perc text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td class="text-left"><label for="sysname">Системна назва</label></td>
                                <td><input type="text" name="sysname" id="sysname" value="" class="width100perc text ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr>
                                <td class="text-left"><label for="schemaname">Назва схеми</label></td>
                                <td><input type="text" name="schemaname" id="schemaname" value="" class="width100perc text ui-widget-content ui-corner-all"></td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- Allow form submission with keyboard without duplicating the dialog button -->
                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
            </form>
        </div>

        <jsp:include page="/jsp/jscript.jsp"/>
        <script type="text/javascript">
 $(function () {
                var mode, id;
                var tbl_nm = "software";
                var dialogForAddOrEdit, dialogForDelete, dialogErrorMessage, form,
                        emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                        softname = $("#softname"),
                        schemaname = $("#schemaname"),
                        sysname = $("#sysname"),
                        allFields = $([]).add(softname).add(schemaname).add(sysname),
                        tips = $(".validateTips");
                var scrollbarWidth = getScrollBarWidth();

                function getScrollBarWidth() {
                    var $outer = $('<div>').css({visibility: 'hidden', width: 100, overflow: 'scroll'}).appendTo('body'),
                            widthWithScroll = $('<div>').css({width: '100%'}).appendTo($outer).outerWidth();
                    $outer.remove();
                    return 100 - widthWithScroll;
                }

                function updateTips(t) {
                    tips
                            .text(t)
                            .addClass("ui-state-highlight");
                }

                function checkLength(o, min, max, message) {
                    if (o.val().length > max || o.val().length < min) {
                        o.addClass("ui-state-error", 250);
                        updateTips(message);
                        o.focus();
                        return false;
                    } else {
                        return true;
                    }
                }

                function checkRegexp(o, regexp, message) {
                    if (!(regexp.test(o.val()))) {
                        o.addClass("ui-state-error", 250);
                        updateTips(message);
                        o.focus();
                        return false;
                    } else {
                        return true;
                    }
                }

                function addRecord() {
                    var valid = true;
                    allFields.removeClass("ui-state-error");
                    valid = valid && checkLength(softname, 3, 50,
                            "Назва програмного забезпечення повинна бути не менш ніж 3 символи та не більше 50");
                    valid = valid && checkRegexp(softname, /^[a-zа-яіґ\"'\s_0-9]+$/i,
                            "Назва програмного забезпечення повинна повинна містити літери англійскої або української мови, пробіли, цифри, символ \" або апостроф");
                    valid = valid && checkLength(sysname, 3, 20,
                            "Системна назва повинна бути не менш ніж 3 символи та не більше 20");
                    valid = valid && checkRegexp(sysname, /^[a-z0-9]+$/i,
                            "Системна назва повинна містити літери англійскої мови або цифри");
                    if (valid) {
                        var rowData = {softid: id,
                            softname: $('input#softname').val(),
                            sysname: $('input#sysname').val(),
                            schemaname: $('input#schemaname').val()};
                        $.post("${pageContext.servletContext.contextPath}/ajaxdata", {
                            mode: mode,
                            tbl_nm: tbl_nm,
                            row_data: JSON.stringify(rowData)
                        }).done(function (data) {
                            var jsonObj = data;
                            var previousHref = window.location.href;
                            var hrefStr = window.location.href.replace(window.location.search, "").replace(/#.*/, "");
                            hrefStr += ((jsonObj !== null) ? "?cur_id=" + jsonObj.id : "");
                            if (previousHref === hrefStr) {
                                window.location.reload();
                            } else {
                                window.location.href = hrefStr;
                            }
                        }).fail(function (xhr, status, error) {
                            $(dialogErrorMessage).find("#error-content").html(decodeURIComponent(xhr.getResponseHeader("error")).replace(/\s*\++\s*/g, " "));
                            $(dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(xhr.getResponseHeader("error_details")).replace(/\s*\++\s*/g, " "));
                            dialogErrorMessage.dialog("open");
                        });
                    }
                    return valid;
                }

                // this creates dialog placeholder for deletion confirmation
                $("body").append(
                        $("<div>")
                        .prop("id", "dialog-confirm-deletion")
                        .prop("title", "Видалити обраний запис?")
                        .append($("<p>").prop("id", "confirm-content")
                                .html("Чи дійсно ви бажаєте видатили обраний запис?</br>Обраний запис буде видалено з БД"))
                        );
                // this creates dialog placeholder for error message        
                $("body").append(
                        $("<div>")
                        .prop("id", "dialog-error-message")
                        .prop("title", "Помилка")
                        .append($("<div>")
                                .append($("<img>")
                                        .prop("src", "${pageContext.servletContext.contextPath}/images/error.png")
                                        .prop("alt", "error")
                                        .css("float", "left")
                                        )
                                .append($("<div>").prop("id", "error-content")
                                        .css("display", "inline-block")
                                        .css("min-height", "50px")
                                        .css("padding-left", "10px")
                                        .css("width", "85%")
                                        )
                                )
                        .append($("<div>").addClass("arrow-down").click(function () {
                            $(this).siblings("#error-details-content").toggle(400);
                        }))
                        .append($("<div>").prop("id", "error-details-content")
                                .css("display", "none")
                                .css("padding-top", "5px")
                                )
                        );
                // this initializes the dialog to add or edit information in table
                dialogForAddOrEdit = $("#dialog-form").dialog({
                    autoOpen: false,
                    height: 400,
                    width: '75%',
                    resizable: false,
                    modal: true,
                    buttons: [{id: "ok",
                            "class": "ui-button",
                            text: "Ok",
                            click: function () {
                                addRecord();
                            }},
                        {id: "cancel",
                            "class": "ui-button",
                            text: "Відмінити",
                            click: function () {
                                $(this).dialog("close");
                            }}
                    ],
                    close: function () {
                        form[0].reset();
                        allFields.removeClass("ui-state-error");
                    }
                });
                // this initializes the dialog to delete information from table
                dialogForDelete = $("#dialog-confirm-deletion").dialog({
                    autoOpen: false,
                    resizable: false,
                    height: "auto",
                    width: 400,
                    modal: true,
                    buttons: [{id: "ok",
                            "class": "ui-button",
                            text: "Так",
                            click: function () {
                                var rowData = {softid: id,
                                    softname: $('input#softname').val(),
                                    sysname: $('input#sysname').val(),
                                    schemaname: $('input#schemaname').val()};
                                $.post("${pageContext.servletContext.contextPath}/ajaxdata", {
                                    mode: mode,
                                    tbl_nm: tbl_nm,
                                    row_data: JSON.stringify(rowData)
                                }).done(function () {
                                    var previousHref = window.location.href;
                                    var hrefStr = window.location.href.replace(window.location.search, "").replace(/#.*/, "");
                                    if (previousHref === hrefStr) {
                                        window.location.reload();
                                    } else {
                                        window.location.href = hrefStr;
                                    }
                                }).fail(function (xhr, status, error) {
                                    $(dialogErrorMessage).find("#error-content").html(decodeURIComponent(xhr.getResponseHeader("error")).replace(/\s*\++\s*/g, " "));
                                    $(dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(xhr.getResponseHeader("error_details")).replace(/\s*\++\s*/g, " "));
                                    dialogErrorMessage.dialog("open");
                                });
                            }},
                        {id: "cancel",
                            "class": "ui-button",
                            text: "Ні",
                            click: function () {
                                $(this).dialog("close");
                            }}
                    ]
                });
                // this initializes the dialog for error message
                dialogErrorMessage = $("#dialog-error-message").dialog({
                    autoOpen: false,
                    resizable: false,
                    height: "auto",
                    width: 400,
                    modal: true,
                    buttons: [{id: "ok",
                            "class": "ui-button",
                            text: "Так",
                            click: function () {
                                $(this).dialog("close");
                            }
                        }]
                });
                // this makes more pleasant look for dialog title close button
                $('button.ui-dialog-titlebar-close').addClass('ui-button').addClass("ui-icon-closethick");

                // this makes an object form, used for reseting while pressing "cancel" button
                form = dialogForAddOrEdit.find("form").on("submit", function (event) {
                    event.preventDefault();
                    //dialogForAddOrEdit.find("#ok").click();
                    addRecord();
                });

                // this initializes click on "Add" button                
                $("#btnAdd").button().on("click", function () {
                    $("#dialog-form").siblings().children("span.ui-dialog-title").html("Додання нового запису");
                    $("#dialog-form").removeClass("hidden");
                    mode = "add";
                    dialogForAddOrEdit.dialog("open");
                });
                // this initializes click on "Edit" button                
                $("#btnEdit").button().on("click", function () {
                    if ($("tr.active").length === 0)
                        return false;
                    $("#dialog-form").siblings().children("span.ui-dialog-title").html("Редагування запису");
                    $("#dialog-form").removeClass("hidden");
                    mode = "edit";
                    id = $("tr.active").children("td.hidden").html();
                    var atc = "0";
                    var filter = {softid: $("tr.active").children("td.hidden").html()};
                    $.getJSON("${pageContext.servletContext.contextPath}/ajaxdata", {atc: atc, tbl_nm: tbl_nm, filter: JSON.stringify(filter)})
                            .done(function (result) {
                                dialogForAddOrEdit.dialog("open");
                                $('input#softname').val(result[0].softname);
                                $('input#sysname').val((result[0].sysname === null) ? "" : result[0].sysname);
                                $('input#schemaname').val((result[0].schemaname === null) ? "" : result[0].schemaname);
                            });
                });
                // this initializes click on "Delete" button              
                $("#btnDel").button().on("click", function () {
                    if ($("tr.active").length === 0)
                        return false;
                    mode = "delete";
                    id = $("tr.active").children("td.hidden").html();
                    dialogForDelete.dialog("open");
                });
                // this scrolls down to active current row if it's present here
                if ($("#current-row").length !== 0)
                    $("html, body").animate({scrollTop: $("#current-row").offset().top}, 500);
            });
        </script>
    </body>
</html>