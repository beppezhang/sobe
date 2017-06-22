package com.kpluswebup.web.service.impl;

import java.io.IOException;
import java.io.OutputStream;
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

import com.kpluswebup.web.domain.CouponBatchDTO;
import com.kpluswebup.web.domain.CouponDTO;
import com.kpluswebup.web.promotion.dao.CouponBatchDAO;
import com.kpluswebup.web.service.CouponBatchService;
import com.kpluswebup.web.vo.CouponBatchVO;
import com.kpluswebup.web.vo.CouponPromotionVO;
import com.kpluswebup.web.vo.CouponVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class CouponBatchServiceImpl implements CouponBatchService {

	private static final Logger LOGGER = LogManager
			.getLogger(CouponBatchServiceImpl.class);

	@Autowired
	private CouponBatchDAO couponBatchDAO;

	public List<CouponPromotionVO> findAllCouponBatch() {
		try {
			List<CouponPromotionVO> list = couponBatchDAO.findAllCouponBatch();
			return list;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<CouponBatchVO> findCouponBatchByPagination(
			CouponBatchDTO couponBatchDTO) {
		Long count = couponBatchDAO.findCouponBatchCount(couponBatchDTO);
		couponBatchDTO.doPage(count, couponBatchDTO.getPageNo(),
				couponBatchDTO.getPageSize());
		List<CouponBatchVO> list = couponBatchDAO
				.findCouponBatchByPagination(couponBatchDTO);
		return list;
	}

	@Override
	public void addCouponBatch(CouponBatchDTO couponBatchDTO) {
		couponBatchDAO.insertCouponBatch(couponBatchDTO);
		if (couponBatchDTO.getType() != 1) {
			if (couponBatchDTO.getCouponCount() != null
					&& couponBatchDTO.getCouponCount() > 0) {
				for (int i = 0; i < couponBatchDTO.getCouponCount(); i++) {
					CouponDTO couponDTO = new CouponDTO();
					couponDTO.setBatchID(couponBatchDTO.getMainID());
					couponDTO.setPassword(StringUtil.randomSex());
					couponDTO.setAmount(couponBatchDTO.getAmount());
					couponDTO.setStatus(0);
					couponDTO.setFromDate(couponBatchDTO.getUseFromDate());
					couponDTO.setEndDate(couponBatchDTO.getUseEndDate());
					couponDTO.setMainID(UUIDUtil.getUUID());
					couponDTO.setCreator(couponBatchDTO.getCreator());
					couponBatchDAO.insertCoupon(couponDTO);
				}
			}
		}
	}

	@Override
	public CouponBatchVO findCouponBatchByMainId(String mainID) {
		return couponBatchDAO.findCouponBatchByMainId(mainID);
	}

	@Override
	public void updateCouponBatch(CouponBatchDTO couponBatchDTO) {
		couponBatchDAO.updateCouponBatch(couponBatchDTO);
	}

	@Override
	public Boolean deleteCouponBatch(String mainIds) {
		try {
			String ids[] = mainIds.split(",");
			for (String mainId : ids) {
				couponBatchDAO.deleteCouponBatch(mainId);
				couponBatchDAO.deleteCoupon(mainId);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CouponVO> findCouponByBatchID(CouponDTO couponDTO) {
		Long count = couponBatchDAO.findCouponCount(couponDTO);
		couponDTO.doPage(count, couponDTO.getPageNo(), couponDTO.getPageSize());
		List<CouponVO> list = couponBatchDAO.findCouponByBatchID(couponDTO);
		return list;
	}

	@Override
	public void exportCoupon(HttpServletResponse response, String batchID) {
		try {
			List<CouponVO> list = couponBatchDAO
					.findCouponByCouponBatchID(batchID);
			HSSFWorkbook exportBook = this.exportCoupon(list);
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-Disposition", "Attachment;filename= "
					+ new String(("优惠券明细信息.xls").getBytes("gb2312"), "ISO8859_1"));
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

	public HSSFWorkbook exportCoupon(List<CouponVO> list) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("优惠券明细信息");
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
		cell.setCellValue("劵编号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("金额");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("使用有效期");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("状态");

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CouponVO couponVO = list.get(i);
				HSSFRow content = sheet.createRow(i + 1);
				cell = content.createCell(0);
				cell.setCellValue(couponVO.getId());
				cell = content.createCell(1);
				cell.setCellValue(couponVO.getAmount());
				cell = content.createCell(2);
				cell.setCellValue(DateUtil.formatDate(couponVO.getFromDate(),
						"yyyy-MM-dd HH:mm:ss")
						+ "~"
						+ DateUtil.formatDate(couponVO.getEndDate(),
								"yyyy-MM-dd HH:mm:ss"));
				cell = content.createCell(3);
				if (couponVO.getStatus() == 0) {
					cell.setCellValue("未发放");
				} else if (couponVO.getStatus() == 1) {
					cell.setCellValue("已发放");
				} else if (couponVO.getStatus() == 2) {
					cell.setCellValue("已使用");
				} else if (couponVO.getStatus() == 3) {
					cell.setCellValue("中止");
				} else if (couponVO.getStatus() == 4) {
					cell.setCellValue("过期");
				}
			}
		}
		return workbook;
	}

	@Override
	public Boolean changeCouponStatus(String mainID, Integer status) {
		try {
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setMainID(mainID);
			couponDTO.setStatus(status);
			couponBatchDAO.updateCouponStatus(couponDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean changeCouponBatchStatus(String mainID, Integer status) {
		try {
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setBatchID(mainID);
			couponDTO.setStatus(status);
			couponBatchDAO.updateCouponBatchStatus(couponDTO);
			CouponBatchDTO couponBatchDTO=new CouponBatchDTO();
			couponBatchDTO.setMainID(mainID);
			if(status==3){
				couponBatchDTO.setStatus(2);
			}else{
				couponBatchDTO.setStatus(1);
			}
			couponBatchDAO.updateCouponBatchSta(couponBatchDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
