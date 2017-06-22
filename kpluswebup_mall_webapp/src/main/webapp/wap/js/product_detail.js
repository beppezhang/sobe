/*添加购物车*/
function addCat() {
	var customerID = jQuery("#customerID").val();
	var itemID = jQuery("#itemID").val();
	var stock = parseInt(jQuery("#stock").text());
	var productID = jQuery("#productID").val();
	var itemCount = jQuery("#itemCount").val();
	var type = jQuery("input[name='type']:checked").val();
	if (stock == "" || stock <= 0 ) {
		alert("库存不足！");
		return;
	}
	if (customerID == "") {
		//alert("请登录！");
		window.location.href="/mall/member/toLogin.htm";
		return;
	} else if (type == undefined) {
		checkSel();
		return;
	} else {
		//alert("加入购物车成功");
		jQuery.ajax({
			url : "ajaxAddCat.do",
			data : {
				"itemCount" : itemCount,
				"itemID" : itemID,
				"productID" : productID,
				"customerID" : customerID,
				"type" : type
			},
			type : "POST",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					openprot(0);
				} else {
					alert("添加失败！");
				}
			},
			error : function() {
				alert("系统出错！");
			}
		});
	}
}

/* 立即购买 */
function gobuy() {
	var stock = jQuery("#stock").text();
	var type = jQuery(".pricebox .on").children("input").val();
	var itemID = jQuery("#itemID").val();
	var itemCount = jQuery("#itemCount").val();
	var orderType = jQuery("#orderType").val();
	var productID=jQuery("#productID").val();
	if (stock == "" || stock == "0") {
		alert("库存不足！");
		return;
	}
	if (type == undefined) {
		checkSel();
		return;
	}
	window.location.href = "/goBuy.htm?cartID=" + itemID + "&number="
			+ itemCount + "&type=" + type + "&orderType=" + orderType+"&productID="+productID;
}

