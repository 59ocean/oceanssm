package com.ocean.mapper;

import com.ocean.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.query.RoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author chenhy
 * @since 2019-07-13
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectByQuery(Page<Role> page, @Param("query") RoleQuery query);
}