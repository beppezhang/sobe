package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.ShoppingCartDAO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ShoppingCartVO;

@Service
public class ShoppingCartSerivceImpl implements ShoppingCartSerivce {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    @Autowired
    private ItemDAO         itemDAO;

    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        shoppingCartDAO.insertShoppingCart(shoppingCartDTO);
    }

    @Override
    public List<ShoppingCartVO> findShoppingCart(String customerID) {
        List<ShoppingCartVO> list = shoppingCartDAO.findShoppingCart(customerID);
        if (list != null && list.size() > 0) {
            for (ShoppingCartVO shoppingCartVO : list) {
                List<ItemDetailVO> props = itemDAO.findItemDetailByItemID(shoppingCartVO.getItemID());
                if (props != null && props.size() > 0) {
                    String itemProp = "";
                    for (ItemDetailVO prop : props) {
                        itemProp += prop.getItemPropName() + ":" + prop.getItemPropValue() + " ";
                    }
                    shoppingCartVO.setItemProp(itemProp);
                }
            }
        }
        return list;
    }

    @Override
    public void updateShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        shoppingCartDAO.updateShoppingCart(shoppingCartDTO);

    }

    @Override
    public ShoppingCartVO findShoppingCartByCustomerItem(ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartDAO.findShoppingCartByCustomerItem(shoppingCartDTO);
    }

    @Override
    public ShoppingCartVO findShoppingCartByID(Long id) {
        ShoppingCartVO shoppingCartVO = shoppingCartDAO.findShoppingCartByID(id);
        if(shoppingCartVO == null)
        	return null;
        List<ItemDetailVO> props = itemDAO.findItemDetailByItemID(shoppingCartVO.getItemID());
        if (props != null && props.size() > 0) {
            String itemProp = "";
            for (ItemDetailVO prop : props) {
                itemProp += prop.getItemPropName() + ":" + prop.getItemPropValue() + " ";
            }
            shoppingCartVO.setItemProp(itemProp);
        }
        return shoppingCartVO;
    }

    @Override
    public Boolean delShoppingCartByID(Long id) {
        try {
            shoppingCartDAO.delShoppingCartByID(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delShoppingCartAll(String customerID) {
        try {
            shoppingCartDAO.delShoppingCartAll(customerID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
