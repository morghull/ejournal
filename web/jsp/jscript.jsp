<%-- 
    Document   : jscript
    Created on : Nov 11, 2016, 10:38:13 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="${pageContext.servletContext.contextPath}/js/jquery-1.12.4.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min-3.1.1.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/jquery-datapicker-ukr-localization.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/common-interface-init.js"></script>
<script type="text/javascript">
    var tips = $(".validateTips");

    $(function () {
        //clickable table row click
        $('table' || '#table' || '.table').on('click', '.clickable-row', function (event) {
            $(this).addClass('active').siblings().removeClass('active').children().removeClass('border-right-none');
            $(this).children("td:not(.hidden):last").addClass('border-right-none');
            $("#footer-placeholder").html($(this).children("#hidden-content").html());
            if ($("#currentId").length) {
                $("#currentId").empty().append($(this).attr("data-id"));
            } else {
                $("body").append($("<span>").prop("id", "currentId").attr("new-one","false").addClass("hidden").append($(this).attr("data-id")));
            }
        });
    });

    // this creates overlay placeholder with preloader gif
    //$("body").append(
    //        $("<div>")
    //        .prop("id", "overlay-wrapper")
    //        .addClass("overlay")
    //        .hide()
    //        );

    // this creates dialog placeholder for deletion confirmation
    $("body").append($("<div>")
            .prop("id", "dialog-confirm-deletion")
            .prop("title", "Видалити обраний запис?")
            .append($("<p>")
                    .prop("id", "confirm-content")
                    .html("Чи дійсно ви бажаєте видатили обраний запис?</br>Обраний запис буде видалено з БД")
                    )
            .hide()
            );

    // this creates dialog placeholder for error message        
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

    function stringFormat(s) {
        return (typeof s === 'string' && !s.trim() || typeof s === 'undefined' || s === null) ? "&nbsp;" : s;
    }
    function getScrollBarWidth() {
        var $outer = $('<div>').css({visibility: 'hidden', width: 100, overflow: 'scroll'}).appendTo('body'),
                widthWithScroll = $('<div>').css({width: '100%'}).appendTo($outer).outerWidth();
        $outer.remove();
        return 100 - widthWithScroll;
    }
    function resetTips() {
        tips.text("").removeClass("ui-state-highlight");
    }
    function updateTips(t) {
        tips.text(t).addClass("ui-state-highlight");
    }
    function checkLength(o, min, max, message) {
        if (o.val().length > max || o.val().length < min) {
            o.addClass("ui-state-error", 250);
            updateTips(message);
            o.focus();
            return false;
        } else {
            return true;
        }
    }
    function checkRegexp(o, regexp, message) {
        if (!(regexp.test(o.val()))) {
            o.addClass("ui-state-error", 250);
            updateTips(message);
            o.focus();
            return false;
        } else {
            return true;
        }
    }
</script>
