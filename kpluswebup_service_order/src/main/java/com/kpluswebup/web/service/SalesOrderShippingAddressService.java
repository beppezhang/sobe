package com.kpluswebup.web.service;

import com.kpluswebup.web.domain.SalesOrderShippingAddressDTO;
import com.kpluswebup.web.vo.SalesOrderShippingAddressVO;



public interface SalesOrderShippingAddressService {

    
    /**
     * 根据订单id查找发货地址
     * @date 2014年11月25日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderShippingAddressVO findSalesOrderShippingAddressByID(String orderID);
    
    /**
     * 根据订单id修改发货地址
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderShippingAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderShippingAddressByOrderID(SalesOrderShippingAddressDTO salesOrderShippingAddressDTO);
    
    /**
     * 添加订单发货订单
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderShippingAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSalesOrderShippingAddress(SalesOrderShippingAddressDTO salesOrderShippingAddressDTO);
    
}
