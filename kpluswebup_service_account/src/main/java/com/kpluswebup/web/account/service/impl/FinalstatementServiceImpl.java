package com.kpluswebup.web.account.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

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

import com.kpluswebup.web.account.dao.FinalstatementDAO;
import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.domain.FinalstatementDTO;
import com.kpluswebup.web.domain.RSOFFinalstatementAndSaleOrderDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.supplier.dao.SupplierDAO;
import com.kpluswebup.web.vo.FinalstatementVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.DateUtil;
@Service("finalstatementService")
public class FinalstatementServiceImpl implements FinalstatementService {
	
	@Autowired
	private FinalstatementDAO finalstatementDAO;
	
	@Autowired
	private SalesOrderDAO salesOrderDAO;
	
	@Autowired
	private SalesOrderLineDAO salesOrderLineDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;

	@Transactional
	@Override
	public int generateYesterdayFinalStatement() {
		List<FinalstatementVO> finalstatementList = finalstatementDAO.findYesterdayFinalStatement();
		if(finalstatementList == null || finalstatementList.size() == 0)
			return 0;
		Map<String, List<FinalstatementVO>> orderGroup = new HashMap<String, List<FinalstatementVO>>();
		for (FinalstatementVO finalstatementVO : finalstatementList) {
			String key = finalstatementVO.getSupplierID();
			if(orderGroup.containsKey(key)){
				orderGroup.get(key).add(finalstatementVO);
			}else{
				List<FinalstatementVO> list = new ArrayList<FinalstatementVO>();
				list.add(finalstatementVO);
				orderGroup.put(key, list);
			}
		}
		Set<Entry<String, List<FinalstatementVO>>> entrySet = orderGroup.entrySet();
		FinalstatementDTO finalstatementDTO = new FinalstatementDTO();
		for (Entry<String, List<FinalstatementVO>> entry : entrySet) {
			String supplierID = entry.getKey();
			List<FinalstatementVO> list = entry.getValue();
			double totalAmount = 0.0;
			finalstatementDTO.setMainID(this.getMainIDByTime());
			finalstatementDTO.setSupplierID(supplierID);
			finalstatementDTO.setCreator("sys");
			for (FinalstatementVO finalstatementVO : list) {
				totalAmount += finalstatementVO.getTotalAmount();
				RSOFFinalstatementAndSaleOrderDTO rs = new RSOFFinalstatementAndSaleOrderDTO();
				rs.setOrderID(finalstatementVO.getOrderID());
				rs.setFinalstatementID(finalstatementDTO.getMainID());
				rs.setTotalAmount(finalstatementVO.getTotalAmount());
				finalstatementDAO.addRSOFFinalstatementAndSaleOrder(rs);
				finalstatementDAO.updateSalesOrderLineIsFinalstatemented(finalstatementVO.getOrderID(), supplierID);
			}
			finalstatementDTO.setTotalAmount(totalAmount);
			finalstatementDAO.addFinalstatement(finalstatementDTO);
		}
		return finalstatementList.size();
	}
	
