package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CityVO;

public interface AreaService {

    /**
     * 获取全部省份（34条记录）
     * 
     * @date 2014年10月28日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AreaVO> getAllProvince();

    /**
     * 根据父类查询对应的子类(市，区)
     * 
     * @date 2014年10月28日
     * @author zhuhp
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AreaVO> getAreaByParentID(String parentID);
    public List<CityVO> getCityByParentID(String parentID);

    /**
     * 根据id,层级查询地区
     * @param mainID
     * @param depth
     * @return
     */
    public List<AreaVO> getCityByMainIDAndDepth(String mainID, String depth);
}
