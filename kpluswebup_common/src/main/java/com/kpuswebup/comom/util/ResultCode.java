package com.kpuswebup.comom.util;

public enum ResultCode {
    /**
     * 正常
     */
    NORMAL(0, "NORMAL", "正常", 0),

    /** ========== 系统错误码 ========== **/

    /**
     * 系统错误
     */
    ERROR_SYSTEM(10001, "ERROR_SYSTEM", "系统错误！", 1),
    
    /**
     * 系统错误
     */
    ERROR_OUT_SHOPCART(10005, "ERROR_SYSTEM", "超过了该商品最大库存！", 1),

    /**
     * 系统错误
     */
    ERROR_SYSTEM_PRODUCNT(10001, "ERROR_SYSTEM_PRODUCNT", "商品已经删除或已下架！", 1),
    /**
     * 每天获取短信次数限制
     */
    ERROR_SMSCODECOUNT(2, "ERROR_SMSCODECOUNT", "每天获取短信次数限制！", 2),
    
    /**
     * 每个号码获取时间限制
     */
    ERROR_SMSCODEMOBILE(3, "ERROR_SMSCODEMOBILE", "每个号码获取时间限制！", 3),

    /**
     * 未取货
     */
    ERROR_UNPICKUP(1000, "ERROR_UNPICKUP", "订单含有尚未取货商品！", 3),

    /**
     * 未填写序列号
     */
    ERROR_UNWRITESERIALISENO(1001, "ERROR_UNWRITESERIALISENO", "订单含有尚未填写序列号的商品！", 3),
    /**
     * 未付款
     */
    ERROR_UNPAY(2000, "ERROR_UNPAY", "订单尚未付款！", 3),

    /**
     * whh 未登录
     */
    ERROR_LOGIN(2, "ERROR_LOGIN", "未登录！", 2),

    /**
     * whh 已收藏
     */
    ERROR_FAVORITE(3, "ERROR_FAVORITE", "已收藏！", 3),

    /**
     * whh 未购买
     */
    ERROR_BUY(4, "ERROR_BUY", "未购买！", 4),

    /**
     * 数据库查询失败
     */
    ERROR_SYSTEM_DATABASE_QUERY(10011, "ERROR_SYSTEM_DATABASE_QUERY", "数据库查询失败！", 1),

    /**
     * 数据库存储失败
     */
    ERROR_SYSTEM_DATABASE_INSERT(10012, "ERROR_SYSTEM_DATABASE_INSERT", "数据库存储失败！", 1),

    /**
     * 数据库更新失败
     */
    ERROR_SYSTEM_DATABASE_UPDATE(10013, "ERROR_SYSTEM_DATABASE_UPDATE", "数据库更新失败！", 1),

    /**
     * 数据库事务执行失败
     */
    ERROR_SYSTEM_DATABASE_TRANSACTION(10014, "ERROR_SYSTEM_DATABASE_TRANSACTION", "数据库事务执行失败！", 1),

    /**
     * 数据库查询失败
     */
    ERROR_SYSTEM_DATABASE_QUERY_NULL(10015, "ERROR_SYSTEM_DATABASE_QUERY", "数据库查询值为空！", 1),

    /**
     * 数据对象转换失败
     */
    ERROR_SYSTEM_DATAOBJECT_TRANSFORM(10016, "ERROR_SYSTEM_DATAOBJECT_TRANSFORM", "数据对象转换失败！", 1),

    /**
     * 请求出错，请检查您传入的参数格式是否正确
     */
    ERROR_SYSTEM_PARAM_FORMAT(10017, "ERROR_SYSTEM_PARAM_FORMAT", "请求出错，请检查您传入的参数格式是否正确", 1),

    /**
     * 时间格式不正确
     */
    ERROR_SYSTEM_DATETIME_PARAM_FORMAT(10018, "ERROR_SYSTEM_DATETIME_PARAM_FORMAT", "时间格式不正确！", 1),
    
    /**
     * 资源被关联使用
     */
    ERROR_RESOURCE_RELATED_USE(20000, "ERROR_RESOURCE_RELATED_USE", "数据已被关联使用，不能删除！", 1);
    
    

    private String msg;

    private int    code;

    private String codeStr;

    /**
     * 错误类型（0:一般结果码；1：系统级的错误；2：应用级的错误）
     */
    private int    type;

    public String getCode() {
        return String.valueOf(this.code);
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCodeStr() {
        return this.codeStr;
    }

    public int getType() {
        return this.type;
    }

    private ResultCode(int code, String codeStr, String msg, int type){
        this.msg = msg;
        this.code = code;
        this.codeStr = codeStr;
        this.type = type;
    }

}
