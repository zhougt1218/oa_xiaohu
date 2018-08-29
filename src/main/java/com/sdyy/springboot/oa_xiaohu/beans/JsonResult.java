package com.sdyy.springboot.oa_xiaohu.beans;

/**
 * @author xiaohu
 * @createDate 2018-08-06 11:26
 */
public class JsonResult {
    private Integer ResultCode;
    private String ResultMsg;
    private Object ResultObj;
    private Boolean State;

    public Integer getResultCode() {
        return ResultCode;
    }

    public void setResultCode(Integer resultCode) {
        ResultCode = resultCode;
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public void setResultMsg(String resultMsg) {
        ResultMsg = resultMsg;
    }

    public Object getResultObj() {
        return ResultObj;
    }

    public void setResultObj(Object resultObj) {
        ResultObj = resultObj;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public  JsonResult(){}
    public JsonResult(Integer resultCode, String resultMsg, Object resultObj, Boolean state) {
        ResultCode = resultCode;
        ResultMsg = resultMsg;
        ResultObj = resultObj;
        State = state;
    }

    public JsonResult(Integer resultCode, String resultMsg, Boolean state) {
        ResultCode = resultCode;
        ResultMsg = resultMsg;
        State = state;
    }


    @Override
    public String toString() {
        return "JsonResult{" +
                "ResultCode=" + ResultCode +
                ", ResultMsg='" + ResultMsg + '\'' +
                ", ResultObj=" + ResultObj +
                ", State=" + State +
                '}';
    }
}
