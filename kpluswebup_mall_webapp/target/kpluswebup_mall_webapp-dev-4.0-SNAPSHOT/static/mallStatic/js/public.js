// JavaScript Document, designed by jsann, for ...

(function($){
	$(function(){
		if($("div.index_main").attr("data-placeholder") && $("div.index_main").attr("data-placeholder") == "true"){
			$(".navbar").attr("data-placeholder", "true");
		}
		
		$(window).resize(function(){
			$(".nav_right").width((($("body").width() - 1200) / 2 > 0 ? ($("body").width() - 1200) / 2 : 0) + 133);
		});
		$(window).resize();
		
		if($(".help_menu_list").innerHeight() > $(".help_content_wrap").innerHeight()){
			$(".help_content_wrap").height($(".help_menu_list").height());
		}else{
			$(".help_menu_list").height($(".help_content_wrap").height());
		}
		
		if($(".data_placeholder").length || ($(".navbar").attr("data-placeholder") && $(".navbar").attr("data-placeholder") == "true")){
			$(".data_placeholder").height($(".navbar").attr("data-placeholder", "true").find(".navbar_content").show().outerHeight());
			$(".navbar .navbar_but").addClass("hover");
		}
		
		var timeout = 0;
		$(".navbar").mouseleave(function(){
			if(!!$(this).attr("data-placeholder") && $(this).attr("data-placeholder") == "true"){
				return;
			}
			timeout = setTimeout(function(){
				$(".navbar_content").hide();
			}, 500);
		});
		$(".navbar, .navbar *, .navbar_content, .navbar_content *").mouseenter(function(){
			clearTimeout(timeout)
			$(".navbar_content").show();
		});
		
		var $itemsLi = $(".navbar .navchild li");
	    var $categoryDetail = $(".navbar_contentside");
	    var $categoryDetailDiv = $(".navbar_contentside ul");
	    var index = null;
	    $itemsLi.hover(function () {
	        index = $(this).index();
	        $(this).addClass("hover");
	        $categoryDetailDiv.eq(index).show();
			$categoryDetail.css({top : function(){
				var _height = 0;
				$(this).parent().find("li").each(function(i, el){
					if(i == index){
						return false;
					}
					_height += $(el).outerHeight();
				});
				return _height + 3;
			}});
	    }, function () {
	        $(this).removeClass("hover");
	        $categoryDetailDiv.hide();
	        offset = null;
	        index = null;
	    })
	    $categoryDetailDiv.hover(function () {
	        index = $(this).index();
	        $itemsLi.eq(index).addClass('hover');
	        $categoryDetailDiv.eq(index).show();
	    }, function () {
	        $itemsLi.eq(index).removeClass('hover');
	        $categoryDetailDiv.hide();
	    });
		
		$(".drop").mouseenter(function(){
			$(this).addClass("dropdown");
		}).mouseleave(function(){
			$(this).removeClass("dropdown");
		});
		
		$(".search .type").click(function(){
			$(this).find("ul").toggle();
		});
		$(".search .type ul").on("click", "li", function(){
			$(this).parents(".type").find("em").text($(this).text());
			$(this).parents(".type").find("input").attr("value",$(this).attr("value"));
		});
		$(document).click(function(event){
			if(!$(event.target).hasClass("type") && !$(event.target).parents(".type").length){
				$(".search .type ul").hide();
			}
			if(!$(event.target).hasClass("select_price") && !$(event.target).parents(".select_price").length){
				$(".select_price").find(".select_price_control").hide();
			}
		});
		
		$(".pagetotop").click(function(){
			$(this).parent().ScrollTo();
		});
		
		var _tabEvent = "click";
		$(".title, .tab").each(function(){
			if($(this).attr("data-tigger") && $(this).attr("data-tigger") == "enter"){
				_tabEvent = "mouseenter";
			}
			$(this).on(_tabEvent, "li", function(){
				if($(this).hasClass("active") || $(this).hasClass("except") || $(this).parents("ul").hasClass("not_tab")){
					return;
				}
				if(!$(this).attr("data-tab")){
					$(this).parents(".tab_handle").find(".tab_content").eq($(this).index()).show().addClass("alone_tab").siblings(".tab_content").hide().removeClass("alone_tab");
				}else{
					if($(this).attr("data-tab") == "showall"){
						$(this).parents(".tab_handle").find(".tab_content").show().removeClass("alone_tab").end().find("[data-allshow='false']").hide();
					}
				}
				if(!$(this).parents(".tab").hasClass("anchor_tab")){
					$(this).addClass("active").siblings("li").removeClass("active");
				}else{
					if($(this).ScrollTo){
						$(this).parents(".tab_handle").ScrollTo();
					}
				}
			});
		});
		
		$(".parduct_tree, .ex_channel_tree, .mod_question").on("click", "a.host", function(){
			$(this).parents("li").toggleClass("active");
		});
		
		$(".areaduct_list li, .exchange_top li").mouseenter(function(){
			$(this).addClass("hover").siblings().removeClass("hover");
		});
		
		$(".select_sort").on("click", "a", function(){
			if($(this).hasClass("up")){
				$(this).removeClass("up").addClass("down");
			}else if($(this).hasClass("down")){
				$(this).removeClass("down").addClass("up");
			}else{
				$(this).addClass("active").siblings("a").removeClass("active up down");
				if($(this).find("span").length){
					$(this).addClass("up");
				}
			}
		});
		
		$(".select_price").on("focus", "input", function(){
			$(this).parents(".select_price").find(".select_price_control").show();
		}).on("click", ".areasubmit", function(){
			$(this).parents(".select_price").find(".select_price_control").hide();
		});
		
		$(".number_control").on("click", "a", function(){
			var _t = $(this);
			var _v = _t.parent("div").find("input");
			var _r = _t.hasClass("number_prev") ? $(this) : $(this).next();
			if(_t.hasClass("number_prev") && _v.val() <= 1){
				return;
			}
			_v.val(_t.hasClass("number_prev") ? parseInt(_v.val()) - 1 : parseInt(_v.val()) + 1);
		});
		
		$(".address_list li").hover(function(){
			if(!$(this).hasClass("selected")){
				$(this).toggleClass("hover");
			}
		}).find(".address_isdefault").on("click", "a", function(){
			$(this).parents("li").removeClass("hover").addClass("selected").siblings().removeClass("selected");
		});
		
		$(".busin_side").on("click", "a.host", function(){
			$(this).parents("dl").toggleClass("active");
		});
		
		$(".add_newclass").click(function(){
			//<td><a href="javascript:;"></a></td>
			$('<tr class="haschild" data=""><td class="class"><a class="child_ext" href="javascript:;"></a><input class="text" type="text"> <a class="areabut">保存</a></td><td class="option"><a class="up_row" title="向上移动" href="javascript:;"></a><a class="down_row" title="向下移动" href="javascript:;"></a></td><td class="option"><a class="delete_row" title="删除" href="javascript:;"></a></td></tr><tr class="child not_child"><td class="class" colspan="5"><span class="child_mark"></span><a class="add_child" href="javascript:;">添加子分类</a></td></tr>').appendTo($(".busin_table"));
			reftable($(".busin_table"));
		});
		
		$("table").on("click", "a.first_row", function(){
			var tr = $(this).parents("tr"), trp;
			if(tr.hasClass("first") || tr.hasClass("disabled")){
				return;
			}else if(tr.hasClass("child")){
				trp = tr.prevAll(".first.child")[0];
				if(tr.hasClass("lastchild")){
					tr.prev().addClass("lastchild last");
					tr.removeClass("lastchild last").addClass("first");
				}
				if($(trp).hasClass("first")){
					tr.addClass("first");
					$(trp).removeClass("first");
				}
			}else{
				trp = tr.prevAll(".first:not(.child)")[0];
			}
			if(tr.hasClass("haschild")){
				tr.nextAll(".child").each(function(){
					tr = tr.add(this);
					if($(this).hasClass("not_child")){
						return false;
					}
				});
			}
			$(trp).before(tr);
			reftable($(this).parents("table"));
		});
		
		$("table").on("click", "a.up_row", function(){
			var tr = $(this).parents("tr"), trp;
			if(tr.hasClass("first") || tr.hasClass("disabled")){
				return;
			}else if(tr.hasClass("child")){
				trp = tr.prev();
				if(tr.hasClass("lastchild")){
					tr.prev().addClass("lastchild last");
					tr.removeClass("lastchild last");
				}
				if(trp.hasClass("first")){
					tr.addClass("first");
					trp.removeClass("first");
				}
			}else{
				trp = tr.prevAll(":not(.child)")[0];
			}
			if(tr.hasClass("haschild")){
				tr.nextAll(".child").each(function(){
					tr = tr.add(this);
					if($(this).hasClass("not_child")){
						return false;
					}
				});
			}
			$(trp).before(tr);
			reftable($(this).parents("table"));
		});
		
		$("table").on("click", "a.down_row", function(){
			var tr = $(this).parents("tr"), trn;
			if(tr.hasClass("last") || tr.hasClass("disabled")){
				return;
			}else if(tr.hasClass("child")){
				trn = tr.next();
				if(tr.hasClass("first")){
					tr.next().addClass("first");
					tr.removeClass("first");
				}
				if(trn.hasClass("lastchild")){
					tr.addClass("lastchild last");
					trn.removeClass("lastchild last");
				}
			}else{
				trn = tr.nextAll(":not(.child)")[0];
				if($(trn).hasClass("haschild")){
					trn = $(trn).nextAll("tr.not_child")[0];
				}
			}
			if(tr.hasClass("haschild")){
				tr.nextAll(".child").each(function(){
					tr = tr.add(this);
					if($(this).hasClass("not_child")){
						return false;
					}
				});
			}
			$(trn).after(tr);
			reftable($(this).parents("table"));
		});
		
		$("table").on("click", "a.last_row", function(){
			var tr = $(this).parents("tr"), trn;
			if(tr.hasClass("last") || tr.hasClass("disabled")){
				return;
			}else if(tr.hasClass("child")){
				trn = tr.nextAll(".child.last")[0];
				if(tr.hasClass("first")){
					tr.next().addClass("first");
					tr.removeClass("first").addClass("last lastchild");
				}
				if($(trn).hasClass("lastchild")){
					tr.addClass("lastchild last");
					$(trn).removeClass("lastchild last");
				}
			}else{
				trn = tr.nextAll(".last:not(.child)")[0];
				if($(trn).hasClass("haschild")){
					trn = $(trn).nextAll("tr.not_child")[0];
				}
			}
			if(tr.hasClass("haschild")){
				tr.nextAll(".child").each(function(){
					tr = tr.add(this);
					if($(this).hasClass("not_child")){
						return false;
					}
				});
			}
			$(trn).after(tr);
			reftable($(this).parents("table"));
		});
		
		$("table .class_table").on("click", "a.delete_row", function(){
			var tr = $(this).parents("tr"), table = $(this).parents("table"), trn;
			if(tr.hasClass("disabled")){
				return;
			}
			if(confirm("此删除操作会删除当前项及其子项并且不可恢复，你确定要继续？")){
				if(tr.hasClass("haschild")){
					tr.nextAll(".child").each(function(){
						tr = tr.add(this);
						if($(this).hasClass("not_child")){
							return false;
						}
					});
				}
				tr.fadeOut("slow", function(){
					tr.remove();
					reftable(table);
				});
			}
		});
		
		$("table").on("click", "a.add_child", function(){
			//<td><a href="javascript:;"></a></td>
			var tr = $('<tr class="child last lastchild" data=""><td class="class"><span class="child_mark"></span><input class="text" id="classifychildname"  name="classifychildname" type="text" data="ddddd"><a class="areabut">保存</a></td><td class="option"><a class="up_row" title="向上移动" href="javascript:;"></a><a class="down_row" title="向下移动" href="javascript:;"></a></td><td class="option"><a class="delete_row" title="删除" href="javascript:;"></a></td></tr>').show(), trp = $(this).parents("tr").prev("tr");
			if(trp.hasClass("haschild")){
				tr.addClass("first");
			}else{
				trp.removeClass("last lastchild");
			}
			trp.after(tr);
		});
		
		$("table").on("click", "a.child_ext", function(){
			var tr = $(this).parents("tr");
			$(this).toggleClass("ext_active");
			if(tr.hasClass("haschild")){
				tr.nextAll(".child").each(function(){
					$(this).fadeToggle();
					if($(this).hasClass("not_child")){
						return false;
					}
				});
			}
		});
		
		function reftable(table){
			table.find("tr:not(.child)").removeClass("first").removeClass("last");
			table.find("tr:eq(1)").addClass("first");
			table.find("tr:not(.child):last").addClass("last");
		}
		
		$("table").on("click", "a.evaluate_but", function(){
			var pr = $(this).parents("tr"), ne = pr.next();
			if(ne.hasClass("evaluate_send")){
				$(this).toggleClass("evaluate_but_active");
				ne.toggle();
				if(pr.hasClass("last") || pr.hasClass("temp_last")){
					pr.toggleClass("last temp_last");
					ne.toggleClass("last");
				}
			}
		});
		
		if($(".slides").length){
			$(".slides").each(function(){
				$(this).slides({
					preload: true,
					next: 'next',
					prev: 'prev',
					preloadImage: 'images/base/loading.gif',
					play: 5000,
					pause: 2500,
					hoverPause: true,
					animationStart: function(current, el){
						$(el).find('.caption').animate({
							bottom:-48
						},100);
					},
					animationComplete: function(current, el){
						$(el).find('.caption').animate({
							bottom:0
						},200);
					},
					slidesLoaded: function(el) {
						$(el).find('.caption').animate({
							bottom:0
						},200);
					}
				});
			});
		}
		
		if($(".slides_handle").length){
			var t = ".slides_handle", isfull = $(t).hasClass("full_handle"), ww = 0, container = $(t).find(".slides_container"), img = container.find("a");
			if(isfull){
				ww = $(window).width();
				if(ww < 1000){
					ww = 1000;
				}
				container.css("width", ww);
				img.css("width", ww);
			}
			$(".slides_handle").slides({
				preload: true,
				next: 'next_handle',
				prev: 'prev_handle',
				preloadImage: 'images/base/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true
			});
			if($(t).hasClass("welter")){
				var timeout = 0;
				$(t).find(".next_handle, .next_handle").mouseenter(function(){
					t = this;
					$(t).click();
					timeout = setInterval(function(){
						$(t).click();
					}, 1000);
				}).mouseleave(function(){
					clearInterval(timeout);
				});
			}
			if(isfull){
				$(".slides_handle").find("a").load(function(){
					$(window).resize(function(){
						var imgheight;
						ww = $(window).width();
						if(ww < 1000){
							ww = 1000;
						}
						container.css("width", ww);
						img.css({"width" : ww, "height" : "auto"});
						imgheight = $(t).find("img:visible").height();
						img.css("height", imgheight);
						$(t).find(".slides_control").css("height", imgheight);
					});
					$(window).resize();
				});
			}
		}
	});
})(jQuery);