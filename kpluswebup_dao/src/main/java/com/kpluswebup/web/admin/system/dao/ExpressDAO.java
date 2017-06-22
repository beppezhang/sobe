package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.ExpressDTO;
import com.kpluswebup.web.vo.ExpressVO;

public interface ExpressDAO {

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @param expressDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addExpress(ExpressDTO expressDTO);

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @param expressDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long updateExpressByMainID(ExpressDTO expressDTO);

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteExpressByMainID(String mainID);

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ExpressVO> findALlExpress();

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ExpressVO findExpressByMainID(String mainID);
    
    
    /**
     * @author zhoulei 
     * @param name
     * @return
     */
    public ExpressVO findExpressByName(String name);
    /**
     * 查看默认物流公司
     * @date 2015年1月14日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ExpressVO findExpressIsDefault();
    
    /**
     * 修改为非默认
     * @date 2015年1月14日
     * @author wanghehua
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void updateExpressDefault(String mainID);

	/**
	 * 物流公司查询不带分页的
	 * @param expressDTO
	 * @return
	 */
	public List<ExpressVO> findALlExpressBySupplierID(String supplierID);
	
	/**
	 * 物流公司查询带分页的
	 * @param expressDTO
	 * @return
	 */
	public List<ExpressVO> findExpressPageBySupplierID(ExpressDTO expressDTO);

	/**
	 * 物流公司查询总条数
	 * @param expressDTO
	 * @return
	 */
	public long findExpressCountBySupplierID(ExpressDTO expressDTO);

	public ExpressVO findExpress(ExpressDTO expressDTO);

 }
