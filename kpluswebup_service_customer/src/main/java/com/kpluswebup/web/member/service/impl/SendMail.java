/*
功能:加入了参考文章中没有的发送附件的功能.本机测试通过.
参考文章:http://ajava.org/article-722-1.html
使用Javamail发送邮件例子及解释 

 2012-2-1 00:14| 发布者: mark| 查看: 236| 评论: 0|原作者: mark|来自: ajava.org
摘要: 下面例子演示怎样用javamail来发送邮件，在测试之前，我们要下载javamail的类包，并添加入你的工程中，如果你的IDE自带javamail的类包，那就很简单，直接import 就行，我使用的是MyEclipse 7.5，自带，所以可以直接 ...
下面例子演示怎样用javamail来发送邮件，在测试之前，我们要下载javamail的类包，并添加入你的工程中，如果你的IDE自带javamail的类包，那就很简单，直接import 就行，mark使用的是MyEclipse 7.5，自带，所以可以直接测试下面代码了。

 

几个javamail类的作用
javax.mail.Properties类 
我们使用Properties来创建一个session对象。里面保存里对Session的一些设置，如协议，SMTP地址，是否验证的设置信息 

javax.mail.Session类 
代表一个邮件session. 有session才有通信。

javax.mail.InternetAddress类 
Address确定信件地址。

javax.mail.MimeMessage类 
Message对象将存储发送的电子邮件信息，如主题，内容等等

javax.mail.Transport类 
 Transport传输邮件类，采用send方法是发送邮件。
 * */
package com.kpluswebup.web.member.service.impl;

import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//JavaMail发送例子
public class SendMail {

	/**
	 * 此段代码用来发送普通电子邮件
	 */
	public static Boolean sendEmail(String toMail, String subject, String content,
			String mailInfo[]) throws Exception {
		try {
			boolean sessionDebug = false;
			java.util.Properties props = System.getProperties();
			props.put("mail.host", mailInfo[3]);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Authenticator auth = new PopupAuthenticator(mailInfo[0],
					mailInfo[1]);
			javax.mail.Session mailSession = javax.mail.Session.getInstance(
					props, auth);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);

			// 设定传送邮件的发信4
			msg.setFrom(new InternetAddress(mailInfo[2], mailInfo[4]));
			InternetAddress[] address = InternetAddress.parse(toMail, false);
			msg.setRecipients(Message.RecipientType.TO, address);

			// 设定信中的主题
			msg.setSubject(subject);
			// 设定送信的时间
			msg.setSentDate(new Date());

			// 设定传送信的MIME Type
			msg.setContent(content, "text/html;charset=utf-8");
			Transport.send(msg);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
}
