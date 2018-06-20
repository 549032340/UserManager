package cn.com.taiji.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.entity.Permission;
import cn.com.taiji.entity.Role;
import cn.com.taiji.entity.RolePermission;
import cn.com.taiji.service.PermissionService;
import cn.com.taiji.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="Role",tags="角色管理")
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	
	@ApiOperation("跳转角色页面")
	@GetMapping("/showroles")
	public String show() {
		
		return "showRoles";
	}
	@ApiOperation("获取所有角色数据转换成json，返回前台")
	@PostMapping("/showroles")
	@ResponseBody
	public List<Role> showRoles() {
		List<Role> rolelist=roleService.findAllRoles();
//		System.out.println("showroles: "+rolelist);
		return rolelist;
	}
	@ApiOperation("添加角色")
	@PostMapping("addrole")
	public String addRole(HttpServletRequest request,String[] plist1) {
		String rName=request.getParameter("rName1");
//		System.out.println("addRole--rName: "+rName);
		Role role=new Role();
		role.setRName(rName);
		roleService.saveRole(role);
		role=roleService.findRoleByRName(rName);
//		System.out.println("addRole--role: "+role);
		
		for (String pId : plist1) {
//			System.out.println("adRole--pId: "+pId);
			Permission permission=new Permission();
			permission=permissionService.findPermissionByPId(Integer.valueOf(pId));
			RolePermission rolePermission=new RolePermission();
			rolePermission.setPermission(permission);//把role对象添加到中间表UserRole中
			rolePermission.setRole(role);
			roleService.saveRolePermission(rolePermission);
//			System.out.println("addRole--rolePermission: "+rolePermission);
			
		}
		return "redirect:showroles";
	}
	@ApiOperation("删除角色")
	@GetMapping("/deleterole")
	public String delete(int rid) {
//		System.out.println("---------delete"+rid);
		roleService.deleteRole(rid);
		return "redirect:showroles";
	}
	@ApiOperation("获取需要修改的角色的数据")
	@GetMapping("updaterole")
	@ResponseBody
	public List<RolePermission> update(int rid) {
//		System.out.println("update: "+rid);
		List<RolePermission> rolePermission=roleService.findByRoleRId(rid);
//		System.out.println(rolePermission);
		return rolePermission;
	}
	@ApiOperation("修改角色")
	@PostMapping("updaterole")
	public String updateRole(HttpServletRequest request,String[] plist) {
		String rid=request.getParameter("rId");
		String rName=request.getParameter("rName");
//		System.out.println("UpdateRole--uid:"+rid+" - "+rName);
		roleService.deleteRole(Integer.valueOf(rid));//先删除已存在的需要修改的用户
		Role role=new Role();
		role.setRId(Integer.valueOf(rid));
		role.setRName(rName);
		roleService.saveRole(role);//保存Role对象
//		System.out.println("updateRole: "+role);
		for (String pid : plist) {
//			System.out.println("updateRole--pId: "+pid);
			Permission permission=new Permission();
			permission=permissionService.findPermissionByPId(Integer.valueOf(pid));
//			System.out.println("updateRole: "+permission);
			RolePermission rolePermission=new RolePermission();
			rolePermission.setRole(role);//把role对象添加到中间表RolePermission中
			rolePermission.setPermission(permission);
			roleService.saveRolePermission(rolePermission);//把需要修改的角色重新添加到数据库中
//			System.out.println("updateRole--rolePermission: "+rolePermission);
		}
		return "redirect:showroles";
	}

}
