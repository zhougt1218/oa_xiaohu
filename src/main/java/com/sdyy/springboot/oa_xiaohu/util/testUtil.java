package com.sdyy.springboot.oa_xiaohu.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author xiaohu
 * @createDate 2018-08-28 17:00
 */
public class testUtil {
    public static void main(String[] args) {


    //设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
    //云通信产品-短信API服务产品名称（短信产品名固定，无需修改）
    final String product = "Dysmsapi";
    //云通信产品-短信API服务产品域名（接口地址固定，无需修改）
    final String domain = "dysmsapi.aliyuncs.com";
    //此处需要替换成开发者自己的AK信息
    final String accessKeyId = "LTAIi9ZMOv2k6DoO";
    final String accessKeySecret = "PuXb09UYoPgg2nLWeGeoSGyO1dTcQF";
    //初始化ascClient
    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
    //组装请求对象
    QuerySendDetailsRequest request = new QuerySendDetailsRequest();
    //必填-号码
        request.setPhoneNumber("18615402286");
    //可选-调用发送短信接口时返回的BizId
        request.setBizId("1234567^8901234");
    //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate("20170513");
    //必填-页大小
        request.setPageSize(10L);
    //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
    //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = null;
        try {
            querySendDetailsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //获取返回结果
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        //代表请求成功
    }
    }
}
