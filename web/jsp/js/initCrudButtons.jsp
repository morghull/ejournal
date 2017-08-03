<%-- 
    Document   : initCrudButtons
    Created on : Jun 21, 2017, 2:25:04 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        // this initializes click on "Add" button                
        $("#btnAdd").button().on("click", function () {
            $("#dialog-form").siblings().children("span.ui-dialog-title").html("Додавання нового запису");
            $("#dialog-form").removeClass("hidden");
            $.iskra.mode = "add";
            $.iskra.setInputDefaults();
            $.iskra.dialogForAddOrEdit.dialog("open");
        });

        // this initializes click on "Edit" button                
        $("#btnEdit").button().on("click", function () {
            if ($("tr.active").length === 0)
                return false;
            $("#dialog-form").siblings().children("span.ui-dialog-title").html("Редагування запису");
            $("#dialog-form").removeClass("hidden");
            $.iskra.mode = "edit";
            $.iskra.id = $("tr.active").attr("data-id");
            $.getJSON("${pageContext.servletContext.contextPath}/servlets/ajax/" + $.iskra.servletUrlPatternCrud, {q_id: $.iskra.id, q_table_name: $.iskra.tableName})
                    .success(function (data) {
                        $.iskra.dialogForAddOrEdit.dialog("open");
                        $.each(data.row, function (key, value) {
                            $("#dialog-form input#" + key).val(value);
                        });
                    })
                    .error(function (xhr, status, error) {
                        if (xhr.getResponseHeader("error") === null && status === "error") {
                            $($.iskra.dialogErrorMessage).find("#error-content").html("Не виявлена помилка серверу");
                            $($.iskra.dialogErrorMessage).find("#error-details-content").html("Можливо web-сервер не відповідає на запити. Зверніться до розробників.");
                        } else {
                            $($.iskra.dialogErrorMessage).find("#error-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error"))).replace(/\s*\++\s*/g, " "));
                            $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error_details"))).replace(/\s*\++\s*/g, " "));
                        }
                        $.iskra.dialogErrorMessage.dialog("open");
                        console.log("pre-edit getJSON ERROR : ", error);
                    });
        });

        // this initializes click on "Delete" button              
        $("#btnDel").button().on("click", function () {
            if ($("tr.active").length === 0)
                return false;
            $.iskra.mode = "delete";
            $.iskra.dialogForDelete.dialog("open");
        });
    });
</script>