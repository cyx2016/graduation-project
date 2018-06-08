package com.cyx.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cyx.common.MyInterceptor1;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


//@EnableRedisHttpSession
//@SpringBootApplication(scanBasePackages={"com.service.something","com.service.application"})
@Configuration
@SpringBootApplication
@ComponentScan({"com.cyx.controller","com.cyx.service","com.cyx.pojo"})
@MapperScan(basePackages = "com.cyx.mapper")
public class DemoApplication extends WebMvcConfigurerAdapter {


	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.PrettyFormat
		);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}


	/**
	 * 配置静态访问资源
	 * 注：添加绝对路径访问
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/NewsImg/**").addResourceLocations("file:D:/毕业设计/FileData/img/");
		registry.addResourceHandler("/document/**").addResourceLocations("file:D:/毕业设计/FileData/literature/");
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**")
				.excludePathPatterns("/index.html","/login.html","/showhomepage","/login","/show1",
						"/newstemplate","/news/showById","/news/intoKindDetail","/NewsImg/**","/links/show","/static/**");
		super.addInterceptors(registry);
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
