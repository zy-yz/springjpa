package com.peppers.exam.config;

import com.peppers.exam.interceptor.MyAccessDecisionManager;
import com.peppers.exam.interceptor.MyFilterInvocationSecurityMetadataSource;
import com.peppers.exam.interceptor.handler.MyAccessDeniedHandler;
import com.peppers.exam.interceptor.handler.MyAuthenticationFailureHandler;
import com.peppers.exam.interceptor.handler.MyAuthenticationSuccessHandler;
import com.peppers.exam.interceptor.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;

/**
 * @author peppers
 * @description spring-security权限管理的核心配置de
 * @since 2021/1/7
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) /**全局*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserSecurityService userSecurityService;
    @Autowired
    private MyFilterInvocationSecurityMetadataSource filterMetadataSource; //权限过滤器
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;//权限决策器

    /**
     * @Description: 配置userDetails的数据源，密码加密格式
     * @Param: [auth]
     * @return: void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userSecurityService)
//                .passwordEncoder(new BCryptPasswordEncoder());// 实现自定义登录校验
    }

    /**
     * @Description: 配置放行的资源
     * @Param: [web]
     * @return: void
     **/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/index.html", "/static/**", "/login_p", "/favicon.ico")
                // 给 swagger 放行；不需要权限能访问的资源
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");
    }

    /**
     * @Description: 拦截配置
     * @Param: [http]
     * @return: void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 使其支持跨域
                .requestMatchers(CorsUtils:: isPreFlightRequest).permitAll()
                // 其他路径需要授权访问
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(filterMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(new MyAuthenticationFailureHandler())
                .successHandler(new MyAuthenticationSuccessHandler())
                .permitAll()
                .and()
                // 退出登录后的默认路径
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .permitAll()
                .and()
                .csrf().disable() //关闭csrf
                .exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
    }
}

