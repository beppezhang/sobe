package com.kpluswebup.web.account.service.impl;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.account.dao.AccountDetailDAO;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerGradeDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.FlashSaleDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.order.dao.SalesOrderReturnDAO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.promotion.dao.FlashSaleDAO;
import com.kpluswebup.web.promotion.dao.PreSaleDAO;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FlashSaleVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {

    private static final Logger LOGGER = LogManager.getLogger(AccountDetailServiceImpl.class);

    @Autowired
    private AccountDetailDAO    accountDetailDAO;
    @Autowired
    private SalesOrderReturnDAO salesOrderReturnDAO;
    @Autowired
    private ItemDAO             itemDAO;
    @Autowired
    private SalesOrderLineDAO   salesOrderLineDAO;
    @Autowired
    private SalesOrderDAO       salesOrderDAO;
    @Autowired
    private CustomerGradeDAO    customerGradeDAO;
    @Autowired
    private CustomerDAO         customerDAO;
    @Autowired
    private CustomerGroupDAO    customerGroupDAO;
    @Autowired
    private SystemCodeDAO       systemCodeDAO;
    @Autowired
    private PreSaleDAO          preSaleDAO;
    @Autowired
    private FlashSaleDAO        flashSaleDAO;

    @Override
    public List<AccountDetailVO> findAccountDetailByPagination(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        List<AccountDetailVO> list = null;
        if (accountDetailDTO.getDetailType() != null) {
            if (accountDetailDTO.getDetailType() == 3 && accountDetailDTO.getType() == 1) {
                Long count = accountDetailDAO.findAccountDetailCount(accountDetailDTO);
                accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
                list = accountDetailDAO.findAccountDetailByPagination(accountDetailDTO);
                if (list != null && list.size() > 0) {
                    for (AccountDetailVO accountDetailVO : list) {
                        SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByMainID(accountDetailVO.getObjID());
                        accountDetailVO.setSalesOrderReturnVO(salesOrderReturnVO);
                    }
                }
            } else if (accountDetailDTO.getDetailType() == 3 && accountDetailDTO.getType() == 2) {
                Long count = accountDetailDAO.findAccountDetailCount(accountDetailDTO);
                accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
                list = accountDetailDAO.findAccountDetailByPagination(accountDetailDTO);
                if (list != null && list.size() > 0) {
                    for (AccountDetailVO accountDetailVO : list) {
                        SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnDAO.findSalesOrderReturnByMainID(accountDetailVO.getObjID());
                        accountDetailVO.setSalesOrderReturnVO(salesOrderReturnVO);
                    }
                }
            } else if (accountDetailDTO.getDetailType() == 2) {
                Long count = accountDetailDAO.findPaymentCount(accountDetailDTO);
                accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
                list = accountDetailDAO.findPaymentByPagination(accountDetailDTO);
            } else if (accountDetailDTO.getDetailType() == 4) {
                Long count = accountDetailDAO.findMemberScoreCount(accountDetailDTO);
                accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
                list = accountDetailDAO.findMemberScoreByPagination(accountDetailDTO);
            }
        }
        return list;
    }

    @Override
    public void updateAccountDetail(AccountDetailDTO accountDetailDTO) {
        try {
            notNull(accountDetailDTO, "accountDetailDTO is null");
            accountDetailDAO.updateAccountDetail(accountDetailDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public SalesOrderReturnApplyVO findSalesOrderReturnApply(String returnApplyID) {
        notNull(returnApplyID, "returnApplyID is null");
        return accountDetailDAO.findSalesOrderReturnApply(returnApplyID);
    }

    @Override
    public void updateSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) {
        try {
            notNull(salesOrderReturnApplyDTO, "salesOrderReturnApplyDTO is null");
            accountDetailDAO.updateSalesOrderReturnApply(salesOrderReturnApplyDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public SalesOrderReturnVO findSalesOrderReturn(String returnID) {
        notNull(returnID, "returnID is null");
        return accountDetailDAO.findSalesOrderReturn(returnID);
    }

    @Override
    public void updateSalesOrderReturn(SalesOrderReturnDTO salesOrderReturnDTO) {
        try {
            notNull(salesOrderReturnDTO, "salesOrderReturnDTO is null");
            accountDetailDAO.updateSalesOrderReturn(salesOrderReturnDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public AccountDetailVO findAccountDetail(String accountID) {
        notNull(accountID, "accountID is null");
        return accountDetailDAO.findAccountDetail(accountID);
    }

    @Override
    public void addMemberScore(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.PAYDETAILID);
        if (codeConfigVO != null) {
            String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
            accountDetailDTO.setMainID(mainID);
        }
        accountDetailDAO.insertMemberScore(accountDetailDTO);
        if (accountDetailDTO.getDetailType() == 2) {
            // 改变商品的售出件数
            List<SalesOrderLineVO> list = salesOrderLineDAO.findSalesOrderLine(accountDetailDTO.getObjID());
            for (SalesOrderLineVO salesOrderLineVO : list) {
                if (StringUtil.isNotEmpty(salesOrderLineVO.getObjID())) {
                    PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(salesOrderLineVO.getObjID());
                    FlashSaleVO flashSaleVO = flashSaleDAO.findFlashSaleByMainID(salesOrderLineVO.getObjID());
                    if (preSaleVO != null) {
                        PreSaleDTO preSaleDTO = new PreSaleDTO();
                        preSaleDTO.setMaxmumQty(Long.valueOf(preSaleVO.getMaxmumQty()
                                                             - Integer.valueOf(salesOrderLineVO.getItemCount())));
                        preSaleDTO.setPeopleBuy(preSaleVO.getPeopleBuy() + 1);
                        preSaleDTO.setMainID(preSaleVO.getMainID());
                        preSaleDAO.updatePreSaleByMainID(preSaleDTO);
                    }
                    if (flashSaleVO != null) {
                        FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
                        flashSaleDTO.setTotality(flashSaleVO.getTotality()
                                                 - Integer.valueOf(salesOrderLineVO.getItemCount()));
                        flashSaleDTO.setPeopleRush(flashSaleVO.getPeopleRush() + 1);
                        flashSaleDTO.setMainID(flashSaleVO.getMainID());
                        flashSaleDAO.updateFlashSale(flashSaleDTO);
                    }
                } else {
                    ItemVO itemVO = itemDAO.findItemById(salesOrderLineVO.getItemID());
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setSalesVolume(itemVO.getSalesVolume() + salesOrderLineVO.getItemCount());
                    itemDAO.updateItemByMainID(itemDTO);
                }
            }
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            salesOrderDTO.setPaymentStatus(1);
            salesOrderDTO.setMainID(accountDetailDTO.getObjID());
            salesOrderDAO.updateSalesOrderByMainID(salesOrderDTO);
            SalesOrderVO salesOrderVO = salesOrderDAO.findSalesOrderByMainID(accountDetailDTO.getObjID());
            CustomerVO customerVO = customerDAO.findCustomerByMainID(salesOrderVO.getCustomerID());
            CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeByMainID(customerVO.getGradeID());
            if (customerGradeVO != null && customerGradeVO.getGradeSet() == 4) {
                Integer shoppingIntegral = Double.valueOf(accountDetailDTO.getAmount()
                                                                  * (customerGradeVO.getShoppingIntegral() / 100)).intValue();
                if (shoppingIntegral != null && shoppingIntegral > 0) {
                    AccountDetailDTO scoreAccountDetailDTO = new AccountDetailDTO();
                    scoreAccountDetailDTO.setSerialNumber(customerVO.getMainID());
                    scoreAccountDetailDTO.setDetailType(4);
                    scoreAccountDetailDTO.setAccountType(2);
                    scoreAccountDetailDTO.setPaymentType(3);
                    scoreAccountDetailDTO.setAmount(Double.valueOf(shoppingIntegral));
                    scoreAccountDetailDTO.setDescription("购物返积分");
                    scoreAccountDetailDTO.setObjID(salesOrderDTO.getMainID());
                    scoreAccountDetailDTO.setCustomerID(customerVO.getMainID());
                    scoreAccountDetailDTO.setStatus(1);
                    CodeConfigVO codeConfigVO1 = systemCodeDAO.findCodeConfigByID(Constant.PAYDETAILID);
                    if (codeConfigVO1 != null) {
                        String mainID = codeConfigVO1.getCodeEx() + GenerationNum.getRandomNumber(9);
                        scoreAccountDetailDTO.setMainID(mainID);
                    }
                    accountDetailDAO.insertMemberScore(scoreAccountDetailDTO);
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setScore(customerVO.getScore() + shoppingIntegral);
                    customerDTO.setScoreIntotal(customerVO.getScoreIntotal() + shoppingIntegral);
                    customerDTO.setMainID(customerVO.getMainID());
                    customerDAO.updateCustomerByMainId(customerDTO);
                }
            }
            if (StringUtil.isNotEmpty(customerVO.getReferee())) {
                CustomerDTO customerDTO1 = new CustomerDTO();
                customerDTO1.setReferee(customerVO.getReferee());
                Long count = customerDAO.findCustomerCount(customerDTO1);
                if (count >= 10) {
                    CustomerVO refCustomerVO = customerDAO.findCustomerByMainID(customerVO.getReferee());
                    if (refCustomerVO != null) {
                        CustomerGradeVO refCustomerGradeVO = customerGradeDAO.findCustomerGradeByMainID(refCustomerVO.getGradeID());
                        if (refCustomerGradeVO != null && refCustomerGradeVO.getGradeSet() == 4) {
                            Integer lineShoppingIntegral = Double.valueOf(accountDetailDTO.getAmount()
                                                                                  * (customerGradeVO.getLineShoppingIntegral() / 100)).intValue();
                            if (lineShoppingIntegral != null && lineShoppingIntegral > 0) {
                                AccountDetailDTO scoreAccountDetailDTO = new AccountDetailDTO();
                                scoreAccountDetailDTO.setSerialNumber(customerVO.getMainID());
                                scoreAccountDetailDTO.setDetailType(4);
                                scoreAccountDetailDTO.setAccountType(2);
                                scoreAccountDetailDTO.setPaymentType(3);
                                scoreAccountDetailDTO.setAmount(Double.valueOf(lineShoppingIntegral));
                                scoreAccountDetailDTO.setDescription("下线购物返积分");
                                scoreAccountDetailDTO.setObjID(salesOrderDTO.getMainID());
                                scoreAccountDetailDTO.setCustomerID(refCustomerVO.getMainID());
                                scoreAccountDetailDTO.setStatus(1);
                                CodeConfigVO codeConfigVO1 = systemCodeDAO.findCodeConfigByID(Constant.PAYDETAILID);
                                if (codeConfigVO1 != null) {
                                    String mainID = codeConfigVO1.getCodeEx() + GenerationNum.getRandomNumber(9);
                                    scoreAccountDetailDTO.setMainID(mainID);
                                }
                                accountDetailDAO.insertMemberScore(scoreAccountDetailDTO);
                                CustomerDTO customerDTO = new CustomerDTO();
                                customerDTO.setScore(refCustomerVO.getScore() + lineShoppingIntegral);
                                customerDTO.setScoreIntotal(refCustomerVO.getScoreIntotal() + lineShoppingIntegral);
                                customerDTO.setMainID(refCustomerVO.getMainID());
                                customerDAO.updateCustomerByMainId(customerDTO);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<AccountDetailVO> findMemberScoreByPagination(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        Long count = accountDetailDAO.findAccountDetailCountByCustomerID(accountDetailDTO);
        accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
        List<AccountDetailVO> list = accountDetailDAO.findMemberScoreByPagination(accountDetailDTO);
        return list;
    }

    @Override
    public List<AccountDetailVO> findAccountDetailTypeByCustermor(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        Long count = accountDetailDAO.findAccountDetailCountByCustomerID(accountDetailDTO);
        accountDetailDTO.doPage(count, accountDetailDTO.getPageNo(), accountDetailDTO.getPageSize());
        List<AccountDetailVO> list = accountDetailDAO.findAccountDetailTypeByCustermor(accountDetailDTO);
        return list;
    }

    @Override
    public List<AccountDetailVO> findAccountDetailByBuy(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        return accountDetailDAO.findAccountDetailByBuy(accountDetailDTO);
    }

    @Override
    public void insertMemberScore(AccountDetailDTO accountDetailDTO) {
        notNull(accountDetailDTO, "accountDetailDTO is null");
        accountDetailDAO.insertMemberScore(accountDetailDTO);
    }

    @Override
    public void addScore(String[] customerIDs, String[] groupIDs, String type, String amount, String description,
                         String currentOperator) {
        if (customerIDs != null) {
            for (String customerID : customerIDs) {
                AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                CustomerVO customerVO = customerDAO.findMemberByMainID(customerID);
                if (StringUtil.isNotEmpty(type)) {
                    accountDetailDTO.setDetailType(Integer.valueOf(type));
                }
                if (StringUtil.isNotEmpty(amount)) {
                    accountDetailDTO.setAmount(Double.valueOf(amount));
                }
                if (StringUtil.isNotEmpty(description)) {
                    accountDetailDTO.setDescription(description);
                }
                accountDetailDTO.setStatus(1);
                accountDetailDTO.setAccountType(2);
                accountDetailDTO.setMainID(UUIDUtil.getUUID());
                accountDetailDTO.setCreator(currentOperator);
                CustomerDTO customerDTO = new CustomerDTO();
                if (type.equals("5")) {
                    customerDTO.setScoreIntotal(customerVO.getScoreIntotal() + accountDetailDTO.getAmount());
                    customerDTO.setScore(customerVO.getScore() + accountDetailDTO.getAmount());
                } else if (type.equals("6")) {
                    if (customerVO.getScore() >= accountDetailDTO.getAmount()) {
                        customerDTO.setScore(customerVO.getScore() - accountDetailDTO.getAmount());
                    }
                }
                accountDetailDTO.setBlance(customerDTO.getScore());
                accountDetailDTO.setCustomerID(customerID);
                accountDetailDAO.insertMemberScore(accountDetailDTO);
                customerDTO.setMainID(customerID);
                customerDTO.setModifier(currentOperator);
                customerDAO.updateCustomerByMainId(customerDTO);
            }
        }
        if (groupIDs != null) {
            for (String groupID : groupIDs) {
                List<CustomerGroupMemberVO> list = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                if (list != null && list.size() > 0) {
                    for (CustomerGroupMemberVO customerGroupMemberVO : list) {
                        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                        CustomerVO customerVO = customerDAO.findMemberByMainID(customerGroupMemberVO.getCustomerID());
                        if (StringUtil.isNotEmpty(type)) {
                            accountDetailDTO.setDetailType(Integer.valueOf(type));
                        }
                        if (StringUtil.isNotEmpty(amount)) {
                            accountDetailDTO.setAmount(Double.valueOf(amount));
                        }
                        if (StringUtil.isNotEmpty(description)) {
                            accountDetailDTO.setDescription(description);
                        }
                        accountDetailDTO.setStatus(1);
                        accountDetailDTO.setAccountType(2);
                        accountDetailDTO.setMainID(UUIDUtil.getUUID());
                        accountDetailDTO.setCreator(currentOperator);
                        CustomerDTO customerDTO = new CustomerDTO();
                        if (type.equals("5")) {
                            customerDTO.setScoreIntotal(customerVO.getScoreIntotal() + accountDetailDTO.getAmount());
                            customerDTO.setScore(customerVO.getScore() + accountDetailDTO.getAmount());
                        } else if (type.equals("6")) {
                            if (customerVO.getScore() >= accountDetailDTO.getAmount()) {
                                customerDTO.setScore(customerVO.getScore() - accountDetailDTO.getAmount());
                            }
                        }
                        accountDetailDTO.setBlance(customerDTO.getScore());
                        accountDetailDTO.setCustomerID(customerVO.getMainID());
                        accountDetailDAO.insertMemberScore(accountDetailDTO);
                        customerDTO.setMainID(customerVO.getMainID());
                        customerDTO.setModifier(currentOperator);
                        customerDAO.updateCustomerByMainId(customerDTO);
                    }
                }
            }
        }

    }
                    
    @Override
    public void exportFinanceReport(HttpServletResponse response, AccountDetailDTO accountDetailDTO) {
        try {
            List<AccountDetailVO> list = accountDetailDAO.findPaymentByPagination(accountDetailDTO);
            /*if (StringUtil.isNotEmpty(mainIds)) {
                String[] ids = mainIds.split(",");
                for (int i = 0; i < ids.length; i++) {
                    AccountDetailVO accountDetailVO = accountDetailDAO.findAccountDetail(ids[i]);
                    list.add(accountDetailVO);
                }
            }*/
            HSSFWorkbook exportBook = this.exportFinanceReportInfo(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("财务报表.xls").getBytes("gb2312"), "ISO8859_1"));
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

    public HSSFWorkbook exportFinanceReportInfo(List<AccountDetailVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("财务报表");
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
        cell.setCellValue("交易号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("交易金额");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("交易用户");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("付款方式");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("付款时间");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AccountDetailVO accountDetailVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(accountDetailVO.getSerialNumber());
                cell = content.createCell(1);
                cell.setCellValue(accountDetailVO.getAmount());
                cell = content.createCell(2);
                cell.setCellValue(accountDetailVO.getCustomerName());
                cell = content.createCell(3);
                if (accountDetailVO.getPaymentType() == 1) {
                    cell.setCellValue("款到发货");
                } else {
                    cell.setCellValue("货到付款");
                }
                cell = content.createCell(4);
                cell.setCellValue(DateUtil.formatDate(accountDetailVO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return workbook;
    }

}
