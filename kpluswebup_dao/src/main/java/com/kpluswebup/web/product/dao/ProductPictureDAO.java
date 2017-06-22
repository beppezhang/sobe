package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ProductPictureDTO;
import com.kpluswebup.web.vo.ProductPictureVO;

public interface ProductPictureDAO {

    /**
     * @date 2014年11月25日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductPictureById(Long id);

    /**
     * @date 2014年11月27日
     * @author zhuhp
     * @param productID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductPictureByProductID(String productID);
    
    /**
     * 
     * @date 2014年11月27日
     * @author zhuhp
     * @param itemID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductPictureByItemID(String itemID);

    /**
     * @date 2014年11月25日
     * @author zhuhp
     * @param productPictureDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductPicture(ProductPictureDTO productPictureDTO);

    /**
     * 不分页条件查询
     * 
     * @date 2014年11月25日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPictureVO> findProductPicture(ProductPictureDTO productPictureDTO);

}
