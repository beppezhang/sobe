jQuery("#mobile").bind("blur",function(){
		var mobile = jQuery(this).val();
		var filter=/^1[3|4|5|7|8][0-9]\d{8}$/;
		if(mobile == ""){
			jQuery(this).find(".prompt_1").removeClass("ok").addClass("error");
			alert("手机号码不能为空");
			return;
		}else if(!filter.test(mobile)){
			jQuery(this).find(".prompt_1").removeClass("ok").addClass("error");
			alert("手机号码格式不正确");
			return;
		}else{
			jQuery.ajax({url : "ajaxCheckUsername.do",
						type : "POST",
						data : {'phone': mobile},
					dataType : "json",
					 success : function(data) {
						if (data.code == 0) {
						    if(data.result){
						    	jQuery(".mobile").find(".prompt_1").removeClass("ok").addClass("error");
						    	alert("手机号码存在");
						    	return;
						    }else{
						    	jQuery(".mobile").find(".prompt_1").removeClass("error").addClass("ok");
						    }
						} else {
							//alert("请求出错!");
						}
				     },
					   error : function() {
						//alert("请求出错!");
					 }
			});
		}
	});
jQuery("#companyName").bind("blur",function(){
	var companyName = jQuery(this).val();
	if(companyName == ""){
		jQuery(".companyName").find(".prompt_1").removeClass("ok").addClass("error");
		alert("公司名称不能为空");
		return;
	}else{
		jQuery(".companyName").find(".prompt_1").removeClass("error").addClass("ok");
	}
});

