package com.module.product.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.exception.ServiceException;
import com.module.product.common.interceptor.ShiroInterceptor;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by on 2017/5/10.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ShiroInterceptor shiroInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器
        registry.addInterceptor(shiroInterceptor).excludePathPatterns("/auth/**", "/resources/**","/error");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //使用FastJSON作为@ResponseBody消息转换器
        FastJsonpHttpMessageConverter4 converter = new FastJsonpHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        //统一的异常处理
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                ModelAndView mv = new ModelAndView();
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    if (null != handlerMethod.getBean().getClass().getAnnotation(RestController.class)
                            || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {//返回JSON格式

                        if (ex instanceof UnauthorizedException) {
                            logger.warn(ex.getMessage());
                        } else if (ex instanceof ServiceException) {
                            logger.debug(ex.getMessage());
                        } else {
                            String message = String.format("调用接口%s出现异常，方法：%s.%s，异常摘要：%s",
                                    request.getRequestURI(),
                                    handlerMethod.getBean().getClass().getName(),
                                    handlerMethod.getMethod().getName(),
                                    ex.getMessage());
                            logger.error(message, ex);
                        }

                        Class<?> returnType = handlerMethod.getMethod().getReturnType();
                        if (returnType == DataTablePageModel.class) {
                            DataTablePageModel pageModel = new DataTablePageModel();
                            pageModel.setError(ex.getMessage());
                            responseResult(response, pageModel);
                        } else {
                            ResponseJsonModel responseJsonModel = new ResponseJsonModel();
                            responseJsonModel.setCode(ResponseCodeConstant.ERROR_CODE);
                            responseJsonModel.setMsg(ex.getMessage());
                            responseResult(response, responseJsonModel);
                        }

                    } else {//普通页面
                        if (response.getStatus() == 404) {
                            mv.setViewName("error/404");
                        } else {
                            mv.setViewName("error/500");
                            logger.error(ex.getMessage(), ex);
                        }

                    }
                }
                return mv;
            }
        });

    }

    private void responseResult(HttpServletResponse response, Object result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
