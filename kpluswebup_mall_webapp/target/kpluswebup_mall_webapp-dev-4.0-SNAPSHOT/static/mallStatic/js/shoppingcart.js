$(function() {
	$("#addressForm").validate({
		groups: {
			contact: "mobile telephone"
		},
		errorPlacement: function(error, element) {  //错误提示在什么地方  
	         if (element.attr("name") == "mobile" || element.attr("name") == "telephone" ){  
	           error.insertAfter("#phone-last");   
	         }else{  
	           error.insertAfter(element);  
	         }  
	    },
		rules : {
			name : {
				required : true
			},
			address : {
				required : true
			},
			mobile : {
				required : {  
                    depends:function(){ //二选一  
                        return ($('input[name=telephone]').val().length <= 0);  
                    }  
                },  
				digits : true,
				maxlength : 11
			},
			telephone : {
				required : {  
                    depends:function(){ //二选一  
                        return ($('input[name=mobile]').val().length <= 0);  
                    }  
                },  
			},
			
		},
		messages : {
			name : {
				required : "必填字段"
			},
			address : {
				required : "必填字段"
			},
			mobile : {
				required : "固话或者手机号至少填一项",
				digits : "只能输入数字",
				maxlength : jQuery.validator.format("请输入正确的手机号")
			},
			telephone : {
				required : "固话或者手机号至少填一项"
			}
		}
	});

	$(".add-new").click(function(){
		addressInit();
		$(".noAddr").show();
	})
	$(".add-cancel").click(function(){
		addressInit();
		$(".noAddr").hide();
	})
	$(".isdefault").click(function(){
		if(this.checked)
			$(this).val(1);
		else
			$(this).val(0);
	})
});

/** shoppingcart_1.vm js* */
// counter
$(".number_control").on(
		"click",
		"a",
		function() {
			var _t = $(this);
			var _v = _t.closest("div").find("input[name='num']");
			var nStock = $(this).closest("div").find(".stock").val();
			var unitPrice = $(this).closest("tr").find("td").eq(2).find(
					"em.price").html();
			var unitPoints = $(this).closest("tr").find("td").eq(2).find(
					"em.jf").html();

			var totalPrices = parseFloat(unitPrice);
			var totalPoints = parseInt(unitPoints);

			if (_t.hasClass("prev") && _v.val() <= 1) {
				return;
			}
			if (_t.hasClass("next") && _v.val() == nStock) {
				return;
			} else {
				_v.val(_t.hasClass("prev") ? parseInt(_v.val()) - 1
						: parseInt(_v.val()) + 1);
				$(this).closest("tr").find("td").eq(4).find("em.price")
						.html(
								(_t.hasClass("prev") ? parseFloat(
										totalPrices * _v.val()).toFixed(2)
										: parseFloat(totalPrices * _v.val())
												.toFixed(2)));
				$(this).closest("tr").find("td").eq(4).find("em.jf").html(
						(_t.hasClass("prev") ? parseInt(totalPoints * _v.val())
								: parseInt(totalPoints * _v.val())));
				calcProdSubTotal();
				calcProdTotal();
				var cartID = $(this).parent().attr("data");
				var number = $("#cart_" + cartID).val();
				jQuery.ajax({
					url : "updateShoppingCart.do",
					data : {
						id : cartID,
						number : number,
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
					},
					error : function() {
						alert("系统出错！");
					}
				});
			}

		});

