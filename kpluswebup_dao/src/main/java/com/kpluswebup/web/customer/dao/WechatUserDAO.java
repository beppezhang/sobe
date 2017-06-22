package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.WechatUserDTO;
import com.kpluswebup.web.vo.WechatUserVO;

public interface WechatUserDAO {

    /**
     * 分页查询微信粉丝
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param wechatUserDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<WechatUserVO> findWechatUserByPagination(WechatUserDTO wechatUserDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param WechatUserDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findWechatUserCount(WechatUserDTO wechatUserDTO);

    /**
     * 根据id查询微信粉丝
     * 
     * @date 2014年12月25日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public WechatUserVO findWechatUserByID(Long id);

}
