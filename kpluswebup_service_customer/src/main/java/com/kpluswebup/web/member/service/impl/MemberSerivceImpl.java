package com.kpluswebup.web.member.service.impl;

import static org.springframework.util.Assert.notNull;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerGradeDAO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerGradeDTO;
import com.kpluswebup.web.domain.CustomerSalesOrderVO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.UUIDUtil;
import com.swetake.util.Qrcode;

@Service
public class MemberSerivceImpl implements MemberSerivce {

    @Autowired
    private CustomerDAO      customerDAO;
    @Autowired
    private CustomerGradeDAO customerGradeDAO;
    @Autowired
    private SystemCodeDAO    systemCodeDAO;

    public List<CustomerVO> findCustomerByPagination(CustomerDTO customerDTO) {
        notNull(customerDTO, "customerDTO is null");
        Long count = customerDAO.findCustomerCount(customerDTO);
        customerDTO.doPage(count, customerDTO.getPageNo(), customerDTO.getPageSize());
        List<CustomerVO> list = customerDAO.findCustomerByPagination(customerDTO);
        return list;
    }
    
    public List<CustomerVO> findCustomerApplyByPagination(CustomerDTO customerDTO) {
        notNull(customerDTO, "customerDTO is null");
        Long count = customerDAO.findCustomerApplyCount(customerDTO);
        customerDTO.doPage(count, customerDTO.getPageNo(), customerDTO.getPageSize());
        List<CustomerVO> list = customerDAO.findCustomerApplyByPagination(customerDTO);
        return list;
    }


    public CustomerVO findCustomeByMianId(String mainId) {
        notNull(mainId, "mainId is null");
        return customerDAO.findCustomerByMainID(mainId);
    }

    public void deleteCustomerBatch(String mainIds) {
        notNull(mainIds, "mainIds is null");
        String[] ids = mainIds.split(",");
        for (String mainId : ids) {
            customerDAO.deleteCustomerByMainId(mainId);
        }

    }

