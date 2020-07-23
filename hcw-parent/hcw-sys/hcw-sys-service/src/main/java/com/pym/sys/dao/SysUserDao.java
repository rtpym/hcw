package com.pym.sys.dao;

import com.pym.sys.pojo.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserDao {
    @Select("select * from sys_user where userId = #{userId}")
    SysUser queryById(@Param("userId") Long userId);

    @Select("select * from sys_user where userName=#{userName}")
    SysUser queryByUserName(@Param("userName") String userName);

    @Select("select * from sys_user where phone = #{phone}")
    SysUser queryByPhone(@Param("phone") String phone);
    /* @Delete("delete from sys_user where userId = #{userId}")
    int delete(@Param("userId") Long userId);

    int update(SysUser user);

    int create(SysUser user);

    List<SysUser> query(Map<String, Object> params);*/
}
