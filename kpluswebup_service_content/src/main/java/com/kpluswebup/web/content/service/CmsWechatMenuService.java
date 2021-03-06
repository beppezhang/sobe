package com.kpluswebup.web.content.service;

import java.util.List;

import com.kpluswebup.web.domain.CmsWechatMenuDTO;
import com.kpluswebup.web.vo.CmsWechatMenuVO;

public interface CmsWechatMenuService {

    /**
     * 查询菜单配置
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatMenuDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsWechatMenuVO> findWechatMenuByPagination(CmsWechatMenuDTO cmsWechatMenuDTO);

    /**
     * 删除菜单配置
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteWechatMenuByMainID(String mainID);

    /**
     * 添加菜单配置
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatMenuDTO
     * @since JDK 1.6
     * @Description
     */
    public void addWechatMenu(CmsWechatMenuDTO cmsWechatMenuDTO);

    /**
     * 根据id查找菜单配置
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CmsWechatMenuVO findWechatMenuByMainID(String mainID);

    /**
     * 修改菜单配置
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param cmsWechatMenuDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateWechatMenuByMainID(CmsWechatMenuDTO cmsWechatMenuDTO);

    /**
     * 查询父类
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsWechatMenuVO> findCmsWechatMenuParent();

    /**
     * 根据父类id查找子类
     * 
     * @date 2014年12月23日
     * @author wanghehua
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsWechatMenuVO> findCmsWechatMenuChildByParentID(String parentID);

}
