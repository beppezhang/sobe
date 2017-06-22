package com.kpuswebup.comom.util;


public class UUIDUtil {

    /**
     *  生成32位的
     * @date 2014年10月31日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public static String  getUUID(){  
        return  java.util.UUID.randomUUID().toString().replaceAll("-", "");  
    }  

    /**
     *  生成36位的
     * @date 2015年11月4日
     * @author sxc
     * @return
     * @since JDK 1.6
     * @Description
     */
    public static String  getOrigUUID(){  
        return  java.util.UUID.randomUUID().toString();  
    }  

}
