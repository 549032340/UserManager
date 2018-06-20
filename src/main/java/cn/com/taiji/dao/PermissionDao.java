package cn.com.taiji.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.taiji.entity.Permission;


public interface PermissionDao extends JpaRepository<Permission, Integer> {
	
	//根据id查找permission权限
	Permission findByPId(int pid);
	
	//根据角色名称查找对应权限
	@Query("SELECT p.pName FROM Permission p,RolePermission rp,Role r WHERE r.rName=:rName AND r.rId=rp.role.rId AND rp.permission.pId=p.pId")
	public List<String> findPermissionByRoleRName(@Param("rName")String rName);
}
