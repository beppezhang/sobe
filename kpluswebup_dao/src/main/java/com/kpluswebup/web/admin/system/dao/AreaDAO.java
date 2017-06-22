package com.kpluswebup.web.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CityVO;

public interface AreaDAO {

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
     * 根据mainID查询区域
     * 
     * @date 2014年10月28日
     * @author zhuhp
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public AreaVO getAreaByMainID(String mainID);

    /**
     * 根据区县编号查询对应的省、市信息
     * @date 2014年11月1日
     * @author lupeng
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public AreaVO getAreaCascadeByMainID(String mainID);

	public List<AreaVO> getCityByMainIDAndDepth(List<String> list);
    
}
