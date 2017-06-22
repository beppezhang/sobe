(function($){ 
$.fn.extend({  
		timgroll:function(options) { 
		var defaults = {   
         timgn:".timgn",
		 timgi:".timgi",
		 numn:"cur",
		 time1:4000};   
               
var o = $.extend(defaults, options);   
return this.each(function() {  
var n=0;
var $tag1=$(o.timgn).children().eq(0).attr("tagName");
var $tag2=$(o.timgi).children().eq(0).attr("tagName");

var $allnum=$(o.timgn).children($tag1).length;
function numc(n){$(o.timgn).children($tag1).attr("class"," ").eq(n).addClass(o.numn);} 
function imgc(n){$(o.timgi).children($tag2).hide().eq(n).show();}
function main0(n){
	imgc(n);
	numc(n);
	}
function auto(){n++;if(n>$allnum-1){n=0;};main0(n);} 
var autoStart=window.setInterval( function(){auto(n);}, o.time1);
function clearAuto(){clearInterval(autoStart)}

$(o.timgn).children($tag1).click(function(){
var $cnum=$(o.timgn).children($tag1).index(this);
n=$cnum;
imgc(n);
numc(n);
}); 

$(this).hover(function(){clearAuto();},function(){autoStart=window.setInterval( function(){auto(n);}, o.time1);});
//
});
}
});
})(jQuery);

/* 幻灯滚动 */



