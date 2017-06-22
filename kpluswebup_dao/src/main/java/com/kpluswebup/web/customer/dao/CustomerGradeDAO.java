package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.CustomerGradeDTO;
import com.kpluswebup.web.vo.CustomerGradeVO;

public interface CustomerGradeDAO {

    /**
     * 获取所有的会员等级信息
     * @date 2014年11月10日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<CustomerGradeVO> findAllCustomerGrade();
    
    /**
     * 查询总条数
     * @date 2014年11月11日
     * @author wanghehua
     * @param customerGradeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerGradeCount(CustomerGradeDTO customerGradeDTO);
    
    /**
     * 分页查询会员等级
     * @date 2014年11月11日
     * @author wanghehua
     * @param customerGradeDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGradeVO> findCustomerGradeByPagination(CustomerGradeDTO customerGradeDTO);
    
   /**
    * 删除会员等级
    * @date 2014年11月11日
    * @author wanghehua
    * @param mainID
    * @return
    * @since JDK 1.6
    * @Description
    */
	public Integer deleteCustomerGradeByMainID(String mainID);

	/**
	 * 添加会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param customerGradeDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertCustomerGrade(CustomerGradeDTO customerGradeDTO);

	/**
	 * 根据id查找会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CustomerGradeVO findCustomerGradeByMainID(String mainID);

	/**
	 * 修改会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param customerGradeDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateCustomerGradeByMainID(CustomerGradeDTO customerGradeDTO);
	
	/**
	 * 查询等级默认记录
	 * @date 2014年12月31日
	 * @author liulihui
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CustomerGradeVO findCustomerGradeTypedefault();
	
	/**
	 * 查询vip等级
	 * @date 2015年5月19日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CustomerGradeVO findCustomerGradeTypeVIP();
	
}
