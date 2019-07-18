package com.net.start.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.net.start.entity.Task;
import com.net.start.entity.User;
import com.net.start.mapper.TaskMapper;
import com.net.start.mapper.UserMapper;
import com.net.start.service.IUserService;
import com.net.start.service.exception.DuplicateKeyException;
import com.net.start.service.exception.InsertException;
import com.net.start.service.exception.PasswordNotMatchException;
import com.net.start.service.exception.UserNotFoundException;
@Service
public class UserServiceImpl implements IUserService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserMapper usermapper;
	@Autowired
	TaskMapper taskmapper;
	@Override
	public User getUserByid(Integer id) {
      User oneu=findByid(id);
      oneu.setPassword(null);
      oneu.setSalt(null);
      oneu.setIsDelete(null);
      
		return oneu;
	}

	@Override
	public List<User> getUsersBycity(String cityFrom, String cityTo) {
		List<User> manies=null;
		if(cityFrom!=null&cityTo!=null){
			
			 manies=findBycity(cityFrom, cityTo);
			if(manies==null||manies.size()==0 ){
				
				throw new UserNotFoundException("没有找到用户!"); 
			}
		}
		log.error("--------通过城市找用户---------");
		return manies;
	}
	/**
	 * 根据用户id查询用户数据
	 * @param id 用户id
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByid(Integer id){
		return usermapper.findByid(id);
	}
	/**
	 * 
	 * @param cityFrom
	 * @param cityTo
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private List<User> findBycity(String cityFrom,String cityTo){
		return usermapper.findByCity(cityFrom, cityTo);
	}

	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		User data=findByid(user.getUid());
		if(data==null){
			user.setIsDelete(0);
			Date now = new Date();
			user.setCreatedUser(user.getCompany());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getCompany());
			user.setModifiedTime(now);
			// 加密-1：获取随机的UUID作为盐值
			String salt=UUID.randomUUID().toString();
			// 加密-2：获取用户提交的原始密码
		    String srcPassword=user.getPassword();
		 // 加密-3：基于原始密码和盐值执行加密，获取通过MD5加密的密码
		    String md5Password = getMd5Password(srcPassword, salt);
		 // 加密-4：将加密后的密码封装在user对象中
		 			user.setPassword(md5Password);
		 		// 加密-5：将盐值封装在user对象中
					user.setSalt(salt);
					// 执行注册
					
						
						addnew(user);
					
					// 返回注册的用户对象
			return user;
		}else{
			// 否：用户名已被占用，抛出DuplicateKeyException异常
						throw new DuplicateKeyException(
							"注册失败！尝试注册的用户名(" + user.getCompany() + ")已经被占用！");
		}
		
	}

	private void addnew(User user) {
		Integer rows = usermapper.addnew(user);
		if (rows != 1) {
			throw new InsertException(
				"增加用户数据时出现未知错误！");
		}
	}

	private String getMd5Password(String srcPassword, String salt) {
		// 盐值 拼接 原密码 拼接 盐值
				String str = salt + srcPassword + salt;
				// 循环执行10次摘要运算
				for (int i = 0; i < 10; i++) {
					str=DigestUtils.md5DigestAsHex(str.getBytes());
					
					
				}
		return str;
	}
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUsername(String username) {
		return usermapper.findByUsername(username);
	}
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		// 根据参数username查询用户数据
				User data = findByUsername(username);
				// 判断用户数据是否为null
				if (data == null) {
					// 是：为null，用户名不存在，则抛出UserNotFoundException
					throw new UserNotFoundException(
						"登录失败！您尝试登录的用户名不存在！");
				}
				// 否：非null，根据用户名找到了数据，取出盐值
				String salt = data.getSalt();
				// 	对参数password执行加密
				String md5Password 
					= getMd5Password(password, salt);
//			 	判断密码是否匹配
				if (data.getPassword().equals(md5Password)) {
					//是：匹配，密码正确，则判断是否被删除
					if (data.getIsDelete() == 1) {
						// 是：已被删除，则抛出UserNotFoundException或自定义“用户被删除异常”
						throw new UserNotFoundException(
							"登录失败！您尝试登录的用户数据已经被删除！");
					}
					// 否：没被删除，则登录成功，将第1步查询的用户数据中的盐值和密码设置为null
					data.setPassword(null);
					data.setSalt(null);
					// 返回用户数据
					return data;
				}else {
					// 否：不匹配，密码错误，则抛出PasswordNotMatchException
					throw new PasswordNotMatchException(
						"登录失败！密码错误！");
				}
	}

	@Override
	public List<Task> getAlltask() {
		// TODO Auto-generated method stub
		return taskmapper.findAllTask();
	}

	@Override
	public List<Task> getTasksByLikeN(String name) {
		// TODO Auto-generated method stub
		return taskmapper.findByLikeN(name);
	}

	@Override
	public Integer updateTask(Task task, String time) {
		// TODO Auto-generated method stub
		return taskmapper.addNewTask(task);
	}

	@Override
	public Task getBydingshiId(Integer id, String time) {
		// TODO Auto-generated method stub
		return taskmapper.findBydsId(id, time);
	}

	@Override
	public List<Task> getMaxBylineId(Integer lineId) {
		// TODO Auto-generated method stub
		return taskmapper.findMaxBylineId(lineId);
	}

	@Override
	public List<Task> gettasksBylineId(Integer lineId) {
		// TODO Auto-generated method stub
		return taskmapper.findTasksBylineId(lineId);
	}

	@Override
	public Integer addTask(Task task) {
		// TODO Auto-generated method stub
		return taskmapper.addNewTask(task);
	}
}
