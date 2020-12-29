package com.peppers.exam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author peppers
 * @description spring jpa中的重试机制
 *    添加@EnableRetry 注解，是为了开启重试机制，使 @Retryable 生效。
 * @since 2020/12/29
 **/
@EnableRetry
@Configuration
public class RetryConfiguration {
}
