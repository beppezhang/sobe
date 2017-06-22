package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.CustomerGradeDAO;
import com.kpluswebup.web.domain.CustomerGradeDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.vo.CustomerGradeVO;

@Service
public class CustomerGradeSerivceImpl implements CustomerGradeSerivce {

	private static final Logger LOGGER = LogManager
			.getLogger(CustomerGradeSerivceImpl.class);

	@Autowired
	private CustomerGradeDAO customerGradeDAO;

	public List<CustomerGradeVO> findAllCustomerGrade() {
		try {
			return customerGradeDAO.findAllCustomerGrade();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<CustomerGradeVO> findCustomerGradeByPagination(
			CustomerGradeDTO customerGradeDTO) {
	    //NotNull(customerGradeDTO, "customerGradeDTO is null");
		Long count = customerGradeDAO.findCustomerGradeCount(customerGradeDTO);
		customerGradeDTO.doPage(count, customerGradeDTO.getPageNo(),
				customerGradeDTO.getPageSize());
		List<CustomerGradeVO> list = customerGradeDAO
				.findCustomerGradeByPagination(customerGradeDTO);
		return list;
	}

	@Override
	public void addCustomerGrade(CustomerGradeDTO customerGradeDTO) {
		customerGradeDAO.insertCustomerGrade(customerGradeDTO);
	}

	@Override
	public CustomerGradeVO findCustomerGradeByMainID(String mainID) {
		return customerGradeDAO.findCustomerGradeByMainID(mainID);
	}

	@Override
	public void editCustomerGrade(CustomerGradeDTO customerGradeDTO) {
		customerGradeDAO.updateCustomerGradeByMainID(customerGradeDTO);
	}

	@Override
	public Boolean deleteCustomerGradeByMainID(String mainIds) {
		try {
			String ids[] = mainIds.split(",");
			for (String mainId : ids) {
				customerGradeDAO.deleteCustomerGradeByMainID(mainId);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CustomerGradeVO findCustomerGradeTypedefault() {
		return customerGradeDAO.findCustomerGradeTypedefault();
	}

}
