package com.kpluswebup.web.promotion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.domain.PromotionItemDTO;
import com.kpluswebup.web.domain.PromotionSetDTO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductItemVO;
import com.kpluswebup.web.vo.PromotionItemVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;

public interface PromotionDAO {

    /**
     * 添加一条满送促销活动
     * 
     * @date 2014年11月7日
     * @author lupeng
     * @param promotionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addPromotionSelective(PromotionDTO promotionDTO);

    /**
     * 根据mainID更新促销活动
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param promotionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updatePromotionSelective(PromotionDTO promotionDTO);

    /**
     * 促销活动分页查询
     * 
     * @date 2014年11月7日
     * @author lupeng
     * @param promotionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionVO> findPromotionByPagination(PromotionDTO promotionDTO);

    /**
     * 查询库满送活动表的总记录数
     * 
     * @date 2014年11月7日
     * @author lupeng
     * @param promotionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findPromotionCount(PromotionDTO promotionDTO);

    /**
     * 获取系统的所有促销活动类型
     * 
     * @date 2014年11月7日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<Integer> findAllPromotionType();

    /**
     * 根据mainID查询一条记录
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public PromotionVO findPromotionByMainID(String mainId);

    /**
     * 根据mainID删除一条记录（逻辑删除）
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deletePromotionByMainID(String mainId);

    /**
     * 查找所有产品
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductItemVO> findAllProduct();

    /**
     * 根据产品id查找商品
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductItemVO> findItemByProductID(String mainID);

    /**
     * 添加促销产品
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param promotionItemDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertPromotionItem(PromotionItemDTO promotionItemDTO);

    /**
     * 修改促销产品
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param promotionItemDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatePromotionItem(PromotionItemDTO promotionItemDTO);

    /**
     * 根据促销id查找促销产品
     * 
     * @date 2014年11月21日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public PromotionItemVO findPromotionItemByPromotionMainID(String promotionID);

    /**
     * 根据促销id查找促销条件-产品类型
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionSetVO> findPromotionSetTypeByPromotionID(String promotionID);

    /**
     * 根据促销id查找促销条件-产品类目
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionSetVO> findPromotionSetCategoryByPromotionID(String promotionID);

    /**
     * 根据促销id查找促销条件-产品
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionSetVO> findPromotionSetProductByPromotionID(String promotionID);

    /**
     * 根据促销id查找促销条件-商品
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionSetVO> findPromotionSetItemByPromotionID(String promotionID);

    /**
     * 根据id删除促销条件
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deletePromotionSetByID(Long id);

    /**
     * 添加促销条件
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionSetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertPromotionSet(PromotionSetDTO promotionSetDTO);

    /**
     * 查找促销条件是否已添加
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionSetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public PromotionSetVO findPromotionSetByPTO(PromotionSetDTO promotionSetDTO);

    /**
     * 根据促销id查询促销条件
     * 
     * @date 2015年1月26日
     * @author wanghehua
     * @param pID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionSetVO> findPromotionSetByPID(String pID);
    
    /**
     * 
     * @date 2015年5月12日
     * @author moo
     * @param promotionMainID
     * @param itemMainID
     * @return
     * @return ProductItemVO
     * @since JDK 1.6
     * @Description 根据促销ID,商品ID 查询商品
     */
	public ProductItemVO findProductItemByPromotionIDAndItemID(
		@Param(value="promotionID")	String promotionMainID, @Param(value="itemID") String itemMainID);

	/**
	 * 
	 * @date 2015年6月16日
	 * @author moo
	 * @param promotionID
	 * @return
	 * @return List<PromotionSetVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<PromotionSetVO> findPromotionSetByPromotionID(String promotionID);

	/**
	 * 
	 * @date 2015年6月17日
	 * @author moo
	 * @param categoryID
	 * @param itemID
	 * @return
	 * @return ItemVO
	 * @since JDK 1.6
	 * @Description
	 */
	public ItemVO findItemByItemIDAndCategoryID(
			@Param(value="categoryID") String categoryID, 
			@Param(value="itemID") String itemID);

}
