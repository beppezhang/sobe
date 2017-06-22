package com.kpluswebup.web.admin.system.service;

import com.kpluswebup.web.vo.AdminMainVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.StatisticsOrderStstusVO;

public interface AdminSystemService {

    /**
     * 后台首页（近7天交易额、近7天新增会员、近7天商品排行榜）
     * 
     * @date 2014年12月3日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public AdminMainVO findAdminMain();

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
