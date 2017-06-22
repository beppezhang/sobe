package com.kpluswebup.web.order.dao;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.vo.SalesOrderLineVO;

public interface SalesOrderLineDAO {

    /**
     * 根据订单号多表查询
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findSalesOrderLine(String orderID);

    /**
     * 根据主键修改订单明细
     * 
     * @date 2014年10月30日
     * @author lupeng
     * @param salesOrderLineDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateByPrimaryKeySelective(SalesOrderLineDTO salesOrderLineDTO);

    /**
     * 根据订单编号、产品编号、商品编号修改订单明细
     * 
     * @date 2014年11月6日
     * @author lupeng
     * @param salesOrderLineDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateByParameters(SalesOrderLineDTO salesOrderLineDTO);

    /**
     * 添加订单明细
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param salesOrderLineDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO);

    /**
     * 修改订单明细
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderLineDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderLineByID(SalesOrderLineDTO salesOrderLineDTO);

    /**
     * 根据id查找订单明细
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderLineVO findSalesOrderLineByID(Long id);

    /***
     * 根据订单ID多表查询
     * 
     * @date 2014年12月23日
     * @author liulihui
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findSalesOrderPresellLine(String orderID);

    /**
     * 搜索订单商品
     * 
     * @date 2014年12月31日
     * @author wanghehua
     * @param salesOrderLineDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findSalesOrderLineBySearch(SalesOrderLineDTO salesOrderLineDTO);
    
    /**
     * 搜索被删除订单商品
     * 
     * @date 2014年12月31日
     * @author wanghehua
     * @param salesOrderLineDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findDeleteOrderLineBySearch(SalesOrderLineDTO salesOrderLineDTO);
    
    /**
     * 查询商品销售情况
     * @date 2015年4月27日
     * @author Administrator
     * @param itemID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findSalesOrderLineByItem(String itemID);
    /**
     * 根据商品id查询销售记录
     * @param productID
     * @author zhoulei
     * @date 2015年5月11日
     * @return
     */
    public List<SalesOrderLineVO> findSalesOrderLineByProductId(String productID);
    
    /********************************************************************/
    
    public List<SalesOrderLineDTO> findSalesOrderLineByIDTparts(String orderID);
}
