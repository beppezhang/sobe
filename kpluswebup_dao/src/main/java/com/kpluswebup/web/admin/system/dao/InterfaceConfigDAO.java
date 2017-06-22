package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.InterfaceConfigDTO;
import com.kpluswebup.web.domain.InterfaceConfigParameterDTO;
import com.kpluswebup.web.vo.InterfaceConfigParameterVO;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.SystemInterfaceParameterVO;
import com.kpluswebup.web.vo.SystemInterfaceVO;

public interface InterfaceConfigDAO {

    /**
     * 分页查询支付接口
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<InterfaceConfigVO> findInterfaceConfigByPagination(InterfaceConfigDTO interfaceConfigDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findInterfaceConfigCount(InterfaceConfigDTO interfaceConfigDTO);

    /**
     * 根据接口类型查询接口
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceType
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemInterfaceVO> findSystemInterface(Integer interfaceType);

    /**
     * 根据支付接口id查找系统支付接口参数
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemInterfaceParameterVO> findSystemInterfaceParameterByInterfaceID(String interfaceID);

    /**
     * 添加支付接口
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertInterfaceConfig(InterfaceConfigDTO interfaceConfigDTO);

    /**
     * 添加支付接口参数
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigParameterDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertInterfaceConfigParameter(InterfaceConfigParameterDTO interfaceConfigParameterDTO);

    /**
     * 修改支付接口
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateInterfaceConfig(InterfaceConfigDTO interfaceConfigDTO);

    /**
     * 修改支付接口参数
     * 
     * @date 2014年12月1日
     * @author wanghehua
     * @param interfaceConfigParameterDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateInterfaceConfigParameter(InterfaceConfigParameterDTO interfaceConfigParameterDTO);

    /**
     * 根据id查找支付接口
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public InterfaceConfigVO findInterfaceConfigByMainID(String mainID);

    /**
     * 根据支付id查找支付参数
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @param configID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<InterfaceConfigParameterVO> findInterfaceConfigParameterByConfigID(String configID);

    /**
     * 删除支付
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteInterfaceConfig(String mainID);

    /**
     * 启用/停用支付
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @param interfaceConfigDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateInterfaceConfigActive(InterfaceConfigDTO interfaceConfigDTO);

    /**
     * 根据类型查找接口列表
     * 
     * @date 2014年12月22日
     * @author wanghehua
     * @param type
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<InterfaceConfigVO> findInterfaceConfigByType(Integer type);

    /**
     * 查询接口参数值
     * 
     * @date 2014年12月23日
     * @author wanghehua
     * @param interfaceConfigParameterDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String findInterfaceConfigParameterValue(InterfaceConfigParameterDTO interfaceConfigParameterDTO);
    
    /**
     * 根据接口id、参数id查询接口参数值
     * @date 2014年12月29日
     * @author wanghehua
     * @param interfaceConfigParameterDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String findInterfaceParameterValue(InterfaceConfigParameterDTO interfaceConfigParameterDTO);
}
