package com.kpluswebup.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.vo.SalesOrderTransVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;
import com.kpluswebup.web.vo.UserOrderStstusVO;
import com.kpuswebup.common.exception.DuplicateSerialiseNoException;

public interface SalesOrderService {
    
    /**
     * 同意某时间段类的总金额
     * @date 2015年7月27日
     * @author yuanyuan
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO countSalesOrderAllAmount(SalesOrderDTO salesOrderDTO);

    /**
     * 查询订单列表
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getSalesOrderList(SalesOrderDTO salesOrderDTO);

    /**
     * 查询结算单列表
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getSettlementList(SalesOrderDTO salesOrderDTO);
    
    /**
     * 订单详情
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO getSalesOrderLine(String mainId);

    /**
     * 修改订单
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrder(SalesOrderDTO salesOrderDTO);
    
    /**
     * 
     * @date 2015年7月24日
     * @author yuanyuan
     * @since JDK 1.6
     * @Description 根据订单id修改
     */
    public void uploadSalesOrderProofURL(SalesOrderDTO salesOrderDTO);

    /**
     * 修改订单(包含关联表)
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrder(SalesOrderVO salesOrder, String priceAndItemCountStr);

    /**
     * 更新物流单信息(送货信息、发货信息)
     * 
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateLogisticsInfo(SalesOrderVO salesOrder);

    /**
     * 打印单子
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param mainID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean doPrint(String mainIDs, Integer status);

    /**
     * 修改订单状态
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param orderIDs
     * @param orderStatus
     * @since JDK 1.6
     * @Description
     */
    public int updateSalesOrderStatus(String orderIDs, String orderStatus,String expressNumber);

    /**
     * 修改订单
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderVO
     * @since JDK 1.6
     * @Description
     */
    public void editSalesOrder(SalesOrderDTO salesOrderDTO);

    /**
     * 导出订单excel
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSalesOrder(HttpServletResponse response, SalesOrderDTO salesOrderDTO);
    
    /**
     * 导出订单excel
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSalesOrderBySupplier(HttpServletResponse response, SalesOrderDTO salesOrderDTO);

    /**
     * 查询订单不同状态下数量
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    public UserOrderStstusVO findUserOrderStstusVO(String userMainID);

    /**
     * 查询预售订单列表
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getUserPresellList(SalesOrderDTO salesOrderDTO);

    /**
     * 判断用户是否购买购指定订单的商品
     * 
     * @date 2015年1月4日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    public boolean isBuyByCustomer(String customerID, String productID);

    /**
     * 添加订单
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param allFreight 
     * @param supplierFreightStr 
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public StringBuffer addSalesOrder(String customerID, String cartIDs, String addressID, String paymentType,
                                String orderAmount, String scoreAll, String memo, String itemCount, String itemID, 
                                String supplierID, String itemType,String orderType,String objID,
                                String invoiceType, String invoiceTitle, String generateType, String orderID, String allFreight, String supplierFreightStr)throws Exception;

    /**
     * 根据id查询订单
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO findSalesOrderByMainID(String mainId);
    
    /**
     * 订单编号
     * @date 2015年1月14日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String randomNumeric();
    
    /**
     * 查询用户的订单
     * @date 2015年1月12日
     * @author liudanqi
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> findOrdersByCustomerID(String customerID);
    
    /**
     * 
     * @date 2015年5月15日
     * @author moo
     * @param orderDTO
     * @return
     * @return List<SalesOrderVO>
     * @since JDK 1.6
     * @Description
     */
	public List<SalesOrderVO> findOrdersBySupplierID(SalesOrderDTO orderDTO);
	
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @param mainId
	 * @param supplierID
	 * @return
	 * @return SalesOrderVO
	 * @since JDK 1.6
	 * @Description
	 */
	
	public SalesOrderVO getSupplierSalesOrderLine(String mainId, String supplierID);
	
