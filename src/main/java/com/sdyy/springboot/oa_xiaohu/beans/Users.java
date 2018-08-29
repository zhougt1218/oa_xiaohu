package com.sdyy.springboot.oa_xiaohu.beans;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author xiaohu
 * @createDate 2018-08-25 9:31
 */
public class Users {
    private  String userId;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String loginTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public Users(String userName, String password, String email, String phone, String loginTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.loginTime = loginTime;
    }

    public Users(){}
}
