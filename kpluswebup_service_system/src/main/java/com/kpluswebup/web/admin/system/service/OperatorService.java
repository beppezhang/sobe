package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.OperatorDTO;
import com.kpluswebup.web.vo.OperatorVO;

public interface OperatorService {

    /**
     * @date 2014年11月28日
     * @author zhuhp
     * @param username
     * @param password
     * @param ValidateCode
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean isLogin(String userName, String password, String validateCode);

    /**
     * 分页查找用户
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param operatorDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<OperatorVO> findOperatorByPagination(OperatorDTO operatorDTO);

    /**
     * 添加用户
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param operatorDTO
     * @since JDK 1.6
     * @Description
     */
    public void addOperator(OperatorDTO operatorDTO);

    /**
     * 根据id查找用户
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public OperatorVO findOperatorByMainID(String mainID);

    /**
     * @date 2014年12月9日
     * @author zhuhp
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    public OperatorVO findOperatorByUserName(String userName);

    /**
     * 修改用户
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param operatorDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateOperator(OperatorDTO operatorDTO);

    /**
     * 删除用户
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteOperatorByMainID(String mainID);

    /**
     * 锁定/解锁
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param operatorDTO
     * @since JDK 1.6
     * @Description
     */
    public void changeOperatorStatus(OperatorDTO operatorDTO);
    
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
