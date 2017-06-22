// JavaScript Document
/* 世纳科技整合项目  @author xuefei @email xuefei131030@163.com
*/
var kt = {
   /*
	*sendAjax  ajax异步请求
	*@parame [提交类型,提交类型,提交数据,是否异步,处理服务端返回数据的回调函数]
	*@author xuefei 
	*
	*/
	sendAjax:function(type,url,sendData,isAsync,callback){
		$.ajax({
			type:(type != null && type != undefined)?type:"post",/*get、post*/
			url:url,
			data:sendData,
			async:isAsync,/*true异步，false同步*/
			/*dataType:"json",预期服务器返回的数据类型。如果不指定，JQuery将自动根据http包mime
          信息返回responseXML或responseText，并作为回调函数参数传递 {json,jsonp,text,xml,html,script}*/
			timeout:function(){
				
			},
			success:function(data){
				callback(data);
			},
			error:function(error){
				
			},
		});
	},
/*列表右边操作按钮下拉*/
	listFunBtn:function(){
		var obj = $('.listFunBtn .arrowBtn'),itemObj = $('.listFunBtnDown li'),itemLen = itemObj.length;
		if(itemLen > 0)
		{
			obj.mouseenter(function(){
				$(this).closest('.listFunBtn').addClass('listFunBtn_h').find('.listFunBtnScroll').slideDown(100);
			});	
			obj.mouseleave(function(){
				$(this).closest('.listFunBtn').removeClass('listFunBtn_h').find('.listFunBtnScroll').hide();
			});
			itemObj.click(function(){
				$(this).addClass('c').siblings().removeClass('c').closest('.listFunBtn').find('.linkDemo').html($(this).html());
				$(this).addClass('c').siblings().removeClass('c').closest('.listFunBtn').find('input[type="hidden"]').val($(this).find("a").attr("rel"));
				$(this).closest('.down').hide();
			});
		}
	},			
	//展开artDialog弹框
    /**
	 * @title:        弹框标题
	 * @con:          弹框内容容器obj或url:http://xxx 或文本内容
	 * @id:           弹框唯一标识
	 * @fun:          回调函数
	 * @isafter:      回调函数执行的地方,默认在代码末尾执行{1:末尾执行;0:开头执行}
	 * 
	**/
	dialog:"",
	getDialog:function(title,con,id,fun,isafter)
	{
		if(id == undefined || id == null){id = 'globalDialog';}
		var url = con.toLowerCase().substr(0,6);
		var conBox = con.toLowerCase().substr(0,1);
		
		if(typeof(isafter) == 'undefined' || isafter == '' || isafter == null){isafter = 1;}
		if(typeof(fun) == 'undefined' || fun == '' || fun == null){fun = '';}
		
		dialog = art.dialog(
		{
			id:id,
			padding:"0 10px",
			title:title,
			lock: true
		});
		dialog.hidden();
		
		if(url == 'http:/' || url == 'https:' || con.toLowerCase().indexOf(".htm") > 0 || con.toLowerCase().indexOf(".aspx") > 0 )
		{
			$('#dialogContBox').load(con/*+'?_='+Math.random()*/,function(data)
			{
				if(isafter!==1)
				{
					if(typeof(fun) === 'function')
					{
						fun(data);	
					}
				}
				dialog._reset();
				dialog.visible();
				if(isafter===1)
				{
					if(typeof(fun) === 'function')
					{
						fun(data);	
					}
				}
			});
		}
		else
		{
			var _con = con;
			if(conBox == '#' && $(con).length > 0)
			{
				_con = $(con).html();
			}
			if(isafter!==1)
			{
				if(typeof(fun) === 'function')
				{
					fun(_con);	
				}
			}
			dialog.content(_con);
			dialog._reset();
			dialog.visible();
			if(isafter === 1)
			{
				if(typeof(fun) === 'function')
				{
					fun(_con);
				}
			}
		}
	}
		
}
//Dialog函数--------------------------------------------------------S
	kt.tips = {
		second:5,
		intervalObj:null,
		
		//设置tips弹框默认时间/设置tips提示内容为一条或多条提示信息
		tipsDefaultSet:function(conmsg,t){
			kt.tips.resetDialogSize();
			if(t == undefined || t == null){t = 3;}
			if($.isArray(conmsg))
			{
				var strmsg='<ol>';
				t=parseInt(3+conmsg.length);
				$.each(conmsg, function(i, n)
				{
					strmsg+='<li>'+conmsg[i]+'</li>';
				});
				conmsg=strmsg+'</ol>';
			}
		},
		
		//站内错误警告/正确提示弹框
		/*
			style  : 提示框样式,正确(correct),错误(error)
			commsg : 提示内容
			t      : 多长时间消失
			调用示例：kt.tips.error('error','数据库出错！',3);
		*/
		error:function(style,conmsg,t){
			kt.tips.resetDialogSize();
			this.tipsDefaultSet(t,conmsg);
			if(t == undefined || t == null){t = 2;}
			this.second = t;
			//this.intervalObj = window.setInterval(this.setRemainTime, 1000);
			var error = art.dialog({
				id:'error',
				padding:0,
				title:'提示信息在'+this.second+'秒后关闭',
				content: '<div class="tips tips_'+style+'"><em></em>'+conmsg+'</div>'
			});
			$('.d-dialog').has('.tips_error').find('#dialogTitle,#dialogClose').hide();
			$('.d-dialog').has('.tips_correct').find('#dialogTitle,#dialogClose').hide();
			error.time(this.second*1000);
		},
		
		//loading提示弹框
		/*
			commsg : 提示内容
			t      : 多长时间消失
			调用示例：kt.tips.loading('加载中...',3);
		*/
		loading:function(conmsg,t){
			kt.tips.resetDialogSize();
			if(t == undefined || t == null){t = 3;}
			var tipsLoading = art.dialog({
				id:'tipsLoading',
				padding:0,
				content: '<div class="tips tips_loading"><em></em>'+conmsg+'</div>'
			});
			
			loading.time(t*1000);
			$("#dialogTitle").hide();
			
		},
		
		//普通警告信息弹框
		/*
			commsg : 提示内容
			t      : 多长时间消失
			调用示例：kt.tips.notice('三秒后跳转到着页',3);
		*/
		notice:function(conmsg,t){
			kt.tips.resetDialogSize();
			if(typeof(t) === 'undefined'){t = 3;}
			this.tipsDefaultSet(t,conmsg);
			this.second = t;
			this.intervalObj = window.setInterval(this.setRemainTime, 1000);
			var notice = art.dialog({
				id:'tipsNotice',
				padding:0,
				title:'提示信息在'+this.second+'秒后关闭',
				content: '<div class="tips tips_notice"><em></em>'+conmsg+'</div>'
			});
			notice.time(t*1000);
		},
		
		//临时提醒
		/*
			commsg  : 提示内容
			t       : 多长时间消失
			errStyle: 正确或错误样式，默认为tips_temp 正确( correct ),错误( error ) default(默认为白底)
			fun     : 执行完后调用的function
			islock  : 是否锁屏，1为锁屏，0为无锁屏，默认为0;
			调用示例：kt.tips.temp('三秒后跳转到着页','correct',3);
	
		*/
		temp:function(conmsg,errStyle,t,fun,islock)
		{
			kt.tips.resetDialogSize();
			if(typeof(t) === 'undefined'){t = 3;}
			if(typeof(errStyle) === 'undefined'){errStyle = '';}
			if(typeof(islock) !== 'boolean'){islock = false;}
			var temp = art.dialog(
			{
				id:'tipsTemp',
				padding:0,
				title:false,
				lock: islock,
				content: '<div class="tips_temp tips_'+errStyle+'"><em></em>'+conmsg+'</div>'
			});
			$('.d-dialog').has('.tips_temp').find('#dialogClose').hide();
			temp.time(t*1000,fun);
		},
		
		//周期函数
		setRemainTime:function(){
			var dialogTitle = $('#dialogTitle');
			dialogTitle.addClass('dialogTimeout');
			if (kt.tips.second > 0){
				kt.tips.second = kt.tips.second - 1;
				var dialog = art.dialog.get('error');
				var tipsNotice = art.dialog.get('tipsNotice');
				if(dialog != undefined || dialog != null){
					dialog.title('提示信息在' + kt.tips.second + '秒后关闭');
				}
				if(tipsNotice != undefined || tipsNotice != null){
					tipsNotice.title('提示信息在' + kt.tips.second + '秒后关闭');
				}
				//$(".dialogTimeout").html('提示信息在' + kt.tips.second + '秒后关闭');
			}
			else
			{
				window.clearInterval(kt.tips.intervalObj);
			}	
		},
		resetDialogSize:function(){
			$('#dialogStyle').empty();
		}
		
	};

