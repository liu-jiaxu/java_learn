package com.tjl.userdao;

import com.tji.bean.User;

public interface UserDao {
	
	//本接口定义了五个方法，用于在UserDao_Imp中具体编写
	
	int login(User user);
	
	boolean insert(User user);
	
	boolean delete(int stunumber);
	
	boolean update(User user);
	
	User select(int stunumber);
		// 对应UserDao_Imp
}
