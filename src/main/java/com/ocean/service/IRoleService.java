package com.ocean.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.entity.Role;
import com.ocean.query.RoleQuery;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-13
 */
public interface IRoleService extends IService<Role> {
	/**
	 *  分页查询
	 * @param query
	 * @return
	 */
	Page<Role> selectPage(RoleQuery query);


}
