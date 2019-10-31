package com.yswu.project.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Author yswu3
 * @Date 2019/10/31.
 */
public class RegisterParam {
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 20, message = "姓名长度不能超过20")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    @Length(max = 20, message = "密码长度不能超过20位")
    private String password;

    @NotEmpty(message = "手机号不能为空")
    @Length(max = 20, message = "手机号长度不能超过20位")
    private String mobile;

    @NotEmpty(message = "身份证号不能为空")
    @Length(max = 30, message = "身份证号长度不能超过30位")
    private String idCardNum;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
}
