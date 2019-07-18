package com.net.start.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.net.start.entity.Task;
import com.net.start.service.IUserService;
import com.net.start.util.ResponseResult;

@Controller
public class TaskController extends BaseController{
	@Autowired
	IUserService userService;
	@GetMapping("/blank")
	public String blank(Model model) {
 		return "blank";
	}
	//selds
	@RequestMapping(value = "selds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<List<Task>>selds(String name){
		//System.err.println(name+"nane");
		List<Task>l=userService.getTasksByLikeN(name);
		return new ResponseResult<List<Task>>(SUCCESS,l);
	}
	
	@RequestMapping(value = "setFrequencyCron", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> setFrequencyCron(Integer status,Integer companyId,Integer a1,Integer a2,Integer a3,Integer id,Integer total) {
		//取出cookie
		//制定刷新计划，存入数据库中，时间点，id，
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//8-12 4*60   240   30分钟刷一次     8
	   //Integer a1=30;  
		System.err.println("aaaaaaaaaaaa:"+a1);
		System.err.println("aaaaaaaaaaaa:"+id);
		//System.err.println("aaaaaaaaaaaa:"+a3);
	   //13-18 5*60   300  30分钟刷一次  10
	   //Integer a2=30;
	   //19-21 2*60   120   60分钟刷一次  2
	   //Integer a3=60;
		//获取物流币余额 如果小于toatl直接返回抛出物流币余额不足请及时充值异常
	   //List<planlist> time=new ArrayList<planlist>();
	   Calendar calendar = Calendar.getInstance();
	   for(int a=1;a<total+1;a++){
		   int hour = calendar.get(Calendar.HOUR_OF_DAY);
		   int minute=calendar.get(Calendar.MINUTE);
		  // System.err.println("获取时间小时"+hour+":"+minute);
		   if(hour>=8&&hour<=11){
			   System.err.println("8-12");
			   int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
			   int pa2=(hour2-8)*60;
			 //  System.err.println("8-pa-12:"+pa2);
			   if(a1==null&&a2!=null&&a3!=null){
					a1=240+60;
					calendar.add(Calendar.MINUTE, a1-pa2);
				}
			   else  if(a1!=null&&a2==null&&a3!=null){
					a2=300+60;
					calendar.add(Calendar.MINUTE, a1);
				}
			   else  if(a1!=null&&a2!=null&&a3==null){
					a3=120+660;
					calendar.add(Calendar.MINUTE, a1);
				}

			   else  if(a1!=null&&a2==null&&a3==null){
				   a3=120+660;
				   a2=300+60+120+660;
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a1-pa2);
			   }
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, a1-pa2);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else   if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			   else  if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a1);
			   }
			 //判断时间在8-12 
	        	//加30分钟
		   }else if(hour==12){
			   int minu=calendar.get(calendar.MINUTE);
			   if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				 //  System.err.println(".......a1.....");
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   
			   else  if(a2!=null&&a1!=null&&a3==null){
				   a3=120+660;
				  // System.err.println("..........a3..");
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else  if(a2==null&&a1!=null&&a3!=null){
				   a2=300+60;
				  // System.err.println("...a2.........");
				   calendar.add(Calendar.MINUTE, 60+300+60-minu);
			   }
			   
			   else   if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				  // System.err.println(".......a1...a2..");
				   calendar.add(Calendar.MINUTE, 240+60+300+60-minu);
			   }
			   
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				  // System.err.println("...a1.....a3....");
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else  if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				  // System.err.println("...a2.......a3..");
				   calendar.add(Calendar.MINUTE, 60+300+60+120+660-minu);
			   }
			   
			   
			   else  if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60+300+60);
			   }
			   else   if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   
			   else  if(a1<=240&&a2>300&&a3>120){
				  // System.err.println("........这里.......");
				   calendar.add(Calendar.MINUTE, 60+300+60+120+660);
			   }
			   else   if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60+300+60);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else   if(a1<=240&&a2<=300&&a3<=120){
				   //System.err.println("...............");
				   
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   
		   }
		   else if(hour>=13&&hour<=17){
			 //判断时间在13-18
			  // System.err.println("13-18");
			   if(a2==null&&a1!=null&&a3!=null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				  // System.err.println("13??????-pa-18:"+pa);
					a2=300+60;
					 calendar.add(Calendar.MINUTE, a2-pa);
				}
			   
			   else  if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					 calendar.add(Calendar.MINUTE, a2);
				}
			   else  if(a2!=null&&a1==null&&a3!=null){
					a1=240+60;
					 calendar.add(Calendar.MINUTE, a2);
				}
			   
			   else if(a2==null&&a1==null&&a3!=null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				  // System.err.println("13-pa-###18:"+pa);
				   a2=300+60;
				   a1=240+60+300+60;
				   calendar.add(Calendar.MINUTE, a2-pa);
			   }
			   else   if(a2==null&&a1!=null&&a3==null){
				   int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa=(hour1-13)*60;
				   //System.err.println("13-!!!pa-18:"+pa);
				   a2=300+60+120+660;
				   a3=120+660;
				   calendar.add(Calendar.MINUTE, a2-pa);
			   }
			   else   if(a2!=null&&a1==null&&a3==null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   
			   
			   else  if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else   if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else  if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   else   if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a2);
			   }
			   //还可以刷次数
			  // int minu=calendar.get(calendar.MINUTE);
			 //int a1=(18-hour)*60-minu;
			// System.err.println("---a--"+a1);
		      
		   }else if(hour>=18&&hour<19){
			   int minu=calendar.get(calendar.MINUTE);
			   if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
			   else if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					calendar.add(Calendar.MINUTE, 60+120+660-minu);
				}
			   else if(a2==null&&a1!=null&&a3!=null){
					a2=300+60;
					calendar.add(Calendar.MINUTE, 60-minu);
				}
			   else if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, 60-minu);
			   }
			   else if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				   calendar.add(Calendar.MINUTE, 60+120+660+240+60-minu);
			   }
			   
			   else if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				   calendar.add(Calendar.MINUTE, 60+120+660-minu);
			   }
			   else if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, 60+120+660);
			   }
			   
			   else if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else  if(a3<=120&&a1>240&&a2>300){
				   calendar.add(Calendar.MINUTE, 60);
			   }
			   else if(a3>120&&a1>240&&a2<=300){
				   calendar.add(Calendar.MINUTE, 60+120+660+240+60);
			   }
			   else  if(a3>120&&a1<=240&&a2>300){
				   calendar.add(Calendar.MINUTE, 60+120+660);
			   }
			   else  if(a1<=240&&a2<=300&&a3<=120){
				 //  System.err.println("...............");
				   
				   calendar.add(Calendar.MINUTE, 60-minu);
				}
		   }
		   else if(hour>=19&&hour<=20){
			 //判断时间在19-21
			  // System.err.println("19-21");
			   if(a3==null&&a1!=null&&a2!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   
			   else if(a1!=null&&a2==null&&a3!=null){
				   
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1==null&&a2!=null&&a3!=null){
					
					a1=240+60;
					calendar.add(Calendar.MINUTE, a3);
				}
			   else if(a3==null&&a1==null&&a2!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660+240+60;
					a1=240+60;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   else if(a3==null&&a2==null&&a1!=null){
				   int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
				   int pa3=(hour3-19)*60;
				  // System.err.println("19-21-pa-:"+pa3);
					a3=120+660;
					a2=300+61+120+660;
					calendar.add(Calendar.MINUTE, a3-pa3);
				}
			   else if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1<=240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1>240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else  if(a1>240&&a2<=300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   
			   else if(a1<=240&&a2>300&&a3>120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1>240&&a2>300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
			   else if(a1<=240&&a2<=300&&a3<=120){
				   calendar.add(Calendar.MINUTE, a3);
			   }
	        	
		   }else if(hour>=21||hour<8){
			  // System.err.println("---其他时间段--");
			   
			   if(a1==null&&a2==null&&a3!=null){
				   a1=240+60+300+60;
				   a2=300+60;
				 //  System.err.println("111....111");
				   calendar.add(Calendar.MINUTE, 660+240+60+300+60);
			   }
			   else  if(a1==null&&a3==null&&a2!=null){
				   a1=240+60;
				   a3=120+660+240+60;
				  // System.err.println("118888111");
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else if(a2==null&&a1!=null&&a3==null){
				   a2=300+60+120+660;
				   a3=120+660;
				  // System.err.println("11177111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1==null&&a2!=null&&a3!=null){
				   a1=240+60;
				  // System.err.println("1116661111");
				   calendar.add(Calendar.MINUTE, 660+240+60);
				}
			   else if(a2!=null&&a1!=null&&a3==null){
					a3=120+660;
					// System.err.println("11166611");
					calendar.add(Calendar.MINUTE, 660);
				}
			   else if(a2==null&&a1!=null&&a3!=null){
				   a2=300+60;
				   //System.err.println("11555111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1>240&&a2>300&&a3<=120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660+240+60+300+60);
			   }
			   else if(a1>240&&a2<=300&&a3>120){
				  // System.err.println("1333311");
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else  if(a1<=240&&a2>300&&a3>120){
				   //System.err.println("1144111");
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1>240&&a2<=300&&a3<=120){
				  // System.err.println("*********1111111");
				   
				   calendar.add(Calendar.MINUTE, 660+240+60);
			   }
			   else  if(a1<=240&&a2<=300&&a3>120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else  if(a1<=240&&a2>300&&a3<=120){
				  // System.err.println("11111111");
				   
				   calendar.add(Calendar.MINUTE, 660);
			   }
			   else if(a1<=240&&a2<=300&&a3<=120){
				  // System.err.println("12111");
				   calendar.add(Calendar.HOUR_OF_DAY,11);
				   
				}
			   
		   }
		   String date;
		    date=dateFormat.format(calendar.getTime());
		   //根据计算的时间点和id从数据库中找到是否有相同的数据
		   Task t=userService.getBydingshiId(id, date);
		   
		   //如果有
		   if(t!=null){
			  List<Task> t1=userService.getMaxBylineId(t.getLineId());
			   System.err.println(t1);
			   //String time1=t1.getTime();
			   calendar.add(Calendar.DAY_OF_MONTH,1);
			    date=dateFormat.format(calendar.getTime());
		   }
		   
		   //如果没有就直接添加
		   Task task=new Task();
		   task.setId(companyId);
		   task.setLineId(id);
		   task.setTime(date);
		   task.setStatus(status);
		   
		   //刷新一次，物流币根据刷新要求把时间点和id存入，
		   userService.addTask(task);
		   //time.add(p);
		  // System.err.println(dateFormat.format(Calendar.getInstance().getTime()));
	   }
	   //System.err.println("----"+time.size()+time);
	   //剩余物流币
		//设置网点为1
		//设置完后把物流币总数-total保存

		return new ResponseResult<String>(SUCCESS);
	}



		
}
