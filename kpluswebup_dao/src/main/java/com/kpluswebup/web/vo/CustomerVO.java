package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("customerVO")
public class CustomerVO implements Serializable {

    private static final long serialVersionUID  = -1510425069669614367L;

    private String            mainID;                                   // 会员编号

    private String            username;

    private String            password;

    private Date              lastLoginTime;

    private String            nick;

    private String            name;

    private String            avatar;

    private String            email;

    private String            mobile;

    private Double            scoreIntotal;                             // 累计积分

    private Double            amount;                                   // 账户余额

    private Double            score;                                    // 可用积分

    private String            gradeID;                                  // 会员等级编号

    private Integer           sex;                                      // 1：男2：女

    private Date              birthday;

    private Integer           age;

    private String            countryID;
    
    private String 			  countryName;

    private String            provinceID;
    

    private String            cityID;

    private String            districtID;

    private Integer           isDelete;

    private Integer           status;                                   // 1:正常 2：锁定

    private Integer           loginTimes;//登录错误次数
    private Date              updateTime;

    private String            lastLoginIP;

    private String            registerIP;

    private String            telephone;

    private String            address;

    private String            zip;

    private Date              createTime;

    private String            gradeName;

    private String            provinceName;

    private String            cityName;

    private String            districtName;

    private Double            minscore;

    private Double            maxscore;

    private Date              mincreateTime;

    private Date              maxcreateTime;

    private Integer           minage;

    private Integer           maxage;

    private Double            minscoreIntotal;

    private Double            maxscoreIntotal;

    private String            orderAmount;

    private Double            orderTotalCount;                          // 订单金额合计

    private String            usermemberID;                             // 用户下级会员ID

    private String            referee;                                  // 推荐人

    private Integer           shoppingcartCount = 0;                    // 购物车数量

    private String            openid;                                   // 微信id

    private String            qrcodeurl;                                // 邀请会员二维码

    private String            companyName; //公司名称
    
    private String            openingBank; //开户银行
    
    private String            accountNumber; //银行帐号
    
    private String            bankAccount; //银行帐户
    
    private String            picURL1;      //营业执照
    
    private String            picURL2;      //税务登记证
    
    private String            picURL3;      //组织机构代码
    
    private String            invoice;      //发票信息
    
    public String getInvoice() {
		return invoice;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Double getOrderTotalCount() {
        return orderTotalCount;
    }

    public void setOrderTotalCount(Double orderTotalCount) {
        this.orderTotalCount = orderTotalCount;
    }

    public String getUsermemberID() {
        return usermemberID;
    }

    public void setUsermemberID(String usermemberID) {
        this.usermemberID = usermemberID;
    }

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getScoreIntotal() {
        return scoreIntotal;
    }

    public void setScoreIntotal(Double scoreIntotal) {
        this.scoreIntotal = scoreIntotal;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Double getMinscore() {
        return minscore;
    }

    public void setMinscore(Double minscore) {
        this.minscore = minscore;
    }

    public Double getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(Double maxscore) {
        this.maxscore = maxscore;
    }

    public Date getMincreateTime() {
        return mincreateTime;
    }

    public void setMincreateTime(Date mincreateTime) {
        this.mincreateTime = mincreateTime;
    }

    public Date getMaxcreateTime() {
        return maxcreateTime;
    }

    public void setMaxcreateTime(Date maxcreateTime) {
        this.maxcreateTime = maxcreateTime;
    }

    public Integer getMinage() {
        return minage;
    }

    public void setMinage(Integer minage) {
        this.minage = minage;
    }

    public Integer getMaxage() {
        return maxage;
    }

    public void setMaxage(Integer maxage) {
        this.maxage = maxage;
    }

    public Double getMinscoreIntotal() {
        return minscoreIntotal;
    }

    public void setMinscoreIntotal(Double minscoreIntotal) {
        this.minscoreIntotal = minscoreIntotal;
    }

    public Double getMaxscoreIntotal() {
        return maxscoreIntotal;
    }

    public void setMaxscoreIntotal(Double maxscoreIntotal) {
        this.maxscoreIntotal = maxscoreIntotal;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public Integer getShoppingcartCount() {
        return shoppingcartCount;
    }

    public void setShoppingcartCount(Integer shoppingcartCount) {
        this.shoppingcartCount = shoppingcartCount;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl;
    }

    
    public String getCompanyName() {
        return companyName;
    }

    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    public String getPicURL1() {
        return picURL1;
    }

    
    public void setPicURL1(String picURL1) {
        this.picURL1 = picURL1;
    }

    
    public String getPicURL2() {
        return picURL2;
    }

    
    public void setPicURL2(String picURL2) {
        this.picURL2 = picURL2;
    }

    
    public String getPicURL3() {
        return picURL3;
    }

    
    public void setPicURL3(String picURL3) {
        this.picURL3 = picURL3;
    }


}
