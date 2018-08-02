package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.TbTest;
import com.xbb.communication_gateway.service.TbTestService;

@RestController
@RequestMapping("/tbTest")
public class TbTestController extends BaseController{
	@Autowired
	TbTestService tbTestService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		TbTest tbTest=simpleModel(TbTest.class);
		return easyuiPageResult(tbTestService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), tbTest,"test_name"));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		TbTest tbTest=simpleModel(TbTest.class);
		Boolean result=tbTestService.save(tbTest);
		return result?AjaxJson.success().setData(tbTest):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		TbTest tbTest=simpleModel(TbTest.class);
		tbTest.remove("tbTestname", "password");
		Boolean result=tbTestService.update(tbTest);
		return result?AjaxJson.success().setData(tbTest):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		TbTest tbTest=simpleModel(TbTest.class);
		Boolean result=tbTestService.delete(tbTest);
		return result?AjaxJson.success().setData(tbTest):AjaxJson.failure();
	}

}
