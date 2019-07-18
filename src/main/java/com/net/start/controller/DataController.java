package com.net.start.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.net.start.entity.User;
import com.net.start.service.IUserService;

@Controller

public class DataController extends BaseController {
	@Autowired
	IUserService userService;
	@RequestMapping("/web/test")
    public String test(Map<Object,Object>map,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Integer id = getUidFromSession(session);
		User user=userService.getUserByid(id);
		
		
       
        map.put("user",user);
       
        
       
        return "test";
    }
}
