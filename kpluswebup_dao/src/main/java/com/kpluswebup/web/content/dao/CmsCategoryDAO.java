package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.SystemAdvertPosionDTO;
import com.kpluswebup.web.vo.SystemAdvertPosionVO;

public interface CmsCategoryDAO {

    /**
     * 查询内容分类
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
     * 查询总条数
     * 
     * @date 2014年11月8日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCmsCategoryCount(CmsCategoryDTO cmsCategoryDTO);

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
    public Integer deleteCmsCategoryByMainID(String mainID);

    /**
     * 添加内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void insertCmsCategory(CmsCategoryDTO cmsCategoryDTO);
    /**
     * 添加广告位置
     * 
     * @date 2014年11月10日
     * @author zhoulei
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void addSystemAdvertPosion(SystemAdvertPosionDTO systemAdvertPosionDTO) ;
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
     * 修改内容分类
     * 
     * @date 2014年11月10日
     * @author wanghehua
     * @param cmsCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateCmsCategoryByMainID(CmsCategoryDTO cmsCategoryDTO);

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

    /**
     * 获取广告位
     * 
     * @date 2014年12月23日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemAdvertPosionVO> findAllSystemAdvertPosion();
}
