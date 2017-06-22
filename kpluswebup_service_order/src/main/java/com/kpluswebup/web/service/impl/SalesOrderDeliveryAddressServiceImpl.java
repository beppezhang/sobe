package com.kpluswebup.web.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.order.dao.SalesOrderDeliveryAddressDAO;
import com.kpluswebup.web.service.SalesOrderDeliveryAddressService;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;

import static org.springframework.util.Assert.notNull;
@Service
public class SalesOrderDeliveryAddressServiceImpl implements SalesOrderDeliveryAddressService {

    private static final Logger LOGGER = LogManager.getLogger(SalesOrderDeliveryAddressServiceImpl.class);
    
    @Autowired
    private SalesOrderDeliveryAddressDAO     salesOrderDeliveryAddressDAO;
    
    public void updateBySalesOrderIDSelective(SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO) {
        try {
            notNull(salesOrderDeliveryAddressDTO, "salesOrderDeliveryAddressDTO is null");
            salesOrderDeliveryAddressDAO.updateBySalesOrderDeliveryAddressByOrderID(salesOrderDeliveryAddressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

	@Override
	public SalesOrderDeliveryAddressVO findSalesOrderDeliveryAddressByID(
			String orderID) {
		return salesOrderDeliveryAddressDAO.findSalesOrderDeliveryAddressByOrderID(orderID);
	}

	@Override
	public void updateSalesOrderDeliveryAddressByOrderID(
			SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO) {
		salesOrderDeliveryAddressDAO.updateBySalesOrderDeliveryAddressByOrderID(salesOrderDeliveryAddressDTO);
	}

	@Override
	public void addSalesOrderDeliveryAddress(
			SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO) {
		salesOrderDeliveryAddressDAO.insertSalesOrderDeliveryAddress(salesOrderDeliveryAddressDTO);
	}

}
