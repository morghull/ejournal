<%-- 
    Document   : initCustomPageRefresh
    Created on : Jun 21, 2017, 9:14:20 PM
    Author     : Morghull
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        //this prevents native refreshing after pressing F5 button, and makes it customized
        function disableF5(e) {
            if ((e.which || e.keyCode) === 116 || ((e.which || e.keyCode) === 82) && e.ctrlKey) {
                e.preventDefault();
                $.iskra.getPage();
            }
        }
        $(document).ready(function () {
            $(document).on("keydown", disableF5);
        });
    });
</script>
