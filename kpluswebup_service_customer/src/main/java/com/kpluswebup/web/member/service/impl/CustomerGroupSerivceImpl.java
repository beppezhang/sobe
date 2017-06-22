package com.kpluswebup.web.member.service.impl;

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

import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.domain.CustomerGroupDTO;
import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.CustomerGroupSetDTO;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerGroupSetVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class CustomerGroupSerivceImpl implements CustomerGroupSerivce {

    private static final Logger LOGGER = LogManager.getLogger(CustomerGroupSerivceImpl.class);

    @Autowired
    private CustomerGroupDAO    customerGroupDAO;

    public List<CustomerGroupVO> findAllCustomerGroup() {
        try {
            return customerGroupDAO.findAllCustomerGroup();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<CustomerGroupVO> finCustomerGroupByPagination(CustomerGroupDTO customerGroupDTO) {
        notNull(customerGroupDTO, "customerGroupDTO is null");
        Long count = customerGroupDAO.findCustomerGroupCount(customerGroupDTO);
        customerGroupDTO.doPage(count, customerGroupDTO.getPageNo(), customerGroupDTO.getPageSize());
        List<CustomerGroupVO> list = customerGroupDAO.findCustomerGroupByPagination(customerGroupDTO);
        return list;
    }

    @Override
    public void addCustomerGroup(CustomerGroupDTO customerGroupDTO) {
        customerGroupDAO.insertCustomerGroup(customerGroupDTO);
    }

    @Override
    public CustomerGroupVO findCustomerGroupByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return customerGroupDAO.findCustomerGroupByMainID(mainID);
    }

    @Override
    public void editCustomerGroup(CustomerGroupDTO customerGroupDTO) {
        customerGroupDAO.updateCustomerGroupByMainID(customerGroupDTO);
    }

    @Override
    public Boolean deleteCustomerGroupByMainID(String mainIds) {
        notNull(mainIds, "mainIds is null");
        try {
            String[] ids = mainIds.split(",");
            for (String mainId : ids) {
                customerGroupDAO.deleteCustomerGroupByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<CustomerGroupSetVO> findCustomerGroupSetByGroupId(String groupId) {
        return customerGroupDAO.findCustomerGroupSetByGroupId(groupId);
    }

    @Override
    public Boolean deleteGroupSetByID(Long id) {
        notNull(id, "id is null");
        try {
            customerGroupDAO.deleteGroupSetByID(id);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void addGroupSet(CustomerGroupSetDTO customerGroupSetDTO) {
        customerGroupDAO.insertGroupSet(customerGroupSetDTO);
    }

    @Override
    public void editGroupSet(CustomerGroupSetDTO customerGroupSetDTO) {
        customerGroupDAO.updateGroupSetByID(customerGroupSetDTO);
    }

    @Override
    public List<CustomerVO> findCustomerByGroupSet(CustomerVO customerVO) {
        return customerGroupDAO.findCustomerByGroupSet(customerVO);
    }

    @Override
    public void addCustomerGroupMember(CustomerGroupMemberDTO customerGroupMemberDTO) {
        customerGroupDAO.insertCustomerGroupMember(customerGroupMemberDTO);
    }

    @Override
    public List<CustomerGroupMemberVO> findCustomerGroupMemberByPagination(CustomerGroupMemberDTO customerGroupMemberDTO) {
        notNull(customerGroupMemberDTO, "customerGroupMemberDTO is null");
        Long count = customerGroupDAO.findCustomerGroupMemberCount(customerGroupMemberDTO);
        customerGroupMemberDTO.doPage(count, customerGroupMemberDTO.getPageNo(), customerGroupMemberDTO.getPageSize());
        List<CustomerGroupMemberVO> list = customerGroupDAO.findCustomerGroupMemberByPagination(customerGroupMemberDTO);
        return list;
    }

    @Override
    public Boolean deleteCustomerGroupMemberByGroupID(String groupId) {
        notNull(groupId, "groupId is null");
        try {
            customerGroupDAO.deleteCustomerGroupMemberByGroupID(groupId);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<CustomerGroupMemberVO> findCustomerGroupMemberByGroupID(String groupId) {
        return customerGroupDAO.findCustomerGroupMemberByGroupID(groupId);
    }

    @Override
    public List<CustomerGroupVO> findCustomerGroupByName(CustomerGroupDTO customerGroupDTO) {
        Long count = customerGroupDAO.findCustomerGroupByNameCount(customerGroupDTO);
        customerGroupDTO.doPage(count, customerGroupDTO.getPageNo(), customerGroupDTO.getPageSize());
        List<CustomerGroupVO> list = customerGroupDAO.findCustomerGroupByName(customerGroupDTO);
        return list;
    }

    @Override
    public List<CustomerGroupMemberVO> findCustomerGroupMember() {
        return customerGroupDAO.findCustomerGroupMember();
    }

    @Override
    public void exportCustomerGroupMember(HttpServletResponse response, String mainIds,String groupID) {
        try {
            String ids[] = mainIds.split(",");
            List<CustomerVO> list = new ArrayList<CustomerVO>();
            CustomerGroupMemberDTO customerGroupMemberDTO = new CustomerGroupMemberDTO();
            if (StringUtil.isNotEmpty(groupID)) {
                customerGroupMemberDTO.setGroupID(groupID);
            }
            for (String mainId : ids) {
                customerGroupMemberDTO.setCustomerID(mainId);
                CustomerVO customerVO = customerGroupDAO.findGroupMemberByGroupIDCustomerID(customerGroupMemberDTO);
                list.add(customerVO);
            }
            HSSFWorkbook exportBook = this.exportCustomerGroupMemberCenter(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("组内采购商信息.xls").getBytes("gb2312"), "ISO8859_1"));
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
    
    public HSSFWorkbook exportCustomerGroupMemberCenter(List<CustomerVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("组内采购商信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 128);
        sheet.setColumnWidth(2, 18 * 128);
        sheet.setColumnWidth(3, 18 * 128);
        /*sheet.setColumnWidth(4, 18 * 200);
        sheet.setColumnWidth(5, 18 * 128);
        sheet.setColumnWidth(6, 18 * 128);
        sheet.setColumnWidth(7, 18 * 128);*/
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
        cell.setCellValue("客户编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("用户名");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("姓名");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("手机号");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("邮箱");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("积分");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("会员等级");

        cell = row.createCell(7);
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
                cell.setCellValue(customerVO.getName());
                cell = content.createCell(3);
                cell.setCellValue(customerVO.getTelephone());
                cell = content.createCell(4);
                cell.setCellValue(customerVO.getEmail());
                cell = content.createCell(5);
                cell.setCellValue(customerVO.getScore());
                cell = content.createCell(6);
                cell.setCellValue(customerVO.getGradeName());
                cell = content.createCell(7);
                if (customerVO.getStatus() == 1) {
                    cell.setCellValue("正常");
                } else {
                    cell.setCellValue("锁定");
                }
            }
        }
        return workbook;
    }
}
