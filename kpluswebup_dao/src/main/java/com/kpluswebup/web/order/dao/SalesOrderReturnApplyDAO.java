package com.kpluswebup.web.order.dao;

import java.util.List;

import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;

public interface SalesOrderReturnApplyDAO {

    /**
     * 退换货申请信息分页查询
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApplyByPagination(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);

    /**
     * 退换货申请信息总的记录数
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Long findSalesOrderReturnApplyCount(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);

    /**
     * 根据mainID查询退货单申请
     * @date 2014年11月4日
     * @author lupeng
     * @param mainID
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderReturnApplyVO findSalesOrderReturnApplyByMainID(String mainID);
    
    /**
     * 根据主键更新退换货申请信息
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateByPrimaryKeySelective(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    /**
     * 根据申请编号更新退换货申请信息
     * @date 2014年11月5日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateByMainIDSelective(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    /**
     * 根据id查询退换货申请
     * @date 2015年1月12日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderReturnApplyVO findOrderReturnApplyByMainID(String mainID);
    
    /**
     * 申请表插入数据
     * @date 2015年1月9日
     * @author yuanyuan
     * @param salesOrderReturnApplyDTO
     * @since JDK 1.6
     * @Description
     */
    public void  insertSelective(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) ;
    
    /**
     * 查找退换货申请是否存在
     * @date 2015年1月12日
     * @author yuanyuan
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    /**
     * 根据申请ID 查找退换单记录
     * @date 2015年1月14日
     * @author yuanyuan
     * @param orderReturnApplyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderReturnVO findSalesOrderReturnByApplyID(String retrunApplyID);
    
    /**
     * 编辑
     * @date 2015年1月14日
     * @author yuanyuan
     * @param salesOrderReturnApplyDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateReturnOrder(SalesOrderReturnDTO salesOrderReturnDTO) ;
}

