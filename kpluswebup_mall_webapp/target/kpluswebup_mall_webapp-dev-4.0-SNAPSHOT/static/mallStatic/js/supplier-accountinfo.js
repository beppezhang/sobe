$(function(){
	$("#infoForm").validate({
		rules: {

			mobile: {
				required: true
			},
			companyName: {
				required: true
			},
			linkMan: {
				required: true
			},
			openingBank: {
				required: true
			},
			bankAccount: {
				required: true
			},
			address: {
				required: true
			}

		},
		messages: {
			mobile: {
				required: "不能为空"
			},
			companyName: {
				required: "不能为空"
			},
			linkMan: {
				required: "不能为空"
			},
			openingBank: {
				required: "不能为空"
			},
			bankAccount: {
				required: "不能为空"
			},
			address: {
				required: "不能为空"
			}
		}


	})

	$(".nextLink").click(function(){
		$("#infoForm").submit();
	})

})