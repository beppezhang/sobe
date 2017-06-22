function changeDeliverAddress(addressId, status) {
	if (confirm("是否确认？")) {
		$.ajax({
			url : "/admin/member/changeDeliveryAddress.do",
			type : "POST",
			data : {
				"addressId" : addressId,
				"status" : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("状态修改成功！");
				} else {
					alert("状态修改失败！");
				}
				location.reload();
			},
			error : function() {
			}
		});
	}
}

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
			if (data.substring(0, 1) == 0) {// 0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
				$("img[id='uploadImage" + type + "']").attr("src",
						data.substring(2));
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

/** 富文本编辑器* */
$(function() {
	KindEditor.ready(function(K) {
		jQuery("[id^='text_']").each(function(i) {
			var textareaId = jQuery(this).attr("id");
			var i = KindEditor.create("#" + textareaId, {
				width : '98.8%',
				height : '400px',
				cssPath : '/kindeditor/plugins/code/prettify.css',
				uploadJson : '/file/imgUpload.do',
				fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				fullscreenMode : false,
				afterBlur : function() {
					i.sync();
				}
			});
		});
	});

	KindEditor.ready(function(K) {
		jQuery("[id^='textarea_']").each(function(i) {
			var textareaId = jQuery(this).attr("id");
			var i = KindEditor.create("#" + textareaId, {
				width : '98.8%',
				height : '400px',
				cssPath : '/kindeditor/plugins/code/prettify.css',
				uploadJson : '/file/imgUpload.do',
				fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				fullscreenMode : false,
				afterBlur : function() {
					i.sync();
				}
			});
		});
	});
})
/** ***************商城参数start********** */
var domainObj;
function ajaxAreaMessage(mainId, domain, type) {
	domainObj = domain;
	var data = {
		parentID : mainId,
	};
	var url = domain + "/admin/system/ajaxAreaMessage.do";
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var areaAry = data.result;
				if (type == 1) {
					$('#dvProvinceID').val(mainId);
					$('#city').empty();
					for ( var id in areaAry) {
						$('#city').append(
								"<li><a href='javascript:void(0);' onclick='ajaxAreaMessage2("
										+ areaAry[id].mainID + ",1)'>"
										+ areaAry[id].name + "</a></li>");
					}
				} else if (type == 2) {
					$('#spProvinceID').val(mainId);
					$('#city2').empty();
					for ( var id in areaAry) {
						$('#city2').append(
								"<li><a href='javascript:void(0);' onclick='ajaxAreaMessage2("
										+ areaAry[id].mainID + ",2)'>"
										+ areaAry[id].name + "</a></li>");
					}
				}
			} else {

			}
		},
		error : function() {

		}
	});
}

function ajaxAreaMessage2(mainId, type) {
	var data = {
		parentID : mainId,
	};
	var url = domainObj + "/admin/system/ajaxAreaMessage.do";
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var areaAry = data.result;
				if (type == 1) {
					$('#dvCityID').val(mainId);
					$('#disctrict').empty();
					for ( var id in areaAry) {
						$('#disctrict').append(
								"<li><a href='javascript:void(0);' onclick='ajaxAreaMessage3("
										+ areaAry[id].mainID + ",1)'>"
										+ areaAry[id].name + "</a></li>");
					}
				} else if (type == 2) {
					$('#spCityID').val(mainId);
					$('#disctrict2').empty();
					for ( var id in areaAry) {
						$('#disctrict2').append(
								"<li><a href='javascript:void(0);' onclick='ajaxAreaMessage3("
										+ areaAry[id].mainID + ",2)'>"
										+ areaAry[id].name + "</a></li>");
					}
				}
			} else {

			}
		},
		error : function() {

		}
	});
}

function ajaxAreaMessage3(mainId, type) {
	if (type == 1) {
		$('#dvDisctrictID').val(mainId);
	} else if (type == 2) {
		$('#spDisctrictID').val(mainId);
	}
}
/** **************商城参数end*********** */

/** *************订单管理start*************************** */
$(function() {
	$("#confirmSalesOrder").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeOrderStatus(vals, 2);
		} else {
			alert("请选择要确认的订单");
		}
	});
	$("#outBoundSalesOrder").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeOrderStatus(vals, 3);
		} else {
			alert("请选择要出库的订单");
		}
	});
	$("#shippingSalesOrder").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeOrderStatus(vals, 4);
		} else {
			alert("请选择要发货的订单");
		}
	});
	$("#printInvoice").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			window.location.href = "orderPicking.htm?mainIds=" + vals;
		} else {
			alert("请选择要打印的配货单");
		}
	});
	$("#printCarriage").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			window.location.href = "orderLogistics.htm?mainIds=" + vals;
		} else {
			alert("请选择要打印的物流单");
		}
	});
	$("#printSalesOrder").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			window.location.href = "orderInfo.htm?mainIds=" + vals;
		} else {
			alert("请选择要打印的订单");
		}
	});
});
function changeOrderStatus(orderIDs, status) {
	var data = {
		orderIDs : orderIDs,
		orderStatus : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "changeOrderStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
					location.reload();
				} else {
					alert(data.errorMsg);
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
$(function() {
	$("#confirmReceivableAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changePaymentStatus(vals);
		} else {
			alert("请选择要收款的记录");
		}
	});
});
function changePaymentStatus(mainIDs) {
	var data = {
		mainIDs : mainIDs
	};
	if (confirm("确定要操作吗？")) {
		var url = "/admin/order/changePaymentStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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

$(function() {
	$("#confirmSettlementAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeSettlementStatus(vals,1);
			//alert(vals);
		} else {
			alert("请选择要结算的记录");
		}
	});
});
function changeSettlementStatus(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "/admin/account/changeSettlementStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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

function updateValues() {
	var argStr = getArgumentsStr();
	var url = "computeProductAmount.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			argStr : argStr
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				$("#productAmount").html(data.result);
			} else {
			}
		},
		error : function() {
		}
	});
}

