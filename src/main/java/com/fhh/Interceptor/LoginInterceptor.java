package com.fhh.Interceptor;

import com.fhh.pojo.User;
import com.fhh.protal.service.TokenService;
import com.fhh.utils.CookieUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录处理拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //前处理,执行Handler之前执行此方法,返回true放行
        //从Cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        //如果没有token,说明是未登录状态,直接放行
        if(StringUtils.isEmpty(token)){
            return true;
        }
        //如果取到token,需要调用系统服务,根据token取用户信息
        YZResult yzResult = tokenService.getUserByToken(token);
        //如果没有取到用户信息,说明登录过期,直接放行
        if(yzResult.getStatus()!=200){
            return true;
        }
        //如果取到用户信息,说明是登录状态,
        User user = (User) yzResult.getData();
        //把用户信息放到request中,只需要在Controller中判断request中是否包含user信息
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //Handler执行之后,返回ModelAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        //返回ModelAndView之后,可以在此处理异常
    }
}
