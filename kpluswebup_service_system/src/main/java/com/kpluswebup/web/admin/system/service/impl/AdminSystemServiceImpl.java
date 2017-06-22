package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.MainDAO;
import com.kpluswebup.web.admin.system.service.AdminSystemService;
import com.kpluswebup.web.vo.AdminMainVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.MemberCountVO;
import com.kpluswebup.web.vo.OrderVolumeVO;
import com.kpluswebup.web.vo.ProductStatisticsVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.StatisticsOrderStstusVO;
import com.kpuswebup.comom.util.DateUtil;

@Service
public class AdminSystemServiceImpl implements AdminSystemService {

    @Autowired
    private MainDAO mainDAO;

    public AdminMainVO findAdminMain() {
        String startTime = DateUtil.getDateAgo(7);
        String endTime = DateUtil.getDateAgo(0);
        AdminMainVO adminMainVO = new AdminMainVO();

        List<CustomerVO> customerStatisticsList = mainDAO.findCustomerStatistics(startTime, endTime);
        adminMainVO.setCustomerStatistics(customerStatisticsList);

        List<MemberCountVO> memberCountList = mainDAO.findMemberCount(startTime, endTime);
        adminMainVO.setMemberCounts(memberCountList);

        List<OrderVolumeVO> orderVolumeList = mainDAO.findOrderVolume(startTime, endTime);
        adminMainVO.setOrderVolumes(orderVolumeList);

        List<ProductStatisticsVO> productStatisticsList = mainDAO.findProductStatistics(startTime, endTime);
        adminMainVO.setProductStatistics(productStatisticsList);

        StringBuilder dateTime = new StringBuilder();
        for (int i = 7; i >= 1; i--) {
            dateTime.append("'" + DateUtil.getDateAgo(i, "dd") + "'");
            if (i <= 7) {
                dateTime.append(",");
            }
        }
        adminMainVO.setDateTime(dateTime.toString());
        return adminMainVO;
    }

    public StatisticsInfoVO findStatisticsInfo() {
        return mainDAO.findStatisticsInfo();
    }

    public StatisticsOrderStstusVO findStatisticsOrderStstus() {

        return mainDAO.findStatisticsOrderStstus();
    }
}
