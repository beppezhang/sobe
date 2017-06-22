package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductItemVO;
import com.kpluswebup.web.vo.PromotionItemVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;

public interface PromotionService {

    public List<PromotionVO> findPromotionList(PromotionDTO promotionDTO);

    public List<Integer> findAllPromotionType();

    public String addPromotionSelective(PromotionVO promotion, String gradeIds, String groupIds,String currentOperator);

    public void updatePromotionSelective(PromotionVO promotion, String gradeIds, String groupIds,String currentOperator);

    public PromotionVO findPromotionByMainID(String mainId);

    public Boolean deletePromotionByMainID(String mainIds);

    public PromotionVO promotionInfo(String mainId);

    /**
     * 查找所有产品\产品下的商品
     * 
     * @date 2014年11月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductItemVO> findAllProductItem(String mainID);

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
    public Boolean deletePromotionSetByID(Long id);

    /**
     * 根据名称查找促销条件
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @param promotionsetType
     * @param promotionsetName
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CmsAdvertLinkVO> findPromotionSetByName(Integer promotionsetType, String promotionsetName);

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
    public Boolean addPromotionSet(Integer promotionsetType, String promotionID, String promotionsetIds,String currentOperator);
    
    /**
     * 
     * @date 2015年5月12日
     * @author moo
     * @param promotionID
     * @param itemID
     * @return
     * @return boolean
     * @since JDK 1.6
     * @Description 查询促销中是否含有符合免邮的商品(类目匹配)
     */
	public boolean hasPromotionProduct(String promotionMainID, String itemID);

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
	 * @author categoryID
	 * @param objID
	 * @param itemID
	 * @return
	 * @return ItemVO
	 * @since JDK 1.6
	 * @Description
	 */
	public ItemVO findItemByItemIDAndCategoryID(String categoryID, String itemID);
    
    
}
