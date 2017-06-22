package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;

public interface SalesOrderDeliveryAddressDAO {

    /**
     * 根据订单号修改收货信息
     * @date 2014年10月30日
     * @author lupeng
     * @param record
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateBySalesOrderDeliveryAddressByOrderID(SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO);
    
    /**
     * 根据订单id查找收货地址
     * @date 2014年11月25日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderDeliveryAddressVO findSalesOrderDeliveryAddressByOrderID(String orderID);
    
    /**
     * 添加订单收货地址
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertSalesOrderDeliveryAddress(SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO);

}