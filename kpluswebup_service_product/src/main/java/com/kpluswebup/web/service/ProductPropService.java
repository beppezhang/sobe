package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductPropValueDTO;
import com.kpluswebup.web.vo.ItemPropValueVO;
import com.kpluswebup.web.vo.ProductPropVO;
import com.kpluswebup.web.vo.ProductPropValueVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;

public interface ProductPropService {

    /**
     * 删除一条记录
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean isDeleteProductProp(String mainID);

    /**
     * 新增一条
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param ProductPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void addProductProp(ProductPropDTO productPropDTO);

    /**
     * 编辑产品类型
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param ProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatProductProp(ProductPropDTO productPropDTO);

    /**
     * 分页查询
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param ProductPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPropVO> findProductPropByPagination(ProductPropDTO productPropDTO);

    /**
     * 查询单条产品信息
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductPropVO findProductPropByMainID(String mainID);

    /**
     * 删除一个产品属性值
     * 
     * @date 2014年10月30日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteProductPropVale(String mainID);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param productTypeMainID
     * @param productPropID
     * @param productPropName
     * @param productPropValue
     * @since JDK 1.6
     * @Description
     */
    public void addProductTypeProductProp(String productTypeMainID, String productPropID, String productPropName,
                                          String productPropValue, String productPropMainID, String currentOperator);

    /**
     * @date 2015年5月29日
     * @author wanghehua
     * @param productTypeMainID
     * @param productPropID
     * @param productPropName
     * @param productPropValue
     * @param productPropMainID
     * @param currentOperator
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void editProductTypeProductProp(String productTypeMainID, String productPropID, String productPropName,
                                           String productPropValue, String productPropMainID, String currentOperator,
                                           String mainID);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeProductPropVO> findProductTypeProductProp(String productTypeMainID);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteProductTypeProductProp(String mainID);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param productPropValueDTO
     * @since JDK 1.6
     * @Description
     */
    public void saveProductPropValue(ProductPropValueDTO productPropValueDTO);

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param productTypeMainID
     * @param itemPropID
     * @param itemPropName
     * @param string
     * @since JDK 1.6
     * @Description
     */
    public void addProductTypeItemProp(String productTypeMainID, String itemPropID, String itemPropName,
                                       String itemPropValue, String itemPropValueID, String currentOperator);

    /**
     * @date 2015年5月27日
     * @author wanghehua
     * @param productTypeMainID
     * @param itemPropID
     * @param itemPropName
     * @param itemPropValue
     * @param itemPropValueID
     * @param currentOperator
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void editProductTypeItemProp(String productTypeMainID, String itemPropID, String itemPropName,
                                        String itemPropValue, String itemPropValueID, String currentOperator,
                                        String mainID);

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeItemPropVO> findProductTypeItemProp(String productTypeMainID);

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteProductTypeItemtProp(String mainID);

    /**
     * @date 2014年12月30日
     * @author zhuhp
     * @param catID
     * @param brandID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeProductPropVO> seachProductprop(String catID, String brandID);

    /**
     * @date 2015年5月29日
     * @author wanghehua
     * @param productPropID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPropValueVO> findAllProductPropValueByProductPropMianID(String productPropID);
}
