/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    $.fn.tablehelp = function (options) {

        $(document).mouseup(function (e) {
            var container = $("#ui-tablehelp-div");
            // if the target of the click isn't the container nor a descendant of the container
            if (container.is(":visible") && !container.is(e.target) && container.has(e.target).length === 0) {
                //container.fadeOut("fast");
                container.hide();
            }
        });
        // this creates dialog placeholder for error message
        if ($("#dialog-error-message").length === 0)
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
                    .hide()
                    );
        return this.each(function () {
            var settings = $.extend({
                "pageSize": 15
            }, options);
            var obj = $(this);

            if (settings.onError === undefined)
                $.error("jquery.tablehelp: missing onError handler in plugin options of " + "#" + obj.attr("id"));
            if (settings.columns === undefined)
                $.error("jquery.tablehelp: missing columns description option in plugin options of " + "#" + obj.attr("id"));
            var columnsWithInvalidItems = $.grep(settings.columns, function (c) {
                return (c.caption === undefined || c.item === undefined);
            });
            if (settings.columns.length === 0 || columnsWithInvalidItems.length !== 0)
                $.error("jquery.tablehelp: columns description array have invalid format in plugin options of " + "#" + obj.attr("id"));
            obj.attr("readonly", true);
            obj.click(function () {
                //placeholder initialization
                if ($("#ui-tablehelp-div").length === 0)
                    $("<div>").attr("id", "ui-tablehelp-div")
                            .addClass("ui-tablehelp ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
                            .appendTo("body");
                else
                    $("#ui-tablehelp-div").empty();
                $("<div>").addClass("ui-widget-header ui-tablehelp-header ui-helper-clearfix ui-corner-all")
                        .appendTo("#ui-tablehelp-div")
                        .append(
                                $("<div>").addClass("ui-tablehelp-title").append(settings.tableCaption)
                                );
                var table = $("<table>").addClass("ui-tablehelp-table")
                        .append($("<thead>"), $("<tbody>"))
                        .appendTo("#ui-tablehelp-div");
                //var tableHeader = $("<thead>").appendTo(table);
                var tableHeader = $("<tr>");//.append($("<th>").append("№ п\п"));
                $.each(settings.columns, function (key, col) {
                    tableHeader.append($("<th>").append(col.caption));
                });
                tableHeader.appendTo($("#ui-tablehelp-div table thead"));
                $("<div>").addClass("ui-tablehelp-overlay").css({"display": "none"}).appendTo("#ui-tablehelp-div");
                $("#ui-tablehelp-div").css({
                    "position": "absolute",
                    "z-index": "101",
                    "min-width": obj.outerWidth(),
                    "height": "auto",
                    "left": obj.offset().left,
                    "top": obj.offset().top + obj.outerHeight()
                }).fadeIn("fast");
                $.ajax({
                    type: "get",
                    url: settings.urlToGetData,
                    dataType: "json",
                    data: {
                        q_table_name: settings.tableName,
                        q_page_size: settings.pageSize,
                        q_page_number: 1
                    },
                    timeout: 600000,
                    beforeSend: function () {
                        $("#ui-tablehelp-div .ui-tablehelp-overlay").fadeIn("fast");
                    },
                    success: function (data, status, xhr) {
                        var tableBody = $("#ui-tablehelp-div table tbody");
                        var rowNumber = settings.pageSize * (1 - 1);
                        tableBody.empty();
                        $.each(data.page, function (key, val) {
                            rowNumber++;
                            var row = $("<tr>").addClass("clickable-row");
                            //.append($("<td>").append(rowNumber).addClass("text-center"));
                            $.each(settings.columns, function (key, col) {
                                var textAlign;
                                row.append(
                                        $("<td>").append(val[col.item])
                                        .addClass((col.align !== undefined) ? "text-" + col.align : "text-center")
                                        );
                            });
                            tableBody.append(row);
                        });

                        console.log("tablehelp getPage() SUCCESS : ", data);
                    },
                    error: function (xhr, status, error) {
                        if (settings.onError !== undefined) {
                            settings.onError(xhr, status, error);
                        }
                    },
                    complete: function () {
                        $("#ui-tablehelp-div .ui-tablehelp-overlay").fadeOut("fast");
                    }
                });


            });
        });
    };
})(jQuery);

