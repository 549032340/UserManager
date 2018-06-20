package cn.com.taiji.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.entity.Permission;
import cn.com.taiji.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="Permission",tags="权限管理")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	@ApiOperation("跳转权限页面")
	@GetMapping("/showpermissions")
	public String show() {
		
		return "showPermissions";
	}
	//获取所有的权限信息，把获取到的list转换成json传递到前台页面
	@ApiOperation("获取所有权限，转换成json")
	@PostMapping("/showpermissions")
	@ResponseBody
	public List<Permission> showPermissions() {
		List<Permission> permissionlist=permissionService.findAllPermissions();
//		System.out.println("showroles: "+permissionlist);
		return permissionlist;
	}
	@ApiOperation("删除权限")
	@GetMapping("/deletepermission")
	public String delete(int pid) {
//		System.out.println("---------delete"+pid);
		permissionService.deletePermission(pid);
		return "redirect:showpermissions";
	}
	@ApiOperation("添加权限")
	@PostMapping("/addpermission")
	public String addPermission(Permission permission) {
		if(permission!=null) {
			permissionService.addPermission(permission);
			System.out.println("addpermission: "+permission);
		}
		return "redirect:showpermissions";
		
	}
	@ApiOperation("根据pid获取需要修改的权限的数据")
	@GetMapping("updatepermission")
	@ResponseBody
	public List<Permission> update(int pid) {
//		System.out.println("update: "+pid);
		Permission permission1=permissionService.findPermissionByPId(pid);//根据pid查找权限
		List<Permission> permission=new ArrayList<>();
		permission.add(permission1);
		return permission;
	}
	@ApiOperation("修改权限")
	@PostMapping("updatepermission")
	public String updateRole(Permission permission) {
		permissionService.addPermission(permission);
		return "redirect:showpermissions";
	}
	
}
