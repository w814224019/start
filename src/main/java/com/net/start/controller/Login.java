package com.net.start.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.net.start.entity.User;
import com.net.start.service.IUserService;
import com.net.start.util.ResponseResult;
@RestController
public class Login extends BaseController{
	@Autowired
	IUserService userService;
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session) throws ServletException, IOException {
			// 执行登录
			User user
				= userService.login(username, password);
			// 将相关信息存入到Session
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			
			return new ResponseResult<>(SUCCESS,user);
}
}