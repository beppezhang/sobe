package com.kpluswebup.web.supplier.service.impl;

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

import com.kpluswebup.web.domain.SupplierCategoryDTO;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.supplier.dao.SupplierDAO;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SupplierCategoryVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.Md5Algorithm;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDAO supplierDAO;
	

    @Override
    public List<SupplierVO> findSupplier(SupplierDTO supplierDTO) {
        notNull(supplierDTO, "supplierDTO is null");
        Long count = supplierDAO.findSupplierCount(supplierDTO);
        supplierDTO.doPage(count, supplierDTO.getPageNo(), supplierDTO.getPageSize());
        return supplierDAO.supplierList(supplierDTO);
    }

    @Override
    public SupplierVO findSupplierByID(String mainID) {
        notNull(mainID, "mainID is null");
        return supplierDAO.findSupplierByID(mainID);
    }

    @Override
    public void addSupplier(SupplierDTO SupplierDTO) {
        supplierDAO.addSupplier(SupplierDTO);

    }

    @Override
    public void editSupplier(SupplierDTO SupplierDTO) {
        supplierDAO.editSupplier(SupplierDTO);

    }

    @Override
    public Boolean deleteSupplierById(String mainID) {
        return supplierDAO.deleteSupplierById(mainID);
    }

    @Override
    public void exportSupplier(HttpServletResponse response, SupplierDTO supplierDTO) {
        try {
            List<SupplierVO> list = supplierDAO.supplierList(supplierDTO);
            HSSFWorkbook exportBook = this.exportSuppliers(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("供应商信息.xls").getBytes("gb2312"), "ISO8859_1"));
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

    public HSSFWorkbook exportSuppliers(List<SupplierVO> list) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("供应商信息");
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

	@Override
	public SupplierVO findSupplierByUserName(String username) {
		return supplierDAO.findSupplierByUserName(username);
	}
	
	@Override
	public SupplierVO findSupplierByCompanyName(String companyname) {
		return supplierDAO.findSupplierByCompanyName(companyname);
	}	
	
/*	public SupplierVO findSupplierCategoryByLastloginTime( Date LastloginTime) {
		return supplierDAO.findSupplierCategoryByLastloginTime(LastloginTime);
	}*/

	@Override
	public Boolean isLogin(SupplierDTO supplier) {
		 SupplierVO supplierVO = supplierDAO.findSupplierByUserName(supplier.getUserName());
		 /*supplierVO =supplierDAO.findSupplierCategoryByLastloginTime(supplier.getLastloginTime());*/
	        if (supplierVO == null) {
	            return null;
	        }
	        if(supplierVO.getLastloginTime()==null){
	        	return true;
	        }
	        if (Md5Algorithm.getInstance().md5Digest(supplier.getPassWord().getBytes()).equals(supplierVO.getPassWord())) {
	            return true;
	        }
	        return false;
	}


	@Override
	public void updateSupplierLastLogTime(String supplierName, String ip) {
		supplierDAO.updateSupplierLastLogTime(supplierName, ip);
		
	}

	@Override
	public void updateSupplierInfo(SupplierDTO supplierDTO) {
		
		supplierDAO.updateSupplierInfo(supplierDTO);
	}

	@Override
	public void updateSupplierPwd(SupplierDTO supplierDTO) {
		supplierDAO.updateSupplierPwd(supplierDTO);
		
	}

    @Override
    public List<SupplierCategoryVO> findSupplierCategory(SupplierCategoryDTO supplierCategoryDTO) {
       List<SupplierCategoryVO> list =  supplierDAO.findSupplierCategory(supplierCategoryDTO);
       if(list!= null && list.size()>0){
            for (SupplierCategoryVO supplierCategoryVO : list) {
              List<SupplierCategoryVO> childList =  supplierDAO.findChildSupplierCategory(supplierCategoryVO.getMainID());
              supplierCategoryVO.setChildBusinClassList(childList);
            }
       }
        return list;
    }

    @Override
    public void editBusinClass(SupplierCategoryDTO supplierCategoryDTO) {
        supplierDAO.editSupplierCategory(supplierCategoryDTO);    
    }

    @Override
    public void saveBusinClass(SupplierCategoryDTO supplierCategoryDTO) {
        supplierDAO.saveBusinClass(supplierCategoryDTO);
    }

    @Override
    public SupplierCategoryVO findSupplierCategoryByMainID(SupplierCategoryDTO supplierCategoryDTO) {
        return supplierDAO.findSupplierCategoryByMainID(supplierCategoryDTO);
    }

    @Override
    public void deleteBusinClass(String mainID) {
       supplierDAO.deleteBusinClass(mainID);        
    }

	@Override
	public List<ProductVO> findSupplierMap(SupplierDTO supplierDTO) {
		return supplierDAO.findSupplierMap(supplierDTO);
	}
}
