// JavaScript Document
function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : '/file/fileUpload.do',
		secureuri : false,// 是否启用安全提交,默认为false
		fileElementId : 'myBlogImage',// 文件选择框的id属性
		dataType : 'text',// 服务器返回的格式,可以是json或xml等
		success : function(data, status) {
			data = data.replace("<PRE>", '');// ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace("</PRE>", '');
			data = data.replace("<pre>", '');
			data = data.replace("</pre>", ''); // 本例中设定上传文件完毕后,服务端会返回给前台[0`filepath] 
			if (data.substring(0, 1) == 0) {// 0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
				$("img[id='uploadImage']").attr("src", data.substring(2));
				$("#picURL").val(data.substring(2));
				$('#result').html("图片上传成功<br/>");
			} else {
				$('#result').html('图片上传失败，请重试！！');
			}
		},
		error : function(data, status, e) { // 服务器响应失败时的处理函数
			$('#result').html('图片上传失败，请重试！！');
		}
	});
}
function ajaxFileUploads(type) {
	$.ajaxFileUpload({
		url : '/file/fileUpload.do',
		secureuri : false,// 是否启用安全提交,默认为false
		fileElementId : 'myBlogImage' + type,// 文件选择框的id属性
		dataType : 'text',// 服务器返回的格式,可以是json或xml等
		success : function(data, status) {
			data = data.replace("<PRE>", '');// ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace("</PRE>", '');
			data = data.replace("<pre>", '');
			data = data.replace("</pre>", ''); // 本例中设定上传文件完毕后,服务端会返回给前台[0`filepath] 
			
			if (data.substring(0, 1) == 0) {
				
				// 0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
				$("img[id='uploadImage" + type + "']").attr("src","http://localhost"+
						data.substring(2));
				
				$("#picURL"+ type).val(data.substring(2));
				$("#uploadImage"+ type).show();
				$("#picURL" + type).val(data.substring(2));
				$('#result').html("图片上传成功<br/>");
			} else {
				$('#result').html('图片上传失败，请重试！！');
			}
		},
		error : function(data, status, e) { // 服务器响应失败时的处理函数
			$('#result').html('图片上传失败，请重试！！');
		}
	});
}
function userOrderSearch(pageNo) {
	$('#pageNo').val(pageNo);
	$('#seach').submit();
}

function saveUserInfo() {
	$("#userForm").submit();
}

// 弹出对话框
var isIE = (document.all) ? true : false;
var isIE6 = isIE && ([ /MSIE (\d)\.0/i.exec(navigator.userAgent) ][0][1] == 6);
var cwidth = $(window).width();
var cheight = $(window).height();
var dheight = $(document).height();
var i;
function openprot(i) {
	$("label[class='error']").remove();
	var idname = document.getElementById("addr" + i);
	$("body").append("<div id='wrapbg' onClick='closeprot()'></div>");

	$("#wrapbg").width(cwidth);
	$("#wrapbg").height(dheight);

	$(idname).fadeIn(300);
	idname.style.left = (cwidth - idname.offsetWidth) / 2 + "px";
	idname.style.top = (cheight - idname.offsetHeight) / 2 + "px";
}

function closeprot() {
	$("div[id='wrapbg']").remove();
	$(".popbox").fadeOut(200);
}

/** 保存收货地址* */
function saveAddress() {
	var addressDefault = $("input[id='addressDefault']:checked").val();
	if (addressDefault != undefined) {
		$("#addressDefault").val("0");
	} else {
		$("#addressDefault").val("1");
	}
	$("#addressForm").submit();
}
/** 保存门店地址* */
function saveStore() {
	var storeDefault = $("input[id='storeDefault']:checked").val();
	if (storeDefault != undefined) {
		$("#storeDefault").val("0");
	} else {
		$("#storeDefault").val("1");
	}
	$("#storeForm").submit();
}

function editAddress(i, addressID) {
	$.ajax({
		url : "/editAddressPage.do",
		type : "POST",
		data : {
			addressID : addressID
		},
		dataType : "html",
		success : function(data) {
			$("#addressData").html(data);
			var idname = document.getElementById("addr" + i);
			$("body").append("<div id='wrapbg' onClick='closeprot()'></div>");

			$("#wrapbg").width(cwidth);
			$("#wrapbg").height(dheight);

			$(idname).fadeIn(400);
			idname.style.left = (cwidth - idname.offsetWidth) / 2 + "px";
			idname.style.top = (cheight - idname.offsetHeight) / 2 + "px";
		}
	});
}

function editStore(i, storeID) {
	$.ajax({
		url : "/editStorePage.do",
		type : "POST",
		data : {
			storeID : storeID
		},
		dataType : "html",
		success : function(data) {
			$("#storeData").html(data);
			var idname = document.getElementById("addr" + i);
			$("body").append("<div id='wrapbg' onClick='closeprot()'></div>");

			$("#wrapbg").width(cwidth);
			$("#wrapbg").height(dheight);

			$(idname).fadeIn(400);
			idname.style.left = (cwidth - idname.offsetWidth) / 2 + "px";
			idname.style.top = (cheight - idname.offsetHeight) / 2 + "px";
		}
	});
}

