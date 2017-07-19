<%-- 
    Document   : initDialogs
    Created on : Jun 21, 2017, 8:15:02 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
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
                        $.iskra.addRecord();
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
                $.iskra.allFields.removeAttr("valid-status").removeAttr("ajv-icon");
                $.iskra.allFields.parents(".form-group").find(".error-popup").remove();
            }
        });

        // this makes an object form, used for reseting while pressing "cancel" button
        $.iskra.form = $.iskra.dialogForAddOrEdit.find("form").on("submit", function (event) {
            event.preventDefault();
            $.iskra.addRecord();
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
                            url: "${pageContext.servletContext.contextPath}/servlets/ajax/" + $.iskra.servletUrlPatternCrud,
                            data: {
                                q_table_name: $.iskra.tableName,
                                q_mode: $.iskra.mode,
                                q_id: $("tr.active").attr("data-id")
                            },
                            timeout: 600000,
                            beforeSend: function () {
                                $("#overlay-wrapper").fadeIn("fast");
                            },
                            success: function (data, status, xhr) {
                                $.iskra.dialogForDelete.dialog("close");
                                console.log("deleteRecord() SUCCESS ");
                                $.iskra.getPage();
                                $.iskra.getTotalRowCount();
                            },
                            error: function (xhr, status, error) {
                                if (xhr.getResponseHeader("error") === null && status === "error") {
                                    $($.iskra.dialogErrorMessage).find("#error-content").html("Не виявлена помилка серверу");
                                    $($.iskra.dialogErrorMessage).find("#error-details-content").html("Можливо web-сервер не выдповідає на запити. Зверніться до розробників.");
                                } else {
                                    $($.iskra.dialogErrorMessage).find("#error-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error"))).replace(/\s*\++\s*/g, " "));
                                    $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error_details"))).replace(/\s*\++\s*/g, " "));
                                }
                                $.iskra.dialogErrorMessage.dialog("open");
                                console.log("deleteRecord() ERROR : ", error);
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
    });
</script>
