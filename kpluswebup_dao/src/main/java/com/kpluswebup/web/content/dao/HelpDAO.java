package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;

public interface HelpDAO {

	/**
	 * 查询帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsHelpCenterVO> findHelpByPagination(
			CmsHelpCenterDTO cmsHelpCenterDTO);

	/**
	 * 查询总条数
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findHelpCount(CmsHelpCenterDTO cmsHelpCenterDTO);

	/**
	 * 删除帮助
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteHelpByMainID(String mainID);

	/**
	 * 添加帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertHelp(CmsHelpCenterDTO cmsHelpCenterDTO);

	/**
	 * 根据id查找帮助
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsHelpCenterVO findHelpByMainID(String mainID);

	/**
	 * 修改帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateHelpByMainID(CmsHelpCenterDTO cmsHelpCenterDTO);
	
	/**
	 * 查询帮助类型
	 * @date 2014年12月26日
	 * @author liulihui
	 * @param cmsCategoryVO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsCategoryVO> findHelpCategoryNameList(CmsCategoryDTO cmsCategoryDTO);
	
	/**
	 * 查询帮助内容
	 * @date 2014年12月26日
	 * @author liulihui
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsHelpCenterVO> frinCmsHelpCenterList(String mainID); 
	
	/**
	 * 查找帮助中心
	 * @date 2015年1月6日
	 * @author liudanqi
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsHelpCenterVO> findHelps();
		
}
