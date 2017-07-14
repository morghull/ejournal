<%-- 
    Document   : initDisableSubmitOnEnter
    Created on : Jul 11, 2017, 4:08:27 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        //this prevents submiting on pressing "enter" key, and makes move to next inpute:text element
        function disableSubmitOnEnter(e) {
            if ((e.which || e.keyCode) === 13) {
                e.preventDefault();
                var inputs = $('#dialog-form').find('input:text');
                if ($("#dialog-form input:focus").length != 0)
                    inputs.eq(inputs.index($("#dialog-form input:focus")) + 1).focus();
            }
        }
        $(document).ready(function () {
            $(document).on("keydown", disableSubmitOnEnter);
        });
    });
</script>
