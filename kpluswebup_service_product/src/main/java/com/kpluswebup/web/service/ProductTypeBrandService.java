package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.vo.ProductTypeBrandVO;

public interface ProductTypeBrandService {

    /**
     * 根据产品ID查询关联品牌
     * 
     * @date 2014年11月24日
     * @author zhuhp
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeBrandVO> findProductTypeBrandByPtid(String productTypeMainID);

    /**
     * 根据新增关联品牌
     * 
     * @date 2014年11月24日
     * @author zhuhp
     * @param productTypeID
     * @param brandID
     * @since JDK 1.6
     * @Description
     */
    public void addProductTypeBrand(String productTypeID, String brandID,String currentOperator);

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteProductTypeBrandByID(String id);
}
