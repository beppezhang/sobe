package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.FreightTemplateDTO;
import com.kpluswebup.web.vo.FreightTemplateVO;

public interface FreightTemplateDAO {

	/**
	 * 查询运费模板列表
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<FreightTemplateVO> findFreightTemplate();
	
	/**
	 * 添加运费模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param freightTemplateDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertFreightTemplate(FreightTemplateDTO freightTemplateDTO);
	
	/**
	 * 根据id查找运费模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public FreightTemplateVO findFreightTemplateByMainID(String mainID);
	
	/**
	 * 修改运费模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param freightTemplateDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateFreightTemplate(FreightTemplateDTO freightTemplateDTO);
	
	/**
	 * 根据id删除运费模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteFreightTemplateByMainID(String mainID);
	
	/**
	 * 查询默认模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public FreightTemplateVO findFreightTemplateDefault();
	
	/**
	 * 修改默认为非默认
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param mainID
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateFreightTemplateDefault(String mainID);
	
	/**
	 * 设置默认运费模板
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param mainID
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateFreightTemplateIsDefault(String mainID);

	public List<FreightTemplateVO> findFreightTemplateBySupplierID(
			String supplierID);

	public FreightTemplateVO findFreightTemplateByProduct(String productID);
	
	public FreightTemplateVO findFreightTemplateByItem(String itemID);
	
	public List<FreightTemplateVO> findFreightTemplateByExpressID(String expressID);

	public FreightTemplateVO findFreightTemplateByName(FreightTemplateDTO freightTemplateDTO);
}
