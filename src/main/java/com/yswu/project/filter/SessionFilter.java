package com.yswu.project.filter;

import com.yswu.project.comm.WebConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @Author yswu3
 * @Date 2019/11/1.
 */
public class SessionFilter implements Filter{
    protected Logger log = LoggerFactory.getLogger(SessionFilter.class);
    // 不登陆也可以访问的资源
    private static Set<String> GreenUrlSet = new HashSet<String>();

    public void doFilter(ServletRequest srequest, ServletResponse sresponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        String uri = request.getRequestURI();
        sresponse.setCharacterEncoding("UTF-8");//设置响应编码格式
        sresponse.setContentType("text/html;charset=UTF-8");//设置响应编码格式
        if (uri.endsWith(".js")
                || uri.endsWith(".css")
                || uri.endsWith(".jpg")
                || uri.endsWith(".gif")
                || uri.endsWith(".png")
                || uri.endsWith(".ico")) {
            log.debug("security filter, pass, " + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
            return;
        }

        if (GreenUrlSet.contains(uri) ) {
            log.debug("security filter, pass, " + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
            return;
        }
        String userName=(String)request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if(StringUtils.isEmpty(userName)){
            String html = "<script type=\"text/javascript\">window.location.href=\"/toLogin\"</script>";
            sresponse.getWriter().write(html);
        }else {
            filterChain.doFilter(srequest, sresponse);
        }
    }

    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
        GreenUrlSet.add("/toRegister");
        GreenUrlSet.add("/toLogin");
        GreenUrlSet.add("/login");
        GreenUrlSet.add("/loginOut");
        GreenUrlSet.add("/register");
    }
}
