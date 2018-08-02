package com.xbb.communication_gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jfinal.plugin.activerecord.Record;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.RoleMenu;
import com.xbb.communication_gateway.service.RoleMenuService;

@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController{
	@Autowired
	RoleMenuService roleMenuService;
	
	@RequestMapping("/page")
	public Record getPage(PageInfo pageInfo) {
		RoleMenu roleMenu=simpleModel(RoleMenu.class);
		return easyuiPageResult(roleMenuService.paginateDynamicEqual(pageInfo.getPage(), pageInfo.getPageSize(), roleMenu));
	}

	
	@RequestMapping("/save")
	public AjaxJson save() {
		RoleMenu roleMenu=simpleModel(RoleMenu.class);
		Boolean result=roleMenuService.save(roleMenu);
		return result?AjaxJson.success().setData(roleMenu):AjaxJson.failure();
	}
	
	@RequestMapping("/update")
	public AjaxJson update() {
		RoleMenu roleMenu=simpleModel(RoleMenu.class);
		roleMenu.remove("roleMenuname", "password");
		Boolean result=roleMenuService.update(roleMenu);
		return result?AjaxJson.success().setData(roleMenu):AjaxJson.failure();
	}
  	
	@RequestMapping("/delete")
	public AjaxJson delete() {
		RoleMenu roleMenu=simpleModel(RoleMenu.class);
		Boolean result=roleMenuService.delete(roleMenu);
		return result?AjaxJson.success().setData(roleMenu):AjaxJson.failure();
	}

}
