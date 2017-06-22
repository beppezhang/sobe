package com.kpluswebup.web.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;

public interface CustomerAddressService {

    /**
     * 收货地址列表
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerDeliveryAddressVO> findAddressByCustomerID(String customerID, Integer type);

    /**
     * 删除收货地址
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteAddressByPrimaryKey(Long id);
    
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
     * 查找会员默认地址
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerDeliveryAddressVO findDefaultAddressByCustomerID(String customerID, Integer type);

    /**
     * 修改会员默认地址为非默认
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void editDefaultAddressByID(Long id);

    /**
     * 设置默认地址
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void editIsDefaultAddressByID(Long id);

    /**
     * 根据id查找收货地址
     * 
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
     * 添加收货地址
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void addAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);

    /**
     * 修改收货地址
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param customerDeliveryAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void editAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);

    /**
     * 分页查找收货地址
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @param customerDeliveryAddressVO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerDeliveryAddressVO> findDeliveryAddressByPagination(CustomerDeliveryAddressDTO customerDeliveryAddressDTO);
    
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

	/**
	 * 
	 * @date 2015年6月5日
	 * @author moo
	 * @param mainID
	 * @param i
	 * @return
	 * @return List<CustomerDeliveryAddressVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CustomerDeliveryAddressVO> findPassAddressByCustomerID(
			String mainID, int i);
   
}