	private String getMainIDByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		return sdf.format(new Date());
	}



	@Override
	public FinalstatementVO getFinalStatementByMainID(String finalStatementID) {
		FinalstatementVO finalstatementVO = finalstatementDAO.findFinalStatementByMainID(finalStatementID);
		List<SalesOrderVO> salesOrderList = salesOrderDAO.findSalesOrderByFinalstatementID(finalStatementID);
		for (SalesOrderVO salesOrder : salesOrderList) {
			List<SalesOrderLineVO> lineList = salesOrderDAO.findOrderLineByOrderAndSupplier(salesOrder.getMainID(),
					finalstatementVO.getSupplierID());
			Integer itemCount=0;
			Double totalAmount=0d;
			for(SalesOrderLineVO salesOrderLineVO:lineList){
				itemCount+=salesOrderLineVO.getItemCount();
				//totalAmount+=salesOrderLineVO.getTotalAmount();
			}
			salesOrder.setItemCount(itemCount);
			//salesOrder.setTotalAmount(totalAmount);
			salesOrder.setSalesOrderLineList(lineList);
		}
		finalstatementVO.setSalesOrderList(salesOrderList);
		return finalstatementVO;
	}

	@Override
	public List<FinalstatementVO> getFinalStatementListByPaginateion(
			FinalstatementDTO finalstatementDTO) {
		long count = finalstatementDAO.findFinalStatementListCount(finalstatementDTO);
		finalstatementDTO.doPage(count, finalstatementDTO.getPageNo(), finalstatementDTO.getPageSize());
		List<FinalstatementVO> finalstatementList = finalstatementDAO.findFinalStatementListByPaginateion(finalstatementDTO);
		return finalstatementList;
	}

	@Override
	public void confirmFinalstatement(String mainId, String operatorID) {
		
		finalstatementDAO.updateFinalstatementStatus(mainId, operatorID);
		
	}
	
	@Override
	public void exportSettlementDetail(HttpServletResponse response, String finalStatementID) {
		try {
			List<SalesOrderVO> list = salesOrderDAO.findSalesOrderByFinalstatementID(finalStatementID);
            HSSFWorkbook exportBook = this.exportSettlementDetails(list,finalStatementID);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("结算单明细.xls").getBytes("gb2312"), "ISO8859_1"));
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
	
	public HSSFWorkbook exportSettlementDetails(List<SalesOrderVO> list,String finalStatementID) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("结算单明细");
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
        cell.setCellValue("订单编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("下单时间");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("商品数量");
        

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("订单金额");
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	SalesOrderVO salesOrderVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
        		cell = content.createCell(0);
        		cell.setCellValue(salesOrderVO.getMainID());
        		cell = content.createCell(1);
        		cell.setCellValue(DateUtil.getDateTime(salesOrderVO.getCreateTime()));
        		cell = content.createCell(2);
        		FinalstatementVO finalstatementVO = finalstatementDAO.findFinalStatementByMainID(finalStatementID);
        		List<SalesOrderLineVO> lineList = salesOrderDAO.findOrderLineByOrderAndSupplier(salesOrderVO.getMainID(),
        					finalstatementVO.getSupplierID());
        			Integer itemCount=0;
        			//Double totalAmount=0d;
        			for(SalesOrderLineVO salesOrderLineVO:lineList){
        				itemCount+=salesOrderLineVO.getItemCount();
        				//totalAmount+=salesOrderLineVO.getTotalAmount();
        			}
        		cell.setCellValue(itemCount);
        		cell = content.createCell(3);
        		cell.setCellValue(salesOrderVO.getTotalAmount());
            }
        }
        return workbook;
    }
	
	@Override
	public void exportSettlement(HttpServletResponse response, FinalstatementDTO finalstatementDTO) {
		try {
			List<FinalstatementVO> list = finalstatementDAO.findFinalStatementList(finalstatementDTO);
			HSSFWorkbook exportBook = this.exportSettlements(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("结算单.xls").getBytes("gb2312"), "ISO8859_1"));
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
	
	public HSSFWorkbook exportSettlements(List<FinalstatementVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("结算单");
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
        cell.setCellValue("结算单号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("供应商");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("订单金额");
        
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("结算金额");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("创建日期");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("结算日期");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("结算人");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	FinalstatementVO finalstatementVO = list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(finalstatementVO.getMainID());
                cell = content.createCell(1);
                cell.setCellValue(finalstatementVO.getSupplierName());
                cell = content.createCell(2);
                cell.setCellValue(finalstatementVO.getTotalAmount());
                cell = content.createCell(3);
                cell.setCellValue(finalstatementVO.getTotalAmount()*0.97);
                cell = content.createCell(4);
                cell.setCellValue(DateUtil.getDateTime(finalstatementVO.getCreateTime()));
                cell = content.createCell(5);
                if(finalstatementVO.getStatus() != null){
                	if(finalstatementVO.getStatus() == 0){
                		cell.setCellValue("未结算");
                	}else if(finalstatementVO.getStatus() == 1){
                		cell.setCellValue("已结算");
                	}
                }
                cell = content.createCell(6);
                cell.setCellValue(DateUtil.getDateTime(finalstatementVO.getModifyTime()));
                cell = content.createCell(7);
                cell.setCellValue(finalstatementVO.getModifier());
            }
        }
        return workbook;
    }

}