$("input[name='elect']").click(function() {

	$("input[name='elect']").each(function() {
		var ck = $(this).prop("checked");
		if (ck == true) {
			$("#sel_all").attr("checked", true);
		} else {
			$("#sel_all").removeAttr("checked");
			return false;
		}
	})
	calcProdTotal();
	checkNull()
});
function checkNull() {
	var objJf = $(".shoppingcart_list_footer .jf");
	var objPrice = $(".shoppingcart_list_footer #product-subtotal");
	var objExtJf = $(".shoppingcart_list_footer .extJf");
	var objExtPrice = $(".shoppingcart_list_footer .extPrice");
	if (objJf.html() == 0) {
		$(".shoppingcart_list_footer .extJf").hide();
	} else {
		$(".shoppingcart_list_footer .extJf").show();
	}
	if (objPrice.html() == 0) {
		$(".shoppingcart_list_footer .extPrice").hide();
	} else {
		$(".shoppingcart_list_footer .extPrice").show();
	}
	if ((objJf.html() == 0) && (objPrice.html() == 0)) {
		$(".shoppingcart_list_footer .puls").hide();
		objExtPrice.show();
		objExtJf.hide();
	} else if ((objJf.html() != 0) && (objPrice.html() != 0)) {
		$(".shoppingcart_list_footer .puls").show();
		objExtPrice.show();
		objExtJf.show();
	} else {
		$(".shoppingcart_list_footer .puls").hide();
	}

}
function calcProdTotal() {
	var num = 0, tolPrice = 0.00, tolJf = 0, allcountJf = 0, allcountPrice = 0.00, tariffAmount = 0.00;
	$("input[name='elect']:checked").each(
			function() {
				var n = parseInt($(this).closest("tr").find("td").eq(3).find(
						"input[name='num']").val());
				num = num + n;
				var checkPrice = $(this).closest("tr").find("td").eq(4).find(
						"em.price").length;
				var checkJf = $(this).closest("tr").find("td").eq(4).find(
						"em.jf").length;
				var extendedPrice = parseFloat($(this).closest("tr").find("td")
						.eq(4).find("em.price").html());
				var extendedJf = parseInt($(this).closest("tr").find("td")
						.eq(4).find("em.jf").html());

				if (checkPrice == 0) {
					extendedPrice = 0;
				}

				if (checkJf == 0) {
					extendedJf = 0;
				}

				var w = parseFloat(checkPrice);
				tolPrice = tolPrice + n * w;
				var s = parseInt(checkJf);
				tolJf = tolJf + n * s;

				allcountPrice = allcountPrice + extendedPrice;
				allcountJf = allcountJf + extendedJf;
			});
	$(".shoppingcart_list_footer .num_c").html(num);
	$(".shoppingcart_list_footer #product-subtotal").html(tolPrice.toFixed(2));
	$(".shoppingcart_list_footer .jf").html(allcountJf);
	$("#product-subtotal").html(allcountPrice.toFixed(2));
}

function calcProdSubTotal() {
	var prodSubTotal = 0;
	$(".totalize").each(function() {
		var valString = parseFloat($(this).html()) || 0;
		prodSubTotal += parseFloat(valString);
	});
};
// checkbox
$("#sel_all").click(function() {
	$('input[name="elect"]').attr("checked", this.checked);
	calcProdTotal();
	checkNull()
});
$(".shoppingcart_list").on(
		"click",
		".sel_area",
		function() {
			$(this).closest(".shoppingcart_list").find('input[name="elect"]')
					.attr("checked", this.checked);
		});
$("input[name='num']").change(
		function() {
			var iptVal = $(this).val();
			var nStock = $(this).closest("div").find(".stock").val();
			var unitPrice = $(this).closest("tr").find("td").eq(2).find(
					"em.price").html();
			var unitPoints = $(this).closest("tr").find("td").eq(2).find(
					"em.jf").html();
			var inVAL = $(this).closest("tr").find("td").eq(3).find(
					"input[name='num']").val();
			var totalPrices = parseFloat(unitPrice);
			var totalPoints = parseInt(unitPoints);
			var cartID = $(this).parent().attr("data");
			if (parseInt(iptVal) <= 1) {
				$(this).val(1);
				$(this).closest("tr").find("td").eq(4).find("em.price").html(
						parseFloat(totalPrices * 1).toFixed(2));
				$(this).closest("tr").find("td").eq(4).find("em.jf").html(
						parseInt(totalPoints * 1));
				jQuery.ajax({
					url : "updateShoppingCart.do",
					data : {
						id : cartID,
						number : 1,
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
					},
					error : function() {
						alert("系统出错！");
					}
				});
			}
			if (parseInt(iptVal) > nStock) {
				$(this).val(nStock);
				$(this).closest("tr").find("td").eq(4).find("em.price").html(
						parseFloat(totalPrices * nStock).toFixed(2));
				$(this).closest("tr").find("td").eq(4).find("em.jf").html(
						parseInt(totalPoints * nStock));
				jQuery.ajax({
					url : "updateShoppingCart.do",
					data : {
						id : cartID,
						number : nStock,
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
					},
					error : function() {
						alert("系统出错！");
					}
				});
			}
			if (parseInt(iptVal) > 1) {
				n = $(this).val();
				var intNum = parseInt(n);
				$(this).val(intNum);
				$(this).closest("tr").find("td").eq(4).find("em.price").html(
						parseFloat(totalPrices * intNum).toFixed(2));
				$(this).closest("tr").find("td").eq(4).find("em.jf").html(
						parseInt(totalPoints * intNum));
				jQuery.ajax({
					url : "updateShoppingCart.do",
					data : {
						id : cartID,
						number : intNum,
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
					},
					error : function() {
						alert("系统出错！");
					}
				});
			}
			if (isNaN(iptVal) || iptVal == ""
					|| iptVal.replace(/\s+/g, "") == "") {
				$(this).val(1);
				jQuery.ajax({
					url : "updateShoppingCart.do",
					data : {
						id : cartID,
						number : 1,
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
					},
					error : function() {
						alert("系统出错！");
					}
				});
			}

		})

