(function($) {
    function Strenchbox(element, opts) {
        this.element = $(element);
        this.opts = $.extend({
            pad : 20,
            container : ".section-content",
            additional : 80,
            alignCenter : false,
            fhContent: false
        },opts);
        this.init();
    }

    Strenchbox.prototype.init = function() {
        var instance = this;
        $(window).resize(function(){
            var height = instance.element.height(),
                containerHeight = $(instance.opts.container).height();
            if($(window).height() <= containerHeight + instance.opts.pad * 2 + instance.opts.additional){
                instance.element.height(containerHeight + instance.opts.additional);
            } else {
                instance.element.height($(window).height() - instance.opts.pad * 2);
            }
            if(instance.opts.alignCenter){
                $(instance.opts.container).css("margin-top", (height - containerHeight) / 2);
            }
            if(instance.opts.fhContent){
                $(instance.opts.container).height($(window).height() - instance.opts.pad * 2 - instance.opts.additional);
            }
        });
        $(window).resize();
        if(this.opts.alignCenter){
            $(this.opts.container).css("margin-top", (this.element.height() - $(this.opts.container).height()) / 2);
        }
    };

    $.fn.extend({
        strenchbox: function(opts) {
            return this.each(function() {
                this.strenchbox = new Strenchbox(this, opts);
            });
        }
    });
})(jQuery);