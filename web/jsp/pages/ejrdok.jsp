<%-- 
    Document   : ejrdok
    Created on : Jun 8, 2017, 10:12:54 AM
    Author     : u27brvz04
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
        <title>Електронний журнал: розпорядчі документи</title>
        <jsp:include page="/jsp/css.jsp"/>
    </head>
    <body>
        <%
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String strCurDate = df.format(new Date());
        %>
        <div class="overlay" id="overlay-wrapper"></div>
        <div style="padding:5px;">
            <div class="btn-toolbar" role="toolbar" aria-label="toolBar">
                <div class="btn-group" role="group" aria-label="iudGroup">
                    <button id="btnAdd" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_add.png" alt="Mountain View" style="height:24px;">
                    </button>
                    <button id="btnEdit" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_edit.png" alt="Mountain View" style="height:24px;">
                    </button>
                    <button id="btnDel" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_del.png" alt="Mountain View" style="height:24px;">
                    </button>
                </div>
                <div class="text-center table-title-caption"><h4>Електронний журнал: розпорядчі документи</h4></div>
                <jsp:include page="/jsp/itemToolbarPageSize.jsp"/>
                <jsp:include page="/jsp/itemToolbarPagination.jsp"/>
            </div>
        </div>
        <div class="table-content">
            <table class="singleline">
                <thead>
                    <tr>
                        <td style="width:1%;" rowspan="2">№ п\п</td>
                        <td colspan="5">Документ</td>
                        <td style="width:1%;" rowspan="2">Основний розпорядчий документ</td>
                        <td style="width:1%;" rowspan="2">Дата виконання по документу</td>
                        <td style="width:1%;" rowspan="2">Основне замовлення</td>
                        <td style="width:50%;" rowspan="2">Примітки</td>
                        <td style="width:10%;" rowspan="2">Посилання</td>
                    </tr>
                    <tr>
                        <td style="width:1%;">Код</td>
                        <td style="width:1%;">Номер</td>
                        <td style="width:1%;">Дата</td>
                        <td style="width:50%;">Назва</td>
                        <td style="width:1%;">Шифр</td>
                    </tr>
                </thead>
                <tbody id="content-table-body">

                </tbody>
            </table>
        </div>
        <div id="dialog-form" title="title" class="hidden"> <!--class="hidden"-->
            <p class="validateTips"></p>
            <form id="formdata">
                <fieldset>
                    <div id="fieldset-rd">
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="rdk" class="input-group-label width60px">Код</label>
                                <input id="rdk" name="rdk" type="text" maxlength="1" class="form-control" placeholder="Введіть код або скористайтесь допомогою..."
                                       autocomplete="on" atc-table-name="zapas.rdt" atc-field-name="rdtk">
                                <span class="input-group-btn">
                                    <button class="btn btn-secondary btn-custom-help" type="button">...</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="rdn" class="input-group-label width60px">Номер</label>
                                <input id="rdn" name="rdn" type="text" maxlength="4" class="form-control" placeholder="Введіть номер...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group date" id="datetimepicker" style="width:24.1em;width:calc(17em + 60px + 39px);">
                                <label for="rdd" class="input-group-label width60px">Дата</label>
                                <input id="rdd" name="rdd" type="text" class="form-control" style="position: relative; z-index: 100;" readonly placeholder="Оберіть дату...">
                                <span id="rdd-help-btn" class="input-group-addon help-btn">
                                    <span class="glyphicon glyphicon-calendar">
                                    </span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width100perc">
                                <label for="nazz" class="input-group-label width60px">Назва</label>
                                <input id="nazz" name="nazz" type="text" maxlength="90" class="form-control" placeholder="Введіть назву...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="rdsh" class="input-group-label width60px">Шифр</label>
                                <input id="rdsh" name="rdsh" type="text" maxlength="5" class="form-control" placeholder="Введіть шифр або скористайтесь допомогою...">
                                <span class="input-group-btn">
                                    <button class="btn btn-secondary btn-custom-help" type="button">...</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div id="fieldset-ord">
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="ordk" class="input-group-label width60px">Код</label>
                                <input id="ordk" name="ordk" type="text" maxlength="1" class="form-control" placeholder="Введіть код або скористайтесь допомогою...">
                                <span class="input-group-btn">
                                    <button class="btn btn-secondary btn-custom-help" type="button">...</button>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="ordn" class="input-group-label width60px">Номер</label>
                                <input id="ordn" name="ordn" type="text" maxlength="4" class="form-control" placeholder="Введіть номер...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group date" id="datetimepicker" style="width:24.1em;width:calc(17em + 60px + 39px);">
                                <label for="ordd" class="input-group-label width60px">Дата</label>
                                <input id="ordd" name="ordd" type="text" class="form-control" style="position: relative; z-index: 100;" readonly placeholder="Оберіть дату...">
                                <span id="ordd-help-btn" class="input-group-addon help-btn">
                                    <span class="glyphicon glyphicon-calendar">
                                    </span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group date" id="datetimepicker" style="width:calc(17em + 215px + 39px);">
                            <label for="dvd" class="input-group-label" style="width:215px;">Дата виконання по документу</label>
                            <input id="dvd" name="dvd" type="text" class="form-control" style="position: relative; z-index: 100;" readonly placeholder="Оберіть дату...">
                            <span id="dvd-help-btn" class="input-group-addon help-btn">
                                <span class="glyphicon glyphicon-calendar">
                                </span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group width600px">
                            <label for="nzak" class="input-group-label" style="width:215px;">Основне замовлення</label>
                            <input id="nzak" name="nzak" type="text" maxlength="9" class="form-control" placeholder="Введіть номер або скористайтесь допомогою...">
                            <span class="input-group-btn">
                                <button class="btn btn-secondary btn-custom-help" type="button">...</button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group width100perc">
                            <label for="prim" class="input-group-label width60px">Примітки</label>
                            <input id="prim" name="prim" type="text" maxlength="100" class="form-control" placeholder="Введіть примітки...">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group width100perc">
                            <label for="uplfiles" class="input-group-label width100px">Прикріпленний файл</label>
                        </div>
                    </div>
                    <!-- Allow form submission with keyboard without duplicating the dialog button -->
                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
            </form>
        </div>
        <jsp:include page="/jsp/jscript.jsp"/>
        <jsp:include page="/jsp/js/initDialogPlaceholders.jsp"/>
        <jsp:include page="/jsp/js/initClickableTableRow.jsp"/>
        <script type="text/javascript">
            $(function () {
                var mode, id;
                var dialogForAddOrEdit, dialogForDelete, dialogErrorMessage, form,
                        emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                        rdk = $("#rdk"),
                        rdn = $("#rdn"),
                        rdd = $("#rdd"),
                        nazz = $("#nazz"),
                        rdsh = $("#rdsh"),
                        ordk = $("#ordk"),
                        ordn = $("#ordn"),
                        ordd = $("#ordd"),
                        dvd = $("#dvd"),
                        nzak = $("#nzak"),
                        prim = $("#prim");
                $.iskra = $.fn;
                $.iskra.allFields = $([]).add(rdk).add(rdn).add(rdd).add(nazz)
                        .add(rdsh).add(ordk).add(ordn).add(ordd).add(nzak)
                        .add(prim);
                $.iskra.pageSize = 15;
                var pageSize = 15, pageNumber = 1, totalRowCount;
                var tableName = "xxx.ejrdok";

                function addRecord() {
                    var valid = true;
                    resetTips();
                    $.iskra.allFields.removeClass("ui-state-error");
                    valid = valid && checkLength(rdk, 1, 1,
                            "Код розпорядчого документу не повинен бути пустим!");
                    valid = valid && checkLength(rdn, 1, 4,
                            "Номер розпорядчого документу не повинен бути пустим!");
                    valid = valid && checkLength(nzak, 1, 9,
                            "Номер основного фінансового замовлення не повинен бути пустим!");
                    /*valid = valid && checkRegexp(sysname, /^[a-zа-яіґ\"'\s_0-9]+$/i,
                     "Назва програмного забезпечення повинна повинна містити літери англійскої або української мови, пробіли, цифри, символ \" або апостроф");
                     valid = valid && checkLength(sysname, 3, 20,
                     "Системна назва повинна бути не менш ніж 3 символи та не більше 20");
                     valid = valid && checkRegexp(sysname, /^[a-z0-9]+$/i,
                     "Системна назва повинна містити літери англійскої мови або цифри");*/
                    if (valid) {
                        // Create an FormData object
                        //console.log();
                        var data = new FormData($("#formdata")[0]);
                        // If you want to add an extra field for the FormData
                        data.append("q_mode", mode);
                        data.append("q_table_name", tableName);
                        data.append("q_id", $("tr.active").attr("data-id"));

                        $.ajax({
                            type: "post",
                            enctype: 'multipart/form-data',
                            url: "${pageContext.servletContext.contextPath}/servlets/ajax/ejrdokCrud",
                            data: data,
                            processData: false, // Important!
                            contentType: false,
                            cache: false,
                            timeout: 600000,
                            beforeSend: function () {
                                $("#overlay-wrapper").fadeIn("fast");
                            },
                            success: function (data, status, xhr) {
                                if ($("#currentId").length) {
                                    $("#currentId").empty().append(xhr.getResponseHeader("ret_id"));
                                } else {
                                    $("body").append($("<span>").prop("id", "currentId").attr("new-one", "true").addClass("hidden").append(xhr.getResponseHeader("ret_id")));
                                }
                                var newRowNumber = xhr.getResponseHeader("ret_rn");
                                pageNumber = ((newRowNumber - (newRowNumber % $.iskra.pageSize)) / $.iskra.pageSize) + (((newRowNumber % $.iskra.pageSize) === 0) ? 0 : 1);
                                $.iskra.dialogForAddOrEdit.dialog("close");
                                console.log("addRecord() SUCCESS: ", "retId:", xhr.getResponseHeader("ret_id"), "; retRowNumber:", xhr.getResponseHeader("ret_rn"));
                                getPage();
                                getTotalRowCount();
                                //$("html, body").animate({scrollTop: $("tr.active").offset().top}, 500);
                            },
                            error: function (xhr, status, error) {
                                $($.iskra.dialogErrorMessage).find("#error-content").html(stringFormat(decodeURIComponent(xhr.getResponseHeader("error")).replace(/\s*\++\s*/g, " ")));
                                $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(xhr.getResponseHeader("error_details")).replace(/\s*\++\s*/g, " "));
                                $.iskra.dialogErrorMessage.dialog("open");
                                console.log("addRecord() ERROR : ", error);
                            },
                            complete: function () {
                                $("#overlay-wrapper").fadeOut("fast");
                            }
                        });
                    }
                    return valid;
                }

                // this initializes the dialog to add or edit information in table
                $.iskra.dialogForAddOrEdit = $("#dialog-form").dialog({
                    autoOpen: false,
                    height: "auto",
                    width: "75%",
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
                        $.iskra.form[0].reset();
                        $.iskra.allFields.removeClass("ui-state-error");
                    }
                });

                // this makes an object form, used for reseting while pressing "cancel" button
                $.iskra.form = $.iskra.dialogForAddOrEdit.find("form").on("submit", function (event) {
                    event.preventDefault();
                    addRecord();
                });

                // this initializes the dialog to delete information from table
                $.iskra.dialogForDelete = $("#dialog-confirm-deletion").dialog({
                    autoOpen: false,
                    resizable: false,
                    height: "auto",
                    width: 400,
                    modal: true,
                    buttons: [{id: "ok",
                            "class": "ui-button",
                            text: "Так",
                            click: function () {
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.servletContext.contextPath}/servlets/ajax/ejrdokCrud",
                                    data: {
                                        q_table_name: tableName,
                                        q_mode: mode,
                                        q_id: $("tr.active").attr("data-id")
                                    },
                                    timeout: 600000,
                                    beforeSend: function () {
                                        $("#overlay-wrapper").fadeIn("fast");
                                    },
                                    success: function (data, status, xhr) {
                                        $.iskra.dialogForDelete.dialog("close");
                                        console.log("deleteRecord() SUCCESS ");
                                        getPage();
                                        getTotalRowCount();
                                    },
                                    error: function (xhr, status, error) {
                                        $($.iskra.dialogErrorMessage).find("#error-content").html(stringFormat(decodeURIComponent(xhr.getResponseHeader("error")).replace(/\s*\++\s*/g, " ")));
                                        $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(xhr.getResponseHeader("error_details")).replace(/\s*\++\s*/g, " "));
                                        $.iskra.dialogErrorMessage.dialog("open");
                                        console.log("addRecord() ERROR : ", error);
                                    },
                                    complete: function () {
                                        $("#overlay-wrapper").fadeOut("fast");
                                    }
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
                $.iskra.dialogErrorMessage = $("#dialog-error-message").dialog({
                    autoOpen: false,
                    resizable: false,
                    height: "auto",
                    width: 500,
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

                $.iskra.setInputDefaults = function () {
                    //set defaults
                    var d = $.datepicker.parseDate("dd.mm.yy", "<%=strCurDate%>");
                    $("#dialog-form").find("#rdd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#ordd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#dvd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                }

                $("#rdd").datepicker();
                $("#rdd-help-btn").click(function () {
                    $("#rdd").datepicker("show");
                });
                $("#ordd").datepicker();
                $("#ordd-help-btn").click(function () {
                    $("#ordd").datepicker("show");
                });
                $("#dvd").datepicker();
                $("#dvd-help-btn").click(function () {
                    $("#dvd").datepicker("show");
                });
                
                getPage();
                function getPage() {
                    var currentId = ($("#currentId").length) ? parseInt($("#currentId").text()) : -1;
                    if ($("#currentId").length && $("#currentId").attr("new-one") === "true") {
                        $("#currentId").attr("new-one", "false");
                    }
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/ajax/ejrdokGetPage",
                        dataType: "json",
                        data: {
                            q_table_name: tableName,
                            q_page_size: $.iskra.pageSize,
                            q_page_number: pageNumber
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            $("#overlay-wrapper").fadeIn("fast");
                        },
                        success: function (data, status, xhr) {
                            var tableBody = $("#content-table-body");
                            var rowNumber = $.iskra.pageSize * (pageNumber - 1);
                            tableBody.empty();
                            $.each(data.page, function (key, val) {
                                rowNumber++;
                                tableBody.append($("<tr>").addClass("clickable-row").attr("data-id", val.idd).addClass((val.idd === currentId) ? "active" : "")
                                        .append($("<td>").append(rowNumber).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.rdk)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.rdn)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.rdd)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.nazz)).addClass("text-left"))
                                        .append($("<td>").append(stringFormat(val.rdsh)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.ordk) + "<br/>" + stringFormat(val.ordn)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.dvd)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.nzak)).addClass("text-center"))
                                        .append($("<td>").append(stringFormat(val.prim)).addClass("text-left"))
                                        .append($("<td>"))
                                        );
                            });
                            //redrawPagination();
                            console.log("getPage() SUCCESS : ", data);
                        },
                        error: function (xhr, status, error) {
                            $($.iskra.dialogErrorMessage).find("#error-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error"))).replace(/\s*\++\s*/g, " "));
                            $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error_details"))).replace(/\s*\++\s*/g, " "));
                            $.iskra.dialogErrorMessage.dialog("open");
                            console.log("getPage() ERROR : ", error);
                        },
                        complete: function () {
                            $("#overlay-wrapper").fadeOut("fast");
                        }
                    });
                }
                getTotalRowCount();
                function getTotalRowCount() {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/ajax/getRowCount",
                        dataType: "json",
                        data: {
                            q_table_name: tableName
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            $("#toolbar-pagination-list").empty();
                            $("#toolbar-pagination-list").addClass("overlay-pagination");
                        },
                        success: function (data, status, xhr) {
                            totalRowCount = data.total_row_count;
                            $("#toolbar-pagination-list").removeClass("overlay-pagination");
                            redrawPagination();
                            console.log("getTotalRowCount() SUCCESS : ", data);
                        },
                        error: function (xhr, status, error) {
                            $($.iskra.dialogErrorMessage).find("#error-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error"))).replace(/\s*\++\s*/g, " "));
                            $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error_details"))).replace(/\s*\++\s*/g, " "));
                            $.iskra.dialogErrorMessage.dialog("open");
                            console.log("getTotalRowCount() ERROR : ", error);
                        },
                        complete: function () {

                        }
                    });
                }
                //this prevents native refreshing after pressing F5 button, and makes it customized
                function disableF5(e) {
                    if ((e.which || e.keyCode) === 116 || ((e.which || e.keyCode) === 82) && e.ctrlKey) {
                        e.preventDefault();
                        getPage();
                    }
                }
                $(document).ready(function () {
                    $(document).on("keydown", disableF5);
                });

                //this initializes choosing of page size
                $("#toolbar-page-sizes-list")
                        .children("li").click(function (e) {
                    e.preventDefault();
                    $("#page-size").text($(this).text());
                    $.iskra.pageSize = parseInt($(this).text());
                    pageNumber = 1;
                    getPage();
                    getTotalRowCount();
                });

                function redrawPagination() {
                    $("#toolbar-pagination-list").empty();
                    var totalPagesCount = ((totalRowCount - (totalRowCount % $.iskra.pageSize)) / $.iskra.pageSize) + (((totalRowCount % $.iskra.pageSize) === 0) ? 0 : 1);
                    var paginationDiapazonSize = (totalPagesCount < 5) ? totalPagesCount : 5;
                    var paginationMin, paginationMax;
                    if (totalPagesCount < 5) {
                        paginationMin = 1;
                        paginationMax = paginationDiapazonSize;
                    } else {
                        paginationMin = (pageNumber <= (paginationDiapazonSize - 1) / 2) ? 1 : (pageNumber >= totalPagesCount - ((paginationDiapazonSize - 1) / 2)) ? totalPagesCount - paginationDiapazonSize + 1 : pageNumber - ((paginationDiapazonSize - 1) / 2);
                        paginationMax = (paginationMin + paginationDiapazonSize - 1 >= totalPagesCount) ? totalPagesCount : paginationMin + paginationDiapazonSize - 1;
                    }
                    $("#toolbar-pagination-list")
                            .append($("<li>")
                                    .append($("<a>")
                                            .attr("aria-label", "Previous")
                                            .append($("<span>")
                                                    .attr("aria-hidden", "true")
                                                    .text("«")
                                                    )
                                            .append($("<span>")
                                                    .addClass("sr-only")
                                                    .text("Previous")
                                                    )
                                            )
                                    );
                    for (var i = paginationMin; i <= paginationMax; i++) {
                        $("#toolbar-pagination-list")
                                .append($("<li>")
                                        .addClass((i === pageNumber) ? "active" : "")
                                        .append($("<a>")
                                                .text(i)
                                                )
                                        );
                    }
                    $("#toolbar-pagination-list")
                            .append($("<li>")
                                    .append($("<a>")
                                            .attr("aria-label", "Next")
                                            .append($("<span>")
                                                    .attr("aria-hidden", "true")
                                                    .text("»")
                                                    )
                                            .append($("<span>")
                                                    .addClass("sr-only")
                                                    .text("Next")
                                                    )
                                            )
                                    );
                    //this initializes choosing of page number            
                    $("#toolbar-pagination-list")
                            .children("li").click(function (e) {
                        e.preventDefault();
                        if ($(this).is(":first-child"))
                            pageNumber = (pageNumber - 1 < 1) ? 1 : pageNumber - 1;
                        else if ($(this).is(":last-child"))
                            pageNumber = (pageNumber + 1 > totalPagesCount) ? totalPagesCount : pageNumber + 1;
                        else
                            pageNumber = parseInt($(this).text());
                        redrawPagination();
                        getPage();
                    });
                }
            });
        </script>
        <jsp:include page="/jsp/js/initCrudButtons.jsp"/>
        <jsp:include page="/jsp/js/initAutocomplete.jsp"/>
    </body>
</html>
