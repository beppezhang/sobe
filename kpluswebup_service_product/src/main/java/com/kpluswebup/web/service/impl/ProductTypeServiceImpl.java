package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.product.dao.ProductDetailDAO;
import com.kpluswebup.web.product.dao.ProductTypeDAO;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductTypeVO;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDAO productTypeDAO;
    @Autowired
    private ProductDetailDAO  productDetailDAO;

    public Boolean isDeleteProductType(String mainID) {
        notNull(mainID, "mainID is null");
        try {
            productTypeDAO.deleteProductTypeById(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addProductType(ProductTypeDTO productTypeDTO) {
        notNull(productTypeDTO, "productTypeDTO is null");
        productTypeDAO.insertProductType(productTypeDTO);

    }

    public void updatProductType(ProductTypeDTO productTypeDTO) {
        notNull(productTypeDTO, "productTypeDTO is null");
        notNull(productTypeDTO.getmainID(), "mainID is null");
        productTypeDAO.updatProductTypeByMainID(productTypeDTO);

    }

    public List<ProductTypeVO> findProductTypeByPagination(ProductTypeDTO productTypeDTO) {
        notNull(productTypeDTO, "productTypeDTO is null");
        productTypeDTO.doPage(productTypeDAO.findProductTypeCount(productTypeDTO), productTypeDTO.getPageNo(),
                              productTypeDTO.getPageSize());
        return productTypeDAO.findProductTypeByPagination(productTypeDTO);

    }

    public ProductTypeVO findProductTypeByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return productTypeDAO.findProductTypeByMainID(mainID);
    }
    /**
     * 通过productMainID c查询已经选中的
     * @param productMainID
     * @return
     */
    public List<ProductDetailVO> findProductDetailsByProductMainID(String productMainID){
    	return productDetailDAO.findProductDetailsByProductMainID(productMainID);
    }

}
