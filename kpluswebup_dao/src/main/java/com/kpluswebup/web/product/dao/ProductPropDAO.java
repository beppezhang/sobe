package com.kpluswebup.web.product.dao;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductPropValueDTO;
import com.kpluswebup.web.domain.ProductTypeItemPropDTO;
import com.kpluswebup.web.domain.ProductTypeProductPropDTO;
import com.kpluswebup.web.vo.ProductPropVO;
import com.kpluswebup.web.vo.ProductPropValueVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;

public interface ProductPropDAO {

    /**
     * 删除一条属性
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductPropByMainID(String mainID);

    /**
     * 插入产品属性
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param record
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductProp(ProductPropDTO productPropDTO);

    /**
     * 修改一个产品属性
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductProp(ProductPropDTO productPropDTO);

    /**
     * 分页查询
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPropVO> findProductPropByPagination(ProductPropDTO productPropDTO);

    /**
     * 总条数
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param ProductPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findProductPropCount(ProductPropDTO productPropDTO);

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
     * 根据mainID查询属性值
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPropValueVO> findProductPropValueByMainID(String productPropID);

    /**
     * 删除一个属性值
     * 
     * @date 2014年10月30日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductPropValueByMainID(String mainID);

    /**
     * 根据彩产品属性删除对应的属性值
     * 
     * @date 2014年10月30日
     * @author zhuhp
     * @param productPropID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductPropValueByProductPropID(String productPropID);

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @param productTypeProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertpProductTypeProductProp(ProductTypeProductPropDTO productTypeProductPropDTO);

    /**
     * @date 2015年5月29日
     * @author wanghehua
     * @param productTypeProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatepProductTypeProductProp(ProductTypeProductPropDTO productTypeProductPropDTO);

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductTypeProductProp(String mainID);

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @param productTypeProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateProductTypeProductProp(ProductTypeProductPropDTO productTypeProductPropDTO);

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @param productTypeProductPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeProductPropVO> findProductTypeProductProp(ProductTypeProductPropDTO productTypeProductPropDTO);

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
     * @param productTypeProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertpProductTypeItemProp(ProductTypeItemPropDTO productTypeProductPropDTO);

    /**
     * @date 2015年5月27日
     * @author wanghehua
     * @param productTypeProductPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatepProductTypeItemProp(ProductTypeItemPropDTO productTypeProductPropDTO);

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductTypeItemProp(String mainID);

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param productTypeProductPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeItemPropVO> findProductTypeItemProp(ProductTypeItemPropDTO productTypeProductPropDTO);

    /**
     * @date 2014年12月30日
     * @author zhuhp
     * @param map
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeProductPropVO> seachProductprop(Map<String, String> map);

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
