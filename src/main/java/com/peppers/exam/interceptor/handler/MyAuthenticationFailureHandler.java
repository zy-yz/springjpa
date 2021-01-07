package com.peppers.exam.interceptor.handler;

import com.peppers.exam.utils.page.ResponseResult;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author peppers
 * @description 认证失败的处理
 * @since 2021/1/7
 **/
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseResult respBean;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            respBean = ResponseResult.error("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            respBean = ResponseResult.error("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean = ResponseResult.error("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            respBean = ResponseResult.error("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            respBean = ResponseResult.error("账户被禁用，请联系管理员!");
        } else {
            respBean = ResponseResult.error("登录失败!");
        }
        //response.setStatus(401);
        new PeppersWebMvcWrite().writeToWeb(response, respBean);
    }
}
