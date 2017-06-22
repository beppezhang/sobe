package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 优惠券批次
 */
@Alias("couponPromotionVO")
public class CouponPromotionVO {

    private String mainID;

    private String name;

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
