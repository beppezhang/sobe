package com.kpluswebup.web.supplier.service;

import java.util.List;

import com.kpluswebup.web.domain.CarrierNoteDTO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupVO;
import com.kpluswebup.web.vo.CarrierNoteVO;

public interface CarrierNoteService {
	
	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return
	 * @return List<CarrierNoteVO>
	 * @since JDK 1.6
	 * @Description 打印单列表查询
	 */
	List<CarrierNoteVO> findCarrierNote(CarrierNoteDTO carrierNoteDTO);
	
	
	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param fromDate
	 * @param endDate
	 * @param mainID
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 根据订单日期生成取货单
	 */
	int addCarrierNoteBySupplierIDAndDate(String fromDate, String endDate,
			String mainID);

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param mainID
	 * @param carriesNoteID
	 * @return
	 * @return List<CarrierNotePrintGroupVO>
	 * @since JDK 1.6
	 * @Description 
	 */
	List<CarrierNotePrintGroupVO> findCarrierNoteBySupplierIDAndCarrierNoteIDs(String mainID,
			String carriesNoteID);

	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param mainID
	 * @param carriesNoteIDs
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 
	 */
	int updateCarrierNotePrintedBySupplierIDAndCarrierNoteIDs(
			String mainID, String pickupMan, String carriesNoteIDs);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return
	 * @return List<CarrierNoteVO>
	 * @since JDK 1.6
	 * @Description 平台查看取货单
	 */
	List<CarrierNoteVO> findCarrierNoteByPagination(
			CarrierNoteDTO carrierNoteDTO);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @return
	 * @return CarrierNotePrintGroupVO
	 * @since JDK 1.6
	 * @Description 平台查看取货单详细
	 */
	CarrierNotePrintGroupVO findCarrierNoteCarrierNoteID(String carrierNoteID);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 平台取货单收货
	 */
	int updateCarrierNoteStoreStatusReceived(String carrierNoteID);
	
	
}
