package com.kpuswebup.comom.util;

import javax.xml.bind.annotation.XmlElement;

public class JsonResult {

    private String  code;

    private Object  result;

    private String  errorMsg;

    private boolean isSuccess = true;

     JsonResult(){
        result = "";
        this.code = ResultCode.NORMAL.getCode();
        this.errorMsg = ResultCode.NORMAL.getMsg();
    }

    public JsonResult(ResultCode resultCode){
        if (resultCode == null) {
            this.code = ResultCode.ERROR_SYSTEM.getCode();
            this.errorMsg = ResultCode.ERROR_SYSTEM.getMsg();
        } else {
            this.code = resultCode.getCode();
            this.errorMsg = resultCode.getMsg();
        }
        if (!ResultCode.NORMAL.getCode().equals(code)) {
            isSuccess = false;
        }
        this.result = "";
    }

    public JsonResult(Object result){
        this.code = ResultCode.NORMAL.getCode();
        this.errorMsg = ResultCode.NORMAL.getMsg();
        this.result = result;
        if (!ResultCode.NORMAL.getCode().equals(code)) {
            isSuccess = false;
        }
    }

    public JsonResult(String code, Object result, String errorMsg, boolean isSuccess){
        super();
        this.code = code;
        this.result = result;
        this.errorMsg = errorMsg;
        if (!ResultCode.NORMAL.getCode().equals(code)) {
            isSuccess = false;
        }
    }

    public JsonResult(Object result, ResultCode resultCode){
        super();
        this.code = resultCode.getCode();
        this.result = result;
        this.errorMsg = resultCode.getMsg();
        if (!ResultCode.NORMAL.getCode().equals(code)) {
            isSuccess = false;
        }
    }

    public static JsonResult create(ResultCode resultCode) {
        return new JsonResult(resultCode);
    }

    public static JsonResult create() {
        return new JsonResult();
    }

    @XmlElement
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @XmlElement
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @XmlElement
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
