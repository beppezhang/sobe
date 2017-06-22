package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;


public interface SalesOrderReturnDAO {
    
    /**
     * 根据申请单编号查询退换货单据
     * @date 2014年11月4日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderReturnVO findSalesOrderReturnByRetrunApplyID(String retrunApplyID);
    
    /**
     * 根据申请单编号修改退换货单据
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateByRetrunApplyIDSelective(SalesOrderReturnDTO salesOrderReturnDTO);
    
    /**
     * 添加一条退换货单据记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addSalesOrderReturnSelective(SalesOrderReturnDTO salesOrderReturnDTO);
    
    /**
     * 根据id查询退款单
     * @date 2014年12月19日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderReturnVO findSalesOrderReturnByMainID(String mainID);
    
    /**
     * 添加一条申请退货单
     * @date 2014年12月30日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
}