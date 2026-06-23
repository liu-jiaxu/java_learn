package b1_Socket;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

//太特么厉害了这东西......

public class S2 {

	//例：客户端发送一张照片，服务端接受并保存到本地，同时服务器发送信息给客户端表示已经收到照片
	//客户端
	@Test
	public void test() throws IOException {
		
		Socket socket=new Socket(InetAddress.getByName("127.0.0.1"),12345);
		OutputStream outputStream=socket.getOutputStream();
			/*
			补：简说
				1.客户端输出流中的数据对应服务端输入流数据，客户端输入流数据对应服务端输出流数据
				2.反之亦然
			 */
		FileInputStream fileInputStream=new FileInputStream(new File("D:\\软件安装\\javaee\\jee文件保存\\0Java学习\\b1Socket_File\\S2\\S2.jpg"));
	 
		byte buffer[]=new byte[10];
		int data=fileInputStream.read(buffer);
		while(data!=-1) {
			outputStream.write(buffer,0,data);
			data=fileInputStream.read(buffer);
		}
		socket.shutdownOutput(); //read方法没有终止标识，执行完后处于阻塞状态，需要手动关闭read方法
		
		//接受服务端回应
		InputStream inputStream=socket.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		byte buffer2[]=new byte[1024];
		int data2=inputStream.read(buffer2);
		while(data2!=-1) {
			byteArrayOutputStream.write(buffer2,0,data2);
			data2=fileInputStream.read(buffer2);
		}
		System.out.println(byteArrayOutputStream.toString());
		
		fileInputStream.close();
		outputStream.close();
		socket.close();
		inputStream.close();
		
	}
	
	//服务端
	@Test
	public void test2() throws IOException {
		
		ServerSocket serverSocket=new ServerSocket(12345);
		Socket socket=serverSocket.accept(); 
			//执行客户端到此后处于阻塞状态，一直等待客户端数据传输，之后直到客户端执行完成后才继续执行后面的语句
		InputStream inputStream=socket.getInputStream();
		FileOutputStream fileOutputStream=new FileOutputStream(new File("D:\\软件安装\\javaee\\jee文件保存\\0Java学习\\b1Socket_File\\S2\\S21.jpg"));
		
		byte buffer[]=new byte[10];
		int data=inputStream.read(buffer);
		while(data!=-1) {
			fileOutputStream.write(buffer,0,data);
			data=inputStream.read(buffer);
		}
		
		//服务端回应
		OutputStream outputStream=socket.getOutputStream();
		outputStream.write("成功接收！".getBytes());
		
		fileOutputStream.close();
		inputStream.close();
		socket.close();
		serverSocket.close();
		outputStream.close();
		
	}
	
}
