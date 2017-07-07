<%-- 
    Document   : initPagination
    Created on : Jun 21, 2017, 9:38:20 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $.iskra.redrawPagination = function () {
            $.iskra.paginationListPlaceholder.empty();
            var totalPagesCount = (($.iskra.totalRowCount - ($.iskra.totalRowCount % $.iskra.pageSize)) / $.iskra.pageSize) + ((($.iskra.totalRowCount % $.iskra.pageSize) === 0) ? 0 : 1);
            totalPagesCount = (totalPagesCount === 0) ? 1 : totalPagesCount; //there is always must be at least one page    
            var paginationDiapazonSize = (totalPagesCount < 5) ? totalPagesCount : 5;
            var paginationMin, paginationMax;
            if (totalPagesCount < 5) {
                paginationMin = 1;
                paginationMax = paginationDiapazonSize;
            } else {
                paginationMin = ($.iskra.pageNumber <= (paginationDiapazonSize - 1) / 2) ? 1 : ($.iskra.pageNumber >= totalPagesCount - ((paginationDiapazonSize - 1) / 2)) ? totalPagesCount - paginationDiapazonSize + 1 : $.iskra.pageNumber - ((paginationDiapazonSize - 1) / 2);
                paginationMax = (paginationMin + paginationDiapazonSize - 1 >= totalPagesCount) ? totalPagesCount : paginationMin + paginationDiapazonSize - 1;
            }
            $.iskra.paginationListPlaceholder
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
                $.iskra.paginationListPlaceholder
                        .append($("<li>")
                                .addClass((i === $.iskra.pageNumber) ? "active" : "")
                                .append($("<a>")
                                        .text(i)
                                        )
                                );
            }
            $.iskra.paginationListPlaceholder
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
            $.iskra.paginationListPlaceholder
                    .children("li").click(function (e) {
                e.preventDefault();
                if ($(this).is(":first-child"))
                    $.iskra.pageNumber = ($.iskra.pageNumber - 1 < 1) ? 1 : $.iskra.pageNumber - 1;
                else if ($(this).is(":last-child"))
                    $.iskra.pageNumber = ($.iskra.pageNumber + 1 > totalPagesCount) ? totalPagesCount : $.iskra.pageNumber + 1;
                else
                    $.iskra.pageNumber = parseInt($(this).text());
                $.iskra.redrawPagination();
                $.iskra.getPage();
            });
        };
    });
</script>
