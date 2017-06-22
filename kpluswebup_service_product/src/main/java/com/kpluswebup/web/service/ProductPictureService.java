package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.vo.ProductPictureVO;

public interface ProductPictureService {

    /**
     * @date 2014年11月25日
     * @author zhuhp
     * @param productPictureDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductPictureVO> findProductPictureByProductID(String itemID,String productID);

    /**
     * @date 2014年11月25日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductPictureById(String id);

    /**
     * @date 2014年11月25日
     * @author zhuhp
     * @param name
     * @param productID
     * @param itemID
     * @param picURL
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductPicture(String name, String productID, String itemID, String picURL,String currentOperator);
}
