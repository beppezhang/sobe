package com.kpluswebup.web.customer.dao;

import com.kpluswebup.web.domain.CustomerGroupSetDTO;


public interface CustomerGroupSetDAO {
    public Integer deleteByPrimaryKey(String groupid);

    public Integer insert(CustomerGroupSetDTO record);

    public Integer insertSelective(CustomerGroupSetDTO record);

    CustomerGroupSetDTO selectByPrimaryKey(String groupid);

    public Integer updateByPrimaryKeySelective(CustomerGroupSetDTO record);

    public Integer updateByPrimaryKey(CustomerGroupSetDTO record);
}