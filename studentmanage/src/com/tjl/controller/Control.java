package com.tjl.controller;

import java.util.Scanner;

import javax.swing.InputVerifier;

import com.tji.bean.User;
import com.tjl.userdao.UserDao_Imp;
import com.tjl.view.View;

public class Control {
	
	// 主程序

	public static void main(String[] args) {
		while(true) {
			User user=View.indexView();
				// 创建User类对象（View.indexView()为主页面，返回值为账号和密码）
			UserDao_Imp userDao_Imp=new UserDao_Imp();
				// 创建UserDao_Imp类对象
			int type=userDao_Imp.login(user);
				// login()方法，传入user对象（包含账号和密码），返回权限
			if(type==-1) {
				System.out.println("账号或密码错误，请选择：\n0.退出程序\n1.重新输入");
				Scanner scanner=new Scanner(System.in);
				int choose=scanner.nextInt();
				if(choose==0) {
					System.out.println("您退出了程序！");
					System.exit(-1);
					break;
				}
				else if(choose==1) {
					System.out.println("您选择了重新输入！");
					continue;
				}
				else {
					System.out.println("输入错误，自动退出程序！");
					System.exit(-1);
					break;
				}
			}
			if(type==1) {
				managerServer();
					// 管理员对应操作
				break;
			}
			if(type==2) {
				studentServer();
				// 学生对应操作
				break;
			}
		}
	}
	
	private static void studentServer() {
		UserDao_Imp userDao_Imp=new UserDao_Imp();
		while(true) {
			int studentMenuView=View.studentView();
			boolean flag;
			switch(studentMenuView) {
			case 0:
				System.out.println("您选择了退出！");
				System.exit(-1);
				break;
			case 1:
				User user1=View.stuupdateMenuView();
				flag=userDao_Imp.update(user1);
				System.out.println(flag?"update success":"update fail");
				break;
			case 2:
				int stunumber1=View.selectMenuView();
				User user2=userDao_Imp.select(stunumber1);
				View.printUser(user2);
				break;
			default:
				System.out.println("输入错误，自动退出程序！");
				System.exit(-1);
				break;
			}
		}
	}
	
	private static void managerServer() {
		UserDao_Imp userDao_Imp=new UserDao_Imp();
		while(true) {
			int managerMenuView=View.managerView();
			boolean flag;
			switch(managerMenuView) {
			case 0:
				System.out.println("您选择了退出！");
				System.exit(-1);
				break;
			case 1:
				User user=View.addMenuView();
				flag=userDao_Imp.insert(user);
				System.out.println(flag?"add success":"add fail");
				break;
			case 2:
				int stunumber=View.deleteMenuView();
				flag=userDao_Imp.delete(stunumber);
				System.out.println(flag?"delete success":"delete fail");
				break;
			case 3:
				User user1=View.updateMenuView();
				flag=userDao_Imp.update(user1);
				System.out.println(flag?"update success":"update fail");
				break;
			case 4:
				int stunumber1=View.selectMenuView();
				User user2=userDao_Imp.select(stunumber1);
				if(user2!=null) {
					View.printUser(user2);
				}
				else {
					System.out.println("查无此人！");
				}
				break;
			default:
				System.out.println("输入错误，自动退出程序！");
				System.exit(-1);
				break;
			}
		}
	}

}