	/**
	 * 
	 * @date 2015年5月21日
	 * @author moo
	 * @param orderId
	 * @return
	 * @return List<SupplierItemIDVO>
	 * @since JDK 1.6
	 * @Description 查询订单下所有单个商品
	 */
	public List<SupplierItemIDVO> findSupplierItemIDByOrderID(String orderId);
	
	/**
	 * 
	 * @date 2015年5月22日
	 * @author moo
	 * @param supplierItemID
	 * @param serialiseNo
	 * @return void
	 * @since JDK 1.6
	 * @Description 商品发货
	 */
	public void updateSalesOrderSupplierItemIDSerialiseNO(
			String orderID, String packageWeight, String packageNum,
			String[] supplierItemID, String[] serialiseNo) throws DuplicateSerialiseNoException;

	/**
     * 查询回收站
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getDeleteOrderList(SalesOrderDTO salesOrderDTO);
    
    /**
     * 删除订单
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderByID(String mainID);
    /**
     * 保存物流信息
     * @param sXml
     * @return
     */
    public String salesOrderTransSave(List<SalesOrderTransVO> salesOrderTransVOList);
    
    /**
     * 查询订单交易列表
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> findUserTradeList(SalesOrderDTO salesOrderDTO);
    
    /**
     * 确认收款
     * 
     * @date 2015年5月30日
     * @author wanghehua
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public Boolean updatePaymentStatus(String mainIds);
    
    /**
     * 撤销收款
     * @date 2015年6月10日
     * @author wanghehua
     * @param mainIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean updateReceiveStatus(String mainId);
    
    /**
     * 根据订单号查物流信息
     * @param orderID
     * @return
     */
    public List<SalesOrderTransVO> getSalesOrderTrans(SalesOrderTransDTO  salesOrderTransDTO);

    /**
     * 
     * @date 2015年6月3日
     * @author moo
     * @param mainID
     * @return
     * @return List<SalesOrderVO>
     * @since JDK 1.6
     * @Description 销售统计
     */
	public List<SalesOrderVO> findFinishedOrderBySupplierID(SalesOrderDTO salesOrderDTO);

	/**
	 * 
	 * @date 2015年6月8日
	 * @author moo
	 * @param response
	 * @param mainIDs
	 * @return void
	 * @since JDK 1.6
	 * @Description 导出销售统计
	 */
	public void exportSupplierSalesOrder(HttpServletResponse response,
			String mainIDs, String supplierID);
	
	/**
	 * 导出应收账款
	 * @date 2015年6月15日
	 * @author wanghehua
	 * @param response
	 * @param mainIds
	 * @since JDK 1.6
	 * @Description
	 */
	public void exportReceivable(HttpServletResponse response,SalesOrderDTO salesOrderDTO);

	/**
	 * 
	 * @date 2015年6月16日
	 * @author moo
	 * @param supplierID
	 * @param orderID
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void supplierConfirmOrderCancel(String supplierID, String orderID);
	
	/**
	 * 商家待处理订单
	 * @date 2015年7月6日
	 * @author yuanyuan
	 * @param salesOrderDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long getWaitForDealOrder(SalesOrderDTO salesOrderDTO);
	
	/**
	 * 商家近期的订单
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param supplierID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<SalesOrderVO> getNearFutureOrderBySupplierID(SalesOrderDTO salesOrderDTO);

	
	/*********************************************************************/
    /**
     * 根据id查询订单
     * 
     * @date 2015年10月29日
     * @author lby
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO findSalesOrderByMainIDNew(String mainId);
    
    
    /**
     * 添加订单
     * 
     * @date 2015年11月3日
     * @author sxc
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public StringBuffer addSalesOrderTparts(String customerID, String cartIDs, String addressID, String paymentType,
                                String orderAmount, String scoreAll, String memo, String itemCount, String itemID, 
                                String supplierID, String itemType,String orderType,String objID,
                                String invoiceType, String invoiceTitle, String generateType, String orderID,String memoIDs, String allFreight, String supplierFreightStr)throws Exception;    
}		

