package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.vo.ProductTypeVO;


public interface ProductTypeDAO {

    /**
     * 逻辑删除
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductTypeById(String mainID);

    /**
     * 插入
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productTypeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProductType(ProductTypeDTO productTypeDTO);

    /**
     * 分页查询
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productTypeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductTypeVO> findProductTypeByPagination(ProductTypeDTO productTypeDTO);

    /**
     * 产品总条数
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productTypeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findProductTypeCount(ProductTypeDTO productTypeDTO);

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
    public ProductTypeVO findProductTypeByMainID(String mainID);

    /**
     * 修改记录
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param record
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updatProductTypeByMainID(ProductTypeDTO productTypeDTO);
}
