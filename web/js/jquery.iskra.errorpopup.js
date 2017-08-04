/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "message": "an error has occured!"
    };
    var methods = {
        init: function (options) {
            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);

                if (settings.container === undefined)
                    $.error("jquery.iskra.errorpopup: missing container (container where error will pops) option in plugin options of " + "#" + obj.attr("id"));

                $(obj).attr("valid-status", "invalid");
                var oldoneErrorPopup = $(settings.container).find(".ui-error-popup");
                if (oldoneErrorPopup.length !== 0)
                    $(settings.container).find(".ui-error-popup").empty().append(settings.message);
                else {
                    var err = $("<div>").append(settings.message).addClass("ui-error-popup").css("margin-left", $(obj).position().left + 10);
                    $(settings.container).append(err.show("fast").css("display", "inline-block"));
                }
                obj.data("errorpopup-container", settings.container);
            });
        },
        show: function () {

        },
        hide: function () {
            return this.each(function () {
               var obj = $(this);
                if (obj.data("errorpopup-container") !== undefined)
                    obj.data("errorpopup-container").children(".ui-error-popup").remove();
                //else $.error("jquery.errorpopup: plugin is not initialized for " + "#" + obj.attr("id"));
            });
        },
        update: function (content) {

        }
    };
    $.fn.errorpopup = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.iskra.errorpopup');
        }
    };
})(jQuery);

