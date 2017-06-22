package com.kpluswebup.web.supplier.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.SupplierCategoryDTO;
import  com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SupplierCategoryVO;
import com.kpluswebup.web.vo.SupplierVO;

public interface SupplierDAO {
	/**
     * 供应商列表
     * 
     * @date 2015年4月16日
     * @author meisu
     * @param  
     * @return
     * @since JDK 1.6
     * @Description
     */
	public List<SupplierVO> supplierList(SupplierDTO supplierDTO);
	
	public List<ProductVO> findSupplierMap(SupplierDTO supplierDTO);
	/**
     * 查询供应商总条数
     * 
     * @date 2015年4月20日
     * @author meisu
     * @param supplierDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSupplierCount(SupplierDTO supplierDTO);
	
	/**
     * 根据ID查询供应商列表
     * 
     * @date 2015年4月17日
     * @author meisu
     * @param  
     * @return
     * @since JDK 1.6
     * @Description
     */
	public SupplierVO findSupplierByID(String mainID);
	
	/**
     * 添加供应商信息
     * 
     * @date 2014年4月16日
     * @author meisu
     * @param SupplierDTO
     * @since JDK 1.6
     * @Description
     */
    public void addSupplier(SupplierDTO SupplierDTO);
    
	
    /**
     * 修改供应商信息
     * 
     * @date 2014年4月16日
     * @author meisu
     * @param SupplierDTO
     * @since JDK 1.6
     * @Description
     */
    public void editSupplier(SupplierDTO SupplierDTO);
	
	/**
     * 删除供应商列表
     * 
     * @date 2015年4月16日
     * @author meisu
     * @param  mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteSupplierById(String mainID);
    
    /**
     * 
     * @date 2015年5月13日
     * @author moo
     * @param username
     * @return
     * @return SupplierVO
     * @since JDK 1.6
     * @Description
     */
	public SupplierVO findSupplierByUserName(String username);
    
    /**
     * 
     * @date 2015年12月22日
     * @author lby
     * @param companyname
     * @return
     * @return SupplierVO
     * @since JDK 1.6
     * @Description
     */
	public SupplierVO findSupplierByCompanyName(String companyname);
	
	/**
	 * 
	 * @date 2015年6月2日
	 * @author moo
	 * @param supplierName
	 * @param ip
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierLastLogTime(
			@Param(value="supplierName") String supplierName, 
			@Param(value="ip") String ip);

	/**
	 * 
	 * @date 2015年6月2日
	 * @author moo
	 * @param supplierDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierInfo(SupplierDTO supplierDTO);

	/**
	 * 
	 * @date 2015年6月15日
	 * @author moo
	 * @param supplierDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierPwd(SupplierDTO supplierDTO);
	
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<SupplierCategoryVO> findSupplierCategory(SupplierCategoryDTO supplierCategoryDTO);
	
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<SupplierCategoryVO> findChildSupplierCategory(String mainID);
	
	/**
	 * 
	 * @date 2015年7月8日
	 * @author yuanyuan
	 * @param supplierCategoryDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public SupplierCategoryVO findSupplierCategoryByMainID(SupplierCategoryDTO supplierCategoryDTO);
	
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param supplierCategoryDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void saveBusinClass(SupplierCategoryDTO supplierCategoryDTO);
	
	/**
	 * 
	 * @date 2015年7月8日
	 * @author yuanyuan
	 * @param mainID
	 * @since JDK 1.6
	 * @Description
	 */
	public void deleteBusinClass(String mainID);
	
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param supplierCategoryDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void editSupplierCategory(SupplierCategoryDTO supplierCategoryDTO);
}
