// JavaScript Document

function gHeight(){
		var cHeight = $(window).height();
		$("#toolbar").css("height", cHeight);
	}
$(window).resize(function() {
	gHeight()	
})

$(function(){
	
	// check placeholder
	if(!placeholderSupport()){   
		$('[placeholder]').focus(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
				input.val('');
				input.css("color","#595959")
				input.removeClass('placeholder');
			}
		}).blur(function() {
			var input = $(this);
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
				input.addClass('placeholder');
				input.css("color","#999999")
				input.val(input.attr('placeholder'));
			}
		}).blur();
	};

	// back to top
	var ScrolltoTop=$("#totop");
	$(ScrolltoTop).click(function(){
			$("html,body").animate({scrollTop:0},200);return false
	});
	//下拉
	$('.goDown').hover(function(){
		$(this).find(".child").stop().animate({height:"toggle"},100);
	},function(){
		$(this).find(".child").stop().animate({height:"toggle"},100);
	});
	$('.goLeft').hover(function(){
		$(this).find(".child").stop().animate({width:"toggle"},100);
	},function(){
		$(this).find(".child").stop().animate({width:"toggle"},100);
	});
	//tr bgcolor
	$("tr:odd",this).addClass('bg');
	//tabs
	 $(".tab span").click(function(e){
		 e.preventDefault();
		 $(this).addClass('cur').siblings().removeClass();
		 $(".con div.none").eq($(".tab span").index(this)).addClass("cur").siblings().removeClass("cur");
	 })
	// img move
	$('.imgMove').hover(function(){
		$(this).stop().animate({marginRight:"10px"},300);
	},function(){
		$(this).stop().animate({marginRight:0},300);
	});
	
	
	//counter
	/*$(".number_control").on("click", "a", function(){
			var _t = $(this);
			var _v = _t.parent("span").find("input");
			var _r = _t.hasClass("prev") ? $(this) : $(this).next();
			if(_t.hasClass("prev") && _v.val() <= 1){
				return;
			}
			_v.val(_t.hasClass("prev") ? parseInt(_v.val()) - 1 : parseInt(_v.val()) + 1);
	});*/
	//menu 
	$("#nav").on("click", "h2 a", function(){
		$(this).closest("h2").next("#category").toggleClass("active");
	});
	$("#category").mouseleave(function(){
		$(this).removeClass("active");	
	})
	$("#sidernav").on("click","li.firNav a.firLink",function(){
			var checkUl = $(this).next("ul.secNav").length;
			if(checkUl>0){
				$(this).closest("li.firNav").toggleClass("cur").siblings().removeClass("cur");
			}
	})
    $(".toTop").hide();
    $(window).scroll(function () {
        if($(window).scrollTop()=="0") {
            $(".toTop").fadeOut(200);
        }else {
            $(".toTop").fadeIn(300);
        }
    });
	$(".toTop").click(function(){
        $("html,body").animate({scrollTop:0},200);return false
    });
	
	gHeight();
})
	
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}






