/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    $.fn.tablehelp = function () {
        $(document).mouseup(function (e) {
            var container = $("#ui-tablehelp-div");
            // if the target of the click isn't the container nor a descendant of the container
            if (container.is(":visible") && !container.is(e.target) && container.has(e.target).length === 0) {
                container.fadeOut("fast");//.hide();
            }
        });
        return this.each(function () {
            var obj = $(this);
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
                                $("<div>").addClass("ui-tablehelp-title").append("Tablehelp header")
                                );
                $("<div>").addClass("ui-tablehelp-overlay").css({"display": "none"}).appendTo("#ui-tablehelp-div");
                $("#ui-tablehelp-div").css({
                    "position": "absolute",
                    "z-index": "101",
                    "min-width": obj.outerWidth(),
                    "height": 100,
                    "left": obj.offset().left,
                    "top": obj.offset().top + obj.outerHeight()
                }).fadeIn("fast");
                $.ajax({
                    type: "get",
                    url: "/ejournal/servlets/ajax/rdtGetPage",
                    dataType: "json",
                    data: {
                        q_table_name: "rdt",
                        q_page_size: "15",
                        q_page_number: "1"
                    },
                    timeout: 600000,
                    beforeSend: function () {
                        $("#ui-tablehelp-div .ui-tablehelp-overlay").fadeIn("fast");
                    },
                    success: function (data, status, xhr) {
                        var tableBody = $("#content-table-body");
                        var rowNumber = $.iskra.pageSize * ($.iskra.pageNumber - 1);
                        tableBody.empty();
                        $.each(data.page, function (key, val) {
                            rowNumber++;
                            tableBody.append($("<tr>").addClass("clickable-row").attr("data-id", val.idd)
                                    .append($("<td>").append(rowNumber).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.rdk)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.rdn)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.rdd)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.nazz)).addClass("text-left"))
                                    .append($("<td>").append(stringFormat(val.rdsh)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.ordk) + "<br/>" + stringFormat(val.ordn)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.dvd)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.nzak)).addClass("text-center"))
                                    .append($("<td>").append(stringFormat(val.prim)).addClass("text-left"))
                                    .append($("<td>"))
                                    );
                        });
                        console.log("tablehelp getPage() SUCCESS : ", data);
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
                        console.log("tablehelp getPage() ERROR : ", error);
                    },
                    complete: function () {
                        $("#ui-tablehelp-div .ui-tablehelp-overlay").fadeOut("fast");
                    }
                });


            });
        });
    };
})(jQuery);

