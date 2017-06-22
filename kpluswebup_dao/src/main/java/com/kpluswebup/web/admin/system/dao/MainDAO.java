package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.MemberCountVO;
import com.kpluswebup.web.vo.OrderVolumeVO;
import com.kpluswebup.web.vo.ProductStatisticsVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.StatisticsOrderStstusVO;

/**
 * 管理中心首页相关sql
 * 
 * @author zhuhp
 */
public interface MainDAO {

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @param dateAgo
     * @param dateAgo2
     * @return
     * @since JDK 1.6
     * @Description
     */

    public List<OrderVolumeVO> findOrderVolume(@Param("startTime")
    String startTime, @Param("endTime")
    String endTime);

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @param startTime
     * @param endTime
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<MemberCountVO> findMemberCount(@Param("startTime")
    String startTime, @Param("endTime")
    String endTime);

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @param startTime
     * @param endTime
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductStatisticsVO> findProductStatistics(@Param("startTime")
    String startTime, @Param("endTime")
    String endTime);

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @param startTime
     * @param endTime
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CustomerVO> findCustomerStatistics(@Param("startTime")
    String startTime, @Param("endTime")
    String endTime);

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO findStatisticsInfo();

    /**
     * @date 2014年12月3日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsOrderStstusVO findStatisticsOrderStstus();

}