//Dialog函数--------------------------------------------------------E
//jquery扩展
$.fn.extend({
	//关闭弹出层窗口
	closeLayer:function(){
		
		$(this).bind({
			click:function(){
				dialog.close();
			}
		});
	},		
	clearInput:function(){
		$(this).bind({
			click:function(){
				$(".searchBar").find("input:text").val("");
				$(".searchBar").find("input:text").prev(".itemLabel").show();
			}
		})
		
	},
	
	//文本框聚焦清空文本---S-->
	inputFocus:function(txt){

		var len = $(this).prev('label').length,str = '';
	
		//页面加载时如果文本框内有值则清空label文本
		if(len > 0){
			if($.trim($(this).val()) != '')
			{	
				$(this).prev('label').empty();	
			}
			else
			{
				$(this).prev('label').css({'color':'#999999'});
			}
			str = $(this).prev('label').text();
		}
		else
		{
			str = $(this).val();
		}
		if(typeof(txt) == 'undefined' || txt == '' || txt == null){txt = str;}
		$(this).bind({
			focus:function(){
				if(len > 0)
				{
					if($(this).val() == ''){
						
						$(this).prev('label').css('color','#dfdfdf');	
					}
					else
					{
						$(this).prev('label').text('');		
					}
				}
				else
				{
					if($.trim($(this).val()) == txt){$(this).val('')}
				}
			},
			keydown:function(){
				if(len > 0)
				{
					$(this).prev("label").empty();
				}
				else
				{
					$(this).css({'color':'#333333'});
				}
			},
			blur:function(){
				if(len > 0)
				{
					if($(this).val() == ''){
						$(this).prev('label').text(txt).css('color','#a9a9a9');
					}
					else
					{
						$(this).prev('label').text('');	
					}
				}
				else
				{
					if($.trim($(this).val()) == ''){$(this).css({'color':'#999999'}).val(txt)}		
				}
			}
		});	
		$(this).prev('label').click(function(){
			$(this).css('color','#dfdfdf');	
			$(this).next('input,textarea').focus();
		});
		$(this).bind('paste',function(){$(this).prev('label').empty();});	
	}	
	//文本框聚焦清空文本---E-->
});
//Alert函数---------------------------------------------------------S
    /**
	 * @msg:        	弹框信息
	 * @callback:     回调函数(要给回调函数传参数) 点击确定按钮传入true  点击关闭或者取消传入false；
	 * 
	**/
