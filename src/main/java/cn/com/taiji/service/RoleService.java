package cn.com.taiji.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.dao.RoleDao;
import cn.com.taiji.dao.RolePermissionDao;
import cn.com.taiji.entity.Role;
import cn.com.taiji.entity.RolePermission;


@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	@Autowired
	RolePermissionDao rolePermissionDao;
	
	//查找所有的角色
	@Transactional
	public List<Role> findAllRoles(){
		List<Role> role=roleDao.findAll();
		return role;
	}
	
	//根据角色名称查找role
	@Transactional
	public Role findRoleByRName(String rName) {
		Role role=roleDao.findByRName(rName);
		return role;
	}
	
	//根据角色id查找role
	@Transactional
	public Role findRoleByRId(int rId) {
		Role role=roleDao.findByRId(rId);
		return role;
	}
	
	//保存角色
	@Transactional
	public void saveRole(Role role){
		roleDao.save(role);
	}
	
	//删除角色
	@Transactional
	public void deleteRole(int rid){
		roleDao.delete(rid);
	}
	
	//------------------------以下是RolePermissionDao的Service-------------------------------
	
	//查找所有的角色和所拥有权限
	@Transactional
	public List<RolePermission> findAllRoles2(){
		List<RolePermission> rolePermission=rolePermissionDao.findAllRoles();
		return rolePermission;
	}
	
	//查找所有的角色对应的权限
	@Transactional
	public List<RolePermission> findRoles(){
		List<RolePermission> rolePermission=rolePermissionDao.findRoles();
		return rolePermission;
	}
	
	//保存RolePermission
	@Transactional
	public void saveRolePermission(RolePermission rolePermission){
		rolePermissionDao.save(rolePermission);
	}
	
	//根据id查找RolePermission
	@Transactional
	public List<RolePermission> findByRoleRId(int rid){
		List<RolePermission> rolePermission=rolePermissionDao.findByRoleRId(rid);
		return  rolePermission;
	}
}
