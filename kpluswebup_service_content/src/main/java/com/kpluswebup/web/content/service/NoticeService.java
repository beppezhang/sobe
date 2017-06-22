package com.kpluswebup.web.content.service;

import java.util.List;

import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

public interface NoticeService {

	/**
	 * 分页查询公告列表
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
	 * 删除公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteNoticeByMainID(String mainID);

	/**
	 * 添加公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addNotice(CmsContentDTO cmsContentDTO);

	/**
	 * 根据id查找公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CmsContentVO findNoticeByMainID(String mainID);

	/**
	 * 编辑公告
	 * @date 2014年11月10日
	 * @author wanghehua
	 * @param cmsContentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void editNotice(CmsContentDTO cmsContentDTO);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param i
	 * @return
	 * @return List<CmsContentVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CmsContentVO> findSupplierNotice(int i);
}