jQuery("#name").bind("blur",function(){
	var companyName = jQuery(this).val();
	if(companyName == ""){
		jQuery(".name1").find(".prompt_1").removeClass("ok").addClass("error");
		alert("收件人不能为空");
		return;
	}else{
		jQuery(".name1").find(".prompt_1").removeClass("error").addClass("ok");
	}
});

    jQuery("#verify").bind("blur",function(){
		var code = jQuery(this).val();
		if(code == ""){
			jQuery(".verify").find(".prompt_1").removeClass("ok").addClass("error");
			alert("验证码不能为空");
			return;
		}else{
			var phone=jQuery("#mobile").val();
			jQuery.ajax({
	    		url : "checkSmsCode.do",
	    		type : "POST",
	    		data : {"code":code,"phone":phone},
	    		dataType : "json",
	    		success : function(data) {
	    			if (data.code == 0) {
	    				jQuery(".verify").find(".prompt_1").removeClass("error").addClass("ok");
	    			} else {
	    				jQuery(".verify").find(".prompt_1").removeClass("ok").addClass("error");
	    				alert("验证码错误");
	    				return;
	    			}
	    		},
	    		error : function() {
	    			jQuery(".verify").find(".prompt_1").removeClass("ok").addClass("error");
	    			//alert("系统错误");
	    		}
	    	});
		}
	}); 
    jQuery(".validdateCode").bind("blur",function(){
    	var validateCode = jQuery(this).val();
    	if(validateCode == ""){
    		jQuery(".validdateCode").find(".prompt_1").removeClass("ok").addClass("error");
    		alert("校验码不能为空");
    		return;
    	}else{
    		var phone=jQuery("#validdateCode").val();
    		jQuery.ajax({
    			url : "checkValidDateCode.do",
    			type : "POST",
    			data : {"validateCode":validateCode},
    			dataType : "json",
    			success : function(data) {
    				if (data.code == 0) {
    					jQuery(".validdateCode").find(".prompt_1").removeClass("error").addClass("ok");
    					jQuery(".getvf").removeAttr("disabled").removeClass("off");
    				} else {
    					jQuery(".validdateCode").find(".prompt_1").removeClass("ok").addClass("error");
    					alert("校验码错误");
    					return;
    				}
    			},
    			error : function() {
    				jQuery(".validdateCode").find(".prompt_1").removeClass("ok").addClass("error");
    				//alert("系统错误");
    			}
    		});
    	}
    }); 
	
	jQuery(".password").bind("blur",function(){
		var password = jQuery(this).val();
		var reg = /^[a-zA-Z0-9]{6,16}$/;
		if(password == null|| password == ""){
			jQuery(".password").find(".prompt_1").removeClass("ok").addClass("error");
			alert("密码不能为空");
			return;
		}else if(!reg.test(password)){
			jQuery(".password").find(".prompt_1").removeClass("ok").addClass("error");
			alert("密码格式错误！");
			return;
		}else{
			jQuery(".password").find(".prompt_1").removeClass("error").addClass("ok");
		}
	});
	 
	jQuery(".picURL").bind("blur",function(){
		var picURL = jQuery(".picURL").val();
		if(picURL == ""){
			jQuery(".picURL").find(".prompt_1").removeClass("ok").addClass("error");
			alert("营业执照不能为空");
		}else{
			jQuery(".picURL").find(".prompt_1").removeClass("error").addClass("ok");	
		}
	}); 
	
	function register(){
        jQuery(".mobile").blur();
        jQuery(".validdateCode").blur();
        jQuery(".verify").blur();
    	jQuery(".password").blur();
    	jQuery(".companyName").blur();
    	jQuery(".name1").blur();
    	//jQuery(".picURL").blur();
    	var errorObj = jQuery(".error");
    	if(errorObj.length>0){
			 return;    		
    	}else{
    		jQuery(".registerForm").submit();
    	}
    }
    
    /** 发送验证码* */
    function sendSms(){
    	jQuery(".mobile").blur();
    	jQuery(".getvf").find(".prompt_1").removeClass("error").addClass("ok");
    	var errorObj = jQuery(".error");
    	if(errorObj.length>0){
    		return;
    	}
    	var i=60;
		var countdown = setInterval(cdTime, 1000);
		function cdTime() {
			jQuery(".getvf").attr("disabled",true).addClass("off").text( i + "秒后重新获取");
			if (i == 0) {
				jQuery(".getvf").removeAttr("disabled").removeClass("off").text("重新获取验证码");
				clearInterval(countdown);
			}
			i--;
		}
    	var mobile=jQuery("#mobile").val();
    	jQuery.ajax({
    		url : "sendRegisterSms.do",
    		type : "POST",
    		data : {"phone":mobile},
    		dataType : "json",
    		success : function(data) {
    			if (data.code == 0) {
    				jQuery(".getvf").find(".prompt_1").removeClass("error").addClass("ok");
    			}else if(data.code==2){
    				jQuery(".getvf").parent().find(".prompt_1").removeClass("ok").addClass("error");
    				alert("无法获取,单IP的请求次数不能超过五次");
    			} else {
    				jQuery(".getvf").find(".prompt_1").removeClass("ok").addClass("error");
    				alert("发送失败");
    			}
    		},
    		error : function() {
    			jQuery(".getvf").find(".prompt_1").removeClass("ok").addClass("error");
    			alert("发送失败");
    		}
    	});
    }
    
    function changeProvinceID(value) {
    	if (value != 0) {
    		jQuery.ajax({
    			url : "/getCityByParentID.do",
    			type : 'POST',
    			dataType : 'json',
    			data : {
    				'parentID' : value
    			},
    			success : function(data) {
    				if (data.code == 0) {
    					var areaAry = data.result;
    					jQuery("#cityid").empty();
    					for ( var id in areaAry) {
    						jQuery('#cityid').append(
    								"<option value=" + areaAry[id].mainID + ">"
    										+ areaAry[id].name + "</option>");
    					}
    					changeCity(areaAry[0].mainID);
    				}
    			}
    		});
    	}
    }
    
    function changeCity(value) {
    	if (value != 0) {
    		jQuery.ajax({
    			url : "/getCityByParentID.do",
    			type : 'POST',
    			dataType : 'json',
    			data : {
    				'parentID' : value
    			},
    			success : function(data) {
    				if (data.code == 0) {
    					var areaAry = data.result;
    					jQuery("#districtid").empty();
    					for ( var id in areaAry) {
    						jQuery('#districtid').append(
    								"<option value=" + areaAry[id].mainID + ">"
    										+ areaAry[id].name + "</option>");
    					}
    				}
    			}
    		});
    	}
    }
    
    function changeProvince(mainID) {
    	jQuery('#provinceID').val(mainID);

    }

    function changeProvinceIDs(value) {
    	if (value != 0) {
    		jQuery.ajax({
    			url : "getCityByParentID.do",
    			type : 'POST',
    			dataType : 'json',
    			data : {
    				'parentID' : value
    			},
    			success : function(data) {
    				if (data.code == 0) {
    					var areaAry = data.result;
    					jQuery("#cityids").empty();
    					for ( var id in areaAry) {
    						jQuery('#cityids').append(
    								"<option value=" + areaAry[id].mainID + ">"
    										+ areaAry[id].name + "</option>");
    					}
    					changeCitys(areaAry[0].mainID);
    				}
    			}
    		});
    	}
    }
    
    function changeCitys(value) {
    	if (value != 0) {
    		jQuery.ajax({
    			url : "/getCityByParentID.do",
    			type : 'POST',
    			dataType : 'json',
    			data : {
    				'parentID' : value
    			},
    			success : function(data) {
    				if (data.code == 0) {
    					var areaAry = data.result;
    					jQuery("#districtids").empty();
    					for ( var id in areaAry) {
    						jQuery('#districtids').append(
    								"<option value=" + areaAry[id].mainID + ">"
    										+ areaAry[id].name + "</option>");
    					}
    				}
    			}
    		});
    	}
    }