package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.DictItem;
import com.xbb.communication_gateway.service.DictItemService;

@RestController
@RequestMapping("/dictItem")
public class DictItemController extends BaseController{
	@Autowired
	DictItemService dictItemService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		DictItem dictItem=simpleModel(DictItem.class);
		return easyuiPageResult(dictItemService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), dictItem));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		DictItem dictItem=simpleModel(DictItem.class);
		Boolean result=dictItemService.save(dictItem);
		return result?AjaxJson.success().setData(dictItem):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		DictItem dictItem=simpleModel(DictItem.class);
		dictItem.remove("dictItemname", "password");
		Boolean result=dictItemService.update(dictItem);
		return result?AjaxJson.success().setData(dictItem):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		DictItem dictItem=simpleModel(DictItem.class);
		Boolean result=dictItemService.delete(dictItem);
		return result?AjaxJson.success().setData(dictItem):AjaxJson.failure();
	}

}
