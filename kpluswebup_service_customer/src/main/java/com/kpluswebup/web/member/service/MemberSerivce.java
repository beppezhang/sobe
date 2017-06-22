package com.kpluswebup.web.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerSalesOrderVO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpluswebup.web.vo.ItemReviewVO;

public interface MemberSerivce {

    /**
     * 分页查询用户列表
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> findCustomerByPagination(CustomerDTO customerDTO);
    
    /**
     * 
     * @date 2015年5月19日
     * @author wanghehua
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> findCustomerApplyByPagination(CustomerDTO customerDTO);

    /**
     * 用户详情
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCustomeByMianId(String mainId);

    /**
     * 批量删除，mainIds以,分割
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void deleteCustomerBatch(String mainIds);

    /**
     * 锁定
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void lockCustomerBatch(String mainIds,String status);

    /**
     * 解锁
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void unLockCustomerBatch(String mainIds,String status);

    /**
     * 修改会员信息
     * 
     * @date 2014年10月27日
     * @author zhuhp
     * @param customerDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateMember(CustomerDTO customerDTO);

    /**
     * 查找会员订单列表
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerSalesOrderVO> findCustomerSalesOrder(SalesOrderDTO salesOrderDTO);

    /**
     * 查找会员商品咨询
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param itemConsultingDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemConsultingVO> findCustomerConsulting(ItemConsultingDTO itemConsultingDTO);

    /**
     * 根据id查找商品咨询
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemConsultingVO findConsultingByMainID(String mainID);

    /**
     * 回复咨询
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param itemConsultingDTO
     * @since JDK 1.6
     * @Description
     */
    public void replyConsulting(ItemConsultingDTO itemConsultingDTO);

    /**
     * 批量删除商品咨询
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteConsulting(String mainIds);

    /**
     * 查找会员商品评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemReviewVO> findCustomerReview(ItemReviewDTO itemReviewDTO);

    /**
     * 根据id查找商品评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemReviewVO findReviewByMainID(String mainID);

    /**
     * @date 2014年11月14日
     * @author wanghehua
     * @param itemReviewDTO
     * @since JDK 1.6
     * @Description
     */
    public void replyReview(ItemReviewDTO itemReviewDTO);

    /**
     * 批量审核商品评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param mainIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean checkReview(String mainIds,String currentOperator);

    /**
     * 会员导出excel
     * 
     * @date 2014年11月18日
     * @author wanghehua
     * @param list
     * @since JDK 1.6
     * @Description
     */
    public void exportMember(HttpServletResponse response, CustomerDTO customerDTO);

    /**
     * 查询所有会员
     * 
     * @date 2014年12月2日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> findCustomerAll();

    /**
     * 创建会员
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @param phone
     * @param code
     * @param password
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean addMember(String mobile, String companyName, String password,String mainID,String openid);

    /**
     * 创建会员
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @param phone
     * @param code
     * @param password
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean editMember(String picURL1, String picURL2, String picURL3, String mainID, String openid);
    
    
    /**
     * 新增注册信息
     * 
     * @date 2015年5月7日
     * @author meisu
     * @param mobile
     * @param companyName
     * @param password
     * @param mainID
     * @param openid
     * @return 
     * @since JDK 1.6
     * @Description
     */
    public void addRegister(CustomerDTO customerDTO);
    
    /**
     * 修改注册信息
     * 
     * @date 2015年5月7日
     * @author meisu
     * @return CustomerDTO
     * @since JDK 1.6
     * @Description
     */
    public void editRegister(CustomerDTO customerDTO);
    
    /**
     * 查询会员是否存在
     * 
     * @date 2014年12月18日
     * @author yuanyuan
     * @param phone
     * @since JDK 1.6
     * @Description
     */
    public Boolean findCustomeByPhone(String phone);
    /**
     * 昵称
     * @param name
     * @return
     */
    public Boolean findCustomeByName(String name);

    /**
     * 添加一条记录
     * 
     * @date 2014年12月23日
     * @author liudanqi
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertCustomer(CustomerDTO customerDTO);

    /**
     * 查询我的会员
     * 
     * @date 2014年12月24日
     * @author liulihui
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> getUserMember(CustomerDTO customerDTO);

    /**
     * 会员登录
     * 
     * @date 2014年12月25日
     * @author yuanyuan
     * @param userName
     * @param password
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean isLogin(String userName, String password);

    /**
     * @date 2014年12月30日
     * @author zhuhp
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCustomerByUserName(String userName);

    /**
     * 根据id查询会员
     * 
     * @date 2015年1月7日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findMemberByMainID(String mainID);
    
    /**
     * 根据微信id查找用户
     * @date 2015年1月16日
     * @author liudanqi
     * @param openid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCustomerByOpenID(String openid);
    
  
}
