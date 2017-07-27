/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "mime-type": "application/pdf,image/jpeg,image/bmp,application/msword"
                + ",image/png,application/excel",
        "imitateKeyUp": true,
        "returnFocus": true
    };
    var methods = {
        init: function (options) {

            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);
                obj.wrap($("<div>").addClass("ui-file-upload"));

                var wrapper = obj.parents(".ui-file-upload");
                var placeholder = $("<div>")
                        .append("Натисніть щоб обрати файл")
                        .addClass("ui-file-upload-item ui-file-upload-placeholder");

                $(wrapper).append(placeholder);
                obj.change(function (e) {
                    var files = e.target.files;
                    var filesArr = Array.prototype.slice.call(files);
                    if (filesArr.length > 0) {
                        $(wrapper).find("div.ui-file-upload-item").remove();
                        filesArr.forEach(function (f) {
                            var fileDiv = $("<div>")
                                    .append(f.name)
                                    .addClass("ui-file-upload-item ui-file-upload-file");
                            $(wrapper).append(fileDiv);
                        });
                    } else
                        console.log("test");//$("#ui-file-upload-wrapper").append(placeholder);
                }).change();
            });
        },
        show: function ( ) {

        },
        hide: function ( ) {

        },
        update: function (content) {

        }
    };
    $.fn.fileupload = function (method) {
        // логика вызова метода
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.fileupload');
        }
    };
})(jQuery);