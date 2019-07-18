package com.net.start.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.net.start.util.ResponseResult;

@RestController
public class httpdata extends BaseController {
@RequestMapping(value="/http",method=RequestMethod.GET)
public  ResponseResult<Map> httpdata(@RequestParam String http) throws IOException{
    //根据获取的网址 爬取内容
    Document doc = Jsoup.connect("http://"+http).get();  //"http://www.yiibai.com" @PathVariable
    String title = doc.title();  
    System.out.println("title is: " + title);  

    Map<String,Object> map= new HashMap<String,Object>();
    ResponseResult<Map> result = new ResponseResult<Map>(SUCCESS);
    if(title.isEmpty()){
        result.setState(0);
        result.setMessage("未找到记录");
    }else{
        map.put("title", title);
        result.setState(200);
        result.setMessage("查询成功");
        result.setData(map);
    }
    return result;
}
}