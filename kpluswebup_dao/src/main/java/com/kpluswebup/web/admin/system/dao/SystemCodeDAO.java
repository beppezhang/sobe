package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.CodeConfigDTO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.SystemCodeVO;

public interface SystemCodeDAO {

    /**
     * 查找系统编码配置
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemCodeVO> findSystemCode(String mainID);

    /**
     * 设置编码
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @param codeConfigDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertCodeConfig(CodeConfigDTO codeConfigDTO);

    /**
     * 修改编码
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @param codeConfigDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateCodeConfig(CodeConfigDTO codeConfigDTO);

    /**
     * 根据id查找编码
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CodeConfigVO findCodeConfigByID(String codeID);

    /**
     * @date 2014年12月16日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CodeConfigVO> findAllCodeConfig();
}
