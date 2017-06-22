package com.kpluswebup.web.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderTransVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.UserOrderStstusVO;

public interface SalesOrderDAO {

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
     * 分页查询订单列表
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> findSalesOrderByPagination(SalesOrderDTO salesOrderDTO);

    /**
     * 分页查询回收站
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> findDeleteOrderByPagination(SalesOrderDTO salesOrderDTO);

    /**
     * 查询符合条件的结算单列表
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getSettlementList(SalesOrderDTO salesOrderDTO);

    /**
     * 返回符合条件的总记录数
     * 
     * @date 2014年10月27日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSalesOrderCount(SalesOrderDTO salesOrderDTO);

    /**
     * 返回符合条件的总记录数
     * 
     * @date 2014年10月27日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findDeleteOrderCount(SalesOrderDTO salesOrderDTO);

    /**
     * 根据mainID查询订单(多表查询)
     * 
     * @date 2014年10月28日
     * @author lupeng
     * @param mainid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO findSalesOrderByMainID(String mainId);

    /**
     * 根据换货单编号查询订单
     * 
     * @date 2014年11月6日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO findSalesOrderByChangeOrderID(String changeOrderID);

    /**
     * 根据主键逻辑删除订单
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param mainid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteByPrimaryKey(String id);

    /**
     * 添加订单
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param salesOrderDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSalesOrder(SalesOrderDTO salesOrderDTO);

    /**
     * 根据主键查询订单(单表查询)
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param mainid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO selectByPrimaryKey(String id);

    /**
     * 修改订单
     * 
     * @date 2014年10月28日
     * @author lupeng
     * @param record
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderByMainID(SalesOrderDTO salesOrderDTO);

    /**
     * 打印订单
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateSalesOrderPrintStatus(SalesOrderDTO salesOrderDTO);

    /**
     * 打印出库单
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateSalesOrderInvoiceStatus(SalesOrderDTO salesOrderDTO);

    /**
     * 打印物流单
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateSalesOrderCarriageStatus(SalesOrderDTO salesOrderDTO);

    /***
     * 查询订单不同状态下条数
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    public UserOrderStstusVO findUserOrderStstusVO(String userMainID);

    /***
     * 返回符合条件的总记录
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findUserOrderPresellCount(SalesOrderDTO salesOrderDTO);

    /***
     * 分页查询预售订单列表
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> getUserOrderPresellPagination(SalesOrderDTO salesOrderDTO);

    /**
     * 查询用户购买过该商品的订单
     * 
     * @date 2015年1月4日
     * @author liudanqi
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findOrderLineByCustomer(SalesOrderDTO salesOrderDTO);

    /**
     * 根据用户id查询订单
     * 
     * @date 2015年1月12日
     * @author liudanqi
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderVO> findOrdersByCustomerID(String customerID);

    /**
     * 根据orderid查询订单明细
     * 
     * @date 2015年1月12日
     * @author liudanqi
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findOrdersByOrderID(String orderID);

    /**
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
     * @date 2015年5月18日
     * @author moo
     * @param orderDTO
     * @return
     * @return long
     * @since JDK 1.6
     * @Description
     */
    public long findOrderCountBySupplierID(SalesOrderDTO orderDTO);

    /**
     * @date 2015年5月18日
     * @author moo
     * @param mainID
     * @param supplierID
     * @return
     * @return List<SalesOrderLineVO>
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findOrderLineByOrderAndSupplier(@Param(value = "orderMainID")
    String mainID, @Param(value = "supplierID")
    String supplierID);

    /**
     * @date 2015年5月19日
     * @author moo
     * @param fromDate
     * @param endDate
     * @param mainID
     * @return
     * @return List<SalesOrderLineVO>
     * @since JDK 1.6
     * @Description 根据日期，供应商ID查询订单明细
     */
    public List<SalesOrderLineVO> findorderLineBySupplierIDAndDate(@Param(value = "fromDate")
    String fromDate, @Param(value = "endDate")
    String endDate, @Param(value = "supplierID")
    String mainID);

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
     * 
     * @param salesOrderTransVOList
     * @return
     */
    public Integer salesOrderTransSave(List<SalesOrderTransVO> salesOrderTransVOList);

