package com.kpluswebup.mall.web.netpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpluswebup.mall.web.control.BaseController;

@Controller
@RequestMapping("/")
public class ReadMerky extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ReadMerky.class);

    private String              merprk;

    public String getMerPrk() {
        String url = "MerPrk.Key";
        LOGGER.info("MerPrk-------url==" + url);
        File file = new File(url);
        StringBuffer bufer = new StringBuffer();
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    char countent = (char) tempchar;
                    System.out.print((char) tempchar);
                    LOGGER.info((char) tempchar);
                    bufer.append(countent);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("签名====" + bufer.toString());
        merprk = bufer.toString();
        return "";
    }

    public String getMerprk() {
        return merprk;
    }

    public void setMerprk(String merprk) {
        this.merprk = merprk;
    }

}
