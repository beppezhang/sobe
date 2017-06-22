
//function loginSucess(){
//	var result = self.get("returnValue");
//	alert("result");
//	if(result.result){
//		location=result.url;
//	}else{
//		view.set("#msg.content","<font color='red'>"+result.errormsg+"</font>");
//	}
//
//}
//
function checkLogin(){
	alert("checkLogin")
	var data=view.get("#autoFormLogin").get("entity");
	var username=data["username"];
	var password=data["password"];
	if(username && username.length>0 && password && password.length>0){
		self.set("parameter",data);
	}else{
		dorado.MessageBox.alert("用户名密码不允许为空");
		arg.processDefault=false;
	}
}