    /**
     * @date 2015年5月27日
     * @author moo
     * @param finalStatementID
     * @return
     * @return List<SalesOrderVO>
     * @since JDK 1.6
     * @Description 查询结算单下订单
     */
    public List<SalesOrderVO> findSalesOrderByFinalstatementID(String finalStatementID);

    /**
     * 确认收款
     * @date 2015年5月30日
     * @author wanghehua
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void updatePaymentStatus(String mainID);
    
    /**
     * 撤销收款
     * @date 2015年6月10日
     * @author wanghehua
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void updateReceiveStatus(String mainID);
	
	/**
     * 返回交易完成的订单数量
     * @date 2014年10月27日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Long findUserTradeCount(SalesOrderDTO salesOrderDTO);
    
    /**
     * 分页查询交易信息列表
     * @date 2014年10月24日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<SalesOrderVO> findUserTradeByPagination(SalesOrderDTO salesOrderDTO);
    /**
     * 根据订单号查物流信息
     * @param orderID
     * @return
     */
    public List<SalesOrderTransVO> getSalesOrderTrans(SalesOrderTransDTO salesOrderTransDTO);

    /**
     * 
     * @date 2015年6月3日
     * @author moo
     * @param salesOrderDTO
     * @return
     * @return List<SalesOrderVO>
     * @since JDK 1.6
     * @Description 商家销售统计
     */
	public List<SalesOrderVO> findFinishedOrderBySupplierID(
			SalesOrderDTO salesOrderDTO);

	/**
	 * 
	 * @date 2015年6月3日
	 * @author moo
	 * @param salesOrderDTO
	 * @return
	 * @return long
	 * @since JDK 1.6
	 * @Description
	 */
	public long getFinishedOrderCountBySupplierID(SalesOrderDTO salesOrderDTO);

	/**
	 * 
	 * @date 2015年6月8日
	 * @author moo
	 * @param supplierID
	 * @param orderID
	 * @return
	 * @return SalesOrderVO
	 * @since JDK 1.6
	 * @Description 商家查看订单
	 */
	public SalesOrderVO findSupplierOrderBySupplierIDAndOrderID(
			@Param(value="supplierID") String supplierID, 
			@Param(value="orderID") String orderID);
	
	/**
     * 去掉取消后订单商品的串号
     * 
     * @date 2014年10月24日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void updateSupplierItemIdByOrderID(String orderID);

    /**
     * 
     * @date 2015年6月16日
     * @author moo
     * @param supplierID
     * @param orderID
     * @return void
     * @since JDK 1.6
     * @Description 商家确认取消订单（收到退货）
     */
	public void supplierConfirmOrderCancel(
			@Param(value="supplierID") String supplierID, 
			@Param(value="orderID") String orderID);
	
	public List<SalesOrderVO> findAllSalesOrder(SalesOrderDTO salesOrderDTO);
	
	/**
	 * 待处理订单
	 * @date 2015年7月6日
	 * @author yuanyuan
	 * @param salesOrderDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long getWaitForDealOrder(SalesOrderDTO salesOrderDTO) ;
	
	/**
	 * 
	 * @date 2015年7月24日
	 * @author yuanyuan
	 * @param salesOrderDTO
	 * @since JDK 1.6
	 * @Description 根据订单id 修改
	 */
	public void uploadSalesOrderProofURL(SalesOrderDTO salesOrderDTO);

	/*********************************************/
    /**
     * 根据mainID查询订单(多表查询,需要得出商品信息)
     * 
     * @date 2015年10月29日
     * @author lby
     * @param mainid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderVO findSalesOrderByMainIDNew(String mainId);

    /**
     * 查当月销量
     * @date 2015年12月9日
     * @author lby
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO countMonthSales(SalesOrderDTO salesOrderDTO);

    /**
     * 查累计销量
     * @date 2015年12月9日
     * @author lby
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO countTotalSales(SalesOrderDTO salesOrderDTO);
}
