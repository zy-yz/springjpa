package com.peppers.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *EnableJpaRepositories 可以决定扫描和不扫描的类，注解，加快启动速度
 * 		value 等于 basePackage  用于配置扫描 Repositories 所在的 package 及子 package
 * 	    basePackageClasses   指定 Repository 类所在包，可以替换 basePackage 的使用
 * 	    includeFilters   指定包含的过滤器，该过滤器采用 ComponentScan 的过滤器，可以指定过滤器类型
 * 	    excludeFilters    指定不包含过滤器，该过滤器也是采用 ComponentScan 的过滤器里面的类
 * */
@SpringBootApplication
@EntityScan("com.peppers.exam.entity")
@EnableJpaRepositories(basePackages = {"com.peppers.exam.repository.*"},repositoryImplementationPostfix = "Impl")
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

}
