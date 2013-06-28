define(["jquery", "text!html/base.html"], function($, baseHtml) {

    $(function() {
        $("body").html(baseHtml);
    });
});