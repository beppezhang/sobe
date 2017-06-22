package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.AreaDAO;
import com.kpluswebup.web.admin.system.dao.FreightTemplateDAO;
import com.kpluswebup.web.admin.system.dao.FreightTemplatePriceDAO;
import com.kpluswebup.web.admin.system.service.FreightTemplateService;
import com.kpluswebup.web.domain.FreightTemplateDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceAreaSetDTO;
import com.kpluswebup.web.domain.FreightTemplatePriceDTO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaSetVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

	@Autowired
	private FreightTemplateDAO freightTemplateDAO;
	@Autowired
	private FreightTemplatePriceDAO freightTemplatePriceDAO;
	@Autowired
	private AreaDAO areaDAO;

	@Override
	public List<FreightTemplateVO> findFreightTemplate() {
		List<FreightTemplateVO> tlist = freightTemplateDAO
				.findFreightTemplate();
		if (tlist != null && tlist.size() > 0) {
			for (FreightTemplateVO freightTemplateVO : tlist) {
				List<FreightTemplatePriceVO> plist = freightTemplatePriceDAO
						.findFreightTemplatePriceByFTID(freightTemplateVO
								.getMainID());
				if (plist != null && plist.size() > 0) {
					freightTemplateVO.setPriceList(plist);
					for (FreightTemplatePriceVO freightTemplatePriceVO : plist) {
						FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO=new FreightTemplatePriceAreaSetDTO();
						freightTemplatePriceAreaSetDTO.setfTPriceID(freightTemplatePriceVO.getMainID());
						freightTemplatePriceAreaSetDTO.setAreaDepth(6);
						List<FreightTemplatePriceAreaSetVO> alist = freightTemplatePriceDAO
								.findFreightTemplatePriceAreaSetByFTPID(freightTemplatePriceAreaSetDTO);
						freightTemplatePriceVO.setPriceareasetList(alist);
					}
				}

			}
		}
		return tlist;
	}

	@Override
	public void addFreightTemplate(String name, String priceType,
			String expressID, String formatID, String firstCondition,
			String firstPrice, String addUnit, String addPrice,
			String description, String[] fTPricesAreaIDs,String currentOperator) {
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		if (StringUtil.isNotEmpty(name)) {
			freightTemplateDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(priceType)) {
			freightTemplateDTO.setPriceType(Integer.valueOf(priceType));
		}
		if (StringUtil.isNotEmpty(expressID)) {
			freightTemplateDTO.setExpressID(expressID);
		}
		if (StringUtil.isNotEmpty(formatID)) {
			freightTemplateDTO.setFormatID(formatID);
		}
		if (StringUtil.isNotEmpty(firstCondition)) {
			freightTemplateDTO.setFirstCondition(Integer
					.valueOf(firstCondition));
		}
		if (StringUtil.isNotEmpty(firstPrice)) {
			freightTemplateDTO.setFirstPrice(Double.valueOf(firstPrice));
		}
		if (StringUtil.isNotEmpty(addUnit)) {
			freightTemplateDTO.setAddUnit(Integer.valueOf(addUnit));
		}
		if (StringUtil.isNotEmpty(addPrice)) {
			freightTemplateDTO.setAddPrice(Double.valueOf(addPrice));
		}
		if (StringUtil.isNotEmpty(description)) {
			freightTemplateDTO.setDescription(description);
		}
		freightTemplateDTO.setMainID(UUIDUtil.getUUID());
		freightTemplateDTO.setCreator(currentOperator);
		freightTemplateDAO.insertFreightTemplate(freightTemplateDTO);
		if (fTPricesAreaIDs != null) {
			for (int i = 0; i < fTPricesAreaIDs.length; i++) {
				String str = fTPricesAreaIDs[i];
				if (StringUtil.isNotEmpty(str)) {
					String[] ftPrices = str.split(",");
					FreightTemplatePriceDTO freightTemplatePriceDTO = new FreightTemplatePriceDTO();
					freightTemplatePriceDTO
							.setFreightTemplateID(freightTemplateDTO
									.getMainID());
					String prices = ftPrices[0];
					prices = prices.replaceAll("\\|", ",");
					String[] ftPrice = prices.split(",");
					if (StringUtil.isNotEmpty(ftPrice[0])) {
						freightTemplatePriceDTO.setFirstCondition(Integer
								.valueOf(ftPrice[0]));
					}
					if (StringUtil.isNotEmpty(ftPrice[1])) {
						freightTemplatePriceDTO.setFirstPrice(Double
								.valueOf(ftPrice[1]));
					}
					if (StringUtil.isNotEmpty(ftPrice[2])) {
						freightTemplatePriceDTO.setAddUnit(Integer
								.valueOf(ftPrice[2]));
					}
					if (StringUtil.isNotEmpty(ftPrice[3])) {
						freightTemplatePriceDTO.setAddPrice(Double
								.valueOf(ftPrice[3]));
					}
					freightTemplatePriceDTO.setMainID(UUIDUtil.getUUID());
					freightTemplatePriceDTO.setCreator(currentOperator);
					freightTemplatePriceDAO
							.insertFreightTemplatePrice(freightTemplatePriceDTO);
					String areaIDs = ftPrices[1];
					if (StringUtil.isNotEmpty(areaIDs)) {
						areaIDs = areaIDs.replaceAll("\\|", ",");
						String[] areaID = areaIDs.split(",");
						for (int a = 0; a < areaID.length; a++) {
							FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO = new FreightTemplatePriceAreaSetDTO();
							freightTemplatePriceAreaSetDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								freightTemplatePriceAreaSetDTO
										.setAreaID(areaIds[0]);
								freightTemplatePriceAreaSetDTO
										.setAreaDepth(Integer
												.valueOf(areaIds[1]));
							}
							freightTemplatePriceDAO
									.insertFreightTemplatePriceAreaSet(freightTemplatePriceAreaSetDTO);
							FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO = new FreightTemplatePriceAreaDTO();
							freightTemplatePriceAreaDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								AreaVO areaVO = areaDAO
										.getAreaByMainID(areaIds[0]);
								if (areaVO != null && areaVO.getDepth() != 5) {
									freightTemplatePriceAreaDTO
											.setCityID(areaVO.getMainID());
									if (areaVO.getParentID() != null) {
										freightTemplatePriceAreaDTO
												.setProvinceID(areaVO
														.getParentID());
										AreaVO provinceVO = areaDAO
												.getAreaByMainID(areaVO
														.getParentID());
										if (provinceVO != null) {
											freightTemplatePriceAreaDTO
													.setCountryID(provinceVO
															.getParentID());
										}
										freightTemplatePriceAreaDTO.setCreator(currentOperator);
										freightTemplatePriceDAO
												.insertFreightTemplatePriceArea(freightTemplatePriceAreaDTO);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public FreightTemplateVO findFreightTemplateByMainID(String mainID) {
		return freightTemplateDAO.findFreightTemplateByMainID(mainID);
	}

	@Override
	public void updateFreightTemplate(String mainID, String name,
			String priceType, String expressID, String formatID,
			String firstCondition, String firstPrice, String addUnit,
			String addPrice, String description, String[] fTPricesAreaIDs,String currentOperator) {
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		if (StringUtil.isNotEmpty(name)) {
			freightTemplateDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(priceType)) {
			freightTemplateDTO.setPriceType(Integer.valueOf(priceType));
		}
		if (StringUtil.isNotEmpty(expressID)) {
			freightTemplateDTO.setExpressID(expressID);
		}
		if (StringUtil.isNotEmpty(formatID)) {
			freightTemplateDTO.setFormatID(formatID);
		}
		if (StringUtil.isNotEmpty(firstCondition)) {
			freightTemplateDTO.setFirstCondition(Integer
					.valueOf(firstCondition));
		}
		if (StringUtil.isNotEmpty(firstPrice)) {
			freightTemplateDTO.setFirstPrice(Double.valueOf(firstPrice));
		}
		if (StringUtil.isNotEmpty(addUnit)) {
			freightTemplateDTO.setAddUnit(Integer.valueOf(addUnit));
		}
		if (StringUtil.isNotEmpty(addPrice)) {
			freightTemplateDTO.setAddPrice(Double.valueOf(addPrice));
		}
		if (StringUtil.isNotEmpty(description)) {
			freightTemplateDTO.setDescription(description);
		}
		freightTemplateDTO.setMainID(mainID);
		freightTemplateDTO.setModifier(currentOperator);
		freightTemplateDAO.updateFreightTemplate(freightTemplateDTO);
		List<FreightTemplatePriceVO> ftplist = freightTemplatePriceDAO
				.findFreightTemplatePriceByFTID(mainID);
		if (ftplist != null && ftplist.size() > 0) {
			freightTemplatePriceDAO.deleteFreightTemplatePriceByFTID(mainID);
			for (FreightTemplatePriceVO freightTemplatePriceVO : ftplist) {
				freightTemplatePriceDAO
						.deleteFreightTemplatePriceAreaSet(freightTemplatePriceVO
								.getMainID());
				freightTemplatePriceDAO
						.deleteFreightTemplatePriceArea(freightTemplatePriceVO
								.getMainID());
			}
		}
		if (fTPricesAreaIDs != null) {
			for (int i = 0; i < fTPricesAreaIDs.length; i++) {
				String str = fTPricesAreaIDs[i];
				if (StringUtil.isNotEmpty(str)) {
					String[] ftPrices = str.split(",");
					FreightTemplatePriceDTO freightTemplatePriceDTO = new FreightTemplatePriceDTO();
					freightTemplatePriceDTO
							.setFreightTemplateID(freightTemplateDTO
									.getMainID());
					String prices = ftPrices[0];
					prices = prices.replaceAll("\\|", ",");
					String[] ftPrice = prices.split(",");
					if (StringUtil.isNotEmpty(ftPrice[0])) {
						freightTemplatePriceDTO.setFirstCondition(Integer
								.valueOf(ftPrice[0]));
					}
					if (StringUtil.isNotEmpty(ftPrice[1])) {
						freightTemplatePriceDTO.setFirstPrice(Double
								.valueOf(ftPrice[1]));
					}
					if (StringUtil.isNotEmpty(ftPrice[2])) {
						freightTemplatePriceDTO.setAddUnit(Integer
								.valueOf(ftPrice[2]));
					}
					if (StringUtil.isNotEmpty(ftPrice[3])) {
						freightTemplatePriceDTO.setAddPrice(Double
								.valueOf(ftPrice[3]));
					}
					freightTemplatePriceDTO.setMainID(UUIDUtil.getUUID());
					freightTemplatePriceDTO.setCreator(currentOperator);
					freightTemplatePriceDAO
							.insertFreightTemplatePrice(freightTemplatePriceDTO);
					String areaIDs = ftPrices[1];
					if (StringUtil.isNotEmpty(areaIDs)) {
						areaIDs = areaIDs.replaceAll("\\|", ",");
						String[] areaID = areaIDs.split(",");
						for (int a = 0; a < areaID.length; a++) {
							FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO = new FreightTemplatePriceAreaSetDTO();
							freightTemplatePriceAreaSetDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								freightTemplatePriceAreaSetDTO
										.setAreaID(areaIds[0]);
								freightTemplatePriceAreaSetDTO
										.setAreaDepth(Integer
												.valueOf(areaIds[1]));
							}
							freightTemplatePriceDAO
									.insertFreightTemplatePriceAreaSet(freightTemplatePriceAreaSetDTO);
							FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO = new FreightTemplatePriceAreaDTO();
							freightTemplatePriceAreaDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								AreaVO areaVO = areaDAO
										.getAreaByMainID(areaIds[0]);
								if (areaVO != null && areaVO.getDepth() != 5) {
									freightTemplatePriceAreaDTO
											.setCityID(areaVO.getMainID());
									if (areaVO.getParentID() != null) {
										freightTemplatePriceAreaDTO
												.setProvinceID(areaVO
														.getParentID());
										AreaVO provinceVO = areaDAO
												.getAreaByMainID(areaVO
														.getParentID());
										if (provinceVO != null) {
											freightTemplatePriceAreaDTO
													.setCountryID(provinceVO
															.getParentID());
										}
										freightTemplatePriceAreaDTO.setCreator(currentOperator);
										freightTemplatePriceDAO
												.insertFreightTemplatePriceArea(freightTemplatePriceAreaDTO);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Boolean deleteFreightTemplate(String mainID) {
		try {
			freightTemplateDAO.deleteFreightTemplateByMainID(mainID);
			List<FreightTemplatePriceVO> plist = freightTemplatePriceDAO
					.findFreightTemplatePriceByFTID(mainID);
			if (plist != null && plist.size() > 0) {
				freightTemplatePriceDAO
						.deleteFreightTemplatePriceByFTID(mainID);
				for (FreightTemplatePriceVO freightTemplatePriceVO : plist) {
					freightTemplatePriceDAO
							.deleteFreightTemplatePriceAreaSet(freightTemplatePriceVO
									.getMainID());
					freightTemplatePriceDAO
							.deleteFreightTemplatePriceArea(freightTemplatePriceVO
									.getMainID());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateFreightTemplateIsDefault(String mainID) {
		try {
			FreightTemplateVO freightTemplateVO = freightTemplateDAO
					.findFreightTemplateDefault();
			if (freightTemplateVO != null) {
				freightTemplateDAO
						.updateFreightTemplateDefault(freightTemplateVO
								.getMainID());
			}
			freightTemplateDAO.updateFreightTemplateIsDefault(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public void addFreightTemplate(String name, String priceType,
			String expressID, String formatID, String firstCondition,
			String firstPrice, String addUnit, String addPrice,
			String description, String[] fTPricesAreaIDs,
			String currentOperator, SupplierVO supplierVO) {
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		if (StringUtil.isNotEmpty(name)) {
			freightTemplateDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(priceType)) {
			freightTemplateDTO.setPriceType(Integer.valueOf(priceType));
		}
		if (StringUtil.isNotEmpty(expressID)) {
			freightTemplateDTO.setExpressID(expressID);
		}
		if (StringUtil.isNotEmpty(formatID)) {
			freightTemplateDTO.setFormatID(formatID);
		}
		if (StringUtil.isNotEmpty(firstCondition)) {
			freightTemplateDTO.setFirstCondition(Integer
					.valueOf(firstCondition));
		}
		if (StringUtil.isNotEmpty(firstPrice)) {
			freightTemplateDTO.setFirstPrice(Double.valueOf(firstPrice));
		}
		if (StringUtil.isNotEmpty(addUnit)) {
			freightTemplateDTO.setAddUnit(Integer.valueOf(addUnit));
		}
		if (StringUtil.isNotEmpty(addPrice)) {
			freightTemplateDTO.setAddPrice(Double.valueOf(addPrice));
		}
		if (StringUtil.isNotEmpty(description)) {
			freightTemplateDTO.setDescription(description);
		}
		freightTemplateDTO.setMainID(UUIDUtil.getUUID());
		freightTemplateDTO.setCreator(currentOperator);
		freightTemplateDTO.setSupplierID(supplierVO.getMainID());
		freightTemplateDAO.insertFreightTemplate(freightTemplateDTO);
		if (fTPricesAreaIDs != null) {
			for (int i = 0; i < fTPricesAreaIDs.length; i++) {
				String str = fTPricesAreaIDs[i];
				if (StringUtil.isNotEmpty(str)) {
					String[] ftPrices = str.split(",");
					FreightTemplatePriceDTO freightTemplatePriceDTO = new FreightTemplatePriceDTO();
					freightTemplatePriceDTO
							.setFreightTemplateID(freightTemplateDTO
									.getMainID());
					String prices = ftPrices[0];
					prices = prices.replaceAll("\\|", ",");
					String[] ftPrice = prices.split(",");
					if (StringUtil.isNotEmpty(ftPrice[0])) {
						freightTemplatePriceDTO.setFirstCondition(Integer
								.valueOf(ftPrice[0]));
					}
					if (StringUtil.isNotEmpty(ftPrice[1])) {
						freightTemplatePriceDTO.setFirstPrice(Double
								.valueOf(ftPrice[1]));
					}
					if (StringUtil.isNotEmpty(ftPrice[2])) {
						freightTemplatePriceDTO.setAddUnit(Integer
								.valueOf(ftPrice[2]));
					}
					if (StringUtil.isNotEmpty(ftPrice[3])) {
						freightTemplatePriceDTO.setAddPrice(Double
								.valueOf(ftPrice[3]));
					}
					freightTemplatePriceDTO.setMainID(UUIDUtil.getUUID());
					freightTemplatePriceDTO.setCreator(currentOperator);
					freightTemplatePriceDAO
							.insertFreightTemplatePrice(freightTemplatePriceDTO);
					String areaIDs = ftPrices[1];
					if (StringUtil.isNotEmpty(areaIDs)) {
						areaIDs = areaIDs.replaceAll("\\|", ",");
						String[] areaID = areaIDs.split(",");
						for (int a = 0; a < areaID.length; a++) {
							FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO = new FreightTemplatePriceAreaSetDTO();
							freightTemplatePriceAreaSetDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								freightTemplatePriceAreaSetDTO
										.setAreaID(areaIds[0]);
								freightTemplatePriceAreaSetDTO
										.setAreaDepth(Integer
												.valueOf(areaIds[1]));
							}
							freightTemplatePriceDAO
									.insertFreightTemplatePriceAreaSet(freightTemplatePriceAreaSetDTO);
							FreightTemplatePriceAreaDTO freightTemplatePriceAreaDTO = new FreightTemplatePriceAreaDTO();
							freightTemplatePriceAreaDTO
									.setfTPriceID(freightTemplatePriceDTO
											.getMainID());
							if (StringUtil.isNotEmpty(areaID[a])) {
								String[] areaIds = areaID[a].split("%");
								AreaVO areaVO = areaDAO
										.getAreaByMainID(areaIds[0]);
								if (areaVO != null && areaVO.getDepth() != 5) {
									freightTemplatePriceAreaDTO
											.setCityID(areaVO.getMainID());
									if (areaVO.getParentID() != null) {
										freightTemplatePriceAreaDTO
												.setProvinceID(areaVO
														.getParentID());
										AreaVO provinceVO = areaDAO
												.getAreaByMainID(areaVO
														.getParentID());
										if (provinceVO != null) {
											freightTemplatePriceAreaDTO
													.setCountryID(provinceVO
															.getParentID());
										}
										freightTemplatePriceAreaDTO.setCreator(currentOperator);
										freightTemplatePriceDAO
												.insertFreightTemplatePriceArea(freightTemplatePriceAreaDTO);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<FreightTemplateVO> findFreightTemplateBySupplierID(
			String supplierID) {

		List<FreightTemplateVO> tlist = freightTemplateDAO
				.findFreightTemplateBySupplierID(supplierID);
		if (tlist != null && tlist.size() > 0) {
			for (FreightTemplateVO freightTemplateVO : tlist) {
				List<FreightTemplatePriceVO> plist = freightTemplatePriceDAO
						.findFreightTemplatePriceByFTID(freightTemplateVO
								.getMainID());
				if (plist != null && plist.size() > 0) {
					freightTemplateVO.setPriceList(plist);
					for (FreightTemplatePriceVO freightTemplatePriceVO : plist) {
						FreightTemplatePriceAreaSetDTO freightTemplatePriceAreaSetDTO=new FreightTemplatePriceAreaSetDTO();
						freightTemplatePriceAreaSetDTO.setfTPriceID(freightTemplatePriceVO.getMainID());
						freightTemplatePriceAreaSetDTO.setAreaDepth(6);
						List<FreightTemplatePriceAreaSetVO> alist = freightTemplatePriceDAO
								.findFreightTemplatePriceAreaSetByFTPID(freightTemplatePriceAreaSetDTO);
						freightTemplatePriceVO.setPriceareasetList(alist);
					}
				}

			}
		}
		return tlist;
	
	}

	@Override
	public FreightTemplateVO findFreightTemplateByProduct(String productID) {
		FreightTemplateVO freightTemplateVO = freightTemplateDAO.findFreightTemplateByProduct(productID);
		return freightTemplateVO;
	}
	
	@Override
	public FreightTemplateVO findFreightTemplateByItem(String itemID) {
		FreightTemplateVO freightTemplateVO = freightTemplateDAO.findFreightTemplateByItem(itemID);
		return freightTemplateVO;
	}

	@Override
	public List<FreightTemplateVO> findFreightTemplateByExpressID(String expressID) {
		return freightTemplateDAO.findFreightTemplateByExpressID(expressID);
	}

	@Override
	public FreightTemplateVO findFreightTemplateByName(FreightTemplateDTO freightTemplateDTO) {
		return freightTemplateDAO.findFreightTemplateByName(freightTemplateDTO);
		
	}

}
