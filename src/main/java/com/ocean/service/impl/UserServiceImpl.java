package com.ocean.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.entity.User;
import com.ocean.mapper.UserMapper;
import com.ocean.query.UserQuery;
import com.ocean.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 董春雨
 * @since 2019-06-25
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {


	@Autowired
	private UserMapper userMapper;

	@Override
	public Page<User> selectPage(UserQuery query) {
		Page<User> page= new Page<User>(query.getPage(),query.getLimit());
		return page.setRecords(userMapper.selectByQuery(page,query));
	}

	@Override
	public void insertUser(User user){
		userMapper.insertUser(user);
	}




}
