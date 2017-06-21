<%-- 
    Document   : initClickableTableRow
    Created on : Jun 21, 2017, 11:26:22 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        //clickable table row click
        $('table' || '#table' || '.table').on('click', '.clickable-row', function () {
            $(this).addClass('active').siblings().removeClass('active').children().removeClass('border-right-none');
            $(this).children("td:not(.hidden):last").addClass('border-right-none');
            $("#footer-placeholder").html($(this).children("#hidden-content").html());
            if ($("#currentId").length) {
                $("#currentId").empty().append($(this).attr("data-id"));
            } else {
                $("body").append($("<span>").prop("id", "currentId").attr("new-one", "false").addClass("hidden").append($(this).attr("data-id")));
            }
        });
    });
</script>