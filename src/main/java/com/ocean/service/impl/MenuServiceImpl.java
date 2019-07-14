package com.ocean.service.impl;


import com.ocean.entity.Menu;
import com.ocean.mapper.MenuMapper;
import com.ocean.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.query.MenuQuery;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-14
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Page<Menu> selectPage(MenuQuery query) {
		Page<Menu> page= new Page<Menu>(query.getPage(),query.getLimit());
		return page.setRecords(menuMapper.selectByQuery(page,query));
	}


}
