package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.CmsWechatReplySetDTO;
import com.kpluswebup.web.vo.CmsWechatReplySetVO;

public interface CmsWechatReplySetDAO {

    /**
     * 查询关注回复
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatReplySetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsWechatReplySetVO> findWechatReplyByPagination(CmsWechatReplySetDTO cmsWechatReplySetDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatReplySetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findWechatReplyCount(CmsWechatReplySetDTO cmsWechatReplySetDTO);

    /**
     * 删除关注回复
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteWechatReplyByMainID(String mainID);

    /**
     * 添加关注回复
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatReplySetDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertWechatReply(CmsWechatReplySetDTO cmsWechatReplySetDTO);

    /**
     * 根据id查找关注回复
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CmsWechatReplySetVO findWechatReplyByMainID(String mainID);

    /**
     * 修改关注回复
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatReplySetDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateWechatReplyByMainID(CmsWechatReplySetDTO cmsWechatReplySetDTO);
    
    /**
     * 根据关键字查找关键词回复
     * @date 2015年1月5日
     * @author liudanqi
     * @param keyword
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CmsWechatReplySetVO findWechatByKeyword(String keyword);
    
}
