package cn.com.taiji.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.entity.RolePermission;


public interface RolePermissionDao extends JpaRepository<RolePermission, Integer> {
	
	//查找所有的角色和所拥有权限
	@Query("select rp FROM RolePermission rp left join rp.permission p left join rp.role r where p.pId=rp.permission.pId and r.rId=rp.role.rId")
	public List<RolePermission> findAllRoles(); 
	
	//查找所有的角色对应的权限
	@Query("select rp.role.rName,rp.permission.pName FROM RolePermission rp left join rp.permission p left join rp.role r where p.pId=rp.permission.pId and r.rId=rp.role.rId")
	public List<RolePermission> findRoles(); 
	
	//根据rId查找所有的RolePermission
	List<RolePermission> findByRoleRId(int rid);
}
