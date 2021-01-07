package com.peppers.exam.interceptor.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peppers.exam.utils.page.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author peppers
 * @description 页面信息
 * @since 2021/1/7
 **/
public class PeppersWebMvcWrite {

    /**
     * @Description     输出信息到界面
     * @param
     * @return
     */
    public void writeToWeb(HttpServletResponse response, ResponseResult respBean) throws IOException {
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
