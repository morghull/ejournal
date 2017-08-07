/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "waitMessage": "Зачекайте, іде пошук інформації для допомоги...",
        "minLength": 0
    };
    var methods = {
        init: function (options) {

            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);

                if (settings.onError === undefined)
                    $.error("jquery.iskra.autocomplete: missing onError handler in plugin options of " + "#" + obj.attr("id"));
                if (settings.urlToGetData === undefined)
                    $.error("jquery.iskra.autocomplete: missing urlToGetData (url of servlet\service where data can be acquired) option in plugin options of " + "#" + obj.attr("id"));
                if (settings.tableName === undefined)
                    $.error("jquery.iskra.autocomplete: missing tableName (name of the table to get field value of) option in plugin options of " + "#" + obj.attr("id"));
                if (settings.fieldName === undefined)
                    $.error("jquery.iskra.autocomplete: missing fieldName (name of the field to get value of for autocomplete) option in plugin options of " + "#" + obj.attr("id"));

                obj.autocomplete({
                    source: function (request, response) {
                        $(".ui-helper-hidden-accessible").empty();
                        $(".ui-autocomplete").hide();
                        var atcWaitDiv = $("<div>").html(settings.waitMessage)
                                .prop("id", "atc-wait")
                                .css({
                                    "top": (obj.offset().top + obj.outerHeight()).toString() + "px",
                                    "left": obj.offset().left.toString() + "px",
                                    "width": obj.outerWidth().toString() + "px"
                                })
                                .addClass("ui-autocomplete-wait ui-widget-content");
                        $("body").append(atcWaitDiv);
                        $.ajax({
                            url: settings.urlToGetData,
                            type: "get",
                            data: {q_string_pattern: request.term,
                                q_table_name: settings.tableName,
                                q_field_name: settings.fieldName},
                            dataType: "json",
                            success: function (data) {
                                $("#atc-wait").remove();
                                response(data.autocomplete);
                            },
                            error: function (error) {
                                if (settings.onError !== undefined) {
                                    settings.onError(error);
                                }
                            },
                            complete: function () {
                                $("#atc-wait").remove();
                            }
                        });
                    },
                    minLength: settings.minLength
                });
            });
        },
        show: function () {

        },
        hide: function () {

        },
        update: function (content) {

        }
    };
    $.fn.iskraAutocomplete = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.iskra.autocomplete');
        }
    };
})(jQuery);