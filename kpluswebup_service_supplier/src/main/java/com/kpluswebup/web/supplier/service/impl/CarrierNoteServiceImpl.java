package com.kpluswebup.web.supplier.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.CarrierNoteDTO;
import com.kpluswebup.web.domain.CarrierNoteLineDTO;
import com.kpluswebup.web.domain.CarrierNoteLineDetailDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;
import com.kpluswebup.web.product.dao.SupplierItemIDDAO;
import com.kpluswebup.web.supplier.dao.CarrierNoteDAO;
import com.kpluswebup.web.supplier.service.CarrierNoteService;
import com.kpluswebup.web.vo.CarrierNoteLineDetailVO;
import com.kpluswebup.web.vo.CarrierNoteLineVO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupLineVO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupVO;
import com.kpluswebup.web.vo.CarrierNoteVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
@Service
public class CarrierNoteServiceImpl implements CarrierNoteService {

	@Autowired
	private CarrierNoteDAO carrierNoteDAO;
	
	@Autowired
	private	SalesOrderDAO salesOrderDAO;
	
	@Autowired
	private SalesOrderLineDAO salesOrderLineDAO;
	
	@Autowired
	private SupplierItemIDDAO supplierItemIDDAO;
	
	@Override
	public List<CarrierNoteVO> findCarrierNote(CarrierNoteDTO carrierNoteDTO) {
		long count = carrierNoteDAO.getCarrierNoteCount(carrierNoteDTO);
		carrierNoteDTO.doPage(count, carrierNoteDTO.getPageNo(), carrierNoteDTO.getPageSize());
		List<CarrierNoteVO> carrierNoteList = carrierNoteDAO.queryCarrierNote(carrierNoteDTO);
		if(carrierNoteList == null || carrierNoteList.size() == 0)
			return null;
		for (CarrierNoteVO carrierNoteVO : carrierNoteList) {
			List<CarrierNoteLineVO> carrierNoteLineList = carrierNoteDAO.getCarrierNoteLineBycarrierNoteID(carrierNoteVO.getMainID());
			for (CarrierNoteLineVO carrierNoteLine : carrierNoteLineList) {
				List<CarrierNoteLineDetailVO> carrierNoteLineDetailList = carrierNoteDAO.getCarrierNoteLineDetailBycarrierNoteLineID(carrierNoteLine.getMainID());
				carrierNoteLine.setCarrierNoteLineDetailList(carrierNoteLineDetailList);
			}
			carrierNoteVO.setCarrierNoteLineList(carrierNoteLineList);
		}
		return carrierNoteList;
	}

	@Override
	public int addCarrierNoteBySupplierIDAndDate(String fromDate,
			String endDate, String mainID) {
		List<SalesOrderLineVO> salesOrderLineList = salesOrderDAO.findorderLineBySupplierIDAndDate(fromDate, endDate, mainID);
		if(salesOrderLineList == null || salesOrderLineList.size() == 0)
			return 1000;
		CarrierNoteDTO carrierNoteDTO = new CarrierNoteDTO();
		String carrierNoteMainID = "PU" + getMainIDByTime();
		carrierNoteDTO.setMainID(carrierNoteMainID);
		carrierNoteDTO.setSupplierID(mainID);
		carrierNoteDTO.setStatus(0);
		carrierNoteDTO.setStoreStatus(0);
		int totalItemCount = 0;
		double totalAmount = 0d;
		Map<String, List<SalesOrderLineVO>> orderGroup = new HashMap<String, List<SalesOrderLineVO>>();
		for (SalesOrderLineVO salesOrderLine : salesOrderLineList) {
			String orderID = salesOrderLine.getOrderID();
			if(orderGroup.containsKey(orderID)){
				List<SalesOrderLineVO> lineList = orderGroup.get(orderID);
				lineList.add(salesOrderLine);
			}else{
				List<SalesOrderLineVO> lineList = new ArrayList<SalesOrderLineVO>();
				lineList.add(salesOrderLine);
				orderGroup.put(orderID, lineList);
			}
		}
		Set<Entry<String, List<SalesOrderLineVO>>> entrySet = orderGroup.entrySet();
		SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO(); 
		salesOrderLineDTO.setIscarriered(1);
		for (Entry<String, List<SalesOrderLineVO>> entry : entrySet) {
			String orderID = entry.getKey();
			List<SalesOrderLineVO> lineList = entry.getValue();
			CarrierNoteLineDTO carrierNoteLineDTO = new CarrierNoteLineDTO();
			String carrierNoteLineMainID = "PL" + getMainIDByTime();
			carrierNoteLineDTO.setMainID(carrierNoteLineMainID);
			carrierNoteLineDTO.setCarrierNoteID(carrierNoteMainID);
			carrierNoteLineDTO.setSalesOrderID(orderID);
			carrierNoteDAO.addCarrierNoteLine(carrierNoteLineDTO);
		
			for (SalesOrderLineVO salesOrderLine : lineList) {
				CarrierNoteLineDetailDTO carrierNoteLineDetailDTO = new CarrierNoteLineDetailDTO();
				String carrierNoteLineDetailMainID = "LD" + getMainIDByTime();
				carrierNoteLineDetailDTO.setMainID(carrierNoteLineDetailMainID);
				carrierNoteLineDetailDTO.setCarrierNoteLineID(carrierNoteLineMainID);
				carrierNoteLineDetailDTO.setItemID(salesOrderLine.getItemID());
				carrierNoteLineDetailDTO.setSalesPrice(salesOrderLine.getSalesPrice());
				carrierNoteLineDetailDTO.setCount(salesOrderLine.getItemCount());
				double total = salesOrderLine.getSalesPrice() * salesOrderLine.getItemCount();
				carrierNoteLineDetailDTO.setTotalAmount(total);
				carrierNoteDAO.addCarrierNoteLineDetail(carrierNoteLineDetailDTO);
				salesOrderLineDTO.setId(salesOrderLine.getId());
				salesOrderLineDAO.updateSalesOrderLineByID(salesOrderLineDTO);
				totalItemCount += salesOrderLine.getItemCount();
				totalAmount += total;
			}
		}
		carrierNoteDTO.setItemCount(totalItemCount);
		carrierNoteDTO.setTotalAmount(totalAmount);
		carrierNoteDAO.addCarrierNote(carrierNoteDTO);
		
		return 0;
	}

