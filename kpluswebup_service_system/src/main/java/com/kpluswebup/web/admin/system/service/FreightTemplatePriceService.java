package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.FreightTemplatePriceAreaDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaSetDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceDTO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;

public interface FreightTemplatePriceService {

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
    public List<FreightTemplatePriceVO> findFreightTemplatePriceByFTID(String freightTemplateID);

    /**
     * 添加运费模板价格
     * 
     * @date 2014年12月4日
     * @author wanghehua
     * @param freightTemplatePriceDTO
     * @since JDK 1.6
     * @Description
     */
    public void addFreightTemplatePrice(FreightTemplatePriceDTO freightTemplatePriceDTO);

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
     * 
     * @date 2014年12月4日
     * @author wanghehua
     * @param freightTemplatePriceAreaDTO
     * @since JDK 1.6
     * @Description
     */
    public void addFreightTemplatePriceAreaSet(FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO);

    /**
     * 根据运费价格删除运费区间条件
     * 
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
     * 
     * @date 2014年12月4日
     * @author wanghehua
     * @param freightTemplatePriceAreaDTO
     * @since JDK 1.6
     * @Description
     */
    public void addFreightTemplatePriceArea(FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO);

    /**
     * 根据运费价格删除运费价格地区
     * 
     * @date 2014年12月4日
     * @author wanghehua
     * @param ftPriceID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteFreightTemplatePriceArea(String ftPriceID);

    /**
     * 根据城市查找运费价格地区
     * 
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
     * 
     * @date 2015年1月13日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public FreightTemplatePriceVO findFreightTempatePriceByMainID(String mainID);
	/**
	 * 根据模板与产品查询运费
	 */
	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndProduct(
			String productID, CustomerVO userInfo);
	/**
	 * 根据模板与商品查询运费 重新计算
	 */
	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndItem(
			String itemID, CustomerVO userInfo);	
	/**
	 * 根据模板与产品查询运费
	 */	
	public FreightTemplatePriceVO findFreightTemplatePriceByCustomerDeliveryAddressAndProduct(
			String productID, String customerDeliveryAddressId);
	/**
	 * 根据模板与商品查询运费 重新计算
	 */	
	public FreightTemplatePriceVO findFreightTemplatePriceByCustomerDeliveryAddressAndItem(
			String itemID, String customerDeliveryAddressId);	
}
