package com.ycc.dao;

import com.ycc.domain.User;

import java.sql.Connection;

public interface UserDao {

	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @param conn 
	 * @return 查找到的用户,如果找不到返回null
 	 */
	User findUserByName(String username, Connection conn);

	/**
	 * 添加用户
	 * @param user 封装了用户信息的bean
	 * @param conn 
	 */
	void addUser(User user, Connection conn);

	/**
	 * 根据激活码查找用户
	 * @param activecode 激活码
	 * @return 找到的用户,如果不存在返回null
	 */
	User findUserByActivecode(String activecode);

	/**
	 * 根据id删除用户
	 * @param id 要删除的用户id
	 */
	void delUser(int id);

	/**
	 * 修改指定id客户的状态
	 * @param id 客户id
	 * @param i 要更新的客户状态
	 */
	void updateState(int id, int state);

	/**
	 * 根据用户名密码查找用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 找到的用户bean
	 */
	User finUserByNameAndPsw(String username, String password);

}
