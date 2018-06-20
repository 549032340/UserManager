package cn.com.taiji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.dao.PermissionDao;
import cn.com.taiji.dao.RoleDao;

@Service
public class LoginService {
	
	@Autowired
	RoleDao roleDao;
	@Autowired
	PermissionDao permissionDao;
	
	//根据用户名查找对应角色
	public List<String> findRoleByUserUName(String uName){
		List<String> role=roleDao.findRoleByUserUName(uName);
		return role;
	}
	
	//根据角色名称查找对应权限
	public List<String> findPermissionByRoleRName(String rName){
		List<String> permission=permissionDao.findPermissionByRoleRName(rName);
		return permission;
	}
}
