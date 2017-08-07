/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {};
    var methods = {
        init: function (options) {
            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);

                if (settings.onError === undefined)
                    $.error("jquery.iskra.ajaxvalidation: missing onError handler in plugin options of " + "#" + obj.attr("id"));
                if (settings.urlToGetData === undefined)
                    $.error("jquery.iskra.ajaxvalidation: missing urlToGetData (url of servlet\service where data can be acquired) option in plugin options of " + "#" + obj.attr("id"));
                if (settings.fieldName === undefined)
                    $.error("jquery.iskra.ajaxvalidation: missing fieldName (name of the field to validate) option in plugin options of " + "#" + obj.attr("id"));

                obj.addClass("ui-ajv-input");

                var ajvPreviousValue;
                obj.focusout(function () {
                    var value = obj.val();
                    //var ajvPreviousValue = obj.data("ajv-prev-value");
                    if (value.length !== 0 && value !== ajvPreviousValue) {
                        $.ajax({
                            type: "get",
                            url: settings.urlToGetData,
                            dataType: "json",
                            data: {
                                q_item: settings.ajvFieldName,
                                q_value: value
                            },
                            timeout: 600000,
                            beforeSend: function () {
                                obj.attr("ajv-icon", "true");
                                obj.attr("valid-status", "pending");
                            },
                            success: function (data, status, xhr) {
                                if (data.valid) {
                                    obj.attr("valid-status", "valid");
                                    obj.errorpopup("hide");
                                    ajvPreviousValue = value;
                                } else {
                                    obj.errorpopup({
                                        "message": data.message,
                                        "container": $(obj).parents(".form-group")
                                    });
                                }
                            },
                            error: function (error) {
                                if (settings.onError !== undefined) {
                                    settings.onError(error);
                                }
                            },
                            complete: function () {
                            }
                        });
                    }
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
    $.fn.ajaxvalidation = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.iskra.ajaxvalidation');
        }
    };
})(jQuery);

