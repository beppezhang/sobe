package com.kpluswebup.web.member.service;

import com.kpluswebup.web.vo.CustomerVO;

public interface AuthService {

	public CustomerVO login(String userName, String password);

	public String logout();

	public String refresh(String token);
}
