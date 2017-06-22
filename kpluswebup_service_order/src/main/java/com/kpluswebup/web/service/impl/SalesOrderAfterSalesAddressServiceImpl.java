package com.kpluswebup.web.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.SalesOrderAfterSalesAddressDTO;
import com.kpluswebup.web.order.dao.SalesOrderAfterSalesAddressDAO;
import com.kpluswebup.web.service.SalesOrderAfterSalesAddressService;

@Service
public class SalesOrderAfterSalesAddressServiceImpl implements SalesOrderAfterSalesAddressService {

    private static final Logger LOGGER = LogManager.getLogger(SalesOrderAfterSalesAddressServiceImpl.class);
    
    @Autowired
    private SalesOrderAfterSalesAddressDAO     salesOrderAfterSalesAddressDAO;

	@Override
	public void addSalesOrderAfterSalesAddressDTO(
			SalesOrderAfterSalesAddressDTO salesOrderAfterSalesAddressDTO) {
		salesOrderAfterSalesAddressDAO.addSalesAddressSelective(salesOrderAfterSalesAddressDTO);
		
	}
    

}