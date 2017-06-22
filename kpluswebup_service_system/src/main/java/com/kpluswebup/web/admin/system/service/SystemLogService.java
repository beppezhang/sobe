package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.SystemLogVO;

public interface SystemLogService {

    /**
     * 分页查找操作日志
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
     * @date 2014年12月8日
     * @author zhuhp
     * @param systemLogDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean insertSystemLog(SystemLogDTO systemLogDTO);
}
