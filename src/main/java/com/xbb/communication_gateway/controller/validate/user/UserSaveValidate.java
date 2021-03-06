package com.xbb.communication_gateway.controller.validate.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.xbb.communication_gateway.framework.validate.Validate;
import com.xbb.communication_gateway.framework.validate.Validator;
import com.xbb.communication_gateway.model.User;
import com.xbb.communication_gateway.service.UserService;





@Validate
public class UserSaveValidate extends Validator {

	@Autowired
	UserService userService;
	
	@Override
	protected void validate() {
		User user=this.controller.simpleModel(User.class);
		validateRequired("username", "username can't empty", "用户名不能空");
		validateRequired("password", "password can't empty", "密码不能为空");
		long count = userService.getCountByEqualAttr(false, user, "username");
		if (count > 0){
			addError("username already existed", "该用户名已存在");
		}
	}

}
