package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.ExpressFormatDTO;
import com.kpluswebup.web.vo.ExpressFormatVO;

public interface ExpressFormatDAO {

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param expressFormatDTO
     * @since JDK 1.6
     * @Description
     */
    public void addExpressFormat(ExpressFormatDTO expressFormatDTO);

    /**
     * 不分页查询
     * 
     * @date 2014年11月22日
     * @author zhuhp
     * @param expressFormatDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ExpressFormatVO> findExpressFormat(ExpressFormatDTO expressFormatDTO);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param expressFormatDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateExpressFormatByMainID(ExpressFormatDTO expressFormatDTO);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteExpressFormatByMainID(String mainID);

    /**
     * 单个查询
     * 
     * @date 2014年11月22日
     * @author zhuhp
     * @param expressFormatDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ExpressFormatVO findExpressFormatByMainID(String mainID);

    /**
     * 根据物流id查询物流模板
     * 
     * @date 2014年12月18日
     * @author wanghehua
     * @param expressID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ExpressFormatVO findExpressFormatByExpressID(String expressID);
}
