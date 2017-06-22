package com.kpluswebup.web.account.dao;

import java.util.List;

import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;


public interface AccountDetailDAO {
    
    /**
     * 添加一条账户明细记录
     * @date 2014年11月5日
     * @author lupeng
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addAccountDetailSelective(AccountDetailDTO accountDetailDTO);
    
    /**
     * 分页查询退款列表
     * @date 2014年11月7日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<AccountDetailVO> findAccountDetailByPagination(AccountDetailDTO accountDetailDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月7日
     * @author wanghehua
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findAccountDetailCount(AccountDetailDTO accountDetailDTO);
    
    /**
     * 个人中心该会员的积分记录总条数
     * @date 2015年1月23日
     * @author yuanyuan
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findAccountDetailCountByCustomerID(AccountDetailDTO accountDetailDTO);
    
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
     * 修改退货状态
     * @date 2014年11月7日
     * @author wanghehua
     * @param accountDetailDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateAccountDetail(AccountDetailDTO accountDetailDTO);
    
    /**
     * 查找退款单对应的退款申请单
     * @date 2014年11月7日
     * @author wanghehua
     * @param returnID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SalesOrderReturnApplyVO findSalesOrderReturnApply(String returnApplyID);
    
    /**
     * 修改退货申请退款状态
     * @date 2014年11月7日
     * @author wanghehua
     * @param salesOrderReturnApplyDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderReturnApply(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO);
    
    /**
     * 查找退款单对应的退货单据
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
     * @date 2014年11月7日
     * @author wanghehua
     * @param salesOrderReturnDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSalesOrderReturn(SalesOrderReturnDTO salesOrderReturnDTO);
    
    /**
     * 分页查询支付列表
     * @date 2014年11月27日
     * @author wanghehua
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
     public List<AccountDetailVO> findPaymentByPagination(AccountDetailDTO accountDetailDTO);
     
     /**
      * 查询总条数
      * @date 2014年11月27日
      * @author wanghehua
      * @param accountDetailDTO
      * @return
      * @since JDK 1.6
      * @Description
      */
     public Long findPaymentCount(AccountDetailDTO accountDetailDTO);
     
     /**
      * 分页查询会员积分
      * @date 2014年12月2日
      * @author wanghehua
      * @param accountDetailDTO
      * @return
      * @since JDK 1.6
      * @Description
      */
     public List<AccountDetailVO> findMemberScoreByPagination(AccountDetailDTO accountDetailDTO);
     
     /**
      * 查询总条数
      * @date 2014年12月2日
      * @author wanghehua
      * @param accountDetailDTO
      * @return
      * @since JDK 1.6
      * @Description
      */
     public Long findMemberScoreCount(AccountDetailDTO accountDetailDTO);
     
     /**
      * 添加会员积分
      * @date 2014年12月2日
      * @author wanghehua
      * @param accountDetailDTO
      * @since JDK 1.6
      * @Description
      */
     public void insertMemberScore(AccountDetailDTO accountDetailDTO);
     
     /**
      * 我的返利
      * @date 2014年12月24日
      * @author yuanyuan
      * @param accountDetailDTO
      * @return
      * @since JDK 1.6
      * @Description
      */
     public List<AccountDetailVO> findAccountDetailTypeByCustermor(AccountDetailDTO accountDetailDTO);
     
     /**
      * 积分购买
      * @date 2014年12月24日
      * @author yuanyuan
      * @param accountDetailDTO
      * @return
      * @since JDK 1.6
      * @Description
      */
     public List<AccountDetailVO> findAccountDetailByBuy(AccountDetailDTO accountDetailDTO) ;
     
     /**
      * 删除收款记录
      * @date 2015年6月10日
      * @author wanghehua
      * @param objID
      * @since JDK 1.6
      * @Description
      */
     public void deleteAccountDetail(String objID);
     
     public List<AccountDetailVO> findAllAccountDetail(AccountDetailDTO accountDetailDTO);
    
}