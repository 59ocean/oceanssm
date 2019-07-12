package com.ocean.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author chenhy
 * @date @time 2019/6/27 11:40
 */
public class UserQuery extends PageQuery {
	private String username;

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}
}
