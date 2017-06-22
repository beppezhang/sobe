package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.account.dao.AccountDetailDAO;
import com.kpluswebup.web.admin.system.dao.AreaDAO;
import com.kpluswebup.web.admin.system.dao.ExpressDAO;
import com.kpluswebup.web.admin.system.dao.ExpressFormatDAO;
import com.kpluswebup.web.admin.system.dao.ExpressFormatItemDAO;
import com.kpluswebup.web.admin.system.dao.ShippingAddressDAO;
import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerDeliveryAddressDAO;
import com.kpluswebup.web.customer.dao.CustomerGradeDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.customer.dao.ShoppingCartDAO;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.FlashSaleDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SalesOrderShippingAddressDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.domain.SupplierItemIDDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderDeliveryAddressDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.order.dao.SalesOrderShippingAddressDAO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.product.dao.SupplierItemDAO;
import com.kpluswebup.web.product.dao.SupplierItemIDDAO;
import com.kpluswebup.web.promotion.dao.FlashSaleDAO;
import com.kpluswebup.web.promotion.dao.PreSaleDAO;
import com.kpluswebup.web.promotion.dao.PromotionDAO;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.supplier.dao.SupplierDAO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ExpressFormatItemVO;
import com.kpluswebup.web.vo.ExpressFormatVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.FlashSaleVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderShippingAddressVO;
import com.kpluswebup.web.vo.SalesOrderTransVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShippingAddressVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpluswebup.web.vo.UserOrderStstusVO;
import com.kpuswebup.common.exception.DuplicateSerialiseNoException;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    private static final Logger          LOGGER = LogManager.getLogger(SalesOrderServiceImpl.class);
    @Autowired
    private SalesOrderDAO                salesOrderDAO;
    @Autowired
    private SalesOrderLineDAO            salesOrderLineDAO;
    @Autowired
    private SalesOrderDeliveryAddressDAO salesOrderDeliveryAddressDAO;
    @Autowired
    private SalesOrderShippingAddressDAO salesOrderShippingAddressDAO;
    @Autowired
    private AreaDAO                      areaDAO;
    @Autowired
    private ItemDAO                      itemDAO;
    @Autowired
    private ExpressDAO                   expressDAO;
    @Autowired
    private ExpressFormatDAO             expressFormatDAO;
    @Autowired
    private ExpressFormatItemDAO         expressFormatItemDAO;
    @Autowired
    private CustomerDeliveryAddressDAO   customerDeliveryAddressDAO;
    @Autowired
    private ShoppingCartDAO              shoppingCartDAO;
    @Autowired
    private SystemCodeDAO                systemCodeDAO;
    @Autowired
    private CachedClient                 cachedClient;
    @Autowired
    private CustomerDAO                  customerDAO;
    @Autowired
    private PromotionDAO                 promotionDAO;
    @Autowired
    private AccountDetailDAO             accountDetailDAO;
    @Autowired
    private CustomerGradeDAO             customerGradeDAO;
    @Autowired
    private PreSaleDAO                   preSaleDAO;
    @Autowired
    private FlashSaleDAO                 flashSaleDAO;
    @Autowired
    private CustomerGroupDAO             customerGroupDAO;
    @Autowired
    private ProductDAO                   productDAO;
    @Autowired
    private ProductCategoryDAO           productCategoryDAO;
    @Autowired
    private ShippingAddressDAO           shippingAddressDAO;
    @Autowired
    private SupplierItemIDDAO            supplierItemIDDAO;
    @Autowired
    private SupplierItemDAO              supplierItemDAO;
    @Autowired
    private SalesOrderLineService        salesOrderLineService;
    @Autowired
    private SupplierDAO supplierDAO;

    public SalesOrderServiceImpl(){

    }

    public List<SalesOrderVO> getSalesOrderList(SalesOrderDTO salesOrderDTO) {
        try {
            notNull(salesOrderDTO, "salesOrderDTO is null");
            Long count = salesOrderDAO.findSalesOrderCount(salesOrderDTO);
            salesOrderDTO.doPage(count, salesOrderDTO.getPageNo(), salesOrderDTO.getPageSize());
            salesOrderDTO.setOrderByClause("ORDER BY s.id DESC");
            List<SalesOrderVO> list = salesOrderDAO.findSalesOrderByPagination(salesOrderDTO);
            List<SalesOrderVO> orderList = new ArrayList<SalesOrderVO>();
            if (list != null && list.size() > 0) {
                for (SalesOrderVO src : list) {
                    SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
                    if (salesOrderDTO.getItemID() != null) {
                        salesOrderLineDTO.setItemID(salesOrderDTO.getItemID());
                    }
                    if (salesOrderDTO.getItemName() != null) {
                        salesOrderLineDTO.setItemName(salesOrderDTO.getItemName());
                    }
                    salesOrderLineDTO.setOrderID(src.getMainID());
                    List<SalesOrderLineVO> list2 = salesOrderLineDAO.findSalesOrderLineBySearch(salesOrderLineDTO);
                    for (SalesOrderLineVO salesOrderLineVO : list2) {
                    	SupplierVO supplierVO = supplierDAO.findSupplierByID(salesOrderLineVO.getSupplierID());
                    	src.setSupplierName(supplierVO.getCompanyName());//商铺
                    	src.setLinkQQ(supplierVO.getLinkQQ());
                    	src.setLinkWangWang(supplierVO.getLinkWangWang());
                        if (salesOrderLineVO.getTransactionPrice() != null && salesOrderLineVO.getItemCount() != null) {
                            salesOrderLineVO.setAllamount(salesOrderLineVO.getTransactionPrice()
                                                          * salesOrderLineVO.getItemCount());
                            salesOrderLineVO.setStatus(salesOrderLineVO.getStatus());
                        }
                    }
                    src.setSalesOrderLineList(list2);
                    if (list2 != null && list2.size() > 0) {
                        orderList.add(src);
                    }
                }
            }
            if (StringUtil.isNotEmpty(salesOrderDTO.getItemID()) || StringUtil.isNotEmpty(salesOrderDTO.getItemName())) {
                return orderList;
            } else {
                return list;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public SalesOrderVO getSalesOrderLine(String mainId) {
        try {
            notNull(mainId, "mainId is null");
            SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(mainId);
            salesOrderVO.setNowDate(new Date());
            SalesOrderShippingAddressVO salesOrderShippingAddressVO = salesOrderShippingAddressDAO.findSalesOrderShippingAddressByOrderID(mainId);
            SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressDAO.findSalesOrderDeliveryAddressByOrderID(mainId);
            salesOrderVO.setSalesOrderShippingAddressVO(salesOrderShippingAddressVO);
            salesOrderVO.setSalesOrderDeliveryAddressVO(salesOrderDeliveryAddressVO);
            if (StringUtil.isNotEmpty(salesOrderVO.getExpressID())) {
                ExpressVO expressVO = expressDAO.findExpressByMainID(salesOrderVO.getExpressID());
                salesOrderVO.setExpressName(expressVO.getName());
                ExpressFormatVO expressFormatVO = expressFormatDAO.findExpressFormatByExpressID(expressVO.getMainID());
                if (expressFormatVO != null) {
                    salesOrderVO.setExpressPicURL(expressFormatVO.getPicURL());
                    List<ExpressFormatItemVO> list = expressFormatItemDAO.findExpressFormatItemByFormatID(expressFormatVO.getMainID());
                    salesOrderVO.setExpressFormatItemList(list);
                }
            }
            AreaVO dvpVO = areaDAO.getAreaByMainID(salesOrderVO.getDvProvinceID());
            if (dvpVO != null) {
                salesOrderVO.setDvProvinceName(dvpVO.getName());
            }
            AreaVO dvcVO = areaDAO.getAreaByMainID(salesOrderVO.getDvCityID());
            if (dvcVO != null) {
                salesOrderVO.setDvCityName(dvcVO.getName());
            }
            AreaVO dvdVO = areaDAO.getAreaByMainID(salesOrderVO.getDvDisctrictID());
            if (dvdVO != null) {
                salesOrderVO.setDvDistrictName(dvdVO.getName());
            }
            AreaVO sppVO = areaDAO.getAreaByMainID(salesOrderVO.getSpProvinceID());
            if (sppVO != null) {
                salesOrderVO.setSpProvinceName(sppVO.getName());
            }
            AreaVO spcVO = areaDAO.getAreaByMainID(salesOrderVO.getSpCityID());
            if (spcVO != null) {
                salesOrderVO.setSpCityName(spcVO.getName());
            }
            AreaVO spdVO = areaDAO.getAreaByMainID(salesOrderVO.getSpDisctrictID());
            if (spdVO != null) {
                salesOrderVO.setSpDistrictName(spdVO.getName());
            }
            List<SalesOrderLineVO> list = salesOrderLineDAO.findSalesOrderLine(mainId);
            if (list != null && list.size() > 0) {
                Integer itemCount = 0;
                for (SalesOrderLineVO salesOrderLineVO : list) {
                    List<ItemDetailVO> detailist = itemDAO.findItemDetailByItemID(salesOrderLineVO.getItemID());
                    if (detailist != null && detailist.size() > 0) {
                        salesOrderLineVO.setItemDetailList(detailist);
                    }
                    if (salesOrderLineVO.getTransactionPrice() != null && salesOrderLineVO.getItemCount() != null) {
                        salesOrderLineVO.setProductPrice(salesOrderLineVO.getTransactionPrice()
                                                         * salesOrderLineVO.getItemCount());
                    }
                    itemCount += salesOrderLineVO.getItemCount();
                    if (salesOrderLineVO.getItemCount() != null && salesOrderLineVO.getTransactionPrice() != null) {
                        salesOrderLineVO.setAllamount(salesOrderLineVO.getItemCount()
                                                      * salesOrderLineVO.getTransactionPrice());
                    }
                }
                salesOrderVO.setItemCount(itemCount);
            }
            salesOrderVO.setSalesOrderLineList(list);
            return salesOrderVO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public SalesOrderVO getSupplierSalesOrderLine(String mainId, String supplierID) {
        try {
            notNull(mainId, "mainId is null");
            SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(mainId);
            salesOrderVO.setNowDate(new Date());
            SalesOrderShippingAddressVO salesOrderShippingAddressVO = salesOrderShippingAddressDAO.findSalesOrderShippingAddressByOrderID(mainId);
            SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressDAO.findSalesOrderDeliveryAddressByOrderID(mainId);
            salesOrderVO.setSalesOrderShippingAddressVO(salesOrderShippingAddressVO);
            salesOrderVO.setSalesOrderDeliveryAddressVO(salesOrderDeliveryAddressVO);
            if (StringUtil.isNotEmpty(salesOrderVO.getExpressID())) {
                ExpressVO expressVO = expressDAO.findExpressByMainID(salesOrderVO.getExpressID());
                salesOrderVO.setExpressName(expressVO.getName());
                ExpressFormatVO expressFormatVO = expressFormatDAO.findExpressFormatByExpressID(expressVO.getMainID());
                if (expressFormatVO != null) {
                    salesOrderVO.setExpressPicURL(expressFormatVO.getPicURL());
                    List<ExpressFormatItemVO> list = expressFormatItemDAO.findExpressFormatItemByFormatID(expressFormatVO.getMainID());
                    salesOrderVO.setExpressFormatItemList(list);
                }
            }
            AreaVO dvpVO = areaDAO.getAreaByMainID(salesOrderVO.getDvProvinceID());
            if (dvpVO != null) {
                salesOrderVO.setDvProvinceName(dvpVO.getName());
            }
            AreaVO dvcVO = areaDAO.getAreaByMainID(salesOrderVO.getDvCityID());
            if (dvcVO != null) {
                salesOrderVO.setDvCityName(dvcVO.getName());
            }
            AreaVO dvdVO = areaDAO.getAreaByMainID(salesOrderVO.getDvDisctrictID());
            if (dvdVO != null) {
                salesOrderVO.setDvDistrictName(dvdVO.getName());
            }
            AreaVO sppVO = areaDAO.getAreaByMainID(salesOrderVO.getSpProvinceID());
            if (sppVO != null) {
                salesOrderVO.setSpProvinceName(sppVO.getName());
            }
            AreaVO spcVO = areaDAO.getAreaByMainID(salesOrderVO.getSpCityID());
            if (spcVO != null) {
                salesOrderVO.setSpCityName(spcVO.getName());
            }
            AreaVO spdVO = areaDAO.getAreaByMainID(salesOrderVO.getSpDisctrictID());
            if (spdVO != null) {
                salesOrderVO.setSpDistrictName(spdVO.getName());
            }
            List<SalesOrderLineVO> list = salesOrderDAO.findOrderLineByOrderAndSupplier(salesOrderVO.getMainID(),
                                                                                        supplierID);
            if (list != null && list.size() > 0) {
                Integer itemCount = 0;
                Double totalAmount = 0.0;
                for (SalesOrderLineVO salesOrderLineVO : list) {
                    List<ItemDetailVO> detailist = itemDAO.findItemDetailByItemID(salesOrderLineVO.getItemID());
                    if (detailist != null && detailist.size() > 0) {
                        salesOrderLineVO.setItemDetailList(detailist);
                    }
                    if (salesOrderLineVO.getTransactionPrice() != null && salesOrderLineVO.getItemCount() != null) {
                        salesOrderLineVO.setProductPrice(salesOrderLineVO.getTransactionPrice()
                                                         * salesOrderLineVO.getItemCount());
                    }
                    itemCount += salesOrderLineVO.getItemCount();
                    totalAmount += salesOrderLineVO.getTotalAmount();
                    if (salesOrderLineVO.getItemCount() != null && salesOrderLineVO.getTransactionPrice() != null) {
                        salesOrderLineVO.setAllamount(salesOrderLineVO.getItemCount()
                                                      * salesOrderLineVO.getTransactionPrice());
                    }
                }
                salesOrderVO.setItemCount(itemCount);
               // salesOrderVO.setTotalAmount(totalAmount);
                salesOrderVO.setTotalAmount(salesOrderVO.getTotalAmount());
            }
            salesOrderVO.setSalesOrderLineList(list);
            return salesOrderVO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public void updateSalesOrder(SalesOrderDTO salesOrderDTO) {
        try {
            notNull(salesOrderDTO, "salesOrderDTO is null");
            salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Transactional
    public void updateSalesOrder(SalesOrderVO salesOrder, String priceAndItemCountStr) {
        try {
            notNull(salesOrder, "salesOrder is null");
            notNull(priceAndItemCountStr, "priceAndItemCountStr is null");
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            salesOrderDTO.setId(salesOrder.getId());
            salesOrderDTO.setMemo(salesOrder.getMemo());
            salesOrderDTO.setExpressFee(salesOrder.getExpressFee());
            salesOrderDTO.setProductAmount(salesOrder.getProductAmount());
            salesOrderDTO.setModifyTime(new Date());
            SalesOrderDeliveryAddressDTO deliveryAddressDTO = new SalesOrderDeliveryAddressDTO();
            deliveryAddressDTO.setSalesOrderID(salesOrder.getMainID());
            deliveryAddressDTO.setName(salesOrder.getDvName());
            deliveryAddressDTO.setMobile(salesOrder.getDvMobile());
            deliveryAddressDTO.setAddress(salesOrder.getDvAddress());
            deliveryAddressDTO.setModifyTime(new Date());
            List<SalesOrderLineDTO> list = new ArrayList<SalesOrderLineDTO>();
            if (StringUtil.isNotEmpty(priceAndItemCountStr)) {
                String[] salesOrderLineAry = priceAndItemCountStr.split("#");
                for (String src : salesOrderLineAry) {
                    String[] salesOrderLineStr = src.split(",");
                    if (salesOrderLineStr.length == 3) {
                        SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
                        salesOrderLineDTO.setId(Long.valueOf(salesOrderLineStr[0]));
                        salesOrderLineDTO.setTransactionPrice(Double.valueOf(salesOrderLineStr[1]));
                        salesOrderLineDTO.setItemCount(Integer.valueOf(salesOrderLineStr[2]));
                        list.add(salesOrderLineDTO);
                    }
                }
            }
            salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
            salesOrderDeliveryAddressDAO.updateBySalesOrderDeliveryAddressByOrderID(deliveryAddressDTO);
            for (SalesOrderLineDTO src : list) {
                salesOrderLineDAO.updateByPrimaryKeySelective(src);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public void updateLogisticsInfo(SalesOrderVO salesOrder) {
        try {
            notNull(salesOrder, "salesOrder is null");
            SalesOrderDeliveryAddressDTO dvDTO = new SalesOrderDeliveryAddressDTO();
            dvDTO.setSalesOrderID(salesOrder.getMainID());
            dvDTO.setName(salesOrder.getDvName());
            dvDTO.setMobile(salesOrder.getDvMobile());
            dvDTO.setTelephone(salesOrder.getDvTelephone());
            dvDTO.setProvinceID(salesOrder.getDvProvinceID());
            dvDTO.setCityID(salesOrder.getDvCityID());
            dvDTO.setDisctrictID(salesOrder.getDvDisctrictID());
            dvDTO.setAddress(salesOrder.getDvAddress());
            dvDTO.setZip(salesOrder.getDvZip());
            dvDTO.setModifier(salesOrder.getModifier());
            SalesOrderShippingAddressDTO spDTO = new SalesOrderShippingAddressDTO();
            spDTO.setSalesOrderID(salesOrder.getMainID());
            spDTO.setName(salesOrder.getSpName());
            spDTO.setMobile(salesOrder.getSpMobile());
            spDTO.setTelephone(salesOrder.getSpTelephone());
            spDTO.setProvinceID(salesOrder.getSpProvinceID());
            spDTO.setCityID(salesOrder.getSpCityID());
            spDTO.setDisctrictID(salesOrder.getSpDisctrictID());
            spDTO.setAddress(salesOrder.getSpAddress());
            spDTO.setZip(salesOrder.getSpZip());
            spDTO.setModifier(salesOrder.getModifier());
            salesOrderDeliveryAddressDAO.updateBySalesOrderDeliveryAddressByOrderID(dvDTO);
            salesOrderShippingAddressDAO.updateBySalesOrderShippingAddressByOrderID(spDTO);
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            if (StringUtil.isNotEmpty(salesOrder.getExpressID())) {
                salesOrderDTO.setExpressID(salesOrder.getExpressID());
            }
            if (StringUtil.isNotEmpty(salesOrder.getExpressNumber())) {
                salesOrderDTO.setExpressNumber(salesOrder.getExpressNumber());
            }
            if (StringUtil.isNotEmpty(salesOrder.getMainID())) {
                salesOrderDTO.setMainID(salesOrder.getMainID());
            }
            salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public Boolean doPrint(String mainIDs, Integer status) {
        try {
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
                SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(mainID);
                salesOrderDTO.setMainID(mainID);
                if (status == 1) {
                    salesOrderDTO.setPrintStatus(salesOrderVO.getPrintStatus() + 1);
                    salesOrderDAO.updateSalesOrderPrintStatus(salesOrderDTO);
                } else if (status == 2) {
                    salesOrderDTO.setInvoiceStatus(salesOrderVO.getInvoiceStatus() + 1);
                    salesOrderDAO.updateSalesOrderInvoiceStatus(salesOrderDTO);
                } else if (status == 3) {
                    salesOrderDTO.setCarriageStatus(salesOrderVO.getCarriageStatus() + 1);
                    salesOrderDAO.updateSalesOrderCarriageStatus(salesOrderDTO);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updateSalesOrderStatus(String orderIDs, String orderStatus,String expressNumber) {
        notNull(orderIDs, "orderIDs is null");
        notNull(orderStatus, "orderStatus is null");
        String ids[] = orderIDs.split(",");
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        for (String orderID : ids) {
            /*
             * // 出库操作判断 if (orderStatus.equals("3")) { List<SupplierItemIDVO> itemIDList =
             * supplierItemIDDAO.findSupplierItemIDByOrderID(orderID); for (SupplierItemIDVO supplierItemIDVO :
             * itemIDList) { if (supplierItemIDVO.getStatus() == 0) return 1000; else if
             * (supplierItemIDVO.getSerialiseNO() == null) return 1001; } }
             */
            // 取消订单库存变更
            if (orderStatus.equals("0")) {
                List<SalesOrderLineVO> list = salesOrderLineDAO.findSalesOrderLine(orderID);
                if (list != null && list.size() > 0) {
                    for (SalesOrderLineVO salesOrderLineVO : list) {
                        SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
                        supplierItemDTO.setSupplierID(salesOrderLineVO.getSupplierID());
                        supplierItemDTO.setItemID(salesOrderLineVO.getItemID());
                        SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
                        if (supplierItemVO != null) {
                            supplierItemDTO.setStock(supplierItemVO.getStock() + salesOrderLineVO.getItemCount());
                            supplierItemDTO.setSaleCount(supplierItemVO.getSaleCount()
                                                         - salesOrderLineVO.getItemCount());
                            supplierItemDAO.updateSupplierItemStockAndSaleCount(supplierItemDTO);
                            ItemDTO itemDTO = new ItemDTO();
                            ItemVO itemVO = itemDAO.findItemById(supplierItemVO.getMainID());
                            itemDTO.setMainID(itemVO.getMainID());
                            if ((itemVO.getSalesVolume() - salesOrderLineVO.getItemCount()) > 0) {
                                itemDTO.setSalesVolume(itemVO.getSalesVolume() - salesOrderLineVO.getItemCount());
                            } else {
                                itemDTO.setSalesVolume(0);
                            }
                            itemDAO.updateItemByMainID(itemDTO);
                        }
                    }
                }
                /*
                 * SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(orderID); if
                 * (salesOrderVO.getScoreTotal() != null) { AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                 * accountDetailDTO.setSerialNumber(salesOrderVO.getMainID()); accountDetailDTO.setDetailType(8);
                 * accountDetailDTO.setAmount( Double.valueOf(salesOrderVO.getScoreTotal()));
                 * accountDetailDTO.setAccountType(2); accountDetailDTO.setPaymentType(3);
                 * accountDetailDTO.setDescription("取消订单退积分"); accountDetailDTO.setObjID(salesOrderVO.getMainID());
                 * accountDetailDTO.setCustomerID(salesOrderVO.getCustomerID()); accountDetailDTO.setStatus(1);
                 * CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.REFUNDDETAILID); if
                 * (codeConfigVO != null) { String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
                 * accountDetailDTO.setMainID(mainID); } accountDetailDAO.insertMemberScore(accountDetailDTO);
                 * CustomerVO customerVO = customerDAO.findCustomerByMainID(salesOrderVO .getCustomerID()); CustomerDTO
                 * customerDTO = new CustomerDTO(); customerDTO.setMainID(customerVO.getMainID());
                 * customerDTO.setScore(customerVO.getScore() + salesOrderVO.getScoreTotal());
                 * customerDAO.insertCustomer(customerDTO); }
                 */
                //salesOrderDAO.updateSupplierItemIdByOrderID(orderID);
                // 款到发货判断
            } else if (orderStatus.equals("1")) {
                SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(orderID);
                if (salesOrderVO.getPaymentType() == 1) return 2000;
            }
            // 更新订单状态
            salesOrderDTO.setMainID(orderID);
            salesOrderDTO.setOrderStatus(Integer.valueOf(orderStatus));
            salesOrderDTO.setShipmentsTime(new Date());
            salesOrderDTO.setExpressNumber(expressNumber);
            salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
        }

        return 0;
    }

    @Override
    public void editSalesOrder(SalesOrderDTO salesOrderDTO) {
        salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
    }

    @Override
    public void exportSalesOrder(HttpServletResponse response, SalesOrderDTO salesOrderDTO) {
        try {
            List<SalesOrderVO> list = salesOrderDAO.findAllSalesOrder(salesOrderDTO);
            HSSFWorkbook exportBook = this.exportSalesOrderInfo(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("订单信息.xls").getBytes("gb2312"), "ISO8859_1"));
            OutputStream out = response.getOutputStream();
            exportBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportSalesOrderBySupplier(HttpServletResponse response, SalesOrderDTO salesOrderDTO) {
        try {
            List<SalesOrderVO> list = salesOrderDAO.findOrdersBySupplierID(salesOrderDTO);
            for (SalesOrderVO salesOrderVO : list) {
                List<SalesOrderLineVO> lineList = salesOrderDAO.findOrderLineByOrderAndSupplier(salesOrderVO.getMainID(),
                                                                                                salesOrderDTO.getSupplierID());
                salesOrderVO.setSalesOrderLineList(lineList);
            }
            HSSFWorkbook exportBook = this.exportSalesOrderBySupplierInfo(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("商家订单信息.xls").getBytes("gb2312"), "ISO8859_1"));
            OutputStream out = response.getOutputStream();
            exportBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HSSFWorkbook exportSalesOrderInfo(List<SalesOrderVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("订单信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 18 * 128 * 8);
        sheet.setColumnWidth(3, 18 * 128);
        /*sheet.setColumnWidth(4, 18 * 256);*/
        sheet.setColumnWidth(4, 18 * 128 * 2);
        sheet.setColumnWidth(5, 18 * 200);
        sheet.setColumnWidth(6, 18 * 128);
        sheet.setColumnWidth(7, 18 * 128);
        sheet.setColumnWidth(8, 18 * 128);
        sheet.setColumnWidth(9, 18 * 256);
       /* sheet.setColumnWidth(11, 18 * 256);
        sheet.setColumnWidth(12, 18 * 256);
        sheet.setColumnWidth(13, 18 * 256);*/
        sheet.setColumnWidth(10, 18 * 256);
        sheet.setColumnWidth(11, 18 * 128 * 4);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // HSSFCellStyle style1 = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);

        HSSFCellStyle styledata = workbook.createCellStyle();
        styledata.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 产生表格标题行

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("用户名");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("商品名称");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("商品数量");

        /*cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("串号");*/

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("下单时间");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("订单金额");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("支付方式");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("支付状态");

        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("订单状态");

        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("供应商");

       /* cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("箱数");

        cell = row.createCell(12);
        cell.setCellStyle(style);
        cell.setCellValue("物流单号");

        cell = row.createCell(13);
        cell.setCellStyle(style);
        cell.setCellValue("重量");*/

        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("发货时间");

        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("收货地址");
        int rowNum = 0;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                SalesOrderVO salesOrderVO = list.get(i);
                List<SalesOrderLineVO> lineList = salesOrderLineDAO.findSalesOrderLine(salesOrderVO.getMainID());
                if (lineList != null && lineList.size() > 0) {
                    int lineSize = lineList.size();
                    int totalNCount = 0;
                    for (SalesOrderLineVO salesOrderLineVO2 : lineList) {
                        totalNCount += salesOrderLineVO2.getItemCount();
                    }
                    for (int j = 0; j < lineSize; j++) {
                        SalesOrderLineVO salesOrderLineVO = lineList.get(j);
                        if (salesOrderVO.getOrderStatus() > 3) {
                            rowNum = statusGreatThanThree(sheet, rowNum, style, salesOrderVO, lineSize, j,
                                                          salesOrderLineVO, totalNCount);
                        } else {
                            rowNum = statusLessThan4(sheet, rowNum, style, salesOrderVO, lineSize, j, salesOrderLineVO);
                        }

                    }
                }
            }
        }
        return workbook;
    }

    private int statusLessThan4(HSSFSheet sheet, int rowNum, HSSFCellStyle style, SalesOrderVO salesOrderVO,
                                int lineSize, int j, SalesOrderLineVO salesOrderLineVO) {
        HSSFCell cell;
        HSSFRow content = sheet.createRow((rowNum++) + 1);
        cell = content.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue(salesOrderVO.getMainID());
        cell = content.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue(salesOrderVO.getCustomerUserName());
        cell = content.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue(salesOrderLineVO.getItemName());
        cell = content.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue(salesOrderLineVO.getItemCount());
        /*cell = content.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("");*/
        cell = content.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getCreateTime()));
        cell = content.createCell(5);
        cell.setCellStyle(style);
        if (salesOrderVO.getTotalAmount() != null) {
            cell.setCellValue(salesOrderVO.getTotalAmount());
        }
        cell = content.createCell(6);
        cell.setCellStyle(style);
        if (salesOrderVO.getPaymentType() != null) {
            if (salesOrderVO.getPaymentType() == 1) {
                cell.setCellValue("款到发货");
            } else if (salesOrderVO.getPaymentType() == 2) {
                cell.setCellValue("货到付款");
            }
        }
        cell = content.createCell(7);
        cell.setCellStyle(style);
        if (salesOrderVO.getPaymentStatus() != null) {
            if (salesOrderVO.getPaymentStatus() == 0) {
                cell.setCellValue("未支付");
            } else if (salesOrderVO.getPaymentStatus() == 1) {
                cell.setCellValue("已支付");
            } else if (salesOrderVO.getPaymentStatus() == 3) {
                cell.setCellValue("部分支付");
            }
        }
        cell = content.createCell(8);
        cell.setCellStyle(style);
        if (salesOrderVO.getOrderStatus() != null) {
            if (salesOrderVO.getOrderStatus() == 0) {
                cell.setCellValue("订单已取消");
            } else if (salesOrderVO.getOrderStatus() == 1) {
                cell.setCellValue("待确认");
            } else if (salesOrderVO.getOrderStatus() == 2) {
                cell.setCellValue("待出库");
            } else if (salesOrderVO.getOrderStatus() == 3) {
                cell.setCellValue("待发货");
            } else if (salesOrderVO.getOrderStatus() == 4) {
                cell.setCellValue("已发货");
            } else if (salesOrderVO.getOrderStatus() == 5) {
                cell.setCellValue("已收货");
            } else if (salesOrderVO.getOrderStatus() == 6) {
                cell.setCellValue("已完成");
            } else if (salesOrderVO.getOrderStatus() == 7) {
                cell.setCellValue("售后申请中");
            } else if (salesOrderVO.getOrderStatus() == 8) {
                cell.setCellValue("售后处理中");
            } else if (salesOrderVO.getOrderStatus() == 9) {
                cell.setCellValue("已售后");
            } else if (salesOrderVO.getOrderStatus() == 10) {
                cell.setCellValue("售后已取消");
            } else if (salesOrderVO.getOrderStatus() == 11) {
                cell.setCellValue("售后申请被拒绝");
            }
            cell = content.createCell(9);
            cell.setCellStyle(style);
            cell.setCellValue(salesOrderLineVO.getSupplierName());
            /*cell = content.createCell(11);
            cell.setCellStyle(style);
            if (salesOrderVO.getPackageNum() != null) {
                cell.setCellValue(salesOrderVO.getPackageNum());
            } else {
                cell.setCellValue("");
            }
            cell = content.createCell(12);
            cell.setCellStyle(style);
            if (salesOrderVO.getExpressNumber() != null) {
                cell.setCellValue(salesOrderVO.getExpressNumber());
            } else {
                cell.setCellValue("");
            }
            cell = content.createCell(13);
            cell.setCellStyle(style);
            if (salesOrderVO.getWeight() != null) {
                cell.setCellValue(salesOrderVO.getWeight());
            } else {
                cell.setCellValue("");
            }*/
            cell = content.createCell(10);
            cell.setCellStyle(style);
            if (salesOrderVO.getShipmentsTime() != null) {
                cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getShipmentsTime()));
            } else {
                cell.setCellValue("");
            }
            cell = content.createCell(11);
            cell.setCellStyle(style);
            if (salesOrderVO.getDvProvinceName() != null && salesOrderVO.getDvCityName() != null
                && salesOrderVO.getDvDistrictName() != null) {
                cell.setCellValue(salesOrderVO.getDvProvinceName() + salesOrderVO.getDvCityName()
                                  + salesOrderVO.getDvDistrictName());
            } else {
                cell.setCellValue("");
            }
        }
        if (lineSize > 1 && j == 0) {
            int rowNumStart = rowNum + 1;
            int rowNumEnd = rowNumStart + lineSize - 1;
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + rowNumStart + ":$A$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$B$" + rowNumStart + ":$B$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$F$" + rowNumStart + ":$F$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$G$" + rowNumStart + ":$G$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$H$" + rowNumStart + ":$H$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$I$" + rowNumStart + ":$I$" + rowNumEnd));// 指定合并区域
            sheet.addMergedRegion(CellRangeAddress.valueOf("$J$" + rowNumStart + ":$J$" + rowNumEnd));// 指定合并区域
        }
        return rowNum;
    }

    private int statusGreatThanThree(HSSFSheet sheet, int rowNum, HSSFCellStyle style, SalesOrderVO salesOrderVO,
                                     int lineSize, int j, SalesOrderLineVO salesOrderLineVO, int totalNCount) {
        HSSFCell cell;

        List<SupplierItemIDVO> supplierItemIDList = supplierItemIDDAO.findSupplierItemIDByOrderLineID(salesOrderLineVO.getMainID());
        for (int k = 0, count = supplierItemIDList.size(); k < count; k++) {
            SupplierItemIDVO itemID = supplierItemIDList.get(k);
            HSSFRow content = sheet.createRow((rowNum++) + 1);
            cell = content.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(salesOrderVO.getMainID());
            cell = content.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(salesOrderVO.getCustomerUserName());
            cell = content.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(salesOrderLineVO.getItemName());
            cell = content.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(1);
            /*cell = content.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(itemID.getSerialiseNO());*/
            cell = content.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getCreateTime()));
            cell = content.createCell(5);
            cell.setCellStyle(style);
            if (salesOrderVO.getTotalAmount() != null) {
                cell.setCellValue(salesOrderVO.getTotalAmount());
            }
            cell = content.createCell(6);
            cell.setCellStyle(style);
            if (salesOrderVO.getPaymentType() != null) {
                if (salesOrderVO.getPaymentType() == 1) {
                    cell.setCellValue("款到发货");
                } else if (salesOrderVO.getPaymentType() == 2) {
                    cell.setCellValue("货到付款");
                }
            }
            cell = content.createCell(7);
            cell.setCellStyle(style);
            if (salesOrderVO.getPaymentStatus() != null) {
                if (salesOrderVO.getPaymentStatus() == 0) {
                    cell.setCellValue("未支付");
                } else if (salesOrderVO.getPaymentStatus() == 1) {
                    cell.setCellValue("已支付");
                } else if (salesOrderVO.getPaymentStatus() == 3) {
                    cell.setCellValue("部分支付");
                }
            }
            cell = content.createCell(8);
            cell.setCellStyle(style);
            if (salesOrderVO.getOrderStatus() != null) {
                if (salesOrderVO.getOrderStatus() == 0) {
                    cell.setCellValue("订单已取消");
                } else if (salesOrderVO.getOrderStatus() == 1) {
                    cell.setCellValue("待确认");
                } else if (salesOrderVO.getOrderStatus() == 2) {
                    cell.setCellValue("待出库");
                } else if (salesOrderVO.getOrderStatus() == 3) {
                    cell.setCellValue("待发货");
                } else if (salesOrderVO.getOrderStatus() == 4) {
                    cell.setCellValue("已发货");
                } else if (salesOrderVO.getOrderStatus() == 5) {
                    cell.setCellValue("已收货");
                } else if (salesOrderVO.getOrderStatus() == 6) {
                    cell.setCellValue("已完成");
                } else if (salesOrderVO.getOrderStatus() == 7) {
                    cell.setCellValue("售后申请中");
                } else if (salesOrderVO.getOrderStatus() == 8) {
                    cell.setCellValue("售后处理中");
                } else if (salesOrderVO.getOrderStatus() == 9) {
                    cell.setCellValue("已售后");
                } else if (salesOrderVO.getOrderStatus() == 10) {
                    cell.setCellValue("售后已取消");
                } else if (salesOrderVO.getOrderStatus() == 11) {
                    cell.setCellValue("售后申请被拒绝");
                }
                cell = content.createCell(9);
                cell.setCellStyle(style);
                cell.setCellValue(salesOrderLineVO.getSupplierName());
                /*cell = content.createCell(11);
                cell.setCellStyle(style);
                if (salesOrderVO.getPackageNum() != null) {
                    cell.setCellValue(salesOrderVO.getPackageNum());
                } else {
                    cell.setCellValue("");
                }
                cell = content.createCell(12);
                cell.setCellStyle(style);
                if (salesOrderVO.getExpressNumber() != null) {
                    cell.setCellValue(salesOrderVO.getExpressNumber());
                } else {
                    cell.setCellValue("");
                }
                cell = content.createCell(13);
                cell.setCellStyle(style);
                if (salesOrderVO.getWeight() != null) {
                    cell.setCellValue(salesOrderVO.getWeight());
                } else {
                    cell.setCellValue("");
                }*/
                cell = content.createCell(10);
                cell.setCellStyle(style);
                if (salesOrderVO.getShipmentsTime() != null) {
                    cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getShipmentsTime()));
                } else {
                    cell.setCellValue("");
                }
                cell = content.createCell(11);
                cell.setCellStyle(style);
                if (salesOrderVO.getDvProvinceName() != null && salesOrderVO.getDvCityName() != null
                    && salesOrderVO.getDvDistrictName() != null && salesOrderVO.getDvAddress() != null) {
                    cell.setCellValue(salesOrderVO.getDvProvinceName() + salesOrderVO.getDvCityName()
                                      + salesOrderVO.getDvDistrictName() + salesOrderVO.getDvAddress());
                } else {
                    cell.setCellValue("");
                }
            }
            if (totalNCount > 1 && j == 0 && k == 0) {
                int rowNumStart = rowNum + 1;
                int rowNumEnd = rowNumStart + totalNCount - 1;
                sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + rowNumStart + ":$A$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$B$" + rowNumStart + ":$B$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$F$" + rowNumStart + ":$F$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$G$" + rowNumStart + ":$G$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$H$" + rowNumStart + ":$H$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$I$" + rowNumStart + ":$I$" + rowNumEnd));// 指定合并区域
                sheet.addMergedRegion(CellRangeAddress.valueOf("$J$" + rowNumStart + ":$J$" + rowNumEnd));// 指定合并区域
            }
        }
        return rowNum;
    }


    public HSSFWorkbook exportSupplierSalesOrderInfo(List<SalesOrderVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("订单信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 128);
        sheet.setColumnWidth(2, 18 * 128);
        sheet.setColumnWidth(3, 18 * 128);
        sheet.setColumnWidth(4, 18 * 200);

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);

        HSSFCellStyle styledata = workbook.createCellStyle();
        styledata.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 产生表格标题行

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("买家");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("下单时间");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("订单金额");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("订单状态");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                SalesOrderVO salesOrderVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(salesOrderVO.getMainID());
                cell = content.createCell(1);
                cell.setCellValue(salesOrderVO.getCustomerUserName());
                cell = content.createCell(2);
                cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getCreateTime()));
                cell = content.createCell(3);
                if (salesOrderVO.getTotalAmount() != null) {
                    cell.setCellValue(salesOrderVO.getTotalAmount());
                }
                cell = content.createCell(4);
                if (salesOrderVO.getOrderStatus() != null) {
                    if (salesOrderVO.getOrderStatus() == 0) {
                        cell.setCellValue("订单已取消");
                    } else if (salesOrderVO.getOrderStatus() == 1) {
                        cell.setCellValue("待确认");
                    } else if (salesOrderVO.getOrderStatus() == 2) {
                        cell.setCellValue("待出库");
                    } else if (salesOrderVO.getOrderStatus() == 3) {
                        cell.setCellValue("待发货");
                    } else if (salesOrderVO.getOrderStatus() == 4) {
                        cell.setCellValue("已发货");
                    } else if (salesOrderVO.getOrderStatus() == 5) {
                        cell.setCellValue("已收货");
                    } else if (salesOrderVO.getOrderStatus() == 6) {
                        cell.setCellValue("已完成");
                    } else if (salesOrderVO.getOrderStatus() == 7) {
                        cell.setCellValue("售后申请中");
                    } else if (salesOrderVO.getOrderStatus() == 8) {
                        cell.setCellValue("售后处理中");
                    } else if (salesOrderVO.getOrderStatus() == 9) {
                        cell.setCellValue("已售后");
                    } else if (salesOrderVO.getOrderStatus() == 10) {
                        cell.setCellValue("售后已取消");
                    } else if (salesOrderVO.getOrderStatus() == 11) {
                        cell.setCellValue("售后申请被拒绝");
                    }
                }
            }
        }
        return workbook;
    }

    public HSSFWorkbook exportSalesOrderBySupplierInfo(List<SalesOrderVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("订单信息");
        sheet.setColumnWidth(0, 18 * 300);
        sheet.setColumnWidth(1, 18 * 350);
        sheet.setColumnWidth(2, 18 * 128);
        sheet.setColumnWidth(3, 18 * 128);
        sheet.setColumnWidth(4, 18 * 200);
        sheet.setColumnWidth(5, 18 * 200);
        sheet.setColumnWidth(6, 18 * 300);
        sheet.setColumnWidth(7, 18 * 200);

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);

        HSSFCellStyle styledata = workbook.createCellStyle();
        styledata.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 产生表格标题行

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("商品");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("单价");
        
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("数量");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("实付款");
        
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("成交时间");
        
        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("收货人账号");
        

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                SalesOrderVO salesOrderVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                for(int f = 0; f <salesOrderVO.getSalesOrderLineList().size();f++){
                    SalesOrderLineVO salesOrderLineVO =  salesOrderVO.getSalesOrderLineList().get(f);
                    cell = content.createCell(0);
                    cell.setCellValue(salesOrderVO.getMainID());
                    
                    cell = content.createCell(1);
                    cell.setCellValue(salesOrderLineVO.getItemName());
                    
                    cell = content.createCell(2);
                    cell.setCellValue(salesOrderLineVO.getSalesPrice());
                    
                    cell = content.createCell(3);
                    if (salesOrderLineVO.getItemCount() != null) {
                        cell.setCellValue(salesOrderLineVO.getItemCount());
                    }
                    cell = content.createCell(4);
                    if (salesOrderVO.getOrderStatus() != null) {
                        if (salesOrderVO.getOrderStatus() == 0) {
                            cell.setCellValue("订单已取消");
                        } else if (salesOrderVO.getOrderStatus() == 1) {
                            cell.setCellValue("待确认");
                        } else if (salesOrderVO.getOrderStatus() == 2) {
                            cell.setCellValue("待出库");
                        } else if (salesOrderVO.getOrderStatus() == 3) {
                            cell.setCellValue("待发货");
                        } else if (salesOrderVO.getOrderStatus() == 4) {
                            cell.setCellValue("已发货");
                        } else if (salesOrderVO.getOrderStatus() == 5) {
                            cell.setCellValue("已收货");
                        } else if (salesOrderVO.getOrderStatus() == 6) {
                            cell.setCellValue("已完成");
                        } else if (salesOrderVO.getOrderStatus() == 7) {
                            cell.setCellValue("售后申请中");
                        } else if (salesOrderVO.getOrderStatus() == 8) {
                            cell.setCellValue("售后处理中");
                        } else if (salesOrderVO.getOrderStatus() == 9) {
                            cell.setCellValue("已售后");
                        } else if (salesOrderVO.getOrderStatus() == 10) {
                            cell.setCellValue("售后已取消");
                        } else if (salesOrderVO.getOrderStatus() == 11) {
                            cell.setCellValue("售后申请被拒绝");
                        }
                    }
                    cell = content.createCell(5);
                    cell.setCellValue(salesOrderLineVO.getSalesPrice());
                    
                    cell = content.createCell(6);
                    cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getCreateTime()));
                    cell = content.createCell(7);
                    cell.setCellValue(salesOrderVO.getCustomerUserName());
                }
            }
        }
        return workbook;
    }

    @Override
    public UserOrderStstusVO findUserOrderStstusVO(String userMainID) {

        return salesOrderDAO.findUserOrderStstusVO(userMainID);
    }

    @Override
    public List<SalesOrderVO> getUserPresellList(SalesOrderDTO salesOrderDTO) {
        try {
            notNull(salesOrderDTO, "salesOrderDTO is null");
            Long count = salesOrderDAO.findUserOrderPresellCount(salesOrderDTO);
            salesOrderDTO.doPage(count, salesOrderDTO.getPageNo(), salesOrderDTO.getPageSize());
            salesOrderDTO.setOrderByClause("ORDER BY s.id DESC");
            List<SalesOrderVO> list = salesOrderDAO.getUserOrderPresellPagination(salesOrderDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean isBuyByCustomer(String customerID, String productID) {
        notNull(customerID, "customerID is null");
        notNull(productID, "productID is null");
        SalesOrderDTO dto = new SalesOrderDTO();
        dto.setCustomerID(customerID);
        dto.setItemID(productID);
        List<SalesOrderLineVO> list = salesOrderDAO.findOrderLineByCustomer(dto);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public StringBuffer addSalesOrder(String customerID, String cartIDs, String addressID, String paymentType,
                                String orderAmount, String scoreAll, String memo, String itemCount, String itemID,
                                String supplierID, String itemType, String orderType, String objID, String invoiceType,
                                String invoiceTitle, String generateType, String orderID, String allFreight, String supplierFreightStr) throws Exception {

//        SalesOrderVO tempOrder = cachedClient.get(customerID + orderID);
    	
        String ids[] = cartIDs.split(",");
        List<ShoppingCartVO> shopCartList = new ArrayList<ShoppingCartVO>();
        for (String id : ids) {
            ShoppingCartVO shoppingCartVO = shoppingCartDAO.findShoppingCartByID(Long.valueOf(id));
            shopCartList.add(shoppingCartVO);
        }
        Map<String,List<ShoppingCartVO>> maps = new HashMap<String,List<ShoppingCartVO>>();
        for(ShoppingCartVO s:shopCartList){
        	List<ShoppingCartVO> temp = new ArrayList<ShoppingCartVO>();
        	 String sid1= s.getSupplierID();
             String siname1= s.getSupplierName();
             for(ShoppingCartVO shoppingCartVO1 : shopCartList){
             	//当前店铺对应的购物车集合
             	String sid2 = shoppingCartVO1.getSupplierID();
             	if(sid1.equals(sid2)){
             		temp.add(shoppingCartVO1);
             	}
             }
             maps.put(sid1+"_"+siname1, temp);
        }
        
        //根据物流模板计算运费
        Map<String, Double> supplierFreightMap = new HashMap<String, Double>();
        String[] supplierFreightAll = supplierFreightStr.split("\\|");
        for (int i = 0; i < supplierFreightAll.length; i++) {
        	String[] supplierFreight = supplierFreightAll[i].split(":");
        	supplierFreightMap.put(supplierFreight[0], Double.parseDouble(supplierFreight[1]));
		}        
        
        StringBuffer ordersList = new StringBuffer();
//        cachedClient.delete(customerID + orderID);
        Set<String> set = maps.keySet();
		for(String string : set){
		   List<ShoppingCartVO> temp1= maps.get(string);
		   double supplierFreight = supplierFreightMap.get(string);
		   Double amount = 0d;
		   Integer count = 0;
           Integer score = 0;
           Double weight = 0d;
		   for (ShoppingCartVO shoppingCartVO : temp1) {
               ItemVO itemVO = itemDAO.findItemById(shoppingCartVO.getItemID());
               count += shoppingCartVO.getItemCount();
              // weight += itemVO.getWeight();
               if (shoppingCartVO.getType() == 1) {
                   amount += shoppingCartVO.getItemCount() * shoppingCartVO.getSalesPrice();
               } else if (shoppingCartVO.getType() == 2) {
                   score += shoppingCartVO.getItemCount() * itemVO.getScore();
               } else if (shoppingCartVO.getType() == 3) {
                   amount += shoppingCartVO.getItemCount() * itemVO.getScorePrice();
                   score += shoppingCartVO.getItemCount() * shoppingCartVO.getSalesScore();
               }
           }
		   //SalesOrderVO tempOrder = new SalesOrderVO();
	        /*if (tempOrder == null) {
	            throw new Exception("参数错误");
	        }
	        if ("012".indexOf(invoiceType) == -1) {
	            throw new Exception("参数错误");
	        }*/
	        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
	        salesOrderDTO.setMainID(randomNumeric());
	        salesOrderDTO.setCustomerID(customerID);
	        salesOrderDTO.setPaymentType(Integer.valueOf(paymentType));
	        salesOrderDTO.setPaymentStatus(0);

	       // salesOrderDTO.setExpressFee(tempOrder.getExpressFee());//运费
	        salesOrderDTO.setProductAmount(amount);
	        salesOrderDTO.setTotalAmount(amount + supplierFreight);
	        salesOrderDTO.setPayableAmount(amount);

	        salesOrderDTO.setOrderType(Integer.valueOf(orderType));
	        salesOrderDTO.setMemo(memo);
	        salesOrderDTO.setInvoiceType(Integer.parseInt(invoiceType));
	        salesOrderDTO.setInvoiceTitle(invoiceTitle);
	        salesOrderDTO.setSupplierID(string);
	        salesOrderDTO.setExpressFee(supplierFreight);
	        salesOrderDAO.addSalesOrder(salesOrderDTO);
	        ordersList.append(salesOrderDTO.getMainID()+",");
	        if(!paymentType.equals("3")){
	        	 CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findAddressByID(Long.valueOf(addressID));
	 	        if (customerDeliveryAddressVO != null) {
	 	            SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO = new SalesOrderDeliveryAddressDTO();
	 	            salesOrderDeliveryAddressDTO.setSalesOrderID(salesOrderDTO.getMainID());
	 	            salesOrderDeliveryAddressDTO.setName(customerDeliveryAddressVO.getName());
	 	            salesOrderDeliveryAddressDTO.setCountryID(customerDeliveryAddressVO.getCountryID());
	 	            salesOrderDeliveryAddressDTO.setProvinceID(customerDeliveryAddressVO.getProvinceID());
	 	            salesOrderDeliveryAddressDTO.setCityID(customerDeliveryAddressVO.getCityID());
	 	            salesOrderDeliveryAddressDTO.setDisctrictID(customerDeliveryAddressVO.getDisctrictID());
	 	            salesOrderDeliveryAddressDTO.setAddress(customerDeliveryAddressVO.getAddress());
	 	            salesOrderDeliveryAddressDTO.setMobile(customerDeliveryAddressVO.getMobile());
	 	            salesOrderDeliveryAddressDTO.setTelephone(customerDeliveryAddressVO.getTelephone());
	 	            if (customerDeliveryAddressVO.getZip() != null) {
	 	                salesOrderDeliveryAddressDTO.setZip(customerDeliveryAddressVO.getZip());
	 	            }
	 	            salesOrderDeliveryAddressDAO.insertSalesOrderDeliveryAddress(salesOrderDeliveryAddressDTO);
	 	        }
	 	        ShippingAddressVO shippingAddressVO = shippingAddressDAO.findDefaultShippingAddress();
	 	        if (shippingAddressVO != null) {
	 	            SalesOrderShippingAddressDTO salesOrderShippingAddressDTO = new SalesOrderShippingAddressDTO();
	 	            salesOrderShippingAddressDTO.setSalesOrderID(salesOrderDTO.getMainID());
	 	            salesOrderShippingAddressDTO.setName(shippingAddressVO.getName());
	 	            salesOrderShippingAddressDTO.setCountryID(shippingAddressVO.getCountryID());
	 	            salesOrderShippingAddressDTO.setProvinceID(shippingAddressVO.getProvinceID());
	 	            salesOrderShippingAddressDTO.setCityID(shippingAddressVO.getCityID());
	 	            salesOrderShippingAddressDTO.setDisctrictID(shippingAddressVO.getDisctrictID());
	 	            salesOrderShippingAddressDTO.setAddress(shippingAddressVO.getAddress());
	 	            salesOrderShippingAddressDTO.setMobile(shippingAddressVO.getMobile());
	 	            salesOrderShippingAddressDTO.setTelephone(shippingAddressVO.getTelephone());
	 	            salesOrderShippingAddressDTO.setZip(shippingAddressVO.getZip());
	 	            salesOrderShippingAddressDAO.insertSalesOrderShippingAddress(salesOrderShippingAddressDTO);
	 	        }
	        }
	       
	        ItemDTO itemDTO = new ItemDTO();
	        SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
	        SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
	        if (generateType.equals("quickBuy")) {
	            generateQuickBuyOrder(itemCount,cartIDs, itemID, supplierID, objID, salesOrderDTO, itemDTO, supplierItemDTO,
	                                  salesOrderLineDTO);

	        } else if (generateType.equals("oneKey")) {
	            generateOneKeyOrder(customerID, itemCount, itemType, objID, salesOrderDTO, itemDTO, supplierItemDTO,
	                                salesOrderLineDTO);

	        } else if (generateType.equals("shoppingcart")) {
	            genarateShoppingCartOrder(customerID, cartIDs, itemCount, itemType, objID, salesOrderDTO, itemDTO,
	                                      supplierItemDTO, salesOrderLineDTO);
	        } else {
	            throw new Exception("订单类型无法确定");
	        }
		}
        return ordersList;
    }
    
    
    /**
     * @date 2015年5月29日
     * @author moo
     * @param customerID
     * @param itemCount
     * @param itemType
     * @param objID
     * @param salesOrderDTO
     * @param itemDTO
     * @param supplierItemDTO
     * @param salesOrderLineDTO
     * @return void
     * @since JDK 1.6
     * @Description 一键订货订单
     */
    private void generateOneKeyOrder(String customerID, String itemCount, String itemType, String objID,
                                     SalesOrderDTO salesOrderDTO, ItemDTO itemDTO, SupplierItemDTO supplierItemDTO,
                                     SalesOrderLineDTO salesOrderLineDTO) {
        List<ShoppingCartVO> list = cachedClient.get(customerID);
        for (ShoppingCartVO shoppingCartVO : list) {
            salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
            salesOrderLineDTO.setProductID(shoppingCartVO.getProductID());
            salesOrderLineDTO.setItemID(shoppingCartVO.getItemID());
            ItemVO itemVO = itemDAO.findItemById(shoppingCartVO.getItemID());
            supplierItemDTO.setSupplierID(shoppingCartVO.getSupplierID());
            supplierItemDTO.setItemID(shoppingCartVO.getItemID());
            SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
            salesOrderLineDTO.setMainID(this.randomNumeric());
            salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
            salesOrderLineDTO.setItemCount(shoppingCartVO.getItemCount());
            salesOrderLineDTO.setIsPromotion(0);
            salesOrderLineDTO.setStatus(1);
            salesOrderLineDTO.setType(shoppingCartVO.getType());
            salesOrderLineDTO.setSalesPrice(supplierItemVO.getSalesPrice());
            salesOrderLineDTO.setTransactionPrice(supplierItemVO.getSalesPrice());
            salesOrderLineDTO.setSupplierID(shoppingCartVO.getSupplierID());
            salesOrderLineDTO.setTotalAmount(salesOrderLineDTO.getSalesPrice() * salesOrderLineDTO.getItemCount());
            salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
            supplierItemDTO.setMainID(supplierItemVO.getMainID());
            int supplierItemCount = shoppingCartVO.getItemCount();
            int newStock = supplierItemVO.getStock() - supplierItemCount;
            supplierItemDTO.setStock(newStock);
            supplierItemDTO.setSaleCount(supplierItemVO.getSaleCount() + supplierItemCount);
            CodeConfigVO saftyStock = systemCodeDAO.findCodeConfigByID("saftyStock");
            if (supplierItemDTO.getStock() < Integer.parseInt(saftyStock.getCodeEx())) supplierItemDTO.setStatus(0);
            supplierItemDAO.updateSupplierItemStockAndSaleCount(supplierItemDTO);
            itemDTO.setMainID(itemVO.getMainID());
            itemDTO.setSalesVolume(itemVO.getSalesVolume() + supplierItemCount);
            itemDAO.updateItemByMainID(itemDTO);
            for (int i = 0; i < supplierItemCount; i++) {
                SupplierItemIDDTO supplierItemID = new SupplierItemIDDTO();
                supplierItemID.setMainID(randomNumeric());
                supplierItemID.setSupplierID(shoppingCartVO.getSupplierID());
                supplierItemID.setOrderID(salesOrderDTO.getMainID());
                supplierItemID.setOrderLineId(supplierItemDTO.getMainID());
                supplierItemID.setSalesOrderLineID(salesOrderLineDTO.getMainID());
                supplierItemID.setItemName(shoppingCartVO.getItemName());
                supplierItemIDDAO.addSupplierItemID(supplierItemID);
            }
            List<PromotionVO> pList = getPromotionList();
            if (pList != null && pList.size() > 0) {
                for (PromotionVO promotionVO : pList) {
                    if (promotionVO.getType() == 7) {
                        Boolean isPromotion = false;
                        Boolean isItem = false;
                        if (salesOrderLineDTO.getItemCount() >= promotionVO.getPromotionCondition()) {
                            String customerGrade = promotionVO.getCustomerGrade();
                            String customerGroup = promotionVO.getCustomerGroup();
                            if (StringUtil.isNotEmpty(customerGrade)) {
                                CustomerVO customerVO = customerDAO.findCustomerByMainID(customerID);
                                if (customerGrade.contains(customerVO.getGradeID())) {
                                    isPromotion = true;
                                }
                            }
                            if (isPromotion == false) {
                                if (StringUtil.isNotEmpty(customerGroup)) {
                                    String[] groups = customerGroup.split(",");
                                    for (String groupID : groups) {
                                        List<CustomerGroupMemberVO> groupList = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                                        for (CustomerGroupMemberVO customerGroupMemberVO : groupList) {
                                            if (customerID.equals(customerGroupMemberVO.getCustomerID())) {
                                                isPromotion = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (isPromotion) {
                                List<PromotionSetVO> psList = promotionDAO.findPromotionSetByPID(promotionVO.getMainID());
                                if (psList != null && psList.size() > 0) {
                                    for (PromotionSetVO promotionSetVO : psList) {
                                        if (promotionSetVO.getSetType() == 4) {
                                            if (itemVO.getMainID().equals(promotionSetVO.getObjID())) {
                                                isItem = true;
                                                break;
                                            }
                                        } else if (promotionSetVO.getSetType() == 3) {
                                            if (itemVO.getProductID().equals(promotionSetVO.getObjID())) {
                                                isItem = true;
                                                break;
                                            }
                                        } else if (promotionSetVO.getSetType() == 2) {
                                            ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                            ProductCategoryVO productCategoryVO = productCategoryDAO.findProductCategoryByMainID(promotionSetVO.getObjID());
                                            if (productVO.getProductTypeID().equals(productCategoryVO.getProductTypeID())) {
                                                isItem = true;
                                                break;
                                            }
                                        } else if (promotionSetVO.getSetType() == 1) {
                                            ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                            if (productVO.getProductTypeID().equals(promotionSetVO.getObjID())) {
                                                isItem = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (isItem) {
                                salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
                                salesOrderLineDTO.setProductID(itemVO.getProductID());
                                salesOrderLineDTO.setItemID(itemVO.getMainID());
                                salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
                                salesOrderLineDTO.setTransactionPrice(itemVO.getSalesPrice());
                                salesOrderLineDTO.setItemCount(Integer.valueOf(promotionVO.getPromotionValue()));
                                salesOrderLineDTO.setIsPromotion(1);
                                salesOrderLineDTO.setStatus(1);
                                salesOrderLineDTO.setType(Integer.valueOf(itemType));
                                if (StringUtil.isNotEmpty(objID)) {
                                    salesOrderLineDTO.setObjID(objID);
                                    PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(objID);
                                    FlashSaleVO flashSaleVO = flashSaleDAO.findFlashSaleByMainID(objID);
                                    if (preSaleVO != null) {
                                        salesOrderLineDTO.setSalesPrice(preSaleVO.getSalesPrice());
                                    }
                                    if (flashSaleVO != null) {
                                        salesOrderLineDTO.setSalesPrice(flashSaleVO.getSalesPrice());
                                    }
                                } else {
                                    salesOrderLineDTO.setSalesPrice(itemVO.getSalesPrice());
                                }
                                salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
                                itemDTO.setMainID(itemVO.getMainID());
                                if (itemVO.getStock() > Integer.valueOf(itemCount)) {
                                    itemDTO.setStock(itemVO.getStock() - Integer.valueOf(itemCount));
                                }
                                itemDAO.updateItemByMainID(itemDTO);
                            }
                        }
                    }
                }
            }
        }
        cachedClient.delete(customerID);
    }

    /**
     * @date 2015年5月29日
     * @author moo
     * @param itemCount
     * @param itemID
     * @param supplierID
     * @param objID
     * @param salesOrderDTO
     * @param itemDTO
     * @param supplierItemDTO
     * @param salesOrderLineDTO
     * @return void
     * @throws Exception
     * @since JDK 1.6
     * @Description 快速购买订单
     */
    private void generateQuickBuyOrder(String itemCount,String cartIDs, String itemID, String supplierID, String objID,
                                       SalesOrderDTO salesOrderDTO, ItemDTO itemDTO, SupplierItemDTO supplierItemDTO,
                                       SalesOrderLineDTO salesOrderLineDTO) throws Exception {
        String ids[] = cartIDs.split(",");
       
        ItemVO itemVO = itemDAO.findItemById(itemID);
        supplierItemDTO.setSupplierID(supplierID);
        supplierItemDTO.setItemID(itemID);
        SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
        salesOrderLineDTO.setMainID(this.randomNumeric());
        salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
        salesOrderLineDTO.setProductID(itemVO.getProductID());
        salesOrderLineDTO.setItemID(itemVO.getMainID());
        salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
        salesOrderLineDTO.setItemCount(Integer.valueOf(itemCount));
        salesOrderLineDTO.setIsPromotion(0);
        salesOrderLineDTO.setStatus(1);
        salesOrderLineDTO.setSupplierID(supplierID);
        salesOrderLineDTO.setSalesPrice(supplierItemVO.getSalesPrice());
        salesOrderLineDTO.setTransactionPrice(supplierItemVO.getSalesPrice());
        salesOrderLineDTO.setTotalAmount(supplierItemVO.getSalesPrice() * Integer.valueOf(itemCount));
        salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
        shoppingCartDAO.delShoppingCartByID(Long.valueOf(ids[0]));
        if (StringUtil.isNotEmpty(objID)) {
            PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(objID);
            FlashSaleVO flashSaleVO = flashSaleDAO.findFlashSaleByMainID(objID);
            if (preSaleVO != null) {
                PreSaleDTO preSaleDTO = new PreSaleDTO();
                preSaleDTO.setMaxmumQty(Long.valueOf(preSaleVO.getMaxmumQty() - Integer.valueOf(itemCount)));
                preSaleDTO.setPeopleBuy(preSaleVO.getPeopleBuy() + 1);
                preSaleDTO.setMainID(preSaleVO.getMainID());
                preSaleDAO.updatePreSaleByMainID(preSaleDTO);
            }
            if (flashSaleVO != null) {
                FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
                flashSaleDTO.setTotality(flashSaleVO.getTotality() - Integer.valueOf(itemCount));
                flashSaleDTO.setPeopleRush(flashSaleVO.getPeopleRush() + 1);
                flashSaleDTO.setMainID(flashSaleVO.getMainID());
                flashSaleDAO.updateFlashSale(flashSaleDTO);
            }
        } else {
            itemDTO.setMainID(itemVO.getMainID());
            supplierItemDTO.setMainID(supplierItemVO.getMainID());
            int supplierItemCount = Integer.valueOf(itemCount);
            if (supplierItemVO.getStock() >= supplierItemCount) {
                supplierItemDTO.setStock(supplierItemVO.getStock() - supplierItemCount);
                supplierItemDTO.setSaleCount(supplierItemVO.getSaleCount() + supplierItemCount);
                for (int i = 0; i < supplierItemCount; i++) {
                    SupplierItemIDDTO supplierItemID = new SupplierItemIDDTO();
                    supplierItemID.setMainID(randomNumeric());
                    supplierItemID.setSupplierID(supplierID);
                    supplierItemID.setOrderLineId(salesOrderLineDTO.getMainID());
                    supplierItemID.setOrderID(salesOrderDTO.getMainID());
                    supplierItemID.setSalesOrderLineID(salesOrderLineDTO.getMainID());
                    supplierItemID.setItemName(itemVO.getName());
                    supplierItemIDDAO.addSupplierItemID(supplierItemID);
                }

            } else {
                throw new Exception("库存不足");
            }
            // 库存不足下架

            CodeConfigVO saftyStock = systemCodeDAO.findCodeConfigByID("saftyStock");

            if (supplierItemDTO.getStock() < Integer.parseInt(saftyStock.getCodeEx())) supplierItemDTO.setStatus(0);
            supplierItemDAO.updateSupplierItemStockAndSaleCount(supplierItemDTO);
            itemDTO.setSalesVolume(itemVO.getSalesVolume() + supplierItemCount);
            itemDAO.updateItemByMainID(itemDTO);
        }
    }

    /**
     * @date 2015年5月29日
     * @author moo
     * @param customerID
     * @param cartIDs
     * @param itemCount
     * @param itemType
     * @param objID
     * @param salesOrderDTO
     * @param itemDTO
     * @param supplierItemDTO
     * @param salesOrderLineDTO
     * @return void
     * @since JDK 1.6
     * @Description 正常购物车订单
     */
    private void genarateShoppingCartOrder(String customerID, String cartIDs, String itemCount, String itemType,
                                           String objID, SalesOrderDTO salesOrderDTO, ItemDTO itemDTO,
                                           SupplierItemDTO supplierItemDTO, SalesOrderLineDTO salesOrderLineDTO) {
        String ids[] = cartIDs.split(",");
        for (String id : ids) {
            ShoppingCartVO shoppingCartVO = shoppingCartDAO.findShoppingCartByID(Long.valueOf(id));
            if (shoppingCartVO != null&&shoppingCartVO.getSupplierID().equals(salesOrderDTO.getSupplierID().split("_")[0])) {
                salesOrderLineDTO.setMainID(this.randomNumeric());
                salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
                salesOrderLineDTO.setProductID(shoppingCartVO.getProductID());
                salesOrderLineDTO.setItemID(shoppingCartVO.getItemID());
                ItemVO itemVO = itemDAO.findItemById(shoppingCartVO.getItemID());
                supplierItemDTO.setSupplierID(shoppingCartVO.getSupplierID());
                supplierItemDTO.setItemID(shoppingCartVO.getItemID());
                SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
                salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
                salesOrderLineDTO.setItemCount(shoppingCartVO.getItemCount());
                salesOrderLineDTO.setIsPromotion(0);
                salesOrderLineDTO.setStatus(1);
                salesOrderLineDTO.setType(1);
                salesOrderLineDTO.setSalesPrice(supplierItemVO.getSalesPrice());
                salesOrderLineDTO.setTransactionPrice(supplierItemVO.getSalesPrice());
                salesOrderLineDTO.setSupplierID(shoppingCartVO.getSupplierID());
                salesOrderLineDTO.setTotalAmount(salesOrderLineDTO.getSalesPrice() * salesOrderLineDTO.getItemCount());
                salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
                shoppingCartDAO.delShoppingCartByID(Long.valueOf(id));
                supplierItemDTO.setMainID(supplierItemVO.getMainID());
                int supplierItemCount = shoppingCartVO.getItemCount();
                int newStock = supplierItemVO.getStock() - supplierItemCount;
                supplierItemDTO.setStock(newStock);
                supplierItemDTO.setSaleCount(supplierItemVO.getSaleCount() + supplierItemCount);
                CodeConfigVO saftyStock = systemCodeDAO.findCodeConfigByID("saftyStock");
                if (supplierItemDTO.getStock() < Integer.parseInt(saftyStock.getCodeEx())) supplierItemDTO.setStatus(0);
               // supplierItemDAO.updateSupplierItemStockAndSaleCount(supplierItemDTO);
                itemDTO.setMainID(itemVO.getMainID());
                itemDTO.setSalesVolume(itemVO.getSalesVolume() + supplierItemCount);
                itemDTO.setStock(newStock);
                itemDAO.updateItemByMainID(itemDTO);
               /* ????????到底是干嘛用的？？？？？
                * for (int i = 0; i < supplierItemCount; i++) {
                    SupplierItemIDDTO supplierItemID = new SupplierItemIDDTO();
                    supplierItemID.setMainID(randomNumeric());
                    supplierItemID.setOrderLineId(salesOrderLineDTO.getMainID());
                    supplierItemID.setSupplierID(shoppingCartVO.getSupplierID());
                    supplierItemID.setOrderID(salesOrderDTO.getMainID());
                    supplierItemID.setItemName(shoppingCartVO.getItemName());
                    supplierItemID.setSalesOrderLineID(salesOrderLineDTO.getMainID());
                    supplierItemIDDAO.addSupplierItemID(supplierItemID);
                }*/
                List<PromotionVO> pList = getPromotionList();
                if (pList != null && pList.size() > 0) {
                    for (PromotionVO promotionVO : pList) {
                        if (promotionVO.getType() == 7) {
                            Boolean isPromotion = false;
                            Boolean isItem = false;
                            if (salesOrderLineDTO.getItemCount() >= promotionVO.getPromotionCondition()) {
                                String customerGrade = promotionVO.getCustomerGrade();
                                String customerGroup = promotionVO.getCustomerGroup();
                                if (StringUtil.isNotEmpty(customerGrade)) {
                                    CustomerVO customerVO = customerDAO.findCustomerByMainID(customerID);
                                    if (customerGrade.contains(customerVO.getGradeID())) {
                                        isPromotion = true;
                                    }
                                }
                                if (isPromotion == false) {
                                    if (StringUtil.isNotEmpty(customerGroup)) {
                                        String[] groups = customerGroup.split(",");
                                        for (String groupID : groups) {
                                            List<CustomerGroupMemberVO> groupList = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                                            for (CustomerGroupMemberVO customerGroupMemberVO : groupList) {
                                                if (customerID.equals(customerGroupMemberVO.getCustomerID())) {
                                                    isPromotion = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isPromotion) {
                                    List<PromotionSetVO> psList = promotionDAO.findPromotionSetByPID(promotionVO.getMainID());
                                    if (psList != null && psList.size() > 0) {
                                        for (PromotionSetVO promotionSetVO : psList) {
                                            if (promotionSetVO.getSetType() == 4) {
                                                if (itemVO.getMainID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 3) {
                                                if (itemVO.getProductID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 2) {
                                                ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                                ProductCategoryVO productCategoryVO = productCategoryDAO.findProductCategoryByMainID(promotionSetVO.getObjID());
                                                if (productVO.getProductTypeID().equals(productCategoryVO.getProductTypeID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 1) {
                                                ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                                if (productVO.getProductTypeID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isItem) {
                                    salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
                                    salesOrderLineDTO.setProductID(itemVO.getProductID());
                                    salesOrderLineDTO.setItemID(itemVO.getMainID());
                                    salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
                                    salesOrderLineDTO.setTransactionPrice(itemVO.getSalesPrice());
                                    salesOrderLineDTO.setItemCount(Integer.valueOf(promotionVO.getPromotionValue()));
                                    salesOrderLineDTO.setIsPromotion(1);
                                    salesOrderLineDTO.setStatus(1);
                                    salesOrderLineDTO.setType(Integer.valueOf(itemType));
                                    if (StringUtil.isNotEmpty(objID)) {
                                        salesOrderLineDTO.setObjID(objID);
                                        PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(objID);
                                        FlashSaleVO flashSaleVO = flashSaleDAO.findFlashSaleByMainID(objID);
                                        if (preSaleVO != null) {
                                            salesOrderLineDTO.setSalesPrice(preSaleVO.getSalesPrice());
                                        }
                                        if (flashSaleVO != null) {
                                            salesOrderLineDTO.setSalesPrice(flashSaleVO.getSalesPrice());
                                        }
                                    } else {
                                        salesOrderLineDTO.setSalesPrice(itemVO.getSalesPrice());
                                    }
                                    salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
                                    itemDTO.setMainID(itemVO.getMainID());
                                    if (itemVO.getStock() > Integer.valueOf(itemCount)) {
                                        itemDTO.setStock(itemVO.getStock() - Integer.valueOf(itemCount));
                                    }
                                    itemDAO.updateItemByMainID(itemDTO);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取促销条件
     * 
     * @date 2015年1月13日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionVO> getPromotionList() {
        PromotionDTO promotionDTO = new PromotionDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String nowdate = sdf.format(date);
        try {
            promotionDTO.setFromDate(DateUtil.strintToDatetimeYMD(nowdate));
            promotionDTO.setEndDate(DateUtil.strintToDatetimeYMD(nowdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<PromotionVO> pList = promotionDAO.findPromotionByPagination(promotionDTO);
        return pList;
    }

    @Override
    public SalesOrderVO findSalesOrderByMainID(String mainId) {
        return salesOrderDAO.findSalesOrderByMainID(mainId);
    }

    public String randomNumeric() {
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.SALESORDERID);
        String flowCode = RandomStringUtils.randomNumeric(7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String merId = "03602";
        String result = merId + flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeDAO.findSystemCode(Constant.SALESORDERID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

    @Override
    public List<SalesOrderVO> findOrdersByCustomerID(String customerID) {
        List<SalesOrderVO> orders = new ArrayList<SalesOrderVO>();
        List<SalesOrderVO> list = salesOrderDAO.findOrdersByCustomerID(customerID);
        if (list != null && list.size() > 0) {
            for (SalesOrderVO order : list) {
                List<SalesOrderLineVO> lines = salesOrderDAO.findOrdersByOrderID(order.getMainID());
                if (lines != null && lines.size() > 0) {
                    order.setSalesOrderLineList(lines);
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    @Override
    public List<SalesOrderVO> getSettlementList(SalesOrderDTO salesOrderDTO) {
        notNull(salesOrderDTO, "salesOrderDTO is null");
        List<SalesOrderVO> list = null;
        if (salesOrderDTO.getOrderStatus() != null && salesOrderDTO.getPaymentStatus() != null) {
            if (salesOrderDTO.getOrderStatus() == 6 && salesOrderDTO.getPaymentStatus() == 1) {
                list = salesOrderDAO.getSettlementList(salesOrderDTO);
            }
        }
        return list;
    }

    @Override
    public List<SalesOrderVO> findOrdersBySupplierID(SalesOrderDTO orderDTO) {

        long count = salesOrderDAO.findOrderCountBySupplierID(orderDTO);
        orderDTO.doPage(count, orderDTO.getPageNo(), orderDTO.getPageSize());
        List<SalesOrderVO> orderList = salesOrderDAO.findOrdersBySupplierID(orderDTO);
        if (orderList == null || orderList.size() == 0) return null;
        for (SalesOrderVO salesOrderVO : orderList) {
            List<SalesOrderLineVO> lineList = salesOrderDAO.findOrderLineByOrderAndSupplier(salesOrderVO.getMainID(),
                                                                                            orderDTO.getSupplierID());
            salesOrderVO.setSalesOrderLineList(lineList);
        }
        return orderList;
    }

    @Transactional
    @Override
    public void updateSalesOrderSupplierItemIDSerialiseNO(String orderID, String packageWeight, String packageNum,
                                                          String[] supplierItemID, String[] serialiseNo)
                                                                                                        throws DuplicateSerialiseNoException {
        if (serialiseNo != null && serialiseNo.length > 0) {
            for (int i = 0; i < serialiseNo.length; i++) {
                if ("".equals(serialiseNo[i])) continue;
                SupplierItemIDVO item = supplierItemIDDAO.findSupplierItemIDBySerialiseNo(serialiseNo[i]);
                if (item != null) throw new DuplicateSerialiseNoException("重复序列号");

                SupplierItemIDDTO supplierItemIDDTO = new SupplierItemIDDTO();
                supplierItemIDDTO.setSerialiseNO(serialiseNo[i]);
                supplierItemIDDTO.setMainID(supplierItemID[i]);
                supplierItemIDDTO.setStatus(2);
                supplierItemIDDAO.updateSupplierItemID(supplierItemIDDTO);
            }
        }
        // 更新订单状态
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setMainID(orderID);
        salesOrderDTO.setOrderStatus(4);
        if (StringUtil.isNotEmpty(packageWeight)) {
            salesOrderDTO.setWeight(Double.parseDouble(packageWeight));
        }
        if (StringUtil.isNotEmpty(packageNum)) {
            salesOrderDTO.setPackageNum(Integer.parseInt(packageNum));
        }
        salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
    }

    @Override
    public List<SupplierItemIDVO> findSupplierItemIDByOrderID(String orderId) {

        return supplierItemIDDAO.findSupplierItemIDByOrderID(orderId);
    }

    public List<SalesOrderVO> getDeleteOrderList(SalesOrderDTO salesOrderDeleteDTO) {
        try {
            notNull(salesOrderDeleteDTO, "salesOrderDeleteDTO is null");
            Long count = salesOrderDAO.findDeleteOrderCount(salesOrderDeleteDTO);
            salesOrderDeleteDTO.doPage(count, salesOrderDeleteDTO.getPageNo(), salesOrderDeleteDTO.getPageSize());
            salesOrderDeleteDTO.setOrderByClause("ORDER BY s.id DESC");
            List<SalesOrderVO> list = salesOrderDAO.findDeleteOrderByPagination(salesOrderDeleteDTO);
            List<SalesOrderVO> deleteList = new ArrayList<SalesOrderVO>();
            if (list != null && list.size() > 0) {
                for (SalesOrderVO src : list) {
                    SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
                    if (salesOrderDeleteDTO.getItemID() != null) {
                        salesOrderLineDTO.setItemID(salesOrderDeleteDTO.getItemID());
                    }
                    if (salesOrderDeleteDTO.getItemName() != null) {
                        salesOrderLineDTO.setItemName(salesOrderDeleteDTO.getItemName());
                    }
                    salesOrderLineDTO.setOrderID(src.getMainID());
                    List<SalesOrderLineVO> list2 = salesOrderLineDAO.findDeleteOrderLineBySearch(salesOrderLineDTO);
                    for (SalesOrderLineVO salesOrderLineVO : list2) {
                        if (salesOrderLineVO.getTransactionPrice() != null && salesOrderLineVO.getItemCount() != null) {
                            salesOrderLineVO.setAllamount(salesOrderLineVO.getTransactionPrice()
                                                          * salesOrderLineVO.getItemCount());
                        }
                    }
                    src.setSalesOrderLineList(list2);
                    if (list2 != null && list2.size() > 0) {
                        deleteList.add(src);
                    }
                }
            }
            if (StringUtil.isNotEmpty(salesOrderDeleteDTO.getItemID())
                || StringUtil.isNotEmpty(salesOrderDeleteDTO.getItemName())) {
                return deleteList;
            } else {
                return list;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void updateSalesOrderByID(String mainID) {
        salesOrderDAO.updateSalesOrderByID(mainID);

    }

    /**
     * 保存物流信息
     */
    @Override
    public String salesOrderTransSave(List<SalesOrderTransVO> salesOrderTransVOList) {
        return salesOrderDAO.salesOrderTransSave(salesOrderTransVOList) + "";

    }

    @Override
    public List<SalesOrderVO> findUserTradeList(SalesOrderDTO salesOrderDTO) {
        notNull(salesOrderDTO, "salesOrderDTO is null");
        Long count = salesOrderDAO.findUserTradeCount(salesOrderDTO);
        salesOrderDTO.doPage(count, salesOrderDTO.getPageNo(), salesOrderDTO.getPageSize());
        salesOrderDTO.setOrderByClause("ORDER BY s.id DESC");
        List<SalesOrderVO> list = salesOrderDAO.findUserTradeByPagination(salesOrderDTO);
        return list;
    }

    @Override
    public Boolean updatePaymentStatus(String mainIds) {
        try {
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(mainId);
                if (salesOrderVO.getPaymentStatus() == 0) {
                    salesOrderDAO.updatePaymentStatus(mainId);
                    AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                    accountDetailDTO.setSerialNumber(mainId);
                    accountDetailDTO.setDetailType(2);
                    accountDetailDTO.setAmount(salesOrderVO.getTotalAmount());
                    accountDetailDTO.setAccountType(1);
                    accountDetailDTO.setPaymentType(salesOrderVO.getPaymentType());
                    accountDetailDTO.setDescription(salesOrderVO.getCustomerUserName());
                    accountDetailDTO.setObjID(mainId);
                    accountDetailDTO.setCustomerID(salesOrderVO.getCustomerID());
                    accountDetailDTO.setStatus(1);
                    CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.PAYDETAILID);
                    if (codeConfigVO != null) {
                        String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
                        accountDetailDTO.setMainID(mainID);
                    }
                    accountDetailDAO.insertMemberScore(accountDetailDTO);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateReceiveStatus(String mainId) {
        try {
            SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(mainId);
            if (salesOrderVO.getPaymentStatus() == 1) {
                salesOrderDAO.updateReceiveStatus(mainId);
                accountDetailDAO.deleteAccountDetail(mainId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据订单号查物流信息
     * 
     * @param orderID
     * @return
     */
    @Override
    public List<SalesOrderTransVO> getSalesOrderTrans(SalesOrderTransDTO salesOrderTransDTO) {
        return salesOrderDAO.getSalesOrderTrans(salesOrderTransDTO);
    }

    @Override
    public List<SalesOrderVO> findFinishedOrderBySupplierID(SalesOrderDTO salesOrderDTO) {
        long count = salesOrderDAO.getFinishedOrderCountBySupplierID(salesOrderDTO);
        salesOrderDTO.doPage(count, salesOrderDTO.getPageNo(), salesOrderDTO.getPageSize());
        List<SalesOrderVO> salesOrderList = salesOrderDAO.findFinishedOrderBySupplierID(salesOrderDTO);
        return salesOrderList;
    }

    @Override
    public void exportSupplierSalesOrder(HttpServletResponse response, String mainIDs, String supplierID) {
        try {
            String ids[] = mainIDs.split(",");
            List<SalesOrderVO> list = new ArrayList<SalesOrderVO>();
            for (String mainId : ids) {
                SalesOrderVO salesOrderVO = salesOrderDAO.findSupplierOrderBySupplierIDAndOrderID(supplierID, mainId);
                list.add(salesOrderVO);
            }
            HSSFWorkbook exportBook = this.exportSupplierSalesOrderInfo(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("订单信息.xls").getBytes("gb2312"), "ISO8859_1"));
            OutputStream out = response.getOutputStream();
            exportBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exportReceivable(HttpServletResponse response, SalesOrderDTO salesOrderDTO) {
        try {
            List<SalesOrderVO> list = salesOrderDAO.findAllSalesOrder(salesOrderDTO);
            /*
             * if (StringUtil.isNotEmpty(mainIds)) { String[] ids = mainIds.split(","); for (int i = 0; i < ids.length;
             * i++) { SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(ids[i]); list.add(salesOrderVO);
             * } }
             */
            HSSFWorkbook exportBook = this.exportReceivableInfo(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("应收账款.xls").getBytes("gb2312"), "ISO8859_1"));
            OutputStream out = response.getOutputStream();
            exportBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HSSFWorkbook exportReceivableInfo(List<SalesOrderVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("应收账款");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 128);
        sheet.setColumnWidth(2, 18 * 128);
        sheet.setColumnWidth(3, 18 * 128);
        sheet.setColumnWidth(4, 18 * 200);
        sheet.setColumnWidth(5, 18 * 128);
        sheet.setColumnWidth(6, 18 * 128);
        sheet.setColumnWidth(7, 18 * 128);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);

        HSSFCellStyle styledata = workbook.createCellStyle();
        styledata.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 产生表格标题行

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("物流单号");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("收款金额");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("创建时间");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("支付方式");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("收款时间");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("地区");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                SalesOrderVO salesOrderVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(salesOrderVO.getMainID());
                cell = content.createCell(1);
                cell.setCellValue(salesOrderVO.getExpressNumber());
                cell = content.createCell(2);
                cell.setCellValue(salesOrderVO.getTotalAmount());
                cell = content.createCell(3);
                cell.setCellValue(DateUtil.formatDate(salesOrderVO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                cell = content.createCell(4);
                if (salesOrderVO.getPaymentType() == 1) {
                    cell.setCellValue("款到发货");
                } else {
                    cell.setCellValue("货到付款");
                }
                cell = content.createCell(5);
                cell.setCellValue(DateUtil.formatDate(salesOrderVO.getReceivableTime(), "yyyy-MM-dd HH:mm:ss"));
                cell = content.createCell(6);
                cell.setCellValue(salesOrderVO.getDvProvinceName() + salesOrderVO.getDvCityName()
                                  + salesOrderVO.getDvDistrictName());
                cell = content.createCell(7);
                if (salesOrderVO.getPaymentStatus() == 0) {
                    cell.setCellValue("未收款");
                } else {
                    cell.setCellValue("已收款");
                }
            }
        }
        return workbook;
    }

    @Override
    public void supplierConfirmOrderCancel(String supplierID, String orderID) {
        salesOrderDAO.supplierConfirmOrderCancel(supplierID, orderID);

    }

    @Override
    public Long getWaitForDealOrder(SalesOrderDTO salesOrderDTO) {
        return salesOrderDAO.getWaitForDealOrder(salesOrderDTO);
    }

    @Override
    public List<SalesOrderVO> getNearFutureOrderBySupplierID(SalesOrderDTO salesOrderDTO) {
        List<SalesOrderVO> orderList = salesOrderDAO.findOrdersBySupplierID(salesOrderDTO);
        return orderList;
    }

    @Override
    public void uploadSalesOrderProofURL(SalesOrderDTO salesOrderDTO) {
         salesOrderDAO.uploadSalesOrderProofURL(salesOrderDTO);
    }

    @Override
    public StatisticsInfoVO countSalesOrderAllAmount(SalesOrderDTO salesOrderDTO) {
        
        return salesOrderDAO.countSalesOrderAllAmount(salesOrderDTO);
    }

    /******************************************************/
	@Override
	public SalesOrderVO findSalesOrderByMainIDNew(String mainId) {
		return salesOrderDAO.findSalesOrderByMainIDNew(mainId);
	}
	
	
	private String getMemoBySupplier(String [] memos,String supplierId)
	{
		String memo = null;
		String tempMemo [] = null;
		for (String m : memos) {
			tempMemo = m.split("~--~");
			if(tempMemo[0].equals(supplierId))
			{
				if(tempMemo.length >= 2)
				{
					memo = tempMemo[1];
				}
				break;
			}
		}
		return memo;
	}
	
    @Transactional
    @Override
    public StringBuffer addSalesOrderTparts(String customerID, String cartIDs, String addressID, String paymentType,
                                String orderAmount, String scoreAll, String memo, String itemCount, String itemID,
                                String supplierID, String itemType, String orderType, String objID, String invoiceType,
                                String invoiceTitle, String generateType, String orderID,String memoIDs,String allFreight, String supplierFreightStr) throws Exception {

//        SalesOrderVO tempOrder = cachedClient.get(customerID + orderID);
    	
        String ids[] = cartIDs.split(",");
        String memos[] = memoIDs.split(",");
        List<ShoppingCartVO> shopCartList = new ArrayList<ShoppingCartVO>();
        for (String id : ids) {
            ShoppingCartVO shoppingCartVO = shoppingCartDAO.findShoppingCartByID(Long.valueOf(id));
            shopCartList.add(shoppingCartVO);
        }
        Map<String,List<ShoppingCartVO>> maps = new HashMap<String,List<ShoppingCartVO>>();
        for(ShoppingCartVO s:shopCartList){
        	List<ShoppingCartVO> temp = new ArrayList<ShoppingCartVO>();
        	 String sid1= s.getSupplierID();
             String siname1= s.getSupplierName();
             for(ShoppingCartVO shoppingCartVO1 : shopCartList){
             	//当前店铺对应的购物车集合
             	String sid2 = shoppingCartVO1.getSupplierID();
             	if(sid1.equals(sid2)){
             		temp.add(shoppingCartVO1);
             	}
             }
             maps.put(sid1+"_"+siname1, temp);
        }
        
        //根据物流模板计算运费
        Map<String, Double> supplierFreightMap = new HashMap<String, Double>();
        String[] supplierFreightAll = null;
        if(StringUtil.isNotEmpty(supplierFreightStr))
        {
        	supplierFreightAll = supplierFreightStr.split("\\|");
            for (int i = 0; i < supplierFreightAll.length; i++) {
            	String[] supplierFreight = supplierFreightAll[i].split(":");
            	supplierFreightMap.put(supplierFreight[0], Double.parseDouble(supplierFreight[1]));
    		}          	
        }
              
        
        StringBuffer ordersList = new StringBuffer();
        int num = 0;
//        cachedClient.delete(customerID + orderID);
        Set<String> set = maps.keySet();
		for(String string : set){
		   List<ShoppingCartVO> temp1= maps.get(string);
		   double supplierFreight = 0d;
		   if(supplierFreightMap.size()>0)
		   {
			   supplierFreight = supplierFreightMap.get(string);
		   }
		   Double amount = 0d;
		   Integer count = 0;
           Integer score = 0;
           Double weight = 0d;
		   for (ShoppingCartVO shoppingCartVO : temp1) {
               ItemVO itemVO = itemDAO.findItemById(shoppingCartVO.getItemID());
               count += shoppingCartVO.getItemCount();
              // weight += itemVO.getWeight();
               if (shoppingCartVO.getType() == 1) {
                   amount += shoppingCartVO.getItemCount() * shoppingCartVO.getSalesPrice();
               } else if (shoppingCartVO.getType() == 2) {
                   score += shoppingCartVO.getItemCount() * itemVO.getScore();
               } else if (shoppingCartVO.getType() == 3) {
                   amount += shoppingCartVO.getItemCount() * itemVO.getScorePrice();
                   score += shoppingCartVO.getItemCount() * shoppingCartVO.getSalesScore();
               }
           }
		   //SalesOrderVO tempOrder = new SalesOrderVO();
	        /*if (tempOrder == null) {
	            throw new Exception("参数错误");
	        }
	        if ("012".indexOf(invoiceType) == -1) {
	            throw new Exception("参数错误");
	        }*/
	        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
	        salesOrderDTO.setMainID(randomNumeric());
	        salesOrderDTO.setCustomerID(customerID);
	        salesOrderDTO.setPaymentType(Integer.valueOf(paymentType));
	        salesOrderDTO.setPaymentStatus(0);

	       // salesOrderDTO.setExpressFee(tempOrder.getExpressFee());//运费
	        salesOrderDTO.setProductAmount(amount);
	        salesOrderDTO.setTotalAmount(amount + supplierFreight);
	        salesOrderDTO.setPayableAmount(amount);

	        salesOrderDTO.setOrderType(Integer.valueOf(orderType));
	        salesOrderDTO.setMemo(getMemoBySupplier(memos,string.split("_")[0]));
	        salesOrderDTO.setInvoiceType(Integer.parseInt(invoiceType));
	        salesOrderDTO.setInvoiceTitle(invoiceTitle);
	        salesOrderDTO.setSupplierID(string);
	        salesOrderDTO.setExpressFee(supplierFreight);
	        salesOrderDAO.addSalesOrder(salesOrderDTO);
	        ordersList.append(salesOrderDTO.getMainID()+",");
	        // 支付方式1：余额支付2：货到付款3：在线支付4：积分礼品5：线下汇款
	        if(!paymentType.equals("3")){
	        	//3：在线支付
	        	 CustomerDeliveryAddressVO customerDeliveryAddressVO = customerDeliveryAddressDAO.findAddressByID(Long.valueOf(addressID));
	 	        if (customerDeliveryAddressVO != null) {
	 	            SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO = new SalesOrderDeliveryAddressDTO();
	 	            salesOrderDeliveryAddressDTO.setSalesOrderID(salesOrderDTO.getMainID());
	 	            salesOrderDeliveryAddressDTO.setName(customerDeliveryAddressVO.getName());
	 	            salesOrderDeliveryAddressDTO.setCountryID(customerDeliveryAddressVO.getCountryID());
	 	            salesOrderDeliveryAddressDTO.setProvinceID(customerDeliveryAddressVO.getProvinceID());
	 	            salesOrderDeliveryAddressDTO.setCityID(customerDeliveryAddressVO.getCityID());
	 	            salesOrderDeliveryAddressDTO.setDisctrictID(customerDeliveryAddressVO.getDisctrictID());
	 	            salesOrderDeliveryAddressDTO.setAddress(customerDeliveryAddressVO.getAddress());
	 	            salesOrderDeliveryAddressDTO.setMobile(customerDeliveryAddressVO.getMobile());
	 	            salesOrderDeliveryAddressDTO.setTelephone(customerDeliveryAddressVO.getTelephone());
	 	            if (customerDeliveryAddressVO.getZip() != null) {
	 	                salesOrderDeliveryAddressDTO.setZip(customerDeliveryAddressVO.getZip());
	 	            }
	 	            salesOrderDeliveryAddressDAO.insertSalesOrderDeliveryAddress(salesOrderDeliveryAddressDTO);
	 	        }
	 	        ShippingAddressVO shippingAddressVO = shippingAddressDAO.findDefaultShippingAddress();
	 	        if (shippingAddressVO != null) {
	 	            SalesOrderShippingAddressDTO salesOrderShippingAddressDTO = new SalesOrderShippingAddressDTO();
	 	            salesOrderShippingAddressDTO.setSalesOrderID(salesOrderDTO.getMainID());
	 	            salesOrderShippingAddressDTO.setName(shippingAddressVO.getName());
	 	            salesOrderShippingAddressDTO.setCountryID(shippingAddressVO.getCountryID());
	 	            salesOrderShippingAddressDTO.setProvinceID(shippingAddressVO.getProvinceID());
	 	            salesOrderShippingAddressDTO.setCityID(shippingAddressVO.getCityID());
	 	            salesOrderShippingAddressDTO.setDisctrictID(shippingAddressVO.getDisctrictID());
	 	            salesOrderShippingAddressDTO.setAddress(shippingAddressVO.getAddress());
	 	            salesOrderShippingAddressDTO.setMobile(shippingAddressVO.getMobile());
	 	            salesOrderShippingAddressDTO.setTelephone(shippingAddressVO.getTelephone());
	 	            salesOrderShippingAddressDTO.setZip(shippingAddressVO.getZip());
	 	            salesOrderShippingAddressDAO.insertSalesOrderShippingAddress(salesOrderShippingAddressDTO);
	 	        }
	        }
	       
	        ItemDTO itemDTO = new ItemDTO();
	        SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
	        SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
	        if (generateType.equals("quickBuy")) {
	            generateQuickBuyOrder(itemCount,cartIDs, itemID, supplierID, objID, salesOrderDTO, itemDTO, supplierItemDTO,
	                                  salesOrderLineDTO);

	        } else if (generateType.equals("oneKey")) {
	            generateOneKeyOrder(customerID, itemCount, itemType, objID, salesOrderDTO, itemDTO, supplierItemDTO,
	                                salesOrderLineDTO);

	        } else if (generateType.equals("shoppingcart")) {
	        	genarateShoppingCartOrderTparts(customerID, cartIDs, itemCount, itemType, objID, salesOrderDTO, itemDTO,
	                                      supplierItemDTO, salesOrderLineDTO);
	        } else {
	            throw new Exception("订单类型无法确定");
	        }
	        num++;
		}
        return ordersList;
    }	
    
    
    /**
     * @date 2015年5月29日
     * @author sxc
     * @param customerID
     * @param cartIDs
     * @param itemCount
     * @param itemType
     * @param objID
     * @param salesOrderDTO
     * @param itemDTO
     * @param supplierItemDTO
     * @param salesOrderLineDTO
     * @return void
     * @since JDK 1.6
     * @Description 正常购物车订单
     */
    private void genarateShoppingCartOrderTparts(String customerID, String cartIDs, String itemCount, String itemType,
                                           String objID, SalesOrderDTO salesOrderDTO, ItemDTO itemDTO,
                                           SupplierItemDTO supplierItemDTO, SalesOrderLineDTO salesOrderLineDTO) {
        String ids[] = cartIDs.split(",");
        for (String id : ids) {
            ShoppingCartVO shoppingCartVO = shoppingCartDAO.findShoppingCartByID(Long.valueOf(id));
            if (shoppingCartVO != null&&shoppingCartVO.getSupplierID().equals(salesOrderDTO.getSupplierID().split("_")[0])) {
                salesOrderLineDTO.setMainID(this.randomNumeric());
                salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
                salesOrderLineDTO.setProductID(shoppingCartVO.getProductID());
                salesOrderLineDTO.setItemID(shoppingCartVO.getItemID());
                ItemVO itemVO = itemDAO.findItemById(shoppingCartVO.getItemID());
                supplierItemDTO.setSupplierID(shoppingCartVO.getSupplierID());
                supplierItemDTO.setItemID(shoppingCartVO.getItemID());
                SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierIDTparts(supplierItemDTO);
                salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
                salesOrderLineDTO.setItemCount(shoppingCartVO.getItemCount());
                salesOrderLineDTO.setIsPromotion(0);
                salesOrderLineDTO.setStatus(1);
                salesOrderLineDTO.setType(1);
                salesOrderLineDTO.setSalesPrice(supplierItemVO.getSalesPrice());
                salesOrderLineDTO.setTransactionPrice(supplierItemVO.getSalesPrice());
                salesOrderLineDTO.setSupplierID(shoppingCartVO.getSupplierID());
                salesOrderLineDTO.setTotalAmount(salesOrderLineDTO.getSalesPrice() * salesOrderLineDTO.getItemCount());
                salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
                shoppingCartDAO.delShoppingCartByID(Long.valueOf(id));
                supplierItemDTO.setMainID(supplierItemVO.getMainID());
                int supplierItemCount = shoppingCartVO.getItemCount();
                int newStock = supplierItemVO.getStock() - supplierItemCount;
                supplierItemDTO.setStock(newStock);
                supplierItemDTO.setSaleCount(supplierItemVO.getSaleCount() + supplierItemCount);
                CodeConfigVO saftyStock = systemCodeDAO.findCodeConfigByID("saftyStock");
                if (supplierItemDTO.getStock() < Integer.parseInt(saftyStock.getCodeEx())) supplierItemDTO.setStatus(0);
               // supplierItemDAO.updateSupplierItemStockAndSaleCount(supplierItemDTO);
                itemDTO.setMainID(itemVO.getMainID());
                itemDTO.setSalesVolume(itemVO.getSalesVolume() + supplierItemCount);
                itemDTO.setStock(newStock);
                itemDAO.updateItemByMainID(itemDTO);
               /* ????????到底是干嘛用的？？？？？
                * for (int i = 0; i < supplierItemCount; i++) {
                    SupplierItemIDDTO supplierItemID = new SupplierItemIDDTO();
                    supplierItemID.setMainID(randomNumeric());
                    supplierItemID.setOrderLineId(salesOrderLineDTO.getMainID());
                    supplierItemID.setSupplierID(shoppingCartVO.getSupplierID());
                    supplierItemID.setOrderID(salesOrderDTO.getMainID());
                    supplierItemID.setItemName(shoppingCartVO.getItemName());
                    supplierItemID.setSalesOrderLineID(salesOrderLineDTO.getMainID());
                    supplierItemIDDAO.addSupplierItemID(supplierItemID);
                }*/
                List<PromotionVO> pList = getPromotionList();
                if (pList != null && pList.size() > 0) {
                    for (PromotionVO promotionVO : pList) {
                        if (promotionVO.getType() == 7) {
                            Boolean isPromotion = false;
                            Boolean isItem = false;
                            if (salesOrderLineDTO.getItemCount() >= promotionVO.getPromotionCondition()) {
                                String customerGrade = promotionVO.getCustomerGrade();
                                String customerGroup = promotionVO.getCustomerGroup();
                                if (StringUtil.isNotEmpty(customerGrade)) {
                                    CustomerVO customerVO = customerDAO.findCustomerByMainID(customerID);
                                    if (customerGrade.contains(customerVO.getGradeID())) {
                                        isPromotion = true;
                                    }
                                }
                                if (isPromotion == false) {
                                    if (StringUtil.isNotEmpty(customerGroup)) {
                                        String[] groups = customerGroup.split(",");
                                        for (String groupID : groups) {
                                            List<CustomerGroupMemberVO> groupList = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                                            for (CustomerGroupMemberVO customerGroupMemberVO : groupList) {
                                                if (customerID.equals(customerGroupMemberVO.getCustomerID())) {
                                                    isPromotion = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isPromotion) {
                                    List<PromotionSetVO> psList = promotionDAO.findPromotionSetByPID(promotionVO.getMainID());
                                    if (psList != null && psList.size() > 0) {
                                        for (PromotionSetVO promotionSetVO : psList) {
                                            if (promotionSetVO.getSetType() == 4) {
                                                if (itemVO.getMainID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 3) {
                                                if (itemVO.getProductID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 2) {
                                                ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                                ProductCategoryVO productCategoryVO = productCategoryDAO.findProductCategoryByMainID(promotionSetVO.getObjID());
                                                if (productVO.getProductTypeID().equals(productCategoryVO.getProductTypeID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            } else if (promotionSetVO.getSetType() == 1) {
                                                ProductVO productVO = productDAO.findProductByMainID(itemVO.getProductID());
                                                if (productVO.getProductTypeID().equals(promotionSetVO.getObjID())) {
                                                    isItem = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isItem) {
                                    salesOrderLineDTO.setOrderID(salesOrderDTO.getMainID());
                                    salesOrderLineDTO.setProductID(itemVO.getProductID());
                                    salesOrderLineDTO.setItemID(itemVO.getMainID());
                                    salesOrderLineDTO.setStandrardPrice(itemVO.getStandrardPrice());
                                    salesOrderLineDTO.setTransactionPrice(itemVO.getSalesPrice());
                                    salesOrderLineDTO.setItemCount(Integer.valueOf(promotionVO.getPromotionValue()));
                                    salesOrderLineDTO.setIsPromotion(1);
                                    salesOrderLineDTO.setStatus(1);
                                    salesOrderLineDTO.setType(Integer.valueOf(itemType));
                                    if (StringUtil.isNotEmpty(objID)) {
                                        salesOrderLineDTO.setObjID(objID);
                                        PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(objID);
                                        FlashSaleVO flashSaleVO = flashSaleDAO.findFlashSaleByMainID(objID);
                                        if (preSaleVO != null) {
                                            salesOrderLineDTO.setSalesPrice(preSaleVO.getSalesPrice());
                                        }
                                        if (flashSaleVO != null) {
                                            salesOrderLineDTO.setSalesPrice(flashSaleVO.getSalesPrice());
                                        }
                                    } else {
                                        salesOrderLineDTO.setSalesPrice(itemVO.getSalesPrice());
                                    }
                                    salesOrderLineDAO.addSalesOrderLine(salesOrderLineDTO);
                                    itemDTO.setMainID(itemVO.getMainID());
                                    if (itemVO.getStock() > Integer.valueOf(itemCount)) {
                                        itemDTO.setStock(itemVO.getStock() - Integer.valueOf(itemCount));
                                    }
                                    itemDAO.updateItemByMainID(itemDTO);
                                }
                            }
                        }
                    }
                }
            }
        }
    }    
}
