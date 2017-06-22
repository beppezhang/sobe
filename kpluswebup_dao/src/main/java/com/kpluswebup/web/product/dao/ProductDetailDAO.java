package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ProductDetailDTO;
import com.kpluswebup.web.vo.ProductDetailVO;

public interface ProductDetailDAO {

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductDetailByID(Long id);

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param productDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductDetail(ProductDetailDTO productDetailDTO);

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductDetailVO findProductDetailByID(Long id);

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param productDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductDetailByID(ProductDetailDTO productDetailDTO);

    /**
     * 不分页条件查询
     * 
     * @date 2014年11月24日
     * @author zhuhp
     * @param productDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductDetailVO> findProductDetail(ProductDetailDTO productDetailDTO);

    /**
     * @date 2014年12月12日
     * @author zhuhp
     * @param productMainId
     * @since JDK 1.6
     * @Description
     */
    public void deteleProductDetailByProductMainId(String productMainId);
    /**
     * 通过productMainID c查询已经选中的
     * @param productMainID
     * @return
     */
    public List<ProductDetailVO> findProductDetailsByProductMainID(String productMainID);

}
