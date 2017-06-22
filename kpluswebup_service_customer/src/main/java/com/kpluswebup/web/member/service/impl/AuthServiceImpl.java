package com.kpluswebup.web.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.member.service.AuthService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.CustomerVO;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private MemberSerivce memberSerivce;
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public CustomerVO login(String userName, String password) {
		CustomerVO customerVO = customerDAO.findCusertomerByUserNameNOStatus(userName);
		if (customerVO == null) {
			return null;
		}
		return customerVO;
//		if (Md5Algorithm.getInstance().md5Digest(password.getBytes())
//				.equals(customerVO.getPassword())) {
//			return customerVO;
//		}else{
//			return customerVO;
//		}
	}

	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String refresh(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
