package com.net.start.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.net.start.entity.Task;
import com.net.start.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
@Autowired
private UserMapper usermapper;
@Autowired
TaskMapper taskmapper;

@Test
public void findByCity(){
//	String cityFrom="郑州";
//	String cityTo="南阳";
//	List<User>users=new ArrayList<User>();
//	users =usermapper.findByCity(cityFrom, cityTo);
	//Integer lineId=7;
//	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	   String time=dateFormat.format(Calendar.getInstance().getTime());
//	   Task t=new Task();
//	   t.setLineId(lineId);
//	   t.setStatus(0);
//	   t.setTime(time);
//	List<Task> users=taskmapper.findByLikeN(16);
//	System.out.println(users);
		
}
@Test
public void findByUsername(){
	String username="刘强东";
	User user=usermapper.findByUsername(username);
	System.err.println("通过用户名查找："+user);
}
@Test
public void findyById(){
	User user=usermapper.findByid(130);
	System.out.println(user);
}
@Test
public void add(){
	User user=new User();
	Date now = new Date();
	user.setCityFrom("北京");
	user.setCityTo("上海");
	user.setCompany("得力物流");
	user.setPassword("1234ewrewtwetre");
	user.setQq(235234);
	user.setUid(130);
	user.setPhone(1380013800);
	user.setEmail("root@tedu.cn");
	user.setSalt("Hello,MD5!");
	user.setIsDelete(0);
	user.setCreatedUser("Admin");
	user.setModifiedUser("Admin");
	user.setCreatedTime(now);
	user.setModifiedTime(now);
	Integer rows = usermapper.addnew(user);
	System.err.println("rows=" + rows);
}

}
