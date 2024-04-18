package com.alibaba.edas.test.portal;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public class JsonResp {
    private boolean success;
    private Object data;
    private String errorMsg;
    public JsonResp() {
    }

    public JsonResp(boolean success, Object data, String errorMsg) {
        this.success = success;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public static JsonResp newResp(boolean success, Object data, String errorMsg) {
        return new JsonResp(success, data, errorMsg);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
