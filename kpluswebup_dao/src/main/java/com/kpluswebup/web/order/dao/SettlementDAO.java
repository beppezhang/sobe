package com.kpluswebup.web.order.dao;

import java.util.List;

import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SettlementDTO;
import com.kpluswebup.web.vo.SettlementVO;

public interface SettlementDAO {

	/**
     * 添加结算单
     * @date 2015年1月5日
     * @author wanghehua
     * @param settlementDAO
     * @since JDK 1.6
     * @Description
     */
    public void addSettlement(SettlementDTO settlementDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月7日
     * @author wanghehua
     * @param settlementDAO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSettlementCount(SettlementDTO settlementDTO);
    
    /**
     * 分页查询结算单列表
     * @date 2014年11月7日
     * @author wanghehua
     * @param settlementDAO
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
	public SettlementVO findSettlement(String mainID);
	
	/**
     * 修改结算状态
     * @date 2014年11月7日
     * @author wanghehua
     * @param settlementDAO
     * @since JDK 1.6
     * @Description
     */
    public void updateSettlement(SettlementDTO settlementDTO);
	
}
