jQuery("#mobile").bind("blur",function(){
		var mobile = jQuery(this).val();
		var filter=/^1[3|4|5|7|8][0-9]\d{8}$/;
		if(mobile == ""){
			jQuery(this).parent().find(".prompt_1").removeClass("ok").addClass("error").html("手机号码不能为空");
		}else if(!filter.test(mobile)){
			jQuery(this).parent().find(".prompt_1").removeClass("ok").addClass("error").html("手机号码格式不正确");
		}else{
			jQuery.ajax({url : "ajaxCheckUsername.do",
						type : "POST",
						data : {'phone': mobile},
					dataType : "json",
					 success : function(data) {
						if (data.code == 0) {
						    if(data.result){
						    	jQuery(".mobile").parent().find(".prompt_1").removeClass("ok").addClass("error").html("手机号码存在");
						    }else{
						    	jQuery(".mobile").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
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
			jQuery(this).parent().find(".prompt_1").removeClass("ok").addClass("error").html("公司名称不能为空");
		}else{
			jQuery("#companyName").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
		}
	});
	jQuery("#name").bind("blur",function(){
		var companyName = jQuery(this).val();
		if(companyName == ""){
			jQuery(this).parent().find(".prompt_1").removeClass("ok").addClass("error").html("昵称不能为空");
		}else{
			jQuery.ajax({url : "ajaxCheckName.do",
						type : "POST",
						data : {'name': companyName},
					dataType : "json",
					 success : function(data) {
						if (data.code == 0) {
						    if(data.result){
						    	jQuery("#name").parent().find(".prompt_1").removeClass("ok").addClass("error").html("昵称存在");
						    }else{
						    	jQuery("#name").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
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

    jQuery(".verify").bind("blur",function(){
		var code = jQuery(this).val();
		if(code == ""){
			jQuery(".verify").parent().find(".prompt_1").removeClass("ok").addClass("error").html("验证码不能为空");
		}else{
			var phone=jQuery("#mobile").val();
			jQuery.ajax({
	    		url : "checkSmsCode.do",
	    		type : "POST",
	    		data : {"code":code,"phone":phone},
	    		dataType : "json",
	    		success : function(data) {
	    			if (data.code == 0) {
	    				jQuery(".verify").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
	    			} else {
	    				jQuery(".verify").parent().find(".prompt_1").removeClass("ok").addClass("error").html("验证码错误");
	    			}
	    		},
	    		error : function() {
	    			jQuery(".verify").parent().find(".prompt_1").removeClass("ok").addClass("error").html("验证码错误");
	    		}
	    	});
		}
	}); 
    jQuery(".validdateCode").bind("blur",function(){
    	var validateCode = jQuery(this).val();
    	if(validateCode == ""){
    		jQuery(".validdateCode").parent().find(".prompt_1").removeClass("ok").addClass("error").html("校验码不能为空");
    	}else{
    		var phone=jQuery("#validdateCode").val();
    		jQuery.ajax({
    			url : "checkValidDateCode.do",
    			type : "POST",
    			data : {"validateCode":validateCode},
    			dataType : "json",
    			success : function(data) {
    				if (data.code == 0) {
    					jQuery(".validdateCode").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
    					jQuery(".getvf").removeAttr("disabled").removeClass("off");
    				} else {
    					jQuery(".validdateCode").parent().find(".prompt_1").removeClass("ok").addClass("error").html("校验码错误");
    				}
    			},
    			error : function() {
    				jQuery(".validdateCode").parent().find(".prompt_1").removeClass("ok").addClass("error").html("校验码错误");
    			}
    		});
    	}
    }); 
	
	jQuery(".password").bind("blur",function(){
		var password = jQuery(this).val();
		var reg = /^[a-zA-Z0-9]{6,16}$/;
		var reg1 = /^\d{6,}$/;
		var pattern = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,16}$/;
		
		if(password == null|| password == ""){
			jQuery(".password").parent().find(".prompt_1").removeClass("ok").addClass("error").html("密码不能为空");
		}else if(!reg.test(password)&&!pattern.test(password)){
			jQuery(".password").parent().find(".prompt_1").removeClass("ok").addClass("error").html("密码格式错误！");
		}else if(reg1.test(password)){
			jQuery(".password").parent().find(".prompt_1").removeClass("error").addClass("ok").html("安全级别：低");
		}else if(reg.test(password)){
			jQuery(".password").parent().find(".prompt_1").removeClass("error").addClass("ok").html("安全级别：中");
		}else if(pattern.test(password)){
			jQuery(".password").parent().find(".prompt_1").removeClass("error").addClass("ok").html("安全级别：高");
		}
	});
	
	jQuery(".repassword").bind("blur",function(){
		var password = jQuery(".password").val();
		var repassword = jQuery(this).val();
		var reg = /^[a-zA-Z0-9]{6,16}$/;
		var reg1 = /^\d{6,}$/;
		var pattern = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,16}$/;
		if(repassword == "" || repassword == null){
			jQuery(".repassword").parent().find(".prompt_1").removeClass("ok").addClass("error").html("确认密码不能为空");	
		}else if(!reg.test(repassword)&&!pattern.test(password)){
			jQuery(".repassword").parent().find(".prompt_1").removeClass("ok").addClass("error").html("确认密码格式错误！");
		}else if(password != repassword ){
			jQuery(".repassword").parent().find(".prompt_1").removeClass("ok").addClass("error").html("两次密码不一致");
		}else{
			jQuery(".repassword").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");	
		}
	}); 
	jQuery(".picURL").bind("blur",function(){
		var picURL = jQuery(".picURL").val();
		if(picURL == ""){
			jQuery(".picURL").parent().find(".prompt_1").removeClass("ok").addClass("error").html("营业执照不能为空");	
		}else{
			jQuery(".picURL").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");	
		}
	}); 
	
    jQuery(".psub").click(function(){
        jQuery(".mobile").blur();
        jQuery(".validdateCode").blur();
        jQuery(".verify").blur();
    	jQuery(".password").blur();
    	jQuery(".repassword").blur(); 
    	jQuery(".name").blur();
    	jQuery(".companyName").blur();
    	jQuery(".picURL").blur();
    	var errorObj = jQuery(".error");
    	if(errorObj.length>0){
			 return;    		
    	}else{
    		jQuery(".psub").css("font-size","14px").html("提交中,请稍后...");
    		jQuery("#submitform").submit();
    	}
    });
    
    /** 发送验证码* */
    function sendSms(){
    	jQuery(".mobile").blur();
    	jQuery(".getvf").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
    	var errorObj = jQuery(".error");
    	if(errorObj.length>0){
    		return;
    	}
    	var i=60;
		var countdown = setInterval(cdTime, 1000);
		function cdTime() {
			jQuery(".getvf").attr("disabled",true).addClass("off").val( i + "秒后重新获取");
			if (i == 0) {
				jQuery(".getvf").removeAttr("disabled").removeClass("off").val("重新获取验证码");
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
    				jQuery(".getvf").parent().find(".prompt_1").removeClass("error").addClass("ok").html("");
    			}else if(data.code==2){
    				jQuery(".getvf").parent().find(".prompt_1").removeClass("ok").addClass("error").html("无法获取,单IP的请求次数不能超过五次");
    			}else if(data.code==3){
    				jQuery(".getvf").parent().find(".prompt_1").removeClass("ok").addClass("error").html("无法获取,60秒后才能获取");
    			} else {
    				jQuery(".getvf").parent().find(".prompt_1").removeClass("ok").addClass("error").html("发送失败");
    			}
    		},
    		error : function() {
    			jQuery(".getvf").parent().find(".prompt_1").removeClass("ok").addClass("error").html("发送失败");
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