package com.kpluswebup.web.service.impl;

import java.io.IOException;
import java.io.OutputStream;
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

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.product.dao.SupplierItemDAO;
import com.kpluswebup.web.service.SupplierItemService;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class SupplierItemServiceImpl implements SupplierItemService {

    @Autowired
    /**
     * 供应商商品管理DAO
     */
    private SupplierItemDAO supplierItemDAO;

    @Autowired
    private SystemCodeDAO   systemCodeDAO;

    @Override
    /**
     * 
     * TODO 新增/更新供应商商品信息（可选）.
     * user lei.zhou@kata.com.cn
     * @see com.kpluswebup.web.service.SupplierItemService#addOrEditSupplierItem(com.kpluswebup.web.domain.SupplierItemDTO)
     */
    public int addOrEditSupplierItem(SupplierItemDTO supplierItemDTO) {
        SupplierItemVO supplierItemVO = supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
        if (supplierItemVO == null) {
            // 新增
            String mainID = null;
            CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.ITEMID);
            if (codeConfigVO != null) {
                mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
            }
            supplierItemDTO.setMainID(mainID);
            supplierItemDAO.insertSupplierItem(supplierItemDTO);
        } else {
            // 修改
            supplierItemDTO.setMainID(supplierItemVO.getMainID());
            supplierItemDAO.updateSupplierItem(supplierItemDTO);
        }
        return 0;
    }

    @Override
    /**
     * 
     * TODO 删除供应商商品（可选）.
     * user lei.zhou@kata.com.cn
     * @see com.kpluswebup.web.service.SupplierItemService#deleteSupplierItem(com.kpluswebup.web.domain.SupplierItemDTO)
     */
    public int deleteSupplierItem(SupplierItemDTO supplierItemDTO) {
       return supplierItemDAO.updateSupplierItem(supplierItemDTO);
    }

    @Override
    public int findItemBySupplierAdnType(String mainID, int type) {

        return supplierItemDAO.findItemBySupplierAdnType(mainID, type);
    }

    /**
     * 审核供应商
     */
    @Override
    public Integer updateSupplierItemStatus(String mainID, String status) {
        SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
        supplierItemDTO.setMainID(mainID);
        if (StringUtil.isInteger(status)) {
            supplierItemDTO.setStatus(Integer.parseInt(status));
            Integer count = supplierItemDAO.updateSupplierItemStatus(supplierItemDTO);
            return count;
        }

        return 0;
    }

    @Override
    /**
     * 供应商 商品列表
     * @param supplierItemDTO
     * @return
     */
    public List<SupplierItemVO> findSupplierItem(SupplierItemDTO supplierItemDTO) {
//        supplierItemDTO.doPage(supplierItemDAO.findSupplierItemCount(supplierItemDTO), supplierItemDTO.getPageNo(),
//                               supplierItemDTO.getPageSize());
//        return supplierItemDAO.findSupplierItemPagination(supplierItemDTO);
        supplierItemDTO.doPage(supplierItemDAO.findSupplierItemCountTparts(supplierItemDTO), supplierItemDTO.getPageNo(),
                				supplierItemDTO.getPageSize());
        return supplierItemDAO.findSupplierItemPaginationTparts(supplierItemDTO);
        
    }

    @Override
    public List<ItemVO> findTopThreeSaleItem(String supplierID) {

        return supplierItemDAO.findTopThreeSaleItem(supplierID);
    }

    @Override
    public void updateSupplierItemStatusWhichStockLess20(String supplierID) {
        supplierItemDAO.updateSupplierItemStatusWhichStockLess20(supplierID);

    }

    @Override
    public int batchConfirmSupplierItem(String ids) {
        supplierItemDAO.batchConfirmSupplierItem(ids.split(","));
        return 0;
    }

    @Override
    public Boolean deleteSupplierItemBymainID(String mainID) {
        try {
            supplierItemDAO.deleteSupplierItemBymainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SupplierItemVO findSupplierItemByItemIDAndSupplierID(SupplierItemDTO supplierItemDTO) {
        return supplierItemDAO.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
    }

	@Override
	public void exportSupplierItem(HttpServletResponse response, SupplierItemDTO supplierItemDTO) {
		try {
			List<SupplierItemVO> list = supplierItemDAO.findSupplierItem(supplierItemDTO);
			HSSFWorkbook exportBook = this.exportSupplierItems(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("供应商产品.xls").getBytes("gb2312"), "ISO8859_1"));
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
	
	public HSSFWorkbook exportSupplierItems(List<SupplierItemVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("供应商产品");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 18 * 128);
        sheet.setColumnWidth(3, 18 * 128);
        sheet.setColumnWidth(4, 18 * 256);
        sheet.setColumnWidth(5, 18 * 128);
        sheet.setColumnWidth(6, 18 * 200);
        sheet.setColumnWidth(7, 18 * 128);
        sheet.setColumnWidth(8, 18 * 128);
        sheet.setColumnWidth(9, 18 * 128);
        sheet.setColumnWidth(10, 18 * 256);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
//        HSSFCellStyle style1 = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
        cell.setCellValue("供应商名称");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("商品名称");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("商品编号");
        

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("价格");
        
        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("库存");
        
        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("修改时间");
        
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("审核状态");
        
        if(list != null){
        	for (int i = 0; i < list.size(); i++) {
        		SupplierItemVO supplierItemVO = list.get(i);
        		HSSFRow content = sheet.createRow(i + 1);
        		cell = content.createCell(0);
        		cell.setCellValue(supplierItemVO.getSupplierName());
        		cell = content.createCell(1);
        		cell.setCellValue(supplierItemVO.getItemName());
        		cell = content.createCell(2);
        		cell.setCellValue(supplierItemVO.getItemID());
        		cell = content.createCell(3);
        		cell.setCellValue(supplierItemVO.getSalesPrice());
        		cell = content.createCell(4);
        		cell.setCellValue(supplierItemVO.getStock());
        		cell = content.createCell(5);
        		cell.setCellValue(DateUtil.getDateTime(supplierItemVO.getModifyTime()));
        		cell = content.createCell(6);
        		if(supplierItemVO.getStatus() != null){
        			if(supplierItemVO.getStatus() == 0){
        				cell.setCellValue("未审核");
        			}else if(supplierItemVO.getStatus() == 1){
        				cell.setCellValue("审核通过");
        			}else if(supplierItemVO.getStatus() == 2){
        				cell.setCellValue("审核未通过");
        			}
        		}
        	}
        }
        return workbook;
    }
}
