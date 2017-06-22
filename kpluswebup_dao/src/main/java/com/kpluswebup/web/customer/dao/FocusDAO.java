package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.vo.FocusEntity;

public interface FocusDAO {
	//根据用户 id 对应的我的关注信息
	public List<FocusEntity> findFocusByCustomerID(FocusEntity focusEntity);
	
	//插入新的我的关注信息
	public Integer insert(FocusEntity focusEntity);
	
	//更新搜索时间和点击量
    public Integer update(FocusEntity focusEntity);
    
    //根据customerID和referenceID等查对应关注信息(去重用)
    public FocusEntity findExistFocus(FocusEntity focusEntity);
}