/** 收藏商品* */
function addFavorite(itemID, productID) {
	jQuery.ajax({
		url : "addFavorite.do",
		data : {
			"itemID" : itemID,
			"productID" : productID,
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("收藏成功！");
				// location.reload();
			} else if (data.code == 2) {
				window.location.href = "/mall/member/toLogin.htm";
			} else if (data.code == 3) {
				alert("您已经收藏过该商品！");
			} else {
				alert("收藏失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/** 删除购物车商品* */
function delShoppingCart(id) {
	jQuery.ajax({
		url : "delShoppingCart.do",
		data : {
			id : id,
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("删除成功！");
				location.reload();
			} else if (data.code == 2) {
				window.location.href = "/mall/member/toLogin.htm";
			} else {
				alert("删除失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/** 清空购物车* */
function delShoppingCartAll() {
	jQuery.ajax({
		url : "delShoppingCartAll.do",
		data : {},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("清空成功！");
				location.reload();
			} else if (data.code == 2) {
				window.location.href = "/mall/member/toLogin.htm";
			} else {
				alert("清空失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/** 去结算* */
function shoppingCart2() {
	var valArr = new Array;
	$("#productList .ck:checked").each(function(i) {
		valArr[i] = $(this).val();
	});
	var vals = valArr.join(',');
	if (vals != "") {
		window.location.href = "shoppingCart2.htm?cartID=" + vals;
	} else {
		alert("请选择商品");
	}
}

/** shoppingcart_2.vm js* */
$(function() {
	$(".man_addr").on("click", "li.addr input", function() {
		$(this).closest(".man_addr").find("li.addr").removeClass("on");
		$(this).closest("li.addr").addClass("on");
	})

	/*
	 * $(".man_addr").on( "click", "li.addr .default", function() {
	 * $(this).addClass("get").html("默认地址").closest("li").siblings()
	 * .find(".default").removeClass("get").html("设为默认地址"); })
	 */
	$(".man_addr li").hover(function() {
		$(this).addClass("cur")
	}, function() {
		$(this).removeClass("cur")
	})

	$(".shoppingcart_list").on("click", ".sel_area", function() {
		$('input[name="elect"]').attr("checked", this.checked);
	});
	
	$(".closeLink").click(function(){
		$(".insufficient").hide();
	})
})
function editAddress(){
	$(".noAddr").show();
	var _this = arguments[0];
	var name = $(_this).parents(".mt10").find("strong").text();
	var isdefault = $(_this).parents(".mt10").find(":hidden").eq(0).val();
	var place = $(_this).parents(".mt10").find(":hidden").eq(1).val();
	var mobile = $(_this).parents(".mt10").find(":hidden").eq(2).val();
	var telephone = $(_this).parents(".mt10").find(":hidden").eq(3).val();
	var addrId = arguments[1];
	var provinceId = arguments[2];
	var cityId = arguments[3];
	var districtId = arguments[4];

	if ($.trim(isdefault) == "1") {
		$(".isdefault").attr("checked", true).val("1");
	}else{
		$(".isdefault").attr("checked", false).val("0");
	}
	
	$("#addressForm input[name='name']").val(name);
	$("select[name='provinceId']").val(provinceId);
	changeProvinceID(provinceId);
	$("select[name='cityId']").val(cityId);
	changeCity(cityId);
	$("select[name='districtId']").val(districtId);
	$("#addressForm input[name='addressID']").val(addrId);
	$("#addressForm input[name='address']").val(place);
	$("#addressForm input[name='mobile']").val(mobile);
	$("#addressForm input[name='telephone']").val(telephone);
	
	
}
function addressInit(){

	$("#addressForm input[name='name']").val('');
	$("#addressForm input[name='addressID']").val('');
	$("#addressForm input[name='address']").val('');
	$("#addressForm input[name='mobile']").val('');
	$("#addressForm input[name='telephone']").val('');
	$(".isdefault").attr("checked", false).val("0");
}


function delAddress(id) {
	$.ajax({
		url : "deleteAddress.do",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				location.reload();
			} else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("删除失败");
		}
	});
}

/** 设置默认地址* */
function setDefaultAddress(addressId, customerId, type) {
	var data = {
		addressId : addressId,
		customerId : customerId,
		type : type
	}
	var url = "updateDefaultAddress.do";
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				location.reload();
			} else {
				alert("设置失败");
			}
		},
		error : function() {
			alert("设置失败");
		}
	});
}

function changeProvinceID(value) {
	if (value != 0) {
		$.ajax({
			url : "getCityByParentID.do",
			type : 'POST',
			async: false,
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
			url : "getCityByParentID.do",
			type : 'POST',
			async: false,
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

function changeProvinceIDs(value) {
	if (value != 0) {
		$.ajax({
			url : "getCityByParentID.do",
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
			url : "getCityByParentID.do",
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

function shoppingCart3() {
	var addressID = $(".mt15 :radio:checked").val();
	var paymentType = $("input[name='paymentType']:checked").val();
	var memo = $("#memo").val();
	var orderAmount = $("#orderAmount").val();
	var scoreAll = $("#scoreAll").val();
	var itemCount = $("#itemCount").val();
	var itemID = $("#itemID").val();
	var itemType = $("#itemType").val();
	var orderType = $("#orderType").val();
	var objID = $("#objID").val();
	var supplierID = $("#supplierID").val();
	var generateType = $("#generateType").val();
	var orderID = $("#orderID").val();
	var allFreight = $("#allFreight").val();
	var supplierFreightStr = $("#supplierFreightStr").val();	
	var invoiceType = 0;
	var invoiceTitle = "";
	if($("input[name='needInvoice']:checked").val() == "true"){
		invoiceType = $("input[name='invoiceType']:checked").val();
	}
	if(invoiceType != 0 && $("#invoice").val() == ''){
		alert('请填写发票抬头');
		return
	}else if(invoiceType != 0){
		invoiceTitle = $("#invoice").val();
	}
	var paymentTypeva = $(".paywayList input[name='paymentType']:checked").val();
		
		if(paymentTypeva != 3){
			if (typeof addressID === 'undefined') {
					if($(".mt15 :radio").length == 0){
						alert("请先添加收货地址再来购物");
					}else{
						alert("请选择收货地址");
						
					}
					return false;
			}
		}
	

	var valArr = new Array;
	$("table .cartID").each(function(i) {
		valArr[i] = $(this).attr("data-cartId");
	});
	var vals = valArr.join(',');
	
	//获取针对个卖家的留言
	var memoArray = new Array();
	$("table .memoText").each(function(i) {
		memoArray[i] = $(this).attr("data-supplierId")+'~--~'+$(this).val();
	});
	var memos = memoArray.join(',');
	
	var url = "saveOrder.do?addressID=" + addressID + "&paymentType="
			+ paymentType + "&memo=" + memo
			+ "&orderAmount=" + orderAmount + "" + "&scoreAll=" + scoreAll
			+ "&itemCount=" + itemCount + "&orderType=" + orderType
			+"&invoiceType="+invoiceType+"&invoiceTitle="+invoiceTitle
			+"&generateType="+generateType+"&tempOrderID="+orderID
			+ "&itemID=" + itemID + "&itemType="
			+ itemType+"&supplierID="+supplierID
			+ "&cartIDs=" + vals
			+ "&memoIDs=" + memos
			+ "&allFreight=" + allFreight
			+ "&supplierFreightStr=" + supplierFreightStr;			
	/**if (vals != "") {
		window.location.href = url + "&cartIDs=" + vals;
	}
	if (itemID != "" && itemID != undefined) {
		window.location.href = url + "&itemID=" + itemID + "&itemType="
				+ itemType+"&supplierID="+supplierID;
	}**/
	location.href = url;
	
}
