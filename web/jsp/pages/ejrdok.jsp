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
            String version = application.getInitParameter("pluginVersion");
        %>
        <div class="overlay" id="overlay-wrapper"></div>
        <div style="padding:5px;">
            <div class="btn-toolbar" role="toolbar" aria-label="toolBar">
                <div class="btn-group" role="group" aria-label="iudGroup">
                    <button id="btnAdd" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_add.png?<%=version%>" alt="Mountain View" style="height:24px;">
                    </button>
                    <button id="btnEdit" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_edit.png?<%=version%>" alt="Mountain View" style="height:24px;">
                    </button>
                    <button id="btnDel" type="button" class="btn btn-default">
                        <img src="${pageContext.servletContext.contextPath}/images/24px_del.png?<%=version%>" alt="Mountain View" style="height:24px;">
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
                        <td style="width:10%;min-width:90px;" rowspan="2">Посилання</td>
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
                            <div class="input-group width100perc">
                                <label for="rdk" class="input-group-label width60px">Код</label>
                                <input id="rdk" name="rdk" type="text" maxlength="1" class="form-control width120px" placeholder="Введіть код..."
                                       ajax-validation="on" ajv-url-pattern="validate_rdt" ajv-field-name="rdtk"
                                       required required-message="Код розпорядчого документу не повинен бути пустим!"
                                       x-moz-errormessage="Код розпорядчого документу не повинен бути пустим!">
                                <span class="input-group-btn">
                                    <span id="rdk-help-btn" class="btn btn-group btn-custom-help" type="button">...</span>
                                </span>
                                <span id="rdk-rdtn" class="input-group-addon-more width500px"></span>
                            </div>
                            <label for="rdk" class="input-group-label width500px" style="padding-left: 60px;"></label>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="rdn" class="input-group-label width60px">Номер</label>
                                <input id="rdn" name="rdn" type="text" maxlength="4" class="form-control" placeholder="Введіть номер..."
                                       required required-message="Номер розпорядчого документу не повинен бути пустим!"
                                       x-moz-errormessage="Номер розпорядчого документу не повинен бути пустим!"
                                       digitonly>
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
                                <input id="nazz" name="nazz" type="text" maxlength="90" class="form-control" placeholder="Введіть назву..."
                                       required required-message="Назва розпорядчого документу не повинна бути пустим!"
                                       x-moz-errormessage="Назва розпорядчого документу не повинна бути пустим!">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="rdsh" class="input-group-label width60px">Шифр</label>
                                <input id="rdsh" name="rdsh" type="text" maxlength="5" class="form-control" placeholder="Введіть шифр..."
                                       required required-message="Шифр розпорядчого документу не повинен бути пустим!"
                                       x-moz-errormessage="Шифр розпорядчого документу не повинен бути пустим!">
                            </div>
                        </div>
                    </div>
                    <div id="fieldset-ord">
                        <div class="form-group">
                            <div class="input-group width100perc">
                                <label for="ordk" class="input-group-label width60px">Код</label>
                                <input id="ordk" name="ordk" type="text" maxlength="1" class="form-control" placeholder="Введіть код..."
                                       ajax-validation="on" ajv-url-pattern="validate_rdt" ajv-field-name="rdtk"
                                       required required-message="Код основного розпорядчого документу не повинен бути пустим!"
                                       x-moz-errormessage="Код основного розпорядчого документу не повинен бути пустим!">
                                <span class="input-group-btn">
                                    <span id="ordk-help-btn" class="btn btn-secondary btn-custom-help" type="button">...</span>
                                </span>
                                <span id="ordk-rdtn" class="input-group-addon-more width500px"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group width500px">
                                <label for="ordn" class="input-group-label width60px">Номер</label>
                                <input id="ordn" name="ordn" type="text" maxlength="4" class="form-control" placeholder="Введіть номер..."
                                       required required-message="Номер основного розпорядчого документу не повинен бути пустим!"
                                       x-moz-errormessage="Номер основного розпорядчого документу не повинен бути пустим!"
                                       digitonly>
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
                            <input id="nzak" name="nzak" type="text" maxlength="9" class="form-control" placeholder="Введіть номер..."
                                   required required-message="Номер основного фінансового замовлення не повинен бути пустим!"
                                   x-moz-errormessage="Номер основного фінансового замовлення не повинен бути пустим!">
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
                            <label for="uplfiles" class="input-group-label width100px">Прикріпленні файли</label>
                            <input id="uplfiles" name="uplfiles" type="file" multiple>
                        </div>
                    </div>
                    <!-- Allow form submission with keyboard without duplicating the dialog button -->
                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
            </form>
        </div>
        <jsp:include page="/jsp/js/jscript.jsp"/>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.iskra.tablehelp.js?<%=version%>"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.iskra.fileupload.js?<%=version%>"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.iskra.errorpopup.js?<%=version%>"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.iskra.ajaxvalidation.js?<%=version%>"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.iskra.autocomplete.js?<%=version%>"></script>
        <script type="text/javascript">
            $(function () {
                var emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;

            });
        </script>
        <jsp:include page="/jsp/js/initClickableTableRow.jsp"/>
        <jsp:include page="/jsp/js/initDisableSubmitOnEnter.jsp"/>
        <script type="text/javascript">
            $(function () {
                var pageSize = 15,
                        pageNumber = 1,
                        totalRowCount = 0,
                        tableName = "xxx.ejrdok",
                        mode = "",
                        id = "",
                        servletUrlPatternCrud = "ejrdokCrud",
                        servletUrlPatternGetPage = "ejrdokGetPage",
                        pageSizeListPlaceholder = $("#toolbar-page-sizes-list"),
                        paginationListPlaceholder = $("#toolbar-pagination-list");

                // this initializes click on "Add" button                
                $("#btnAdd").button().on("click", function () {
                    $("#dialog-form").siblings().children("span.ui-dialog-title").html("Додавання нового запису");
                    $("#dialog-form").removeClass("hidden");
                    mode = "add";
                    setInputDefaults();
                    $("#dialog-form").dialog("open");
                });

                // this initializes click on "Edit" button                
                $("#btnEdit").button().on("click", function () {
                    if ($("tr.active").length === 0)
                        return false;
                    $("#dialog-form").siblings().children("span.ui-dialog-title").html("Редагування запису");
                    $("#dialog-form").removeClass("hidden");
                    mode = "edit";
                    id = $("tr.active").attr("data-id");
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + servletUrlPatternCrud,
                        dataType: "json",
                        data: {
                            q_id: id,
                            q_table_name: tableName
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            $("#overlay-wrapper").fadeIn("fast");
                        },
                        success: function (data) {
                            $("#dialog-form").dialog("open");
                            $.each(data.row, function (key, value) {
                                $("#dialog-form input#" + key).val(value);
                            });
                            console.log("pre-edit getInstance SUCCESS ", data);
                        },
                        error: function (error) {
                            riseAnError("pre-edit getInstance", error);
                        },
                        complete: function () {
                            $("#overlay-wrapper").fadeOut("fast");
                        }
                    });
                });

                // this initializes click on "Delete" button
                $("#btnDel").button().on("click", function () {
                    if ($("tr.active").length === 0)
                        return false;
                    mode = "delete";
                    $("#dialog-confirm-deletion").dialog("open");
                });

                function riseAnError(sender, error) {
                    // this creates dialog placeholder for error message
                    if ($("#dialog-error-message").length === 0) {
                        $("body").append(
                                $("<div>")
                                .prop("id", "dialog-error-message")
                                .prop("title", "Помилка")
                                .append($("<div>")
                                        .append($("<img>")
                                                .prop("src", "${pageContext.servletContext.contextPath}/images/48px_error.png")
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
                                .hide());
                        $("#dialog-error-message").dialog({
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
                        $("#dialog-error-message").siblings(".ui-dialog-titlebar").find("button.ui-dialog-titlebar-close").addClass("ui-button").addClass("ui-icon-closethick");

                    }
                    var errorMessage, errorDetails;
                    if (error.responseJSON !== undefined && error.responseJSON.error !== undefined) {
                        errorMessage = error.responseJSON.error.text;
                        errorDetails = error.responseJSON.error.details;
                    } else {
                        errorMessage = "Не виявлена помилка серверу";
                        errorDetails = "Можливо web-сервер не відповідає на запити. Зверніться до розробників";
                    }
                    $("#dialog-error-message").find("#error-content").html(errorMessage);
                    $("#dialog-error-message").find("#error-details-content").html(errorDetails);
                    $("#dialog-error-message").dialog("open");
                    console.log(sender + " ERROR : ",
                            (error.responseJSON !== undefined && error.responseJSON.error !== undefined) ? error.responseJSON.error : error);
                }

                addRecord = function () {
                    var valid = true;
                    $("#dialog-form input[required]").each(function () {
                        if ($(this).val().length === 0) {
                            $(this).errorpopup({
                                "message": $(this).attr("required-message"),
                                "container": $(this).parents(".form-group")
                            });
                            valid = false;
                        }
                    });
                    var invalidInputs = $("#dialog-form input[valid-status=invalid]");
                    if (invalidInputs.length !== 0) {
                        $("#dialog-form input[valid-status=invalid]:first").focus();
                        valid = false;
                    }
                    /*resetTips();
                     allFields.removeClass("ui-state-error");*/
                    /*valid = valid && checkLength($("#rdk"), 1, 1,
                     "Код розпорядчого документу не повинен бути пустим!");
                     valid = valid && checkLength($("#rdn"), 1, 4,
                     "Номер розпорядчого документу не повинен бути пустим!");
                     valid = valid && checkLength($("#nzak"), 1, 9,
                     "Номер основного фінансового замовлення не повинен бути пустим!");*/
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
                            enctype: "multipart/form-data",
                            url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + servletUrlPatternCrud,
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
                                    $("#currentId").empty().append(data.ret_id);
                                } else {
                                    $("body").append($("<span>").prop("id", "currentId").attr("new-one", "true").addClass("hidden").append(data.ret_id));
                                }
                                var newRowNumber = data.ret_rn;
                                pageNumber = ((newRowNumber - (newRowNumber % pageSize)) / pageSize) + (((newRowNumber % pageSize) === 0) ? 0 : 1);
                                $("#dialog-form").dialog("close");
                                console.log("addRecord() SUCCESS: ", "retId:", data.ret_id, "; retRowNumber:", data.ret_rn);
                                getPage();
                                getTotalRowCount();
                                //$("html, body").animate({scrollTop: $("tr.active").offset().top}, 500);
                            },
                            error: function (error) {
                                riseAnError("addRecord", error);
                            },
                            complete: function () {
                                $("#overlay-wrapper").fadeOut("fast");
                            }
                        });
                    }
                    return valid;
                };
                setInputDefaults = function () {
                    //set defaults
                    var d = $.datepicker.parseDate("dd.mm.yy", "<%=strCurDate%>");
                    $("#dialog-form").find("#rdd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#ordd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                    $("#dialog-form").find("#dvd").val(("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." + d.getFullYear());
                };
                getPage = function () {
                    var currentId = ($("#currentId").length) ? parseInt($("#currentId").text()) : -1;
                    if ($("#currentId").length && $("#currentId").attr("new-one") === "true") {
                        $("#currentId").attr("new-one", "false");
                    }
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + servletUrlPatternGetPage,
                        dataType: "json",
                        data: {
                            q_table_name: tableName,
                            q_page_size: pageSize,
                            q_page_number: pageNumber
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            $("#overlay-wrapper").fadeIn("fast");
                        },
                        success: function (data, status, xhr) {
                            var tableBody = $("#content-table-body");
                            var rowNumber = pageSize * (pageNumber - 1);
                            var url = "${pageContext.servletContext.contextPath}/servlets/ajax/uplfileDownload?q_id=";
                            tableBody.empty();
                            $.each(data.page, function (key, val) {
                                rowNumber++;
                                var ufs = $("<td>").addClass("padding-off");
                                $.each(val.files, function () {
                                    var ext = (/[.]/.exec(this.ufname)) ? /[^.]+$/.exec(this.ufname)[0] : undefined;
                                    var uf = $("<div>")
                                            .attr("uf-id", this.ufid)
                                            .attr("uf-name", this.ufname)
                                            .attr("title", this.ufname)
                                            .addClass("uf-file uf-" + ext);
                                    if (["jpeg", "png", "jpg", "bmp"].indexOf(ext) >= 0)
                                        ufs.append(uf.addClass("uf-img"));
                                    else if (ext === "pdf")
                                        ufs.append(uf);
                                    else {
                                        var a = $("<a>").attr("href", url + this.ufid).attr("download", this.ufname);
                                        ufs.append(a.append(uf));
                                    }
                                });
                                tableBody.append($("<tr>").addClass("clickable-row").attr("data-id", val.idd)
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
                                        .append(ufs)
                                        );
                            });
                            $(".uf-file.uf-pdf").click(function () {
                                $("#pdf-viewer-iframe").attr("src", url + $(this).attr("uf-id"));
                                $("#pdf-viewer-placeholder").fadeIn("fast");
                            });
                            $(".uf-file.uf-img").click(function () {
                                $("#img-viewer-img").attr("src", url + $(this).attr("uf-id"));
                                $("#img-viewer-placeholder").fadeIn("fast");
                            });

                            console.log("getPage SUCCESS : ", data);
                        },
                        error: function (error) {
                            riseAnError("getPage", error);
                        },
                        complete: function () {
                            $("#overlay-wrapper").fadeOut("fast");
                            $("tr[data-id^=" + currentId + "]").click();
                        }
                    });
                };

                //this initializes choosing of page size
                pageSizeList = function () {
                    pageSizeListPlaceholder
                            .children("li").click(function (e) {
                        e.preventDefault();
                        $("#page-size").text($(this).text());
                        pageSize = parseInt($(this).text());
                        pageNumber = 1;
                        getPage();
                        getTotalRowCount();
                    });
                };

                //redraws pagination                
                redrawPagination = function () {
                    paginationListPlaceholder.empty();
                    var totalPagesCount = ((totalRowCount - (totalRowCount % pageSize)) / pageSize) + (((totalRowCount % pageSize) === 0) ? 0 : 1);
                    totalPagesCount = (totalPagesCount === 0) ? 1 : totalPagesCount; //there is always must be at least one page    
                    var paginationDiapazonSize = (totalPagesCount < 5) ? totalPagesCount : 5;
                    var paginationMin, paginationMax;
                    if (totalPagesCount < 5) {
                        paginationMin = 1;
                        paginationMax = paginationDiapazonSize;
                    } else {
                        paginationMin = (pageNumber <= (paginationDiapazonSize - 1) / 2) ? 1 : (pageNumber >= totalPagesCount - ((paginationDiapazonSize - 1) / 2)) ? totalPagesCount - paginationDiapazonSize + 1 : pageNumber - ((paginationDiapazonSize - 1) / 2);
                        paginationMax = (paginationMin + paginationDiapazonSize - 1 >= totalPagesCount) ? totalPagesCount : paginationMin + paginationDiapazonSize - 1;
                    }
                    paginationListPlaceholder
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
                        paginationListPlaceholder
                                .append($("<li>")
                                        .addClass((i === pageNumber) ? "active" : "")
                                        .append($("<a>")
                                                .text(i)
                                                )
                                        );
                    }
                    paginationListPlaceholder
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
                    paginationListPlaceholder
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
                };

                //function to get total row count
                getTotalRowCount = function () {
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
                            console.log("getTotalRowCount SUCCESS : ", data);
                        },
                        error: function (error) {
                            riseAnError("getTotalRowCount", error);
                        },
                        complete: function () {

                        }
                    });
                };

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

                // this initializes the dialog to add or edit information in table
                $("#dialog-form").dialog({
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
                        $("#dialog-form").find("form").trigger("reset");
                        $("#dialog-form input:text").errorpopup("hide");
                        $("#dialog-form input:text").ajaxvalidation("reset");
                        //$("#rdk,#ordk,#nzak").ajaxvalidation("reset");
                    }
                });
                // this makes an object form, used for reseting while pressing "cancel" button
                $("#dialog-form").find("form").on("submit", function (event) {
                    event.preventDefault();
                    addRecord();
                });
                // this creates dialog placeholder for deletion confirmation
                $("body").append($("<div>")
                        .prop("id", "dialog-confirm-deletion")
                        .prop("title", "Видалити обраний запис?")
                        .append($("<p>")
                                .prop("id", "confirm-content")
                                .html("Чи дійсно ви бажаєте видатили обраний запис?</br>Обраний запис буде видалено з БД")
                                )
                        .hide()
                        );
                // this initializes the dialog to delete information from table
                $("#dialog-confirm-deletion").dialog({
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
                                    url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + servletUrlPatternCrud,
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
                                        $("#dialog-confirm-deletion").dialog("close");
                                        console.log("deleteRecord SUCCESS ");
                                        getPage();
                                        getTotalRowCount();
                                    },
                                    error: function (error) {
                                        riseAnError("deleteRecord", error);
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
                // this initializes the dialog for viewing uploaded pdf files
                $("body")
                        .append($("<div>")
                                .attr("id", "pdf-viewer-placeholder")
                                .addClass("uf-viewer-placeholder")
                                .append(
                                        $("<iframe>")
                                        .attr("id", "pdf-viewer-iframe")
                                        .addClass("uf-viewer-iframe")
                                        )
                                .click(function () {
                                    $("#pdf-viewer-iframe").removeAttr("src");
                                    $("#pdf-viewer-placeholder").fadeOut("fast");
                                })
                                .hide()
                                );
                // this initializes the dialog for viewing uploaded image files
                $("body")
                        .append($("<div>")
                                .attr("id", "img-viewer-placeholder")
                                .addClass("uf-viewer-placeholder")
                                .append(
                                        $("<img>")
                                        .attr("id", "img-viewer-img")
                                        .addClass("uf-viewer-img select-disable")
                                        )
                                .click(function () {
                                    $("#img-viewer-img").removeAttr("src");
                                    $("#img-viewer-placeholder").fadeOut("fast");
                                })
                                .hide()
                                );

                // this makes more pleasant look for dialog title close button
                $("button.ui-dialog-titlebar-close").addClass("ui-button").addClass("ui-icon-closethick");

                $("#dialog-form").on("keyup", "input:text", function () {
                    $(this).removeAttr("valid-status");
                    $(this).removeAttr("ajv-icon");
                    $(this).errorpopup("hide");
                });
                $("#dialog-form input:text").attr("autocomplete", "off");
                $("#dialog-form input:text").bind("keydown", function (e) {
                    if ([9, 13].indexOf(e.which || e.keyCode) >= 0) {
                        e.preventDefault();
                        var inputs = $("#dialog-form input:text");
                        var nextItem = inputs.eq(inputs.index($(this)) + ((e.shiftKey) ? -1 : 1));
                        if (nextItem.size() === 0)
                            nextItem = $("#dialog-form input:text").eq(0);
                        nextItem.focus();
                    }
                });

                //alternative way to force user type only digits
                /*$("input[digitonly]").keyup(function (e) {
                 if (/\D/g.test(this.value)) {
                 // Filter non-digits from input value.
                 this.value = this.value.replace(/\D/g, "");
                 }
                 });*/
                $("input[digitonly]").on("keypress", function (evt) {
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    return !(charCode !== 8 && charCode !== 9 // backspace and tab keys
                            && charCode !== 37 && charCode !== 39 // ← → arrows keys
                            && (charCode < 48 || charCode > 57) // 0-9 digits keys
                            );
                });

                $("#dialog-form").parent().css("min-width", "750px");

                //jquery plugin apply
                $("#rdk,#ordk").tablehelp({
                    "tableCaption": "Розпорядчі документи.Типи",
                    "tableName": "rdt",
                    "urlToGetData": "${pageContext.servletContext.contextPath}/servlets/ajax/rdtGetPage",
                    "itemToPickup": "rdtk",
                    "columns": [
                        {"caption": "Код", "item": "rdtk"},
                        {"caption": "Назва", "item": "rdtn", "align": "left"}
                    ],
                    onError: function (error) {
                        riseAnError("jquery.iskra.tablehelp", error);
                    }
                });
                $("#rdk-help-btn").click(function () {
                    $("#rdk").tablehelp("show");
                });
                $("#ordk-help-btn").click(function () {
                    $("#ordk").tablehelp("show");
                });

                $("#rdk,#ordk").ajaxvalidation({
                    "fieldName": "rdtk",
                    "urlToGetData": "${pageContext.servletContext.contextPath}/servlets/api/validate_rdt",
                    onError: function (error) {
                        riseAnError("jquery.iskra.ajaxvalidation", error);
                    }
                });

                $("#nzak").ajaxvalidation({
                    "fieldName": "nzak",
                    "urlToGetData": "${pageContext.servletContext.contextPath}/servlets/api/validate_ski",
                    onError: function (error) {
                        riseAnError("jquery.iskra.ajaxvalidation", error);
                    }
                });

                $("#uplfiles").fileupload();

                $("#nzak").iskraAutocomplete({
                    "tableName": "clippersql.skisql",
                    "fieldName": "nzak",
                    "urlToGetData": "${pageContext.servletContext.contextPath}/servlets/ajax/autocomplete",
                    onError: function (error) {
                        riseAnError("jquery.iskra.autocomplete", error);
                    }
                });

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

                $("#content-table-body").tooltip({
                    selector: ".uf-file",
                    placement: "left"
                });

                getPage();
                getTotalRowCount();
                pageSizeList();
            });
        </script>
    </body>
</html>
