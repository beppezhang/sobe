// JavaScript Document
(function($){
$.fn.cd=function(){
	var data="";
	var _DOM=null;
	var timer;
	createdom =function(dom){
		_DOM=dom;
		data=$(dom).attr("data");
		data = data.replace(/-/g,"/");
		data = Math.round((new Date(data)).getTime()/1000);
		//$(_DOM).append("仅剩 <em class='cdDay'></em> 天 <em class='cdHour'></em> : <em class='cdMin'></em> : <em class='cdSec'></em>");
		reflash();

	};
	reflash=function(){
		var	range  	= data-Math.round((new Date()).getTime()/1000),
					secday = 86400, sechour = 3600,
					days 	= parseInt(range/secday),
					hours	= parseInt((range%secday)/sechour),
					min		= parseInt(((range%secday)%sechour)/60),
					sec		= ((range%secday)%sechour)%60;
		$(_DOM).find(".cdDay").html(nol(days));
		$(_DOM).find(".cdHour").html(nol(hours));
		$(_DOM).find(".cdMin").html(nol(min));
		$(_DOM).find(".cdSec").html(nol(sec));
		if(range<=0){
			$(_DOM).html("已结束，下次请早！");//$(_DOM).closest(".countdown").nextAll(".operate").children(".gobuy").addClass("shut").attr({"href":"javascript:","target":"_self"}).html("已结束")
		}
	};
	
	timer = setInterval( reflash,1000 );
	nol = function(h){
					return h>9?h:'0'+h;
	}
	return this.each(function(){
		var $box = $(this);
		createdom($box);
	});
}
})(jQuery);
$(function(){
	$(".cdbox").each(function(){
		$(this).cd();
	});
});