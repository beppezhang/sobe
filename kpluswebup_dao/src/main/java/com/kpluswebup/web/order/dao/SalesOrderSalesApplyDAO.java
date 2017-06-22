package com.kpluswebup.web.order.dao;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderAfterSalesDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.vo.SalesOrderAftersalesVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;

public interface SalesOrderSalesApplyDAO {

    /**
     * 售后申请信息分页查询
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<SalesOrderSalesApplyVO> findSalesOrderSalesApplyByPagination(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);

    /**
     * 售后申请信息总的记录数
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Long findSalesOrderSalesApplyCount(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);

    /**
     * 根据mainID查询售后申请单
     * @date 2014年11月4日
     * @author lupeng
     * @param mainID
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderSalesApplyVO findSalesOrderSalesApplyByMainID(String mainID);
    
    /**
     * 根据主键更新售后申请信息
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateAfterSalesByPrimaryKeySelective(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
    /**
     * 根据申请编号更新售后申请信息
     * @date 2014年11月5日
     * @author lupeng
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateByMainIDSelectives(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
    /**
     * 根据id查询售后申请
     * @date 2015年1月12日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderSalesApplyVO findOrderSalesApplyByMainID(String mainID);
    
    /**
     * 申请表插入数据
     * @date 2015年1月9日
     * @author yuanyuan
     * @param salesOrderSalesApplyDTO
     * @since JDK 1.6
     * @Description
     */
    public void  saveSelective(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO) ;
    
    /**
     * 查找售后申请是否存在
     * @date 2015年1月12日
     * @author yuanyuan
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderSalesApplyVO> findSalesOrderSalesApply(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO);
    
    /**
     * 根据申请ID 查找售后申请单记录
     * @date 2015年1月14日
     * @author yuanyuan
     * @param orderSalesApplyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderAftersalesVO findSalesOrderSalesByApplyID(String salesApplyID);
    
    /**
     * 编辑
     * @date 2015年1月14日
     * @author yuanyuan
     * @param salesOrderSalesApplyDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateAfterSalesOrder(SalesOrderAfterSalesDTO salesOrderAfterSalesDTO) ;
    
    /**
     * 根据串号查找手机相关信息
     * @date 2014年11月3日
     * @author lupeng
     * @param numberIMEI
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SupplierItemIDVO findSupplierItemByNumberIMEI(String numberIMEI);
    
    /**
     * 根据串号查找售后
     * @date 2014年11月3日
     * @author lupeng
     * @param numberIMEI
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderSalesApplyVO findSalesOrderSalesApplyByNumberIMEI(String numberIMEI);
    
    /**
     * 
     * @date 2014年11月3日
     * @author lupeng
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderLineVO findSalesOrderLineByOrderID(String mainID);
}

