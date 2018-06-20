package cn.com.taiji.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.dao.UserDao;
import cn.com.taiji.dao.UserRoleDao;
import cn.com.taiji.entity.User;
import cn.com.taiji.entity.UserRole;

@Service
public class UserService {
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	UserDao userDao;
	
	//查询所有用户-
	@Transactional
	public List<UserRole> findAllUsers(){
		List<UserRole> user= userRoleDao.findAll();
		return user;
	}
	
	//根据uid查询用户
	@Transactional
	public List<UserRole> findUserById(int uid){
		List<UserRole> userRole= userRoleDao.findByUserUId(uid);
		return userRole;
	}
	
	//根据uid删除UserRole
	@Transactional
	public void deleteUserRoleByUId(int uid){
		
		userRoleDao.delete(uid);
	}
	
	//保存UserRole对象
	@Transactional
	public void saveUserRole(UserRole userRole) {
		userRoleDao.save(userRole);
	}
	
	//根据User查找UserRole
	@Transactional
	public UserRole findByUser(User user) {
		UserRole userRole=userRoleDao.findByUser(user);
		return userRole;
	}
	//根据UidAndRid查找UserRole
	@Transactional
	public UserRole findByUserIdAndRoleId(int uid,int rid) {
		UserRole userRole=userRoleDao.findByRoleRIdAndUserUId(uid, rid);
		System.out.println(userRole);
		return userRole;
	}
	
	//---------------下面是userDao,上面是userRoleDao--------------------
	
	//根据id删除用户
	@Transactional
	public String deleteUser(int uid){
		userDao.delete(uid);
		return "ok";
	}
	
	//添加用户
	@Transactional
	public String addUser(User user){
		userDao.save(user);
		return "showusers";
	}
	
	//根据姓名查找User
	@Transactional
	public User finduIdByUName(String uName) {
		User user=userDao.findByUName(uName);
		return user;
		
	}
	
}
