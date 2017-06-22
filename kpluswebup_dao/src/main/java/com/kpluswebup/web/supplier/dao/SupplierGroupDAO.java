package com.kpluswebup.web.supplier.dao;

import java.util.List;

import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.domain.SupplierGroupDTO;
import com.kpluswebup.web.domain.SupplierGroupMemberDTO;
import com.kpluswebup.web.domain.SupplierGroupSetDTO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SupplierGroupMemberVO;
import com.kpluswebup.web.vo.SupplierGroupSetVO;
import com.kpluswebup.web.vo.SupplierGroupVO;
import com.kpluswebup.web.vo.SupplierVO;

public interface SupplierGroupDAO {
	
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
     * 根据mainID查询供应商分组
     * @date 2015年4月22日
     * @author meisu
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public SupplierGroupVO findSupplierGroupByMainID(String mainID);
	
	/**
     * 分页获取供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SupplierGroupVO> findSupplierGroupByPagination(SupplierGroupDTO supplierGroupDTO);
    
    /**
     * 查询总条数
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSupplierGroupCount(SupplierGroupDTO supplierGroupDTO);   
    
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
     * 添加供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertSupplierGroup(SupplierGroupDTO supplierGroupDTO);
    
    /**
     * 修改供应商分组
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierGroupDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateSupplierGroupByMainID(SupplierGroupDTO supplierGroupDTO);
    
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
 	 * 删除供应商分组条件
 	 * @date 2014年11月15日
 	 * @author wanghehua
 	 * @param id
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Integer deleteGroupSetByID(Long id);
 	
 	/**
 	 * 添加供应商分组条件
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupSetDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void insertGroupSet(SupplierGroupSetDTO supplierGroupSetDTO);
 	
 	/**
 	 * 修改供应商分组条件
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupSetDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void updateGroupSetByID(SupplierGroupSetDTO supplierGroupSetDTO);
 	/**
 	 * 根据分组条件查找供应商
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierVO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<SupplierVO> findSupplierByGroupSet(SupplierVO supplierVO);
 	
 	/**
 	 * 添加分组供应商
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupMemberDTO
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public void insertSupplierGroupMember(SupplierGroupMemberDTO supplierGroupMemberDTO);
 	
 	/**
 	 * 根据分组id分页查找组内供应商
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierId
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<SupplierGroupMemberVO> findSupplierGroupMemberByGroupID(String groupId);
 	
 	/**
 	 * 分页查找组内供应商
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<SupplierGroupMemberVO> findSupplierGroupMemberByPagination(SupplierGroupMemberDTO supplierGroupMemberDTO);
 	
 	/**
 	 * 查总条数
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Long findSupplierGroupMemberCount(SupplierGroupMemberDTO supplierGroupMemberDTO);
 	
 	/**
 	 * 根据id删除组内供应商
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param id
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Integer deleteSupplierGroupMemberByGroupID(String groupId);
 	
 	/**
 	 * 根据名称查询分组
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param SupplierGroupDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public List<SupplierGroupVO> findSupplierGroupByName(SupplierGroupDTO supplierGroupDTO);
 	
 	/**
 	 * 查询总条数
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description
 	 */
 	public Long findSupplierGroupByNameCount(SupplierGroupDTO supplierGroupDTO);
 	
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
 	 * 
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description  根据组内供应商
 	 */
 	public SupplierVO findSupplierMemberByGroupIDSupplierID(SupplierGroupMemberDTO supplierGroupMemberDTO);
 	
 	/**
 	 * 
 	 * @date 2015年4月22日
 	 * @author meisu
 	 * @param supplierGroupMemberDTO
 	 * @return
 	 * @since JDK 1.6
 	 * @Description  根据组内供应商
 	 */
 	/*public SupplierVO findGroupMemberByGroupIDSupplierID(SupplierGroupMemberDTO supplierGroupMemberDTO);*/
 	
}