function changeProvinceID(value) {
	if (value != 0) {
		$.ajax({
			url : "/getCityByParentID.do",
			type : 'POST',
			dataType : 'json',
			data : {
				'parentID' : value
			},
			success : function(data) {
				if (data.code == 0) {
					var areaAry = data.result;
					$("#cityid").empty();
					for ( var id in areaAry) {
						$('#cityid').append(
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
		$.ajax({
			url : "/getCityByParentID.do",
			type : 'POST',
			dataType : 'json',
			data : {
				'parentID' : value
			},
			success : function(data) {
				if (data.code == 0) {
					var areaAry = data.result;
					$("#districtid").empty();
					for ( var id in areaAry) {
						$('#districtid').append(
								"<option value=" + areaAry[id].mainID + ">"
										+ areaAry[id].name + "</option>");
					}
				}
			}
		});
	}
}

function changeProvinceIDs(value) {
	if (value != 0) {
		$.ajax({
			url : "/getCityByParentID.do",
			type : 'POST',
			dataType : 'json',
			data : {
				'parentID' : value
			},
			success : function(data) {
				if (data.code == 0) {
					var areaAry = data.result;
					$("#cityids").empty();
					for ( var id in areaAry) {
						$('#cityids').append(
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
		$.ajax({
			url : "/getCityByParentID.do",
			type : 'POST',
			dataType : 'json',
			data : {
				'parentID' : value
			},
			success : function(data) {
				if (data.code == 0) {
					var areaAry = data.result;
					$("#districtids").empty();
					for ( var id in areaAry) {
						$('#districtids').append(
								"<option value=" + areaAry[id].mainID + ">"
										+ areaAry[id].name + "</option>");
					}
				}
			}
		});
	}
}
function searchUserMember() {
	// $('#pageNo').val(pageNo);
	$('#seach').submit();
}

function userPresellsearch(pageNo) {
	$('#pageNo').val(pageNo);
	$('#userPresellsearch').submit();
}

function searchhelplist(pageNo) {
	$('#pageNo').val(pageNo);
	$('#searchhelplist').submit();
}
function yzpassword() {
	var password = jQuery("#password").val();
	if (password.length < 6) {
		jQuery("#password").next("span").removeClass().addClass("wrong").html(
				"请输入不少于6位字符！");
		return false;
	}
	return true;
}

function yznewpassword() {
	var newpassword = jQuery("#newpassword").val();
	if (newpassword.length < 6) {
		jQuery("#newpassword").next("span").removeClass().addClass("wrong")
				.html("请输入不少于6位字符！");
		return false;
	} else if(newpassword.length > 16){
		jQuery("#newpassword").next("span").removeClass().addClass("wrong")
		.html("请输入不大于16位字符！");
		return false;
	}
	else {
		jQuery("#newpassword").next("span").removeClass().addClass("ok").html(
				"输入正确！");
	}
	return true;
}

function yzpasswrodagain() {
	var newpassword = jQuery("#newpassword").val();
	var passwordagain = jQuery("#passwordagain").val();
	if (passwordagain.length < 6) {
		jQuery("#passwordagain").next("span").removeClass().addClass("wrong")
				.html("请输入不少于6位字符！");
		return false;
	} else {
		jQuery("#passwordagain").next("span").removeClass().addClass("ok")
				.html("输入正确！");
	}
	if (passwordagain != newpassword) {
		jQuery("#passwordagain").next("span").removeClass().addClass("wrong")
				.html("两次输入不一致！");
		return false;
	} else {
		jQuery("#passwordagain").next("span").removeClass().addClass("ok")
				.html("输入正确！");
	}
	return true;
}


function updatePassword(){
	if (yzpassword() && yznewpassword()&& yzpasswrodagain()){
		jQuery("#passwordForm").submit();
	}
}

/** 申请退货* */
function salesOperation(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "salesOperation.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("退货已申请");
					location.reload();
				} else {
					alert("操作失败");
				}
			},
			error : function() {
				alert("操作失败");
			}
		});
	} else {
		return false;
	}
}

function cancelFavorite(id) {
	jQuery.ajax({
		url : "cancelFavorite.do",
		data : {
			"id" : id
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("取消成功！");
				location.reload();
			} else {
				alert("取消失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/** 取消订单* */
function cancelOrder(mainID) {
	jQuery.ajax({
		url : "cancelOrder.do",
		data : {
			"mainID" : mainID
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("取消成功！");
				location.reload();
			} else {
				alert("取消失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/** 删除订单* */
function deleteSalesOrder(mainID) {
	jQuery.ajax({
		url : "deleteSalesOrder.do",
		data : {
			"mainID" : mainID
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("删除成功！");
				location.reload();
			} else {
				alert("删除失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}
