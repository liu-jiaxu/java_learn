package com.tjl.view;

import java.util.Scanner;
import org.w3c.dom.UserDataHandler;
import com.tji.bean.User;

public class View {
	
	private static Scanner inputScanner=new Scanner(System.in); 
	
	public static User indexView() {
		System.out.println("**********************************************************");
		System.out.println("\t\t    学生信息管理系统\t\t");
		System.out.print("请输入账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.print("请输入密码：");
		String password=inputScanner.nextLine();
			// 分别获取账号和密码
		System.out.println("**********************************************************");
		System.out.println("");
		return new User(stunumber,password);
			// 返回账号和密码
	}
		// 主页面
	
	public static int managerView() {
		System.out.println("**********************************************************");
		System.out.println("**********              管理员系统              **********");
		System.out.println("0.退出");
		System.out.println("1.添加学生信息");
		System.out.println("2.删除学生信息");
		System.out.println("3.修改学生密码");
		System.out.println("4.查询学生信息");
		System.out.println("**********************************************************");
		System.out.println("请输入您的选择：");
		String type=inputScanner.nextLine();
		int item=Integer.parseInt(type);
		if(item<0||item>4) {
			System.out.println("输入错误，请重新输入：");
			return managerView();
		}
		System.out.println("**********************************************************");
		System.out.println("");
		return item;
	}
		// 管理员页面
	
	public static int studentView() {
		System.out.println("**********************************************************");
		System.out.println("**********               学生系统               **********");
		System.out.println("0.退出");
		System.out.println("1.修改密码");
		System.out.println("2.查询信息");
		System.out.println("**********************************************************");
		System.out.println("请输入您的选择：");
		String type=inputScanner.nextLine();
		int item=Integer.parseInt(type);
		if(item<0||item>2) {
			System.out.println("输入错误，请重新输入：");
			return studentView();
		}
		System.out.println("**********************************************************");
		System.out.println("");
		return item;
	}
		// 学生页面
	
	public static User addMenuView() {
			// 添加学生信息
		System.out.println("**********************************************************");
		System.out.println("请输入账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.println("请输入姓名：");
		String name=inputScanner.nextLine();
		System.out.println("请输入密码：");
		String password=inputScanner.nextLine();
		System.out.println("请输入权限(1.管理员 2.学生)：");
		String type1=inputScanner.nextLine();
		int type=Integer.parseInt(type1);
		System.out.println("**********************************************************");
		return new User(stunumber,name,password,type);
	}
	
	public static int deleteMenuView() {
			// 删除学习信息
		System.out.println("**********************************************************");
		System.out.println("请输入要删除的账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.println("**********************************************************");
		return stunumber;
	}
	
	public static User updateMenuView() {
			// 管理员页面修改密码
		System.out.println("**********************************************************");
		System.out.println("请输入要修改学生的账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.println("请输入新的密码：");
		String password=inputScanner.nextLine();
		System.out.println("**********************************************************");
		return new User(stunumber,password);
	}
	
	public static User stuupdateMenuView() {
			// 学生页面修改密码
		System.out.println("**********************************************************");
		System.out.println("请输入您的账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.println("请输入新的密码：");
		String password=inputScanner.nextLine();
		System.out.println("**********************************************************");
		return new User(stunumber,password);
	}
	
	public static int selectMenuView() {
			// 查询学生信息
		System.out.println("**********************************************************");
		System.out.println("请输入要查询学生的账号：");
		String stunumber1=inputScanner.nextLine();
		int stunumber=Integer.parseInt(stunumber1);
		System.out.println("**********************************************************");
		return stunumber;
	}
	
	public static void printUser(User user) {
			// 显示学生信息
		System.out.println("账号："+user.getStunumber());
		System.out.println("姓名："+user.getName());
		System.out.println("密码："+user.getPassword());
		if(user.getType()==1) {
			System.out.println("权限：管理员");
		}
		else {
			System.out.println("权限：学生");
		}
	}
	
}
