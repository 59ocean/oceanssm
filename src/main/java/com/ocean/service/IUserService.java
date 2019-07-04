package com.ocean.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.User;
import com.ocean.query.UserQuery;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 董春雨
 * @since 2019-06-25
 */
public interface IUserService extends BaseService<User> {
	public Page<User> selectPage(UserQuery query);
}
