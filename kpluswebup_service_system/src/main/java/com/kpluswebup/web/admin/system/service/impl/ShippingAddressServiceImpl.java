package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.ShippingAddressDAO;
import com.kpluswebup.web.admin.system.service.ShippingAddressService;
import com.kpluswebup.web.domain.ShippingAddressDTO;
import com.kpluswebup.web.vo.ShippingAddressVO;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Autowired
	private ShippingAddressDAO shippingAddressDAO;

	@Override
	public List<ShippingAddressVO> findShippingAddressByPagionation(
			ShippingAddressDTO shippingAddressDTO) {
		Long count = shippingAddressDAO
				.findShippingAddressCount(shippingAddressDTO);
		shippingAddressDTO.doPage(count, shippingAddressDTO.getPageNo(),
				shippingAddressDTO.getPageSize());
		List<ShippingAddressVO> list = shippingAddressDAO
				.findShippingAddressByPagination(shippingAddressDTO);
		return list;
	}

	@Override
	public ShippingAddressVO findShippingAddressByID(Long id) {
		return shippingAddressDAO.findShippingAddressByID(id);
	}

	@Override
	public void addShippingAddress(ShippingAddressDTO shippingAddressDTO) {
		shippingAddressDAO.insertShippingAddress(shippingAddressDTO);
	}

	@Override
	public void updateShippingAddress(ShippingAddressDTO shippingAddressDTO) {
		shippingAddressDAO.updateShippingAddress(shippingAddressDTO);
	}

	@Override
	public Boolean deleteShippingAddress(Long id) {
		try {
			shippingAddressDAO.deleteShippingAddress(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateDefaultShippingAddress(Long id) {
		shippingAddressDAO.updateDefaultShippingAddress(id);
	}

	@Override
	public void updateIsDefaultShippingAddress(Long id) {
		shippingAddressDAO.updateIsDefaultShippingAddress(id);
	}

	@Override
	public void updateforReturnDefaultShippingAddress(Long id) {
		shippingAddressDAO.updateforReturnDefaultShippingAddress(id);
	}

	@Override
	public void updateforReturnIsDefaultShippingAddress(Long id) {
		shippingAddressDAO.updateforReturnIsDefaultShippingAddress(id);
	}

	@Override
	public ShippingAddressVO findDefaultShippingAddress() {
		return shippingAddressDAO.findDefaultShippingAddress();
	}
	@Override
	public ShippingAddressVO findDefaultShippingAddressOrder() {
	    return shippingAddressDAO.findDefaultShippingAddressOrder();
	}

	@Override
	public ShippingAddressVO findforReturnDefaultShippingAddress() {
		return shippingAddressDAO.findforReturnDefaultShippingAddress();
	}

}
