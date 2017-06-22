package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

public interface NoticeDAO {

	/**
	 * 查询公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsContentVO> findNoticeByPagination(
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
	public Long findNoticeCount(CmsContentDTO cmsContentDTO);

	/**
	 * 删除公告
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteNoticeByMainID(String mainID);

	/**
	 * 添加公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertNotice(CmsContentDTO cmsContentDTO);

	/**
	 * 根据id查找公告
	 * 
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNoticeByMainID(String mainID);

	/**
	 * 修改公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateNoticeByMainID(CmsContentDTO cmsContentDTO);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param size
	 * @return
	 * @return List<CmsContentVO>
	 * @since JDK 1.6
	 * @Description 
	 */
	public List<CmsContentVO> findSupplierNotice(int size);
}