	private String getMainIDByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		return sdf.format(new Date());
	}

	@Override
	public List<CarrierNotePrintGroupVO> findCarrierNoteBySupplierIDAndCarrierNoteIDs(
			String mainID, String carriesNoteIDs) {
		List<CarrierNotePrintGroupVO> groupList = carrierNoteDAO.findCarrierNotePrintGroupBySupplierIDAndCarrierNoteIDs(mainID, carriesNoteIDs.split(","));
		if(groupList == null || groupList.size() == 0)
			return null;
		for (CarrierNotePrintGroupVO group : groupList) {
			List<CarrierNotePrintGroupLineVO> groupLineList = carrierNoteDAO.findCarrierNotePrintGroupLineByGroupID(group.getMainID());
			group.setGroupLineList(groupLineList);
			List<CarrierNoteLineVO> carrierNoteLineList = carrierNoteDAO.getCarrierNoteLineBycarrierNoteID(group.getMainID());
			group.setCarrierNoteLineList(carrierNoteLineList);
		}
		return groupList;
	}

	@Override
	public int updateCarrierNotePrintedBySupplierIDAndCarrierNoteIDs(
			String mainID, String pickupMan, String carriesNoteIDs) {
		carrierNoteDAO.updateCarrierNotePrintedBySupplierIDAndCarrierNoteIDs(mainID, pickupMan, carriesNoteIDs.split(","));
		supplierItemIDDAO.updateSupplierItemIDPickUp(mainID, carriesNoteIDs.split(","));
		return 0;
	}

	@Override
	public List<CarrierNoteVO> findCarrierNoteByPagination(
			CarrierNoteDTO carrierNoteDTO) {
		
		long count = carrierNoteDAO.getCarrierNotePaginationCount(carrierNoteDTO);
		carrierNoteDTO.doPage(count, carrierNoteDTO.getPageNo(), carrierNoteDTO.getPageSize());
		List<CarrierNoteVO> carrierNoteList = carrierNoteDAO.queryCarrierNotePagination(carrierNoteDTO);
		if(carrierNoteList == null || carrierNoteList.size() == 0)
			return null;
		for (CarrierNoteVO carrierNoteVO : carrierNoteList) {
			List<CarrierNoteLineVO> carrierNoteLineList = carrierNoteDAO.getCarrierNoteLineBycarrierNoteID(carrierNoteVO.getMainID());
			for (CarrierNoteLineVO carrierNoteLine : carrierNoteLineList) {
				List<CarrierNoteLineDetailVO> carrierNoteLineDetailList = carrierNoteDAO.getCarrierNoteLineDetailBycarrierNoteLineID(carrierNoteLine.getMainID());
				carrierNoteLine.setCarrierNoteLineDetailList(carrierNoteLineDetailList);
			}
			carrierNoteVO.setCarrierNoteLineList(carrierNoteLineList);
		}
		return carrierNoteList;
	}

	@Override
	public CarrierNotePrintGroupVO findCarrierNoteCarrierNoteID(
			String carrierNoteID) {
		CarrierNotePrintGroupVO group = carrierNoteDAO.findCarrierNotePrintGroupCarrierNoteID(carrierNoteID);
		List<CarrierNotePrintGroupLineVO> groupLineList = carrierNoteDAO.findCarrierNotePrintGroupLineByGroupID(group.getMainID());
		group.setGroupLineList(groupLineList);
		List<CarrierNoteLineVO> carrierNoteLineList = carrierNoteDAO.getCarrierNoteLineBycarrierNoteID(group.getMainID());
		group.setCarrierNoteLineList(carrierNoteLineList);
		return group;
	}

	@Override
	public int updateCarrierNoteStoreStatusReceived(String carrierNoteID) {
		carrierNoteDAO.updateCarrierNoteStoreStatusReceived(carrierNoteID);
		supplierItemIDDAO.updateSupplierItemIDStatus(carrierNoteID);
		return 0;
	}

}
