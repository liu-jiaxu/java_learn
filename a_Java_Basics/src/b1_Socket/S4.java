package b1_Socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class S4 {

	/*
	URL
		统一资源定位符，表示Internet上某一资源的地址
		组成：<传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表（相当于web锚点）
		例：https://localhost:8080/examples/beauty.jpg?username=Tom
	 */
	@Test
	public void test() throws MalformedURLException {

		URL url=new URL("http://localhost:8080//examples//00.jpg");
		
		/*
		常用方法
			public String getProtocol() 获取该URL的协议名
			public String getHost()获取该URL的主机名
			public String getPort()获取该URL的端口号
			public String getPath()获取该URL的文件路径
			public String getFile()获取该URL的文件名
			public String getQuery()获取该URL的查询名
		*/
		System.out.println(url.getProtocol()); //http
		System.out.println(url.getHost()); //localhost
		System.out.println(url.getPort()); //8080
		System.out.println(url.getPath()); //examples//00.jpg
		System.out.println(url.getFile()); //examples//00.jpg
		System.out.println(url.getQuery()); //null
		
	}
	
	//下载url资源
	@Test
	public void test2() throws IOException {
		
		URL url=new URL("http://localhost:8080//examples//00.jpg");
		HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection(); //创建对象
		urlConnection.connect(); //获取连接
		InputStream inputStream=urlConnection.getInputStream(); //获取连接数据到流中
		FileOutputStream fileOutputStream=new FileOutputStream("D:\\软件安装\\javaee\\jee文件保存\\0Java学习\\b1Socket_File\\S4\\S41.jpg");
		byte buffer[]=new byte[1024];
		int data=inputStream.read(buffer);
		while(data!=-1) {
			fileOutputStream.write(buffer,0,data);
			data=inputStream.read(buffer);
		}
		System.out.println("succeed!");
		inputStream.close();
		fileOutputStream.close();
		urlConnection.disconnect(); //断开连接
		
	}
	
}
