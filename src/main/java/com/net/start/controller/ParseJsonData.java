package com.net.start.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import com.net.start.entity.Companier;
import com.net.start.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJsonData {
	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//把java对象转换成json对象，并转换成json字符串
		User user=new User();
		Companier companier=new Companier();
		user.setCompany("乐力物流");
		JSONObject object=JSONObject.fromObject(user);
		String jsonstr = object.toString();
		System.out.println(jsonstr);
		//把java 对象列表转换为json对象数组，并转换成json字符串
		User user1=new User();
		user1.setAvatar("1fdf");
          User user2=new User();
          user2.setAvatar("2dff");
          List<User> array=new ArrayList<User>();
          array.add(user1);
          array.add(user2);
          JSONArray jsonarray = JSONArray.fromObject(array);
          String jsonstr1 = jsonarray.toString();
          System.out.println("jsonsrt1:"+jsonstr1);
          // 把JSON字符串转换为JSON对象，并转换成JAVA 对象
          File jsonfile=ResourceUtils.getFile("D:\\win_down_tools_data\\tts9-he\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller\\test3.json");
          
          String jsonsting=FileUtils.readFileToString(jsonfile);
          JSONArray array2=JSONArray.fromObject(jsonsting);
          System.out.println("array1:"+array2.size());
  		Map<String,Object> maps=new HashMap<>();
  		for(int i=0;i<array2.size();i++){
  			JSONObject jsons=JSONObject.fromObject(array2.get(i).toString());
  			maps.put(jsons.getString("companyName"), jsons.getString("picturePath"));
  		}
  		System.out.println("maps:"+maps);
		
		
	}
}
