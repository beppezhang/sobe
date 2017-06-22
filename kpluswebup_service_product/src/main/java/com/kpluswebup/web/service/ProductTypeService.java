package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductTypeVO;

public interface ProductTypeService {

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
    public Boolean isDeleteProductType(String mainID);
    /**
     * 通过productMainID c查询已经选中的
     * @param productMainID
     * @return
     */
    public List<ProductDetailVO> findProductDetailsByProductMainID(String productMainID);
    /**
     * 新增一条
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param productTypeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void addProductType(ProductTypeDTO productTypeDTO);

    /**
     * 编辑产品类型
     * @date 2014年10月29日
     * @author zhuhp
     * @param productTypeDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatProductType(ProductTypeDTO productTypeDTO);
    
    

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
}
