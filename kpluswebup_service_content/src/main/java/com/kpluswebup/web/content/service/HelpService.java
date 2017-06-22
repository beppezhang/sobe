package com.kpluswebup.web.content.service;

import java.util.List;

import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;

public interface HelpService {

	/**
	 * 分页查询帮助列表
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
	 * 删除帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteHelpByMainID(String mainID);

	/**
	 * 添加帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addHelp(CmsHelpCenterDTO cmsHelpCenterDTO);

	/**
	 * 根据id查找帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsHelpCenterVO findHelpByMainID(String mainID);

	/**
	 * 编辑帮助
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsHelpCenterDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void editHelp(CmsHelpCenterDTO cmsHelpCenterDTO);
	
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
     * 查找帮助中心
     * @date 2015年1月6日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsHelpCenterVO> findHelps();
	
}
