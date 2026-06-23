package b1_Socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.junit.Test;

/*
网络编程
	通过网络协议与其他计算机实现数据交换
	问题：找到一台或多台主机并定位主机特定应用，找到主机后数据可以高效的传输
	实现网络主机相互通信
		1.通信双方地址：IP、端口号
		2.规则（网络通信协议）：TCP/IP协议（国际标准）（应用层、传输层、网络层、物理+数据链路层）
 */

/*
通信要素1：IP和端口号
	1.IP：唯一标识Internet上的计算机
	2.在Java中使用InetAddress类代表IP
	3.IP分类：IPv4和IPv6
	4.域名：www.baidu.com等等
	5.本地地址：127.0.0.1对应域名localhost
	6.端口号：正在计算机上运行的程序
		（1）要求不同进程（程序）要有不同端口号
		（2）范围0-65536
	7.IP地址和端口号组合得到一个网络套接字：Socket
	
通信要素2：网络通信协议
	1.TCP：三次握手，可靠，效率低
	2.UDP：不可靠，速度快
 */

public class S1 {

	public static void main(String[] args) throws UnknownHostException {
		
		//InetAddress类使用
		InetAddress inetAddress=InetAddress.getByName("192.168.10.14"); //直接获取IP地址号
		InetAddress inetAddress2=InetAddress.getByName("www.runoob.com"); //先获取域名，系统通过DNS解析获得IP地址号一并输出
		InetAddress inetAddress3=InetAddress.getByName("localhost"); //本机域名127.0.0.1
		InetAddress inetAddress4=inetAddress.getLocalHost(); //获取本机IP
			//注：127.0.0.1和本机IP不同
		System.out.println(inetAddress);
		System.out.println(inetAddress2);
		System.out.println(inetAddress3+" "+inetAddress4);
		
	}
	
	
	
	//TCP网络编程
	
	/*
	例：客户端发送数据到服务端，服务端在控制台显示数据
		注：先启动test2服务端，此时程序会一直处于等待状态，直到接受到客户端发送的数据
	 */
	
	//客户端
	@Test
	public void test() {
		
		Socket socket=null; //客户端对象
		OutputStream outputStream=null;
		try {
			InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
			socket=new Socket(inetAddress,8899); //给客户端IP地址和端口号
			outputStream=socket.getOutputStream(); 
				/*
				客户端上的使用
					getInputStream方法可以得到一个输入流，客户端的Socket对象上的getInputStream方法得到输入流其实就是从服务器端发回的数据。
					getOutputStream方法得到的是一个输出流，客户端的Socket对象上的getOutputStream方法得到的输出流其实就是发送给服务器端的数据。
				服务器端上的使用
					getInputStream方法得到的是一个输入流，服务端的Socket对象上的getInputStream方法得到的输入流其实就是从客户端发送给服务器端的数据流。
					getOutputStream方法得到的是一个输出流，服务端的Socket对象上的getOutputStream方法得到的输出流其实就是发送给客户端的数据。
				 */
			outputStream.write("hello!".getBytes()); //将字符写入流中
				//getBytes() 是Java编程语言中将一个字符串转化为一个字节数组byte[]的方法。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//服务端
	@Test
	public void test2() {
		
		ServerSocket serverSocket=null; //服务端对象
		Socket socket=null;
		InputStream inputStream=null;
		ByteArrayOutputStream byteArrayOutputStream=null; 
			//ByteArrayOutputStream类底层使用数组搭建，用于接受客户端数据并打印（因为常规打印方法遇到中文会拆分字符出现乱码，utf-8下一个中文占3字节）
		try {
			System.out.println("服务端启动成功！");
			serverSocket=new ServerSocket(8899); //服务器指明自己的端口号
			socket=serverSocket.accept(); 
				//ServerSocket的accept()方法是侦听并接受到此套接字的连接，就是一直等待连接，此方法在连接传入之前一直阻塞 (即后面的代码不会往下执行)。
			inputStream=socket.getInputStream(); //创建输入流对象获取服务器字符
			
			byteArrayOutputStream=new ByteArrayOutputStream();
			byte buffer[]=new byte[5];
			int data=inputStream.read(buffer);
			while(data!=-1) {
				byteArrayOutputStream.write(buffer,0,data);
				data=inputStream.read(buffer);
			}
			System.out.println("发送者："+socket.getInetAddress().getHostAddress());
			System.out.println("发送信息："+byteArrayOutputStream.toString());		
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(byteArrayOutputStream!=null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if(serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		
	}
	
}
