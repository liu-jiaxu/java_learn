package com.tjl.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tji.bean.User;
import com.tjl.JDBC.JDBC_Utils;

public class UserDao_Imp implements UserDao {

	private static final String SQL_USER_LOGIN="SELECT * FROM ListInfo WHERE stunumber=? AND password=?";
		// 登录语句
	private static final String SQL_USER_INSET ="INSERT INTO ListInfo VALUES(?,?,?,?)";
		// 添加语句
	private static final String SQL_USER_DELECT ="DELETE FROM ListInfo WHERE stunumber=?";
		// 删除语句
	private static final String SQL_USER_UPDATE ="UPDATE ListInfo SET password=? WHERE stunumber=?";
		// 修改语句
	private static final String SQL_USER_SELECT ="SELECT * FROM ListInfo WHERE stunumber=?";
		// 查询语句
	PreparedStatement preparedStatement=null;
		/*
		 	PreparedStatement对象不仅包含了SQL语句，而且大多数情况下这个语句已经被预编译过，因而当其执行时，只需DBMS运行
		 SQL语句，而不必先编译。当你需要执行Statement对象多次的时候，PreparedStatement对象将会大大降低运行时间，当然也加
		 快了访问数据库的速度。这种转换也带来便利，不必重复SQL语句的句法，而只需更改其中变量的值，便可重新执行SQL语句。
		 */
	ResultSet resultSet=null;
	
	@Override
	public int login(User user) {
		// TODO Auto-generated method stub
		Connection connection=JDBC_Utils.getConnection();
		try {
			preparedStatement=connection.prepareStatement(SQL_USER_LOGIN);
			preparedStatement.setInt(1, user.getStunumber());
				// setInt(n,m)方法：将数据m传递给参数n对应的占位符
			preparedStatement.setString(2, user.getPassword());
				// 账号、密码对应之前SQL语句中的两个占位符
			resultSet=preparedStatement.executeQuery();
				// executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中
			while(resultSet.next()) {
				int type=resultSet.getInt("type");
					// 获取权限，
				return type;
					//返回type用于判断权限，若为1则登录管理员页面，若为2则登录学生页面
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Utils.close(connection, preparedStatement,resultSet);
		}
		return -1;
			// 若为-1则报错，重新输入
	}
		// 登录代码

	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		Connection connection=JDBC_Utils.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(SQL_USER_INSET);
			preparedStatement.setInt(1, user.getStunumber());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getType());
				// 输入数据
			int line=preparedStatement.executeUpdate();
				// executeUpdate()方法用于更新数据库中的数据，若全部成功返回执行语句数，否则返回0
			if(line>0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC_Utils.close(connection, preparedStatement,resultSet);
		}
		return false;
	}
		// 添加方法

	@Override
	public boolean delete(int stunumber) {
		// TODO Auto-generated method stub
		Connection connection=JDBC_Utils.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(SQL_USER_DELECT);
			preparedStatement.setInt(1,stunumber);
			int line=preparedStatement.executeUpdate();
			if(line>0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC_Utils.close(connection, preparedStatement,null);
		}
		return false;
	}
		// 删除方法

	@Override
	public boolean update(User user) {
		Connection connection=JDBC_Utils.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(SQL_USER_UPDATE);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setInt(2, user.getStunumber());
			int line=preparedStatement.executeUpdate();
			if(line>0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC_Utils.close(connection, preparedStatement,null);
		}
		return false;
	}
		// 修改方法
	
	@Override
	public User select(int stunumber) {
		// TODO Auto-generated method stub
		Connection connection=JDBC_Utils.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(SQL_USER_SELECT);
			preparedStatement.setInt(1,stunumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				int number=resultSet.getInt("stunumber");
				String name=resultSet.getString("name");
				String password=resultSet.getString("password");
				int type=resultSet.getInt("type"); 
				return new User(number,name,password,type);
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC_Utils.close(connection, preparedStatement,resultSet);
		}
		return null;
	}
		// 查询方法
}
