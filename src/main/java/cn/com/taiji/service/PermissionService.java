package cn.com.taiji.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.dao.PermissionDao;
import cn.com.taiji.entity.Permission;


@Service
public class PermissionService {

	@Autowired
	PermissionDao permissionDao;
	//查找所有权限
	@Transactional
	public List<Permission> findAllPermissions(){
		List<Permission> permission=permissionDao.findAll();
		return permission;
	}
	//保存删除权限
	@Transactional
	public void deletePermission(int pid){
		permissionDao.delete(pid);
	}
	//保存权限
	@Transactional
	public void addPermission(Permission permission){
		permissionDao.save(permission);
	}
	//根据权限id查找permission
	public Permission findPermissionByPId(int pid) {
		Permission permission=permissionDao.findByPId(pid);
		return permission;
	}
}
