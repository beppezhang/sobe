package com.kpluswebup.web.admin.system.dao;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.domain.FreightTemplatePriceAreaDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaSetDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceDTO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaSetVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;

public interface FreightTemplatePriceDAO {

	/**
	 * 查询运费模板价格列表
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplateID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<FreightTemplatePriceVO> findFreightTemplatePriceByFTID(
			String freightTemplateID);

	/**
	 * 添加运费模板价格
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplatePriceDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertFreightTemplatePrice(
			FreightTemplatePriceDTO freightTemplatePriceDTO);

	/**
	 * 根据id删除运费模板价格
	 * 
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplateID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteFreightTemplatePriceByFTID(String freightTemplateID);
	
	/**
	 * 添加运费区间条件
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplatePriceAreaDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertFreightTemplatePriceAreaSet(FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO);
	
	/**
	 * 根据运费价格删除运费区间条件
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param ftPriceID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteFreightTemplatePriceAreaSet(String ftPriceID);
	/**
	 * 添加运费价格地区
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplatePriceAreaDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertFreightTemplatePriceArea(FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO);
	
	/**
	 * 根据运费价格删除运费价格地区
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param ftPriceID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteFreightTemplatePriceArea(String ftPriceID);
	
	/**
	 * 根据运费模板价格查询运费区间
	 * @date 2014年12月4日
	 * @author wanghehua
	 * @param freightTemplatePriceAreaSetDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<FreightTemplatePriceAreaSetVO> findFreightTemplatePriceAreaSetByFTPID(FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO);
	
	/**
	 * 根据城市查找运费价格地区
	 * @date 2015年1月13日
	 * @author wanghehua
	 * @param cityID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public FreightTemplatePriceAreaVO findFreightTemplatePriceAreaByCityID(String cityID);
	
	/**
	 * 根据id查询模板价格
	 * @date 2015年1月13日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public FreightTemplatePriceVO findFreightTempatePriceByMainID(String mainID);

	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndProduct(Map<String, String> map);
	
	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndItem(Map<String, String> map);

}
