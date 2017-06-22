$(document).ready(function() {

//			var chexiLi=$('.chexi li a');
//			var oInfor=$('.right .auto_infor');
//			var aDl=$('.right .bottom .clearfix');
//			var autoL=$('#auto_all .panel_state');
			var rev_type=$('.right .auto_infor.type .review_type');
//
//			/*双向逐层点击，增加节点，变换内容*/
//			chexiLi.click(function(event) {
//			var oText=$(this).text();
//			var hasLi='<span class="right_arrow">-></span><li class="lianjie"><span title='+oText+'>'+oText+'</span><i class="icon-close"></i></li>';
//			var hasLi02='<li class="lianjie"><span title='+oText+'>'+oText+'</span><i class="icon-close"></i></li>';
//			var _index1=$(this).parents('.panel_state').index();
//			var _index2=$(this).parents('.panel_state').next().index();
//			
//			if(_index1<2){
//				/*oInfor.eq(0).append(hasLi);*/
//				$('.right .auto_infor:eq(0) ul').append(hasLi);
//
//			}else if(_index1>2&&_index1<5){
//				$(".auto_infor.parts").hide();
//				rev_type.show();
//				oInfor.eq(1).show();
//				$(".auto_infor.parts ul").append(hasLi);
//			}else if(_index1=2){
//				$(".auto_infor.parts").hide();
//				rev_type.show();
//				oInfor.eq(1).show();
//				$(".auto_infor.parts ul").append(hasLi02);
//			}
//		
//			autoL.eq(_index1).hide();
//			aDl.eq(_index1).hide();
//			autoL.eq(_index2).show();
//			aDl.eq(_index2).show();
//
//			});
//			/*点击叉时关闭当前元素以及后面所有的元素*/
//			$("#mainContainer .right .top .lianjie .icon-close").click(function(){
//				$(this).parent().css("display","none");
//				$(this).parent().nextAll().css("display","none");
//			});
		
			/*鼠标移上内容的时候，显示li里的内容*/
			$("#mainContainer .right .bottom dl li a").mouseenter(function(){
				$(this).attr("title",$(this).html());
			});
			/*点击查看车型信息显示车型详情页面*/
			$(rev_type).click(function(){
				$("#mainContainer .right .center").slideToggle(600).toggleClass('cur');
			});
			/*点击关闭车型详情*/
			$(".center .top1 .close").click(function(){
				$(this).parents(".center").slideUp(600).addClass('cur');
			});
			/*车型详情中table栏切换*/
			$(".bottom1 dd .nav span").click(function(){
				$(this).addClass('cur').siblings().removeClass('cur');
				$(".bottom1 dd .content div").eq($(this).index()).addClass('cur').siblings().removeClass('cur');
			});
		});