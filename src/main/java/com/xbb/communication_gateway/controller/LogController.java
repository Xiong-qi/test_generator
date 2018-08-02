package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Log;
import com.xbb.communication_gateway.service.LogService;

@RestController
@RequestMapping("/log")
public class LogController extends BaseController{
	@Autowired
	LogService logService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		Log log=simpleModel(Log.class);
		return easyuiPageResult(logService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), log));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		Log log=simpleModel(Log.class);
		Boolean result=logService.save(log);
		return result?AjaxJson.success().setData(log):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		Log log=simpleModel(Log.class);
		log.remove("logname", "password");
		Boolean result=logService.update(log);
		return result?AjaxJson.success().setData(log):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		Log log=simpleModel(Log.class);
		Boolean result=logService.delete(log);
		return result?AjaxJson.success().setData(log):AjaxJson.failure();
	}

}
