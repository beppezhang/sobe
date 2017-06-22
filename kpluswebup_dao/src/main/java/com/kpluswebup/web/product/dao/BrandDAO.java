package com.kpluswebup.web.product.dao;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.domain.BrandDTO;
import com.kpluswebup.web.vo.BrandVO;

public interface BrandDAO {

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */

    public Integer deleteBrandByMainID(String mainID);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param brandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertBrand(BrandDTO brandDTO);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public BrandVO findBrandByMainID(String mainID);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param brandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findBrandByPagination(BrandDTO brandDTO);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param brandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findBrandCount(BrandDTO brandDTO);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param brandDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateBrandByMainID(BrandDTO brandDTO);

    /**
     * @date 2014年11月12日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findAllBrandList();
    
    /**
     * 根据类目查品牌
     * @date 2015年1月29日
     * @author yuanyuan
     * @param catID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findAllBrandListBycatID(String catID);
    
    /**
     * 根据类目查品牌
     * @date 2015年1月29日
     * @author yuanyuan
     * @param catID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findAllBrandListByName(String bname);
    
    //***************************************************sxc
    
    public List<Map> findBrandParts();

}
