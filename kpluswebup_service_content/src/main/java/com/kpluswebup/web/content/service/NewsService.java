package com.kpluswebup.web.content.service;

import java.util.List;

import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

public interface NewsService {

	/**
	 * 分页查询新闻列表
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsContentVO> findNewsByPagination(
			CmsContentDTO cmsContentDTO);

	/**
	 * 删除新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteNewsByMainID(String mainID);

	/**
	 * 添加新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addNews(CmsContentDTO cmsContentDTO);

	/**
	 * 根据id查找新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNewsByMainID(String mainID);

	/**
	 * 编辑新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void editNews(CmsContentDTO cmsContentDTO);
	
	/**
	 * 热点新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsContentVO> findHotNews(CmsContentDTO cmsContentDTO);
	
	/**
	 * 查询当前新闻的上一条
	 * @date 2015年5月30日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNewsPrev(Long id);
	
	/**
	 * 查询当前新闻的下一条
	 * @date 2015年5月30日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNewsNext(Long id);

	/**
	 * 
	 * @date 2015年6月18日
	 * @author moo
	 * @param num
	 * @return
	 * @return List<CmsContentVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsContentVO> findIndexNewsByNum(int num);
}
