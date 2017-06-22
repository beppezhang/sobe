package com.kpluswebup.web.service;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.domain.BrandDTO;
import com.kpluswebup.web.vo.BrandVO;

public interface BrandService {

    /**
     * 分页
     * 
     * @date 2014年11月4日
     * @author zhuhp
     * @param brandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findBrandList(BrandDTO brandDTO);

    /**
     * @date 2014年11月12日
     * @author zhuhp
     * @param brandDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findAllBrandList();
    
    /**
     * 
     * @date 2015年1月29日
     * @author yuanyuan
     * @param catID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandVO> findAllBrandListBycatID(String catID);
    /**
     * 
     * findAllBrandListByName:(通过品牌名称模糊查询所有品牌). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     *
     * @author lei.zhou@kata.com.cn
     * @param bname
     * @return
     * @since JDK 1.6
     * date 2015年8月25日下午3:57:42
     */
    public List<BrandVO> findAllBrandListByName(String bname);

    /**
     * @date 2014年11月4日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteBrandByMainID(String mainID);

    /**
     * @date 2014年11月6日
     * @author zhuhp
     * @param brandDTO
     * @since JDK 1.6
     * @Description
     */
    public void addBrand(BrandDTO brandDTO);

    /**
     * @date 2014年11月6日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public BrandVO findBrandByMainID(String mainID);

    /**
     * @date 2014年11月6日
     * @author zhuhp
     * @param brandDTO
     * @since JDK 1.6
     * @Description
     */
    public void editBrand(BrandDTO brandDTO);
    
    
    //***************************************************sxc
    
    public List<Map> findBrandParts();    

}
