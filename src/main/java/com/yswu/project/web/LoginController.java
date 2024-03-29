package com.yswu.project.web;


import com.yswu.project.comm.WebConfiguration;
import com.yswu.project.model.UserInfo;
import com.yswu.project.param.LoginParam;
import com.yswu.project.param.RegisterParam;
import com.yswu.project.repository.UserInfoRepository;
import com.yswu.project.util.UserInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 注册登陆类
 *
 * @Author yswu3
 * @Date 2019/10/31.
 */
@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if (StringUtils.isEmpty(id)) {
            return "login";
        } else {
            return "success";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(WebConfiguration.LOGIN_KEY);
        request.getSession().removeAttribute(WebConfiguration.LOGIN_USER);
        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/register")
    public String hello(@Valid RegisterParam registerParam, BindingResult result, ModelMap model) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "register";
        }
        UserInfo u = userInfoRepository.findByUserName(registerParam.getUserName());
        if (u != null) {
            model.addAttribute("errorMsg", "用户已存在!");
            return "register";
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registerParam, userInfo);
        userInfo.setPassword(UserInfoUtil.getTransedPwd(userInfo.getPassword()));
        try {
            userInfo.setIdCardNum(UserInfoUtil.getDesensitizationResult(userInfo.getIdCardNum()));
            userInfo.setMobile(UserInfoUtil.getDesensitizationResult(userInfo.getMobile()));
        } catch (Exception e) {
            logger.error(userInfo.toString(), e);
            model.addAttribute("errorMsg", "手机号或身份证号格式异常！");
            return "register";
        }
        userInfoRepository.save(userInfo);
        return "login";
    }

    @RequestMapping("/login")
    public String login(@Valid LoginParam loginParam, BindingResult result, ModelMap model, HttpServletRequest request) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
        UserInfo userInfo = userInfoRepository.findByUserName(loginParam.getLoginName());
        if (userInfo == null
                || !UserInfoUtil.checkpw(loginParam.getPassword(), userInfo.getPassword())) {
            model.addAttribute("errorMsg", "无效的用户名或密码！");
            return "login";
        }
        request.getSession().setAttribute(WebConfiguration.LOGIN_KEY, userInfo.getUserName());
        request.getSession().setAttribute(WebConfiguration.LOGIN_USER, userInfo);
        return "success";
    }
}
