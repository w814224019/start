package com.net.start.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.net.start.entity.User;

public interface UserMapper {
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	
	
	
	
	
	
	
/**
 * 
 * @param cityFrom
 * @param cityTo
 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
 */
	List<User> findByCity(@Param("cityFrom")String cityFrom,@Param("cityTo")String cityTo);
	/**
	 * 
	 * @param id
	 * @return 用户
	 */
	User findByid(Integer id);
}
