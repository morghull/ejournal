/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "pageSize": 15,
        "imitateKeyUp": true,
        "returnFocus": true
    };
    var methods = {
        init: function (options) {
            $(document).mousedown(function (e) {
                var container = $("#ui-tablehelp-div");
                // if the target of the click isn't the container nor a descendant of the container
                if (container.is(":visible") && !container.is(e.target) && container.has(e.target).length === 0) {
                    container.hide();
                }
            });
            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);

                if (settings.onError === undefined)
                    $.error("jquery.iskra.tablehelp: missing onError handler in plugin options of " + "#" + obj.attr("id"));
                if (settings.columns === undefined)
                    $.error("jquery.iskra.tablehelp: missing columns description option in plugin options of " + "#" + obj.attr("id"));
                if (settings.urlToGetData === undefined)
                    $.error("jquery.iskra.tablehelp: missing urlToGetData (url of servlet\service where data can be acquired) option in plugin options of " + "#" + obj.attr("id"));
                if (settings.itemToPickup === undefined)
                    $.error("jquery.iskra.tablehelp: missing itemToPickup (data item value to pickup for help) option in plugin options of " + "#" + obj.attr("id"));
                var columnsWithInvalidItems = $.grep(settings.columns, function (c) {
                    return (c.caption === undefined || c.item === undefined);
                });
                if (settings.columns.length === 0 || columnsWithInvalidItems.length !== 0)
                    $.error("jquery.tablehelp: columns description array have invalid format in plugin options of " + "#" + obj.attr("id"));

                //obj.attr("readonly", true);
                obj.attr("autocomplete", "off");
                obj.keydown(function (e) {
                    var container = $("#ui-tablehelp-div");
                    if ([9, 13, 27].indexOf(e.which || e.keyCode) >= 0 && container.is(":visible")) {
                        container.hide();
                    }
                });
                obj.focus(function () {
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
                    $("<table>").addClass("ui-tablehelp-table")
                            .append($("<thead>"), $("<tbody>"))
                            .appendTo("#ui-tablehelp-div");
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
                            $("#ui-tablehelp-div").css({"min-height": "200px"});
                        },
                        success: function (data, status, xhr) {
                            var tableBody = $("#ui-tablehelp-div table tbody");
                            var rowNumber = settings.pageSize * (1 - 1);
                            tableBody.empty();
                            $.each(data.page, function (key, val) {
                                rowNumber++;
                                var row = $("<tr>").attr("item", val[settings.itemToPickup]);
                                //.append($("<td>").append(rowNumber).addClass("text-center"));
                                $.each(settings.columns, function (key, col) {
                                    row.append(
                                            $("<td>").append(val[col.item])
                                            .addClass((col.align !== undefined) ? "text-" + col.align : "text-center")
                                            );
                                });
                                tableBody.append(row);
                            });
                            $("#ui-tablehelp-div table tbody tr").click(function () {
                                obj.val($(this).attr("item"));
                                if (settings.imitateKeyUp)
                                    obj.keyup();
                                if (settings.imitateKeyPress)
                                    obj.kepress();
                                if (settings.returnFocus)
                                    obj.focus();
                                $("#ui-tablehelp-div").hide();
                            });

                            console.log("tablehelp getPage() SUCCESS : ", data);
                        },
                        error: function (error) {
                            if (settings.onError !== undefined) {
                                settings.onError(error);
                            }
                        },
                        complete: function () {
                            $("#ui-tablehelp-div .ui-tablehelp-overlay").fadeOut("fast");
                            $("#ui-tablehelp-div").css({"min-height": ""});
                        }
                    });
                });
            });
        },
        show: function () {
            var obj = $(this);
            obj.focus();
        },
        hide: function () {

        },
        update: function (content) {

        }
    };
    $.fn.tablehelp = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.iskra.tablehelp');
        }
    };
})(jQuery);

