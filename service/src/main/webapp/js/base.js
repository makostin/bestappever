(function ($) {
    $(document).ready(function () {
        /* Markup tricks*/
        if ($(".main-section").hasClass("no-center")) {
            $(".main-section").strenchbox({
                alignCenter: false,
                additional: 120
            });
        } if ($(".main-section").hasClass("fh-content")) {
            $(".main-section").strenchbox({
                alignCenter: false,
                additional: 100,
                fhContent: true
            });
        } else {
            $(".main-section").strenchbox({
                alignCenter: true
            });
        }
        $("select").dropdown();

        /* Place date */
        var date = Date.today().toString("d-MMM-yyyy").split("-"),
            month = date[1];
        switch (month) {
            case "Jan":
                month = "Января";
                break;
            case "Feb":
                month = "Февраля";
                break;
            case "Mar":
                month = "Марта";
                break;
            case "Apr":
                month = "Апреля";
                break;
            case "May":
                month = "Мая";
                break;
            case "Jun":
                month = "Июня";
                break;
            case "Jul":
                month = "Июля";
                break;
            case "Aug":
                month = "Августа";
                break;
            case "Sep":
                month = "Сентября";
                break;
            case "Oct":
                month = "Октября";
                break;
            case "Nov":
                month = "Ноября";
                break;
            case "Dec":
                month = "Декабря";
                break;
        }

        $(".date-holder").text(date[0] + " " + month + " " + date[2]);
        /* Button behaviour */
        $("input[type='button']").mouseup(
            function () {
                $(this).css({
                    "border-width": "2px",
                    "line-height": "34px"
                });
            }).mousedown(function () {
                $(this).css({
                    "border-width": "0px",
                    "line-height": "36px"
                });
        });

        $(".tab-menu li").click(function(){
            $(".tab-menu li").removeClass("active");
            $(this).addClass("active");
            $(".tabs-container .tab").hide();
            $(".tabs-container .tab").eq($(this).index()).show();
        });
        $(".tab-menu li a").click(function(e){
            e.preventDefault();
        });

        /* Editable */
        $(".subject-tab .edit").editable();
    });
})(jQuery);