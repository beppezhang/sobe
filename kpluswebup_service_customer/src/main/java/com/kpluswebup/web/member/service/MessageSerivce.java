package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.MessageDTO;
import com.kpluswebup.web.vo.MessageVO;

public interface MessageSerivce {

    /**
     * 分页查找站内信
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param messageDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<MessageVO> findMessageByPagination(MessageDTO messageDTO);

    /**
     * 发送站内信
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param customerIDs
     * @param groupIDs
     * @param title
     * @param content
     * @param currentOperator
     * @since JDK 1.6
     * @Description
     */
    public void messageSend(String[] customerIDs, String[] groupIDs, String title, String content,
                            String currentOperator);

    /**
     * 根据id找站内信
     * @date 2015年1月20日
     * @author yuanyuan
     * @param messageDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void updateMessageStatus(MessageDTO messageDTO);
}
