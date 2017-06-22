package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.vo.ShoppingCartVO;

public interface ShoppingCartDAO {

    public Integer deleteByPrimaryKey(Long id);

    public Integer insert(ShoppingCartDTO record);

    public Integer insertSelective(ShoppingCartDTO record);

    ShoppingCartDTO selectByPrimaryKey(Long id);

    public Integer updateByPrimaryKeySelective(ShoppingCartDTO record);

    public Integer updateByPrimaryKey(ShoppingCartDTO record);

    /**
     * 向购物车添加数据
     * 
     * @date 2015年1月3日
     * @author liudanqi
     * @param shoppingCartDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 修改购物车
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param shoppingCartDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询购物车
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ShoppingCartVO> findShoppingCart(String customerID);

    /**
     * 查询用户加入购物车商品
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param shoppingCartDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShoppingCartVO findShoppingCartByCustomerItem(ShoppingCartDTO shoppingCartDTO);

    /**
     * 根据id查询购物车
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ShoppingCartVO findShoppingCartByID(Long id);

    /**
     * 删除购物车商品
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer delShoppingCartByID(Long id);

    /**
     * 清空购物车
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer delShoppingCartAll(String customerID);

}
