package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;


public interface SalesOrderReturnApplyService {
    
    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApplyByPagination(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    public SalesOrderReturnApplyVO findSalesOrderReturnApplyByMainID(String mainID);
    
    public void updateReturnApplyInfo(SalesOrderReturnApplyVO salesOrderReturnApplyVO);
    
    public void updateApplyStatus(String mainId, String status,String currentOperator);
    public void updateApplyStatus(String mainId, String status);
    
    public SalesOrderReturnApplyVO findSalesOrderReturnApplyInfoByMainID(String mainID);

    public void refundmentOperation(String mainId, String status);
    
    public void barterOperation(String mainId, String status);
    
    /**
     * WEB端退货
     * @date 2014年12月30日
     * @author liulihui
     * @param mainId
     * @param status
     * @since JDK 1.6
     * @Description
     */
    public void salesOperation(String mainId,String status);
    
    public void insertSelective(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) ;
    
    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    /**
     * 根据申请ID 查找退换货单
     * @date 2015年1月14日
     * @author yuanyuan
     * @param orderReturnApplyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderReturnVO findSalesOrderReturnByApplyID(String retrunApplyID);
    
    /**
     * 编辑退换货单
     * @date 2015年1月14日
     * @author yuanyuan
     * @param salesOrderReturnDTO
     * @since JDK 1.6
     * @Description
     */
    public void  updateReturnOrder(SalesOrderReturnDTO salesOrderReturnDTO) ;
}
