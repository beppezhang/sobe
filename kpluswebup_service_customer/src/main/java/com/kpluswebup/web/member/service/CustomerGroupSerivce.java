package com.kpluswebup.web.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CustomerGroupDTO;
import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.CustomerGroupSetDTO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerGroupSetVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.CustomerVO;

public interface CustomerGroupSerivce {

    public List<CustomerGroupVO> findAllCustomerGroup();

    /**
     * 分页获取会员分组
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param customerGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupVO> finCustomerGroupByPagination(CustomerGroupDTO customerGroupDTO);

    /**
     * 添加会员分组
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param customerGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void addCustomerGroup(CustomerGroupDTO customerGroupDTO);

    /**
     * 根据id查找会员分组
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerGroupVO findCustomerGroupByMainID(String mainID);

    /**
     * 修改会员分组
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param customerGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void editCustomerGroup(CustomerGroupDTO customerGroupDTO);

    /**
     * 删除会员分组
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteCustomerGroupByMainID(String mainIds);

    /**
     * 根据分组id获取分组条件
     * 
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
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteGroupSetByID(Long id);

    /**
     * 添加会员分组条件
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param customerGroupSetDTO
     * @since JDK 1.6
     * @Description
     */
    public void addGroupSet(CustomerGroupSetDTO customerGroupSetDTO);

    /**
     * 修改会员分组条件
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param customerGroupSetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void editGroupSet(CustomerGroupSetDTO customerGroupSetDTO);

    /**
     * 按分组查找会员
     * 
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
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param customerGroupMemberVO
     * @since JDK 1.6
     * @Description
     */
    public void addCustomerGroupMember(CustomerGroupMemberDTO customerGroupMemberDTO);

    /**
     * 根据分组id查找组内会员
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param groupId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupMemberVO> findCustomerGroupMemberByGroupID(String groupId);

    /**
     * 查找组内会员
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param customerGroupMemberDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupMemberVO> findCustomerGroupMemberByPagination(CustomerGroupMemberDTO customerGroupMemberDTO);

    /**
     * 删除分组会员
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteCustomerGroupMemberByGroupID(String groupId);

    /**
     * 根据名称查询分组
     * 
     * @date 2014年12月24日
     * @author wanghehua
     * @param customerGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerGroupVO> findCustomerGroupByName(CustomerGroupDTO customerGroupDTO);
    
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
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportCustomerGroupMember(HttpServletResponse response, String mainIds,String groupID);
}
