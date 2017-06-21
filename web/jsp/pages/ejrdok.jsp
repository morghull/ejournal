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
        <jsp:include page="/jsp/js/jscript.jsp"/>
        <script type="text/javascript">
            $(function () {
                var emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
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
                        .add(rdsh).add(ordk).add(ordn).add(ordd).add(dvd).add(nzak)
                        .add(prim);
                $.iskra.pageSize = 15;
                $.iskra.pageNumber = 1;
                $.iskra.totalRowCount = 0;
                $.iskra.tableName = "xxx.ejrdok";
                $.iskra.mode = "";
                $.iskra.id = "";
                $.iskra.servletUrlPatternCrud = "ejrdokCrud";
                $.iskra.servletUrlPatternGetPage = "ejrdokGetPage";
                $.iskra.pageSizeListPlaceholder = $("#toolbar-page-sizes-list");
                $.iskra.paginationListPlaceholder = $("#toolbar-pagination-list");

                var options = {
                    allFields: $([]).add(rdk).add(rdn).add(rdd).add(nazz)
                            .add(rdsh).add(ordk).add(ordn).add(ordd).add(dvd).add(nzak)
                            .add(prim),
                    pageSize: 15,
                    pageNumber: 1,
                    totalRowCount: 0,
                    tableName: "xxx.ejrdok",
                    mode: "",
                    id: "",
                    urlGetPageServlet: "${pageContext.servletContext.contextPath}/servlets/ajax/ejrdokGetPage",
                    urlCrudServlet: "${pageContext.servletContext.contextPath}/servlets/ajax/ejrdokCrud",
                    pageSizeListPlaceholder: $("#toolbar-page-sizes-list"),
                    paginationListPlaceholder: $("#toolbar-pagination-list")
                };
            });
        </script>
        <jsp:include page="/jsp/js/initClickableTableRow.jsp"/>
        <jsp:include page="/jsp/js/initDialogPlaceholders.jsp"/>
        <jsp:include page="/jsp/js/initDialogs.jsp"/>
        <jsp:include page="/jsp/js/initCrudButtons.jsp"/>
        <jsp:include page="/jsp/js/initAutocomplete.jsp"/>
        <jsp:include page="/jsp/js/initTotalRowCount.jsp"/>
        <jsp:include page="/jsp/js/initCustomPageRefresh.jsp"/>
        <jsp:include page="/jsp/js/initPageSizeList.jsp"/>
        <jsp:include page="/jsp/js/initPagination.jsp"/>
        <script type="text/javascript">
            $(function () {
                $.iskra.addRecord = function () {
                    var valid = true;
                    resetTips();
                    $.iskra.allFields.removeClass("ui-state-error");
                    valid = valid && checkLength($("#rdk"), 1, 1,
                            "Код розпорядчого документу не повинен бути пустим!");
                    valid = valid && checkLength($("#rdn"), 1, 4,
                            "Номер розпорядчого документу не повинен бути пустим!");
                    valid = valid && checkLength($("#nzak"), 1, 9,
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
                        data.append("q_mode", $.iskra.mode);
                        data.append("q_table_name", $.iskra.tableName);
                        data.append("q_id", $("tr.active").attr("data-id"));

                        $.ajax({
                            type: "post",
                            enctype: 'multipart/form-data',
                            url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + $.iskra.servletUrlPatternCrud,
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
                                $.iskra.pageNumber = ((newRowNumber - (newRowNumber % $.iskra.pageSize)) / $.iskra.pageSize) + (((newRowNumber % $.iskra.pageSize) === 0) ? 0 : 1);
                                $.iskra.dialogForAddOrEdit.dialog("close");
                                console.log("addRecord() SUCCESS: ", "retId:", xhr.getResponseHeader("ret_id"), "; retRowNumber:", xhr.getResponseHeader("ret_rn"));
                                $.iskra.getPage();
                                $.iskra.getTotalRowCount();
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
                };

                $.iskra.setInputDefaults = function () {
                    //set defaults
                    var d = $.datepicker.parseDate("dd.mm.yy", "<%=strCurDate%>");
                    $("#dialog-form").find("#rdd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#ordd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#dvd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                };

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

                $.iskra.getPage = function () {
                    var currentId = ($("#currentId").length) ? parseInt($("#currentId").text()) : -1;
                    if ($("#currentId").length && $("#currentId").attr("new-one") === "true") {
                        $("#currentId").attr("new-one", "false");
                    }
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + $.iskra.servletUrlPatternGetPage,
                        dataType: "json",
                        data: {
                            q_table_name: $.iskra.tableName,
                            q_page_size: $.iskra.pageSize,
                            q_page_number: $.iskra.pageNumber
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            $("#overlay-wrapper").fadeIn("fast");
                        },
                        success: function (data, status, xhr) {
                            var tableBody = $("#content-table-body");
                            var rowNumber = $.iskra.pageSize * ($.iskra.pageNumber - 1);
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
                };
                $.iskra.getPage();
                $.iskra.getTotalRowCount();
                $.iskra.pageSizeList();
            });
        </script>
    </body>
</html>
