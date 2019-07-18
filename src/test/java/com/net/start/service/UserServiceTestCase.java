package com.net.start.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.net.start.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
@Autowired
private IUserService userService;
@Test
public void get(){
	Integer id=110;
	
User result=userService.getUserByid(id);
	System.out.println(result);
	
}
@Test
public void getu(){
	String username="刘强东";
	String password="4";
	User res=userService.login(username, password);
	System.err.println("登录结果："+res);
}
@Test
public void getm(){
	List<User> som=new ArrayList<User>();
	String cityFrom="郑州";
	String cityTo="新";
	som=userService.getUsersBycity(cityFrom, cityTo);
	System.out.println(som);
}
}
