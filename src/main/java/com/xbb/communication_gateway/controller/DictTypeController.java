package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.DictType;
import com.xbb.communication_gateway.service.DictTypeService;

@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController{
	@Autowired
	DictTypeService dictTypeService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		DictType dictType=simpleModel(DictType.class);
		return easyuiPageResult(dictTypeService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), dictType));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		DictType dictType=simpleModel(DictType.class);
		Boolean result=dictTypeService.save(dictType);
		return result?AjaxJson.success().setData(dictType):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		DictType dictType=simpleModel(DictType.class);
		dictType.remove("dictTypename", "password");
		Boolean result=dictTypeService.update(dictType);
		return result?AjaxJson.success().setData(dictType):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		DictType dictType=simpleModel(DictType.class);
		Boolean result=dictTypeService.delete(dictType);
		return result?AjaxJson.success().setData(dictType):AjaxJson.failure();
	}

}
