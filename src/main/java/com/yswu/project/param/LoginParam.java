package com.yswu.project.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 登陆参数校验
 *
 * @Author yswu3
 * @Date 2019/10/31.
 */
public class LoginParam {
    @NotEmpty(message = "姓名不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "姓名只能是英文字母或者数字")
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码只能是英文字母或者数字")
    @Length(min = 6, message = "密码长度不能小于6位")
    @Length(max = 20, message = "密码长度不能超过20位")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
