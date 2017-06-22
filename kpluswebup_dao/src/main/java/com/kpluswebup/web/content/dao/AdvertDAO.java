package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.CmsAdvertClickDTO;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.vo.CmsAdvertClickVO;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.ItemVO;

public interface AdvertDAO {

	/**
	 * 查询广告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertVO> findAdvertByPagination(
			CmsAdvertDTO cmsAdvertDTO);

	/**
	 * 查询总条数
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findAdvertCount(CmsAdvertDTO cmsAdvertDTO);

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
	public Integer deleteAdvertByMainID(String mainID);

	/**
	 * 添加广告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertAdvert(CmsAdvertDTO cmsAdvertDTO);

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
	 * 修改广告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateAdvertByMainID(CmsAdvertDTO cmsAdvertDTO);
	
	/**
	 * 查询广告点击量
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertClickDTO
	 * @param advertID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertClickVO> findAdvertClickByPagination(CmsAdvertClickDTO cmsAdvertClickDTO);

	/**
	 * 查询总条数
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsAdvertClickDTO
	 * @param advertID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findAdvertClickCount(CmsAdvertClickDTO cmsAdvertClickDTO);
	
	/**
	 * 根据名称查找产品类目
	 * @date 2014年11月13日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertLinkVO> findProductTypeByName(String name);
	
	/**
	 * 根据名称查找品牌
	 * @date 2014年11月13日
	 * @author wanghehua
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertLinkVO> findBrandByName(String name);
	/**
	 * 根据名称查找产品
	 * @date 2014年11月13日
	 * @author wanghehua
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertLinkVO> findProductByName(String name);
	/**
	 * 根据名称查找商品
	 * @date 2014年11月13日
	 * @author wanghehua
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertLinkVO> findItemByName(String name);
	
	/**
	 * 根据名称查找类目
	 * @date 2014年11月22日
	 * @author wanghehua
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertLinkVO> findProductCategoryByName(ProductCategoryDTO productCategoryDTO);
	
	public ItemVO findItemByMainID(String mainID);
	
	/**
	 * 查询首页轮播图
	 * @date 2015年4月29日
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertVO> findAdvertIndex();
	
	/**
	 * 首页轮播广告
	 * @date 2015年4月29日
	 * @author Administrator
	 * @param cmsAdvertDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsAdvertVO> findAdvertIndexChild(CmsAdvertDTO cmsAdvertDTO);
}
