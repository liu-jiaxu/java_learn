package com.tji.bean;

public class User {
	
	private int stunumber;
	private String name;
	private String password;
	private int type;
		//四个属性分别对应数据库四个属性
	
	public User() {
		super();
	}
	
	public User(int stunumber, String password) {
		super();
		this.stunumber = stunumber;
		this.password = password;
	}

	public User(String name, String password, int type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}

	public User(int stunumber, String name, String password, int type) {
		super();
		this.stunumber = stunumber;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	// 构造函数用于对应各种传参与返回值
	
	public int getStunumber() {
		return stunumber;
	}
	public void setStunumber(int stunumber) {
		this.stunumber = stunumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
		// get/set方法
		
}
	