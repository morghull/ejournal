/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "loupeSize": 200,
        "imgWidth": "85vw"
    };
    var methods = {
        init: function (options) {

            $(window).resize(function () {
                //$(".ui-imgmagnifier").resize();
            });

            var settings = $.extend({}, defaults, options);
            return this.each(function () {

//                if (settings.urlImg === undefined)
//                    $.error("jquery.iskra.imgmagnifier: missing urlImg option in plugin options of " + "#" + obj.attr("id"));
//                if (settings.urlImgZoom === undefined)
//                    $.error("jquery.iskra.imgmagnifier: missing urlImgZoom option in plugin options of " + "#" + obj.attr("id"));

                var obj = $(this);
                obj.addClass("ui-imgmagnifier");
                obj
                        .append($("<img>").attr("src", settings.urlImg).addClass("ui-imgmagnifier-img"))
                        .append($("<div>").addClass("ui-imgmagnifier-loupe")
                                .append(
                                        $("<img>").attr("src", settings.urlImgZoom)
                                        )
                                );
                obj.find(".ui-imgmagnifier-img").css({
                    "width": settings.imgWidth
                });
                obj.find(".ui-imgmagnifier-loupe").css({
                    "width": settings.loupeSize,
                    "height": settings.loupeSize
                });
                obj.find(".ui-imgmagnifier-loupe img").css({
                    "position": "absolute",
                    "width": settings.imgWidth
                });
                obj.resize(function () {
                    obj.find(".ui-imgmagnifier-img, .ui-imgmagnifier-loupe img").css({
                        "width": settings.imgWidth
                    });
                });
                obj.hover(function () {
                    obj.find(".ui-imgmagnifier-loupe").stop().fadeIn("fast");
                }, function () {
                    obj.find(".ui-imgmagnifier-loupe").stop().fadeOut("fast");
                });
                obj.mousemove(function (e) {
                    var elemPos = {},
                            offset = obj.offset();
                    elemPos = {
                        left: e.pageX - offset.left - settings.loupeSize / 2,
                        top: e.pageY - offset.top - settings.loupeSize / 2
                    };
                    obj.find(".ui-imgmagnifier-loupe").css({
                        "top": elemPos["top"],
                        "left": elemPos["left"]
                    });
                    obj.find(".ui-imgmagnifier-loupe img").css({
                        "top": -elemPos["top"],
                        "left": -elemPos["left"],
                        "width": settings.imgWidth
                    });
                });
            });
        },
        show: function () {

        },
        hide: function () {

        },
        update: function (options) {
            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);
                obj.find(".ui-imgmagnifier-img").attr("src", settings.urlImg);
                obj.find(".ui-imgmagnifier-loupe img").attr("src", settings.urlImgZoom);
            });
        }
    };
    $.fn.imgmagnifier = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.iskra.imgmagnifier');
        }
    };
})(jQuery);

