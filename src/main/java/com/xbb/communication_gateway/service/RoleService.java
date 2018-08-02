package com.xbb.communication_gateway.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Role;

public interface RoleService extends BaseService<Role> {

	public List<Role> getUserRole(String userId);
	
	public Page<Role> pageByUserId(PageInfo pageInfo, String userId);
	
	public List<String> roleHashAuthority(String resourceUrl);
	
	public Page<Role> getPage(PageInfo pageInfo, Role user);
	
	List<Role> renderUserRole(String userId);
	
}
