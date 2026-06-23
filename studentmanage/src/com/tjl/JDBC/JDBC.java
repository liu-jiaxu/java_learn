package com.tjl.JDBC;

import java.security.PublicKey;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// jdk版本16.0

public class JDBC {

	// 调试程序
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
				// 加载SQL server驱动
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		try {
			 Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AList;user=sa;password=zgh2960425");	
				// 连接数据库，获得连接对象
			System.out.println("数据库连接成功");
			Statement st=con.createStatement();
				/*
				  	  是创建一个statement的对象；st对象是数据库sql语句的载体，通过st对象可以执行数据库访问的sql语句；
				  使用st对象执行insert、update、delete语句是调用executeUpdate ()方法。
				*/
			ResultSet rs=st.executeQuery("select * from ListInfo");
				// 执行SQL语句，得到ListInfo表的信息
				/*
				  	  用Statement类的executeQuery()方法来下达select指令以查询数据库，executeQuery()方法会把数据库响
				  应的查询结果存放在ResultSet类对象中供我们使用。
				*/
			while(rs.next()) {
					// rs.next()用于读取ListInfo表的信息，遇到空格停止，直到读完返回0
				System.out.print(rs.getString("stunumber")+" ");
					// 输出学号
					//getString表示以字符串形式获取对象rs中对应的信息
				
				if(rs.getString("name").length()==2) {
					System.out.print(rs.getString("name")+"   ");
				}
				else {
					System.out.print(rs.getString("name")+" ");
				}
					// 输出姓名
				
				if(rs.getString("password").length()==5) {
					System.out.print(rs.getString("password")+"  ");
				}
				else {
					System.out.print(rs.getString("password")+" ");
				}
					// 输出密码
				
				int type=Integer.parseInt(rs.getString("type"));
				if(type==2) {
					System.out.print("权限：学生");
				}
				else {
					System.out.print("权限：管理员");
				}
					// 输出权限
				
				System.out.println();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}