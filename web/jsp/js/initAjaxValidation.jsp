<%-- 
    Document   : initAjaxValidation
    Created on : Jul 10, 2017, 1:39:23 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        /*$("#dialog-form input:text[required]").each(function () {
         $(this).focusout(function () {
         $(this).parents(".form-group").find(".error-popup").remove();
         var value = $(this).val();
         if (value.length === 0)
         {
         errorPopup(this, $(this).attr("required-message"), $(this).parents(".form-group"));
         }
         });
         });*/
        $("#dialog-form input:text[ajax-validation=\"on\"]").each(function () {
            var ajvUrlPattern = $(this).attr("ajv-url-pattern");
            var ajvFieldName = $(this).attr("ajv-field-name");
            var obj = $(this);
            obj.focusout(function () {
                var value = obj.val();
                var ajvPreviousValue = obj.attr("ajv-prev-value");
                if (value.length !== 0 && value !== ajvPreviousValue) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.servletContext.contextPath}/servlets/api/" + ajvUrlPattern,
                        dataType: "json",
                        data: {
                            q_item: ajvFieldName,
                            q_value: value
                        },
                        timeout: 600000,
                        beforeSend: function () {
                            obj.attr("ajv-icon", "true");
                            obj.attr("valid-status", "pending");
                        },
                        success: function (data, status, xhr) {
                            if (data.valid) {
                                obj.attr("valid-status", "valid");
                                obj.parents(".form-group").find(".error-popup").remove();
                                obj.attr("ajv-prev-value", value);
                            } else {
                                errorPopup(obj, data.message, $(obj).parents(".form-group"));
                            }
                        },
                        error: function (xhr, status, error) {
                            riseAnError("ajaxValidation", xhr, status, error);
                        },
                        complete: function () {
                        }
                    });
                }
            });
        });
    });
</script>