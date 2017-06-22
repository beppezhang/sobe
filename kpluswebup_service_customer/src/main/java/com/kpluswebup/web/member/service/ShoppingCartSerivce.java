package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.vo.ShoppingCartVO;

public interface ShoppingCartSerivce {

    /**
     * 添加购物车
     * 
     * @date 2015年1月3日
     * @author liudanqi
     * @param shoppingCartDTO
     * @since JDK 1.6
     * @Description
     */
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

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
    public Boolean delShoppingCartByID(Long id);

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
    public Boolean delShoppingCartAll(String customerID);

}
