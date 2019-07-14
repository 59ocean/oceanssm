package com.ocean.mapper;

import com.ocean.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.ocean.query.MenuQuery;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenhy
 * @since 2019-07-14
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectByQuery(Page<Menu> page, @Param("query") MenuQuery query);
}