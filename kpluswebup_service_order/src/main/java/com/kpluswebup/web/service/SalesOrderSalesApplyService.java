package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.vo.SalesOrderAftersalesVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;


public interface SalesOrderSalesApplyService {
    
    public List<SalesOrderSalesApplyVO> findSalesOrderSalesApplyByPagination(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
    public SalesOrderSalesApplyVO findSalesOrderSalesApplyByMainID(String mainID);
    
    public void updateSalesApplyInfo(SalesOrderSalesApplyVO salesOrderSalesApplyVO);
    
    public void updateApplyStatus(String mainId, String status,String currentOperator,String memo);
    
    public SalesOrderSalesApplyVO findSalesOrderSalesApplyInfoByMainID(String mainID);

    public void salesmentOperation(String mainId, String status);
    
    public void barterOperation(String mainId, String status);
    
    public SalesOrderSalesApplyVO updateSalesOrderSalesApplyByMainID(String mainID);
    
    public SalesOrderSalesApplyVO affirmAfterSale(String mainID);
    
    /**
     * WEB端售后
     * @date 2014年12月30日
     * @author liulihui
     * @param mainId
     * @param status
     * @since JDK 1.6
     * @Description
     */
    public void salesOperation(String mainId,String status);
    
    public void saveSelective(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO) ;
    
    public List<SalesOrderSalesApplyVO> findSalesOrderSalesApply(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
    /**
     * 根据申请ID 查找售后单
     * @date 2015年1月14日
     * @author yuanyuan
     * @param orderReturnApplyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderAftersalesVO findSalesOrderSalesByApplyID(String retrunApplyID);
    
    /**
     * 编辑售后单
     * @date 2015年1月14日
     * @author yuanyuan
     * @param salesOrderReturnDTO
     * @since JDK 1.6
     * @Description
     */
    public void  updateReturnOrder(SalesOrderReturnDTO salesOrderReturnDTO) ;
    
    /**
     * 查询串号是否存在
     * 
     * @date 2014年12月18日
     * @author yuanyuan
     * @param numberIMEI
     * @since JDK 1.6
     * @Description
     */
    public SupplierItemIDVO findnumberIMEI(String numberIMEI);
    
    /**
     * 根据串号查询售后记录
     * 
     * @date 2014年12月18日
     * @author yuanyuan
     * @param numberIMEI
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderSalesApplyVO findSalesApplyByNumberIMEI(String numberIMEI);
    
    /**
     * 根据mainID查找订单明细
     * 
     * @date 2014年12月18日
     * @author yuanyuan
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderLineVO findSalesOrderLineByOrderID(String mainID);
}
