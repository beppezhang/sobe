package com.kpuswebup.comom.util;

import org.apache.log4j.Logger;

import cn.emay.sdk.client.api.Client;

public class SendSms {
	private final static Logger log = Logger.getLogger(SendSms.class);
	private static Client sdkclient;

	/**
	 * 一个序列号只需注册一次
	 */
	public static void registEx() {
		try {
			int i = sdkclient.registEx("993442");
			System.out.println("注册的结果:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		try {
			sdkclient = new Client("6SDK-EMY-6688-KJQRL","993442");
			registEx();
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
	}

	/**
	 * 发送短消息
	 * 
	 * @param toPhone
	 * @param content
	 * @param priority
	 * @return -1发送信息失败（短信内容长度越界） 0短信发送成功 17发送信息失败（未激活序列号或序列号和KEY值不对，或账户没有余额等）
	 *         101客户端网络故障 305服务器端返回错误，错误的返回值（返回值不是数字字符串）
	 *         307目标电话号码不符合规则，电话号码必须是以0、1开头 997平台返回找不到超时的短信，该信息是否成功无法确定
	 *         303由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
	 */
	public static Integer sendSMS(String[] toPhone, String content,
			Integer priority) {
		
		if (sdkclient == null) {//智商开发报告
			try {
				sdkclient = new Client("6SDK-EMY-6688-KJQRL","993442");
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}
		}
		return sdkclient.sendSMS(toPhone, content, priority);
						
	}

	/**
	 * 
	 * @param toPhone
	 * @param content
	 * @param charset
	 * @param priority
	 * @return -1发送信息失败（短信内容长度越界） 0短信发送成功 17发送信息失败（未激活序列号或序列号和KEY值不对，或账户没有余额等）
	 *         101客户端网络故障 305服务器端返回错误，错误的返回值（返回值不是数字字符串）
	 *         307目标电话号码不符合规则，电话号码必须是以0、1开头 997平台返回找不到超时的短信，该信息是否成功无法确定
	 *         303由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
	 */
	public static Integer sendSMSEx(String[] toPhone, String content,
			String charset, Integer priority) {
		if (sdkclient == null) {
			try {
				sdkclient = new Client("6SDK-EMY-6688-KJQRL", "993442");
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}
		}
		return sdkclient.sendSMSEx(toPhone, content, charset, priority);
	}

	/**
	 * 查询短信单价
	 * 
	 * @return double类型
	 */
	public static Double getEachFee() {
		return sdkclient.getEachFee();
	}

	/**
	 * 查询余额
	 * 
	 * @return 返回null时表示出错了
	 */
	public static Double getBalance() {
		try {
			return Double.valueOf(sdkclient.getBalance());
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
		return null;
	}

	/**
	 * 充值
	 * 
	 * @param cardNo
	 * @param cardPass
	 * @return 0充值成功 13充值失败 303客户端网络超时或者网络故障
	 *         305服务器端返回错误，错误的返回值（返回值不是数字字符串）999操作频繁
	 */
	public static Integer chargeUp(String cardNo, String cardPass) {
		return sdkclient.chargeUp(cardNo, cardPass);
	}

	/**
	 * 定时短信发送
	 * 
	 * @param toPhone
	 * @param content
	 * @param sendTime
	 *            格式"yyyyMMddHHmmss"
	 * @return -1发送信息失败（短信内容长度越界） 0短信发送成功 17发送信息失败（未激活序列号或序列号和KEY值不对，或账户没有余额等）
	 *         101客户端网络故障 305服务器端返回错误，错误的返回值（返回值不是数字字符串）
	 *         307目标电话号码不符合规则，电话号码必须是以0、1开头 997平台返回找不到超时的短信，该信息是否成功无法确定
	 *         303由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定
	 */
	public static Integer sendScheduledSMS(String[] toPhone, String content,
			String sendTime) {
		return sdkclient.sendScheduledSMS(toPhone, content, sendTime);
	}

	public static void main(String[] args) {
		System.out.println(SendSms.sendSMS(
				new String[] { "18267176287"}, "中邮普泰", 1));
	}
}