/* 判断用户登录和是否购买该商品 */
function openReview() {
	var customerId = jQuery("#customerID").val();
	var productID = jQuery("#productID").val();
	if (customerId == "") {
		//alert("请登录！");
		window.location.href="/mall/member/toLogin.htm";
		return;
	}
	/* 查询该用户是否买了该商品 */
	jQuery.ajax({
		url : "ajaxisBuy.do",
		data : {
			"productID" : productID,
			"customerID" : customerId
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 4) {
				alert("你没有购买该商品不能评价！");
				return;
			} else {
				openprot(1);
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

/* 提交评论 */
function submitReview() {
	var customerId = jQuery("#customerID").val();
	var itemId = jQuery("#itemID").val();
	var productId = jQuery("#productID").val();
	var content = jQuery("#review").val();
	var score = jQuery(".starsbar .on").length;
	if (score == undefined) {
		alert("请选择评分！");
		return;
	}
	jQuery.ajax({
		url : "ajaxReview.do",
		data : {
			"content" : content,
			"itemID" : itemId,
			"productID" : productId,
			"customerID" : customerId,
			"score" : score
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("评价成功！");
				location.reload();
			} else {
				alert("评价失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

function openConsult() {
	var customerID = jQuery("#customerID").val();
	if (customerID == "") {
		window.location.href = "/mall/member/toLogin.htm";
	} else {
		openprot(2);
	}
}

/* 咨询 */
function zixun() {
	var content = jQuery("#zixun").val();
	var itemID = jQuery("#itemID").val();
	var productID = jQuery("#productID").val();
	if (content == "") {
		alert("咨询内容不能为空！");
		return;
	}
	jQuery.ajax({
		url : "ajaxZixun.do",
		data : {
			"content" : content,
			"itemID" : itemID,
			"productID" : productID
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				alert("咨询成功！");
				location.reload();
			} else if (data.code == 2) {
				window.location.href = "/mall/member/toLogin.htm";
			} else {
				alert("咨询失败！");
			}
		},
		error : function() {
			alert("系统出错！");
		}
	});
}

jQuery(document)
		.ready(
				function() {
					var getTop=jQuery("#tabbar").offset().top;
					jQuery(window).scroll(function(){  
						var t = document.body.scrollTop||document.documentElement.scrollTop;  
			             if(t>getTop){  
			            	 jQuery("#tabbar").attr("class","pos_f");
			             }else{  
			            	 jQuery("#tabbar").removeAttr("class");  
			             }  
			        }); 
					jQuery(".pricebox").find(".check_box").click(
							function() {
								jQuery(this).addClass("on").closest("p")
										.siblings().find(".check_box")
										.removeClass("on");
								if (jQuery(".attbox").hasClass("on")) {
									checkSel()
								}
							})
					jQuery(function() {
						jQuery(".sel span a").click(
								function(e) {
									e.preventDefault();
									jQuery(this).closest("span")
											.addClass('cur').siblings()
											.removeClass();
									var value1 = -1;
									var value2 = -1;
									var leng = jQuery(".sel span.cur").length;
									/*if (leng < 2) {
										checkSel();
										return;
									}*/
									var cur = 0;
									jQuery(".sel span.cur").each(
											function(i) {
												cur = parseInt(cur)+1;
												if (i == 0) {
													value1 = jQuery(this)
															.children("input")
															.val();
												} else {
													value2 = jQuery(this)
															.children("input")
															.val();
												}
											})
											if (parseInt(cur) < parseInt(leng)){
												checkSel();
												return
											}
									jQuery("#value1").val(value1);
									jQuery("#value2").val(value2);
									jQuery(".detailForm").submit();
								})
						jQuery("#tabbar .tab span").click(function(e) {
							e.preventDefault();
							jQuery(document).scrollTop(getTop);
						})
						jQuery(".book")
								.click(
										function(e) {
											e.preventDefault();
											if (jQuery(this).hasClass("cur")) {
												jQuery(this).text("已收藏");
											} else {
												var itemID = jQuery("#itemID")
														.val();
												var productID = jQuery(
														"#productID").val();
												var customerID = jQuery(
														"#customerID").val();
												if (customerID == "") {
													//alert("请登录！");
													window.location.href="/mall/member/toLogin.htm";
													return;
												}
												jQuery
														.ajax({
															url : "ajaxFavorite.do",
															data : {
																"itemID" : itemID,
																"productID" : productID,
																"customerID" : customerID
															},
															type : "POST",
															dataType : "json",
															success : function(
																	data) {
																if (data.code == 0) {
																	jQuery(
																			"#favorite")
																			.addClass(
																					"cur");
																	jQuery(
																			"#favorite")
																			.text(
																					"已收藏");
																	var fcount = jQuery(
																			".fcount")
																			.text();
																	count = parseInt(fcount) + 1;
																	jQuery(
																			".fcount")
																			.text(
																					count);
																} else if (data.code == 2) {
																	window.location.href = "/mall/member/toLogin.htm";
																} else {
																	alert("收藏失败！");
																}
															},
															error : function() {
																alert("出现异常！");
															}
														});
											}
										})
						// tabs
						jQuery(function() {
							jQuery(".distab span").click(
									function(e) {
										e.preventDefault();
										jQuery(this).addClass('cur').siblings()
												.removeClass();
										jQuery(".discon div.dis_list").eq(
												jQuery(".distab span").index(
														this)).addClass("cur")
												.siblings().removeClass("cur");
									})
						})

						// counter
						var nStock = $("#stock").text();
						$(".number_control").on(
								"click",
								"a",
								function() {
									var _t = $(this);

									var _v = _t.parent("span").find("input");
									var _r = _t.hasClass("prev") ? $(this) : $(
											this).next();
									if (_t.hasClass("prev") && _v.val() <= 1) {
										return;
									}
									if (_t.hasClass("next")
											&& _v.val() == nStock) {
										return;
									}
									_v.val(_t.hasClass("prev") ? parseInt(_v
											.val()) - 1
											: parseInt(_v.val()) + 1);
								});
						$("input[name='num']")
								.change(
										function() {
											var iptVal = $(this).val();
											if (parseInt(iptVal) < 1) {
												$(this).val(1);
											}
											if (parseInt(iptVal) > 1) {
												var intNum = parseInt($(this)
														.val());
												$(this).val(intNum);
											}
											if (parseInt(iptVal) > nStock) {
												$(this).val(nStock);
											}
											if (isNaN(iptVal)
													|| iptVal == ""
													|| iptVal.replace(/\s+/g,
															"") == "") {
												$(this).val(1);
											}

										})

					})

					// 图片滚动
					var count = jQuery("#imageMenu li").length; /* 显示 6 个 li标签内容 */
					var interval = jQuery("#imageMenu li:first").width();
					var curIndex = 0;
					if (count <= 4) {
						jQuery('.scrollbutton').hide();
					}
					jQuery('.scrollbutton').click(function() {
						if (jQuery(this).hasClass('disabled'))
							return false;

						if (jQuery(this).hasClass('smallImgUp'))
							--curIndex;
						else
							++curIndex;

						jQuery('.scrollbutton').removeClass('disabled');
						if (curIndex == 0)
							jQuery('.smallImgUp').addClass('disabled');
						if (curIndex == count - 4)
							jQuery('.smallImgDown').addClass('disabled');

						jQuery("#imageMenu ul").stop(false, true).animate({
							"marginLeft" : -curIndex * interval + "px"
						}, 300);
					});

					// 解决 ie6 select框 问题
					jQuery.fn.decorateIframe = function(options) {
						if (jQuery.browser.msie && jQuery.browser.version < 7) {
							var opts = jQuery.extend({},
									jQuery.fn.decorateIframe.defaults, options);
							jQuery(this)
									.each(
											function() {
												var jQuerymyThis = jQuery(this);
												// 创建一个IFRAME
												var divIframe = jQuery("<iframe />");
												divIframe.attr("id",
														opts.iframeId);
												divIframe.css("position",
														"absolute");
												divIframe
														.css("display", "none");
												divIframe.css("display",
														"block");
												divIframe.css("z-index",
														opts.iframeZIndex);
												divIframe.css("border");
												divIframe.css("top", "0");
												divIframe.css("left", "0");
												if (opts.width == 0) {
													divIframe
															.css(
																	"width",
																	jQuerymyThis
																			.width()
																			+ parseInt(jQuerymyThis
																					.css("padding"))
																			* 2
																			+ "px");
												}
												if (opts.height == 0) {
													divIframe
															.css(
																	"height",
																	jQuerymyThis
																			.height()
																			+ parseInt(jQuerymyThis
																					.css("padding"))
																			* 2
																			+ "px");
												}
												divIframe.css("filter",
														"mask(color=#fff)");
												jQuerymyThis.append(divIframe);
											});
						}
					}
					jQuery.fn.decorateIframe.defaults = {
						iframeId : "decorateIframe1",
						iframeZIndex : -1,
						width : 0,
						height : 0
					}
					// 放大镜视窗
					jQuery("#bigView").decorateIframe();

					// 点击到中图
					var midChangeHandler = null;

					jQuery("#imageMenu li img").bind(
							"click",
							function() {
								if (jQuery(this).attr("id") != "onlickImg") {
									midChange(jQuery(this).attr("src").replace(
											"small", "mid"));
									jQuery("#imageMenu li").removeAttr("id");
									jQuery(this).parent().attr("id",
											"onlickImg");
								}
							}).bind(
							"mouseover",
							function() {
								if (jQuery(this).attr("id") != "onlickImg") {
									window.clearTimeout(midChangeHandler);
									midChange(jQuery(this).attr("src").replace(
											"small", "mid"));
									jQuery(this).css({
										"border" : "1px solid #ed145b"
									});
								}
							}).bind(
							"mouseout",
							function() {
								if (jQuery(this).attr("id") != "onlickImg") {
									jQuery(this).removeAttr("style");
									midChangeHandler = window.setTimeout(
											function() {
												midChange(jQuery(
														"#onlickImg img").attr(
														"src").replace("small",
														"mid"));
											}, 1000);
								}
							});

					function midChange(src) {
						jQuery("#midimg").attr("src", src).load(function() {
							changeViewImg();
						});
					}

					// 大视窗看图
					function mouseover(e) {
						if (jQuery("#winSelector").css("display") == "none") {
							jQuery("#winSelector,#bigView").show();
						}

						jQuery("#winSelector").css(fixedPosition(e));
						e.stopPropagation();
					}

					function mouseOut(e) {
						if (jQuery("#winSelector").css("display") != "none") {
							jQuery("#winSelector,#bigView").hide();
						}
						e.stopPropagation();
					}

					jQuery("#midimg").mouseover(mouseover); // 中图事件
					jQuery("#midimg,#winSelector").mousemove(mouseover)
							.mouseout(mouseOut); // 选择器事件

					var jQuerydivWidth = jQuery("#winSelector").width(); // 选择器宽度
					var jQuerydivHeight = jQuery("#winSelector").height(); // 选择器高度
					var jQueryimgWidth = jQuery("#midimg").width(); // 中图宽度
					var jQueryimgHeight = jQuery("#midimg").height(); // 中图高度
					var jQueryviewImgWidth = jQueryviewImgHeight = jQueryheight = null; // IE加载后才能得到
					// 大图宽度 大图高度
					// 大图视窗高度

					function changeViewImg() {
						jQuery("#bigView img").attr(
								"src",
								jQuery("#midimg").attr("src").replace("mid",
										"big"));
					}

					changeViewImg();

					jQuery("#bigView").scrollLeft(0).scrollTop(0);
					function fixedPosition(e) {
						if (e == null) {
							return;
						}
						var jQueryimgLeft = jQuery("#midimg").offset().left; // 中图左边距
						var jQueryimgTop = jQuery("#midimg").offset().top; // 中图上边距
						X = e.pageX - jQueryimgLeft - jQuerydivWidth / 2; // selector顶点坐标
						// X
						Y = e.pageY - jQueryimgTop - jQuerydivHeight / 2; // selector顶点坐标
						// Y
						X = X < 0 ? 0 : X;
						Y = Y < 0 ? 0 : Y;
						X = X + jQuerydivWidth > jQueryimgWidth ? jQueryimgWidth
								- jQuerydivWidth
								: X;
						Y = Y + jQuerydivHeight > jQueryimgHeight ? jQueryimgHeight
								- jQuerydivHeight
								: Y;

						if (jQueryviewImgWidth == null) {
							jQueryviewImgWidth = jQuery("#bigView img")
									.outerWidth();
							jQueryviewImgHeight = jQuery("#bigView img")
									.height();
							if (jQueryviewImgWidth < 200
									|| jQueryviewImgHeight < 200) {
								jQueryviewImgWidth = jQueryviewImgHeight = 800;
							}
							jQueryheight = jQuerydivHeight
									* jQueryviewImgHeight / jQueryimgHeight;
							jQuery("#bigView").width(
									jQuerydivWidth * jQueryviewImgWidth
											/ jQueryimgWidth);
							jQuery("#bigView").height(jQueryheight);
						}

						var scrollX = X * jQueryviewImgWidth / jQueryimgWidth;
						var scrollY = Y * jQueryviewImgHeight / jQueryimgHeight;
						jQuery("#bigView img").css({
							"left" : scrollX * -1,
							"top" : scrollY * -1
						});
						jQuery("#bigView").css(
								{
									"top" : 325,
									"left" : jQuery(".preview").offset().left
											+ jQuery(".preview").width() + 15
								});

						return {
							left : X,
							top : Y
						};
					}
					// 评分
					var oStar = jQuery(".starsbar");
					var aLi = jQuery(".starsbar span");
					// var oUl = oStar.getElementsByTagName("ul")[0];
					var oSpan = jQuery(".s_result");
					// var oP = oStar.getElementsByTagName("p")[0];
					var i = iScore = iStar = 0;
					var aMsg = [ "非常不满", "不满意", "一般", "满意", "非常满意" ]

					for (i = 1; i <= aLi.length; i++) {
						aLi[i - 1].index = i;

						// 鼠标移过显示分数
						aLi[i - 1].onmouseover = function() {
							fnPoint(this.index);
							/*
							 * //浮动层显示 oP.style.display = "block"; //计算浮动层位置
							 * oP.style.left = oUl.offsetLeft + this.index *
							 * this.offsetWidth - 104 + "px"; //匹配浮动层文字内容
							 * oP.innerHTML = "<em><b>" + this.index + "</b>
							 * 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" +
							 * aMsg[this.index - 1].match(/\|(.+)/)[1]
							 */
						};

						// 鼠标离开后恢复上次评分
						aLi[i - 1].onmouseout = function() {
							fnPoint();
							// 关闭浮动层
							// oP.style.display = "none"
						};

						// 点击后进行评分处理
						aLi[i - 1].onclick = function() {
							iStar = this.index;
							// oP.style.display = "none";
							oSpan.innerHTML = (this.index) + " 分 "
						}
					}

					// 评分处理
					function fnPoint(iArg) {
						// 分数赋值
						iScore = iArg || iStar;
						for (i = 0; i < aLi.length; i++)
							aLi[i].className = i < iScore ? "on" : "";
					}
				});
// 弹出对话框
var isIE = (document.all) ? true : false;
var isIE6 = isIE && ([ /MSIE (\d)\.0/i.exec(navigator.userAgent) ][0][1] == 6);
var cwidth = jQuery(window).width();
var cheight = jQuery(window).height();
var dheight = jQuery(document).height();
var i;
function openprot(i) {
	var idname = document.getElementById("agebox" + i);
	jQuery("body").append("<div id='wrapbg' onClick='closeprot()'></div>");

	jQuery("#wrapbg").width(cwidth);
	jQuery("#wrapbg").height(dheight);

	jQuery(idname).fadeIn(300);
	idname.style.left = (cwidth - idname.offsetWidth) / 2 + "px";
	idname.style.top = (cheight - idname.offsetHeight) / 2 + "px";
}

function closeprot() {
	jQuery("div[id='wrapbg']").remove();
	jQuery(".popbox").fadeOut(200);
}
function checkSel() {
	var nPricebox = jQuery(".pricebox .check_box.on").length;
	var nSpan = jQuery(".attbox dl .sel span.cur").length;
	if ((nPricebox == 0) || (nSpan == 0) || (nSpan == 1)) {
		jQuery(".attbox").addClass("on");
	} else if ((nPricebox != 0) || (nSpan != 0) || (nSpan != 1)) {
		jQuery(".attbox").removeClass("on");
	}
	var checkTop = $("#parameter").offset().top;
	$(document).scrollTop(checkTop);
}