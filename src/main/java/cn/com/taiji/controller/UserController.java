package cn.com.taiji.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.entity.Role;
import cn.com.taiji.entity.User;
import cn.com.taiji.entity.UserRole;
import cn.com.taiji.service.RoleService;
import cn.com.taiji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="User",tags="用户管理")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@ApiOperation("跳转到主页面")
	@GetMapping("/")
	public String showIndex() {
		
		return "redirect:showlist";
	}
	@ApiOperation("主页面")
	@GetMapping("/showlist")
	public String showList() {
		
		return "showList";
	}
	@ApiOperation("跳转到用户页面")
	@GetMapping("/showusers")
	public String show() {
		
		return "showUsers";
	}
	@ApiOperation("查询所有的用户转换为json，返回前台页面")
	@PostMapping("/showusers")
	@ResponseBody//转换为json的注解
	public List<UserRole> showusers(){
		List<UserRole> userlist=userService.findAllUsers();
//		System.out.println("showusers: "+userlist);
		return userlist;
	}
	@ApiOperation("删除用户")
	@GetMapping("/deleteuser")
	public String delete(int uid) {
//		System.out.println("---------"+uid);
		userService.deleteUser(uid);
		return "redirect:showusers";
	}
	@ApiOperation("跳转添加用户页面")
	@GetMapping("adduser")
	public String add() {
		return "addUSer";
	}
	@ApiOperation("添加用户")
	@PostMapping("adduser")
	public String addUser(HttpServletRequest request,String[] rolelist) {
		String uName=request.getParameter("uName");
//		System.out.println("addUser--uName: "+uName);
		User user=new User();
		user.setUName(uName);
		userService.addUser(user);//首先保存User对象
		user=userService.finduIdByUName(uName);//由于UId是自动生成的，所以要根据UName查询整个user对象
//		System.out.println("addUser--user2: "+user);
		
		for (String rId : rolelist) {
			
//			System.out.println("addUser--rId: "+rId);
			Role role=new Role();
			role=roleService.findRoleByRId(Integer.valueOf(rId));//根据rId查询整个role对象
//			System.out.println("addUser--role: "+role);
			UserRole userRole=new UserRole();
			userRole.setRole(role);//把role对象添加到中间表UserRole中
//			System.out.println("addUser--role: "+role);
			userRole.setUser(user);
//			System.out.println("addUser--user: "+user2);
			userService.saveUserRole(userRole);
			/*
			 * 此处存在经典错误:A different object with the same identifier value was already associated with the session : [cn.com.taiji.entity.UserRole#0]
			 * 必须在主键上添加注解：@GeneratedValue(strategy = GenerationType.AUTO)或者@GeneratedValue(strategy = GenerationType.IDENTITY)
			 * 这样Spring Data JPA才会知道你想要拿到这个保存后的实体，然后再返回这个实体
			 */
//			System.out.println("addUser--userRole: "+userRole);
			
		}

		return "redirect:showusers";
	}
	@ApiOperation("跳转修改用户页面")
	@GetMapping("updateuser")
	@ResponseBody
	public List<UserRole> update(int uid) {
//		System.out.println("update: "+uid);
		List<UserRole> userRole=userService.findUserById(uid);
		return userRole;
	}
	@ApiOperation("修改用户")
	@PostMapping("updateuser")
	public String updateUser(HttpServletRequest request,String[] rolelist) {
		//由于数据是重复的，所以不能实现级联更新，只能绕过更新，先删除掉已存在的需要修改的用户，然后再重新添加上去
		String uid=request.getParameter("uId");
		String uName=request.getParameter("uName");
//		System.out.println("UpdateUSer--uid:"+uid+" - "+uName);
		userService.deleteUser(Integer.valueOf(uid));//先删除已存在的需要修改的用户
		User user=new User();
		user.setUId(Integer.valueOf(uid));
		user.setUName(uName);
		userService.addUser(user);//保存User对象
//		System.out.println("updateUser: "+user);
		for (String rid : rolelist) {
//			System.out.println("updateUser--rId: "+rid);
			Role role=new Role();
			role=roleService.findRoleByRId(Integer.valueOf(rid));//根据rId查询整个role对象
//			System.out.println("updateUSer: "+role);
			UserRole userRole=new UserRole();
//		    userRole=userService.findByUserIdAndRoleId(Integer.valueOf(uid), Integer.valueOf(rid));//多表联查，根据uid和rid在UserRole表中查找到这条记录
//		    System.out.println("updateUser--userRole: "+userRole);
		    userRole.setRole(role);//把role对象添加到中间表UserRole中
			userRole.setUser(user);
			userService.saveUserRole(userRole);//把需要修改的用户重新添加到数据库中
//			System.out.println("updateUser--userRole: "+userRole);
		}
		return "redirect:showusers";
	}
}
