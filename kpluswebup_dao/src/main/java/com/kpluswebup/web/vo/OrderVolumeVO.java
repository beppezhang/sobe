package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("orderVolumeVO")
public class OrderVolumeVO {

    private String amount;

    private String dateTime;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
