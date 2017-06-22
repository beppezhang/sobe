package com.kpluswebup.web.service;

import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;



public interface SalesOrderDeliveryAddressService {

    public void updateBySalesOrderIDSelective(SalesOrderDeliveryAddressDTO record);
    
    /**
     * 根据订单id查找收货地址
     * @date 2014年11月25日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderDeliveryAddressVO findSalesOrderDeliveryAddressByID(String orderID);
    
    /**
     * 根据订单id修改收货地址
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderDeliveryAddressByOrderID(SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO);
    
    /**
     * 添加订单收货地址
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSalesOrderDeliveryAddress(SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO);
    
}
