package com.kpluswebup.web.supplier.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.CustomerGroupSetDTO;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.domain.SupplierGroupDTO;
import com.kpluswebup.web.domain.SupplierGroupMemberDTO;
import com.kpluswebup.web.domain.SupplierGroupSetDTO;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SupplierGroupMemberVO;
import com.kpluswebup.web.vo.SupplierGroupSetVO;
import com.kpluswebup.web.vo.SupplierGroupVO;
import com.kpluswebup.web.vo.SupplierVO;

public interface SupplierGroupService {
	
	/**
     * 获取所有的供应商分组信息
     * @date 2015年4月22日
     * @author meisu
     * @return
     * @since JDK 1.6
     * @Description
     */
	public List<SupplierGroupVO> findAllSupplierGroup();
	/**
     * 分页获取供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupVO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupVO> findSupplierGroupByPagination(SupplierGroupDTO supplierGroupDTO);
    
    /**
     * 添加供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSupplierGroup(SupplierGroupDTO supplierGroupDTO);
    
    /**
     * 根据id查找供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SupplierGroupVO findSupplierGroupByMainID(String mainID);

    /**
     * 修改供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void editSupplierGroup(SupplierGroupDTO supplierGroupDTO);

    /**
     * 删除供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteSupplierGroupByMainID(String mainIds);
    
    /**
     * 根据分组id获取分组条件
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param groupId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupSetVO> findSupplierGroupSetByGroupId(String groupId);

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
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupSetDTO
     * @since JDK 1.6
     * @Description
     */
    public void addGroupSet(SupplierGroupSetDTO supplierGroupSetDTO);

    /**
     * 修改供应商分组条件
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupSetDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void editGroupSet(SupplierGroupSetDTO supplierGroupSetDTO);

    /**
     * 按分组查找供应商
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierVO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierVO> findSupplierByGroupSet(SupplierVO supplierVO);

    /**
     * 添加分组会员
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupMemberDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSupplierGroupMember(SupplierGroupMemberDTO supplierGroupMemberDTO);

    /**
     * 根据分组id查找组内供应商
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param groupId
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupMemberVO> findSupplierGroupMemberByGroupID(String groupId);

    /**
     * 查找组内供应商
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupMemberDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupMemberVO> findSupplierGroupMemberByPagination(SupplierGroupMemberDTO supplierGroupMemberDTO);

    /**
     * 删除分组会员
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteSupplierGroupMemberByGroupID(String groupId);

    /**
     * 根据名称查询分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupVO> findSupplierGroupByName(SupplierGroupDTO supplierGroupDTO);
    
    /**
     * 查询全部分组供应商
     * @date 2015年4月22日
     * @author meisu
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupMemberVO> findSupplierGroupMember();
    
    /**
     * 导出供应商分组
     * @date 2015年4月22日
     * @author meisu
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSupplierGroupMember(HttpServletResponse response, String mainIds,String groupID);
}
