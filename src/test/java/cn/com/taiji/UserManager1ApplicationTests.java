package cn.com.taiji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.entity.User;
import cn.com.taiji.service.LoginService;
import cn.com.taiji.service.RoleService;
import cn.com.taiji.service.UserService;
import cn.com.taiji.util.JsonUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManager1ApplicationTests {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	LoginService loginService;
	
	
	@Test
	public void contextLoads() {
	}
	@Test
	public void addUser() {
//		try {
			User user=new User();
			user.setUName("我是测试方法");
			userService.addUser(user);
			User user2=new User();
			user2=userService.finduIdByUName("我是测试方法");
			System.out.println(user2);
			List<User> list=new ArrayList<>();
			list.add(userService.finduIdByUName("我是测试方法"));
//			String json=JSONArray.toJSONString(list);
//			System.out.println(json);
//		} catch (Exception e) {
//			// TODO: handle exception
//			
//			System.out.println("查找失败");
//		}
		
		
	}
	@Test
	public void updateUser() {
		User user=new User();
		user=userService.finduIdByUName("我是测试方法");
		user.setUName("我被修改了");
		userService.addUser(user);
		System.out.println("------"+userService.finduIdByUName("我是测试方法"));
	}
	@Test
	public void deleteUser() {
		User user=new User();
		user=userService.finduIdByUName("我被修改了");
		System.out.println("------"+userService.deleteUser(user.getUId()));
	}
	
	@Test
	public void login() {
		List<String> role=loginService.findRoleByUserUName("超级无敌管理员");
		List<String> permission=new ArrayList<>();
		List<String> list=new ArrayList<>();
		for (String string : role) {
			System.out.println("---------"+string);
			permission=loginService.findPermissionByRoleRName(string);
//			for (String string2 : permission) {
//				System.out.println(string2);
//			}
			list.removeAll(permission);
			list.addAll(permission);
		}
		JsonUtil jsonUtil=new JsonUtil();
		
		System.out.println(jsonUtil.SwitchToJson(list));
//		JSONArray jsonArray=JSONArray.
//		String json=JSONArray.toJSONString(list);
//		System.out.println(json);
		
	}

}
