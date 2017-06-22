package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FocusEntity;

public interface FocusService {
	
	public List<FocusEntity> findByFocusEntity(FocusEntity focusEntity);
	
	public FocusEntity save(FocusEntity focusEntity);
	
//	public FocusEntity save(FocusEntity focusEntity,CustomerVO customer);	//为方便查找已存在信息，这里先屏蔽这个接口
	
	public FocusEntity save(String myFocusInfo,String referenceID,CustomerVO customer,Integer focusType);
}
