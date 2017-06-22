package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderAfterSalesAddressDTO;
import com.kpluswebup.web.vo.SalesOrderAfterSalesAddressVO;


public interface SalesOrderAfterSalesAddressDAO {
    
    /**
     * 根据售后单编号查询记录
     * @date 2014年11月4日
     * @author lupeng
     * @param returnID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderAfterSalesAddressVO findSalesOrderAfterSalesAddressByAfterSalesID(String afterSalesID);
    
    /**
     * 添加一条记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderAfterSalesAddressDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addSalesAddressSelective(SalesOrderAfterSalesAddressDTO salesOrderAfterSalesAddressDTO);
    
    /**
     * 根据售后单编号更新记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderAfterSalesAddressDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateSalesAddressByAfterSalesID(SalesOrderAfterSalesAddressDTO salesOrderAfterSalesAddressDTO);
}