package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.ExpressFormatItemDTO;
import com.kpluswebup.web.vo.ExpressFormatItemVO;

public interface ExpressFormatItemDAO {

	/**
	 * 根据模板id查找模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param formatID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<ExpressFormatItemVO> findExpressFormatItemByFormatID(String formatID);
	
	/**
	 * 添加模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param expressFormatItemDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertExpressFormatItem(ExpressFormatItemDTO expressFormatItemDTO);
	
	/**
	 * 编辑模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param expressFormatItemDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateExpressFormatItem(ExpressFormatItemDTO expressFormatItemDTO);
	
	/**
	 * 根据id查找模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param id
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public ExpressFormatItemVO findExpressFormatItemByID(Long id);
	
	/**
	 * 根据id删除模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param formatID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteExpressFormatItemByformatID(String formatID);
}
