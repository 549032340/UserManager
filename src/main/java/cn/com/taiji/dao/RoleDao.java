package cn.com.taiji.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.taiji.entity.Role;


public interface RoleDao extends JpaRepository<Role, Integer> {
	
	//根据姓名查找role
	Role findByRName(String rName);
	//根据id查找role
	Role findByRId(int rId);
	
	//根据用户名查找对应角色
	@Query("SELECT r.rName FROM User u,Role r,UserRole ur WHERE u.uName =:uName AND u.uId=ur.user.uId AND ur.role.rId=r.rId")
	public List<String> findRoleByUserUName(@Param("uName")String uName);
	
}
