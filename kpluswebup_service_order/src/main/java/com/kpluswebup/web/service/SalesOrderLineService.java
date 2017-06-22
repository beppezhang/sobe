package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SupplierItemIDDTO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;

public interface SalesOrderLineService {

    public void updateByPrimaryKeySelective(SalesOrderLineDTO salesOrderLineDTO);
    
    /**
     * 根据id修改商品明细
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderLineDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderLineByID(SalesOrderLineDTO salesOrderLineDTO);
    
    /**
     * 根据id查找订单明细
     * @date 2014年11月25日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderLineVO findSalesOrderLineByID(Long id);
    
    /**
     * 根据订单id查询订单明细
     * @date 2015年1月5日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findSalesOrderLine(String orderID);
    
    /**
     * 根据商品id查询销售记录
     * @param productID
     * @author zhoulei
     * @date 2015年5月11日
     * @return
     */
    public List<SalesOrderLineVO> findSalesOrderLineByProductId(String productID);
    
    /**
     * 删除运送商品
     * @date 2015年6月24日
     * @author wanghehua
     * @param salesOrderLineId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void deleteSupplierItemIDBySalesOrderLineID(String salesOrderLineId);
    
    public void addSupplierItemID(SupplierItemIDDTO supplierItemIDDTO);
    
    public List<SalesOrderLineDTO> findSalesOrderLineByIDTparts(String orderID);    
    
}
