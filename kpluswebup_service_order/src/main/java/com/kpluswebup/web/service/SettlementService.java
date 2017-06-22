package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.SettlementDTO;
import com.kpluswebup.web.vo.SettlementVO;

public interface SettlementService {

	/**
     * 添加结算单
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param settlementDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSettlement(SettlementDTO settlementDTO);
    
    /**
     * 分页查询结算单
     * 
     * @date 2015年1月5日
     * @author wanghehua
     * @param settlementDTO
     * @return 
     * @since JDK 1.6
     * @Description
     */
    public List<SettlementVO> findSettlementByPagination(SettlementDTO settlementDTO);
    
    /**
	 * 查找结算单
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param id
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void settlementOperation(String mainId, String status);
	
	/**
	 * 修改结算状态
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param settlementDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSettlement(SettlementDTO settlementDTO);
    
}
