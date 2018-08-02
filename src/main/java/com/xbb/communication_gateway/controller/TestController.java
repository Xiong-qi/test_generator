package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Test;
import com.xbb.communication_gateway.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController{
	@Autowired
	TestService testService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		Test test=simpleModel(Test.class);
		return easyuiPageResult(testService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), test));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		Test test=simpleModel(Test.class);
		Boolean result=testService.save(test);
		return result?AjaxJson.success().setData(test):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		Test test=simpleModel(Test.class);
		test.remove("testname", "password");
		Boolean result=testService.update(test);
		return result?AjaxJson.success().setData(test):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		Test test=simpleModel(Test.class);
		Boolean result=testService.delete(test);
		return result?AjaxJson.success().setData(test):AjaxJson.failure();
	}

}
