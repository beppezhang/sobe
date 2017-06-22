package com.kpluswebup.web.content.service.impl;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.AdvertDAO;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.domain.CmsAdvertClickDTO;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.vo.CmsAdvertClickVO;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.DateUtil;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertDAO advertDAO;

    @Override
    public List<CmsAdvertVO> findAdvertByPagination(CmsAdvertDTO cmsAdvertDTO) {
        notNull(cmsAdvertDTO, "cmsAdvertDTO is null");
        Long count = advertDAO.findAdvertCount(cmsAdvertDTO);
        cmsAdvertDTO.doPage(count, cmsAdvertDTO.getPageNo(), cmsAdvertDTO.getPageSize());
        List<CmsAdvertVO> list = advertDAO.findAdvertByPagination(cmsAdvertDTO);
        if (list != null && list.size() > 0) {
            for (CmsAdvertVO cmsAdvertVO : list) {
                cmsAdvertDTO = new CmsAdvertDTO();
                cmsAdvertDTO.setSerachDate(new Date());
                cmsAdvertDTO.setPageSize(2l);
                cmsAdvertDTO.setParentID(cmsAdvertVO.getMainID());
                List<CmsAdvertVO> childList = advertDAO.findAdvertIndexChild(cmsAdvertDTO);
                cmsAdvertVO.setChildList(childList);
            }

        }

        return list;
    }

    @Override
    public Boolean deleteAdvertByMainID(String mainID) {
        try {
            advertDAO.deleteAdvertByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addAdvert(CmsAdvertDTO cmsAdvertDTO) {
        notNull(cmsAdvertDTO, "cmsContentDTO is null");
        advertDAO.insertAdvert(cmsAdvertDTO);
    }

    @Override
    public CmsAdvertVO findAdvertByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return advertDAO.findAdvertByMainID(mainID);
    }

    @Override
    public void editAdvert(CmsAdvertDTO cmsAdvertDTO) {
        notNull(cmsAdvertDTO, "cmsAdvertDTO is null");
        notNull(cmsAdvertDTO.getMainID(), "MainID is null");
        advertDAO.updateAdvertByMainID(cmsAdvertDTO);
    }

    @Override
    public List<CmsAdvertClickVO> findAdvertClickByPagination(CmsAdvertClickDTO cmsAdvertClickDTO) {
        notNull(cmsAdvertClickDTO, "cmsAdvertClickDTO is null");
        Long count = advertDAO.findAdvertClickCount(cmsAdvertClickDTO);
        cmsAdvertClickDTO.doPage(count, cmsAdvertClickDTO.getPageNo(), cmsAdvertClickDTO.getPageSize());
        List<CmsAdvertClickVO> list = advertDAO.findAdvertClickByPagination(cmsAdvertClickDTO);
        return list;
    }

    @Override
    public List<CmsAdvertLinkVO> findAdvertLinkByName(String name, Integer type) {
        List<CmsAdvertLinkVO> list = null;
        if (type == 2) {
            list = advertDAO.findProductTypeByName(name);
        } else if (type == 3) {
            list = advertDAO.findBrandByName(name);
        } else if (type == 4) {
            list = advertDAO.findProductByName(name);
        } else if (type == 5) {
            list = advertDAO.findItemByName(name);
        }
        return list;
    }

    @Override
    public ItemVO findItemByMainID(String mainID) {
        return advertDAO.findItemByMainID(mainID);
    }

    @Override
    public void exportAdvertClick(HttpServletResponse response, String advertID) {
        try {
            List<CmsAdvertClickVO> list = advertDAO.findAdvertClickByPagination(null);
            HSSFWorkbook exportBook = this.exportAdvertClickAll(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("广告点击明细信息.xls").getBytes("gb2312"), "ISO8859_1"));
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

    public HSSFWorkbook exportAdvertClickAll(List<CmsAdvertClickVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("广告点击信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 128);
        sheet.setColumnWidth(2, 18 * 400);
        sheet.setColumnWidth(3, 18 * 128);
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
        cell.setCellValue("IP");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("会员");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("时间");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CmsAdvertClickVO cmsAdvertClickVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(cmsAdvertClickVO.getIP());
                cell = content.createCell(1);
                cell.setCellValue(cmsAdvertClickVO.getCustomerName());
                cell = content.createCell(2);
                cell.setCellValue(DateUtil.formatDate(cmsAdvertClickVO.getClickTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return workbook;
    }

    @Override
    public List<CmsAdvertVO> findAdvertIndex() {
        return advertDAO.findAdvertIndex();
    }

}
