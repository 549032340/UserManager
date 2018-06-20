package cn.com.taiji.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.taiji.entity.User;
import cn.com.taiji.entity.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
	
	//根据uid查找UserRole
	List<UserRole> findByUserUId(int uid);
	//根据rid查找UserRole
	UserRole findByRoleRId(int rid);
	
	//根据UidAndRid查找UserRole
	@Query("select ur FROM UserRole ur left join ur.user u left join ur.role r where u.uId=:uId and r.rId=:rId")
	UserRole findByRoleRIdAndUserUId(@Param("uId")int uid,@Param("rId")int rid);
	
	//根据User查找UserRole
	UserRole findByUser(User user);
}
