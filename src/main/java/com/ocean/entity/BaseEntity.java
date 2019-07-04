package com.ocean.entity;

import java.util.Date;

/**
 * @author chenhy
 * @date @time 2019/6/25 15:40
 */
public class BaseEntity {
	private String creator;
	private Date createTime;
	private String updator;
	private Date updateTime;

	public String getCreator () {
		return creator;
	}

	public void setCreator (String creator) {
		this.creator = creator;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator () {
		return updator;
	}

	public void setUpdator (String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}
}
