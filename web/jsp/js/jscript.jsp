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
<script type="text/javascript">
    var tips = $(".validateTips");

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
            var err = $("<div>").append(message).addClass("ui-error-popup");
            /*o.after(err.show("fast"));*/
            o.parents(".form-group").append(err.show("fast"));
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
