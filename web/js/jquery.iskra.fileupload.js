/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    var defaults = {
        "mimeType": "image/jpeg,image/bmp,image/png,application/pdf"
                + ",application/msword,application/vnd.ms-excel",
                //+ ",application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                //+ ",application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        "imitateKeyUp": true,
        "returnFocus": true
    };
    var methods = {
        init: function (options) {

            var settings = $.extend({}, defaults, options);
            return this.each(function () {
                var obj = $(this);
                obj.wrap($("<div>").addClass("ui-file-upload"));
                obj.attr("accept", settings.mimeType);
                var wrapper = obj.parents(".ui-file-upload");
                var placeholder = $("<div>")
                        .append("Натисніть щоб обрати файл")
                        .addClass("ui-file-upload-item ui-file-upload-placeholder");

                obj.change(function (e) {
                    var files = e.target.files;
                    var filesArr = Array.prototype.slice.call(files);
                    $(wrapper).find("div.ui-file-upload-item").remove();
                    if (filesArr.length > 0) {
                        filesArr.forEach(function (f) {
                            //var closeSpan = $("<span>").addClass("glyphicon glyphicon-remove");
                            var fileDiv = $("<div>")
                                    .append(f.name)
                                    .addClass("ui-file-upload-item ui-file-upload-file");
                            //.append(closeSpan);
                            $(wrapper).append(fileDiv);
                            //$(closeSpan).click(function(){
                            //    var index = filesArr.indexOf(f);
                            //    files.splice(index,1);
                            //});
                        });
                    } else
                        $(wrapper).append(placeholder);
                }).change();
                obj.parents("form").bind("reset", function () {
                    obj.val("");
                    obj.change();
                });
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