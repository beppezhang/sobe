package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.ShippingAddressDTO;
import com.kpluswebup.web.vo.ShippingAddressVO;


public interface ShippingAddressDAO {
    
    /**
     * 查询默认的退货地址
     * @date 2014年11月4日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public ShippingAddressVO findForReturnShippingAddress();
    
    /**
     * 分页查找发货地址
     * @date 2014年11月19日
     * @author wanghehua
     * @param shippingAddressDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ShippingAddressVO> findShippingAddressByPagination(ShippingAddressDTO shippingAddressDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月19日
     * @author wanghehua
     * @param shippingAddressDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findShippingAddressCount(ShippingAddressDTO shippingAddressDTO);
    
    /**
     * 根据id查找发货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShippingAddressVO findShippingAddressByID(Long id);
    
    /**
     * 添加发货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param shippingAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertShippingAddress(ShippingAddressDTO shippingAddressDTO);
    
    /**
     * 修改发货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param shippingAddressDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateShippingAddress(ShippingAddressDTO shippingAddressDTO);
    
    /**
     * 删除发货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteShippingAddress(Long id);
    
    /**
     * 设置默认地址为非默认
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateDefaultShippingAddress(Long id);
    
    /**
     * 设置默认地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateIsDefaultShippingAddress(Long id);
    
    /**
     * 设置默认退货地址为非默认
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateforReturnDefaultShippingAddress(Long id);
    
    /**
     * 设置默认退货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateforReturnIsDefaultShippingAddress(Long id);
    
    /**
     * 查找默认地址
     * @date 2014年11月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShippingAddressVO findDefaultShippingAddress();
    
    /**
     * 查找默认退货地址
     * @date 2014年11月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShippingAddressVO findforReturnDefaultShippingAddress();
    
    /**
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShippingAddressVO findDefaultShippingAddressOrder();
}
