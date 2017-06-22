// JavaScript Document, designed by jsann, for has form pages

$(function(){
	
	//记录校验错误消息
	var verify = {
		error : false,
		errorType : []
	};
	
	//校验非空
	//data-nonempty-isTrim : {boolean}是否将连续的空格也当作空, 非必须, 当不为true的时候均算着false
	$(".verify_nonempty").live({
		blur : function(){
			verify.errorType = $.grep(verify.errorType, function(n){
				return n === "nonEmpty";
			}, true);
			if(verify.errorType.toString() && $(this).parents(".dialog").length && $(this).parents(".dialog").hasClass("dialog_error")){
				verify.error = true;
				verify.errorValue = $(this).parents(".dialog").attr("data-error-value") || "";
			}
			var _val = $(this).val(), _err = "内容不能为空";
			if($(this).attr("data-nonempty-isTrim") && $(this).attr("data-nonempty-isTrim") === "true"){
				_val = $.trim(_val);
				_err += "或者连续的空格";
			}
			if(!_val){
				verify.errorType.push("nonEmpty");
				$(this).addClass("text_error");
				$(this).parents(".dialog").addClass("dialog_error").attr("data-error-value", _err);
			}else{
				verify.errorType = $.grep(verify.errorType, function(n){
					return n === "nonEmpty";
				}, true);
				if(!verify.errorType.toString() && !verify.error){
					$(this).removeClass("text_error");
					$(this).parents(".dialog").removeClass("dialog_error");
				}
				$(this).parents(".dialog").attr("data-error-value", verify.errorValue);
			}
		},
		keyup : function(){
			var _val = $(this).val();
			if($(this).attr("data-nonempty-isTrim") && $(this).attr("data-nonempty-isTrim") === "true"){
				_val = $.trim(_val);
			}
			if(_val){
				verify.errorType = $.grep(verify.errorType, function(n){
					return n === "nonEmpty";
				}, true);
				if(!verify.errorType.toString()){
					if(!verify.error){
						$(this).removeClass("text_error");
						$(this).parents(".dialog").removeClass("dialog_error");
					}
					$(this).parents(".dialog").attr("data-error-value", verify.errorValue);
				}
			}
		}
	});
	
	//校验是否是数字
	$(".verify_isnan").live({
		blur : function(){
			verify.errorType = $.grep(verify.errorType, function(n){
				return n === "isNaN";
			}, true);
			if(verify.errorType.toString() && $(this).parents(".dialog").length && $(this).parents(".dialog").hasClass("dialog_error")){
				verify.error = true;
				verify.errorValue = $(this).parents(".dialog").attr("data-error-value") || "";
			}
			var _val = $(this).val();
			if(isNaN(_val)){
				verify.errorType.push("isNaN");
				$(this).addClass("text_error");
				$(this).parents(".dialog").addClass("dialog_error").attr("data-error-value", "请确认输入的是纯数字");
			}else{
				verify.errorType = $.grep(verify.errorType, function(n){
					return n === "isNaN";
				}, true);
				if(!verify.errorType.toString() && !verify.error){
					$(this).removeClass("text_error");
					$(this).parents(".dialog").removeClass("dialog_error");
				}
				$(this).parents(".dialog").attr("data-error-value", verify.errorValue);
			}
		},
		keyup : function(){
			if(!isNaN($(this).val())){
				verify.errorType = $.grep(verify.errorType, function(n){
					return n === "isNaN";
				}, true);
				if(!verify.errorType.toString()){
					if(!verify.error){
						$(this).removeClass("text_error");
						$(this).parents(".dialog").removeClass("dialog_error");
					}
					$(this).parents(".dialog").attr("data-error-value", verify.errorValue);
				}
			}
		}
	});
	
	//校验文字长度
	//data-length : {number}长度, 必须
	//data-error-value : {string}校验错误的提示文字, 非必须
	//data-length-follow : {boolean}显示当前文字长度, 非必须, 当不为true的时候均算着false, (前提: verify and verify_handle)
	//data-length-turn : {boolean}显示剩余文字长度, 非必须, 当不为true的时候均算着false, (前提: verify and verify_handle)
	$(".verify_textlength").live({
		focus : function(){
			if($(this).parents(".verify").length && $(this).parents(".verify").find(".verify_handle").length && !verify.color){
				var verify_handle = $(this).parents(".verify").find(".verify_handle");
				verify.color = verify_handle.css("color");
			}
		},
		keyup : function(){
			verify.errorType = $.grep(verify.errorType, function(n){
				return n === "textLength";
			}, true);
			if(verify.errorType.toString() && $(this).parents(".dialog").length && $(this).parents(".dialog").hasClass("dialog_error")){
				verify.error = true;
				verify.errorValue = $(this).parents(".dialog").attr("data-error-value") || "";
			}
			var text = $(this).val(), verify_handle, nonempty = false, expect_length = $(this).attr("data-length");
			if($(this).parents(".verify").length && $(this).parents(".verify").find(".verify_handle").length){
				verify_handle = $(this).parents(".verify").find(".verify_handle");
			}
			if(text.length > expect_length){
				verify.errorType.push("textLength");
				$(this).addClass("text_error");
				var _error_value = "输入字数太多，请再仔细检查。";
				if($(this).attr("data-error-value")){
					_error_value = $(this).attr("data-error-value");
				}
				$(this).parents(".dialog").addClass("dialog_error").attr("data-error-value", _error_value);
				if(verify_handle){
					verify_handle.css({
						color : "#f00",
						fontWeight : "bold"
					});
				}
			}else{
				verify.errorType = $.grep(verify.errorType, function(n){
					return n === "textLength";
				}, true);
				if(!verify.errorType.toString()){
					$(this).removeClass("text_error");
					$(this).parents(".dialog").removeClass("dialog_error").attr("data-error-value", verify.errorValue);
				}
				if(verify_handle){
					verify_handle.css({
						color : verify.color,
						fontWeight : "normal"
					});
				}
			}
			if(verify_handle && verify_handle.attr("data-length-follow") && verify_handle.attr("data-length-follow") === "true"){
				if(verify_handle.attr("data-length-turn") && verify_handle.attr("data-length-turn") === "true"){
					verify_handle.text(expect_length - text.length);
				}else{
					verify_handle.text(text.length);
				}
			}
		}
	});
	
	//提示文本(显示在文本框后面)
	//data-prompt-text : (string)提示内容, 必须
	$(".prompt_text").live({
		focus : function(){
			if($(this).nextAll(".wait, .ok, .error, .help").length){
				$(this).nextAll(".wait, .ok, .error, .help").replaceWith("<span class='prompt_tip'>" + $(this).attr("data-prompt-text") + "</span>");
			}else{
				$(this).after("<span class='prompt_tip'>" + $(this).attr("data-prompt-text") + "</span>");
			}
		},blur : function(){
			$(this).nextAll(".prompt_tip").fadeOut(function(){
				$(this).remove();
			});
		}
	})
	
	//提示文本(显示在文本框里面)
	//data-think-value : {string}默认的提示文本, 必须
	//data-think-isTrim : {boolean}是否清除开始和结束处的连续空格, 非必须, 当不为true的时候均算着false
	$(".think_value").each(function(index, el){
		var val = $(el).val();
		if($(el).attr("data-think-isTrim") && $(el).attr("data-think-isTrim") === "true"){
			val = $.trim(val);
		}
		if(val === $(el).attr("data-think-value")){
			$(el).attr("data-think-color", $(el).css("color")).css({
				color : "#ccc"
			});
		}
	}).live({
		focus : function(){
			var val = $(this).val();
			if($(this).attr("data-think-isTrim") && $(this).attr("data-think-isTrim") === "true"){
				val = $.trim(val);
			}
			if(val === $(this).attr("data-think-value")){
				$(this).val("");
				if($(this).attr("data-think-color")){
					$(this).css({
						color : $(this).attr("data-think-color")
					});
				}
			}
		},
		blur : function(){
			var val = $(this).val();
			if($(this).attr("data-think-isTrim") && $(this).attr("data-think-isTrim") === "true"){
				val = $.trim(val);
			}
			if(val === "" || val === $(this).attr("data-think-value")){
				$(this).attr("data-think-color", $(this).css("color")).css({
					color : "#ccc"
				}).val($(this).attr("data-think-value"));
			}
		}
	});
	
	//就地编辑
	var __but_text__ = "";
	$(".place_editing_but").bind("click", function(){
		var editing = $(".place_editing[data-name='"+ $(this).attr("data-name") +"']"), old_value = editing.text();
		__but_text__ === "" ? __but_text__ = $(this).text() : "";
		if(editing.find("input").length){
			var titleValue = editing.find("input").val();
			editing.text(titleValue);
			$(this).text(__but_text__).next("a.place_editing_but").remove();
			return false;
		}
		editing.html("<input class='text' value='" + old_value + "'>");
		$(this).clone(false).text("[取消]").insertAfter($(this).text("[保存]")).unbind().bind("click", function(){
			editing.text(old_value);
			$(this).prev("a.place_editing_but").text(__but_text__);
			$(this).remove();
		});
	});
});