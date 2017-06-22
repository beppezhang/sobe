package com.kpluswebup.web.content.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

public interface NewsDAO {

	/**
	 * 查询新闻
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
	 * 查询总条数
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findNewsCount(CmsContentDTO cmsContentDTO);

	/**
	 * 删除新闻
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteNewsByMainID(String mainID);

	/**
	 * 添加新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertNews(CmsContentDTO cmsContentDTO);

	/**
	 * 根据id查找新闻
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNewsByMainID(String mainID);

	/**
	 * 修改新闻
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateNewsByMainID(CmsContentDTO cmsContentDTO);
	
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
	public List<CmsContentVO> findIndexNewsByNum(@Param(value="num") int num);
}
