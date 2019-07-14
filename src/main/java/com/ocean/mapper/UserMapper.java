package com.ocean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.entity.User;
import com.ocean.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);

    int insertUser(User record);

    int insertSelective(User record);


    User selectByPrimaryKey(String id);

    List<User> selectByQuery(Page<User> page, @Param("query") UserQuery query);
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}