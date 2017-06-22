package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.WechatMessageDTO;
import com.kpluswebup.web.vo.WechatMessageVO;

public interface WechatMessageDAO {

    /**
     * 分页查询微信记录
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param wechatMessageDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<WechatMessageVO> findWechatMessageByPagination(WechatMessageDTO wechatMessageDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param wechatMessageDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findWechatMessageCount(WechatMessageDTO wechatMessageDTO);

    /**
     * 根据id查询微信记录
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public WechatMessageVO findWechatMessageByMainID(String mainID);
    
    /**
     * 发送微信
     * @date 2014年12月24日
     * @author wanghehua
     * @param wechatMessageDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertWechatMessage(WechatMessageDTO wechatMessageDTO);
    
    /**
     * 再次发送
     * @date 2014年12月24日
     * @author wanghehua
     * @param wechatMessageDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateWechatMessage(WechatMessageDTO wechatMessageDTO);
    
    /**
     * 根据id查询回复内容
     * @date 2014年12月24日
     * @author wanghehua
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<WechatMessageVO> findWechatMessageByParentID(String parentID);

}
