<%-- 
    Document   : initPageSizeList
    Created on : Jun 21, 2017, 9:25:47 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        //this initializes choosing of page size
        $.iskra.pageSizeList = function () {
            $.iskra.pageSizeListPlaceholder
                    .children("li").click(function (e) {
                e.preventDefault();
                $("#page-size").text($(this).text());
                $.iskra.pageSize = parseInt($(this).text());
                $.iskra.pageNumber = 1;
                $.iskra.getPage();
                $.iskra.getTotalRowCount();
            });
        };
    });
</script>
