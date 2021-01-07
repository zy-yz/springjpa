package com.peppers.exam.interceptor.handler;

import com.peppers.exam.utils.SecurityUserUtil;
import com.peppers.exam.utils.page.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author peppers
 * @description 认证成功的处理
 * @since 2021/1/7
 **/
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseResult respBean = ResponseResult.success("登录成功!", SecurityUserUtil.getCurrentUser());
        new PeppersWebMvcWrite().writeToWeb(response, respBean);
        System.out.println("登录成功!");
    }
}