function refundmentOperation(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "refundmentOperation.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("退款已申请");
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

function salesmentOperation(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "salesmentOperation.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("售后已申请");
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

function barterOperation(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "barterOperation.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("换货已申请");
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

function getArgumentsStr() {
	var str = '';
	var ids = $("#salesOrderLineIds").val();
	var idsAry = ids.split(',');
	for ( var id in idsAry) {
		str += idsAry[id] + ',' + $("#p_" + idsAry[id]).val() + ','
				+ $("#q_" + idsAry[id]).val() + '#';
	}
	return str;
}

function submitForm() {
	$("#productAmountFormValue").val($("#productAmount").html());
	$("#priceAndItemCountStr").val(getArgumentsStr());
	$("#updateSalesOrder").submit();
}

function updateLogisticsInfoForm() {
	$("#updateLogisticsInfo").submit();
}

function modifyForm() {
	$("#updateReturnApplyInfo").submit();
}

function salesApplyForm() {
	$("#updateSalesApplyInfo").submit();
}

function changeApplyStatus(mainID, status) {
	updateApplyStatus(mainID, status);
}
function changeStatus(mainID, status) {
	updateStatus(mainID, status);
}

function updateStatus(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "changeAfterSaleStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
					modifyForm();
					window.location.href = "salesApplyList.htm"
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

function updateApplyStatus(mainID, status) {
	var data = {
		mainId : mainID,
		status : status
	};
	if (confirm("确定要操作吗？")) {
		var url = "changeApplyStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
					modifyForm();
					window.location.href = "returnApplyList.htm"
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

/** 打印单子* */
function doPrint(status) {
	var valArr = new Array;
	$("div .ddanInfo").each(function(i) {
		valArr[i] = $(this).attr("data");
	})
	var mainIDs = valArr.join(',');
	if (mainIDs != "") {
		$.ajax({
			url : "doPrint.do",
			type : "POST",
			data : {
				mainIDs : mainIDs,
				status : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					window.print();
				} else {
					alert("打印失败");
				}
			},
			error : function() {
				alert("打印失败");
			}
		});
	}
}

/** 修改订单明细* */
function submitSalesOrderLine() {
	$("#lineForm").submit();
}
/** 修改订单* */
function submitSalesOrder() {
	$("#salesOrderForm").submit();
}
/** 修改订单发票* */
function submitSalesOrderInvoice() {
	$("#salesOrderInvoiceForm").submit();
}
/** 修改订单收货信息* */
function submitDeliveryAddress() {
	$("#deliveryAddressForm").submit();
}
/** 修改订单发货信息* */
function submitShippingAddress() {
	$(".itemStatus").each(function(index, ele) {
		if ($(ele).text().contains("未取货")) {
			alert("你还有未取货的商品，暂不能发货");
			return false;
		}
	});
	$(".itemSerialise").each(function(index, ele) {
		if ($(ele).val() == '') {
			alert("请输入商品序列号");
			return false;
		}
	});
	$("#shippingAddressForm").submit();
}

$(function() {
	$("#exportSalesOrder").click(function() {
		var searchOrderID=$("#searchOrderID").val();
		var searchItemID=$("#searchItemID").val();
		var searchItemName=$("#searchItemName").val();
		var searchUsername=$("#searchUsername").val();
		var searchPaymentType=$("#searchPaymentType").val();
		var searchPaymentStatus=$("#searchPaymentStatus").val();
		var searchOrderStatus=$("#searchOrderStatus").val();
		var provinceID=$("#provinceID").val();
		var cityID=$("#cityID").val();
		var districtID=$("#districtID").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "exportSalesOrder.do?searchOrderID=" + searchOrderID+"&searchItemID="+searchItemID+"&searchItemName="
												+searchItemName+"&searchUsername="+searchUsername+"&searchPaymentType="+searchPaymentType
												+"&searchPaymentStatus="+searchPaymentStatus+"&searchOrderStatus="+searchOrderStatus+
												"&provinceID="+provinceID+"&cityID="+cityID+"&districtID="+districtID;
		} else {
			return false;
		}
	});
});

$(function() {
	$("#exportSettlementDetail").click(function() {
		var finalStatementID=$("#finalStatementID").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "/admin/account/exportSettlementDetail.do?finalStatementID=" + finalStatementID;
		} else {
			return false;
		}
	});
});

$(function() {
	$("#exportSettlement").click(function() {
		var finalstatementMainID=$("#finalstatementMainID").val();
		var supplierName=$("#supplierName").val();
		var modifier=$("#modifier").val();
		var status=$("#status").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "/admin/account/exportSettlement.do?finalstatementMainID=" + finalstatementMainID+"&supplierName="
																				+supplierName+"&modifier="+modifier+"&status="+status;
		} else {
			return false;
		}
	});
});

$(function() {
	$("#exportSupplierItem").click(function() {
		var supplierName=$("#supplierName").val();
		var searchItemId=$("#searchItemId").val();
		var searchItemName=$("#searchItemName").val();
		var status=$("#status").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "/admin/supplier/exportSupplierItem.do?supplierName=" + supplierName+"&searchItemId="+searchItemId
																				+"&searchItemName="+searchItemName+"&status="+status;
		} else {
			return false;
		}
	});
});

$(function() {
	$("#exportSupplierProduct").click(function() {
		window.location.href = "exportSupplierProduct.do";
	});
});

function changeInvoiceType(value) {
	if (value == 0) {
		$("#invoicetitle").hide();
		$("#invoiceTitle").val("");
	} else {
		$("#invoicetitle").show();
	}
}
/** *************订单管理end*************************** */

/** *************促销管理start*************************** */
$(function() {
	var gradeIds = $("#gradeIDs").val();
	if (gradeIds != null && gradeIds != '') {
		var gradeIdsAry = gradeIds.split(',');
		for ( var id in gradeIdsAry) {
			$("#gradeId" + gradeIdsAry[id]).prop("checked", true);
		}
	}

	var groupIds = $("#groupIDs").val();
	if (groupIds != null && groupIds != '') {
		var groupIdsAry = groupIds.split(',');
		for ( var id in groupIdsAry) {
			$("#groupId" + groupIdsAry[id]).prop("checked", true);
		}
	}
	// 全选
	$("#selectGradeIDAll").click(function() {
		$("#gradeID :checkbox").prop("checked", true);
	});
	$("#selectGroupIDAll").click(function() {
		$("#groupID :checkbox").prop("checked", true);
	});
	// 反选
	$("#unSelectGradeID").click(function() {
		$("#gradeID :checkbox").prop("checked", false);
	});
	$("#unSelectGroupID").click(function() {
		$("#groupID :checkbox").prop("checked", false);
	});
});

function checkGradeAndGroup() {
	var gradeIDArr = new Array;
	$("#gradeID :checkbox:checked").each(function(i) {
		gradeIDArr[i] = $(this).val();
	});
	var gradeIds = gradeIDArr.join(',');
	$('#gradeIDs').val(gradeIds);

	var groupIDArr = new Array;
	$("#groupID :checkbox:checked").each(function(i) {
		groupIDArr[i] = $(this).val();
	});
	var groupIds = groupIDArr.join(',');
	$('#groupIDs').val(groupIds);
}

function submitFlashSale() {
	checkGradeAndGroup();
	$("#flashSaleForm").submit();
}

function submitAddPromotionForm() {
	checkGradeAndGroup();
	$('#addPromotionForm').submit();
}

function submitAddGroupSaleForm() {
	checkGradeAndGroup();
	$('#addGroupSaleForm').submit();
}

function submitEditPromotionForm() {
	checkGradeAndGroup();
	$('#editPromotionForm').submit();
}

function submitEditPreSaleForm() {
	$('#editPreSaleForm').submit();
}

function submitEditGroupSaleForm() {
	checkGradeAndGroup();
	$('#editGroupSaleForm').submit();
}

function ajaxAllCouponBatch(domain) {
	$.ajax({
		url : domain + "/admin/promotion/ajaxCouponBatch.do",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var couponBatchAry = data.result;
				for ( var id in couponBatchAry) {
					$('#promotionValueSelect').append(
							'<option  value="' + couponBatchAry[id].mainID
									+ '">' + couponBatchAry[id].name
									+ '</option>');
				}
			} else {
			}
		},
		error : function() {
		}
	});
	$("#promotionValue").append('</select></div>');
}
function changePromotionType(domain) {
	var promotionType = $("select").find("option:selected").val();
	if (promotionType == 1) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">扣减金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionValue"/></div>');
	} else if (promotionType == 2) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送商品：</div><div class="formRow"><select class="select" id="productid" name="productID"></select></div><div class="formRow"><select class="select" id="itemid" name="itemID"></select></div>');
		getProductAll();
	} else if (promotionType == 3) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").attr("style", "display: none");
	} else if (promotionType == 4) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">优惠折扣：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionValue"/></div>');
	} else if (promotionType == 5) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送券：</div><div class="formRow"><select id="promotionValueSelect" class="select" name="promotionValue">');
		ajaxAllCouponBatch(domain);
	} else if (promotionType == 6) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送券：</div><div class="formRow"><select id="promotionValueSelect" class="select" name="promotionValue">');
		ajaxAllCouponBatch(domain);
	} else if (promotionType == 7) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足件数：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送件数：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionValue"/></div>');
	} else if (promotionType == 8) {
		$("#promotionCondition").removeAttr("style");
		$("#promotionCondition").empty();
		$("#promotionCondition")
				.append(
						'<div class="formTt">满足件数：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionCondition"/></div>');
		$("#promotionValue").attr("style", "display: none");
	} else if (promotionType == 9) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">扣减金额：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionValue"/></div>');
	} else if (promotionType == 10) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送商品：</div><div class="formRow"><select class="select" id="productid" name="productID"><option value=""></option></select></div><div class="formRow"><select class="select" id="itemid" name="itemID"><option value=""></option></select></div>');
		getProductAll();
	} else if (promotionType == 11) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").attr("style", "display: none");
	} else if (promotionType == 12) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">优惠折扣：</div><div class="formRow"><input type="text" class="cText cTextLen" name="promotionValue"/></div>');
	} else if (promotionType == 13) {
		$("#promotionCondition").attr("style", "display: none");
		$("#promotionValue").removeAttr("style");
		$("#promotionValue").empty();
		$("#promotionValue")
				.append(
						'<div class="formTt">赠送券：</div><div class="formRow"><select id="promotionValueSelect" class="select" name="promotionValue">');
		ajaxAllCouponBatch(domain);
	}
}

