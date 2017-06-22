package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.ExpressDTO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.SupplierVO;

public interface ExpressService {

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
    public Boolean deleteExpressByMainID(String mainID);

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
     * @param name
     * @param contactPerson
     * @param mobile
     * @param code
     * @param isDefault
     * @param description
     * @since JDK 1.6
     * @Description
     */
    public void addExpress(String name, String contactPerson, String mobile, String code, String isDefault,
                           String description,String currentOperator);

    /**
     * @date 2014年11月20日
     * @author zhuhp
     * @param mainID
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
     * @date 2014年11月20日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param contactPerson
     * @param mobile
     * @param code
     * @param isDefault
     * @param description
     * @since JDK 1.6
     * @Description
     */
    public void editExpress(String mainID, String name, String contactPerson, String mobile, String code,
                            String isDefault, String description,String currentOperator);

	public void addExpress(String name, String contactPerson, String mobile,
			String code, String isDefault, String description,
			String currentOperator, SupplierVO supplierVO);

//	public List<ExpressVO> findALlExpressBySupplierID(ExpressDTO expressDTO, String mainID);

	List<ExpressVO> findALlExpressBySupplierID(String supplierID);
	
	List<ExpressVO> findExpressPageBySupplierID(ExpressDTO expressDTO);

	public ExpressVO findExpressByName(ExpressDTO expressDTO);
}
