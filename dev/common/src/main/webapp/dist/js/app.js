
if (typeof jQuery === "undefined") {
  throw new Error("AdminLTE requires jQuery");
}

$.AdminLTE = {};



$(function () {
  "use strict";

  //Extend options if external options exist
  if (typeof AdminLTEOptions !== "undefined") {
    $.extend(true,
            $.AdminLTE.options,
            AdminLTEOptions);
  }


  //Set up the object
  _init();

  //Activate the layout maker
  $.AdminLTE.layout.activate();
  

});


function _init() {
  'use strict';

  $.AdminLTE.layout = {
    activate: function () {
      var _this = this;
      _this.fix();
      $(window, ".wrapper").resize(function () {
        _this.fix();
      });
    },
    fix: function () {
    	 var neg = $('.main-header').outerHeight() + $('.main-footer').outerHeight();
         var footer = $('.main-footer').outerHeight();
         var search = $('.search-box').outerHeight();
         var navtabs = $('.nav-tabs').outerHeight();
         var pullrightBtn = $('.pull-right').outerHeight();
         
         //var tabContent = $('.tab-content').outerHeight();
         //var boxContents = $('.box.contents').outerHeight();
         
         
         var window_height = $(window).height();
         var window_width = $(window).width();
     	
    	 if(window_width > 1200){
         	$(".content-wrapper").css('height', window_height - footer);
         	$(".tab-content").css('height', window_height - footer - navtabs - 80);
         }else{
            $(".tab-content").css('height', 'auto');
            $(".content-wrapper").css('height', 'auto');
        }         
     	
    	 // $(".content-wrapper").css('height', window_height - footer);
         // $(".tab-content").css('height', window_height - footer - navtabs - 80);            
         // $(".section-box").css('height', window_height - footer - navtabs - search  - 105);
         // $(".content-box.auto-height").css('height', window_height - tabContent - search  - pullrightBtn);             
         // $(".table-panel-search").css('height', window_height - footer - search - navtabs - 195);
         // $(".table-panel-search-pullrightBtnGroup").css('height', window_height - footer - search - pullrightBtnGroup - navtabs - 275);

            
	    }
	  }
	  

  }