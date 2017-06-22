package com.kpluswebup.web.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
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

import com.kpluswebup.web.domain.ItemScoreDTO;
import com.kpluswebup.web.domain.ItemScoreDetailDTO;
import com.kpluswebup.web.promotion.dao.ItemScoreDAO;
import com.kpluswebup.web.service.ItemScoreService;
import com.kpluswebup.web.vo.ItemScoreDetailVO;
import com.kpluswebup.web.vo.ItemScoreVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.UUIDUtil;
import com.swetake.util.Qrcode;

@Service
public class ItemScoreServiceImpl implements ItemScoreService {

    @Autowired
    private ItemScoreDAO itemScoreDAO;

    @Override
    public List<ItemScoreVO> findItemScoreByPagination(ItemScoreDTO itemScoreDTO) {
        Long count = itemScoreDAO.findItemScoreCount(itemScoreDTO);
        itemScoreDTO.doPage(count, itemScoreDTO.getPageNo(), itemScoreDTO.getPageSize());
        List<ItemScoreVO> list = itemScoreDAO.findItemScoreByPagination(itemScoreDTO);
        return list;
    }

    @Override
    public void addItemScore(ItemScoreDTO itemScoreDTO) {
        itemScoreDAO.addItemScore(itemScoreDTO);
        if (itemScoreDTO.getNum() != null && itemScoreDTO.getNum() > 0) {
            Integer num = itemScoreDTO.getNum();
            for (int i = 0; i < num; i++) {
                ItemScoreDetailDTO itemScoreDetailDTO = new ItemScoreDetailDTO();
                itemScoreDetailDTO.setItemScoreID(itemScoreDTO.getMainID());
                itemScoreDetailDTO.setCreator(itemScoreDTO.getCreator());
                itemScoreDetailDTO.setMainID(UUIDUtil.getUUID());
                itemScoreDetailDTO.setQrCodeUrl(this.qrCodeItemScore(itemScoreDetailDTO.getMainID()));
                itemScoreDAO.addItemScoreDetail(itemScoreDetailDTO);
            }
        }
    }

    @Override
    public void updateItemScore(ItemScoreDTO itemScoreDTO) {
        itemScoreDAO.updateItemScore(itemScoreDTO);

    }

    @Override
    public Boolean deleteItemScore(String mainIds) {
        try {
            String[] ids = mainIds.split(",");
            for (String mainID : ids) {
                itemScoreDAO.deleteItemScore(mainID);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemScoreVO findItemScoreByMainID(String mainID) {
        return itemScoreDAO.findItemScoreByMainID(mainID);
    }

    @Override
    public List<ItemScoreDetailVO> findItemScoreDetailByPatination(ItemScoreDetailDTO itemScoreDetailDTO) {
        Long count = itemScoreDAO.findItemScoreDetailCount(itemScoreDetailDTO);
        itemScoreDetailDTO.doPage(count, itemScoreDetailDTO.getPageNo(), itemScoreDetailDTO.getPageSize());
        List<ItemScoreDetailVO> list = itemScoreDAO.findItemScoreDetailByPatination(itemScoreDetailDTO);
        return list;
    }

    @SuppressWarnings("static-access")
    public String qrCodeItemScore(String mainID) {
        try {
            String dir = "/file/" + DateUtil.getDateAgo(0);
            this.getResponse().setContentType("text/html; charset=UTF-8");
            String uuid = UUIDUtil.getUUID();
            String realPath = this.getRequest().getSession().getServletContext().getRealPath(dir);
            String imgPath = dir + "/" + uuid + ".png";
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            ClassPathResource resource = new ClassPathResource("server.properties");
            Properties prop = new Properties();
            prop.load(new FileReader(resource.getFile()));
            String domain = prop.getProperty("qrurl");
            this.encoderQRCode(domain + "/weixin/getPoints.htm?mainID=" + mainID, realPath + "/" + uuid + ".png");
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    public void exportItemScoreDetail(HttpServletResponse response, String itemScoreID) {
        try {
            List<ItemScoreDetailVO> list = itemScoreDAO.findItemScoreDetailByItemScoreID(itemScoreID);
            HSSFWorkbook exportBook = this.exportItemScoreDetail(list);
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setHeader("Content-Disposition",
                               "Attachment;filename= " + new String(("商品积分明细信息.xls").getBytes("gb2312"), "ISO8859_1"));
            OutputStream out = this.getResponse().getOutputStream();
            exportBook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HSSFWorkbook exportItemScoreDetail(List<ItemScoreDetailVO> list) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("商品积分明细信息");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 18 * 300);
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
        cell.setCellValue("序号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("二维码");
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ItemScoreDetailVO itemScoreDetailVO = (ItemScoreDetailVO) list.get(i);
                HSSFRow content = sheet.createRow(i + 1);
                content.setHeight((short) 1500);
                cell = content.createCell(0);
                cell.setCellValue(itemScoreDetailVO.getId());
                if (itemScoreDetailVO.getQrCodeUrl() != null) {
                    String url = this.getRequest().getSession().getServletContext().getRealPath("/")
                                 + itemScoreDetailVO.getQrCodeUrl();
                    BufferedImage bufferedImage = ImageIO.read(new File(url));
                    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "png", byteArrayOut);
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1 + i, (short) 2, 2 + i);
                    anchor.setAnchorType(0);
                    // 插入图片
                    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(),
                                                                        HSSFWorkbook.PICTURE_TYPE_JPEG));
                    cell = content.createCell(1);
                    cell.setCellStyle(style);
                    // cell.setCellValue(itemScoreDetailVO.getQrCodeUrl());
                }
            }
        }
        return workbook;
    }

    @Override
    public ItemScoreDetailVO findItemScoreDetailByMainID(ItemScoreDetailDTO itemScoreDetailDTO) {
        return itemScoreDAO.findItemScoreDetailByMainID(itemScoreDetailDTO);
    }

    @Override
    public void updateItemScoreDetail(ItemScoreDetailDTO itemScoreDetailDTO) {
        itemScoreDAO.updateItemScoreDetail(itemScoreDetailDTO);
    }

}
