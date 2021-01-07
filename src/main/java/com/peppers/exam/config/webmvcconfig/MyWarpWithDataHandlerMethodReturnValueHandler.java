//package com.peppers.exam.config.webmvcconfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author peppers
// * @description 自定义自己的return的处理类，我们直接继承RequestResponseBodyMethodProcessor，这样父类里面的方法我们直接使用就可以了
// * @since 2020/12/30
// **/
//@Component
//public class MyWarpWithDataHandlerMethodReturnValueHandler extends RequestResponseBodyMethodProcessor implements HandlerMethodReturnValueHandler {
//
//    //参考父类RequestResponseBodyMethodProcessor的做法
//    @Autowired
//    public MyWarpWithDataHandlerMethodReturnValueHandler(List<HttpMessageConverter<?>> converters) {
//        super(converters);
//    }
//
//    /**只处理需要包装的注解的方法*/
//    @Override
//    public boolean supportsReturnType(MethodParameter returnType) {
//        return returnType.hasMethodAnnotation(WarpWithData.class);
//    }
//
//    /**将返回结果包装一层Data*/
//    @Override
//    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws IOException, HttpMediaTypeNotAcceptableException {
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",returnValue);
//        super.handleReturnValue(res,methodParameter,modelAndViewContainer,nativeWebRequest);
//
//    }
//
//}
