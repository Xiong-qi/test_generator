package com.xbb.communication_gateway.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jfinal.plugin.activerecord.Page;
import com.xbb.communication_gateway.kit.SqlKit;
import com.xbb.communication_gateway.kit.SqlKit.PageInfo;
import com.xbb.communication_gateway.model.Role;
import com.xbb.communication_gateway.model.User;
import com.xbb.communication_gateway.service.RoleService;
import com.xbb.communication_gateway.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService,UserDetailsService {

	@Autowired
	RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByUsername(username);
		if (null == user){
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}
	
	private User getUserByUsername(String username) {
		User user = super.findFirst("select * from user where username=?", username);
		if (null != user){
			List<Role> roles = roleService.getUserRole(user.getStr("id"));
			user.put("roles", roles);
		}
		return user;
	}

	@Override
	public Page<User> getPage(PageInfo pageInfo, User user) {
		List<Object>params=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer("SELECT id,username,realname,phone FROM user WHERE 1=1");
		SqlKit.appendOrderInfo(pageInfo, sql);
		return super.paginate(pageInfo.getPage(), pageInfo.getPageSize(),sql.toString(),params.toArray());
	}
	
}
