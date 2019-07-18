
$(document).ready(function(){
	 /* ===== Open-Close Menu Panel ===== */
     $('.menu-panel-toggle').on('click', function () {
         $(this).toggleClass('active');
         $(".menu-shade").toggleClass("shw-shade");
         $(".menu-panel").slideDown(50).toggleClass("shw-mpanel");    
     });
    $('.menu-shade').on('click', function () {
         $(".menu-shade").toggleClass("shw-shade");
         $(".menu-panel").slideDown(50).removeClass("shw-mpanel");
         $(".menu-panel-toggle").removeClass("active");
     });

        
});



