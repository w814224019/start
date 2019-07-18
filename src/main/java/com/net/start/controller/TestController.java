package com.net.start.controller;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.net.start.entity.Position;

//这是一个测试类，会去txt文件中读取数据并返回最新的经纬度给我
@Controller

public class TestController {
//这个方法用来返回视图
    @RequestMapping(value = "/currentMap",method = RequestMethod.GET)
    public String toCurrentPositionMap(){
        return "CurrentPositionMap";
    }
//这个方法在前台用ajax调用，返回最新的位置坐标
    @RequestMapping(value = "/currentMapFuck",method = RequestMethod.GET)
    @ResponseBody
    //此方法返回一个最新的位置点给我
    public Map<String,Object> getCurrentPosition(){

        List<Double> doubleList = new ArrayList<Double>();//用来存储从文件中读取到的数据

        File file = new File("D:\\开发工具\\tts9\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller\\position.txt");
        try{
            String readData;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((readData = br.readLine())!=null){//没有读到文件尾的s时候
                try{
                    doubleList.add(Double.parseDouble(readData));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        double lon = doubleList.get(doubleList.size()-2);
        double lat = doubleList.get(doubleList.size()-1);

        Map<String,Object> modelMap = new HashMap<String, Object>(2);
        modelMap.put("data",new Position(lon,lat));
        modelMap.put("success","true");

        return modelMap;

    }
}
