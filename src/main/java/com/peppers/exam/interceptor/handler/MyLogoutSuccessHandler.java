package com.peppers.exam.interceptor.handler;

import com.peppers.exam.utils.page.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author peppers
 * @description 注销登录处理
 * @since 2021/1/7
 **/
public class MyLogoutSuccessHandler  implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseResult respBean = ResponseResult.success("注销成功!");
        new PeppersWebMvcWrite().writeToWeb(response, respBean);
        System.out.println("注销成功!");
    }
}
