package com.kpluswebup.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.ItemScoreDTO;
import com.kpluswebup.web.domain.ItemScoreDetailDTO;
import com.kpluswebup.web.vo.ItemScoreDetailVO;
import com.kpluswebup.web.vo.ItemScoreVO;

public interface ItemScoreService {

    /**
     * 分页查询商品积分列表
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param itemScoreDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemScoreVO> findItemScoreByPagination(ItemScoreDTO itemScoreDTO);

    /**
     * 新增商品积分
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param itemScoreDTO
     * @since JDK 1.6
     * @Description
     */
    public void addItemScore(ItemScoreDTO itemScoreDTO);

    /**
     * 编辑商品积分
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param itemScoreDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateItemScore(ItemScoreDTO itemScoreDTO);

    /**
     * 根据id删除商品积分
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param mainIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteItemScore(String mainIds);

    /**
     * 根据id查询商品积分
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemScoreVO findItemScoreByMainID(String mainID);

    /**
     * 根据商品积分查询商品积分详情
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param itemScoreID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemScoreDetailVO> findItemScoreDetailByPatination(ItemScoreDetailDTO itemScoreDetailDTO);

    /**
     * 导出商品积分明细
     * 
     * @date 2014年12月26日
     * @author wanghehua
     * @param itemScoreID
     * @since JDK 1.6
     * @Description
     */
    public void exportItemScoreDetail(HttpServletResponse response, String itemScoreID);

    /**
     * 根据id获取商品积分明细
     * 
     * @date 2015年1月7日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemScoreDetailVO findItemScoreDetailByMainID(ItemScoreDetailDTO itemScoreDetailDTO);

    /**
     * 修改商品积分明细
     * 
     * @date 2015年1月7日
     * @author wanghehua
     * @param itemScoreDetailDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateItemScoreDetail(ItemScoreDetailDTO itemScoreDetailDTO);
}
