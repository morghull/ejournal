<%-- 
    Document   : initDialogPlaceholder
    Created on : Jun 21, 2017, 10:00:22 AM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
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
    });
</script>