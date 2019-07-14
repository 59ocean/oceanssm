package com.ocean.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ocean.query.MenuQuery;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenhy
 * @since 2019-07-14
 */
public interface IMenuService extends IService<Menu> {
	/**
	 *  分页查询
	 * @param query
	 * @return
	 */
	Page<Menu> selectPage(MenuQuery query);


}
