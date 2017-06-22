package com.kpluswebup.web.promotion.dao;

import java.util.List;

import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.vo.PreSaleVO;


public interface PreSaleDAO {
    
    /**
     * 分页查询
     * @date 2014年11月12日
     * @author lupeng
     * @param preSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<PreSaleVO> findPreSaleByPagination(PreSaleDTO preSaleDTO);
    
    /**
     * 获取总的记录数
     * @date 2014年11月12日
     * @author lupeng
     * @param preSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Long findPreSaleCount(PreSaleDTO preSaleDTO);
    
    /**
     * 添加一个预售商品
     * @date 2014年11月12日
     * @author lupeng
     * @param preSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addPreSaleSelective(PreSaleDTO preSaleDTO);
    
    /**
     * 根据mainID逻辑删除预售商品 
     * @date 2014年11月13日
     * @author lupeng
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer deletePreSaleByMainID(String mainId);
    
    /**
     * 根据mainID查询预售商品
     * @date 2014年11月13日
     * @author lupeng
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public PreSaleVO findPreSaleByMainID(String mainID);
    
    /**
     * 根据mainID更新预售商品
     * @date 2014年11月13日
     * @author lupeng
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updatePreSaleByMainID(PreSaleDTO preSaleDTO);
    
}