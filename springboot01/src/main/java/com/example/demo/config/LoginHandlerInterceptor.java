package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//登入成功后，应该有用户的session
		Object loginUser = request.getSession().getAttribute("loginUser");
		if(loginUser == null){ //没有登入
			request.setAttribute("msg","没有权限，请先登入");
			request.getRequestDispatcher("/index.html").forward(request,response);
			return false;
		}else {
			return true;
		}
	}
}
