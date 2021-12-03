package com.example.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	//ShiroFilterFactoryBean:3
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//设置安全管理器
		bean.setSecurityManager(securityManager);

		//添加shiro的内置过滤器
		/**
		 * anon: 无需认证就可以访问
		 * authc: 必须认证了才可以访问
		 * user: 必须拥有了《记住我》功能才能进入
		 * perms: 拥有对某个资源的权限才能访问
		 * role: 拥有某个角色权限才能访问
		 */

		Map<String, String> filterMap = new LinkedHashMap<>();
//		filterMap.put("/user/add","authc");
//		filterMap.put("/user/update","authc");

		//授权,正常的情况下，会跳转到为授权页面
		filterMap.put("/user/add","perms[user:add]");
		filterMap.put("/user/update","perms[user:update]");

		//登入拦截
		filterMap.put("/user/*","authc");



		bean.setFilterChainDefinitionMap(filterMap);

		//设置登入的请求
		bean.setLoginUrl("/toLogin");
		//设置未授权的页面
		bean.setUnauthorizedUrl("/noauth");

		return bean;
	}

	//DefaultWebSecurityManager:2
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	//创建Realm对象, 需要自定义:1
	@Bean
	public UserRealm userRealm(){
		return new UserRealm();
	}

	//整合shiroDialect : 用来整合shiro和thymeleaf
	@Bean
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}


}
