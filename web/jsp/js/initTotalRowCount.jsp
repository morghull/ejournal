<%-- 
    Document   : initTotalRowCount
    Created on : Jun 21, 2017, 8:26:43 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $.iskra.getTotalRowCount = function () {
            $.ajax({
                type: "get",
                url: "${pageContext.servletContext.contextPath}/servlets/ajax/getRowCount",
                dataType: "json",
                data: {
                    q_table_name: $.iskra.tableName
                },
                timeout: 600000,
                beforeSend: function () {
                    $("#toolbar-pagination-list").empty();
                    $("#toolbar-pagination-list").addClass("overlay-pagination");
                },
                success: function (data, status, xhr) {
                    $.iskra.totalRowCount = data.total_row_count;
                    $("#toolbar-pagination-list").removeClass("overlay-pagination");
                    $.iskra.redrawPagination();
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
        };
    });
</script>