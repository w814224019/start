package com.net.start.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.net.start.controller.exception.FileEmptyException;
import com.net.start.entity.User;
import com.net.start.service.IUserService;
import com.net.start.util.ResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	/**
	 * 上传文件夹的名称
	 */
	private static final String UPLOAD_DIR_NAME = "upload";
	/**
	 * 上传文件的最大大小
	 */
	private static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
	/**
	 * 允许上传的文件类型
	 */
	private static final List<String> FILE_CONTENT_TYPES 
	= new ArrayList<>();

	/**
	 * 初始化允许上传的文件类型的集合
	 */
	static {
		FILE_CONTENT_TYPES.add("image/jpeg");
		FILE_CONTENT_TYPES.add("image/png");
	}
	@Autowired
	IUserService userService;
	

	@PostMapping("/selectAll.do")

	public ResponseResult<List<User>> getall(@RequestParam("cityFrom")String cityFrom,@RequestParam("cityTo")String cityTo){

		List<User>users=userService.getUsersBycity(cityFrom, cityTo);
		return new ResponseResult<List<User>>(SUCCESS,users);
	}
	@PostMapping("/insert.do")
	public ResponseResult<Void> add(User user,HttpSession session){
		
		userService.reg(user);
		session.setAttribute("uid", user.getId());
		// 返回
		return new ResponseResult<Void>(SUCCESS);	
		
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> upload(User user,HttpServletRequest request,@RequestParam("file") MultipartFile file){
		if(file.isEmpty()){
			throw new FileEmptyException("上传失败！没有选择上传的文件，或者"
					+ "选中的文件为空！");
		}
		// TODO 检查文件大小 > file.getSize()
		if (file.getSize() > FILE_MAX_SIZE) {
			// 抛出异常：文件大小超出限制
		}

		// TODO 检查文件类型 > file.getContentType()
		if (!FILE_CONTENT_TYPES.contains(
				file.getContentType())) {
			// 抛出异常：文件类型限制
		}
		String parentPath = request.getSession().getServletContext().getRealPath(UPLOAD_DIR_NAME);
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		// 确定文件名 > getOriginalFileName()
		String originalFileName = file.getOriginalFilename();
		int beginIndex = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(beginIndex);
		String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000) + 10000000) + suffix;
		File dest = new File(parent, fileName);
		// 执行保存文件
		try {
			file.transferTo(dest);
			System.err.println("上传完成！");
		} catch (IllegalStateException e) {
			// 抛出异常：上传失败
		} catch (IOException e) {
			// 抛出异常：上传失败
		}
		String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
		user.setAvatar(avatar);
		
		System.out.println("avatar:"+avatar);
		ResponseResult<String> rr
		= new ResponseResult<>();
	rr.setState(SUCCESS);
	rr.setData(avatar);
	return rr;
	}
	
	
	@PostMapping("/addinfo.do")
	public ResponseResult<Void> add(User user) throws Exception{
		File jsonfile=ResourceUtils.getFile("D:\\win_down_tools_data\\tts9-he\\tts9\\workspace\\start\\src\\main\\java\\com\\net\\start\\controller\\test4.json");

		String jsonsting=FileUtils.readFileToString(jsonfile);
		System.out.println("11111");
		JSONObject str =JSONObject.fromObject(jsonsting);
		System.out.println("2222");
		if(str.has("province")){
			JSONArray tr=str.getJSONArray("province");
			System.out.println("333");
			for (int i = 0; i < tr.size(); i++) {
				JSONObject jsons=JSONObject.fromObject(tr.get(i));
				System.out.println("4444");
				System.out.println("公司名称："+jsons.getString("companyName")); 
				String company=jsons.getString("companyName");

				user.setCompany(company);


				if(jsons.has("branchCompanyList")){
					System.out.println("5555");
					JSONArray tr1=jsons.getJSONArray("branchCompanyList");
					System.out.println(tr1.size());
					for(int j=0;j<tr1.size();j++){
						System.out.println("6666");
						JSONObject jsons1=JSONObject.fromObject(tr1.get(j));
						System.out.println("公司地址："+jsons1.getString("address")); 

						user.setTele(jsons1.getString("telephone"));
						userService.reg(user);
					}
				}



			}
		}




		return new ResponseResult<Void>(SUCCESS);
	}
}
