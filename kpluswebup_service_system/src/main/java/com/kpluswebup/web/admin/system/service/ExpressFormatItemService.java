package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.vo.ExpressFormatItemVO;

public interface ExpressFormatItemService {

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
	 * 设置模板元素
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param itemID
	 * @param formatID
	 * @param item
	 * @param value
	 * @param xray
	 * @param yray
	 * @param width
	 * @param height
	 * @param wordsize
	 * @param font
	 * @param interval
	 * @param linewidth
	 * @param bold
	 * @param italic
	 * @param position
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateExpressFormatItem(String formatID,String []formatItems,String currentOperator);
	
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
	public Boolean deleteExpressFormatItemByformatID(String formatID);
}
