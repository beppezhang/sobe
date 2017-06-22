package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("memberCountVO")
public class MemberCountVO {

    private String count;

    private String dateTime;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
