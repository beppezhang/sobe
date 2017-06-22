package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductPropValueDTO;
import com.kpluswebup.web.domain.ProductTypeItemPropDTO;
import com.kpluswebup.web.domain.ProductTypeProductPropDTO;
import com.kpluswebup.web.product.dao.ProductPropDAO;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.vo.ProductPropVO;
import com.kpluswebup.web.vo.ProductPropValueVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpluswebup.web.vo.PropValueVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class ProductPropServiceImpl implements ProductPropService {

    @Autowired
    private ProductPropDAO productPropDAO;

    @Transactional
    public Boolean isDeleteProductProp(String mainID) {
        notNull(mainID, "mainID is null");
        try {
            productPropDAO.deleteProductPropByMainID(mainID);
            productPropDAO.deleteProductPropValueByProductPropID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addProductProp(ProductPropDTO productPropDTO) {
        notNull(productPropDTO, "ProductPropDTO is null");
        productPropDAO.insertProductProp(productPropDTO);

    }

    public void updatProductProp(ProductPropDTO productPropDTO) {
        notNull(productPropDTO, "ProductPropDTO is null");
        notNull(productPropDTO.getMainID(), "mainID is null");
        productPropDAO.updateProductProp(productPropDTO);

    }

    public List<ProductPropVO> findProductPropByPagination(ProductPropDTO productPropDTO) {
        notNull(productPropDTO, "productPropDTO is null");
        productPropDTO.doPage(productPropDAO.findProductPropCount(productPropDTO), productPropDTO.getPageNo(),
                              productPropDTO.getPageSize());

        // TODO 查询次数太多不合理 需要改进
        List<ProductPropVO> list = productPropDAO.findProductPropByPagination(productPropDTO);
        for (ProductPropVO productPropVO : list) {
            List<ProductPropValueVO> productPropValuelist = productPropDAO.findProductPropValueByMainID(productPropVO.getMainID());
            productPropVO.setProductPropValue(productPropValuelist);
            productPropVO.setProductPropValues(productPropValuelist);
        }
        return list;

    }

    public ProductPropVO findProductPropByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return productPropDAO.findProductPropByMainID(mainID);
    }

    public Boolean deleteProductPropVale(String mainID) {
        notNull(mainID, "productPropID is null");
        try {
            productPropDAO.deleteProductPropValueByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addProductTypeProductProp(String productTypeMainID, String productPropID, String productPropName,
                                          String productPropValue, String productPropMainID, String currentOperator) {
        notNull(productTypeMainID, "productTypeMainID is null");
        notNull(productPropID, "productPropID is null");
        notNull(productPropName, "productPropName is null");
        notNull(productPropValue, "productPropValue is null");
        ProductTypeProductPropDTO productTypeProductPropDTO = new ProductTypeProductPropDTO();
        productTypeProductPropDTO.setproductTypeID(productTypeMainID);
        productTypeProductPropDTO.setName(productPropName);
        productTypeProductPropDTO.setproductPropValue(productPropValue);
        productTypeProductPropDTO.setmainID(UUIDUtil.getUUID());
        productTypeProductPropDTO.setproductPropID(productPropID);
        productTypeProductPropDTO.setProductPropValueID(productPropMainID);
        productTypeProductPropDTO.setCreator(currentOperator);
        productPropDAO.insertpProductTypeProductProp(productTypeProductPropDTO);

    }
    public void editProductTypeProductProp(String productTypeMainID, String productPropID, String productPropName,
                                          String productPropValue, String productPropMainID, String currentOperator,String mainID) {
        notNull(productTypeMainID, "productTypeMainID is null");
        notNull(productPropID, "productPropID is null");
        notNull(productPropName, "productPropName is null");
        notNull(productPropValue, "productPropValue is null");
        notNull(mainID, "mainID is null");
        ProductTypeProductPropDTO productTypeProductPropDTO = new ProductTypeProductPropDTO();
        productTypeProductPropDTO.setproductTypeID(productTypeMainID);
        productTypeProductPropDTO.setName(productPropName);
        productTypeProductPropDTO.setproductPropValue(productPropValue);
        productTypeProductPropDTO.setmainID(mainID);
        productTypeProductPropDTO.setproductPropID(productPropID);
        productTypeProductPropDTO.setProductPropValueID(productPropMainID);
        productTypeProductPropDTO.setModifier(currentOperator);
        productPropDAO.updatepProductTypeProductProp(productTypeProductPropDTO);
        
    }

    public List<ProductTypeProductPropVO> findProductTypeProductProp(String productTypeMainID) {
        notNull(productTypeMainID, "ProductPropDTO is null");
        ProductTypeProductPropDTO productTypeProductPropDTO = new ProductTypeProductPropDTO();
        productTypeProductPropDTO.setproductTypeID(productTypeMainID);
        List<ProductTypeProductPropVO> list = productPropDAO.findProductTypeProductProp(productTypeProductPropDTO);

        return list;
    }

    public Boolean deleteProductTypeProductProp(String mainID) {
        notNull(mainID, "mainID is null");
        try {
            productPropDAO.deleteProductTypeProductProp(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public void saveProductPropValue(ProductPropValueDTO productPropValueDTO) {
        productPropDAO.saveProductPropValue(productPropValueDTO);

    }

    public void addProductTypeItemProp(String productTypeMainID, String itemPropID, String itemPropName,
                                       String itemPropValue, String itemPropValueID, String currentOperator) {
        notNull(productTypeMainID, "productTypeMainID is null");
        notNull(itemPropID, "itemPropID is null");
        notNull(itemPropName, "itemPropName is null");
        notNull(itemPropValue, "itemPropValue is null");
        ProductTypeItemPropDTO productTypeProductPropDTO = new ProductTypeItemPropDTO();
        productTypeProductPropDTO.setProductTypeID(productTypeMainID);
        productTypeProductPropDTO.setName(itemPropName);
        productTypeProductPropDTO.setItemPropValue(itemPropValue);
        productTypeProductPropDTO.setMainID(UUIDUtil.getUUID());
        productTypeProductPropDTO.setItemPropID(itemPropID);
        productTypeProductPropDTO.setItemPropValueID(itemPropValueID);
        productTypeProductPropDTO.setCreator(currentOperator);
        productPropDAO.insertpProductTypeItemProp(productTypeProductPropDTO);
    }
    
    public void editProductTypeItemProp(String productTypeMainID, String itemPropID, String itemPropName,
                                        String itemPropValue, String itemPropValueID,String mainID, String currentOperator) {
         notNull(productTypeMainID, "productTypeMainID is null");
         notNull(itemPropID, "itemPropID is null");
         notNull(itemPropName, "itemPropName is null");
         notNull(itemPropValue, "itemPropValue is null");
         notNull(mainID, "mainID is null");
         ProductTypeItemPropDTO productTypeProductPropDTO = new ProductTypeItemPropDTO();
         productTypeProductPropDTO.setProductTypeID(productTypeMainID);
         productTypeProductPropDTO.setName(itemPropName);
         productTypeProductPropDTO.setItemPropValue(itemPropValue);
         productTypeProductPropDTO.setItemPropID(itemPropID);
         productTypeProductPropDTO.setItemPropValueID(itemPropValueID);
         productTypeProductPropDTO.setModifier(currentOperator);
         productTypeProductPropDTO.setMainID(mainID);
         productPropDAO.updatepProductTypeItemProp(productTypeProductPropDTO);
     }

    public List<ProductTypeItemPropVO> findProductTypeItemProp(String productTypeMainID) {
        notNull(productTypeMainID, "ProductPropDTO is null");
        ProductTypeItemPropDTO productTypeItemPropDTO = new ProductTypeItemPropDTO();
        productTypeItemPropDTO.setProductTypeID(productTypeMainID);
        List<ProductTypeItemPropVO> list = productPropDAO.findProductTypeItemProp(productTypeItemPropDTO);
        List<ProductTypeItemPropVO> newList = new ArrayList<ProductTypeItemPropVO>();
        for (ProductTypeItemPropVO productTypeItemPropVO : list) {
            productTypeItemPropVO.setItemPropValueVO(productTypeItemPropVO.getItemPropValueID(),
                                                     productTypeItemPropVO.getItemPropValue());
            newList.add(productTypeItemPropVO);
        }
        return newList;
    }

    public Boolean deleteProductTypeItemtProp(String mainID) {
        notNull(mainID, "mainID is null");
        try {
            productPropDAO.deleteProductTypeItemProp(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductTypeProductPropVO> seachProductprop(String catID, String brandID) {
        if (StringUtil.isEmpty(catID) && StringUtil.isEmpty(brandID)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        if (!StringUtil.isEmpty(catID)) {
            map.put("catID", catID);
        }
        if (!StringUtil.isEmpty(brandID)) {
            map.put("brandID", brandID);
        }
        List<ProductTypeProductPropVO> list = productPropDAO.seachProductprop(map);
        for (ProductTypeProductPropVO productTypeProductPropVO : list) {
            List<PropValueVO> propValueList = new ArrayList<PropValueVO>();
            String productPropValue = productTypeProductPropVO.getproductPropValue();
            String productPropValueID = productTypeProductPropVO.getProductPropValueID();
            if (productPropValue != null && productPropValueID != null) {
                String[] productPropValues = productPropValue.split(",");
                String[] productPropValueIDs = productPropValueID.split(",");
                for (int i = 0; i < productPropValueIDs.length; i++) {
                    PropValueVO propValueVO = new PropValueVO();
                    propValueVO.setPropID(productPropValueIDs[i]);
                    propValueVO.setPropValue(productPropValues[i]);
                    propValueList.add(propValueVO);
                }
            }
            productTypeProductPropVO.setPropValueList(propValueList);

        }

        return list;
    }

    @Override
    public List<ProductPropValueVO> findAllProductPropValueByProductPropMianID(String productPropID) {
        return productPropDAO.findAllProductPropValueByProductPropMianID(productPropID);
    }

}
