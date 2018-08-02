package com.xbb.communication_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jfinal.plugin.activerecord.DbKit;
import com.xbb.communication_gateway.config.SessionConst;
import com.xbb.communication_gateway.model.User;

@Controller
public class IndexController extends BaseController{

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
//	@RequestMapping("generate")
//	@ResponseBody
//	public String generate() {
//		String srcPath = "/Users/xubinbin/Desktop/agriculture-bigdata/src/main/java/";
//		MyGenerator generator = new MyGenerator(DbKit.getConfig().getDataSource(), "com.xbb.communication_gateway.model.base",
//				srcPath + "com/qianchi/bigdata/model/base", "com.xbb.communication_gateway.model", srcPath + "com/qianchi/bigdata/model",
//				"com.xbb.communication_gateway.dao", srcPath + "com/qianchi/bigdata/dao", "com.xbb.communication_gateway.kit",
//				srcPath + "com/qianchi/bigdata/kit");
//		generator.setGenerateDaoInModel(true);
//		generator.setGenerateChainSetter(true);
//		generator.setGenerateDataDictionary(true);
//		generator.generate();
//		return "index";
//	}
	
	@RequestMapping("generate1")
	@ResponseBody
	public String generate1() {

		String srcPath = "E:/person/project/test_generator/src/main/java/";

		templateservice.MyGenerator generator = new templateservice.MyGenerator(DbKit.getConfig().getDataSource(), "com.xbb.communication_gateway.model.base",
				srcPath + "com/xbb/communication_gateway/model/base", "com.xbb.communication_gateway.model", srcPath + "com/xbb/communication_gateway/model",
				"com.xbb.communication_gateway.service", srcPath + "com/xbb/communication_gateway/service", "com.xbb.communication_gateway.kit",
				srcPath + "com/xbb/communication_gateway/kit", "com.xbb.communication_gateway.controller", srcPath + "com/xbb/communication_gateway/controller");
		generator.setGenerateDaoInModel(true);
		generator.setGenerateChainSetter(true);
		generator.setGenerateDataDictionary(true);
		generator.generate();
		return "index";
	}

	@RequestMapping(value="/view/{path}",method = RequestMethod.GET)  
	public String view(@PathVariable("path") String path) {
		return path;
	}
	
	@RequestMapping(value="/view/{path1}/{path2}",method = RequestMethod.GET)  
	public String view1(@PathVariable("path1") String path1, @PathVariable("path2") String path2) {
		return path1 + "/" + path2;
	}

	@RequestMapping("/userInfo")
	@ResponseBody
	public User index1(){
		System.out.println(getResponse().encodeRedirectURL("/userinfo"));
		System.out.println(getSession().getId());
		return getSessionAttr(SessionConst.USER);
	}
	
	@ResponseBody
	@RequestMapping("hello")
	public String hello() {
		return "Hello";
	}
	
	
	@ResponseBody
	@RequestMapping("hello1")
	public String hello1() {
		getSession(true);
		setSessionAttr("user", "xubinbin");
		return "login";
	}
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/view/login";
	}
}
