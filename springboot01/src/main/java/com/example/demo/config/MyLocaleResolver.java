package com.example.demo.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

	//解析请求
	@Override
	public Locale resolveLocale(HttpServletRequest httpServletRequest) {
		//获取请求中的语言参数
		String language = httpServletRequest.getParameter("l");

		Locale locale = Locale.getDefault(); 	//如果没有，就是用默认

		//如果请求额链接携带了国际化的参数
		if(!StringUtils.isEmpty(language)){
			//zh_CN
			String[] split = language.split("_");
			//语言，国家
			locale = new Locale(split[0],split[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

	}


}
