package com.net.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 递归
 * @author Admin
 *
 */
public class digui {
	
public static void main(String[] args) throws IOException {
	
	 String APIKEY = "6aca1781a549434d917bb3f2087f574c"; 
     String INFO = URLEncoder.encode("北京今日天气", "utf-8"); 
    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
    URL getUrl = new URL(getURL); 
    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
    connection.connect(); 
 
    // 取得输入流，并使用Reader读取 
    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
    StringBuffer sb = new StringBuffer(); 
    String line = ""; 
    while ((line = reader.readLine()) != null) { 
        sb.append(line); 
    } 
    reader.close(); 
    // 断开连接 
    connection.disconnect(); 
    System.out.println(sb); 
	
	//System.out.println(fun(3));
}
public static int fun(int   x){
	if(x>1)   
		return   x+fun(x-1);   
	else   
		return   x;  
}


}
