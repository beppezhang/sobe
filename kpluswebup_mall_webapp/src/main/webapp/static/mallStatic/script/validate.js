$(function() {
	jQuery("form").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
			},
			password1 : {
				required : true,
			},
			password2 : {
				required : true,
			},
			sortorder : {
				required : true,
				digits : true
			},
			sortOrder : {
				required : true,
				digits : true
			},
			activeTime : {
				required : true,
			},
			endTime : {
				required : true,
			},
			publishTime : {
				required : true,
			},
			categoryID : {
				required : true
			},
			author : {
				required : true
			},
			keyword : {
				required : true
			},
			telephone : {
				required : true,
				digits : true
			},
			mobile : {
				required : true,
				digits : true,
				maxlength : 11
			},
			address : {
				required : true
			},
			zip : {
				required : true,
				digits : true
			},
			sourceUrl : {
				required : true,
				url : true
			},
			contactPerson : {
				required : true
			},
			code : {
				required : true
			},
			unit : {
				required : true
			},
			gradeScore : {
				required : true,
				digits : true
			},
			mainID : {
				required : true
			},
			domain : {
				required : true,
				url : true
			},
			promotionCondition : {
				required : true,
				number : true
			},
			promotionValue : {
				required : true,
				number : true
			},
			salesPrice : {
				required : true,
				number : true
			},
			maxmumQty : {
				required : true,
				digits : true
			},
			limitCount : {
				required : true,
				digits : true
			},
			minimum : {
				required : true,
				digits : true
			},
			amount : {
				required : true,
				number : true
			},
			conditionAmount : {
				required : true,
				number : true
			},
			couponCount : {
				required : true,
				digits : true
			},
			score : {
				required : true,
				digits : true
			},
			fromDateStr : {
				required : true
			},
			endDateStr : {
				required : true
			},
			fromDate : {
				required : true
			},
			endDate : {
				required : true
			},
			useFromDate : {
				required : true
			},
			useEndDate : {
				required : true
			},
			birthday : {
				required : true
			},
			firstCondition : {
				required : true,
				digits : true
			},
			firstPrice : {
				required : true,
				number : true
			},
			addUnit : {
				required : true,
				digits : true
			},
			addPrice : {
				required : true,
				number : true
			},
			expressID : {
				required : true
			},
			formatID : {
				required : true
			},
			productID : {
				required : true
			},
			itemID : {
				required : true
			},
			couponDay : {
				required : true,
				digits : true
			},
			menuLink : {
				required : true,
				url : true
			},
			replyLink : {
				required : true,
				url : true
			},
			title : {
				required : true
			},
			content : {
				required : true
			},
			replyContent : {
				required : true
			},
			scorePrice : {
				required : true,
				number : true
			},
			salesScore : {
				required : true,
				digits : true
			},
			brandID : {
				required : true
			},
			userName : {
				required : true
			},
			passWord : {
				required : true
			},
			companyName : {
				required : true
			},
			linkMan : {
				required : true
			},
			openingBank : {
				required : true
			},
			bankAccount : {
				required : true,
				digits : true
			},
			cashDep : {
				required : true,
				number : true
			}
		},
		messages : {
			name : {
				required : "必填字段"
			},
			username : {
				required : "必填字段"
			},
			email : {
				required : "必填字段",
				email : "请输入正确格式的电子邮件"
			},
			password : {
				required : "必填字段"
			},
			password1 : {
				required : "必填字段"
			},
			password2 : {
				required : "必填字段"
			},
			sortorder : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			sortOrder : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			activeTime : {
				required : "必填字段",
			},
			endTime : {
				required : "必填字段",
			},
			publishTime : {
				required : "必填字段",
			},
			categoryID : {
				required : "必填字段"
			},
			author : {
				required : "必填字段"
			},
			keyword : {
				required : "必填字段"
			},
			telephone : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			mobile : {
				required : "必填字段",
				digits : "只能输入数字",
				maxlength : jQuery.validator.format("请输入正确的手机号")
			},
			address : {
				required : "必填字段"
			},
			zip : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			sourceUrl : {
				required : "必填字段",
				url : "请输入合法的网址"
			},
			contactPerson : {
				required : "必填字段"
			},
			code : {
				required : "必填字段"
			},
			unit : {
				required : "必填字段"
			},
			gradeScore : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			mainID : {
				required : "必填字段"
			},
			domain : {
				required : "必填字段",
				url : "请输入合法的网址"
			},
			promotionCondition : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			promotionValue : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			salesPrice : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			maxmumQty : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			limitCount : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			minimum : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			amount : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			conditionAmount : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			couponCount : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			score : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			fromDateStr : {
				required : "必填字段"
			},
			endDateStr : {
				required : "必填字段"
			},
			fromDate : {
				required : "必填字段"
			},
			endDate : {
				required : "必填字段"
			},
			useFromDate : {
				required : "必填字段"
			},
			useEndDate : {
				required : "必填字段"
			},
			birthday : {
				required : "必填字段"
			},
			firstCondition : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			firstPrice : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			addUnit : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			addPrice : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			expressID : {
				required : "必填字段"
			},
			formatID : {
				required : "必填字段"
			},
			productID : {
				required : "必填字段"
			},
			itemID : {
				required : "必填字段"
			},
			couponDay : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			menuLink : {
				required : "必填字段",
				url : "请输入合法的网址"
			},
			replyLink : {
				required : "必填字段",
				url : "请输入合法的网址"
			},
			title : {
				required : "必填字段"
			},
			content : {
				required : "必填字段"
			},
			replyContent : {
				required : "必填字段"
			},
			scorePrice : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
			salesScore : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			brandID : {
				required : "必填字段"
			},
			userName : {
				required : "必填字段"
			},
			passWord : {
				required : "必填字段"
			},
			companyName : {
				required : "必填字段"
			},
			linkMan : {
				required : "必填字段"
			},
			openingBank : {
				required : "必填字段"
			},
			bankAccount : {
				required : "必填字段",
				digits : "只能输入数字"
			},
			cashDep : {
				required : "必填字段",
				number : "请输入合法的数字"
			},
		}
	});
})
