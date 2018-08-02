package com.xbb.communication_gateway.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.config.SessionConst;
import com.xbb.communication_gateway.controller.validate.user.UserSaveValidate;
import com.xbb.communication_gateway.framework.validate.JsonValidate;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.User;
import com.xbb.communication_gateway.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	RestTemplate restTemplate;


	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		User user=simpleModel(User.class);
		return easyuiPageResult(userService.getPage(pageInfo,user));
	}
	
	@RequestMapping("/esPage")
	public Record esPage(PageInfo pageInfo, String queryStr) {
//		Page<EsUser> page = esUserService.getPage(pageInfo, queryStr);
//		Record record = new Record();
//		record.set("rows", page.getContent());
//		record.set("total", page.getTotalElements());
//		return record;
		User user=simpleModel(User.class);
		return easyuiPageResult(userService.getPage(pageInfo,user));
	}
	
	@RequestMapping("/save")
	@JsonValidate(UserSaveValidate.class)
	public AjaxJson save() {
		User user=simpleModel(User.class);
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		Boolean result=userService.save(user);
		//EsUser esUser = BeanKit.modelToBean(user, EsUser.class);
		//esUserRepository.save(esUser);
		return result?AjaxJson.success().setData(user):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		User user=simpleModel(User.class);
		user.remove("username", "password");
		Boolean result=userService.update(user);
		//EsUser esUser = null;
		//Optional<EsUser> option = esUserRepository.findById(user.getId());
		//if (option.isPresent()) {
			//esUser = option.get();
			//BeanKit.copyProperties(user, esUser, "username", "password");
		//}
		//else {
			//esUser = BeanKit.modelToBean(user, EsUser.class);
		//}
		//esUserRepository.save(esUser);
		return result?AjaxJson.success().setData(user):AjaxJson.failure();
	}
	
	@RequestMapping("/updatePwd")
	public AjaxJson updatePwd(HttpServletRequest request, String oldPassword, String newPassword) {
		User user = getSessionAttr(SessionConst.USER);
		if (!bcryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			return AjaxJson.failure().setMsg("输入密码有误");
		} else {
			user.setPassword(bcryptPasswordEncoder.encode(newPassword));
			userService.update(user);
			setSessionAttr(SessionConst.USER, user);
			return AjaxJson.success();
		}
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		User user=simpleModel(User.class);
		Boolean result=userService.delete(user);
		//esUserRepository.deleteById(user.getId());
		return result?AjaxJson.success().setData(user):AjaxJson.failure();
	}

}
