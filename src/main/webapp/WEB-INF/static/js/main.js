define(["jquery", "text!html/base.html", "models/time"], function($, baseHtml, time) {

    $(function() {
        $("body").html(baseHtml);
        
        time();
        
    });
});