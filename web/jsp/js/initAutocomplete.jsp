<%-- 
    Document   : initAutocomplete
    Created on : Jun 21, 2017, 1:36:33 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $("#dialog-form input:text").each(function () {
            if ($(this).attr("atc") === "on") {
                var atcTableName = $(this).attr("atc-table-name");
                var atcFieldName = $(this).attr("atc-field-name");
                var ownerId = $(this).prop("id");
                $(this).autocomplete({
                    source: function (request, response) {
                        $(".ui-helper-hidden-accessible").empty();
                        $(".ui-autocomplete").css({display: "none"});
                        var atcWaitDiv = $("<div>").html("Зачекайте, іде пошук інформації для допомоги......")
                                .prop("id", "atc-wait")
                                .css("display", "block")
                                .css("padding", "6px 30px")
                                .css("position", "absolute")
                                .css("top", ($("#" + ownerId).offset().top + $("#" + ownerId).outerHeight()).toString() + "px")
                                .css("left", $("#" + ownerId).offset().left.toString() + "px")
                                .css("z-index", "110")
                                .css("width", $("#" + ownerId).outerWidth().toString() + "px")
                                .css("background", "url(\"${pageContext.servletContext.contextPath}/images/loading.gif\") 6px 6px no-repeat #fff")
                                .css("background-size", "20px")
                                .addClass("ui-widget-content");
                        $("body").append(atcWaitDiv);
                        $.ajax({
                            url: "${pageContext.servletContext.contextPath}/servlets/ajax/autocomplete",
                            type: "get",
                            data: {q_string_pattern: request.term,
                                q_table_name: atcTableName,
                                q_field_name: atcFieldName},
                            dataType: "json",
                            success: function (data) {
                                $("#atc-wait").remove();
                                response(data.autocomplete);
                            },
                            error: function (xhr, status, error) {
                                $("#atc-wait").remove();
                                if (xhr.getResponseHeader("error") === null && status === "error") {
                                    $($.iskra.dialogErrorMessage).find("#error-content").html("Не виявлена помилка серверу");
                                    $($.iskra.dialogErrorMessage).find("#error-details-content").html("Можливо web-сервер не відповідає на запити. Зверніться до розробників.");
                                } else {
                                    $($.iskra.dialogErrorMessage).find("#error-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error"))).replace(/\s*\++\s*/g, " "));
                                    $($.iskra.dialogErrorMessage).find("#error-details-content").html(decodeURIComponent(stringFormat(xhr.getResponseHeader("error_details"))).replace(/\s*\++\s*/g, " "));
                                }
                                $.iskra.dialogErrorMessage.dialog("open");
                                console.log("autocomplete()", "tableName:", atcTableName, "fieldName", atcFieldName, "ERROR : ", error);
                            }
                        });
                    },
                    minLength: 0
                });
            }
        });
    });
</script>
