package com.net.start.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Pjsons {
	 public static void main(String[] args) throws Exception {
		
		 File jsonfile=ResourceUtils.getFile("D:\\win_down_tools_data\\tts9-he\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller\\test5.json");
	
		String jsonsting=FileUtils.readFileToString(jsonfile);
	        System.out.println("11111");
		JSONObject str =JSONObject.fromObject(jsonsting);
		System.out.println("2222");
		if(str.has("shi")){
		 JSONArray tr=str.getJSONArray("shi");
		 System.out.println("333");
		 for (int i = 0; i < tr.size(); i++) {
			 JSONObject jsons=JSONObject.fromObject(tr.get(i));
			 System.out.println("4444");
			 System.out.println("公司名称："+jsons.getString("name")); 
			 System.out.println("公司名称："+jsons.getString("xian")); 
			 if(jsons.has("xian")){
				 System.out.println("5555");
				 
				 //JSONArray tr1=jsons.getJSONArray("xian");
				 //System.out.println("公司名称："+jsons.getString("xian")); 
				// System.out.println(tr1.size());
				/* for(int j=0;j<tr1.size();j++){
					 System.out.println("6666");
					 JSONObject jsons1=JSONObject.fromObject(tr1.get(j));
					System.out.println("公司地址："+jsons1.getString("address")); 
				 }*/
			 }
			 
			 
			
		}
	 }
	 }
}
