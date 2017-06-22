package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.domain.ProductTypeBrandDTO;
import com.kpluswebup.web.product.dao.ProductTypeBrandDAO;
import com.kpluswebup.web.service.ProductTypeBrandService;
import com.kpluswebup.web.vo.ProductTypeBrandVO;

@Service
public class ProductTypeBrandServiceImpl implements ProductTypeBrandService {

    @Autowired
    private ProductTypeBrandDAO productTypeBrandDAO;

    public List<ProductTypeBrandVO> findProductTypeBrandByPtid(String productTypeMainID) {
        notNull(productTypeMainID, "productTypeMainID is null");
        ProductTypeBrandDTO productTypeBrandDTO = new ProductTypeBrandDTO();
        productTypeBrandDTO.setproductTypeID(productTypeMainID);
        return productTypeBrandDAO.findProductTypeBrand(productTypeBrandDTO);
    }

    @Transactional
    public void addProductTypeBrand(String productTypeID, String brandIDs,String currentOperator) {
        notNull(brandIDs, "brandIDs is null");
        notNull(productTypeID, "productTypeID is null");
        String[] brandID = brandIDs.split(",");
        for (String str : brandID) {
            ProductTypeBrandDTO productTypeBrandDTO = new ProductTypeBrandDTO();
            productTypeBrandDTO.setbrandID(str);
            productTypeBrandDTO.setproductTypeID(productTypeID);
            productTypeBrandDTO.setCreator(currentOperator);
            productTypeBrandDAO.insertProductTypeBrand(productTypeBrandDTO);
        }

    }

    public Boolean deleteProductTypeBrandByID(String id) {
        notNull(id, "id is null");
        try {
            Integer count = productTypeBrandDAO.deleteProductTypeBrandById(Long.parseLong(id));
            if (1 == count) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
