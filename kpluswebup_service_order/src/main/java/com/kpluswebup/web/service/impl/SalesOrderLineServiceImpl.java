package com.kpluswebup.web.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SupplierItemIDDTO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.product.dao.SupplierItemIDDAO;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.vo.SalesOrderLineVO;

import static org.springframework.util.Assert.notNull;

@Service
public class SalesOrderLineServiceImpl implements SalesOrderLineService {

    private static final Logger LOGGER = LogManager.getLogger(SalesOrderServiceImpl.class);
    
    @Autowired
    private SalesOrderLineDAO salesOrderLineDAO;
    @Autowired
    private SupplierItemIDDAO supplierItemIDDAO;

    public void updateByPrimaryKeySelective(SalesOrderLineDTO salesOrderLineDTO) {
        try {
            notNull(salesOrderLineDTO, "salesOrderLineDTO is null");
            salesOrderLineDAO.updateByPrimaryKeySelective(salesOrderLineDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

	@Override
	public void updateSalesOrderLineByID(SalesOrderLineDTO salesOrderLineDTO) {
		salesOrderLineDAO.updateSalesOrderLineByID(salesOrderLineDTO);
	}

	@Override
	public SalesOrderLineVO findSalesOrderLineByID(Long id) {
		return salesOrderLineDAO.findSalesOrderLineByID(id);
	}

    @Override
    public List<SalesOrderLineVO> findSalesOrderLine(String orderID) {
        return salesOrderLineDAO.findSalesOrderLine(orderID);
    }
    /**
     * 根据商品id查询销售记录
     * @param productID
     * @author zhoulei
     * @date 2015年5月11日
     * @return
     */
    @Override
    public List<SalesOrderLineVO> findSalesOrderLineByProductId(String productID){
    	return salesOrderLineDAO.findSalesOrderLineByProductId(productID);
    }

    @Override
    public void deleteSupplierItemIDBySalesOrderLineID(String salesOrderLineId) {
        supplierItemIDDAO.deleteSupplierItemIDBySalesOrderLineID(salesOrderLineId);
    }

    @Override
    public void addSupplierItemID(SupplierItemIDDTO supplierItemIDDTO) {
       supplierItemIDDAO.addSupplierItemID(supplierItemIDDTO);
        
    }
    
    @Override
    public List<SalesOrderLineDTO> findSalesOrderLineByIDTparts(String orderID) {
    	return salesOrderLineDAO.findSalesOrderLineByIDTparts(orderID);
    }
}
