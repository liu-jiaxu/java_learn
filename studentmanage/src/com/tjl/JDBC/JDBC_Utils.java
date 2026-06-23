package com.tjl.JDBC;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC_Utils {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
	static {
		InputStream inputStream = JDBC_Utils.class.getClassLoader().getResourceAsStream("db.properties");
			// student_utils.class获得对象
			// getClassLoader()类加载器
			// getResourceAsStream()加载资源文件放到输入流中
		
		Properties properties=new Properties();
			// 创建对象
		try {
			properties.load(inputStream);
				// 加载流文件
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			username=properties.getProperty("username");
			password=properties.getProperty("password");
			Class.forName(driver);
			Class.forName(url);
			Class.forName(username);
			Class.forName(password);
				// 数据传递
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static Connection getConnection() {
			// 获得连接对象的方法
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(connection!=null) {
				connection.close();
				connection=null;
			}
			if(statement!=null) {
				statement.close();
				statement=null;
			}
			if(resultSet!=null) {
				resultSet.close();
				resultSet=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		// 关闭方法
	
}