    public void lockCustomerBatch(String mainIds,String status) {
        notNull(mainIds, "mainIds is null");
        String[] ids = mainIds.split(",");
        for (String mainId : ids) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(mainId);
            customerDTO.setStatus(1);
            if(status.equals("5")){
                CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypedefault();
                if (customerGradeVO != null) {
                    customerDTO.setGradeID(customerGradeVO.getMainID());
                }
            }
            if(status.equals("6")){
                CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypeVIP();
                if (customerGradeVO != null) {
                    customerDTO.setGradeID(customerGradeVO.getMainID());
                }
            }
            customerDAO.updateCustomerByMainId(customerDTO);
        }
    }

    public void unLockCustomerBatch(String mainIds,String status) {
        notNull(mainIds, "mainIds is null");
        String[] ids = mainIds.split(",");
        for (String mainId : ids) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(mainId);
            customerDTO.setStatus(Integer.valueOf(status));
            customerDAO.updateCustomerByMainId(customerDTO);
        }

    }

    public void updateMember(CustomerDTO customerDTO) {
        customerDAO.updateCustomerByMainId(customerDTO);
    }

    @Override
	public void addRegister(CustomerDTO customerDTO) {
    	customerDAO.insertCustomer(customerDTO);
	}
    
    @Override
	public void editRegister(CustomerDTO customerDTO) {
    	customerDAO.updateCustomerByMainId(customerDTO);
	}
    
    @Override
    public List<CustomerSalesOrderVO> findCustomerSalesOrder(SalesOrderDTO salesOrderDTO) {
        notNull(salesOrderDTO, "salesOrderDTO is null");
        salesOrderDTO.setCustomerID(salesOrderDTO.getCustomerID());
        Long count = customerDAO.findCustomerSalesOrderCount(salesOrderDTO);
        salesOrderDTO.doPage(count, salesOrderDTO.getPageNo(), salesOrderDTO.getPageSize());
        List<CustomerSalesOrderVO> list = customerDAO.findCustomerSalesOrderByPagination(salesOrderDTO);
        return list;
    }

    @Override
    public List<ItemConsultingVO> findCustomerConsulting(ItemConsultingDTO itemConsultingDTO) {
        notNull(itemConsultingDTO, "itemConsultingDTO is null");
        itemConsultingDTO.setCustomerID(itemConsultingDTO.getCustomerID());
        Long count = customerDAO.findCustomerConsultingCount(itemConsultingDTO);
        itemConsultingDTO.doPage(count, itemConsultingDTO.getPageNo(), itemConsultingDTO.getPageSize());
        List<ItemConsultingVO> list = customerDAO.findCustomerConsultingByPagination(itemConsultingDTO);
        return list;
    }

    @Override
    public ItemConsultingVO findConsultingByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return customerDAO.findConsultingByMainID(mainID);
    }

    @Override
    public void replyConsulting(ItemConsultingDTO itemConsultingDTO) {
        notNull(itemConsultingDTO, "itemConsultingDTO is null");
        customerDAO.replyConsulting(itemConsultingDTO);
    }

    @Override
    public Boolean deleteConsulting(String mainIds) {
        notNull(mainIds, "mainIds is null");
        try {
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                customerDAO.deleteConsultingByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ItemReviewVO> findCustomerReview(ItemReviewDTO itemReviewDTO) {
        notNull(itemReviewDTO, "itemReviewDTO is null");
        Long count = customerDAO.findCustomerReviewCount(itemReviewDTO);
        itemReviewDTO.doPage(count, itemReviewDTO.getPageNo(), itemReviewDTO.getPageSize());
        List<ItemReviewVO> list = customerDAO.findCustomerReviewByPagination(itemReviewDTO);
        return list;
    }

    @Override
    public ItemReviewVO findReviewByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return customerDAO.findReviewByMainID(mainID);
    }

    @Override
    public void replyReview(ItemReviewDTO itemReviewDTO) {
        notNull(itemReviewDTO, "itemReviewDTO is null");
        customerDAO.replyReview(itemReviewDTO);
    }

    @Override
    public Boolean checkReview(String mainIds, String currentOperator) {
        notNull(mainIds, "mainIds is null");
        try {
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
                itemReviewDTO.setStatus(2);
                itemReviewDTO.setModifier(currentOperator);
                itemReviewDTO.setMainID(mainId);
                customerDAO.updateReviewByMainID(itemReviewDTO);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void exportMember(HttpServletResponse response, CustomerDTO customerDTO) {
        try {
        	List<CustomerVO> list = customerDAO.findCustomer(customerDTO);
            HSSFWorkbook exportBook = this.exportMemberCenter(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("会员信息.xls").getBytes("gb2312"), "ISO8859_1"));
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

    public HSSFWorkbook exportMemberCenter(List<CustomerVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("会员信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 18 * 128);
        //sheet.setColumnWidth(3, 18 * 128);
        sheet.setColumnWidth(3, 18 * 200);
        sheet.setColumnWidth(4, 18 * 128);
        sheet.setColumnWidth(5, 18 * 128);
        sheet.setColumnWidth(6, 18 * 128);
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
        cell.setCellValue("会员编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("用户名");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("昵称");

       // cell = row.createCell(3);
       // cell.setCellStyle(style);
       // cell.setCellValue("地区");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("联系人姓名");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("注册时间");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("会员等级");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CustomerVO customerVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(customerVO.getMainID());
                cell = content.createCell(1);
                cell.setCellValue(customerVO.getUsername());
                cell = content.createCell(2);
                cell.setCellValue(customerVO.getCompanyName());
               // cell = content.createCell(3);
               // cell.setCellValue(customerVO.getProvinceName()+customerVO.getCityName()+customerVO.getDistrictName());
                cell = content.createCell(3);
                cell.setCellValue(customerVO.getName());
                cell = content.createCell(4);
                cell.setCellValue(DateUtil.getDateTime(customerVO.getCreateTime()));
                cell = content.createCell(5);
                cell.setCellValue(customerVO.getGradeName());
                cell = content.createCell(6);
                if (customerVO.getStatus() == 1) {
                    cell.setCellValue("正常");
                } else {
                    cell.setCellValue("锁定");
                }
            }
        }
        return workbook;
    }

    public List<CustomerVO> findCustomerAll() {
        return customerDAO.findCustomerAll();
    }

    public Boolean addMember(String mobile, String companyName, String password, String mainID, String openid) {
        Boolean status = true;
        try {
            notNull(mobile, "phone is null");
            notNull(password, "password is null");
            CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypedefault();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(memberID());
            customerDTO.setUsername(mobile);
            customerDTO.setMobile(mobile);
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
            if (customerGradeVO != null) {
                customerDTO.setGradeID(customerGradeVO.getMainID());
            }
            if (mainID != null) {
                customerDTO.setReferee(mainID);
            }
            customerDTO.setQrcodeurl(qrCodeCustomer(customerDTO.getMainID()));
            customerDAO.insertCustomer(customerDTO);
        } catch (Exception e) {
            status = false;
        }
        if (status) {
            if (mainID != null) {
                CustomerDTO customerDTO2 = new CustomerDTO();
                customerDTO2.setReferee(mainID);
                Long count = customerDAO.findCustomerCount(customerDTO2);
                if (count == 6 || count == 10) {
                    CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
                    if (count == 6) {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(6)));
                    } else {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(10)));
                    }
                    List<CustomerGradeVO> list = customerGradeDAO.findCustomerGradeByPagination(customerGradeDTO);
                    if (list.size() > 0) {
                        CustomerDTO customerDTO1 = new CustomerDTO();
                        customerDTO1.setMainID(mainID);
                        customerDTO1.setGradeID(list.get(0).getMainID());
                        customerDAO.updateCustomerByMainId(customerDTO1);
                    }
                }
            }
        }
        return status;
    }

    public Boolean editMember(String picURL1, String picURL2, String picURL3, String mainID, String openid) {
        Boolean status = true;
        try {
            notNull(picURL1, "picURL1 is null");
            notNull(picURL2, "picURL2 is null");
            notNull(picURL3, "picURL3 is null");
            CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypedefault();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(memberID());
            customerDTO.setPicURL1(picURL1);
            customerDTO.setPicURL2(picURL2);
            customerDTO.setPicURL3(picURL3);
            if (customerGradeVO != null) {
                customerDTO.setGradeID(customerGradeVO.getMainID());
            }
            if (mainID != null) {
                customerDTO.setReferee(mainID);
            }
            customerDTO.setQrcodeurl(qrCodeCustomer(customerDTO.getMainID()));
            customerDAO.insertCustomer(customerDTO);
        } catch (Exception e) {
            status = false;
        }
        if (status) {
            if (mainID != null) {
                CustomerDTO customerDTO2 = new CustomerDTO();
                customerDTO2.setReferee(mainID);
                Long count = customerDAO.findCustomerCount(customerDTO2);
                if (count == 6 || count == 10) {
                    CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
                    if (count == 6) {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(6)));
                    } else {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(10)));
                    }
                    List<CustomerGradeVO> list = customerGradeDAO.findCustomerGradeByPagination(customerGradeDTO);
                    if (list.size() > 0) {
                        CustomerDTO customerDTO1 = new CustomerDTO();
                        customerDTO1.setMainID(mainID);
                        customerDTO1.setGradeID(list.get(0).getMainID());
                        customerDAO.updateCustomerByMainId(customerDTO1);
                    }
                }
            }
        }
        return status;
    }
    
    
    
   public Boolean  addRegister(String mobile, String address, String companyName, String picURL1, String password, String provinceID,String cityID,String districtID,String mainID) {
    	Boolean status = true;
        try {
            notNull(mobile, "phone is null");
            notNull(password, "password is null");
            CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypedefault();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(memberID());
            customerDTO.setMainID(memberID());
            customerDTO.setUsername(mobile);
            customerDTO.setMobile(mobile);
            customerDTO.setAddress(address);
            customerDTO.setCompanyName(companyName);
            customerDTO.setPicURL1(picURL1);
            customerDTO.setProvinceID(provinceID);
            customerDTO.setCityID(cityID);
            customerDTO.setDistrictID(districtID);
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
            if (customerGradeVO != null) {
                customerDTO.setGradeID(customerGradeVO.getMainID());
            }
            customerDTO.setQrcodeurl(qrCodeCustomer(customerDTO.getMainID()));
            customerDAO.insertCustomer(customerDTO);
        } catch (Exception e) {
            status = false;
        }
        if (status) {
            if (mainID != null) {
                CustomerDTO customerDTO2 = new CustomerDTO();
                customerDTO2.setReferee(mainID);
                Long count = customerDAO.findCustomerCount(customerDTO2);
                if (count == 6 || count == 10) {
                    CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
                    if (count == 6) {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(6)));
                    } else {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(10)));
                    }
                    List<CustomerGradeVO> list = customerGradeDAO.findCustomerGradeByPagination(customerGradeDTO);
                    if (list.size() > 0) {
                        CustomerDTO customerDTO1 = new CustomerDTO();
                        customerDTO1.setMainID(mainID);
                        customerDTO1.setGradeID(list.get(0).getMainID());
                        customerDAO.updateCustomerByMainId(customerDTO1);
                    }
                }
            }
        }
        return status;
   
    }
    
   public Boolean  editRegister(String picURL2, String picURL3, String invoice, String mainID) {
    	Boolean status = true;
        try {
            notNull(picURL2, "picURL2 is null");
            notNull(picURL3, "picURL3 is null");
            CustomerGradeVO customerGradeVO = customerGradeDAO.findCustomerGradeTypedefault();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(memberID());
            customerDTO.setPicURL2(picURL2);
            customerDTO.setPicURL3(picURL3);
            customerDTO.setInvoice(invoice);
            customerDTO.setMainID(mainID);
            if (customerGradeVO != null) {
                customerDTO.setGradeID(customerGradeVO.getMainID());
            }
            if (mainID != null) {
                customerDTO.setReferee(mainID);
            }
            customerDTO.setQrcodeurl(qrCodeCustomer(customerDTO.getMainID()));
            customerDAO.updateCustomerByMainId(customerDTO);
        } catch (Exception e) {
            status = false;
        }
        if (status) {
            if (mainID != null) {
                CustomerDTO customerDTO2 = new CustomerDTO();
               // customerDTO2.setReferee(mainID);
                Long count = customerDAO.findCustomerCount(customerDTO2);
                if (count == 6 || count == 10) {
                    CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
                    if (count == 6) {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(6)));
                    } else {
                        customerGradeDTO.setGradeAmount(Double.parseDouble(String.valueOf(10)));
                    }
                    List<CustomerGradeVO> list = customerGradeDAO.findCustomerGradeByPagination(customerGradeDTO);
                    if (list.size() > 0) {
                        CustomerDTO customerDTO1 = new CustomerDTO();
                        customerDTO1.setMainID(mainID);
                        customerDTO1.setGradeID(list.get(0).getMainID());
                        customerDAO.updateCustomerByMainId(customerDTO1);
                    }
                }
            }
        }
        return status;
}
    
    
    public String memberID() {
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.CUSTOMERID);
        String flowCode = RandomStringUtils.randomNumeric(9);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String result = flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeDAO.findSystemCode(Constant.CUSTOMERID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

    public Boolean findCustomeByPhone(String phone) {
        notNull(phone, "phone is null");
        CustomerVO customerVO = customerDAO.findCusertomerByUserName(phone);
        if (customerVO != null) {
            return true;
        }
        return false;
    }
    /**
     * 昵称
     * @param name
     * @return
     */
    public Boolean findCustomeByName(String name) {
        notNull(name, "phone is null");
        CustomerVO customerVO = customerDAO.findCusertomerByName(name);
        if (customerVO != null) {
            return true;
        }
        return false;
    }

    @Override
    public Integer insertCustomer(CustomerDTO customerDTO) {
        return customerDAO.insertCustomer(customerDTO);
    }

    @Override
    public List<CustomerVO> getUserMember(CustomerDTO customerDTO) {
        Long count = customerDAO.findCustomerCount(customerDTO);
        customerDTO.doPage(count, customerDTO.getPageNo(), customerDTO.getPageSize());
        List<CustomerVO> list = customerDAO.getUserMember(customerDTO);
        return list;
    }

    @Override
    public Boolean isLogin(String userName, String password) {
        CustomerVO customerVO = customerDAO.findCusertomerByUserName(userName);
        if (customerVO == null) {
            return null;
        }
        if (Md5Algorithm.getInstance().md5Digest(password.getBytes()).equals(customerVO.getPassword())) {
            return true;
        }
        return false;
    }

    public CustomerVO findCustomerByUserName(String userName) {
        CustomerVO customerVO = customerDAO.findCusertomerByUserName(userName);
        return customerVO;
    }

    @Override
    public CustomerVO findMemberByMainID(String mainID) {
        return customerDAO.findMemberByMainID(mainID);
    }

    @Override
    public CustomerVO findCustomerByOpenID(String openid) {
        return customerDAO.findCustomerByOpenID(openid);
    }

    @SuppressWarnings("static-access")
    public String qrCodeCustomer(String mainID) {
        try {
            String dir = "/file/" + DateUtil.getDateAgo(0);
            getResponse().setContentType("text/html; charset=UTF-8");
            String uuid = UUIDUtil.getUUID();
            String realPath = getRequest().getSession().getServletContext().getRealPath(dir);
            String imgPath = dir + "/" + uuid + ".png";
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            ClassPathResource resource = new ClassPathResource("server.properties");
            Properties prop = new Properties();
            prop.load(new FileReader(resource.getFile()));
            String domain = prop.getProperty("domain");
            this.encoderQRCode(domain + "/weixin/invitation.htm?customerID=" + mainID, realPath + "/" + uuid + ".png");
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码(QRCode)图片
     * 
     * @param content
     * @param imgPath
     */

    public static void encoderQRCode(String content, String imgPath) {

        try {

            Qrcode qrcodeHandler = new Qrcode();

            qrcodeHandler.setQrcodeErrorCorrect('M');

            qrcodeHandler.setQrcodeEncodeMode('B');

            qrcodeHandler.setQrcodeVersion(7);

            System.out.println(content);

            byte[] contentBytes = content.getBytes("gb2312");

            BufferedImage bufImg = new BufferedImage(140, 140,

            BufferedImage.TYPE_INT_RGB);

            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);

            gs.clearRect(0, 0, 140, 140);

            // 设定图像颜色> BLACK

            gs.setColor(Color.BLACK);

            // 设置偏移量 不设置可能导致解析出错

            int pixoff = 2;

            // 输出内容> 二维码

            if (contentBytes.length > 0 && contentBytes.length < 120) {

                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);

                for (int i = 0; i < codeOut.length; i++) {

                    for (int j = 0; j < codeOut.length; j++) {

                        if (codeOut[j][i]) {

                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);

                        }

                    }

                }

            } else {

                System.err.println("QRCode content bytes length = "

                + contentBytes.length + " not in [ 0,120 ]. ");

            }

            gs.dispose();

            bufImg.flush();

            File imgFile = new File(imgPath);

            // 生成二维码QRCode图片

            ImageIO.write(bufImg, "png", imgFile);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public HttpServletResponse getResponse() {
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(getRequest());
        ServletWebRequest webRequest = (ServletWebRequest) ReflectionTestUtils.getField(asyncManager, "asyncWebRequest");
        return webRequest.getResponse();
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return servletRequestAttributes.getRequest();
    }
}
