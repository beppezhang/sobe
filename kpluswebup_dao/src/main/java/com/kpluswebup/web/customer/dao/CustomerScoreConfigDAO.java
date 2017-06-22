package com.kpluswebup.web.customer.dao;

import com.kpluswebup.web.domain.CustomerScoreConfigDTO;


public interface CustomerScoreConfigDAO {
    public Integer deleteByPrimaryKey(Long id);

    public Integer insert(CustomerScoreConfigDTO record);

    public Integer insertSelective(CustomerScoreConfigDTO record);

    CustomerScoreConfigDTO selectByPrimaryKey(Long id);

    public Integer updateByPrimaryKeySelective(CustomerScoreConfigDTO record);

    public Integer updateByPrimaryKey(CustomerScoreConfigDTO record);
}