package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderReturnShippingAddressDTO;
import com.kpluswebup.web.vo.SalesOrderReturnShippingAddressVO;


public interface SalesOrderReturnShippingAddressDAO {
    
    /**
     * 根据退换货单编号查询记录
     * @date 2014年11月4日
     * @author lupeng
     * @param returnID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SalesOrderReturnShippingAddressVO findSalesOrderReturnShippingAddressByReturnID(String returnID);
    
    /**
     * 添加一条记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnShippingAddressDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addReturnAddressSelective(SalesOrderReturnShippingAddressDTO salesOrderReturnShippingAddressDTO);
    
    /**
     * 根据退换货单编号更新记录
     * @date 2014年11月4日
     * @author lupeng
     * @param salesOrderReturnShippingAddressDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateReturnAddressByReturnID(SalesOrderReturnShippingAddressDTO salesOrderReturnShippingAddressDTO);
}