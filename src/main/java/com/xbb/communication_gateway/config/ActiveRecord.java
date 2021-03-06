package com.xbb.communication_gateway.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.xbb.communication_gateway.model._MappingKit;


@Component
public class ActiveRecord {

	@Autowired
	DataSource dataSource;

	
	@PostConstruct
	public void init(){
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
		arp.stop();
		_MappingKit.mapping(arp);
		//arp.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
		arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
		arp.addSqlTemplate("sql/all.sql");
		arp.setShowSql(true);
		arp.start();
		PropKit.use("application.properties");
	}
	
}
