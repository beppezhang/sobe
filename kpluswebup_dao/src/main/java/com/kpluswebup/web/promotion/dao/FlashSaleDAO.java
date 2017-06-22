package com.kpluswebup.web.promotion.dao;

import java.util.List;

import com.kpluswebup.web.domain.FlashSaleDTO;
import com.kpluswebup.web.vo.FlashSaleVO;

public interface FlashSaleDAO {

    /**
     * 分页查询限时抢购
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param flashSaleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<FlashSaleVO> findFlashSaleByPagination(FlashSaleDTO flashSaleDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param flashSaleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findFlashSaleCount(FlashSaleDTO flashSaleDTO);

    /**
     * 添加限时抢购
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param flashSaleDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertFlashSale(FlashSaleDTO flashSaleDTO);

    /**
     * 限购限时抢购
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param flashSaleDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateFlashSale(FlashSaleDTO flashSaleDTO);

    /**
     * 根据id删除限时抢购
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteFlashSale(String mainID);

    /**
     * 根据id查找限时抢购
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public FlashSaleVO findFlashSaleByMainID(String mainID);
}
