package com.kpluswebup.web.content.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CmsAdvertClickDTO;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.vo.CmsAdvertClickVO;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.ItemVO;

public interface AdvertService {

    /**
     * 分页查询广告列表
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsAdvertDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsAdvertVO> findAdvertByPagination(CmsAdvertDTO cmsAdvertDTO);

    /**
     * 删除广告
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteAdvertByMainID(String mainID);

    /**
     * 添加广告
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsAdvertDTO
     * @since JDK 1.6
     * @Description
     */
    public void addAdvert(CmsAdvertDTO cmsAdvertDTO);

    /**
     * 根据id查找广告
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CmsAdvertVO findAdvertByMainID(String mainID);

    /**
     * 编辑广告
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsAdvertDTO
     * @since JDK 1.6
     * @Description
     */
    public void editAdvert(CmsAdvertDTO cmsAdvertDTO);

    /**
     * 分页查询广告点击列表
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsAdvertClickDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsAdvertClickVO> findAdvertClickByPagination(CmsAdvertClickDTO cmsAdvertClickDTO);

    public List<CmsAdvertLinkVO> findAdvertLinkByName(String name, Integer type);

    public ItemVO findItemByMainID(String mainID);

    /**
     * 广告点击量导出excel
     * 
     * @date 2014年12月16日
     * @author wanghehua
     * @param response
     * @param advertID
     * @since JDK 1.6
     * @Description
     */
    public void exportAdvertClick(HttpServletResponse response, String advertID);
    
    /**
     * 查询首页轮播图
     * @date 2015年4月29日
     * @author Administrator
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsAdvertVO> findAdvertIndex();
}
