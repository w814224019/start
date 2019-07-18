package com.net.start.service;

import java.util.List;

import com.net.start.entity.Task;
import com.net.start.entity.User;
import com.net.start.service.exception.DuplicateKeyException;
import com.net.start.service.exception.InsertException;
import com.net.start.service.exception.PasswordNotMatchException;
import com.net.start.service.exception.UserNotFoundException;





public interface IUserService {
	/**
	 * 查询所有定时任务
	 * @return
	 */
	List<Task> getAlltask();
	/**
	 * 根据时间或者序号模糊找任务
	 * @param name
	 * @return
	 */
	List<Task> getTasksByLikeN(String name);
	
	/**
	 * 删除任务
	 * @param task
	 * @param time
	 * @return
	 */
	Integer updateTask(Task task,String time);
	
	
	
	
	/**
	 * 根据定时表主键id找任务条
	 * @param id
	 * @param time
	 * @return
	 */
	Task getBydingshiId(Integer id,String time);
	/**
	 * 找到序号中最大值
	 * @param lineId
	 * @return
	 */
	List<Task> getMaxBylineId(Integer lineId);
	/**
	 * 根据序号找到任务
	 * @param lineId
	 * @return
	 */
	List<Task> gettasksBylineId(Integer lineId);
	/**
	 * 增加新的任务
	 * @param task
	 * @return
	 */
	Integer addTask(Task task);
	
	
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户数据
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) 
			throws UserNotFoundException, 
				PasswordNotMatchException;
	
	
	
	/**
	 * 用户注册
	 * @param user 用户的注册信息
	 * @return 成功注册的用户数据
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) 
		throws DuplicateKeyException, 
			InsertException;
	
	
	
    /**
     * 
     * @param id
     * @return user
     */
	User getUserByid(Integer id);
	/**
	 * 
	 * @param cityFrom
	 * @param cityTo
	 * @return users
	 */
	List<User> getUsersBycity(String cityFrom,String cityTo);
}