function getProductAll() {
	$.ajax({
		url : "/admin/promotion/getProductAll.do",
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if (data.code == 0) {
				var areaAry = data.result;
				$("#productid").empty();
				for ( var id in areaAry) {
					$('#productid').append(
							"<option value=" + areaAry[id].mainID + ">"
									+ areaAry[id].name + "</option>");
				}
				$("#productid").attr("onchange", "changeProduct(this.value)");
				changeProduct(areaAry[0].mainID);
			}
		}
	});
}

function changeProduct(value) {
	if (value != 0) {
		$.ajax({
			url : "/admin/promotion/getItemByProductID.do",
			type : 'POST',
			dataType : 'json',
			data : {
				'productID' : value
			},
			success : function(data) {
				if (data.code == 0) {
					var areaAry = data.result;
					$("#itemid").empty();
					for ( var id in areaAry) {
						$('#itemid').append(
								"<option value=" + areaAry[id].mainID + ">"
										+ areaAry[id].name + "</option>");
					}
				}
			}
		});
	}
}

/** 删除满送促销* */
$(function() {
	$("#deletePromotionAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deletePromotion(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deletePromotion(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deletePromotion.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除预售商品* */
$(function() {
	$("#deletePreSaleAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deletePreSale(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deletePreSale(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deletePreSale.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除团购* */
$(function() {
	$("#deleteGroupSaleAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteGroupSale(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteGroupSale(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteGroupSale.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除限时抢购* */
$(function() {
	$("#deleteFlashSaleAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteFlashSale(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteFlashSale(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteFlashSale.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 选择优惠券类型* */
function changeCouponType(value) {
	if (value == 4) {
		$("#scoreId").show();
		$("#couponcountId").show();
		$("#useTime").show();
		$("#couponDay").hide();
	} else if (value == 1) {
		$("#scoreId").hide();
		$("#couponcountId").hide();
		$("#useTime").hide();
		$("#couponDay").show();
	} else {
		$("#scoreId").hide();
		$("#couponcountId").show();
		$("#useTime").show();
		$("#couponDay").hide();
	}
}

/** 删除批量优惠券* */
$(function() {
	$("#deleteCouponBatchAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteCouponBatch(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteCouponBatch(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteCouponBatch.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 改变优惠券状态* */
function changeCouponStatus(mainID, status) {
	var data = {
		mainID : mainID,
		status : status
	};
	var str = "";
	if (status == 0) {
		str = "启用";
	} else if (status == 1) {
		str = "发放";
	} else if (status == 3) {
		str = "中止";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeCouponStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}

/** 改变批量优惠券下的优惠券状态* */
function changeCouponBatchStatus(mainID, status) {
	var data = {
		mainID : mainID,
		status : status
	};
	var str = "";
	if (status == 0) {
		str = "启用";
	} else if (status == 1) {
		str = "发放";
	} else if (status == 3) {
		str = "中止";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeCouponBatchStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除促销条件* */
function deletePromotionSet(id) {
	var data = {
		id : id
	};
	if (confirm("确定要删除吗")) {
		var url = "ajaxDeletePromotionSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除商品积分* */
$(function() {
	$("#deleteItemScoreAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteItemScore(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteItemScore(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteItemScore.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** *************促销管理end*************************** */

/** ***************会员中心************* */
function userSearch(pageNo) {
	$('#pageNo').val(pageNo);
	$('#serachform').submit();
}

function changeProvinceID(value) {
	if (value != 0) {
		$.ajax({
			url : "/admin/member/getCityByParentID.do",
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
			url : "/admin/member/getCityByParentID.do",
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
function changeProvince(mainID) {
	$('#provinceID').val(mainID);

}

function changeProvinceIDs(value) {
	if (value != 0) {
		$.ajax({
			url : "/admin/member/getCityByParentID.do",
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
			url : "/admin/member/getCityByParentID.do",
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

function deleteCustomer(mainId) {
	var data = {
		mainIds : mainId
	};
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteCustomer.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

function isLockCustomer(mainId, status) {
	var data = {
		mainIds : mainId,
		status : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "isLockCustomer.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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

$(function() {
	// 全选或全不选
	$("#all").click(function() {
		if (this.checked) {
			$("#list :checkbox").prop("checked", true);
		} else {
			$("#list :checkbox").prop("checked", false);
		}
	});
	// 全选
	$("#selectAll").click(function() {
		$("#list :checkbox,#all").prop("checked", true);
	});
	// 全不选
	$("#unSelect").click(function() {
		$("#list :checkbox,#all").prop("checked", false);
	});
	// // 反选
	// $("#reverse").click(function() {
	// $("#list :checkbox").each(function() {
	// $(this).attr("checked", !$(this).attr("checked"));
	// });
	// allchk();
	// });

	// 设置全选复选框
	$("#list :checkbox").click(function() {
		allchk();
	});

	$("#lockCustomer").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			isLockCustomer(vals, 1);
		} else {
			alert("请选择要解锁的会员");
		}
	});

	$("#unlockCustomer").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			isLockCustomer(vals, 2);
		} else {
			alert("请选择要锁定的会员");
		}
	});

	// 获取选中选项的值
	$("#getValue").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		alert(vals);
	});
});
function allchk() {
	var chknum = $("#list :checkbox").size();// 选项总个数
	var chk = 0;
	$("#list :checkbox").each(function() {
		if ($(this).prop("checked") == true) {
			chk++;
		}
	});
	if (chknum == chk) {// 全选
		$("#all").prop("checked", true);
	} else {// 不全选
		$("#all").prop("checked", false);
	}
}

$(function() {
	$("#exportMember").click(function() {
		
		var searchUserName = $("#searchUserName").val();
		var searchName = $("#searchName").val();
		var searchMobile = $("#searchMobile").val();
		var searchEmail = $("#searchEmail").val();
		//var gradeID = $("#gradeID").val();
		var searchStatus = $("#searchStatus").val();
		//var provinceID = $("#provinceID").val();
		//var cityID = $("#cityID").val();
		//var districtID = $("#districtID").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "exportMember.do?searchUserName=" + searchUserName+"&searchName="+searchName+"&searchMobile="
														+searchMobile+"&searchEmail="+searchEmail+"&searchStatus="
														+searchStatus;
			return false;
		}
	});
});
$(function() {
	$("#exportSupplier").click(function() {
		var searchMobile = $("#searchMobile").val();
		var searchCompanyName = $("#searchCompanyName").val();
		var searchLinkMan = $("#searchLinkMan").val();
		var searchOpeningBank = $("#searchOpeningBank").val();
		var searchBankAccount = $("#searchBankAccount").val();
		var searchAddress = $("#searchAddress").val();
		var searchUserName = $("#searchUserName").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "/admin/supplier/exportSupplier.do?searchMobile=" + searchMobile+"&searchCompanyName="+searchCompanyName
												+"&searchLinkMan="+searchLinkMan+"&searchOpeningBank="+searchOpeningBank+"&searchBankAccount="+
														searchBankAccount+"&searchAddress="+searchAddress+"&searchUserName="+searchUserName;
		} else {
			return false;
		}
	});
});
$(function() {
	$("#exportCustomerGroupMember")
			.click(
					function() {
						var valArr = new Array;
						var groupID = $("#exportCustomerGroupMember").attr(
								"data-value");
						$("#list :checkbox:checked").each(function(i) {
							valArr[i] = $(this).val();
						});
						var vals = valArr.join(',');
						if (vals != "") {
							if (confirm("确定要导出数据吗？")) {
								window.location.href = "exportCustomerGroupMember.do?mainIds="
										+ vals + "&groupID=" + groupID;
							} else {
								return false;
							}
						} else {
							alert("请选择要导出的数据");
						}
					})
})

$(function() {
	$("#deleteMemberGradeAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteMemberGrade(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
/** 删除会员等级* */
function deleteMemberGrade(mainIds) {
	var data = {
		mainIds : mainIds
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteMemberGrade.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 等级配置* */
function changeGradeType(value) {
	if (value == "2") {
		$("#timeID").show();
	} else {
		$("#timeID").hide();
	}
}
/** 删除收货地址* */
function deleteAddress(id) {
	var data = {
		id : id
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteAddress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function setDefaultAddress(addressId, customerId) {
	var data = {
		addressId : addressId,
		customerId : customerId
	}
	if (confirm("确定设为默认吗？")) {
		var url = "updateDefaultAddress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("设置成功");
					location.reload();
				} else {
					alert("设置失败");
				}
			},
			error : function() {
				alert("设置失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除商品咨询* */
$(function() {
	$("#deleteConsultingAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteConsulting(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteConsulting(mainIds) {
	var data = {
		mainIds : mainIds
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteConsulting.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 审核商品评价* */
$(function() {
	$("#checkReviewAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			checkReview(vals);
		} else {
			alert("请选择要审核的记录");
		}
	});
});
function checkReview(mainIds) {
	var data = {
		mainIds : mainIds
	}
	if (confirm("确定要审核吗？")) {
		var url = "checkReview.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("审核成功");
					location.reload();
				} else {
					alert("审核失败");
				}
			},
			error : function() {
				alert("审核失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除会员分组* */
$(function() {
	$("#deleteMemberGroupAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteMemberGroup(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});

function deleteMemberGroup(mainIds) {
	var data = {
		mainIds : mainIds
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteMemberGroup.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除会员分组条件* */
function deleteGroupSet(id) {
	var data = {
		id : id
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 修改分组条件* */
function editGroupSet(id) {
	var sexType = $("#sexType" + id).val();
	var minimum = $("#minimum" + id).val();
	var maxmum = $("#maxmum" + id).val();
	var data = {
		id : id,
		sexType : sexType,
		minimum : minimum,
		maxmum : maxmum
	}
	if (confirm("确定要修改数据吗？")) {
		var url = "editGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("修改成功");
					location.reload();
				} else {
					alert("修改失败");
				}
			},
			error : function() {
				alert("修改失败");
			}
		});
	} else {
		return false;
	}
}

/** 添加供应商分组条件* */
function saveSupplierGroupSet() {
	var groupId = $("#groupId").val();
	var setType = $('#setType').val();
	var minimum = $("#minimum").val();
	var maxmum = $("#maxmum").val();
	var data = {
		groupId : groupId,
		setType : setType,
		minimum : minimum,
		maxmum : maxmum
	}
	if (confirm("确定要保存吗？")) {
		var url = "addSupplierGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("保存成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}

/** 添加分组条件* */
function saveGroupSet() {
	var groupId = $("#groupId").val();
	var setType = $('#setType').val();
	var minimum = $("#minimum").val();
	var maxmum = $("#maxmum").val();
	if (setType == "6") {
		minimum = $("#sexType").val();
	}
	var data = {
		groupId : groupId,
		setType : setType,
		minimum : minimum,
		maxmum : maxmum
	}
	if (confirm("确定要保存吗？")) {
		var url = "addGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("保存成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}

/** 删除供应商分组* */
$(function() {
	$("#deleteSupplierGroupAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteSupplierGroup(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteSupplierGroup(mainIds) {
	var data = {
		mainIds : mainIds
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteSupplierGroup.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除供应商分组条件* */
function deleteSupplierGroupSet(id) {
	var data = {
		id : id
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteSupplierGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 修改供应商分组条件* */
function editSupplierGroupSet(id) {
	var sexType = $("#sexType" + id).val();
	var minimum = $("#minimum" + id).val();
	var maxmum = $("#maxmum" + id).val();
	var data = {
		id : id,
		sexType : sexType,
		minimum : minimum,
		maxmum : maxmum
	}
	if (confirm("确定要修改数据吗？")) {
		var url = "editSupplierGroupSet.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("修改成功");
					location.reload();
				} else {
					alert("修改失败");
				}
			},
			error : function() {
				alert("修改失败");
			}
		});
	} else {
		return false;
	}
}

/** 再次发送邮件* */
function sendEmail(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定再次发送邮件吗？")) {
		var url = "sendEmail.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("发送成功");
					location.reload();
				} else {
					alert("发送失败");
				}
			},
			error : function() {
				alert("发送失败");
			}
		});
	} else {
		return false;
	}
}

/** 再次发送短信* */
function sendSms(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定再次发送短信吗？")) {
		var url = "sendSms.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("发送成功");
					location.reload();
				} else {
					alert("发送失败");
				}
			},
			error : function() {
				alert("发送失败");
			}
		});
	} else {
		return false;
	}
}

/** 再次发送微信* */
function sendWechatMessage(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定再次发送微信吗？")) {
		var url = "sendWechatMessage.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("发送成功");
					location.reload();
				} else {
					alert("发送失败");
				}
			},
			error : function() {
				alert("发送失败");
			}
		});
	} else {
		return false;
	}
}

/** ********************产品和商品管理 start ***************** */
function deleteProductType(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductType.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

function deleteProductPropVale(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductPropVale.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteProductTypeProductProp(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductTypeProductProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

function deleteProductTypeItemProp(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductTypeItemProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteBrand(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteBrand.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteItemtPropVale(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteItemtPropVale.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteProductProp(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteItemProp(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteItemProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteProductCategory(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductCategory.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteProduct(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProduct.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteProductTypeBrand(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteProductTypeBrand.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

$(function() {
	$("#upProduct").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			updateProductStatus(vals, 1);
		} else {
			alert("请选择要上架的产品");
		}
	});
	$("#downProduct").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			updateProductStatus(vals, 0);
		} else {
			alert("请选择要下架的产品");
		}
	});
})

function updateProductStatus(mainIDs, status) {
	var data = {
		mainIDs : mainIDs,
		status : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "updateProductStatus.do";
		jQuery.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
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
function updateSupplierItemStatus(mainID, status) {
	var data = {
		mainID : mainID,
		status : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "updateSupplierItemStatus.do";
		jQuery.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
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
function changeProductProp() {
	var productPropName = $("select").find("option:selected").text();
	$('#productPropName').val(productPropName);
	var mainId = $("select").find("option:selected").val()
	var productPropNames = $('#productPropName_' + mainId).val();
	$('#productPropValue').html(productPropNames);
}
function changeItemProp() {
	var itemPropName = $("select").find("option:selected").text();
	$('#itemPropName').val(itemPropName);
	var mainId = $("select").find("option:selected").val()
	var itemPropNames = $('#itemPropName_' + mainId).val();
	$('#itemPropValue').html(itemPropNames);
}

function saveProductTypeProductProp() {
	var productPropName = $('#productPropName').val();
	var productPropID = $('#changeProductProp').val();
	var productTypeMainID = $('#productTypeMainID').val();
	var productPropValueID = [];
	$('input[name="productPropValue"]:checked').each(function() {
		productPropValueID.push($(this).val());
	});
	var data = {
		productPropName : productPropName,
		productPropID : productPropID,
		productTypeMainID : productTypeMainID,
		productPropValueID : productPropValueID
	}
	if (confirm("确定要保存吗？")) {
		var url = "addProductTypeProductProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}
function editProductTypeProductProp(mainID, productPropID) {
	var productPropName = $('#propName_' + mainID).val();
	var productTypeMainID = $('#productTypeMainID').val();
	var productPropValueID = [];
	$('input[name="propName_' + mainID + '"]:checked').each(function() {
		productPropValueID.push($(this).attr("data"));
	});
	var data = {
		mainID : mainID,
		productPropName : productPropName,
		productPropID : productPropID,
		productTypeMainID : productTypeMainID,
		productPropValueID : productPropValueID
	}
	if (confirm("确定要保存吗？")) {
		var url = "addProductTypeProductProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}
function saveProductTypeItemProp() {
	var itemPropName = $('#itemPropName').val();
	var itemPropID = $('#changeItemProp').val();
	var productTypeMainID = $('#productTypeMainID').val();
	var itemPropValueID = [];
	$('input[name="itemPropValue"]:checked').each(function() {
		itemPropValueID.push($(this).val());
	});
	var data = {
		itemPropName : itemPropName,
		itemPropID : itemPropID,
		productTypeMainID : productTypeMainID,
		itemPropValueID : itemPropValueID
	}
	if (confirm("确定要保存吗？")) {
		var url = "saveProductTypeItemProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}

function editProductTypeItemProp(mainID, itemPropID) {
	var itemPropName = $('#propName_' + mainID).val();
	var productTypeMainID = $('#productTypeMainID').val();
	var itemPropValueID = [];
	$('input[name="propName_' + mainID + '"]:checked').each(function() {
		itemPropValueID.push($(this).attr("data"));
	});
	var data = {
		mainID : mainID,
		itemPropName : itemPropName,
		itemPropID : itemPropID,
		productTypeMainID : productTypeMainID,
		itemPropValueID : itemPropValueID
	}
	if (confirm("确定要保存吗？")) {
		var url = "saveProductTypeItemProp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("成功");
					location.reload();
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
}

function saveProductProp(productPropMainID) {
	var productPropValue = $('#PP_' + productPropMainID).val();
	if (productPropValue != "") {
		$('#PP_' + productPropMainID).siblings().remove();
		var data = {
			productPropValue : productPropValue,
			productPropMainID : productPropMainID
		}
		if (confirm("确定要保存吗？")) {
			var url = "saveProductPropValue.do";
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						alert("保存成功");
						location.reload();
					} else {
						alert("保存失败");
					}
				},
				error : function() {
					alert("保存失败");
				}
			});
		}
	} else {
		$('#PP_' + productPropMainID).siblings().remove();
		$('#PP_' + productPropMainID).after(
				"<label id='name-error' class='error' for='name'>必填字段</label>");
	}
}

function addItemPropValue(itemPropMainID) {
	var propValue = $('#PP_' + itemPropMainID).val();
	if (propValue != "") {
		$('#PP_' + itemPropMainID).siblings().remove();
		var data = {
			propValue : propValue,
			itemPropMainID : itemPropMainID
		}
		if (confirm("确定要保存吗？")) {
			var url = "addItemPropValue.do";
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						alert("保存成功");
						location.reload();
					} else {
						alert("保存失败");
					}
				},
				error : function() {
					alert("保存失败");
				}
			});
		}
	} else {
		$('#PP_' + itemPropMainID).siblings().remove();
		$('#PP_' + itemPropMainID).after(
				"<label id='name-error' class='error' for='name'>必填字段</label>");
	}
}

function addProductDetail(mainID, propType) {
	var productID = $("#productID").val();
	var productPropID = mainID;
	var productPropValue = [];
	var nameID = "productPropValue_" + mainID;

	$("input[name='" + nameID + "']:checked").each(function() {
		productPropValue.push($(this).val());
	});
	var data = {
		productID : productID,
		productPropID : productPropID,
		productPropValue : productPropValue
	}
	if (confirm("确定要保存吗？")) {
		var url = "addProductDetail.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("保存成功");
				} else {
					alert("保存失败");
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
	}

}
function changeProductCategoryOne() {
	var mainId = $('#one').val();
	if (mainId == 0) {
		$("#two").empty();
	}
	$("#parentID").val(mainId);
	var data = {
		mainID : mainId
	}
	var url = "findProductCatByParentID.do";
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var catAry = data.result;
				$("#two").empty();
				$("#two").append("<option value='0'>无</option>");
				for ( var id in catAry) {
					$("#two").append(
							"<option value='" + catAry[id].mainID + "'>"
									+ catAry[id].name + "</option>");
				}
			} else {
				alert("加载失败");
			}
		},
		error : function() {
			alert("加载失败");
		}
	});
}

function changeProductCategorytwo() {
	var mainId = $('#two').val();
	$("#parentID").val(mainId);
}

/** 添加销售规格* */
function submitProp() {
	var propname = jQuery("#propname").val();
	var propvalue = jQuery("#propvalue").val();
	if (propname != "" && propvalue != "") {
		jQuery("#propname").siblings().remove();
		jQuery("#labelId").next().remove();
		jQuery("#propForm").submit();
	} else {
		if (propname == "") {
			jQuery("#propname").siblings().remove();
			jQuery("#propname")
					.after(
							"<label id='name-error' class='error' for='name'>必填字段</label>");
		} else {
			jQuery("#propname").siblings().remove();
		}
		if (propvalue == "") {
			jQuery("#labelId").next().remove();
			jQuery("#labelId")
					.after(
							"<label id='name-error' class='error' for='name'>必填字段</label>");
		} else {
			jQuery("#labelId").next().remove();
		}
	}
}

/** 添加产品属性* */
function submitProductProp() {
	var mainID = jQuery("#mainID").val();
	var name = jQuery("#name").val();
	var productPropValue = jQuery("#productPropValue").val();
	if (mainID != "" && name != "" && productPropValue != "") {
		jQuery("#mainID").siblings().remove();
		jQuery("#name").siblings().remove();
		jQuery("#labelId").next().remove();
		jQuery("#productpropForm").submit();
	} else {
		if (mainID == "") {
			jQuery("#mainID").siblings().remove();
			jQuery("#mainID")
					.after(
							"<label id='name-error' class='error' for='name'>必填字段</label>");
		} else {
			jQuery("#mainID").siblings().remove();
		}
		if (name == "") {
			jQuery("#name").siblings().remove();
			jQuery("#name")
					.after(
							"<label id='name-error' class='error' for='name'>必填字段</label>");
		} else {
			jQuery("#name").siblings().remove();
		}
		if (productPropValue == "") {
			jQuery("#labelId").next().remove();
			jQuery("#labelId")
					.after(
							"<label id='name-error' class='error' for='name'>必填字段</label>");
		} else {
			jQuery("#labelId").next().remove();
		}
	}
}

/** ********************产品和商品管理 end ***************** */

/** *************退款列表start*************************** */
$(function() {
	$("#confirmRefundleAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeAccountDetailStatus(vals);
		} else {
			alert("请选择要退款的记录");
		}
	});
});
function changeAccountDetailStatus(accountIDs) {
	var data = {
		accountIDs : accountIDs
	};
	if (confirm("确定要操作吗？")) {
		var url = "changeAccountDetailStatus.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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
/** *************退款列表end*************************** */

/** *************内容管理start*************************** */
/** 删除内容分类* */
function deleteCmsCategory(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteCmsCategory.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** *************内容管理end*************************** */
/** 删除新闻* */
function deleteNews(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteNews.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除公告* */
function deleteNotice(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteNotice.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除供应商* */
function deleteSupplier(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteSupplier.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除广告* */
function deleteAdvert(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteAdvert.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除帮助* */
function deleteHelp(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteHelp.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 广告* */
// 商品搜索
$(function() {
	var close1 = true;
	$("#linkName")
			.keyup(
					function() {
						var nkey = $.trim($(this).val());
						var type = $("#linkType").val();
						if ("" == nkey || null == nkey) {
							$("#results_id").hide();
						} else {
							$
									.ajax({
										url : 'getAdvertLinkByName.do',
										data : {
											name : nkey,
											type : type
										},
										type : "POST",
										success : function(data) {
											if (data.code == 0) {
												var ary = data.result;
												var html = "<table>";
												for ( var id in ary) {
													html = html
															+ "<tr><td><input type='hidden' value="
															+ ary[id].mainID
															+ ">"
															+ ary[id].name
															+ "</td></tr>";
												}
												html = html + "</table>";
												$("#results_id").show();
												$("#results_id").html(html);
												$("#results_id table tr td")
														.click(
																function() {
																	$(
																			"#linkMainID")
																			.val(
																					$(
																							this)
																							.find(
																									"input")
																							.val());
																	$(
																			"#linkName")
																			.val(
																					$(
																							this)
																							.text());
																	$(
																			"#results_id")
																			.hide();
																})
														.hover(function() {
															close1 = false;
														}, function() {
															close1 = true;
														});
											}
										}
									})
						}
					}).blur(function() {
				if (close1) {
					$("#results_id").hide();
				}
			});
})

function changeLinkType() {
	$("#linkName").val("");
}

function changeReplyType(type) {
	if (type == "1") {
		$("#replyContent").show();
		$("#replyType").hide();
	} else {
		$("#replyType").show();
		$("#replyContent").hide();
	}

}
function changeKeywordType(type) {
	if (type == "1") {
		$("#replyContent").show();
		$("#replyType").hide();
	} else {
		$("#replyType").show();
		$("#replyContent").hide();
	}

}

function changeMenuType(type) {
	if (type == "1") {
		$("#menuType").hide();
	} else {
		$("#menuType").show();
	}

}
/** 删除关注回复* */
$(function() {
	$("#deleteWechatReplyAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteWechatReply(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteWechatReply(mainIDs) {
	var data = {
		mainIDs : mainIDs
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteWechatReply.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除关键词回复* */
$(function() {
	$("#deleteWechatKeywordAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteWechatKeyword(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteWechatKeyword(mainIDs) {
	var data = {
		mainIDs : mainIDs
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteWechatKeyword.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除菜单配置* */
$(function() {
	$("#deleteWechatMenuAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteWechatMenu(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteWechatMenu(mainIDs) {
	var data = {
		mainIDs : mainIDs
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteWechatMenu.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** *************内容管理end*************************** */
/** *************系统设置start*************************** */

function deleteExpress(mainID) {
	var data = {
		mainID : mainID,
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteExpress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
function deleteExpressFormat(mainID) {
	var data = {
		mainID : mainID,
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteExpressFormat.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 设置默认发货地址* */
function setDefaultShippingAddress(id) {
	var data = {
		id : id,
	}
	if (confirm("确定设为默认吗？")) {
		var url = "updateDefaultShippingAddress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("设置成功");
					location.reload();
				} else {
					alert("设置失败");
				}
			},
			error : function() {
				alert("设置失败");
			}
		});
	} else {
		return false;
	}
}
/** 设置默认退货地址* */
function setForReturnShippingAddress(id) {
	var data = {
		id : id,
	}
	if (confirm("确定设为默认吗？")) {
		var url = "updateForReturnShippingAddress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("设置成功");
					location.reload();
				} else {
					alert("设置失败");
				}
			},
			error : function() {
				alert("设置失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除发货地址* */
function deleteShippingAddress(id) {
	var data = {
		id : id,
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteShippingAddress.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除部门* */
function deleteDepartment(mainId) {
	var data = {
		mainID : mainId,
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteDepartment.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 设置是否限制ip访问 * */
function setIP() {
	if ($("#ipLimitedActive")[0].checked) {
		$("#ipactive").val("1");
	} else {
		$("#ipactive").val("0");
	}
}

/** 删除用户* */
function deleteOperator(mainID) {
	var data = {
		mainID : mainID
	};
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteOperator.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 锁定/解锁用户* */
function isLockOperator(mainId, status) {
	var data = {
		mainId : mainId,
		status : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "isLockOperator.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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

/** 设置模板* */
function setCheckMessageActive(functionID, type) {
	var status = 0;
	if ($("#activeCheck" + type + functionID)[0].checked) {
		status = 1;
	}
	var data = {
		functionID : functionID,
		type : type,
		status : status
	}
	if (confirm("确定要操作吗？")) {
		var url = "setCheckMessageActive.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("操作成功");
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
/** 删除支付接口* */
$(function() {
	$("#deleteInterfaceConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteInterfaceConfig(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteInterfaceConfig(mainIDs) {
	var data = {
		mainIDs : mainIDs
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteInterfaceConfig.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 启用/停用支付接口* */
$(function() {
	$("#openInterfaceConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeInterfaceConfigActive(vals, 1);
		} else {
			alert("请选择要启用的记录");
		}
	});
	$("#closeInterfaceConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeInterfaceConfigActive(vals, 0);
		} else {
			alert("请选择要停用的记录");
		}
	});
});
function changeInterfaceConfigActive(mainIDs, active) {
	var data = {
		mainIDs : mainIDs,
		active : active
	};
	var str = "";
	if (active == "1") {
		str = "启用";
	} else {
		str = "停用";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeInterfaceConfigActive.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除邮件接口* */
$(function() {
	$("#deleteEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteEmailConfig(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteEmailConfig(mainIDs) {
	var data = {
		mainIDs : mainIDs
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteEmailConfig.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 启用/停用邮件接口* */
$(function() {
	$("#openEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeEmailConfigActive(vals, 1);
		} else {
			alert("请选择要启用的记录");
		}
	});
	$("#closeEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeEmailConfigActive(vals, 0);
		} else {
			alert("请选择要停用的记录");
		}
	});
});
function changeEmailConfigActive(mainIDs, active) {
	var data = {
		mainIDs : mainIDs,
		active : active
	};
	var str = "";
	if (active == "1") {
		str = "启用";
	} else {
		str = "停用";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeEmailConfigActive.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}
/** 删除运费模板* */
function deleteFreightTemplate(mainID) {
	var data = {
		mainID : mainID
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteFreightTemplate.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 设置默认运费模板* */
function setDefaultFreightTemplate(mainID) {
	var data = {
		mainID : mainID
	};
	if (confirm("确定要默认吗")) {
		var url = "setDefaultFreightTemplate.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("设置成功");
					location.reload();
				} else {
					alert("设置失败");
				}
			},
			error : function() {
				alert("设置失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除角色* */
function deleteRole(mainID) {
	var data = {
		mainID : mainID
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteRole.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除邮件接口* */
$(function() {
	$("#deleteEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteEmailConfig(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteEmailConfig(mainIDs) {
	var data = {
		mainIDs : mainIDs
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteEmailConfig.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** 启用/停用邮件接口* */
$(function() {
	$("#openEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeEmailConfigActive(vals, 1);
		} else {
			alert("请选择要启用的记录");
		}
	});
	$("#closeEmailConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeEmailConfigActive(vals, 0);
		} else {
			alert("请选择要停用的记录");
		}
	});
});
function changeEmailConfigActive(mainIDs, active) {
	var data = {
		mainIDs : mainIDs,
		active : active
	};
	var str = "";
	if (active == "1") {
		str = "启用";
	} else {
		str = "停用";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeEmailConfigActive.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}

/** 删除微信接口* */
$(function() {
	$("#deleteWechatConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			deleteWechatConfig(vals);
		} else {
			alert("请选择要删除的记录");
		}
	});
});
function deleteWechatConfig(mainIDs) {
	var data = {
		mainIDs : mainIDs
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteWechatConfig.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}
/** 启用/停用微信接口* */
$(function() {
	$("#openWechatConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeWechatConfigActive(vals, 1);
		} else {
			alert("请选择要启用的记录");
		}
	});
	$("#closeWechatConfigAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			changeWechatConfigActive(vals, 0);
		} else {
			alert("请选择要停用的记录");
		}
	});
});
function changeWechatConfigActive(mainIDs, active) {
	var data = {
		mainIDs : mainIDs,
		active : active
	};
	var str = "";
	if (active == "1") {
		str = "启用";
	} else {
		str = "停用";
	}
	if (confirm("确定要" + str + "吗")) {
		var url = "changeWechatConfigActive.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert(str + "成功");
					location.reload();
				} else {
					alert(str + "失败");
				}
			},
			error : function() {
				alert(str + "失败");
			}
		});
	} else {
		return false;
	}
}

function deleteWhiteIP() {
	var ids = $("#whiteID").val();
	var id = ids[0];
	var data = {
		id : id
	};
	if (confirm("确定要删除吗")) {
		var url = "deleteWhiteIP.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

/** *************系统设置end*************************** */

/** 确认收款* */
$(function() {
	$("#confirmAccountReceivableAll").click(function() {
		var valArr = new Array;
		$("#list :checkbox:checked").each(function(i) {
			valArr[i] = $(this).val();
		});
		var vals = valArr.join(',');
		if (vals != "") {
			confirmReceivable(vals);
		} else {
			alert("请选择要确认收款的记录");
		}
	});
});
function confirmReceivable(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要收款吗")) {
		var url = "/admin/receivable/confirmReceivable.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("确认成功");
					location.reload();
				} else {
					alert("确认失败");
				}
			},
			error : function() {
				alert("确认失败");
			}
		});
	} else {
		return false;
	}
}

/** 撤销收款* */
function cancelReceivable(mainIds) {
	var data = {
		mainIds : mainIds
	};
	if (confirm("确定要撤销吗")) {
		var url = "/admin/receivable/cancelReceivable.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("撤销成功");
					location.reload();
				} else {
					alert("撤销失败");
				}
			},
			error : function() {
				alert("撤销失败");
			}
		});
	} else {
		return false;
	}
}

$(function() {
	$("#exportItem").click(function() {
		var searchmainID=$("#searchmainID").val();
		var searchName=$("#searchName").val();
		var searchStartModifyTime=$("#searchStartModifyTime").val();
		var searchEndModifyTime=$("#searchEndModifyTime").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "exportItem.do?searchmainID=" + searchmainID+"&searchName="+searchName+"&searchStartModifyTime="
																+searchStartModifyTime+"&searchEndModifyTime="+searchEndModifyTime;
		} else {
			return false;
		}
	});

	$("#exportPurchaseItem").click(function() {
		var searchmainID=$("#searchmainID").val();
		var searchName=$("#searchName").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "exportPurchaseItem.do?searchmainID=" + searchmainID+"&searchName="+searchName;
		} else {
			return false;
		}
	})

	$("#exportFinanceReport")
			.click(
					function() {
						var searchmainID = $("#searchmainID").val();
						var searchcustomerName = $("#searchcustomerName").val();
						var searchStartTime = $("#searchStartTime").val();
						var searchEndTime = $("#searchEndTime").val();
						var searchType = $("#searchType").val();
						if (confirm("确定要导出数据吗？")) {
							window.location.href = "exportFinanceReport.do?searchmainID=" + searchmainID+"&searchcustomerName="+
													searchcustomerName+"&searchStartTime="+searchStartTime+"&searchEndTime="+
													searchEndTime+"&searchType="+searchType;
						} else {
							return false;
						}
					})
	$("#exportReceivable").click(function() {
		var searchmainID=$("#searchmainID").val();
		var searchexpressNumber=$("#searchexpressNumber").val();
		var searchType=$("#searchType").val();
		var searchStartTime=$("#searchStartTime").val();
		var searchEndTime=$("#searchEndTime").val();
		var searchStartModifyTime=$("#searchStartModifyTime").val();
		var searchEndModifyTime=$("#searchEndModifyTime").val();
		var searchStatus=$("#searchStatus").val();
		var provinceID=$("#provinceID").val();
		var cityID=$("#cityID").val();
		var districtID=$("#districtID").val();
		if (confirm("确定要导出数据吗？")) {
			window.location.href = "exportReceivable.do?searchmainID=" + searchmainID+"&searchexpressNumber="+searchexpressNumber+"&searchType="
											+searchType+"&searchStartTime="+searchStartTime+"&searchEndTime="+searchEndTime+"&searchStartModifyTime="
											+searchStartModifyTime+"&searchEndModifyTime="+searchEndModifyTime+"&searchStatus="+searchStatus
											+"&provinceID="+provinceID+"&cityID="+cityID+"&districtID="+districtID;
		} else {
			return false;
		}
	});
});

function deleteMemberApply(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteMemberApply.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}

function deleteSupplierItem(mainId) {
	var data = {
		mainID : mainId
	}
	if (confirm("确定要删除数据吗？")) {
		var url = "deleteSupplierItem.do";
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					alert("删除成功");
					location.reload();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("删除失败");
			}
		});
	} else {
		return false;
	}
}