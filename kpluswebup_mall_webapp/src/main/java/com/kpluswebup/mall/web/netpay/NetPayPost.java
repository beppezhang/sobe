package com.kpluswebup.mall.web.netpay;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpluswebup.mall.web.control.BaseController;

@Controller
@RequestMapping("/")
public class NetPayPost extends BaseController {

    @SuppressWarnings("unused")
    private String getPostMethod() {
        StringBuffer url = new StringBuffer();
        url.append("http://");
        url.append(getRequest().getServerName());
        url.append(":");
        url.append(getRequest().getServerPort());
        url.append(getRequest().getContextPath());
        HttpClient client = new HttpClient();
        PostMethod authpost = new PostMethod(url + "/testpost.htm");
        NameValuePair[] data = { new NameValuePair("name", "form1"), new NameValuePair("method", "post"),
                new NameValuePair("action", "/xpls/citationAct"), new NameValuePair("target", "blank"),
                new NameValuePair("dlSelect", "cite_abs"), new NameValuePair("fileFormate", "BibTeX"),
                new NameValuePair("arnumber", "<arnumber>4231769</arnumber>") };
        authpost.setRequestBody(data);
        // 执行Post请求
        try {
            client.executeMethod(authpost);
        } catch (HttpException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 打印状态码
        System.out.println(" form post: " + authpost.getStatusLine().toString());
        // 释放连接
        authpost.releaseConnection();
        return null;
    }

    public String testpost() {
        System.out.println(getRequest().getParameter("ChkValue"));
        return null;
    }

    public static void main(String[] ad) {
        int snober3 = (int) (10 + Math.random() * 1000);
        System.out.println(snober3);
    }
}
