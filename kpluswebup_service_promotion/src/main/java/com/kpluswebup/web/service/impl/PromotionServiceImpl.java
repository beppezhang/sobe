package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.AdvertDAO;
import com.kpluswebup.web.customer.dao.CustomerGradeDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.domain.PromotionItemDTO;
import com.kpluswebup.web.domain.PromotionSetDTO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.promotion.dao.CouponBatchDAO;
import com.kpluswebup.web.promotion.dao.PromotionDAO;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CouponPromotionVO;

import com.kpluswebup.web.vo.ItemVO;

import com.kpluswebup.web.vo.ProductCategoryVO;

import com.kpluswebup.web.vo.ProductItemVO;
import com.kpluswebup.web.vo.PromotionItemVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class PromotionServiceImpl implements PromotionService {

    private static final Logger LOGGER = LogManager.getLogger(PromotionServiceImpl.class);

    @Autowired
    private PromotionDAO        promotionDAO;
    @Autowired
    private CouponBatchDAO      couponBatchDAO;
    @Autowired
    private CustomerGradeDAO    customerGradeDAO;
    @Autowired
    private CustomerGroupDAO    customerGroupDAO;
    @Autowired
    private ProductCategoryDAO  productCategoryDAO;

    @Autowired
    private AdvertDAO           advertDAO;

    public List<PromotionVO> findPromotionList(PromotionDTO promotionDTO) {
        try {
            notNull(promotionDTO, "promotionDTO is null");
            promotionDTO.doPage(promotionDAO.findPromotionCount(promotionDTO), promotionDTO.getPageNo(),
                                promotionDTO.getPageSize());
            promotionDTO.setOrderByClause("ORDER BY p.id DESC");
            List<PromotionVO> list = promotionDAO.findPromotionByPagination(promotionDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    public List<Integer> findAllPromotionType() {
        try {
            return promotionDAO.findAllPromotionType();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public String addPromotionSelective(PromotionVO promotion, String gradeIds, String groupIds, String currentOperator) {
        try {
            notNull(promotion, "promotion is null");
            PromotionDTO promotionDTO = saveOrUpdatePromotion(promotion, gradeIds, groupIds, currentOperator);
            promotionDTO.setCreateTime(new Date());
            promotionDAO.addPromotionSelective(promotionDTO);
            if (StringUtil.isNotEmpty(promotion.getProductID())) {
                PromotionItemDTO promotionItemDTO = new PromotionItemDTO();
                promotionItemDTO.setPromotionID(promotionDTO.getMainID());
                promotionItemDTO.setProductID(promotion.getProductID());
                promotionItemDTO.setItemID(promotion.getItemID());
                promotionItemDTO.setCreator(currentOperator);
                promotionDAO.insertPromotionItem(promotionItemDTO);
            }
            return promotionDTO.getMainID();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private PromotionDTO saveOrUpdatePromotion(PromotionVO promotion, String gradeIds, String groupIds,
                                               String currentOperator) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setName(promotion.getName());
        promotionDTO.setType(promotion.getType());
        promotionDTO.setDescription(promotion.getDescription());
        promotionDTO.setPromotionCondition(promotion.getPromotionCondition());
        promotionDTO.setPromotionValue(promotion.getPromotionValue());
        promotionDTO.setCustomerGrade(gradeIds);
        promotionDTO.setCustomerGroup(groupIds);
        if (StringUtil.isNotEmpty(promotion.getMainID())) {
            promotionDTO.setMainID(promotion.getMainID());
            promotionDTO.setModifier(currentOperator);
        } else {
            promotionDTO.setMainID(UUIDUtil.getUUID());
            promotionDTO.setCreator(currentOperator);
        }
        if (StringUtil.isNotEmpty(promotion.getFromDateStr())) promotionDTO.setFromDate(sdf.parse(promotion.getFromDateStr()));
        if (StringUtil.isNotEmpty(promotion.getEndDateStr())) promotionDTO.setEndDate(sdf.parse(promotion.getEndDateStr()));
        return promotionDTO;
    }

    public void updatePromotionSelective(PromotionVO promotion, String gradeIds, String groupIds, String currentOperator) {
        try {
            notNull(promotion, "promotion is null");
            PromotionDTO promotionDTO = saveOrUpdatePromotion(promotion, gradeIds, groupIds, currentOperator);
            promotionDAO.updatePromotionSelective(promotionDTO);
            PromotionItemDTO promotionItemDTO = new PromotionItemDTO();
            if (StringUtil.isNotEmpty(promotion.getProductID())) {
                PromotionItemVO promotionItemVO = promotionDAO.findPromotionItemByPromotionMainID(promotionDTO.getMainID());
                if (promotionItemVO != null) {
                    promotionItemDTO.setProductID(promotion.getProductID());
                    promotionItemDTO.setItemID(promotion.getItemID());
                    promotionItemDTO.setId(promotionItemVO.getId());
                    promotionItemDTO.setModifier(currentOperator);
                    promotionDAO.updatePromotionItem(promotionItemDTO);
                } else {
                    promotionItemDTO.setPromotionID(promotionDTO.getMainID());
                    promotionItemDTO.setProductID(promotion.getProductID());
                    promotionItemDTO.setItemID(promotion.getItemID());
                    promotionItemDTO.setCreator(currentOperator);
                    promotionDAO.insertPromotionItem(promotionItemDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public PromotionVO findPromotionByMainID(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            PromotionVO promotionVO = promotionDAO.findPromotionByMainID(mainID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (promotionVO != null && promotionVO.getFromDate() != null) promotionVO.setFromDateStr(sdf.format(promotionVO.getFromDate()));
            if (promotionVO != null && promotionVO.getEndDate() != null) promotionVO.setEndDateStr(sdf.format(promotionVO.getEndDate()));
            return promotionVO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Boolean deletePromotionByMainID(String mainIds) {
        notNull(mainIds, "mainIds is null");
        try {
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                promotionDAO.deletePromotionByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public PromotionVO promotionInfo(String mainId) {
        try {
            notNull(mainId, "mainId is null");
            PromotionVO promotionVO = promotionDAO.findPromotionByMainID(mainId);
            if (promotionVO != null
                && (promotionVO.getType() == 5 || promotionVO.getType() == 6 || promotionVO.getType() == 13)) {
                CouponPromotionVO couponBatchVO = couponBatchDAO.findCouponBatchByMainID(promotionVO.getPromotionValue());
                promotionVO.setCouponBatchVO(couponBatchVO);
            }
            return promotionVO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<ProductItemVO> findAllProductItem(String mainID) {
        if (StringUtil.isEmpty(mainID) || mainID == null) {
            return promotionDAO.findAllProduct();
        } else {
            return promotionDAO.findItemByProductID(mainID);
        }
    }

    @Override
    public PromotionItemVO findPromotionItemByPromotionMainID(String promotionID) {
        return promotionDAO.findPromotionItemByPromotionMainID(promotionID);
    }

    @Override
    public List<PromotionSetVO> findPromotionSetTypeByPromotionID(String promotionID) {
        return promotionDAO.findPromotionSetTypeByPromotionID(promotionID);
    }

    @Override
    public List<PromotionSetVO> findPromotionSetCategoryByPromotionID(String promotionID) {
        return promotionDAO.findPromotionSetCategoryByPromotionID(promotionID);
    }

    @Override
    public List<PromotionSetVO> findPromotionSetProductByPromotionID(String promotionID) {
        return promotionDAO.findPromotionSetProductByPromotionID(promotionID);
    }

    @Override
    public List<PromotionSetVO> findPromotionSetItemByPromotionID(String promotionID) {
        return promotionDAO.findPromotionSetItemByPromotionID(promotionID);
    }

    @Override
    public Boolean deletePromotionSetByID(Long id) {
        try {
            promotionDAO.deletePromotionSetByID(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CmsAdvertLinkVO> findPromotionSetByName(Integer promotionsetType, String promotionsetName) {
        List<CmsAdvertLinkVO> list = null;
        if (promotionsetType == 1) {
            list = advertDAO.findProductTypeByName(promotionsetName);
        } else if (promotionsetType == 2) {
            List<ProductCategoryVO> oneList = productCategoryDAO.findProductOneLevel();
            List<CmsAdvertLinkVO> cList = new ArrayList<CmsAdvertLinkVO>();
            for (ProductCategoryVO productCategoryVO : oneList) {
                ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
                productCategoryDTO.setParentID(productCategoryVO.getMainID());
                productCategoryDTO.setName(promotionsetName);
                List<CmsAdvertLinkVO> list2 = advertDAO.findProductCategoryByName(productCategoryDTO);
                if (list2 != null && list2.size() > 0) {
                    for (CmsAdvertLinkVO cmsAdvertLinkVO : list2) {
                        cList.add(cmsAdvertLinkVO);
                    }
                }
            }
            list = cList;
        } else if (promotionsetType == 3) {
            list = advertDAO.findProductByName(promotionsetName);
        } else if (promotionsetType == 4) {
            list = advertDAO.findItemByName(promotionsetName);
        }
        return list;
    }

    @Override
    public Boolean addPromotionSet(Integer promotionsetType, String promotionID, String promotionsetIds,
                                   String currentOperator) {
        try {
            PromotionSetDTO promotionSetDTO = new PromotionSetDTO();
            String[] setIds = promotionsetIds.split(",");
            for (String promotionsetId : setIds) {
                promotionSetDTO.setPromotionID(promotionID);
                promotionSetDTO.setSetType(promotionsetType);
                promotionSetDTO.setObjID(promotionsetId);
                PromotionSetVO promotionSetVO = promotionDAO.findPromotionSetByPTO(promotionSetDTO);
                if (promotionSetVO == null) {
                    promotionSetDTO.setCreator(currentOperator);
                    promotionDAO.insertPromotionSet(promotionSetDTO);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hasPromotionProduct(String promotionMainID, String itemID) {
        ProductItemVO item = promotionDAO.findProductItemByPromotionIDAndItemID(promotionMainID, itemID);
        return item == null ? false : true;
    }


	@Override
	public List<PromotionSetVO> findPromotionSetByPromotionID(String promotionID) {
		
		return promotionDAO.findPromotionSetByPromotionID(promotionID);
	}

	@Override
	public ItemVO findItemByItemIDAndCategoryID(String categoryID, String itemID) {
		return promotionDAO.findItemByItemIDAndCategoryID(categoryID, itemID);
	}

}
