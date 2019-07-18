package com.net.start.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TheymeleafController {
	@ResponseBody
	@RequestMapping("/hello")
	public void test(HttpServletRequest request, HttpServletResponse response,String echostr) throws ServletException, IOException {
		WeChat we=new WeChat();
		//we.doGet(request, response);
		we.doPost(request, response);
	}
	
	
}
