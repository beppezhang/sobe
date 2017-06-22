//支付信息
function PayVO(partnerId,partnerkey,appid,appkey,total_fee,body,notify_url,out_trade_no,spbill_create_ip){

    this.partnerId=partnerId;//

	this.partnerkey=partnerkey;

	this.appid=appid;

	this.appkey=appkey;


	this.total_fee=total_fee;//总金额

	this.body=body;//商品名称信息

	this.notify_url=notify_url;//支付成功后将通知该地址

	this.out_trade_no=out_trade_no;//订单号

	this.spbill_create_ip=spbill_create_ip;//用户浏览器的ip
}

//辅助函数
function Trim(str, is_global) {
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g, "");
	if (is_global.toLowerCase() == "g") {
		result = result.replace(/\s/g, "");
	}
	return result;
}
function clearBr(key) {
	key = Trim(key, "g");
	key = key.replace(/<\/?.+?>/g, "");
	key = key.replace(/[\r\n]/g, "");
	return key;
}
            
//获取随机数
function getANumber() {
	var date = new Date();
	var times1970 = date.getTime();
	var times = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
	var encrypt = times * times1970;
	if (arguments.length == 1) {
		return arguments[0] + encrypt;
	} else {
		return encrypt;
	}
}
            
            
//以下是package组包过程：
var oldPackageString;//记住package，方便最后进行整体签名时取用

//下面是app进行签名的操作：
var oldTimeStamp;//记住timestamp，避免签名时的timestamp与传入的timestamp时不一致
var oldNonceStr; //记住nonceStr,避免签名时的nonceStr与传入的nonceStr不一致

function getTimeStamp() {
	var timestamp = new Date().getTime();
	var timestampstring = timestamp.toString();//一定要转换字符串
	oldTimeStamp = timestampstring;
	return timestampstring;
}
function getNonceStr() {
	var $chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	var maxPos = $chars.length;
	var noceStr = "";
	for (i = 0; i < 16; i++) {
		noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	oldNonceStr = noceStr;
	return noceStr;
}

function getSignType() {
	return "SHA1";
}

function getSign(pv) {
	var app_id = pv.appid.toString();
	var app_key = pv.appkey.toString();
	var nonce_str = oldNonceStr;
	var package_string = oldPackageString;
	var time_stamp = oldTimeStamp;
    //第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
	var keyvaluestring = "appid=" + app_id + "&appkey=" + app_key + "&noncestr=" + nonce_str + "&package=" + package_string + "&timestamp=" + time_stamp;
	sign = CryptoJS.SHA1(keyvaluestring).toString();
	return sign;
}

function getPackage(pv) {
	var banktype = "WX";
	var total_fee = pv.total_fee;//总金额。
	var body = pv.body;//商品名称信息，这里由测试网页填入。
	var fee_type = "1";//费用类型，这里1为默认的人民币
	var input_charset = "UTF-8";//字符集，这里将统一使用GBK
	var notify_url = pv.notify_url;//支付成功后将通知该地址
	var out_trade_no = pv.out_trade_no;//订单号，商户需要保证该字段对于本商户的唯一性
	var partner = pv.partnerId;//测试商户号
	var spbill_create_ip = pv.spbill_create_ip;//用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
	var partnerKey = pv.partnerkey;//这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。
                
    //首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
	var signString = "bank_type=" + banktype + "&body=" + body + "&fee_type=" + fee_type + "&input_charset=" + input_charset + "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no + "&partner=" + partner + "&spbill_create_ip=" + spbill_create_ip + "&total_fee=" + total_fee + "&key=" + partnerKey;
	var md5SignValue = ("" + CryptoJS.MD5(signString)).toUpperCase();
    //然后第二步，对每个参数进行url转码，如果您的程序是用js，那么需要使用encodeURIComponent函数进行编码。
	banktype = encodeURIComponent(banktype);
	body = encodeURIComponent(body);
	fee_type = encodeURIComponent(fee_type);
	input_charset = encodeURIComponent(input_charset);
	notify_url = encodeURIComponent(notify_url);
	out_trade_no = encodeURIComponent(out_trade_no);
	partner = encodeURIComponent(partner);
	spbill_create_ip = encodeURIComponent(spbill_create_ip);
	total_fee = encodeURIComponent(total_fee);
                
    //然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
	var completeString = "bank_type=" + banktype + "&body=" + body + "&fee_type=" + fee_type + "&input_charset=" + input_charset + "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no + "&partner=" + partner + "&spbill_create_ip=" + spbill_create_ip + "&total_fee=" + total_fee;
	completeString = completeString + "&sign=" + md5SignValue;
	oldPackageString = completeString;//记住package，方便最后进行整体签名时取用
	return completeString;
}


