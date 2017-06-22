package com.kpluswebup.web.admin.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.FreightTemplatePriceDAO;
import com.kpluswebup.web.admin.system.service.FreightTemplatePriceService;
import com.kpluswebup.web.customer.dao.CustomerDeliveryAddressDAO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaSetDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceDTO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaSetVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;

@Service
public class FreightTemplatePriceServiceImpl implements FreightTemplatePriceService {

	@Autowired
	private FreightTemplatePriceDAO freightTemplatePriceDAO;
	
	@Autowired
	private CustomerDeliveryAddressDAO customerDeliveryAddressDAO;

	@Override
	public List<FreightTemplatePriceVO> findFreightTemplatePriceByFTID(
			String freightTemplateID) {
		List<FreightTemplatePriceVO> ftplist=freightTemplatePriceDAO.findFreightTemplatePriceByFTID(freightTemplateID);
		if (ftplist != null && ftplist.size() > 0) {
			for (FreightTemplatePriceVO freightTemplatePriceVO : ftplist) {
				FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO=new FreightTemplatePriceAreaSetDTO();
				freightTemplatePriceAreaSetDTO.setfTPriceID(freightTemplatePriceVO.getMainID());
				List<FreightTemplatePriceAreaSetVO> alist = freightTemplatePriceDAO
						.findFreightTemplatePriceAreaSetByFTPID(freightTemplatePriceAreaSetDTO);
				freightTemplatePriceVO.setPriceareasetList(alist);
			}
		}
		return ftplist;
	}

	@Override
	public void addFreightTemplatePrice(
			FreightTemplatePriceDTO freightTemplatePriceDTO) {
		freightTemplatePriceDAO.insertFreightTemplatePrice(freightTemplatePriceDTO);
		
	}

	@Override
	public Integer deleteFreightTemplatePriceByFTID(String freightTemplateID) {
		return freightTemplatePriceDAO.deleteFreightTemplatePriceByFTID(freightTemplateID);
	}

	@Override
	public void addFreightTemplatePriceAreaSet(
			FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO) {
		freightTemplatePriceDAO.insertFreightTemplatePriceAreaSet(freightTemplatePriceAreaSetDTO);
	}

	@Override
	public Integer deleteFreightTemplatePriceAreaSet(String ftPriceID) {
		return freightTemplatePriceDAO.deleteFreightTemplatePriceAreaSet(ftPriceID);
	}

	@Override
	public void addFreightTemplatePriceArea(
			FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO) {
		freightTemplatePriceDAO.insertFreightTemplatePriceArea(freightTemplatePriceAreaDTO);
	}

	@Override
	public Integer deleteFreightTemplatePriceArea(String ftPriceID) {
		return freightTemplatePriceDAO.deleteFreightTemplatePriceArea(ftPriceID);
	}

    @Override
    public FreightTemplatePriceAreaVO findFreightTemplatePriceAreaByCityID(String cityID) {
        return freightTemplatePriceDAO.findFreightTemplatePriceAreaByCityID(cityID);
    }

    @Override
    public FreightTemplatePriceVO findFreightTempatePriceByMainID(String mainID) {
        return freightTemplatePriceDAO.findFreightTempatePriceByMainID(mainID);
    }

	/**
	 * 根据模板与产品查询运费
	 */
	@Override
	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndProduct(
			String productID, CustomerVO userInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mainID", productID);
		CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
		customerDeliveryAddressDTO.setCustomerID(userInfo.getMainID());
		CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findDefaultAddress(customerDeliveryAddressDTO);
		map.put("cityID", customerDeliveryAddressVO.getCityID());
		return freightTemplatePriceDAO.findFreightTemplatePriceByUserAndProduct(map);
	}
	/**
	 * 根据模板与商品查询运费
	 */
	@Override
	public FreightTemplatePriceVO findFreightTemplatePriceByUserAndItem(
			String itemID, CustomerVO userInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mainID", itemID);
		CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
		customerDeliveryAddressDTO.setCustomerID(userInfo.getMainID());
		CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findDefaultAddress(customerDeliveryAddressDTO);
		map.put("cityID", customerDeliveryAddressVO.getCityID());
		return freightTemplatePriceDAO.findFreightTemplatePriceByUserAndItem(map);
	}
	
	@Override
	public FreightTemplatePriceVO findFreightTemplatePriceByCustomerDeliveryAddressAndProduct(
			String productID, String customerDeliveryAddressId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mainID", productID);
        CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findValidAddressByID(Long.parseLong(customerDeliveryAddressId));
		map.put("cityID", customerDeliveryAddressVO.getCityID());
		return freightTemplatePriceDAO.findFreightTemplatePriceByUserAndProduct(map);
	}
	
	@Override
	public FreightTemplatePriceVO findFreightTemplatePriceByCustomerDeliveryAddressAndItem(
			String itemID, String customerDeliveryAddressId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mainID", itemID);
        CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findValidAddressByID(Long.parseLong(customerDeliveryAddressId));
		map.put("cityID", customerDeliveryAddressVO.getCityID());
		return freightTemplatePriceDAO.findFreightTemplatePriceByUserAndItem(map);	
	}
}
