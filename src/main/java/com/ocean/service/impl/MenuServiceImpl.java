package com.ocean.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ocean.entity.Menu;
import com.ocean.mapper.MenuMapper;
import com.ocean.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.query.MenuQuery;

import java.util.ArrayList;
import java.util.List;

import static java.sql.JDBCType.NULL;

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
	@Override
	public MenuVo getTreemenu(){
		List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().isNull("parent_id"));
		List<MenuVo> menuVoList = new ArrayList<>();
		for (Menu menu : menuList){
			List<Menu> menuList2 = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id",menu.getId()));
			MenuVo menuVo = new MenuVo();
			menuVo.setId(menu.getId());
			menuVo.setName(menu.getName());
			List<MenuVo> menuVoLis2 = new ArrayList<>();
			for (Menu menu1 : menuList2){
				MenuVo menuVo2 = new MenuVo();
				menuVo2.setId(menu1.getId());
				menuVo2.setName(menu1.getName());
				menuVoLis2.add(menuVo2);
			}
			menuVo.setChildren(menuVoLis2);
			menuVoList.add(menuVo);
		}
		MenuVo menuVo3 = new MenuVo();
		menuVo3.setId(null);
		menuVo3.setName("顶级菜单");
		menuVo3.setChildren(menuVoList);
		menuVo3.setOpen(true);
		return menuVo3;
	}

	@Override
	public MenuVo getTreemenu2(String menuId){
		List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().isNull("parent_id"));
		List<MenuVo> menuVoList = new ArrayList<>();
		for (Menu menu : menuList){
			List<Menu> menuList2 = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id",menu.getId()));
			MenuVo menuVo = new MenuVo();
			menuVo.setId(menu.getId());
			menuVo.setName(menu.getName());
			if(menuId!=null&&menuId.equalsIgnoreCase(menuVo.getId())){
				menuVo.setChecked(true);
			}
			List<MenuVo> menuVoLis2 = new ArrayList<>();
			for (Menu menu1 : menuList2){
				MenuVo menuVo2 = new MenuVo();
				menuVo2.setId(menu1.getId());
				menuVo2.setName(menu1.getName());
				if(menuId!=null&&menuId.equalsIgnoreCase(menuVo2.getId())){
					menuVo2.setChecked(true);
				}
				menuVoLis2.add(menuVo2);
			}
			menuVo.setChildren(menuVoLis2);
			menuVoList.add(menuVo);
		}
		MenuVo menuVo3 = new MenuVo();
		menuVo3.setId(null);
		menuVo3.setName("顶级菜单");
		menuVo3.setChildren(menuVoList);
		menuVo3.setOpen(true);
		if(menuId!=null&&menuId.equalsIgnoreCase(menuVo3.getId())){
			menuVo3.setChecked(true);
		}
		return menuVo3;
	}


}
