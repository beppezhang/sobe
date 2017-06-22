package com.kpluswebup.web.account.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;

public interface AccountDetailService {

	/**
	 * 分页查询退款列表
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param accountDetailDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<AccountDetailVO> findAccountDetailByPagination(
			AccountDetailDTO accountDetailDTO);

	/**
	 * 查找退款单
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param id
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public AccountDetailVO findAccountDetail(String accountID);
	
	/**
	 * 修改退款状态
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param accountDetailDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateAccountDetail(AccountDetailDTO accountDetailDTO);

	/**
	 * 查找退款单对应的退款申请单
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param returnID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public SalesOrderReturnApplyVO findSalesOrderReturnApply(
			String returnApplyID);

	/**
	 * 修改退货申请退款状态
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param salesOrderReturnApplyDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSalesOrderReturnApply(
			SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);

	/**
	 * 查找退款单对应的退货单据
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param returnID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public SalesOrderReturnVO findSalesOrderReturn(String returnID);

	/**
	 * 修改退货单据的退款状态
	 * 
	 * @date 2014年11月7日
	 * @author wanghehua
	 * @param salesOrderReturnDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSalesOrderReturn(SalesOrderReturnDTO salesOrderReturnDTO);
	
	  
    /**
     * 添加会员积分
     * @date 2014年12月2日
     * @author wanghehua
     * @param accountDetailDTO
     * @since JDK 1.6
     * @Description
     */
    public void addMemberScore(AccountDetailDTO accountDetailDTO);
    
    /**
     * 分页查询会员积分
     * 
     * @date 2014年12月23日
     * @author yuanyuan
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AccountDetailVO> findMemberScoreByPagination(AccountDetailDTO accountDetailDTO);
    
    /**
     * 我的返利
     * 
     * @date 2014年12月24日
     * @author yuanyuan
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AccountDetailVO> findAccountDetailTypeByCustermor(AccountDetailDTO accountDetailDTO) ;
    
    /**
     * 积分购买
     * 
     * @date 2014年12月24日
     * @author yuanyuan
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AccountDetailVO> findAccountDetailByBuy(AccountDetailDTO accountDetailDTO);
    
    /**
     * 保存充值记录
     * @date 2015年1月14日
     * @author yuanyuan
     * @param accountDetailDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertMemberScore(AccountDetailDTO accountDetailDTO);
    
    /**
     * 积分奖惩
     * @date 2015年1月21日
     * @author wanghehua
     * @param customerIDs
     * @param groupIDs
     * @param type
     * @param title
     * @param description
     * @param currentOperator
     * @since JDK 1.6
     * @Description
     */
    public void addScore(String[] customerIDs, String[] groupIDs, String type,String amount, String description, String currentOperator);
    
    
    /**
     * 导出财务报表
     * @date 2015年6月6日
     * @author wanghehua
     * @param response
     * @since JDK 1.6
     * @Description
     */
    public void exportFinanceReport(HttpServletResponse response,AccountDetailDTO accountDetailDTO);
}
