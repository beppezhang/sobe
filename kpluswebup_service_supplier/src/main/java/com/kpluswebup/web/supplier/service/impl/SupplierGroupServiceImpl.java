package com.kpluswebup.web.supplier.service.impl;

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

import com.kpluswebup.web.domain.SupplierGroupDTO;
import com.kpluswebup.web.domain.SupplierGroupMemberDTO;
import com.kpluswebup.web.domain.SupplierGroupSetDTO;
import com.kpluswebup.web.supplier.dao.SupplierGroupDAO;
import com.kpluswebup.web.supplier.service.SupplierGroupService;
import com.kpluswebup.web.vo.SupplierGroupMemberVO;
import com.kpluswebup.web.vo.SupplierGroupSetVO;
import com.kpluswebup.web.vo.SupplierGroupVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.StringUtil;
@Service
public class SupplierGroupServiceImpl implements SupplierGroupService {

    private static final Logger LOGGER = LogManager.getLogger(SupplierGroupServiceImpl.class);
	
	@Autowired
	private SupplierGroupDAO supplierGroupDAO;
	
	@Override
	public List<SupplierGroupVO> findAllSupplierGroup() {
		try {
            return supplierGroupDAO.findAllSupplierGroup();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
	}

	@Override
	public List<SupplierGroupVO> findSupplierGroupByPagination(SupplierGroupDTO supplierGroupDTO) {
		 notNull(supplierGroupDTO, "supplierGroupDTO is null");
	        Long count = supplierGroupDAO.findSupplierGroupCount(supplierGroupDTO);
	        supplierGroupDTO.doPage(count, supplierGroupDTO.getPageNo(), supplierGroupDTO.getPageSize());
	        List<SupplierGroupVO> list = supplierGroupDAO.findSupplierGroupByPagination(supplierGroupDTO);
	        return list;
	}
	
	@Override
	public void addSupplierGroup(SupplierGroupDTO supplierGroupDTO) {
		supplierGroupDAO.insertSupplierGroup(supplierGroupDTO);
		
	}

	@Override
	public SupplierGroupVO findSupplierGroupByMainID(String mainID) {
		 notNull(mainID, "mainID is null");
	        return supplierGroupDAO.findSupplierGroupByMainID(mainID);
	}

	@Override
	public void editSupplierGroup(SupplierGroupDTO supplierGroupDTO) {
		supplierGroupDAO.updateSupplierGroupByMainID(supplierGroupDTO);
		
	}

	@Override
	public Boolean deleteSupplierGroupByMainID(String mainIds) {
		notNull(mainIds, "mainIds is null");
        try {
            String[] ids = mainIds.split(",");
            for (String mainId : ids) {
            	supplierGroupDAO.deleteSupplierGroupByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
	}

	@Override
	public List<SupplierGroupSetVO> findSupplierGroupSetByGroupId(String groupId) {
		return supplierGroupDAO.findSupplierGroupSetByGroupId(groupId);
	}

	@Override
	public Boolean deleteGroupSetByID(Long id) {
		 notNull(id, "id is null");
	        try {
	        	supplierGroupDAO.deleteGroupSetByID(id);
	            return true;
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage(), e);
	        }
          return false;
	}

	@Override
	public void addGroupSet(SupplierGroupSetDTO supplierGroupSetDTO) {
		supplierGroupDAO.insertGroupSet(supplierGroupSetDTO);
	}

	@Override
	public void editGroupSet(SupplierGroupSetDTO supplierGroupSetDTO) {
		supplierGroupDAO.updateGroupSetByID(supplierGroupSetDTO);
		
	}

	@Override
	public List<SupplierVO> findSupplierByGroupSet(SupplierVO supplierVO) {
		return supplierGroupDAO.findSupplierByGroupSet(supplierVO);
	}

	@Override
	public void addSupplierGroupMember(SupplierGroupMemberDTO supplierGroupMemberDTO) {
		supplierGroupDAO.insertSupplierGroupMember(supplierGroupMemberDTO);
		
	}

	@Override
	public List<SupplierGroupMemberVO> findSupplierGroupMemberByGroupID(String groupId) {
		 return supplierGroupDAO.findSupplierGroupMemberByGroupID(groupId);
	}

	@Override
	public List<SupplierGroupMemberVO> findSupplierGroupMemberByPagination(SupplierGroupMemberDTO supplierGroupMemberDTO) {
		notNull(supplierGroupMemberDTO, "supplierGroupMemberDTO is null");
        Long count = supplierGroupDAO.findSupplierGroupMemberCount(supplierGroupMemberDTO);
        supplierGroupMemberDTO.doPage(count, supplierGroupMemberDTO.getPageNo(), supplierGroupMemberDTO.getPageSize());
        List<SupplierGroupMemberVO> list = supplierGroupDAO.findSupplierGroupMemberByPagination(supplierGroupMemberDTO);
        return list;
	}

	@Override
	public Boolean deleteSupplierGroupMemberByGroupID(String groupId) {
		notNull(groupId, "groupId is null");
        try {
        	supplierGroupDAO.deleteSupplierGroupMemberByGroupID(groupId);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
	}

	@Override
	public List<SupplierGroupVO> findSupplierGroupByName(SupplierGroupDTO supplierGroupDTO) {
		Long count = supplierGroupDAO.findSupplierGroupByNameCount(supplierGroupDTO);
		supplierGroupDTO.doPage(count, supplierGroupDTO.getPageNo(), supplierGroupDTO.getPageSize());
        List<SupplierGroupVO> list = supplierGroupDAO.findSupplierGroupByName(supplierGroupDTO);
        return list;
	}

	@Override
	public List<SupplierGroupMemberVO> findSupplierGroupMember() {
		return supplierGroupDAO.findSupplierGroupMember();
	}

	@Override
	public void exportSupplierGroupMember(HttpServletResponse response,String mainIds, String supplierID) {
		try {
            String ids[] = mainIds.split(",");
            List<SupplierVO> list = new ArrayList<SupplierVO>();
            SupplierGroupMemberDTO supplierGroupMemberDTO = new SupplierGroupMemberDTO();
            if (StringUtil.isNotEmpty(supplierID)) {
            	supplierGroupMemberDTO.setSupplierID(supplierID);
            }
            for (String mainId : ids) {
            	supplierGroupMemberDTO.setSupplierID(mainId);
                //SupplierVO supplierVO = supplierGroupDAO.findGroupMemberByGroupIDSupplierID(supplierGroupMemberDTO);
                //list.add(supplierVO);
            }
            HSSFWorkbook exportBook = this.exportSupplierGroupMemberCenter(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("组内供应商信息.xls").getBytes("gb2312"), "ISO8859_1"));
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

	public HSSFWorkbook exportSupplierGroupMemberCenter(List<SupplierVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("组内供应商信息");
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
        cell.setCellValue("手机号");
        
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("公司名称");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("联系人姓名");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("开户行");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("银行账号");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("取件地址");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("用户名");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	SupplierVO supplierVO = list.get(i);
            	HSSFRow content = sheet.createRow(i + 1);
                cell = content.createCell(0);
                cell.setCellValue(supplierVO.getMobile());
                cell = content.createCell(1);
                cell.setCellValue(supplierVO.getCompanyName());
                cell = content.createCell(2);
                cell.setCellValue(supplierVO.getLinkMan());
                cell = content.createCell(3);
                cell.setCellValue(supplierVO.getOpeningBank());
                cell = content.createCell(4);
                cell.setCellValue(supplierVO.getBankAccount());
                cell = content.createCell(5);
                cell.setCellValue(supplierVO.getAddress());
                cell = content.createCell(6);
                cell.setCellValue(supplierVO.getUserName());
            }
        }
        return workbook;
    }
}
