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
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.domain.SalesOrderReturnShippingAddressDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderDeliveryAddressDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.order.dao.SalesOrderReturnApplyDAO;
import com.kpluswebup.web.order.dao.SalesOrderReturnDAO;
import com.kpluswebup.web.order.dao.SalesOrderReturnShippingAddressDAO;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnShippingAddressVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShippingAddressVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class SalesOrderReturnApplyServiceImpl implements SalesOrderReturnApplyService {

    private static final Logger                LOGGER = LogManager.getLogger(SalesOrderServiceImpl.class);

    @Autowired
    private SalesOrderReturnApplyDAO           salesOrderReturnApplyDAO;
    @Autowired
    private ShippingAddressDAO                 shippingAddressDAO;
    @Autowired
    private AreaDAO                            areaDAO;
    @Autowired
    private SalesOrderReturnDAO                salesOrderReturnDAO;
    @Autowired
    private SalesOrderReturnShippingAddressDAO salesOrderReturnShippingAddressDAO;
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

    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApplyByPagination(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) {
        try {
            notNull(salesOrderReturnApplyDTO, "salesOrderReturnApplyDTO is null");
            Long count = salesOrderReturnApplyDAO.findSalesOrderReturnApplyCount(salesOrderReturnApplyDTO);
            salesOrderReturnApplyDTO.doPage(count, salesOrderReturnApplyDTO.getPageNo(),
                                            salesOrderReturnApplyDTO.getPageSize());
            salesOrderReturnApplyDTO.setOrderByClause("ORDER BY sra.id DESC");
            List<SalesOrderReturnApplyVO> list = salesOrderReturnApplyDAO.findSalesOrderReturnApplyByPagination(salesOrderReturnApplyDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public SalesOrderReturnApplyVO findSalesOrderReturnApplyByMainID(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            SalesOrderReturnApplyVO vo = salesOrderReturnApplyDAO.findOrderReturnApplyByMainID(mainID);
            if (vo != null) {
                ShippingAddressVO shippingAddressVO = shippingAddressDAO.findForReturnShippingAddress();
                SalesOrderReturnShippingAddressVO returnAddressVO = null;
                if (vo != null && StringUtil.isNotEmpty(vo.getMainID())) returnAddressVO = salesOrderReturnShippingAddressDAO.findSalesOrderReturnShippingAddressByReturnID(vo.getMainID());
                AreaVO areaVO = null;
                if (returnAddressVO != null && returnAddressVO.getId() != null) {
                    vo.setReturnAddressVO(returnAddressVO);// 页面修改后查看的是退货收货地址表的物流信息
                    areaVO = areaDAO.getAreaCascadeByMainID(returnAddressVO.getDisctrictID());
                } else {
                    vo.setShippingAddressVO(shippingAddressVO);// 页面第一次修改查看的是系统参数里的物流信息
                    areaVO = areaDAO.getAreaCascadeByMainID(shippingAddressVO.getDisctrictID());
                }
                vo.setAreaVO(areaVO);
                return vo;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional
    public void updateReturnApplyInfo(SalesOrderReturnApplyVO salesOrderReturnApplyVO) {
        try {
            notNull(salesOrderReturnApplyVO, "salesOrderReturnApplyVO is null");
            // 更新退换货申请表中的数据
            SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
            salesOrderReturnApplyDTO.setMainID(salesOrderReturnApplyVO.getMainID());
            salesOrderReturnApplyDTO.setReturnType(salesOrderReturnApplyVO.getReturnType());
            salesOrderReturnApplyDTO.setCount(salesOrderReturnApplyVO.getCount());
            salesOrderReturnApplyDTO.setReturnAmount(salesOrderReturnApplyVO.getReturnAmount());
            salesOrderReturnApplyDTO.setReturnScore(salesOrderReturnApplyVO.getReturnScore());
            salesOrderReturnApplyDTO.setDescription(salesOrderReturnApplyVO.getDescription());
            salesOrderReturnApplyDTO.setModifyTime(new Date());
            salesOrderReturnApplyDAO.updateByPrimaryKeySelective(salesOrderReturnApplyDTO);
            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByRetrunApplyID(salesOrderReturnApplyVO.getMainID());
            if (salesOrderReturnVO != null && salesOrderReturnVO.getId() != null) {
                // 同时更新退换货单据表中的数据
                SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
                salesOrderReturnDTO.setRetrunApplyID(salesOrderReturnApplyVO.getMainID());
                salesOrderReturnDTO.setReturnType(salesOrderReturnApplyVO.getReturnType());
                salesOrderReturnDTO.setCount(salesOrderReturnApplyVO.getCount());
                salesOrderReturnDTO.setReturnAmount(salesOrderReturnApplyVO.getReturnAmount());
                salesOrderReturnDTO.setMemo(salesOrderReturnApplyVO.getDescription());
                salesOrderReturnDTO.setModifyTime(new Date());
                salesOrderReturnDAO.updateByRetrunApplyIDSelective(salesOrderReturnDTO);
            } else {
                SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
                salesOrderReturnDTO.setMainID(returnID());
                salesOrderReturnDTO.setRetrunApplyID(salesOrderReturnApplyVO.getMainID());
                salesOrderReturnDTO.setCustomerID(salesOrderReturnApplyVO.getCustomerID());
                salesOrderReturnDTO.setSalesOrderID(salesOrderReturnApplyVO.getSalesOrderID());
                salesOrderReturnDTO.setProductID(salesOrderReturnApplyVO.getProductID());
                salesOrderReturnDTO.setItemID(salesOrderReturnApplyVO.getItemID());
                salesOrderReturnDTO.setReturnType(salesOrderReturnApplyVO.getReturnType());
                salesOrderReturnDTO.setCount(salesOrderReturnApplyVO.getCount());
                salesOrderReturnDTO.setReturnAmount(salesOrderReturnApplyVO.getReturnAmount());
                salesOrderReturnDTO.setMemo(salesOrderReturnApplyVO.getDescription());
                if (salesOrderReturnApplyVO.getReturnType() != null) {
                    if (salesOrderReturnApplyVO.getReturnType() == 1) {
                        salesOrderReturnDTO.setPaymentStatus(1);
                    } else {
                        salesOrderReturnDTO.setPaymentStatus(0);
                    }
                }
                salesOrderReturnDTO.setStatus(0);
                salesOrderReturnDTO.setCreateTime(new Date());
                salesOrderReturnDTO.setCreator(salesOrderReturnApplyVO.getCreator());
                salesOrderReturnDAO.addSalesOrderReturnSelective(salesOrderReturnDTO);
            }
            // 添加或者更新退货收货地址
            SalesOrderReturnShippingAddressDTO returnAddressDTO = new SalesOrderReturnShippingAddressDTO();
            returnAddressDTO.setReturnID(salesOrderReturnApplyVO.getMainID());
            returnAddressDTO.setName(salesOrderReturnApplyVO.getReturnAddressVO().getName());
            returnAddressDTO.setProvinceID(salesOrderReturnApplyVO.getReturnAddressVO().getProvinceID());
            returnAddressDTO.setCityID(salesOrderReturnApplyVO.getReturnAddressVO().getCityID());
            returnAddressDTO.setDisctrictID(salesOrderReturnApplyVO.getReturnAddressVO().getDisctrictID());
            returnAddressDTO.setAddress(salesOrderReturnApplyVO.getReturnAddressVO().getAddress());
            returnAddressDTO.setZip(salesOrderReturnApplyVO.getReturnAddressVO().getZip());
            returnAddressDTO.setTelephone(salesOrderReturnApplyVO.getReturnAddressVO().getTelephone());
            returnAddressDTO.setMobile(salesOrderReturnApplyVO.getReturnAddressVO().getMobile());
            SalesOrderReturnShippingAddressVO returnAddressVO = salesOrderReturnShippingAddressDAO.findSalesOrderReturnShippingAddressByReturnID(salesOrderReturnApplyVO.getMainID());
            if (returnAddressVO != null && returnAddressVO.getId() != null) {
                returnAddressDTO.setModifyTime(new Date());
                returnAddressDTO.setId(returnAddressVO.getId());
                salesOrderReturnShippingAddressDAO.updateReturnAddressByReturnID(returnAddressDTO);
            } else {
                returnAddressDTO.setCreateTime(new Date());
                salesOrderReturnShippingAddressDAO.addReturnAddressSelective(returnAddressDTO);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String returnID() {
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

    @Transactional
    public void updateApplyStatus(String mainId, String status, String currentOperator) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
            salesOrderReturnApplyDTO.setMainID(mainId);
            salesOrderReturnApplyDTO.setStatus(Integer.valueOf(status));
            salesOrderReturnApplyDTO.setModifyTime(new Date());
            salesOrderReturnApplyDTO.setModifier(currentOperator);
            salesOrderReturnApplyDAO.updateByMainIDSelective(salesOrderReturnApplyDTO);
            SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyDAO.findOrderReturnApplyByMainID(mainId);
            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByRetrunApplyID(salesOrderReturnApplyVO.getMainID());
            if (Integer.valueOf(status) == 2) {
                SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderReturnApplyVO.getSalesOrderID());
                SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                salesOrderDTO.setMainID(salesOrderVO.getMainID());
                salesOrderDTO.setOrderStatus(9);
                salesOrderService.updateSalesOrder(salesOrderDTO);
            }
            if (salesOrderReturnVO != null && salesOrderReturnVO.getId() != null) {
                // 同时更新退换货单据表中的数据
                SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
                salesOrderReturnDTO.setStatus(Integer.valueOf(status));
                salesOrderReturnDTO.setModifyTime(new Date());
                salesOrderReturnDAO.updateByRetrunApplyIDSelective(salesOrderReturnDTO);
            } else {// 生成退换货单
                SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
                salesOrderReturnDTO.setMainID(returnID());
                salesOrderReturnDTO.setRetrunApplyID(mainId);
                salesOrderReturnDTO.setStatus(Integer.valueOf(status));
                salesOrderReturnDTO.setCustomerID(salesOrderReturnApplyVO.getCustomerID());
                salesOrderReturnDTO.setProductID(salesOrderReturnApplyVO.getProductID());
                salesOrderReturnDTO.setItemID(salesOrderReturnApplyVO.getItemID());
                salesOrderReturnDTO.setReturnType(salesOrderReturnApplyVO.getReturnType());
                salesOrderReturnDTO.setCount(salesOrderReturnApplyVO.getCount());
                if (salesOrderReturnApplyVO.getType() != 2) {
                    salesOrderReturnDTO.setReturnAmount(salesOrderReturnApplyVO.getReturnAmount());
                }
                salesOrderReturnDTO.setReturnScore(salesOrderReturnApplyVO.getReturnScore());
                if (salesOrderReturnApplyVO.getType() == 2) {// 全积分
                    salesOrderReturnDTO.setPaymentStatus(1);
                } else {// 金额+积分
                        // if (!salesOrderReturnApplyVO.getReturnAmount().equals("0")) {
                    if (salesOrderReturnApplyVO.getReturnAmount() != null) {
                        salesOrderReturnDTO.setPaymentStatus(1);
                    } else {
                        salesOrderReturnDTO.setPaymentStatus(0);
                    }
                }
                salesOrderReturnDTO.setCreator(currentOperator);
                salesOrderReturnDAO.addSalesOrderReturnSelective(salesOrderReturnDTO);
            }
            if (status.equals("1")) {// 确认
                if (salesOrderReturnVO != null && salesOrderReturnVO.getReturnScore() != null) {// 带积分
                    AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                    accountDetailDTO.setSerialNumber(salesOrderReturnApplyDTO.getMainID());
                    accountDetailDTO.setDetailType(3);
                    accountDetailDTO.setAmount(Double.valueOf(salesOrderReturnVO.getReturnScore()));
                    accountDetailDTO.setAccountType(2);
                    accountDetailDTO.setPaymentType(3);
                    accountDetailDTO.setDescription("退货退积分");
                    accountDetailDTO.setObjID(salesOrderReturnVO.getMainID());
                    accountDetailDTO.setCustomerID(salesOrderReturnVO.getCustomerID());
                    accountDetailDTO.setStatus(1);
                    CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.REFUNDDETAILID);
                    if (codeConfigVO != null) {
                        String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
                        accountDetailDTO.setMainID(mainID);
                    }
                    accountDetailDAO.insertMemberScore(accountDetailDTO);
                    CustomerVO customerVO = customerDAO.findCustomerByMainID(salesOrderReturnVO.getCustomerID());
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setScore(customerVO.getScore() + salesOrderReturnVO.getReturnScore());
                    customerDTO.setMainID(customerVO.getMainID());
                    customerDAO.insertCustomer(customerDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Transactional
    public void updateApplyStatus(String mainId, String status) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
            salesOrderReturnApplyDTO.setMainID(mainId);
            salesOrderReturnApplyDTO.setStatus(Integer.valueOf(status));
            salesOrderReturnApplyDTO.setModifyTime(new Date());
            salesOrderReturnApplyDAO.updateByMainIDSelective(salesOrderReturnApplyDTO);
            SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
            salesOrderReturnDTO.setRetrunApplyID(mainId);
            salesOrderReturnDTO.setStatus(Integer.valueOf(status));
            salesOrderReturnDTO.setModifyTime(new Date());
            salesOrderReturnDAO.updateByRetrunApplyIDSelective(salesOrderReturnDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public SalesOrderReturnApplyVO findSalesOrderReturnApplyInfoByMainID(String mainId) {
        try {
            notNull(mainId, "mainId is null");
            SalesOrderReturnApplyVO vo = salesOrderReturnApplyDAO.findSalesOrderReturnApplyByMainID(mainId);
            return vo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional
    public void refundmentOperation(String mainId, String status) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByRetrunApplyID(mainId);
            SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyDAO.findOrderReturnApplyByMainID(salesOrderReturnVO.getRetrunApplyID());
            if (salesOrderReturnApplyVO.getType() == 2) {
                status = "7";
                SalesOrderVO salesOrder = salesOrderDAO.findSalesOrderByMainID(salesOrderReturnApplyVO.getSalesOrderID());
                SalesOrderDTO orderDTO = new SalesOrderDTO();
                orderDTO.setMainID(salesOrder.getMainID());
                orderDTO.setOrderStatus(8);//已退货
                salesOrderDAO.updateSalesOrderByMainID(orderDTO);
            }
            updateApplyStatus(mainId, status);
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.REFUNDDETAILID);
            if (codeConfigVO != null) {
                String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
                accountDetailDTO.setMainID(mainID);
            }
            accountDetailDTO.setCustomerID(salesOrderReturnVO.getCustomerID());
            if (salesOrderReturnApplyVO.getType() == 2) {
                accountDetailDTO.setAccountType(2);// 积分
                accountDetailDTO.setAmount(Double.valueOf(salesOrderReturnVO.getReturnScore())*salesOrderReturnApplyVO.getCount());
                accountDetailDTO.setStatus(1);
                accountDetailDTO.setModifyTime(new Date());
                accountDetailDTO.setDescription("退货退积分");
            } else {
                accountDetailDTO.setAccountType(1);// 金额
                accountDetailDTO.setAmount(salesOrderReturnVO.getReturnAmount()*salesOrderReturnApplyVO.getCount());
                accountDetailDTO.setStatus(0);
            }
            accountDetailDTO.setDetailType(3);
            accountDetailDTO.setObjID(salesOrderReturnVO.getMainID());
            if (salesOrderReturnApplyVO.getType() == 2 || salesOrderReturnApplyVO.getType() == 3) {// 全积分
                CustomerVO customerVO = customerDAO.findCustomerByMainID(salesOrderReturnVO.getCustomerID());
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setMainID(customerVO.getMainID());
                customerDTO.setScore(customerVO.getScore() + salesOrderReturnVO.getReturnScore());
                customerDAO.updateCustomerByMainId(customerDTO);
            }
            accountDetailDTO.setCreateTime(new Date());
            accountDetailDAO.insertMemberScore(accountDetailDTO);
            updateSalesOrderLineStatus(salesOrderReturnVO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void updateSalesOrderLineStatus(SalesOrderReturnVO salesOrderReturnVO) {
        SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyDAO.findOrderReturnApplyByMainID(salesOrderReturnVO.getRetrunApplyID());
        SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
        salesOrderLineDTO.setOrderID(salesOrderReturnVO.getSalesOrderID());
        salesOrderLineDTO.setProductID(salesOrderReturnVO.getProductID());
        salesOrderLineDTO.setItemID(salesOrderReturnVO.getItemID());
        if (salesOrderReturnApplyVO.getType() == 2) {// 全积分
            salesOrderLineDTO.setStatus(3);
        } else {
            salesOrderLineDTO.setStatus(2);
        }
        salesOrderLineDTO.setReturnApplyID(salesOrderReturnVO.getRetrunApplyID());
        salesOrderLineDTO.setModifyTime(new Date());
        salesOrderLineDAO.updateByParameters(salesOrderLineDTO);
    }

    @Transactional
    public void barterOperation(String mainId, String status) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            updateApplyStatus(mainId, status);
            // 换货单
            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByRetrunApplyID(mainId);
            // 申请单
            SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyDAO.findOrderReturnApplyByMainID(salesOrderReturnVO.getRetrunApplyID());
            SalesOrderVO salesOrderVO1 = salesOrderDAO.findSalesOrderByMainID(salesOrderReturnApplyVO.getSalesOrderID());
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            salesOrderDTO.setMainID(salesOrderService.randomNumeric());// 订单号
            salesOrderDTO.setCustomerID(salesOrderReturnVO.getCustomerID());
            salesOrderDTO.setOrderType(5);// 换货
            salesOrderDTO.setIsDelete(0);// 是否删除
            salesOrderDTO.setChangeOrderID(salesOrderReturnVO.getMainID());// 换货单编号
            salesOrderDTO.setPaymentType(salesOrderVO1.getPaymentType());// 支付方式
            salesOrderDTO.setPaymentStatus(1);// 支付状态：1：已支付
            salesOrderDTO.setOrderStatus(1);// 订单状态
            if (salesOrderVO1.getMemo() != null) {
                salesOrderDTO.setMemo(salesOrderVO1.getMemo());
            }
            if (salesOrderVO1.getExpressFee() != null) {
                salesOrderDTO.setExpressFee(salesOrderVO1.getExpressFee());
            }
            if (salesOrderVO1.getInvoiceType() != null) {// 发票
                salesOrderDTO.setInvoiceType(salesOrderVO1.getInvoiceType());
            }
            if (salesOrderVO1.getInvoiceTitle() != null) {
                salesOrderDTO.setInvoiceTitle(salesOrderVO1.getInvoiceTitle());
            }
            if (salesOrderVO1.getDescription() != null) {
                salesOrderDTO.setDescription(salesOrderVO1.getDescription());
            }
            if (salesOrderReturnApplyVO.getReturnAmount() != null) {
                salesOrderDTO.setProductAmount(salesOrderReturnApplyVO.getReturnAmount());
                salesOrderDTO.setTotalAmount(salesOrderReturnApplyVO.getReturnAmount());
            }
            if (salesOrderReturnApplyVO.getReturnScore() != null) {// 积分总额
                salesOrderDTO.setScoreTotal(salesOrderReturnApplyVO.getReturnScore());
            }
            salesOrderDTO.setCreateTime(new Date());
            // TODO 暂时只加这些信息
            salesOrderDAO.addSalesOrder(salesOrderDTO);
            SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO = new SalesOrderDeliveryAddressDTO();
            SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressDAO.findSalesOrderDeliveryAddressByOrderID(salesOrderReturnApplyVO.getSalesOrderID());
            if (salesOrderDTO.getMainID() != null) {
                salesOrderDeliveryAddressDTO.setSalesOrderID(salesOrderDTO.getMainID());
            }
            if (salesOrderDeliveryAddressVO.getName() != null) {
                salesOrderDeliveryAddressDTO.setName(salesOrderDeliveryAddressVO.getName());
            }
            if (salesOrderDeliveryAddressVO.getCountryID() != null) {
                salesOrderDeliveryAddressDTO.setCountryID(salesOrderDeliveryAddressVO.getCountryID());
            }
            if (salesOrderDeliveryAddressVO.getProvinceID() != null) {
                salesOrderDeliveryAddressDTO.setProvinceID(salesOrderDeliveryAddressVO.getProvinceID());
            }
            if (salesOrderDeliveryAddressVO.getCityID() != null) {
                salesOrderDeliveryAddressDTO.setCityID(salesOrderDeliveryAddressVO.getCityID());
            }
            if (salesOrderDeliveryAddressVO.getDisctrictID() != null) {
                salesOrderDeliveryAddressDTO.setDisctrictID(salesOrderDeliveryAddressVO.getDisctrictID());
            }
            if (salesOrderDeliveryAddressVO.getAddress() != null) {
                salesOrderDeliveryAddressDTO.setAddress(salesOrderDeliveryAddressVO.getAddress());
            }
            if (salesOrderDeliveryAddressVO.getMobile() != null) {
                salesOrderDeliveryAddressDTO.setMobile(salesOrderDeliveryAddressVO.getMobile());
            }
            if (salesOrderDeliveryAddressVO.getTelephone() != null) {
                salesOrderDeliveryAddressDTO.setTelephone(salesOrderDeliveryAddressVO.getTelephone());
            }
            salesOrderDeliveryAddressDAO.insertSalesOrderDeliveryAddress(salesOrderDeliveryAddressDTO);
            SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByChangeOrderID(salesOrderReturnVO.getMainID());
            SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
            salesOrderLineDTO.setOrderID(salesOrderVO.getMainID());
            salesOrderLineDTO.setProductID(salesOrderReturnVO.getProductID());
            salesOrderLineDTO.setItemID(salesOrderReturnVO.getItemID());
            salesOrderLineDTO.setItemCount(salesOrderReturnVO.getCount());
            if (salesOrderReturnVO.getReturnAmount() != null) {
                salesOrderLineDTO.setTransactionPrice(salesOrderReturnVO.getReturnAmount());
                salesOrderLineDTO.setSalesPrice(salesOrderReturnVO.getReturnAmount());
            }
            if (salesOrderReturnVO.getReturnScore() != null) {
                salesOrderLineDTO.setSalesScore(salesOrderReturnVO.getReturnScore());
            }
            salesOrderLineDTO.setStatus(2);
            salesOrderLineDTO.setIsPromotion(0);
            salesOrderLineDTO.setType(salesOrderReturnApplyVO.getType());
            salesOrderLineDTO.setReturnApplyID(salesOrderReturnVO.getRetrunApplyID());
            salesOrderLineDTO.setCreateTime(new Date());
            // TODO 暂时只加这些信息
            salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
            updateSalesOrderLineStatus(salesOrderReturnVO);
            SalesOrderDTO salesOrderDTO2 = new SalesOrderDTO();
            salesOrderDTO2.setMainID(salesOrderVO1.getMainID());
            salesOrderDTO2.setOrderStatus(10);
            salesOrderService.updateSalesOrder(salesOrderDTO2);
            SalesOrderLineDTO salesOrderLineDTO2 = new SalesOrderLineDTO();
            salesOrderLineDTO2.setOrderID(salesOrderVO1.getMainID());
            salesOrderLineDTO2.setType(salesOrderReturnApplyVO.getType());
            // SalesOrderLineVO salesOrderLineVO =
            // salesOrderLineDAO.findSalesOrderLineBySearch(salesOrderLineDTO2).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void salesOperation(String mainId, String status) {
        try {
            notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainId);
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            salesOrderDTO.setOrderStatus(7);
            salesOrderDTO.setMainID(salesOrderVO.getMainID());
            salesOrderService.updateSalesOrder(salesOrderDTO);
            // TODO 暂时只加这些信息
            SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
            salesOrderReturnApplyDTO.setMainID(applyID());
            salesOrderReturnApplyDTO.setCreateTime(new Date());
            salesOrderReturnApplyDTO.setSalesOrderID(salesOrderVO.getMainID());
            salesOrderReturnApplyDTO.setCustomerID(salesOrderVO.getCustomerID());
            salesOrderReturnApplyDTO.setReturnType(1);
            salesOrderReturnApplyDTO.setStatus(0);
            salesOrderReturnDAO.addSalesOrderReturnApply(salesOrderReturnApplyDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertSelective(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) {
        try {
            notNull(salesOrderReturnApplyDTO, "salesOrderReturnApplyDTO is null");
            salesOrderReturnApplyDTO.setMainID(applyID());
            salesOrderReturnApplyDAO.insertSelective(salesOrderReturnApplyDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String applyID() {
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
    public List<SalesOrderReturnApplyVO> findSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) {
        Long count=salesOrderReturnApplyDAO.findSalesOrderReturnApplyCount(salesOrderReturnApplyDTO);
        salesOrderReturnApplyDTO.doPage(count, salesOrderReturnApplyDTO.getPageNo(), salesOrderReturnApplyDTO.getPageSize());
        List<SalesOrderReturnApplyVO> list = salesOrderReturnApplyDAO.findSalesOrderReturnApply(salesOrderReturnApplyDTO);
        return list;
    }

    @Override
    public SalesOrderReturnVO findSalesOrderReturnByApplyID(String retrunApplyID) {
        return salesOrderReturnApplyDAO.findSalesOrderReturnByApplyID(retrunApplyID);
    }

    @Override
    public void updateReturnOrder(SalesOrderReturnDTO salesOrderReturnDTO) {
        salesOrderReturnApplyDAO.updateReturnOrder(salesOrderReturnDTO);
    }

}
