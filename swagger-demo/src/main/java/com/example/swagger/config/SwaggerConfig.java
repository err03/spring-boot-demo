package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.print.Doc;
import java.util.ArrayList;

@Configuration
@EnableSwagger2    //开启swagger2
public class SwaggerConfig {

	@Bean
	public Docket docket1(){
		return new Docket(DocumentationType.SWAGGER_2).groupName("A");
	}

	@Bean
	public Docket docket2(){
		return new Docket(DocumentationType.SWAGGER_2).groupName("B");
	}
	@Bean
	public Docket docket3(){
		return new Docket(DocumentationType.SWAGGER_2).groupName("C");
	}

	//配置了swagger的Docket的bean实例
	@Bean
	public Docket docket(Environment environment){

		//设置要显示的swagger环境
		Profiles profiles = Profiles.of("dev","test");

		//获取项目的环境,通过acceptsProfiles方法判断是否处在在自己的设定当中
		boolean flag = environment.acceptsProfiles(profiles);


		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("Error")
				.enable(true)		//是否启用swagger，默认true
				.select()
				//配置要扫描接口的方式
				//backpackage("com.example.swagger.controller"): 指定要扫描的包
				//any():扫描全部
				//none()：都不扫描
				//withClassAnnotation(): 扫描类上的注解,比如：@Controller
				//withMethodAnnotation(): 扫描方法上的注解,比如:@GetMapping()
				.apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
				//根据url下的路径选择
//				.paths(PathSelectors.ant("/"))
				.build();	//工厂模式
	}

	//配置Swagger信息 = apiInfo
	private ApiInfo apiInfo(){
		//作者信息
		Contact DEFAULT_CONTACT = new Contact("error", "https://github.com/err03", "2431206033@gmail.com");

		return new ApiInfo("Error 的swagger日记",
				"不要沮丧",
				"v1.0",
				"https://github.com/err03",
				DEFAULT_CONTACT,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList());
	}

}
