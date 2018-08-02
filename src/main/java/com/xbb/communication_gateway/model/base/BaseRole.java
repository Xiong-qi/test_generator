package com.xbb.communication_gateway.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.String getId() {
		return getStr("id");
	}

	public M setRoleName(java.lang.String roleName) {
		set("roleName", roleName);
		return (M)this;
	}

	public java.lang.String getRoleName() {
		return getStr("roleName");
	}

	public M setRoleCode(java.lang.String roleCode) {
		set("roleCode", roleCode);
		return (M)this;
	}

	public java.lang.String getRoleCode() {
		return getStr("roleCode");
	}

}
