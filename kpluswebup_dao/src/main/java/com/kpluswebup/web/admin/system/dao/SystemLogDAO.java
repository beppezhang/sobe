package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.SystemLogVO;

public interface SystemLogDAO {

    /**
     * 分页查询操作日志
     * 
     * @date 2014年11月24日
     * @author wanghehua
     * @param systemLogDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemLogVO> findSystemLogByPagination(SystemLogDTO systemLogDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月24日
     * @author wanghehua
     * @param systemLogDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSystemLogCount(SystemLogDTO systemLogDTO);

    /**
     * @date 2014年12月8日
     * @author zhuhp
     * @param systemLogDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertSystemLog(SystemLogDTO systemLogDTO);
}
