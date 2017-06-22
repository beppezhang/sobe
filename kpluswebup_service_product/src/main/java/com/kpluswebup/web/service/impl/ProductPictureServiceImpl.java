package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.ProductPictureDTO;
import com.kpluswebup.web.product.dao.ProductPictureDAO;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.vo.ProductPictureVO;

@Service
public class ProductPictureServiceImpl implements ProductPictureService {

    @Autowired
    private ProductPictureDAO productPictureDAO;

    public List<ProductPictureVO> findProductPictureByProductID(String itemID, String productID) {
        notNull(productID, "productID is null");
        ProductPictureDTO productPictureDTO = new ProductPictureDTO();
        productPictureDTO.setItemID(itemID);
        productPictureDTO.setProductID(productID);
        return productPictureDAO.findProductPicture(productPictureDTO);
    }

    public Integer deleteProductPictureById(String id) {
        notNull(id, "id is null");
        return productPictureDAO.deleteProductPictureById(Long.parseLong(id));
    }

    public Integer insertProductPicture(String name, String productID, String itemID, String picURL,
                                        String currentOperator) {
        ProductPictureDTO productPictureDTO = new ProductPictureDTO();
        productPictureDTO.setProductID(productID);
        productPictureDTO.setPicURL(picURL);
        productPictureDTO.setItemID(itemID);
        productPictureDTO.setName(name);
        productPictureDTO.setCreator(currentOperator);
        return productPictureDAO.insertProductPicture(productPictureDTO);
    }
}
