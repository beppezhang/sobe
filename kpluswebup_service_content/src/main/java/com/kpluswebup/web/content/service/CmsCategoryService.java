package com.kpluswebup.web.content.service;

import java.util.List;

import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.SystemAdvertPosionDTO;
import com.kpluswebup.web.vo.SystemAdvertPosionVO;

public interface CmsCategoryService {

    /**
     * 分页查询内容分类列表
     * 
     * @date 2014年11月8日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsCategoryVO> findCmsCategoryByPagination(CmsCategoryDTO cmsCategoryDTO);

    /**
     * @date 2014年12月23日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemAdvertPosionVO> findAllSystemAdvertPosion();
    
    /**
     * 新增广告位置
     * @date 2014年12月23日
     * @author zhoulei
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void addSystemAdvertPosion(SystemAdvertPosionDTO systemAdvertPosionDTO);
    /**
     * 修改广告位置
     * 
     * @date 2014年11月10日
     * @author zhoulei
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void updateSystemAdvertPosion(SystemAdvertPosionDTO systemAdvertPosionDTO) ;

    /**
     * 删除内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteCmsCategoryByMainID(String mainID);

    /**
     * 添加内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void addCmsCategory(CmsCategoryDTO cmsCategoryDTO);

    /**
     * 根据id查找内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CmsCategoryVO findCmsCategoryByMainID(String mainID);

    /**
     * 编辑内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void editCmsCategory(CmsCategoryDTO cmsCategoryDTO);

    /**
     * 根据类型查找内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param type
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsCategoryVO> findCmsCategoryByType(Integer type);
}
