package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.OperatorDTO;
import com.kpluswebup.web.vo.OperatorVO;

public interface OperatorDAO {

	/**
	 * 分页查询用户
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param OperatorDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<OperatorVO> findOperatorByPagination(OperatorDTO operatorDTO);
	
	/**
	 * 查询总条数
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param OperatorDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Long findOperatorCount(OperatorDTO operatorDTO);
	
	/**
	 * 添加用户
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param OperatorDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertOperator(OperatorDTO operatorDTO);
	
	/**
	 * 修改用户
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param OperatorDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateOperator(OperatorDTO operatorDTO);
	
	/**
	 * 根据id查找用户
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public OperatorVO findOperatorByMainID(String mainID);
	
	/**
	 * 删除用户
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Integer deleteOperatorByMainID(String mainID);

    /**
     * @date 2014年11月28日
     * @author zhuhp
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    public OperatorVO findOperatorByUserName(String userName);
    
    /**
     * 修改密码
     * @date 2014年12月9日
     * @author liulihui
     * @param operatorDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateOperatorPwd(OperatorDTO operatorDTO);

}
