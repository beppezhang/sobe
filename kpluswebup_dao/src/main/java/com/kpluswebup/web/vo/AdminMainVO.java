package com.kpluswebup.web.vo;

import java.util.List;

import com.kpuswebup.comom.util.DateUtil;

/**
 * 管理后台首页信息
 * 
 * @author zhuhp
 */
public class AdminMainVO {

    private List<OrderVolumeVO>       orderVolume;       // 7内天成成交量

    private List<MemberCountVO>       memberCount;       // 7内天会员注册数量

    private List<ProductStatisticsVO> productStatistics; // 商品排行榜

    private List<CustomerVO>          customerStatistics; // 会员排行榜

    private String                    dateTime;          // 7天的时间，以，分割

    private String                    orderVolumes;      // 以，分割

    private String                    memberCounts;      // 以，分割s

    public List<OrderVolumeVO> getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(List<OrderVolumeVO> orderVolume) {
        this.orderVolume = orderVolume;
    }

    public List<MemberCountVO> getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(List<MemberCountVO> memberCount) {
        this.memberCount = memberCount;
    }

    public List<ProductStatisticsVO> getProductStatistics() {
        return productStatistics;
    }

    public void setProductStatistics(List<ProductStatisticsVO> productStatistics) {
        this.productStatistics = productStatistics;
    }

    public List<CustomerVO> getCustomerStatistics() {
        return customerStatistics;
    }

    public void setCustomerStatistics(List<CustomerVO> customerStatistics) {
        this.customerStatistics = customerStatistics;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderVolumes() {
        return orderVolumes;
    }

    public void setOrderVolumes(List<OrderVolumeVO> orderVolume) {
        StringBuilder orderVolumes = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            String time = DateUtil.getDateAgo(i);
            String volume = "0";
            for (OrderVolumeVO orderVolumeVO : orderVolume) {
                if (time.equals(orderVolumeVO.getDateTime())) {
                    volume =orderVolumeVO.getAmount() ;
                    break;
                }
            }
            orderVolumes.append(volume);
            if (i < 7) {
                orderVolumes.append(",");
            }
        }
        this.orderVolumes = orderVolumes.toString();
    }

    public String getMemberCounts() {
        return memberCounts;
    }

    public void setMemberCounts(List<MemberCountVO>       memberCount) {
        StringBuilder memberCounts = new StringBuilder();
        for (int i = 7; i >= 1; i--) {
            String time = DateUtil.getDateAgo(i);
            String count = "0";
            for (MemberCountVO memberCountVO : memberCount) {
                if (time.equals(memberCountVO.getDateTime())) {
                    count =memberCountVO.getCount();
                    break;
                }
            }
            memberCounts.append(count);
            if (i <= 7) {
                memberCounts.append(",");
            }
        }
        this.memberCounts = memberCounts.toString();
    }

}
