package com.example.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("new User")
public class User {
	@ApiModelProperty("用户")
	public String username;
	@ApiModelProperty("密码")
	public String password;
}
