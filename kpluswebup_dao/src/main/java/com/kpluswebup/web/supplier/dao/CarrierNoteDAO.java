package com.kpluswebup.web.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.CarrierNoteDTO;
import com.kpluswebup.web.domain.CarrierNoteLineDTO;
import com.kpluswebup.web.domain.CarrierNoteLineDetailDTO;
import com.kpluswebup.web.vo.CarrierNoteLineDetailVO;
import com.kpluswebup.web.vo.CarrierNoteLineVO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupLineVO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupVO;
import com.kpluswebup.web.vo.CarrierNoteVO;

public interface CarrierNoteDAO {

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return
	 * @return List<CarrierNoteVO>
	 * @since JDK 1.6
	 * @Description 取货单列表查询
	 */
	public List<CarrierNoteVO> queryCarrierNote(CarrierNoteDTO carrierNoteDTO);
	

	public long getCarrierNoteCount(CarrierNoteDTO carrierNoteDTO);

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param mainID
	 * @return
	 * @return List<CarrierNoteLineVO>
	 * @since JDK 1.6
	 * @Description 查询取货单订单明细
	 */
	public List<CarrierNoteLineVO> getCarrierNoteLineBycarrierNoteID(
			String mainID);

	
	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param mainID
	 * @return
	 * @return List<CarrierNoteLineDetailVO>
	 * @since JDK 1.6
	 * @Description 查询取货单明细订单商品
	 */
	public List<CarrierNoteLineDetailVO> getCarrierNoteLineDetailBycarrierNoteLineID(
			String mainID);

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carrierNoteLineDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description 添加取货单订单信息
	 */
	public void addCarrierNoteLine(CarrierNoteLineDTO carrierNoteLineDTO);

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carrierNoteLineDetailDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description 添加取货单订单信息明细
	 */
	public void addCarrierNoteLineDetail(
			CarrierNoteLineDetailDTO carrierNoteLineDetailDTO);

	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description 添加取货单
	 */
	public void addCarrierNote(CarrierNoteDTO carrierNoteDTO);


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
	public List<CarrierNotePrintGroupVO> findCarrierNotePrintGroupBySupplierIDAndCarrierNoteIDs(@Param(value="supplierID") String mainID, 
			@Param(value="carriesNoteIDs") String[] carriesNoteIDs);

	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param mainID
	 * @return
	 * @return List<CarrierNotePrintGroupLineVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<CarrierNotePrintGroupLineVO> findCarrierNotePrintGroupLineByGroupID(
			String mainID);

	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param mainID
	 * @param pickupMan
	 * @param carriesNoteIDs
	 * @return void
	 * @since JDK 1.6
	 * @Description 更新打印状态
	 */
	public void updateCarrierNotePrintedBySupplierIDAndCarrierNoteIDs(
			@Param(value="supplierID") String mainID, 
			@Param(value="pickMan") String pickupMan, 
			@Param(value="carriesNoteIDs") String[] carriesNoteIDs);
	

	/**
	 * 
	 * @date 2015年5月21日
	 * @author moo
	 * @param carrierNoteIDs
	 * @return
	 * @return List<CarrierNoteVO>
	 * @since JDK 1.6
	 * @Description 
	 */
	public List<CarrierNoteVO> findCarrierNoteByCarrierNoteIDs(String[] carrierNoteIDs);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return
	 * @return long
	 * @since JDK 1.6
	 * @Description
	 */
	public long getCarrierNotePaginationCount(CarrierNoteDTO carrierNoteDTO);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteDTO
	 * @return
	 * @return List<CarrierNoteVO>
	 * @since JDK 1.6
	 * @Description 平台取货单查看
	 */
	public List<CarrierNoteVO> queryCarrierNotePagination(
			CarrierNoteDTO carrierNoteDTO);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @return
	 * @return CarrierNotePrintGroupVO
	 * @since JDK 1.6
	 * @Description 平台取货单查看详细
	 */
	public CarrierNotePrintGroupVO findCarrierNotePrintGroupCarrierNoteID(
			String carrierNoteID);

	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */
	public int updateCarrierNoteStoreStatusReceived(String carrierNoteID);

}
