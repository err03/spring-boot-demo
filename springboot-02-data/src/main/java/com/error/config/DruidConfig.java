package com.error.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource(){
		return new DruidDataSource();
	}

	//后台监控功能 :web.xml, ServletRegistrationbean
	//因为springboot 内置了 servlet容器，所以没有web.xml, 替代方法：ServletRegistrationBean
	@Bean
	public ServletRegistrationBean StatViewServlet(){
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

		//后台需要有人登入
		HashMap<String, String> initParameters = new HashMap<>();

		//增加配置
		initParameters.put("loginUsername","admin");	//登入key,固定的配置，loginUsername,loginPassword
		initParameters.put("loginPassword","123");

		//允许说能可以访问
		initParameters.put("allow","");	//如果value为空，所有人都可以访问;localhost 本机才可以访问

		//禁止谁能访问
		//initParameters.put("kuansheng","11ip");	//禁止，"name","ip"

		bean.setInitParameters(initParameters);//设置初始化参数

		return bean;
	}

	//filter
	@Bean
	public FilterRegistrationBean webStatFilter(){
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

		bean.setFilter(new WebStatFilter());

		//可以过滤哪些请求
		HashMap<String, String> initParameters = new HashMap<>();

		initParameters.put("exclusions","*.js,*.css,/druid/*");

		bean.setInitParameters(initParameters);
		return bean;
	}
}
