package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.EmailDTO;
import com.kpluswebup.web.vo.EmailVO;

public interface EmailSerivce {

    /**
     * 分页查找邮件
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param emailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<EmailVO> findEmailByPagination(EmailDTO emailDTO);

    /**
     * 根据id查找邮件
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean findEmailByMainID(String mainID);

    /**
     * 发送邮件
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param customerIDs
     * @param title
     * @param content
     * @param currentOperator
     * @since JDK 1.6
     * @Description
     */
    public void emailSend(String[] customerIDs,String [] groupIDs, String title, String content, String currentOperator);
    
    /**
     * 会员邀请
     * @date 2015年1月20日
     * @author yuanyuan
     * @param email
     * @param title
     * @param content
     * @since JDK 1.6
     * @Description
     */
    public void sendEmail(String mainID,String email,String title,String content);

}
