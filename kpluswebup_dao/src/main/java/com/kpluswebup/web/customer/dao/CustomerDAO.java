package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerSalesOrderVO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpluswebup.web.vo.ItemReviewVO;

public interface CustomerDAO {

    /**
     * 新增一条记录
     * 
     * @date 2014年10月23日
     * @author zhuhp
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertCustomer(CustomerDTO customerDTO);

    /**
     * 更新一条记录
     * 
     * @date 2014年10月23日
     * @author zhuhp
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateCustomerByMainId(CustomerDTO customerDTO);

    /**
     * 分页查询
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
     * 分页查询申请总数
     * @date 2015年5月19日
     * @author wanghehua
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> findCustomerApplyByPagination(CustomerDTO customerDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerCount(CustomerDTO customerDTO);

    /**
     * 查询申请总数
     * 
     * @date 2015年5月19日
     * @author wanghehua
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerApplyCount(CustomerDTO customerDTO);

    /**
     * 根据mainID查询单条记录
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCustomerByMainID(String mainID);

    /**
     * 逻辑删除一条记录
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainId
     * @since JDK 1.6
     * @Description
     */
    public void deleteCustomerByMainId(String mainId);

    /**
     * 分页查询会员订单
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerSalesOrderVO> findCustomerSalesOrderByPagination(SalesOrderDTO salesOrderDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerSalesOrderCount(SalesOrderDTO salesOrderDTO);

    /**
     * 分页查询会员商品咨询
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param itemConsultingDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemConsultingVO> findCustomerConsultingByPagination(ItemConsultingDTO itemConsultingDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param itemConsultingDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerConsultingCount(ItemConsultingDTO itemConsultingDTO);

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
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer replyConsulting(ItemConsultingDTO itemConsultingDTO);

    /**
     * 删除商品咨询
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param mainId
     * @since JDK 1.6
     * @Description
     */
    public void deleteConsultingByMainID(String mainId);

    /**
     * 分页查询会员商品评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemReviewVO> findCustomerReviewByPagination(ItemReviewDTO itemReviewDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerReviewCount(ItemReviewDTO itemReviewDTO);

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
     * 回复评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer replyReview(ItemReviewDTO itemReviewDTO);

    /**
     * 删除商品评价
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param mainId
     * @since JDK 1.6
     * @Description
     */
    public void updateReviewByMainID(ItemReviewDTO itemReviewDTO);

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
     * 根据会员名查找会员
     * 
     * @date 2014年12月25日
     * @author yuanyuan
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCusertomerByUserName(String userName);
    /**
     * 昵称
     * @param name
     * @return
     */
    public CustomerVO findCusertomerByName(String name);
    

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
     * 
     * @date 2015年1月16日
     * @author liudanqi
     * @param openid
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findCustomerByOpenID(String openid);
    
    public List<CustomerVO> findCustomer(CustomerDTO customerDTO);
    /***********************************************************/
    public CustomerVO findCusertomerByUserNameNOStatus(String userName);
}
