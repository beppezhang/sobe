package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.CustomerGroupDTO;
import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.CustomerGroupSetDTO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerGroupSetVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.CustomerVO;

public interface CustomerGroupDAO {

    /**
     * 获取所有的会员分组信息
     * @date 2014年11月10日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupVO> findAllCustomerGroup();
    
    /**
     * 根据mainID查询会员分组
     * @date 2014年11月12日
     * @author lupeng
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public CustomerGroupVO findCustomerGroupByMainID(String mainID);
    
    /**
     * 分页获取会员分组
     * @date 2014年11月13日
     * @author wanghehua
     * @param customerGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupVO> findCustomerGroupByPagination(CustomerGroupDTO customerGroupDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月13日
     * @author wanghehua
     * @param customerGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCustomerGroupCount(CustomerGroupDTO customerGroupDTO);   
    
   /**
    * 删除会员分组
    * @date 2014年11月13日
    * @author wanghehua
    * @param mainID
    * @return
    * @since JDK 1.6
    * @Description
    */
 	public Integer deleteCustomerGroupByMainID(String mainID);

 	/**
 	 * 添加会员分组
 	 * @date 2014年11月13日
 	 * @author wanghehua
 	 * @param customerGroupDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void insertCustomerGroup(CustomerGroupDTO customerGroupDTO);


 	/**
 	 * 修改会员分组
 	 * @date 2014年11月13日
 	 * @author wanghehua
 	 * @param customerGroupDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void updateCustomerGroupByMainID(CustomerGroupDTO customerGroupDTO);
 	
 	/**
 	 * 根据分组id获取分组条件
 	 * @date 2014年11月14日
 	 * @author wanghehua
 	 * @param groupId
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerGroupSetVO> findCustomerGroupSetByGroupId(String groupId);
 	
 	/**
 	 * 删除会员分组条件
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param id
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Integer deleteGroupSetByID(Long id);
 	
 	/**
 	 * 添加会员分组条件
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param customerGroupSetDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void insertGroupSet(CustomerGroupSetDTO customerGroupSetDTO);
 	
 	/**
 	 * 修改会员分组条件
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param customerGroupSetDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void updateGroupSetByID(CustomerGroupSetDTO customerGroupSetDTO);
 	
 	/**
 	 * 根据分组条件查找会员
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param customerVO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerVO> findCustomerByGroupSet(CustomerVO customerVO);
 	
 	/**
 	 * 添加分组会员
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param customerGroupMemberDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void insertCustomerGroupMember(CustomerGroupMemberDTO customerGroupMemberDTO);
 	
 	/**
 	 * 根据分组id分页查找组内会员
 	 * @date 2014年11月17日
 	 * @author wanghehua
 	 * @param groupId
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerGroupMemberVO> findCustomerGroupMemberByGroupID(String groupId);
 	
 	/**
 	 * 分页查找组内会员
 	 * @date 2014年11月17日
 	 * @author wanghehua
 	 * @param customerGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerGroupMemberVO> findCustomerGroupMemberByPagination(CustomerGroupMemberDTO customerGroupMemberDTO);
 	
 	/**
 	 * 查总条数
 	 * @date 2014年11月17日
 	 * @author wanghehua
 	 * @param customerGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Long findCustomerGroupMemberCount(CustomerGroupMemberDTO customerGroupMemberDTO);
 	
 	/**
 	 * 根据id删除组内会员
 	 * @date 2014年11月17日
 	 * @author wanghehua
 	 * @param id
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Integer deleteCustomerGroupMemberByGroupID(String groupId);
 	
 	/**
 	 * 根据名称查询分组
 	 * @date 2014年12月24日
 	 * @author wanghehua
 	 * @param customerGroupDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerGroupVO> findCustomerGroupByName(CustomerGroupDTO customerGroupDTO);
 	
 	/**
 	 * 查询总条数
 	 * @date 2014年12月24日
 	 * @author wanghehua
 	 * @param customerGroupDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Long findCustomerGroupByNameCount(CustomerGroupDTO customerGroupDTO);
 	
 	/**
 	 * 查询全部分组会员
 	 * @date 2015年1月27日
 	 * @author wanghehua
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<CustomerGroupMemberVO> findCustomerGroupMember();
 	
 	/**
 	 * 
 	 * @date 2015年4月17日
 	 * @author yuanyuan
 	 * @param customerGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description  根据组内会员
 	 */
 	public CustomerVO findGroupMemberByGroupIDCustomerID(CustomerGroupMemberDTO customerGroupMemberDTO);
 	
}
