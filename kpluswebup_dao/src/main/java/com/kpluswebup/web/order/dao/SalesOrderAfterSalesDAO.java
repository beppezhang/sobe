package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderAfterSalesDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.vo.SalesOrderAftersalesVO;

public interface SalesOrderAfterSalesDAO {
    
    /**
     * 根据申请单编号查询售后单据
     * @date 2014年11月4日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderAftersalesVO findSalesOrderSalesBySalesApplyID(String salesApplyID);
    
    /**
     * 根据申请单编号修改售后单据
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateBySalesApplyIDSelective(SalesOrderAfterSalesDTO salesOrderAfterSalesDTO);
    
    /**
     * 添加一条售后单据记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addSalesOrderSalesSelective(SalesOrderAfterSalesDTO salesOrderAfterSalesDTO);
    
    /**
     * 根据id查询售后单
     * @date 2014年12月19日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderAftersalesVO findSalesOrderSalesByMainID(String mainID);
    
    /**
     * 添加一条申请售后单
     * @date 2014年12月30日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addSalesOrderSalesApply(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
}