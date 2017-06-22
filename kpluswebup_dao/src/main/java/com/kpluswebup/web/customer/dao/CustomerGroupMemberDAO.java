package com.kpluswebup.web.customer.dao;

import com.kpluswebup.web.domain.CustomerGroupMemberDTO;

public interface CustomerGroupMemberDAO {
    public Integer deleteByPrimaryKey(Long id);

    public Integer insert(CustomerGroupMemberDTO record);

    public Integer insertSelective(CustomerGroupMemberDTO record);

    CustomerGroupMemberDTO selectByPrimaryKey(Long id);

    public Integer updateByPrimaryKeySelective(CustomerGroupMemberDTO record);

    public Integer updateByPrimaryKey(CustomerGroupMemberDTO record);
}