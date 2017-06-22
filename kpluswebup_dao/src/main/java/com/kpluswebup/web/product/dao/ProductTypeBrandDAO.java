package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ProductTypeBrandDTO;
import com.kpluswebup.web.vo.ProductTypeBrandVO;

public interface ProductTypeBrandDAO {

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductTypeBrandById(Long id);

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param productTypeBrandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductTypeBrand(ProductTypeBrandDTO productTypeBrandDTO);

    /**
     * 不分页条件查询
     * 
     * @date 2014年11月24日
     * @author zhuhp
     * @param productTypeBrandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeBrandVO> findProductTypeBrand(ProductTypeBrandDTO productTypeBrandDTO);

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param record
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductTypeBrandById(ProductTypeBrandDTO productTypeBrandDTO);

}
