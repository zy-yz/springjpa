package com.peppers.exam.config.webmvcconfig;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/30
 **/
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private MyWarpWithDataHandlerMethodReturnValueHandler myWarpWithDataHandlerMethodReturnValueHandler;

    /**把我们自定义的myWarpWithDataHandlerMethodReturnValueHandler加入handlers里面即可*/
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

        handlers.add(myWarpWithDataHandlerMethodReturnValueHandler);

    }

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**由于HandlerMethodReturnValueHandler处理的优先级问题，我们通过如下方法，把我们自定义的myWarpWithDataHandlerMethodReturnValueHandler放到第一个*/
    @PostConstruct
    public void init() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = Lists.newArrayList(myWarpWithDataHandlerMethodReturnValueHandler);
        //取出原始列表，重新覆盖进去；
        returnValueHandlers.addAll(requestMappingHandlerAdapter.getReturnValueHandlers());

        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);

    }

}

