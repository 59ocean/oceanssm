package com.ocean.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ocean.entity.BaseEntity;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author chenhy
 * @since 2019-07-13
 */
@TableName("sys_role")
public class Role extends BaseEntity<Role> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.UUID)
	private String id;
	/**
	* 角色名称
	*/
	private String roleName;
	/**
	* 备注
	*/
	private String remark;


	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getRoleName() {
	return roleName;
	}

	public void setRoleName(String roleName) {
	this.roleName = roleName;
	}

	public String getRemark() {
	return remark;
	}

	public void setRemark(String remark) {
	this.remark = remark;
	}
}
