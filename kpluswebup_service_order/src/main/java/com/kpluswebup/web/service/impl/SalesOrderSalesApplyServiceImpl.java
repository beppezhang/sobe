package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.account.dao.AccountDetailDAO;
import com.kpluswebup.web.admin.system.dao.AreaDAO;
import com.kpluswebup.web.admin.system.dao.ShippingAddressDAO;
import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.domain.SalesOrderAfterSalesAddressDTO;
import com.kpluswebup.web.domain.SalesOrderAfterSalesDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.order.dao.SalesOrderAfterSalesAddressDAO;
import com.kpluswebup.web.order.dao.SalesOrderAfterSalesDAO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderDeliveryAddressDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.order.dao.SalesOrderSalesApplyDAO;
import com.kpluswebup.web.service.SalesOrderSalesApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SalesOrderAfterSalesAddressVO;
import com.kpluswebup.web.vo.SalesOrderAftersalesVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShippingAddressVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class SalesOrderSalesApplyServiceImpl implements SalesOrderSalesApplyService {

    private static final Logger                LOGGER = LogManager.getLogger(SalesOrderServiceImpl.class);

    @Autowired
    private SalesOrderSalesApplyDAO           salesOrderSalesApplyDAO;
    @Autowired
    private ShippingAddressDAO                 shippingAddressDAO;
    @Autowired
    private AreaDAO                            areaDAO;
    @Autowired
    private SalesOrderAfterSalesDAO            salesOrderAfterSalesDAO;
    @Autowired
    private SalesOrderAfterSalesAddressDAO     salesOrderAfterSalesAddressDAO;
    @Autowired
    private AccountDetailDAO                   accountDetailDAO;
    @Autowired
    private SalesOrderLineDAO                  salesOrderLineDAO;
    @Autowired
    private SalesOrderDAO                      salesOrderDAO;
    @Autowired
    private SalesOrderService                  salesOrderService;
    @Autowired
    private CustomerDAO                        customerDAO;
    @Autowired
    private SystemCodeDAO                      systemCodeDAO;
    @Autowired
    private SalesOrderDeliveryAddressDAO       salesOrderDeliveryAddressDAO;

    public List<SalesOrderSalesApplyVO> findSalesOrderSalesApplyByPagination(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO) {
        try {
            notNull(salesOrderSalesApplyDTO, "salesOrderSalesApplyDTO is null");
            Long count = salesOrderSalesApplyDAO.findSalesOrderSalesApplyCount(salesOrderSalesApplyDTO);
            salesOrderSalesApplyDTO.doPage(count, salesOrderSalesApplyDTO.getPageNo(),
                                            salesOrderSalesApplyDTO.getPageSize());
            salesOrderSalesApplyDTO.setOrderByClause("ORDER BY zss.id DESC");
            List<SalesOrderSalesApplyVO> list = salesOrderSalesApplyDAO.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


	@Override
	public void updateSalesApplyInfo(SalesOrderSalesApplyVO salesOrderSalesApplyVO) {
		try {
            notNull(salesOrderSalesApplyVO, "salesOrderSalesApplyVO is null");
            // 更新售后申请表中的数据
            SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();
            salesOrderSalesApplyDTO.setMainID(salesOrderSalesApplyVO.getMainID());
            salesOrderSalesApplyDTO.setSalesType(salesOrderSalesApplyVO.getSalesType());
            salesOrderSalesApplyDTO.setCount(salesOrderSalesApplyVO.getCount());
            salesOrderSalesApplyDTO.setDescription(salesOrderSalesApplyVO.getDescription());
            salesOrderSalesApplyDTO.setMemo(salesOrderSalesApplyVO.getMemo());
            salesOrderSalesApplyDTO.setModifyTime(new Date());
            salesOrderSalesApplyDAO.updateAfterSalesByPrimaryKeySelective(salesOrderSalesApplyDTO);
            /*SalesOrderAftersalesVO salesOrderAfterSalesVO = salesOrderAfterSalesDAO.findSalesOrderSalesBySalesApplyID(salesOrderSalesApplyVO.getMainID());
            if (salesOrderAfterSalesVO != null && salesOrderAfterSalesVO.getId() != null) {
            	SalesOrderAfterSalesDTO salesOrderAfterSalesDTO = new SalesOrderAfterSalesDTO();
            	salesOrderAfterSalesDTO.setSalesApplyID(salesOrderSalesApplyVO.getMainID());
            	salesOrderAfterSalesDTO.setSalesType(salesOrderSalesApplyVO.getSalesType());
            	salesOrderAfterSalesDTO.setCount(salesOrderSalesApplyVO.getCount());
            	salesOrderAfterSalesDTO.setDescription(salesOrderSalesApplyVO.getDescription());
            	salesOrderAfterSalesDTO.setMemo(salesOrderSalesApplyVO.getMemo());
            	salesOrderAfterSalesDTO.setModifyTime(new Date());
            	salesOrderAfterSalesDAO.updateBySalesApplyIDSelective(salesOrderAfterSalesDTO);
            } else {
            	SalesOrderAfterSalesDTO salesOrderAfterSalesDTO = new SalesOrderAfterSalesDTO();
            	salesOrderAfterSalesDTO.setMainID(salesID());
            	salesOrderAfterSalesDTO.setSalesApplyID(salesOrderSalesApplyVO.getMainID());
            	salesOrderAfterSalesDTO.setCustomerID(salesOrderSalesApplyVO.getCustomerID());
            	salesOrderAfterSalesDTO.setSalesOrderID(salesOrderSalesApplyVO.getSalesOrderID());
            	salesOrderAfterSalesDTO.setProductID(salesOrderSalesApplyVO.getProductID());
            	salesOrderAfterSalesDTO.setItemID(salesOrderSalesApplyVO.getItemID());
            	salesOrderAfterSalesDTO.setSalesType(salesOrderSalesApplyVO.getSalesType());
            	salesOrderAfterSalesDTO.setCount(salesOrderSalesApplyVO.getCount());
            	salesOrderAfterSalesDTO.setNumberIMEI(salesOrderSalesApplyVO.getNumberIMEI());
            	salesOrderAfterSalesDTO.setIsDelete(0);
            	salesOrderAfterSalesDTO.setDescription(salesOrderSalesApplyVO.getDescription());
            	salesOrderAfterSalesDTO.setMemo(salesOrderSalesApplyVO.getMemo());
            	salesOrderAfterSalesDTO.setStatus(0);
            	salesOrderAfterSalesDTO.setCreateTime(new Date());
            	salesOrderAfterSalesDTO.setCreator(salesOrderSalesApplyVO.getCreator());
            	salesOrderAfterSalesDAO.addSalesOrderSalesSelective(salesOrderAfterSalesDTO);
            }
            // 添加或者更新售后申请地址
            SalesOrderAfterSalesAddressDTO salesAddressDTO = new SalesOrderAfterSalesAddressDTO();
            salesAddressDTO.setAfterSalesID(salesOrderSalesApplyVO.getMainID());
            salesAddressDTO.setName(salesOrderSalesApplyVO.getSalesAddressVO().getName());
            salesAddressDTO.setProvinceID(salesOrderSalesApplyVO.getSalesAddressVO().getProvinceID());
            salesAddressDTO.setCityID(salesOrderSalesApplyVO.getSalesAddressVO().getCityID());
            salesAddressDTO.setDisctrictID(salesOrderSalesApplyVO.getSalesAddressVO().getDisctrictID());
            salesAddressDTO.setAddress(salesOrderSalesApplyVO.getSalesAddressVO().getAddress());
            salesAddressDTO.setTelephone(salesOrderSalesApplyVO.getSalesAddressVO().getTelephone());
            salesAddressDTO.setMobile(salesOrderSalesApplyVO.getSalesAddressVO().getMobile());
            SalesOrderAfterSalesAddressVO salesAddressVO = salesOrderAfterSalesAddressDAO.findSalesOrderAfterSalesAddressByAfterSalesID(salesOrderSalesApplyVO.getMainID());
            if (salesAddressVO != null && salesAddressVO.getId() != null) {
            	salesAddressDTO.setModifyTime(new Date());
            	salesAddressDTO.setId(salesAddressVO.getId());
            	salesOrderAfterSalesAddressDAO.updateSalesAddressByAfterSalesID(salesAddressDTO);
            } else {
                salesAddressDTO.setCreateTime(new Date());
                salesOrderAfterSalesAddressDAO.addSalesAddressSelective(salesAddressDTO);
            }*/
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
	}
	
	public String salesID() {
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.SALESORDERRETURNID);
        String flowCode = RandomStringUtils.randomNumeric(9);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String result = flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeDAO.findSystemCode(Constant.SALESORDERRETURNID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

	@Override
	public void updateApplyStatus(String mainId, String status, String currentOperator,String memo) {
		try {
			notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();
            salesOrderSalesApplyDTO.setMainID(mainId);
            salesOrderSalesApplyDTO.setStatus(Integer.valueOf(status));
            salesOrderSalesApplyDTO.setModifyTime(new Date());
            salesOrderSalesApplyDTO.setModifier(currentOperator);
            salesOrderSalesApplyDTO.setMemo(memo);
            salesOrderSalesApplyDAO.updateByMainIDSelectives(salesOrderSalesApplyDTO);
            SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyDAO.findOrderSalesApplyByMainID(mainId);
            SalesOrderAftersalesVO salesOrderAfterSalesVO = salesOrderAfterSalesDAO.findSalesOrderSalesBySalesApplyID(salesOrderSalesApplyVO.getMainID());
            if (Integer.valueOf(status) == 2) {
            	SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderSalesApplyVO.getSalesOrderID());
            	SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            	salesOrderDTO.setMainID(salesOrderVO.getMainID());
            	salesOrderDTO.setOrderStatus(8);
            	salesOrderService.updateSalesOrder(salesOrderDTO);
            	SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
            	salesOrderLineDTO.setMainID(salesOrderSalesApplyVO.getNumberIMEI());
            	if(salesOrderSalesApplyVO.getSalesType()==1){
            	    salesOrderLineDTO.setStatus(3);
            	}else{
            	    salesOrderLineDTO.setStatus(5);
            	}
            	salesOrderLineDAO.updateSalesOrderLineByID(salesOrderLineDTO);
            
	            if (salesOrderAfterSalesVO != null && salesOrderAfterSalesVO.getId() != null) {
	            	// 同时更新售后单据表中的数据
	            	SalesOrderAfterSalesDTO salesOrderAfterSalesDTO = new SalesOrderAfterSalesDTO();
	            	salesOrderAfterSalesDTO.setStatus(2);
	            	salesOrderAfterSalesDTO.setModifyTime(new Date());
	            	salesOrderAfterSalesDTO.setSalesApplyID(salesOrderAfterSalesVO.getSalesApplyID());
	            	salesOrderAfterSalesDAO.updateBySalesApplyIDSelective(salesOrderAfterSalesDTO);
	            } else {// 生成售后单
	            	SalesOrderAfterSalesDTO salesOrderAfterSalesDTO = new SalesOrderAfterSalesDTO();
	            	salesOrderAfterSalesDTO.setMainID(salesID());
	            	salesOrderAfterSalesDTO.setSalesApplyID(salesOrderSalesApplyVO.getMainID());
	            	salesOrderAfterSalesDTO.setCustomerID(salesOrderSalesApplyVO.getCustomerID());
	            	salesOrderAfterSalesDTO.setSalesOrderID(salesOrderSalesApplyVO.getSalesOrderID());
	            	salesOrderAfterSalesDTO.setProductID(salesOrderSalesApplyVO.getProductID());
	            	salesOrderAfterSalesDTO.setItemID(salesOrderSalesApplyVO.getItemID());
	            	salesOrderAfterSalesDTO.setSalesType(salesOrderSalesApplyVO.getSalesType());
	            	salesOrderAfterSalesDTO.setCount(salesOrderSalesApplyVO.getCount());
	            	salesOrderAfterSalesDTO.setNumberIMEI(salesOrderSalesApplyVO.getNumberIMEI());
	            	salesOrderAfterSalesDTO.setIsDelete(0);
	            	salesOrderAfterSalesDTO.setDescription(salesOrderSalesApplyVO.getDescription());
	            	salesOrderAfterSalesDTO.setMemo(salesOrderSalesApplyVO.getMemo());
	            	salesOrderAfterSalesDTO.setStatus(2);
	            	salesOrderAfterSalesDTO.setCreateTime(new Date());
	            	salesOrderAfterSalesDTO.setCreator(salesOrderSalesApplyVO.getCreator());
	            	salesOrderAfterSalesDAO.addSalesOrderSalesSelective(salesOrderAfterSalesDTO);
	            }
            }
            if (Integer.valueOf(status) == 1) {
            	SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderSalesApplyVO.getSalesOrderID());
            	SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            	salesOrderDTO.setMainID(salesOrderVO.getMainID());
            	salesOrderDTO.setOrderStatus(11);
            	salesOrderService.updateSalesOrder(salesOrderDTO);
            }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}

	@Override
	public SalesOrderSalesApplyVO findSalesOrderSalesApplyInfoByMainID(String mainId) {
		try {
            notNull(mainId, "mainId is null");
            SalesOrderSalesApplyVO vo = salesOrderSalesApplyDAO.findSalesOrderSalesApplyByMainID(mainId);
            return vo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
	}

	@Transactional
    public void salesmentOperation(String mainId, String status) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyDAO.findSalesOrderSalesApplyByMainID(mainId);
            if (salesOrderSalesApplyVO.getSalesType() == 1 || salesOrderSalesApplyVO.getSalesType() == 2) {
                status = "0";
                
                SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();
                salesOrderSalesApplyDTO.setMainID(mainId);
                salesOrderSalesApplyDTO.setStatus(2);
                salesOrderSalesApplyDTO.setModifyTime(new Date());
                salesOrderSalesApplyDAO.updateByMainIDSelectives(salesOrderSalesApplyDTO);
                
                SalesOrderAfterSalesDTO salesOrderAfterSalesDTO = new SalesOrderAfterSalesDTO();
                salesOrderAfterSalesDTO.setMainID(mainId);
                salesOrderAfterSalesDTO.setSalesApplyID(mainId);
                salesOrderAfterSalesDTO.setCustomerID(salesOrderSalesApplyVO.getCustomerID());
                salesOrderAfterSalesDTO.setSalesOrderID(salesOrderSalesApplyVO.getSalesOrderID());
                salesOrderAfterSalesDTO.setProductID(salesOrderSalesApplyVO.getProductID());
                salesOrderAfterSalesDTO.setItemID(salesOrderSalesApplyVO.getItemID());
                salesOrderAfterSalesDTO.setSalesType(salesOrderSalesApplyVO.getSalesType());
                salesOrderAfterSalesDTO.setCount(salesOrderSalesApplyVO.getCount());
                salesOrderAfterSalesDTO.setNumberIMEI(salesOrderSalesApplyVO.getNumberIMEI());
                salesOrderAfterSalesDTO.setMemo(salesOrderSalesApplyVO.getMemo());
                salesOrderAfterSalesDTO.setStatus(2);
                salesOrderAfterSalesDTO.setIsDelete(0);
                salesOrderAfterSalesDTO.setModifyTime(new Date());
                salesOrderAfterSalesDTO.setDescription(salesOrderSalesApplyVO.getDescription());
                salesOrderAfterSalesDAO.addSalesOrderSalesSelective(salesOrderAfterSalesDTO);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

	@Override
	public void barterOperation(String mainId, String status) {
		
	}

	@Override
	public void salesOperation(String mainId, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSelective(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO) {
		try {
            notNull(salesOrderSalesApplyDTO, "salesOrderSalesApplyDTO is null");
            salesOrderSalesApplyDTO.setMainID(salesApplyID());
            salesOrderSalesApplyDAO.saveSelective(salesOrderSalesApplyDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	public String salesApplyID() {
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.SORDERRETURNAPPLYID);
        String flowCode = RandomStringUtils.randomNumeric(9);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String result = flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeDAO.findSystemCode(Constant.SORDERRETURNAPPLYID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

	@Override
	public List<SalesOrderSalesApplyVO> findSalesOrderSalesApply(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO) {
		Long count = salesOrderSalesApplyDAO.findSalesOrderSalesApplyCount(salesOrderSalesApplyDTO);
		salesOrderSalesApplyDTO.doPage(count, salesOrderSalesApplyDTO.getPageNo(), salesOrderSalesApplyDTO.getPageSize());
		List<SalesOrderSalesApplyVO> list = salesOrderSalesApplyDAO.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
		return list;
	}

	@Override
	public void updateReturnOrder(SalesOrderReturnDTO salesOrderReturnDTO) {
		// TODO Auto-generated method stub
		
	}


	public SalesOrderSalesApplyVO findSalesOrderSalesApplyByMainID(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            SalesOrderSalesApplyVO vo = salesOrderSalesApplyDAO.findOrderSalesApplyByMainID(mainID);
            if (vo != null) {
                SalesOrderAfterSalesAddressVO salesAddressVO = null;
                if (vo != null && StringUtil.isNotEmpty(vo.getMainID())) salesAddressVO = salesOrderAfterSalesAddressDAO.findSalesOrderAfterSalesAddressByAfterSalesID(vo.getMainID());
                AreaVO areaVO = null;
                if (salesAddressVO != null && salesAddressVO.getId() != null) {
                    vo.setSalesAddressVO(salesAddressVO);
                    areaVO = areaDAO.getAreaCascadeByMainID(salesAddressVO.getDisctrictID());
                }
                vo.setAreaVO(areaVO);
                return vo;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
	
	public SalesOrderSalesApplyVO updateSalesOrderSalesApplyByMainID(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            SalesOrderSalesApplyVO vo = salesOrderSalesApplyDAO.findOrderSalesApplyByMainID(mainID);
            if (vo != null) {
            	SalesOrderSalesApplyDTO salesOrderSalesApplyDTO =new SalesOrderSalesApplyDTO();
            	salesOrderSalesApplyDTO.setMainID(vo.getMainID());
            	salesOrderSalesApplyDTO.setStatus(4);
            	salesOrderSalesApplyDAO.updateByMainIDSelectives(salesOrderSalesApplyDTO);
            	salesOrderService.updateSalesOrderStatus(vo.getSalesOrderID(), "10",null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
	
	public SalesOrderSalesApplyVO affirmAfterSale(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            SalesOrderSalesApplyVO vo = salesOrderSalesApplyDAO.findOrderSalesApplyByMainID(mainID);
            if (vo != null) {
            	SalesOrderSalesApplyDTO salesOrderSalesApplyDTO =new SalesOrderSalesApplyDTO();
            	salesOrderSalesApplyDTO.setMainID(vo.getMainID());
            	salesOrderSalesApplyDTO.setStatus(3);
            	salesOrderSalesApplyDAO.updateByMainIDSelectives(salesOrderSalesApplyDTO);
            	salesOrderService.updateSalesOrderStatus(vo.getSalesOrderID(), "9",null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

	@Override
	public SupplierItemIDVO findnumberIMEI(String numberIMEI) {
		return salesOrderSalesApplyDAO.findSupplierItemByNumberIMEI(numberIMEI);
	}


	@Override
	public SalesOrderLineVO findSalesOrderLineByOrderID(String mainID) {
		return salesOrderSalesApplyDAO.findSalesOrderLineByOrderID(mainID);
	}


	@Override
	public SalesOrderSalesApplyVO findSalesApplyByNumberIMEI(String numberIMEI) {
		return salesOrderSalesApplyDAO.findSalesOrderSalesApplyByNumberIMEI(numberIMEI);
	}


	@Override
	public SalesOrderAftersalesVO findSalesOrderSalesByApplyID(
			String retrunApplyID) {
		// TODO Auto-generated method stub
		return null;
	}

}
