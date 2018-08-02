package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Region;
import com.xbb.communication_gateway.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController extends BaseController{
	@Autowired
	RegionService regionService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		Region region=simpleModel(Region.class);
		return easyuiPageResult(regionService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), region));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		Region region=simpleModel(Region.class);
		Boolean result=regionService.save(region);
		return result?AjaxJson.success().setData(region):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		Region region=simpleModel(Region.class);
		region.remove("regionname", "password");
		Boolean result=regionService.update(region);
		return result?AjaxJson.success().setData(region):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		Region region=simpleModel(Region.class);
		Boolean result=regionService.delete(region);
		return result?AjaxJson.success().setData(region):AjaxJson.failure();
	}

}
