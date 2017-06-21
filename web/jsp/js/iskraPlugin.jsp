<%-- 
    Document   : iskraPlugin
    Created on : Jun 21, 2017, 10:26:01 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    (function ($) {
        $.fn.plugin = function (options) {
            var settings = $.extend({
                allFields: $([]),
                pageSize: 15,
                pageNumber: 1,
                totalRowCount: 0,
                tableName: "",
                mode: "",
                id: "",
                urlGetPageServlet: "",
                urlCrudServlet: "",
                pageSizeListPlaceholder: null,
                paginationListPlaceholder: null
            }, options);
        }
    })(jQuery);
</script>
