package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;


public interface CustomerDeliveryAddressDAO {
	
	/**
	 * 删除收货地址
	 * @date 2014年11月12日
	 * @author wanghehua
	 * @param id
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
    public Integer deleteByPrimaryKey(Long id);
    
    /**
     * 查找会员的默认地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerDeliveryAddressVO findDefaultAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
    
    /**
     * 修改默认地址为非默认
     * @date 2014年11月12日
     * @author wanghehua
     * @param addressId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateDefaultAddress(Long addressId);
    
    /**
     * 设置默认地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param addressId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateIsDefaultAddress(Long addressId);

    public Integer insert(CustomerDeliveryAddressDTO record);

    public Integer insertSelective(CustomerDeliveryAddressDTO record);

    CustomerDeliveryAddressDTO selectByPrimaryKey(Long id);

    public Integer updateByPrimaryKeySelective(CustomerDeliveryAddressDTO record);

    public Integer updateByPrimaryKey(CustomerDeliveryAddressDTO record);
    
    /**
     * 查找会员收货地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerDeliveryAddressVO> findAddressByCustomerID(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
    
    /**
     * 添加收货地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
    
    /**
     * 编辑收货地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
    
    /**
     * 根据id查找收货地址
     * @date 2014年11月12日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerDeliveryAddressVO findAddressByID(Long id);
    
    public CustomerDeliveryAddressVO findValidAddressByID(Long id);
    
	/**
	 * 分页查找收货地址
	 * @date 2014年12月2日
	 * @author wanghehua
	 * @param customerDeliveryAddressVO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CustomerDeliveryAddressVO> findDeliveryAddressByPagination(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
	
	/**
	 * 查询总条数
	 * @date 2014年12月2日
	 * @author wanghehua
	 * @param customerDeliveryAddressVO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findDeliveryAddressCount(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
	
	/**
	 * 
	 * @date 2015年4月16日
	 * @author yuanyuan
	 * @param customerDeliveryAddressDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void changeDeliveryAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
	
	/**
	 * 
	 * @date 2015年5月12日
	 * @author moo
	 * @param mainID
	 * @return void
	 * @since JDK 1.6
	 * @Description 设置会员所有收货地址非默认
	 */
	public void updateAddressNotDefaultByCustomerMainID(String mainID);
}