package com.kpluswebup.web.customer.dao;

import java.util.List;
import com.kpluswebup.web.domain.MessageDTO;
import com.kpluswebup.web.vo.MessageVO;

public interface MessageDAO {

    public Integer deleteByPrimaryKey(String mainid);

    public Integer insert(MessageDTO record);

    public Integer insertSelective(MessageDTO record);

    MessageDTO selectByPrimaryKey(String mainid);

    public Integer updateByPrimaryKeySelective(MessageDTO record);

    public Integer updateByPrimaryKey(MessageDTO record);

    /**
     * 分页查询站内信
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
     * 查询总条数
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param messageDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findMessageCount(MessageDTO messageDTO);

    /**
     * 发送站内信
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param messageDTO
     * @since JDK 1.6
     * @Description
     */
    public void messageSend(MessageDTO messageDTO);
    
    /**
     * 修改站内信状态
     * @date 2015年1月20日
     * @author yuanyuan
     * @param messageDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateMeaaageByMainID(MessageDTO messageDTO) ;
}
