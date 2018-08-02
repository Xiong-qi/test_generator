package com.xbb.communication_gateway.security.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xbb.communication_gateway.security.MyFilterInvocationSecurityMetadataSource;


/**
 * 权限管理接口地址url及对应的角色关系改变事件 
 * <br/>监听器
 * @author xubinbin
 *
 */
@Component
public class UrlMapChangeEventListener implements ApplicationListener<UrlMapChangeEvent>{

	@Autowired
	MyFilterInvocationSecurityMetadataSource source;
	
	@Override
	public void onApplicationEvent(UrlMapChangeEvent event) {
		source.clearUrlMap();
	}

}
