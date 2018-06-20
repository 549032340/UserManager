package cn.com.taiji.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="UserLogin",tags="用户登陆")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	//根据传过来的uName查找数据库里对应的数据然后获取它的角色，再通过角色查找对应的属性，
	//但是问题在于uName的不唯一性，实际项目中需要根据唯一的标识进行查询
	@ApiOperation("查看用户的所有权限")
	@PostMapping("/login")
	@ResponseBody
	public List<String> login(String uName){
		List<String> role=loginService.findRoleByUserUName(uName);//查找对应的角色
		List<String> permission=new ArrayList<>();//用来存放所有查到的权限
		for (String string : role) {//遍历查询到的所有角色，然后分别进行查询权限
			List<String> permission1=loginService.findPermissionByRoleRName(string);
			permission.removeAll(permission1);//不同的角色可能会有相同的权限，通过list的removeAll()方法去重
			permission.addAll(permission1);//去重之后的合并
		}
//		System.out.println(permission);
		return permission;
	}
}