kt.confirm = function(msg,callback){

	var d = '<div id="alertBar" class="alertBar">'+
			'<div class="alertHead clearfix"><span class="alertTit">系统提示</span><a id="alertClose" class="alertClose">×</a></div>'+
			'<div class="alertCon">'+msg+'</div>'+
			'<div class="alertBtnBox">'+
				'<input type="button" name="sure" id="alertSure" class="alertSure" value="确定" />'+
				'<input type="button" name="cencle" id="alertCencle" class="alertCencle" value="取消" />'+
			'</div>'+
		'</div>';
	$("#setContent").html(d);
	var tab = $("#alertBar");
	tab.offset({ top:(document.documentElement.clientHeight - tab[0].offsetHeight) / 2 - 80 , left: (document.documentElement.clientWidth - tab[0].offsetWidth) / 2});
	
	$(document).on("click","#alertClose, #alertCencle",function(){
		$("#setContent").empty();
		callback(false);
	});
	
	$(document).on("click","#alertSure",function(){
		$("#setContent").empty();
		callback(true);
	});
		
};
kt.alert = function(msg){

	var d = '<div id="alertBar" class="alertBar">'+
			'<div class="alertHead clearfix"><span class="alertTit">系统提示</span><a id="alertClose" class="alertClose">×</a></div>'+
			'<div class="alertCon">'+msg+'</div>'+
			'<div class="alertBtnBox">'+
				'<input type="button" name="sure" id="alertSure" class="alertSure" value="确定" />'+
			'</div>'+
		'</div>';
	$("#setContent").html(d);
	var tab = $("#alertBar");
	tab.offset({ top:(document.documentElement.clientHeight - tab[0].offsetHeight) / 2 - 80 , left: (document.documentElement.clientWidth - tab[0].offsetWidth) / 2});
	
	$(document).on("click","#alertClose, #alertSure",function(){
		$("#setContent").empty();
	});
		
};
//Alert函数---------------------------------------------------------E
//上传图片ajax------------------------------------------------------S
    /**
	 * @inpObj:         上传框
	 * @imgBoxId:    上传后显示图片位置id
	 * @url 后台响应路径
	**/
