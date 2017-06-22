package com.kpluswebup.web.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.FinalstatementDTO;
import com.kpluswebup.web.domain.RSOFFinalstatementAndSaleOrderDTO;
import com.kpluswebup.web.vo.FinalstatementVO;

public interface FinalstatementDAO {
	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @return
	 * @return List<FinalstatementVO>
	 * @since JDK 1.6
	 * @Description 查询昨日可生成的结算单
	 */
	List<FinalstatementVO> findYesterdayFinalStatement();
	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalstatementDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description 添加结算单
	 */
	void addFinalstatement(FinalstatementDTO finalstatementDTO);
	

	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalStatementID
	 * @return
	 * @return FinalstatementVO
	 * @since JDK 1.6
	 * @Description 根据MainID查询结算单
	 */
	FinalstatementVO findFinalStatementByMainID(String finalStatementID);

	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalstatementDTO
	 * @return
	 * @return List<FinalstatementVO>
	 * @since JDK 1.6
	 * @Description 结算单分页查询
	 */
	List<FinalstatementVO> findFinalStatementListByPaginateion(
			FinalstatementDTO finalstatementDTO);

	long findFinalStatementListCount(FinalstatementDTO finalstatementDTO);
	
	/**
	 * 
	 * @date 2015年5月27日
	 * @author moo
	 * @param rs
	 * @return void
	 * @since JDK 1.6
	 * @Description 添加结算单订单关系
	 */
	void addRSOFFinalstatementAndSaleOrder(RSOFFinalstatementAndSaleOrderDTO rs);

	/**
	 * 
	 * @date 2015年5月27日
	 * @author moo
	 * @param orderID
	 * @param supplierID
	 * @return void
	 * @since JDK 1.6
	 * @Description 更新订单明细结算状态
	 */
	void updateSalesOrderLineIsFinalstatemented(
			@Param(value="orderID") String orderID, 
			@Param(value="supplierID") String supplierID);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param mainId
	 * @return void
	 * @since JDK 1.6
	 * @Description 后台确认结算
	 */
	void updateFinalstatementStatus(
			@Param(value="mainID") String mainId, 
			@Param(value="operatorID") String operatorID);

	List<FinalstatementVO> findFinalStatementList(FinalstatementDTO finalstatementDTO);

}
