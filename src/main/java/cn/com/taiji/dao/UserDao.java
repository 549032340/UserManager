package cn.com.taiji.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.taiji.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	//findUser
	User findByUId(int uId);
	
	//根据姓名查找User
	User findByUName(String uName);
}
