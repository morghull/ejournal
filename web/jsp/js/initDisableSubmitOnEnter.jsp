<%-- 
    Document   : initDisableSubmitOnEnter
    Created on : Jul 11, 2017, 4:08:27 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        //this prevents submiting on pressing "enter" key
        function disableSubmitOnEnter(e) {
            if ((e.which || e.keyCode) === 13)
                e.preventDefault();
        }
        $(document).ready(function () {
            $(document).on("keydown", disableSubmitOnEnter);
        });
    });
</script>
