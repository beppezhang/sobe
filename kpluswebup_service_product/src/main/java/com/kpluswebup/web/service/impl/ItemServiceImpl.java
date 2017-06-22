package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductPictureDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.product.dao.ProductPictureDAO;
import com.kpluswebup.web.product.dao.ProductTypeDAO;
import com.kpluswebup.web.promotion.dao.PreSaleDAO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.supplier.dao.SupplierDAO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

/**
 * 
 * @author zhoulei
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	/** 商品DAO */
	@Autowired
	public ItemDAO itemDAO;
	@Autowired
	/**产品图片对应关系DAO*/
	private ProductPictureDAO productPictureDAO;
	@Autowired
	/**预售DAO*/
	private PreSaleDAO preSaleDAO;
	@Autowired
	/**系统配置DAO*/
	private SystemCodeDAO systemCodeDAO;
	@Autowired
	/**子订单DAO*/
	private SalesOrderLineDAO salesOrderLineDAO;
	@Autowired
	/**产品DAO*/
	private ProductDAO productDAO;
	@Autowired
	/**产品类型DAO*/
	private ProductTypeDAO productTypeDAO;
	@Autowired
	/**供应商DAO*/
	private SupplierDAO supplierDAO;
	/**车型*/
	@Autowired
	private VehicleTypeService vehicleTypeService;
	/**车系*/
	@Autowired
	private ProductCategoryService productCategoryService;
	/**订单*/
	@Autowired
	private SalesOrderDAO salesOrderDAO;
	
	/**
	 * @author zhoulei
	 * @Description 新增商品
	 * @param productID
	 *            产品id
	 * @param name
	 *            商品名称
	 * @param picURL
	 *            商品图片
	 * @param standrardPrice
	 *            市场价
	 * @param stock
	 *            库存
	 * @param salesPrice
	 *            销售价
	 * @param weight
	 *            重量
	 * 
	 */
	public void insertItem(String productID, String name, String oldNumber,
			String picURL, String costPrice, String standrardPrice,
			String distributionPrice, String retailPrice, String salesPrice,
			String purchasePrice, String minimumQty, String weight,
			String cubage, String quantity, String stock, String saftyStock,
			String sortOrder, String description) {

	}

	/**
	 * @author zhoulei
	 * @Description editItem编辑商品
	 * @param productID
	 *            产品id
	 * @param name
	 *            商品名称
	 * @param picURL
	 *            商品图片
	 * @param standrardPrice
	 *            市场价
	 * @param stock
	 *            库存
	 * @param salesPrice
	 *            销售价
	 * @param weight
	 *            重量
	 * 
	 */
	public void editItem(String mianID, String productID, String name,
			String oldNumber, String picURL, String costPrice,
			String standrardPrice, String distributionPrice,
			String retailPrice, String salesPrice, String purchasePrice,
			String minimumQty, String weight, String cubage, String quantity,
			String stock, String saftyStock, String sortOrder,
			String description) {

	}

	/**
	 * @author zhoulei
	 * @Description 通过产品id查询改产品下所有商品
	 * @return List<ItemVO> 商品集合
	 */
	public List<ItemVO> findItemByProductID(String productID) {
		notNull(productID, "productID is null");
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductID(productID);
		List<ItemVO> list = itemDAO.findItem(itemDTO);
		for (ItemVO itemVO : list) {
			ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
			itemDetailDTO.setItemID(itemVO.getMainID());
			itemVO.setItemDetailList(itemDAO.findItemDetail(itemDetailDTO));
		}
		return list;
	}

	/**
	 * @author zhoulei
	 * @Description 通过商品DTO获取商品对象
	 * @return ItemVO 商品对象
	 */
	public ItemVO seeItem(ItemDTO itemDTO) {
		notNull(itemDTO, "itemDTO is null");
		ItemVO itemVO = new ItemVO();
		List<ItemVO> list = itemDAO.findItem(itemDTO);
		if (list != null && list.size() > 0) {
			itemVO = list.get(0);
			return itemVO;
		}
		return null;
	}

	/**
	 * @author zhoulei
	 * @Description 通过商品ID获取商品对象
	 * @return ItemVO 商品对象
	 */
	public ItemVO findItemByMainID(String mainID) {
		notNull(mainID, "mainID is null");
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setMainID(mainID);
		List<ItemVO> list = itemDAO.findItem(itemDTO);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @author zhoulei
	 * @Description 通过商品ID删除商品（标识删除）
	 */
	public void deleteItemByMainID(String mainID) {
		notNull(mainID, "mainID is null");
		itemDAO.deleteItemByMainID(mainID);
		itemDAO.deleteItemDetailByItemID(mainID);
		productPictureDAO.deleteProductPictureByItemID(mainID);

	}

	/**
	 * 根据产品id查找选中的商品规格
	 * 
	 * @date 2015年7月12日
	 * @author zhoulei
	 * @param itemID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<ItemDetailVO> findItemDetailSByItemID(String productID) {
		notNull(productID, "productID is null");
		List<ItemVO> itemSVO = itemDAO.findItemByProductMainID(productID);
		List<ItemDetailVO> list = new ArrayList<ItemDetailVO>();
		for (ItemVO itemVO : itemSVO) {
			List<ItemDetailVO> list1 = itemDAO.findItemDetailSByItemID(itemVO
					.getMainID());
			for (ItemDetailVO itemDetailVO : list1) {
				list.add(itemDetailVO);
			}
		}
		return list;
	}

	/**
	 * 根据商品id查找选中的商品规格
	 * 
	 * @date 2015年7月12日
	 * @author zhoulei
	 * @param itemID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<ItemDetailVO> findItemDetailSByItemID1(String productID) {
		notNull(productID, "productID is null");
		List<ItemVO> itemSVO = itemDAO.findItemByProductMainID(productID);
		List<ItemDetailVO> list = new ArrayList<ItemDetailVO>();
		for (ItemVO itemVO : itemSVO) {
			List<ItemDetailVO> list1 = itemDAO.findItemDetailSByItemID1(itemVO
					.getMainID());
			for (ItemDetailVO itemDetailVO : list1) {
				list.add(itemDetailVO);
			}
		}
		return list;
	}

	/**
	 * 根据商品id查找选中的商品详情
	 * 
	 * @date 2015年7月12日
	 * @author zhoulei
	 * @param itemID
	 * @return 商品详情集合
	 * @since JDK 1.6
	 * @Description
	 */
	@Override
	public List<ItemDetailVO> findItemDetailByItemID(String itemID) {
		notNull(itemID, "itemID is null");
		return itemDAO.findItemDetailByItemID(itemID);
	}

	/**
	 * @Description 批量新增或更新商品
	 * @date 2015年7月12日
	 * @author zhoulei
	 * @param productID
	 *            产品id
	 * @param productTypeMainID
	 *            产品所属类型
	 * @param picsURL
	 *            商品图片集合
	 * @param currentOperator
	 *            操作者
	 * @param items
	 *            待发布/待更新的商品集合
	 * @return
	 * @since JDK 1.6
	 */
	public void batchAddOrEditItem(String productID, String productTypeMainID,
			String[] items, String currentOperator, String[] picsURL) {
		notNull(productID, "productID is null");
		notNull(productTypeMainID, "productTypeMainID is null");
		itemDAO.deleteItemByProductID(productID);
		// 删除图片信息
		productPictureDAO.deleteProductPictureByProductID(productID);
		// notNull(items, "items is null");
		if (null != items) {
			for (int i = 0; i < items.length; i++) {
				String str = items[i];
				if (StringUtil.isNotEmpty(str)) {
					str = str.replaceAll("\\|", ",");// split竖线无法解析
					String[] item = str.split(",");// 商品编号,销售规格,商品名称,图片,价格(元),库存,起订数,体积,重量(kg),线下货号，排序
					String mainID = item[7]; // item[3];
					if (StringUtil.isEmpty(mainID)) {
						CodeConfigVO codeConfigVO = systemCodeDAO
								.findCodeConfigByID(Constant.ITEMID);
						if (codeConfigVO != null) {
							mainID = codeConfigVO.getCodeEx()
									+ GenerationNum.getRandomNumber(9);
						}
					}
					String name = item[5];// item[6];
					String stock = item[3];
					String propTypeID = item[1];// 组合
					if (StringUtil.isEmpty(stock)) {
						stock = "0";
					}
					if (StringUtil.isNotEmpty(name)
							&& StringUtil.isNotEmpty(stock)) {
						if (StringUtil.isEmpty(stock)) {
							stock = "0";
						}
						String standrardPrice = item[6];
						String salesPrice = item[2];
						/*
						 * String picURL = item[3]; String purchasePrice =
						 * item[5]; String minimumQty = item[8]; String cubage =
						 * item[9]; String weight = item[10]; String oldNumber =
						 * item[11]; String sortOrder = item[12];
						 */
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setSkuCode(item[4]);
						itemDTO.setMainID(mainID);// 商品编号
						itemDTO.setName(name);// 商品名称
						// itemDTO.setPicURL(picURL);// 图片
						if (StringUtil.isNumberic(standrardPrice)) {
							itemDTO.setStandrardPrice(Double
									.parseDouble(standrardPrice));// 市场价(元)
						}
						/*
						 * if (StringUtil.isNumberic(purchasePrice)) {
						 * itemDTO.setPurchasePrice
						 * (Double.parseDouble(purchasePrice));// 采购价(元) } if
						 * (StringUtil.isNumberic(minimumQty)) {
						 * itemDTO.setMinimumQty
						 * (Integer.parseInt(minimumQty));// 起订数 }
						 * 
						 * if (StringUtil.isNumberic(cubage)) {
						 * itemDTO.setCubage(Integer.parseInt(cubage));// 体积 }
						 * if (StringUtil.isNumberic(weight)) {
						 * itemDTO.setWeight(Double.parseDouble(weight));//
						 * 重量(kg) } if (StringUtil.isNumberic(oldNumber)) {
						 * itemDTO.setOldNumber(oldNumber); } if
						 * (StringUtil.isNumberic(sortOrder)) {
						 * itemDTO.setSortOrder(Integer.parseInt(sortOrder)); }
						 */
						if (StringUtil.isNumberic(salesPrice)) {
							itemDTO.setSalesPrice(Double
									.parseDouble(salesPrice));// 价格(元)
						}
						if (StringUtil.isNumberic(stock)) {
							itemDTO.setStock(Integer.parseInt(stock));// 库存
						}
						String itemMainPicurl = null;
						for (String newPicsURL : picsURL) {
							if (StringUtil.isNotEmpty(newPicsURL)) {
								itemMainPicurl = newPicsURL;
								break;
							}
						}
						itemDTO.setPicURL(itemMainPicurl);
						itemDTO.setProductID(productID);
						ItemVO in = itemDAO.findItemById(mainID);
						itemDAO.deleteItemDetailByItemID(mainID);
						if (null == in) {
							String[] itemDetail = item[0].split("~");
							for (int j = 0; j < itemDetail.length; j++) {
								if (itemDetail[j] != null
										&& itemDetail[j].length() > 0) {
									ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
									itemDetailDTO.setItemID(mainID);
									itemDetailDTO.setItemPropID(itemDetail[j]
											.split(":")[1]);
									itemDetailDTO
											.setItemPropValue(itemDetail[j]
													.split(":")[0]);
									itemDetailDTO.setpTypeIPropID(itemDetail[j]
											.split(":")[2]);
									itemDetailDTO.setPropTypeID(propTypeID);
									itemDetailDTO.setCreator(currentOperator);
									itemDAO.addItemDetail(itemDetailDTO);
								}
							}
						}
						// 删除图片信息
						// productPictureDAO.deleteProductPictureByProductID(productID);
						for (int j = 0; j < picsURL.length; j++) {
							ProductPictureDTO productPictureDTO = new ProductPictureDTO();
							productPictureDTO.setProductID(productID);
							productPictureDTO.setPicURL(picsURL[j]);
							productPictureDTO.setItemID(mainID);
							productPictureDTO.setName(name);
							productPictureDTO.setCreator(currentOperator);
							productPictureDAO
									.insertProductPicture(productPictureDTO);
						}
						ItemDTO temp = new ItemDTO();
						temp.setMainID(mainID);
						List<ItemVO> list = itemDAO.findItem(temp);
						if (list != null && list.size() > 0) {
							itemDTO.setModifier(currentOperator);
							itemDAO.updateItemByMainID(itemDTO);
						} else {
							itemDTO.setCreator(currentOperator);
							itemDAO.insertItem(itemDTO);
						}
					}
				}

			}
		}
	}

	/**
	 * @author zhoulei
	 * @Description 根据名称 商品/产品 搜索商品
	 * @param name
	 *            商品名称
	 * @param type
	 *            0 产品 1 商品
	 * @param catalog
	 * @return 返回商品对象集合
	 */
	public List<ItemVO> searchItem(String name, String type, String catalog) {
		if ("0".equals(type)) { // 按照产品模糊搜索
			ProductDTO productDTO = new ProductDTO();
			productDTO.setName(name);
			if (StringUtil.isNotEmpty(catalog)) {
				productDTO.setCatalog(Integer.valueOf(catalog));
			}
			return itemDAO.searchItemByProduct(productDTO);
		}
		if ("1".equals(type)) {// 按照商品模糊搜索
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setName(name);
			if (StringUtil.isNotEmpty(catalog)) {
				itemDTO.setCatalog(Integer.valueOf(catalog));
			}
			itemDTO.setStatus(1);
			return itemDAO.findItem(itemDTO);
		}

		return null;
	}

	@Override
	public List<ItemDetailVO> findItemProps(String itemID) {
		return itemDAO.findItemProps(itemID);
	}

	@Override
	public List<ItemDetailVO> findproductProps(String productID) {
		return itemDAO.findproductProps(productID);
	}

	@Override
	public List<ItemVO> finditems(String productID) {
		return itemDAO.finditems(productID);
	}

	@Override
	public List<ItemVO> finditemsByProductID(String productID) {
		return itemDAO.finditemsByProductID(productID);
	}

	@Override
	public ItemVO findItemDetailByProductID(ItemDTO ItemDto) {
		return itemDAO.findItemDetailByProductID(ItemDto);
	}

	@Override
	public List<ItemVO> finItemsByItemDto(ItemDTO ItemDto) {
		Long count = itemDAO.finItemsByItemDtoCount(ItemDto);
		if (count == null) {
			return null;
		}
		ItemDto.doPage(count, ItemDto.getPageNo(), ItemDto.getPageSize());
		return itemDAO.finItemsByItemDto(ItemDto);
	}

	@Override
	public ItemVO findItemByValues(ItemDetailDTO itemDetailDTO) {
		return itemDAO.findItemByValues(itemDetailDTO);
	}

	@Override
	public ItemVO findItemById(String mainID) {
		return itemDAO.findItemById(mainID);
	}

	@Override
	public ItemVO findItemByIds(String mainID) {
		return itemDAO.findItemByIds(mainID);
	}

	@Override
	public List<ItemVO> finditemsByProductIDs(String brandID) {
		return itemDAO.finditemsByProductIDs(brandID);
	}

	@Override
	public List<PreSaleVO> findPreSaleList(PreSaleDTO preSaleDTO) {
		Long count = preSaleDAO.findPreSaleCount(preSaleDTO);
		// Long count = productDAO.searchProducItemCatCount(productDTO);
		if (count == null) {
			return null;
		}
		preSaleDTO.doPage(count, preSaleDTO.getPageNo(),
				preSaleDTO.getPageSize());
		return itemDAO.findPreSaleList(preSaleDTO);
	}

	@Override
	public List<SalesOrderLineVO> findOrderItemByOrderID(String orderID) {
		return itemDAO.findOrderItemByOrderID(orderID);
	}

	@Override
	public List<ItemVO> findItemByPagination(ItemDTO itemDTO) {
		Long count = itemDAO.findItemCount(itemDTO);
		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
		List<ItemVO> list = itemDAO.findItemByPagination(itemDTO);
		if (list != null && list.size() > 0) {
			for (ItemVO itemVO : list) {
				if (itemVO.getSupplierID() != null) {
					SupplierVO supplierVO = supplierDAO.findSupplierByID(itemVO
							.getSupplierID());
					itemVO.setSupplierName(supplierVO.getCompanyName());
				}
				if (itemVO.getSalesPrice() != null
						&& itemVO.getSalesVolume() != null)
					itemVO.setAllsalesPrice(itemVO.getSalesPrice()
							* itemVO.getSalesVolume());
				ProductVO productVO = productDAO.findProductByMainID(itemVO
						.getProductID());
				if (productVO != null) {
					ProductTypeVO productTypeVO = productTypeDAO
							.findProductTypeByMainID(productVO
									.getProductTypeID());
					if (productTypeVO != null) {
						itemVO.setProductCat(productTypeVO.getName());
					}
				}
			}
		}
		return list;
	}

	@Override
	/**
	 * 
	 * TODO 通过商品 查询所属分类（可选）.
	 * @see com.kpluswebup.web.service.ItemService#findItemByCategoryId(com.kpluswebup.web.domain.ItemDTO)
	 */
	public List<ItemVO> findItemByCategoryId(ItemDTO itemDTO) {
		long count = itemDAO.getItemCountByCategoryId(itemDTO);
		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
		return itemDAO.findItemByCategoryId(itemDTO);
	}

	@Override
	public List<ItemVO> findSupplierItemWaiting(ItemDTO itemDTO) {
		long count = itemDAO.getSupplierItemWaititngCount(itemDTO);
		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
		return itemDAO.findSupplierItemWaiting(itemDTO);
	}

	@Override
	public List<ItemVO> findSupplierItemPass(ItemDTO itemDTO) {

		long count = itemDAO.getSupplierItemPassCount(itemDTO);
		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
		return itemDAO.findSupplierItemPass(itemDTO);
	}

	@Override
	public int getSupplierItemPassCount(ItemDTO itemDTO) {

		return (int) itemDAO.getSupplierItemPassCount(itemDTO);
	}

	@Override
	/**
	 * 
	 * TODO 通过产品Id 商品名称 查询商品集合（可选）.
	 * @see com.kpluswebup.web.service.ItemService#findItemsByProductID(java.lang.String, java.lang.String)
	 */
	public List<ItemVO> findItemsByProductID(String productID, String name) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductID(productID);
		itemDTO.setName(name);
		return itemDAO.findItemsByProductID(itemDTO);
	}

	@Override
	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.kpluswebup.web.service.ItemService#findItemSupplieredByItemMainIDAndSupplierID(java.lang.String, java.lang.String)
	 */
	public ItemVO findItemSupplieredByItemMainIDAndSupplierID(
			String itemMainID, String supplierID) {

		return itemDAO.findItemSupplieredByItemMainIDAndSupplierID(itemMainID,
				supplierID);
	}

	@Override
	/**
	 * 
	 * TODO 通过商品Id查询供应商（可选）.
	 * @see com.kpluswebup.web.service.ItemService#findSupplierItemById(java.lang.String)
	 */
	public ItemVO findSupplierItemById(String mainID) {
		return itemDAO.findSupplierItemById(mainID);
	}

	@Override
	/**
	 * 
	 * TODO 导出商品（可选）.
	 * @see com.kpluswebup.web.service.ItemService#exportItem(javax.servlet.http.HttpServletResponse, com.kpluswebup.web.domain.ItemDTO)
	 */
	public void exportItem(HttpServletResponse response, ItemDTO itemDTO) {
		try {
			List<ItemVO> list = itemDAO.findAllItem(itemDTO);
			HSSFWorkbook exportBook = this.exportItemInfo(list);
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-Disposition", "Attachment;filename= "
					+ new String(("销售报表.xls").getBytes("gb2312"), "ISO8859_1"));
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
/**
 * 
 * exportItemInfo:(销售报表). <br/>
 * TODO(这里描述这个方法适用条件 – 可选).<br/>
 * @author lei.zhou@kata.com.cn
 * @param list
 * @return
 * @since JDK 1.6
 * date 2015年8月25日下午3:50:31
 */
	public HSSFWorkbook exportItemInfo(List<ItemVO> list) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("销售报表");
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
		cell.setCellValue("商品编码");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("商品名称");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("产品分类");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("销售时间");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("销售数量");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("单价");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("总价");

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ItemVO itemVO = list.get(i);
				HSSFRow content = sheet.createRow(i + 1);
				cell = content.createCell(0);
				cell.setCellValue(itemVO.getMainID());
				cell = content.createCell(1);
				cell.setCellValue(itemVO.getName());
				cell = content.createCell(2);
				cell.setCellValue(itemVO.getProductCat());
				cell = content.createCell(3);
				cell.setCellValue(DateUtil.getDateTime(itemVO.getModifyTime()));
				cell = content.createCell(4);
				cell.setCellValue(itemVO.getSalesVolume());
				cell = content.createCell(5);
				if (itemVO.getSalesPrice() != null) {
					cell.setCellValue(itemVO.getSalesPrice());
				} else {
					cell.setCellValue("");
				}
				cell = content.createCell(6);
				if (itemVO.getSalesVolume() != null
						&& itemVO.getSalesPrice() != null) {
					cell.setCellValue(itemVO.getSalesPrice()
							* itemVO.getSalesVolume());
				}
			}
		}
		return workbook;
	}

	@Override
	public void exportPurchaseItem(HttpServletResponse response, ItemDTO itemDTO) {
		try {
			List<ItemVO> list = itemDAO.findAllItem(itemDTO);
			/*
			 * if(StringUtil.isNotEmpty(mainIds)){ String []
			 * ids=mainIds.split(","); for(int i=0;i<ids.length;i++){ ItemVO
			 * itemVO=itemDAO.findItemById(ids[i]); if (itemVO.getSalesPrice()
			 * != null && itemVO.getSalesVolume() != null)
			 * itemVO.setAllsalesPrice(itemVO.getSalesPrice()
			 * itemVO.getSalesVolume()); ProductVO productVO =
			 * productDAO.findProductByMainID(itemVO.getProductID()); if
			 * (productVO != null) { ProductTypeVO productTypeVO =
			 * productTypeDAO
			 * .findProductTypeByMainID(productVO.getProductTypeID()); if
			 * (productTypeVO != null) {
			 * itemVO.setProductCat(productTypeVO.getName()); } }
			 * list.add(itemVO); } }
			 */
			HSSFWorkbook exportBook = this.exportPurchaseItemInfo(list);
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-Disposition", "Attachment;filename= "
					+ new String(("采购报表.xls").getBytes("gb2312"), "ISO8859_1"));
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

	public HSSFWorkbook exportPurchaseItemInfo(List<ItemVO> list) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("采购报表");
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
		cell.setCellValue("商品编码");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("商品名称");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("商品进价");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("采购数量");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("供应时间");

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ItemVO itemVO = list.get(i);
				HSSFRow content = sheet.createRow(i + 1);
				cell = content.createCell(0);
				cell.setCellValue(itemVO.getMainID());
				cell = content.createCell(1);
				cell.setCellValue(itemVO.getName());
				cell = content.createCell(2);
				cell.setCellValue(itemVO.getPurchasePrice());
				cell = content.createCell(3);
				cell.setCellValue(itemVO.getStock());
				cell = content.createCell(4);
				cell.setCellValue(DateUtil.formatDate(itemVO.getCreateTime(),
						"yyyy-MM-dd"));
			}
		}
		return workbook;
	}

	@Override
	public List<ItemDetailVO> findItemDetailPropValue(String productID,
			String pTypeIPropID) {
		ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
		itemDetailDTO.setItemID(productID);
		itemDetailDTO.setpTypeIPropID(pTypeIPropID);
		return itemDAO.findItemDetailPropValue(itemDetailDTO);
	}

	
	/**************************************/
	/**
	 * @author lby
	 * @Description 通过产品id查询该产品下所有商品
	 * @return List<ItemVO> 商品集合
	 */
	public List<ItemVO> findItemsByProductIDTparts(ItemDTO itemDTO) {
		notNull(itemDTO, "itemDTO is null");
		itemDTO.doPage(itemDAO.findItemsCountByProductID(itemDTO), itemDTO.getPageNo(), itemDTO.getPageSize());
		List<ItemVO> list = itemDAO.findItemsByProductIDNew(itemDTO);
		return list;
	}

	
	/**
	 * @Description 新增或更新商品
	 * @date 2015年11月3日
	 * @author lby
	 * @param ...
	 * @return
	 * @since JDK 1.6
	 */
	public void addOrUpdateItem(String mainID,String productID,String supplierCategoryID, String name, String standrardPrice,String salesPrice,
    		String weight,String cubage,String description,String picURL,String supplierName,String supplierID,String currentOperator,String freightTemplateID){
		ItemDTO itemDTO = new ItemDTO();

		//如果传进来的商品mainID为空则证明是新添商品操作，这时候要构造mainID和skuCode
		if (mainID==""){
			//构造商品mainID
			mainID = UUIDUtil.getOrigUUID();
			
			//构造商品的skuCode（skuCode是商品在前端显示的一个唯一标识，所以只在商品创建之初写入）
			String skuCode = null;
			CodeConfigVO codeConfigVO = systemCodeDAO
					.findCodeConfigByID(Constant.ITEMID);
			if (codeConfigVO != null) {
				skuCode = codeConfigVO.getCodeEx()
						+ GenerationNum.getRandomNumber(9);
			}
			itemDTO.setSkuCode(skuCode);
		}

		itemDTO.setMainID(mainID);// 商品编号
		itemDTO.setName(name);// 商品名称

		if (StringUtil.isNumberic(salesPrice)) {
			itemDTO.setSalesPrice(Double
					.parseDouble(salesPrice));// 搜贝价(元)
		}
		if (StringUtil.isNumberic(standrardPrice)) {
			itemDTO.setStandrardPrice(Double
					.parseDouble(standrardPrice));// 商品价(元)
		}
		if (StringUtil.isNumberic(weight)) {
			itemDTO.setWeight(Double.parseDouble(weight));
		}
		if (StringUtil.isNumberic(cubage)) {
			itemDTO.setCubage(Integer.parseInt(cubage));
		}
		int stock = 99999;
		itemDTO.setStock(stock);// 库存
		itemDTO.setPicURL(picURL);
		itemDTO.setProductID(productID);
		itemDTO.setDescription(description);
		itemDTO.setIsDelete(0);
		itemDTO.setSalesVolume(0);
		itemDTO.setSupplierCategoryID(supplierCategoryID);
		itemDTO.setSupplierName(supplierName);
		itemDTO.setSupplierID(supplierID);
		itemDTO.setFreightTemplateID(freightTemplateID);
		itemDTO.setStatus(1);		//当前版本，发布一个商品就默认为审核通过
		
		ItemVO itemVO= itemDAO.findItemById(mainID);
		if (itemVO != null) {
			itemDTO.setModifier(currentOperator);
			itemDAO.updateItemByMainIDNew(itemDTO);
		} else {
			itemDTO.setCreator(currentOperator);
			itemDAO.insertItemNew(itemDTO);
		}
}
	
    public Integer updateItemStatus(String mainIDs, String status) {
    	String ids[] = mainIDs.split(",");
        Integer count=null;
        
        for (String mainID : ids) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setMainID(mainID);
            
            if (StringUtil.isInteger(status)) {
                itemDTO.setStatus(Integer.parseInt(status));
                count = itemDAO.updateItemByMainIDNew(itemDTO);
            }
        }
        return count;
    }

	@Override
	public List<ItemVO> findSupplierPassItems(ItemDTO itemDTO) {
		//这里直接因为前端分页是由productList来掌控的所以这里就
//		long count = itemDAO.getSupplierPassItemsCount(itemDTO);
//		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
		return itemDAO.findSupplierPassItems(itemDTO);
	}
    
    /**
     * 商品详情页 根据产品ID与商品ID查找商品
     * @date 2015年11月16日
     * @author sxc
     * @param ItemDto
     * @return
     * @since JDK 1.6
     * @Description
     */	
    @Override
    public ItemVO findItemDetailByProductIDTparts(ItemDTO ItemDto) {
    	return itemDAO.findItemDetailByProductIDTparts(ItemDto);
    }
    
    /**
     * //副厂件的适用车型即是对应的所有原厂件的适用车型
     */
    @Override
    public Map<ProductCategoryVO, List<VehicleTypeVO>> findSuitVehicle(
    		ProductVO productVo) {
    	
    	
    	List<VehicleTypeVO> vehicleTypes = vehicleTypeService.findVehicleTypeByProductId(productVo.getMainID());
    	Map<String, List<VehicleTypeVO>> vehicleTypeMap = new HashMap<String, List<VehicleTypeVO>>();
    	List<VehicleTypeVO> _vehicleTypes = null;
    	for (VehicleTypeVO vehicleTypeVO : vehicleTypes) {
    		//查找带品牌信息车系
    		// 待优化 ,应避免循环查询数据库
    		//_p = productCategoryService.findProductCategoryById(vehicleTypeVO.getProductCategoryId());
			if(vehicleTypeMap.containsKey(vehicleTypeVO.getProductCategoryParentId()))
			{
				_vehicleTypes = vehicleTypeMap.get(vehicleTypeVO.getProductCategoryParentId());
				_vehicleTypes.add(vehicleTypeVO);
				vehicleTypeMap.put(vehicleTypeVO.getProductCategoryParentId(), _vehicleTypes);
			}else
			{
				_vehicleTypes = new ArrayList<VehicleTypeVO>();
				_vehicleTypes.add(vehicleTypeVO);
				vehicleTypeMap.put(vehicleTypeVO.getProductCategoryParentId(), _vehicleTypes);
			}
		}
    	//Map<String, List<VehicleTypeVO>> --> Map<ProductCategoryVO, List<VehicleTypeVO>>
    	Map<ProductCategoryVO, List<VehicleTypeVO>> vehicleTypeMapResult = new HashMap<ProductCategoryVO, List<VehicleTypeVO>>();
    	Set<String> productCategoryIds =  vehicleTypeMap.keySet();
    	
    	for (String productCategoryId : productCategoryIds) {
			vehicleTypeMapResult.put(productCategoryService.findProductCategoryById(productCategoryId), vehicleTypeMap.get(productCategoryId));
		}
    	
    	return vehicleTypeMapResult;
    }    
    
    /**
     * 测试事务支持
     * @param ItemId
     * @param picURL
     * @return
     * @throws Exception 
     */    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void editItemTest(ItemVO itemVo) {
    	
    	itemDAO.editItemTest(itemVo);
    	int t = 5/0;
//    	try {
//			int t = 5/0;
//		} catch (Exception e) {
//			throw new Exception("RuntimeException");
//		}
    }

	@Override
	public StatisticsInfoVO countMonthSales(SalesOrderDTO salesOrderDTO) {
		return salesOrderDAO.countMonthSales(salesOrderDTO);
	}

	@Override
	public StatisticsInfoVO countTotalSales(SalesOrderDTO salesOrderDTO) {
		return salesOrderDAO.countTotalSales(salesOrderDTO);
	}
}
