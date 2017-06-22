package com.kpluswebup.web.order.dao;

import com.kpluswebup.web.domain.SalesOrderShippingAddressDTO;
import com.kpluswebup.web.vo.SalesOrderShippingAddressVO;

public interface SalesOrderShippingAddressDAO {
    
	 /**
     * 根据订单号修改发货地址
     * @date 2014年11月25日
     * @author wanghehua
     * @param salesOrderShippingAddressDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateBySalesOrderShippingAddressByOrderID(SalesOrderShippingAddressDTO salesOrderShippingAddressDTO);
    
    /**
     * 根据订单id查找发货地址
     * @date 2014年11月25日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderShippingAddressVO findSalesOrderShippingAddressByOrderID(String orderID);
    
    /**
     * 添加订单发货地址
     * @date 2014年11月26日
     * @author wanghehua
     * @param salesOrderShippingAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertSalesOrderShippingAddress(SalesOrderShippingAddressDTO salesOrderShippingAddressDTO);
}