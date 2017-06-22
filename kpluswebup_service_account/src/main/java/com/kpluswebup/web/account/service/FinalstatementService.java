package com.kpluswebup.web.account.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.FinalstatementDTO;
import com.kpluswebup.web.vo.FinalstatementVO;
/**
 * 
 * @author moo
 *
 */
public interface FinalstatementService {

	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 生成昨日结算单
	 */
	int generateYesterdayFinalStatement();
	
	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalStatementID
	 * @return
	 * @return FinalstatementVO
	 * @since JDK 1.6
	 * @Description 查询结算单详细
	 */
	FinalstatementVO getFinalStatementByMainID(String finalStatementID);

	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalstatementDTO
	 * @return
	 * @return List<FinalstatementVO>
	 * @since JDK 1.6
	 * @Description 查询分页查看结算单
	 */
	List<FinalstatementVO> getFinalStatementListByPaginateion(
			FinalstatementDTO finalstatementDTO);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param mainId
	 * @param operatorID
	 * @return void
	 * @since JDK 1.6
	 * @Description 确认结算
	 */

	void confirmFinalstatement(String mainId, String operatorID);
	
	/**
     * 导出结算单明细
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSettlementDetail(HttpServletResponse response, String finalStatementID);
    
    /**
     * 导出结算单
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSettlement(HttpServletResponse response, FinalstatementDTO finalstatementDTO);


}
