package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.CustomerGradeDTO;
import com.kpluswebup.web.vo.CustomerGradeVO;

public interface CustomerGradeSerivce {

    public List<CustomerGradeVO> findAllCustomerGrade();
    
    /**
	 * 分页查找会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param customerGradeDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
    public List<CustomerGradeVO> findCustomerGradeByPagination(CustomerGradeDTO customerGradeDTO);

    /**
     * 添加会员等级
     * @date 2014年11月11日
     * @author wanghehua
     * @param customerGradeDTO
     * @since JDK 1.6
     * @Description
     */
    public void addCustomerGrade(CustomerGradeDTO customerGradeDTO);

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
	 * 编辑会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param customerGradeDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void editCustomerGrade(CustomerGradeDTO customerGradeDTO);
	
	/**
	 * 删除会员等级
	 * @date 2014年11月11日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteCustomerGradeByMainID(String mainIds);
	
	/**
	 * 查询登记默认记录
	 * @date 2014年12月31日
	 * @author liulihui
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public CustomerGradeVO findCustomerGradeTypedefault();

}
