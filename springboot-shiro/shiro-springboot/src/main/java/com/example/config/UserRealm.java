package com.example.config;


import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的 Realm extends AuthorizingRealm
public class UserRealm  extends AuthorizingRealm {

	@Autowired
	UserService userService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行了授权 AuthorizationInfo");

		//授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//拿到当前登入的对象
		Subject subject = SecurityUtils.getSubject();
		User currentUser = (User) subject.getPrincipal();	//拿到User对象

		//设置当前用户的权限
		info.addStringPermission(currentUser.getPerms());

//		info.addStringPermission("user:add");
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行了认证 AuthenticationInfo");

		//认证：用户名和密码	 ： 数据库中取
//		String name = "root";
//		String password = "123";

		UsernamePasswordToken userToken = (UsernamePasswordToken) token;


		//连接真实的数据库
		User user = userService.queryUserByName(userToken.getUsername());

		if(user == null){
			return null;	//抛出异常 UnknownAccountException
		}

		Subject currentSubject = SecurityUtils.getSubject();
		Session session = currentSubject.getSession();
		session.setAttribute("loginUser",user);
		//密码认证， shiro 做								//没有密码，id代替,密码是String类型，id是Int类型，不匹配
		return new SimpleAuthenticationInfo(user,String.valueOf(user.getId()),"");
	}
}
