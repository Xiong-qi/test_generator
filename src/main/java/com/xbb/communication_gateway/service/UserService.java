package com.xbb.communication_gateway.service;

import com.jfinal.plugin.activerecord.Page;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.User;

public interface UserService extends BaseService<User> {
	Page<User> getPage(PageInfo pageInfo,User user);
}
