package com.kpluswebup.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.SalesOrderShippingAddressDTO;
import com.kpluswebup.web.order.dao.SalesOrderShippingAddressDAO;
import com.kpluswebup.web.service.SalesOrderShippingAddressService;
import com.kpluswebup.web.vo.SalesOrderShippingAddressVO;
@Service
public class SalesOrderShippingAddressServiceImpl implements SalesOrderShippingAddressService {

    
    @Autowired
    private SalesOrderShippingAddressDAO  salesOrderShippingAddressDAO;
    
	@Override
	public SalesOrderShippingAddressVO findSalesOrderShippingAddressByID(
			String orderID) {
		return salesOrderShippingAddressDAO.findSalesOrderShippingAddressByOrderID(orderID);
	}

	@Override
	public void updateSalesOrderShippingAddressByOrderID(
			SalesOrderShippingAddressDTO salesOrderShippingAddressDTO) {
		salesOrderShippingAddressDAO.updateBySalesOrderShippingAddressByOrderID(salesOrderShippingAddressDTO);
	}

	@Override
	public void addSalesOrderShippingAddress(
			SalesOrderShippingAddressDTO salesOrderShippingAddressDTO) {
		salesOrderShippingAddressDAO.insertSalesOrderShippingAddress(salesOrderShippingAddressDTO);
	}

}