kt.uploadAjax = function(inpObj,imgBoxId,url,callback){
 //创建FormData对象
	var data = new FormData();
	//为FormData对象添加数据
	//
	$.each($(inpObj)[0].files, function(i, file) {
		data.append('upload_file', file);
	});
	$.ajax({
		url:url,
		type:'POST',
		data:data,
		cache: false,
		contentType: false,    //不可缺
		processData: false,    //不可缺
		success:function(data){
			if(callback)
            {
                callback(data);
            }
		}
	});
};

//上传图片ajax------------------------------------------------------E

//点击树形应用里的链接---------------------------------------------------S
    /**
	 * @obj:        点击的当前link
	 * @callback	回调函数
	**/
kt.setEnterLi = function (obj, callback){

	$(obj).next("ul").toggle();
	$(".uifLink ").removeClass("hover");
	if(!$(obj).children("em").attr("class")||$(obj).children("em").attr("class") == undefined ||$(obj).children("em").attr("class") == null ){
		$(obj).closest(".ulBar").find("a").removeClass("hover");
		$(obj).addClass("hover");
	}else{
		if($(obj).find("em").hasClass("arrow arrow_up")){
			$(obj).find("em").attr("class","arrow arrow_r");
		}else{
			
			$(obj).find("em").attr("class","arrow arrow_up");
		}
	}
	if(callback){
		callback();	
	}
}

	//模拟单选框
kt.radioUi =  function(){
	//radio
	$(".radioBar").click(function(){
		var Rname = $(this).find(".radioSpan").attr("radioName");
		var i = 0;
		$(".radioBar .radioSpan").each(function(i){
			
			if($(".radioBar .radioSpan").eq(i).attr("radioName") == Rname){
				$(".radioBar .radioSpan[radioName='"+Rname+"']").removeClass("hover");
			}	
						
		})
		
		$(this).find(".radioSpan").addClass("hover");
	});		
}

	//模拟复选框
	//checkbox
kt.checkboxUi =  function(){
	$(".checkboxBar").click(function(){
		console.log(0);
		//$(".checkboxBar .checkboxSpan").removeClass("hover");
		if($(this).find(".checkboxSpan").hasClass("hover")){
			$(this).find(".checkboxSpan").removeClass("hover");
		}else{
			$(this).find(".checkboxSpan").addClass("hover");
		}
	});
	return false;
}
//模拟下拉select
kt.selectUi = function(){
	
	$(".selectUl").on("click","a",function(){
		var c = $(this).html(),v = $(this).attr("rel");
		$(".itemLabel").html("");
		$(this).closest(".iptItemBox").find(".itemText").focus().val(c);
		$(".selectUl").hide();
	}); 
	$(".selectUlLink").click(function(){
		$(".selectUl").show();
	})
	$(".selectUl").bind("mouseleave",function(){
		$(this).hide();
	})	
};
//头部menu
kt.setHead = function(){
	
	$(".navLi").bind("mouseenter",function(){
		$(this).closest(".navLi").find(".navUiSe").show();	
	});
	$(".navLi").bind("mouseleave",function(){
		$(this).closest(".navLi").find(".navUiSe").hide();	
	});
	
	//店面名称
	$(".setInfoBar").bind("mouseenter",function(){
		$(this).find(".setInfoList").show();
	});
	$(".setInfoBar").bind("mouseleave",function(){
		$(this).find(".setInfoList").hide();
	});
	//显示头部消息
	$("#sysInfo").bind("click",function(){
		$("#headerInfo").show();
	});
	$("#headerInfo").bind("mouseleave",function(){
		$("#headerInfo").hide();
	});	
	
};

$(function(){
	kt.setHead();//头部菜单操作
	kt.radioUi();//模拟单选框
	kt.checkboxUi();//模拟多选框
	kt.listFunBtn();//模拟下拉框	
	$(".itemText").inputFocus();//输入框获取焦点消失label
    $(".layerCancel").closeLayer();//点击取消，关闭弹出层
	$(document).on("click",".layerCancel",function(){
		dialog.close();
	})
})