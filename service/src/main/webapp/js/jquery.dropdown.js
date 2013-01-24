(function($) {
    function Dropdown(element, opts) {
        this.element = $(element);
        this.opts = $.extend({
        },opts);
        this.init();
    }

    Dropdown.prototype.init = function() {
        var instance = this,
        	dropdown = $("<div></div>").addClass("ui-dropdown"),
        	dropdownLabel = $("<div></div>").addClass("ui-dropdown-label"),
        	dropdownItems = $("<ul></ul>").addClass("ui-dropdown-items"),
        	dropdownArrow = $("<div></div>").addClass("ui-dropdown-arrow")
        									.click(function(event){
        										event.stopPropagation()

        										if(dropdownItems.is(":visible")){
        											dropdownItems.css("z-index","1");
        											dropdownItems.slideUp("fast");
        										} else {
        											dropdownItems.css("z-index","100");
        											dropdownItems.slideDown("fast");
        										}
        									});

        this.element.after(dropdown);
        dropdown.append(this.element)
        		.append(dropdownLabel)
        		.append(dropdownArrow)
        		.append(dropdownItems);

        // Append items
        var html = "";
        this.element.find("option").each(function() {
            var key = $(this).val()
            html += "<li key='" + key + "'><a href='#'>" + $(this).text() + "</a></li>";
        });
        dropdownItems.html(html);

        // Trigger a click
        dropdownItems.find("li a").click(function(event){
            event.preventDefault();
            event.stopPropagation();

            dropdownItems.find(".selected").removeClass("selected");
            $(this).addClass("selected");

            // Trigger select
            instance.element.val($(this).parent().attr("key"));
            instance.element.change();

            // Fill dropdown field
            dropdownLabel.text($(this).text());
            dropdownItems.css("z-index","1");
            dropdownItems.hide();
        });

        // Select right element
        if(this.element.val()){
            dropdownItems.find("li[key='" + instance.element.val() + "'] a").click();
        } else{
            dropdownItems.find("li a").eq(0).click();
        }

        $('html').click(function() {
            dropdownItems.slideUp("fast");
        });
    };

    $.fn.extend({
        dropdown: function(opts) {
            return this.each(function() {
                this.dropdown = new Dropdown(this, opts);
            });
        }
    });
})(jQuery);