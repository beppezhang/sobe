package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.FreightTemplateDTO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpluswebup.web.vo.SupplierVO;

public interface FreightTemplateService {

	/**
	 * 查询运费模板列表
	 * 
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<FreightTemplateVO> findFreightTemplate();

	/**
	 * 添加运费模板
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param name
	 * @param priceType
	 * @param expressID
	 * @param formatID
	 * @param firstCondition
	 * @param firstPrice
	 * @param addUnit
	 * @param addPrice
	 * @param description
	 * @param areaIDs
	 * @param fTPrices
	 * @since JDK 1.6
	 * @Description
	 */
	public void addFreightTemplate(String name, String priceType,
			String expressID, String formatID, String firstCondition,
			String firstPrice, String addUnit, String addPrice,
			String description, String[] fTPricesAreaIDs,String currentOperator);

	/**
	 * 根据id查找运费模板
	 * 
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
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param mainID
	 * @param name
	 * @param priceType
	 * @param expressID
	 * @param formatID
	 * @param firstCondition
	 * @param firstPrice
	 * @param addUnit
	 * @param addPrice
	 * @param description
	 * @param areaIDs
	 * @param fTPrices
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateFreightTemplate(String mainID, String name,
			String priceType, String expressID, String formatID,
			String firstCondition, String firstPrice, String addUnit,
			String addPrice, String description, String[] fTPricesAreaIDs,String currentOperator);

	/**
	 * 根据id删除运费模板
	 * 
	 * @date 2014年12月3日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteFreightTemplate(String mainID);

	/**
	 * 设置默认运费模板
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param mainID
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean updateFreightTemplateIsDefault(String mainID);

	public void addFreightTemplate(String name, String priceType,
			String expressID, String formatID, String firstCondition,
			String firstPrice, String addUnit, String addPrice,
			String description, String[] fTPricesAreaIDs,
			String currentOperator, SupplierVO supplierVO);

	public List<FreightTemplateVO> findFreightTemplateBySupplierID(String supplierID);

	public FreightTemplateVO findFreightTemplateByProduct(String productID);
	
	public FreightTemplateVO findFreightTemplateByItem(String itemID);
	
	public List<FreightTemplateVO> findFreightTemplateByExpressID(String expressID);

	public FreightTemplateVO findFreightTemplateByName(FreightTemplateDTO freightTemplateDTO);
}
