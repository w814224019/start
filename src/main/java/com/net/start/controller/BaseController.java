package com.net.start.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.net.start.controller.exception.RequestException;
import com.net.start.service.exception.ServiceException;
import com.net.start.service.exception.UserNotFoundException;
import com.net.start.util.ResponseResult;

public abstract class BaseController {
	
	public static final Integer SUCCESS = 200;
	@ExceptionHandler({ServiceException.class,RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handlerException(Exception e){
		Integer state = null;
		if(e instanceof UserNotFoundException){
			state=401;
			//没有找到用户信息
		}
		return new ResponseResult<>(state,e);
	}

	protected Integer getUidFromSession(HttpSession session){
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
}
