// JavaScript Document
/* 世纳科技整合项目  @author xuefei @email xuefei131030@163.com*/
var sn = {
   /*
	*sendAjax  ajax异步请求
	*@parame [提交类型,提交类型,提交数据,是否异步,处理服务端返回数据的回调函数]
	*@author xuefei 
	*
	*/
	sendAjax:function(type,url,sendData,isAsync,callback,obj){
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
				if(obj){
					callback(data, obj);
				} else{
					callback(data);
				}
				
			},
			error:function(error){
				
			}
		});
	},
	
//公用函数-----------------------------------------------------------S
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
	sn.tips = {
		second:5,
		intervalObj:null,
		
		//设置tips弹框默认时间/设置tips提示内容为一条或多条提示信息
		tipsDefaultSet:function(conmsg,t){
			sn.tips.resetDialogSize();
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
			调用示例：sn.tips.error('error','数据库出错！',3);
		*/
		error:function(style,conmsg,t){
			sn.tips.resetDialogSize();
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
			调用示例：sn.tips.loading('加载中...',3);
		*/
		loading:function(conmsg,t){
			sn.tips.resetDialogSize();
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
			调用示例：sn.tips.notice('三秒后跳转到着页',3);
		*/
		notice:function(conmsg,t){
			sn.tips.resetDialogSize();
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
			调用示例：sn.tips.temp('三秒后跳转到着页','correct',3);
	
		*/
		temp:function(conmsg,errStyle,t,fun,islock)
		{
			sn.tips.resetDialogSize();
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
			if (sn.tips.second > 0){
				sn.tips.second = sn.tips.second - 1;
				var dialog = art.dialog.get('error');
				var tipsNotice = art.dialog.get('tipsNotice');
				if(dialog != undefined || dialog != null){
					dialog.title('提示信息在' + sn.tips.second + '秒后关闭');
				}
				if(tipsNotice != undefined || tipsNotice != null){
					tipsNotice.title('提示信息在' + sn.tips.second + '秒后关闭');
				}
				//$(".dialogTimeout").html('提示信息在' + sn.tips.second + '秒后关闭');
			}
			else
			{
				window.clearInterval(sn.tips.intervalObj);
			}	
		},
		resetDialogSize:function(){
			$('#dialogStyle').empty();
		}
		
	};

//Dialog函数--------------------------------------------------------E
//Alert函数---------------------------------------------------------S
    /**
	 * @msg:        	弹框信息
	 * @callback:     回调函数(要给回调函数传参数) 点击确定按钮传入true  点击关闭或者取消传入false；
	 * @arguments 传入要处理的对象
	**/
sn.confirm = function(msg,callback){
	var args = arguments[2];
	var d = '<div id="alertBar" class="alertBar">'+
			'<div class="alertHead clearfix"><span class="alertTit">系统提示</span><a id="alertClose" class="alertClose">×</a></div>'+
			'<div class="alertCon">'+msg+'</div>'+
			'<div class="alertBtnBox">'+
				'<input type="button" name="sure" id="alertSure" class="alertSure" value="确定" />'+
				'<input type="button" name="cencle" id="alertCencle" class="alertCencle" value="取消" />'+
			'</div>'+
		'</div>'+
		'<div style="z-index: 1990; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; display: block;" class="d-mask"></div>';
	$("body").append(d);
	var tab = $("#alertBar");
	tab.offset({ top:(document.documentElement.clientHeight - tab[0].offsetHeight) / 2 - 80 , left: (document.documentElement.clientWidth - tab[0].offsetWidth) / 2});
	
	$(document).on("click","#alertClose, #alertCencle",function(){
		$("#alertBar,.d-mask").remove();
		callback(false);
	});
	
	$(document).on("click","#alertSure",function(){
		$("#alertBar,.d-mask").remove();
		
		if(args){
			callback(true,args);
		}else{
			callback(true);
		}
		
	});
		
};
sn.alert = function(msg){

	var d = '<div id="alertBar" class="alertBar">'+
			'<div class="alertHead clearfix"><span class="alertTit">系统提示</span><a id="alertClose" class="alertClose">×</a></div>'+
			'<div class="alertCon">'+msg+'</div>'+
			'<div class="alertBtnBox">'+
				'<input type="button" name="sure" id="alertSure" class="alertSure" value="确定" />'+
			'</div>'+
		'</div>'+
		'<div style="z-index: 1990; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; display: block;" class="d-mask"></div>';
		
		
	$("body").append(d);
	var tab = $("#alertBar");
	tab.offset({ top:(document.documentElement.clientHeight - tab[0].offsetHeight) / 2 - 80 , left: (document.documentElement.clientWidth - tab[0].offsetWidth) / 2});
	
	$(document).on("click","#alertClose, #alertSure",function(){
		$("#alertBar,.d-mask").remove();

	});
		
};
//Alert函数---------------------------------------------------------E
//上传图片ajax------------------------------------------------------S
    /**
	 * @inpObj:         上传框
	 * @imgBoxId:    上传后显示图片位置id
	 * @url 后台响应路径
	**/
sn.uploadAjax = function(inpObj,imgBoxId,url,callback){
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
sn.setEnterLi = function (obj, callback){

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
/*树形点击左边箭头*/

sn.treeToggle = function (obj, callback){
    /**
	 * @obj:        点击的当前link
	 * @callback	回调函数
	 **/
	$(obj).closest(".linkBar ").next("ul").toggle();
	$(".linkBar ").removeClass("hover");
	if(!$(obj).children("em").attr("class")||$(obj).children("em").attr("class") == undefined ||$(obj).children("em").attr("class") == null ){
		$(obj).closest(".ulBar").find(".linkBar").removeClass("hover");
		$(obj).closest(".linkBar ").addClass("hover");
	}else{
		if($(obj).find("em").hasClass("icon arrowDown")){
			$(obj).find("em").attr("class","icon arrowRight");
		}else{
			
			$(obj).find("em").attr("class","icon arrowDown");
		}
	}
	if(callback){
		callback();	
	}
}
//添加树形结构
/**
 * @data:        数据 格式
 var data = 
		var data = [{"NAME":"健康管理中心","ID":"a63ba0f3-bd91-451c-b334-52ef6ba42a33","LIST":[{"NAME":"世纳研发部","ID":"4e006135-a83f-4e1a-8c21-8c5e1bc72e1a","LIST":[{"NAME":"黄明兵科室","ID":"8b8136f3-7881-4025-96a7-1d095838b5a3","LIST":[{"NAME":"中医体质","ID":"ID"},{"NAME":"中医体质","ID":"ID"}]},{"NAME":"徐亮科室","ID":"839bd0d1-4700-467f-a3dc-c5fa4481fa3c"}]},{"NAME":"健康管理销售部门","ID":"5379b800-9463-4974-a3ac-06b0910aebeb","LIST":""}]}]
;
 * 
**/
sn.setTreeList = function(data){

	var treeHtml="",i=0,j=0,n=0,k = 0;                                            
	var treeData= data,url="",dataId="";	

	treeHtml+='<ul class="enterUlf">';
	if(treeData.length > 0){
	
		for(i=0;i < treeData.length; i++){
			treeHtml+='<li class="uifLi"><a  rel="'+treeData[i].ID+'" class="uifLink" href="javascript:;"><em class="arrow arrow_r"></em>'+treeData[i].NAME+'</a><ul class="enterUls none">';
			if(treeData[i].LIST.length > 0){
				
				for(j=0;j < treeData[i].LIST.length; j++){
					treeHtml +='<li class="uisLi">';

						if(treeData[i].LIST[j].LIST.length > 0){
							treeHtml+='<a class="uisLink" rel="'+treeData[i].LIST[j].ID+'" href="javascript:;"><em class="arrow arrow_r"></em>'+treeData[i].LIST[j].NAME+'</a><ul class="enterUlt none">';
							for(n=0;n < treeData[i].LIST[j].LIST.length; n++){
								
								dataId = (treeData[i].LIST[j].LIST[n].ID !=  undefined && treeData[i].LIST[j].LIST[n].ID != "" )?treeData[i].LIST[j].LIST[n].ID:"";
								url = (treeData[i].LIST[j].LIST[n].URL !=  undefined && treeData[i].LIST[j].LIST[n].URL != "" )?treeData[i].LIST[j].LIST[n].URL:"javascript:;";
					
								if(treeData[i].LIST[j].LIST[n].LIST){
									
									treeHtml+='<li class="uitLi"><a rel="'+dataId+'" class="uitLink" href="'+url+'"><em class="arrow arrow_r"></em>'+treeData[i].LIST[j].LIST[n].NAME+'</a><ul class="enterUlfo none">';
									
									for(k=0;k < treeData[i].LIST[j].LIST[n].LIST.length; k++){
										dataId = dataId = (treeData[i].LIST[j].LIST[n].LIST[k].ID !=  undefined && treeData[i].LIST[j].LIST[n].LIST[k].ID != "" )?treeData[i].LIST[j].LIST[n].LIST[k].ID:""; 
										url = (treeData[i].LIST[j].LIST[n].LIST[k].URL !=  undefined && treeData[i].LIST[j].LIST[n].LIST[k].URL != "" )?treeData[i].LIST[j].LIST[n].LIST[k].URL:"javascript:;"; 										
										treeHtml+='<li class="uifoLi"><a rel="'+dataId+'" class="uitLink" href="'+url+'">'+treeData[i].LIST[j].LIST[n].LIST[k].NAME+'</a></li>';
									}
									
									treeHtml+='</ul>';
									treeHtml+='</li>';
								}else{
									treeHtml+='<li class="uitLi"><a rel="'+dataId+'" class="uitLink" href="'+url+'">'+treeData[i].LIST[j].LIST[n].NAME+'</a></li>';
								}

								
							}
							
							treeHtml+='</ul>';
						}else{
							dataId = (treeData[i].LIST[j].ID != "" && treeData[i].LIST[j].ID != undefined)?treeData[i].LIST[j].ID:"";
							url = (treeData[i].LIST[j].URL != "" && treeData[i].LIST[j].URL != undefined)?treeData[i].LIST[j].URL:"javascript:;";
							treeHtml+='<a class="uisLink" rel="'+dataId+'" href="'+url+'">'+treeData[i].LIST[j].NAME+'</a>'
						}
						treeHtml +='</li>';
				}				
			}	
			
			treeHtml+='</li></ul>'
		}
		
	}//if treeDate.length
	
	$("#ulBar").html(treeHtml);
};

//科室树形结构
/**
 * @data:        数据 格式
 var data = 
		var data = [{"NAME":"健康管理中心","ID":"a63ba0f3-bd91-451c-b334-52ef6ba42a33","LIST":[{"NAME":"世纳研发部","ID":"4e006135-a83f-4e1a-8c21-8c5e1bc72e1a","LIST":[{"NAME":"黄明兵科室","ID":"8b8136f3-7881-4025-96a7-1d095838b5a3","LIST":[{"NAME":"中医体质","ID":"ID"},{"NAME":"中医体质","ID":"ID"}]},{"NAME":"徐亮科室","ID":"839bd0d1-4700-467f-a3dc-c5fa4481fa3c"}]},{"NAME":"健康管理销售部门","ID":"5379b800-9463-4974-a3ac-06b0910aebeb","LIST":""}]}]
;
 * 
**/
sn.setTree = function(data){

	var treeHtml="",i=0,j=0,n=0,k = 0;                                            
	var treeData= data,url="",dataId="";	

	treeHtml+='<ul class="enterUlf">';
	if(treeData.length > 0){
	
		for(i=0;i < treeData.length; i++){
			treeHtml+='<li class="uifLi"><span class="linkBar"><a title="伸缩" href="javascript:;" class="tog"><em class="icon arrowRight"></em></a><a  rel="'+treeData[i].ID+'" class="uifLink" href="javascript:;">'+treeData[i].NAME+'</a></span><ul class="enterUls none">';
			if(treeData[i].LIST.length > 0){
				
				for(j=0;j < treeData[i].LIST.length; j++){
					treeHtml +='<li class="uisLi">';

						if(treeData[i].LIST[j].LIST.length > 0){
							treeHtml+='<span class="linkBar"><a title="伸缩" href="javascript:;" class="tog"><em class="icon arrowRight"></em></a><a class="uisLink" rel="'+treeData[i].LIST[j].ID+'" href="javascript:;">'+treeData[i].LIST[j].NAME+'</a></span><ul class="enterUlt none">';
							for(n=0;n < treeData[i].LIST[j].LIST.length; n++){
								
								dataId = (treeData[i].LIST[j].LIST[n].ID !=  undefined && treeData[i].LIST[j].LIST[n].ID != "" )?treeData[i].LIST[j].LIST[n].ID:"";
								url = (treeData[i].LIST[j].LIST[n].URL !=  undefined && treeData[i].LIST[j].LIST[n].URL != "" )?treeData[i].LIST[j].LIST[n].URL:"javascript:;";
					
								if(treeData[i].LIST[j].LIST[n].LIST){
									
									treeHtml+='<li class="uitLi"><span class="linkBar"><a title="伸缩" href="javascript:;" class="tog"><em class="icon arrowRight"></em></a><a rel="'+dataId+'" class="uitLink" href="'+url+'">'+treeData[i].LIST[j].LIST[n].NAME+'</a></span><ul class="enterUlfo none">';
									
									for(k=0;k < treeData[i].LIST[j].LIST[n].LIST.length; k++){
										dataId = dataId = (treeData[i].LIST[j].LIST[n].LIST[k].ID !=  undefined && treeData[i].LIST[j].LIST[n].LIST[k].ID != "" )?treeData[i].LIST[j].LIST[n].LIST[k].ID:""; 
										url = (treeData[i].LIST[j].LIST[n].LIST[k].URL !=  undefined && treeData[i].LIST[j].LIST[n].LIST[k].URL != "" )?treeData[i].LIST[j].LIST[n].LIST[k].URL:"javascript:;"; 										
										treeHtml+='<li class="uifoLi"><span class="linkBar"><a rel="'+dataId+'" class="uitLink" href="'+url+'">'+treeData[i].LIST[j].LIST[n].LIST[k].NAME+'</a></span></li>';
									}
									
									treeHtml+='</ul>';
									treeHtml+='</li>';
								}else{
									treeHtml+='<li class="uitLi"><span class="linkBar"><a rel="'+dataId+'" class="uitLink" href="'+url+'">'+treeData[i].LIST[j].LIST[n].NAME+'</a></span></li>';
								}

								
							}
							
							treeHtml+='</ul>';
						}else{
							dataId = (treeData[i].LIST[j].ID != "" && treeData[i].LIST[j].ID != undefined)?treeData[i].LIST[j].ID:"";
							url = (treeData[i].LIST[j].URL != "" && treeData[i].LIST[j].URL != undefined)?treeData[i].LIST[j].URL:"javascript:;";
							treeHtml+='<span class="linkBar"><a class="uisLink" rel="'+dataId+'" href="'+url+'">'+treeData[i].LIST[j].NAME+'</a></span>'
						}
						treeHtml +='</li>';
				}				
			}	
			
			treeHtml+='</li></ul>'
		}
		
	}//if treeDate.length
	
	$("#ulBar").html(treeHtml);
};

//弹出窗口-->
/**
 * @url 弹出页面
 * @width 窗口宽度 	
 * @height 窗口高度
 * @showscroll 是否有滚动条true false无
**/
sn.openWindow = function (url, width, height, showscroll) {
    var s = "yes";
    if (showscroll == false) s = "no";
    var l = Math.ceil((window.screen.width - width) / 2);
    var t = Math.ceil((window.screen.height - height) / 2) - 30;
    return window.open(url, "_blank", "left=" + l + ",top=" + t + ",height=" + height + ",width=" + width + ",toolbar=no,status=no,resizable=yes,location=no,scrollbars=" + s);
};

//jquery扩展
var userAgent = navigator.userAgent.toLowerCase();
$.browser = {
    version: (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
    safari: /webkit/.test(userAgent),
    opera: /opera/.test(userAgent),
    msie: /msie/.test(userAgent) && !/opera/.test(userAgent),
    mozilla: /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent),
    mobile: /ipad|iphone|blackberry|android/.test(userAgent)
};

//左右两个select选择框  S-->
$.copyOption = function (l, r, all) {
    var k = l.selectedIndex;
    if (k == -1 && (all == null || all == false)) return;
    var rLen = r.length;
    for (var i = 0; i < l.length; i++) {
        itemL = l.options[i];
        var html = '';
        if (itemL.selected || all) {
            var x = $.findOption(r, itemL.value);
            if (x != null) continue;
            html += '<option value="' + itemL.value + '">' + itemL.innerHTML + '</option>'
        }
        $(r).append(html);
    }
};
$.delOption = function (slt, all) {
    for (var i = slt.length - 1; i >= 0; i--) {
        var item = slt.options[i];
        if (all || item.selected) {
			slt.removeChild(item);
		}
    }
}
$.findOption = function (slt, val) {
    for (var i = slt.length - 1; i >= 0; i--) {
        if (slt.options[i].value == val){
			return slt.options[i];
		} 
    }
    return null;
};

$.moveOption = function (mode, l, r, all) {
    var k = l.selectedIndex;
    if (k == -1 && (all == null || all == false)) return;
    var a;
    switch (mode) {
        case 0:/*左右/右左移动*/
            {
                var rLen = r.length;
                var html = '',itemL="";
                for (var i = l.length - 1; i >= 0; i--) {
                    itemL = l.options[i];
                    if (itemL.selected || all) {
                        var tmp = $.findOption(r, itemL.value);
                        if (!tmp) {
                            html = '<option value="' + itemL.value + '">' + itemL.innerHTML + '</option>' + html
                        }
                        l.removeChild(itemL)
                    }
                }
                $(r).append(html)
            }
            break;
        case 1:/*向上移*/
            if (k == 0) return;
            a = l.options[k];
            var o = document.createElement("option");
            o.innerHTML = a.innerHTML;
            o.value = a.value;
            var b = l.options[k - 1];
            l.remove(k);
            l.insertBefore(o, b);
            l.selectedIndex = k - 1;
            break;
        case 2:/*向下移*/
            a = l.options[k];
            if (k == l.length - 1) return;
            var c = l.options[k + 1];
            l.insertBefore(c, a);
            break
    }
};
$.fn.extend({
	//select选择项
	selectedOptions: function () {
        var right = this[0];
        var val = "";
        var text = "";
        for (var i = 0; i < right.length; i++) {
            var o = right.options[i];
            val += o.value + ",";
            text += o.innerHTML + ","
        }
        if (val == "") return {
            val: "",
            text: ""
        };
        val = val.substring(0, val.length - 1);
        text = text.substring(0, text.length - 1);
        return {
            "val": val,
            "text": text
        }
    }	
});

//左右两个select选择框  E-->

//jquery扩展
$.extend({
	newElement : function (tagName, attr) {
		var el = document.createElement(tagName);
		if (attr == null) return $(el);
		if (attr.className) el.className = attr.className;
		if (attr.id) el.id = attr.id;
		if (attr.html) el.innerHTML = attr.html;
		var s = el.style;
		if (attr.position) s.position = attr.position;
		if (attr.width) s.width = typeof (attr.width) == "string" ? attr.width : attr.width + "px";
		if (attr.height) s.height = typeof (attr.height) == "string" ? attr.height : attr.height + "px";
		if (attr.top) s.top = attr.top + "px";
		if (attr.left) s.left = attr.left + "px";
		return $(el)
	},
	verifyElement : function (item, alertType, checkempty) {
		if (item.style.display == "none" || item.disabled) return true;
		alertType = alertType == null ? 0 : alertType;
		checkempty = checkempty == null ? true : checkempty;
		var msg = item.getAttribute("msg");
		var must = item.getAttribute("must");
		var type = item.getAttribute("verify");
		var pattern;
		switch (type) {
			case "number":
				pattern = /^\d+$/;
				msg = msg == null ? "必须是数字" : msg;
				break;
			case "letter":
				pattern = /^[A-Za-z]+$/;
				msg = msg == null ? "必须是字母" : msg;
				break;
			case "numeric":
				msg = msg == null ? "必须是数值" : msg;
				pattern = /^\d+(.\d+)?$/;
				break;
			case "account":
				msg = msg == null ? "必须是数字、字母、下划线" : msg;
				pattern = /^\w+$/;
				break;
			case "accountvar":
				msg = msg == null ? "必须是数字、字母、下划线且不能数字开头" : msg;
				pattern = /^[_a-zA-Z][\w]*$/;
				break;
			case "email":
				msg = msg == null ? "邮箱格式不正确" : msg;
				pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
				break;
			case "mobile":
				msg = msg == null ? "手机格式不正确" : msg;
				pattern = /^0*(13|15|18)\d{9}$/;
				break;
			case "phone":
				msg = msg == null ? "电话格式不正确" : msg;
				pattern = /^[0-9]{3,4}\-[0-9]{7,8}$/;
				break;
			case "zip":
				msg = msg == null ? "右边格式不正确" : msg;
				pattern = /^\d{6}$/;
				break;
			case "chinese":
				msg = msg == null ? "必须是汉字" : msg;
				pattern = /^[\u4e00-\u9fa5]+$/;
				break;
			case "qq":
				msg = msg == null ? "QQ格式不对" : msg;
				pattern = /^[1-9]\d{5,8}$/;
				break;
			case "IDCard":
				msg = msg == null ? "身份证不对" : msg;
				//pattern = /^\d{18}$/;
				pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				break;
			default:
				pattern = eval(type);
				break;
		};
		var v = false;
		if (must == "1" && checkempty) {
			if (item.value == "") {
				v = false;
				if (msg == null)
					msg = "不能为空";
			} else if (pattern != null) {
				v = pattern.test(item.value)
			} else {
				v = true
			}
		} else {
			if (item.value.length > 0 && pattern != null && pattern != 0) v = pattern.test(item.value);
			else v = true
		}
		if (alertType == 1) {
			var arr = $(item.parentNode).find("span");
			var span;
			if (arr.length > 0) {
				span = $(arr[0])
			} else {
				span = $.newElement("span");
				$(item).after(span);
			}
			span.css(v ? "pass" : "alert");
			if (v) {
				span.html("√");
				
			} else {
				span.html(msg);
				item.value = "";
				item.focus()
			}
		} else if (!v) {
			alert(msg);
			item.value = "";
			item.focus()
		}
		$(item).after(span);
		return v;
	}
    
});
$.fn.extend({

	//后台绑定事件-->
	bindEvent: function (type, eventHandler) {
        if ($.browser.mobile == false && type == "tap") type = "click";
        if (window.addEventListener) {
            if (type == "propertychange") type = "input";
            for (var i = 0; i < this.length; i++) {
                this[i].addEventListener(type, eventHandler, false)
            }
        } else if (window.attachEvent) {
            if (type == "input") type = "propertychange";
            for (var i = 0; i < this.length; i++) {
                eval("var temp" + i + " = this[" + i + "]");
                eval("temp" + i + ".attachEvent('on' + type, function() { eventHandler.apply(temp" + i + ", arguments); })")
            }
        }
    },
	
	//展开收缩-->
	setToggle:function(){
		$(this).bind({
			click:function(){
				if($(this).find("em").hasClass("gtIcon")){
					$(this).find("em").removeClass("gtIcon");
					$(this).find("em").addClass("dwIcon");
				}else {
					$(this).find("em").removeClass("dwIcon");
					$(this).find("em").addClass("gtIcon");
				}
				$(this).closest(".toggleDiv").next(".checkTableDiv").toggle("normal");
			}	
		})
	},
	
	//点击按钮清空输入框
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
	},	
	//文本框聚焦清空文本---E-->
	//关闭弹出层窗口
	closeLayer:function(){
		$(this).bind({
			click:function(){
				dialog.close();
			}
		});
	},
	/*验证表单 S*/
    verifyDiv: function (obj,type) {
        type = type == null ? 0 : 1;
        var form = $(obj).closest("form")[0];
        var v = true;
        ipt = form.getElementsByTagName("INPUT");
        for (var k = 0; k < ipt.length; k++) {
            var item = ipt[k];
            if (item.type == "hidden") continue;
            var verity = item.getAttribute("verify");
            var must = item.getAttribute("must");
            if (verity == null && (must == null || must == "0")) continue;
            if (!$.verifyElement(item, type)) return false
        }
        ipt = form.getElementsByTagName("TEXTAREA");
        for (var k = 0; k < ipt.length; k++) {
            var item = ipt[k];
            var verity = item.getAttribute("verify");
            var must = item.getAttribute("must");
            if (verity == null && (must == null || must == "0")) continue;
            if (!$.verifyElement(item, type)) return false
        }
        ipt = form.getElementsByTagName("SELECT");
        for (var k = 0; k < ipt.length; k++) {
            var item = ipt[k];
            var verity = item.getAttribute("verify");
            var must = item.getAttribute("must");
            if (verity == null && (must == null || must == "0")) continue;
            if (!$.verifyElement(item, type)) return false
        }
        return v
    },
    verifyForm: function (type, blur) {
        type = type == null ? 0 : 1;
        blur = blur == null ? true : blur;

        var form = this[0];
        var ipt = form.getElementsByTagName("*");
        for (var i = 0; i < ipt.length; i++) {
            var item = ipt[i];
            if (item.tagName != "INPUT" && item.tagName != "TEXTAREA" && item.tagName != "SELECT") continue;
            var verity = item.getAttribute("verify");
            var must = item.getAttribute("must");
            if (verity == null && (must == null || must == "0")) continue;
            if (item.className.indexOf("ke-username") > -1 || item.className.indexOf("ke-orgname") > -1 || item.className.indexOf("ke-deptname") > -1 || item.className.indexOf("ke-posname") > -1 || item.className.indexOf("ke-customername") > -1) return true;
            if (blur) $(item).bindEvent("change",
			function () {
			    $.verifyElement(this, type)
			})
        }
        form.onsubmit = function () {
            var v = true;
            ipt = form.getElementsByTagName("INPUT");
            for (var k = 0; k < ipt.length; k++) {
                var item = ipt[k];
                if (item.type == "hidden") continue;
                var verity = item.getAttribute("verify");
                var must = item.getAttribute("must");
                if (verity == null && (must == null || must == "0")) continue;
                if (!$.verifyElement(item, type)) return false
            }
            ipt = form.getElementsByTagName("TEXTAREA");
            for (var k = 0; k < ipt.length; k++) {
                var item = ipt[k];
                var verity = item.getAttribute("verify");
                var must = item.getAttribute("must");
                if (verity == null && (must == null || must == "0")) continue;
                if (!$.verifyElement(item, type)) return false
            }
            ipt = form.getElementsByTagName("SELECT");
            for (var k = 0; k < ipt.length; k++) {
                var item = ipt[k];
                var verity = item.getAttribute("verify");
                var must = item.getAttribute("must");
                if (verity == null && (must == null || must == "0")) continue;
                if (!$.verifyElement(item, type)) return false
            }
            return v
        }
    }	
	/*验证表单 E*/	
});
//表单验证 
var checkJs={

}

$(function(){
	//弹出层取消按钮
	$(".layerCancel").closeLayer();
	$(".itemText").inputFocus()
	//清空
	$(".clearBtn").clearInput();
})