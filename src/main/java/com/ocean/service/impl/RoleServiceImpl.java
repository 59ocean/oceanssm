package com.ocean.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.entity.Role;
import com.ocean.mapper.RoleMapper;
import com.ocean.query.RoleQuery;
import com.ocean.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-13
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Page<Role> selectPage(RoleQuery query) {
		Page<Role> page= new Page<Role>(query.getPage(),query.getLimit());
		return page.setRecords(roleMapper.selectByQuery(page,query));
	}


}
