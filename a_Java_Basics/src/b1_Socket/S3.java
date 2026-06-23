package b1_Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Test;

public class S3 {

	//UDP网络编程
	
	//先启动接收端，再启动发送端
	//发送端
	@Test
	public void test() throws IOException {
		
		DatagramSocket datagramSocket=new DatagramSocket();
		String string=new String("你好呀！");
		byte buffer[]=string.getBytes();
		InetAddress inetAddress=InetAddress.getLocalHost();
		DatagramPacket datagramPacket=new DatagramPacket(buffer,0,buffer.length,inetAddress,9090);
			//buf - 数据包数据。 offset - 分组数据偏移量。 length - 分组数据长度。 address - 目的地址。 port - 目的端口号
		datagramSocket.send(datagramPacket); //发送端datagramSocket发送的数据给了接收端的datagramPacket
			//从此套接字发送数据报包。datagramPacket包括指示要发送的数据，其长度，远程主机的IP地址和远程主机上的端口号的信息。
		datagramSocket.close();
		
	}
	
	//接收端
	@Test
	public void test2() throws IOException {
		
		DatagramSocket datagramSocket=new DatagramSocket(9090); //接收端指明端口号
		byte buffer2[]=new byte[1000]; //此处数组用于存储接收到的信息
		DatagramPacket datagramPacket=new DatagramPacket(buffer2, 0,buffer2.length);
		datagramSocket.receive(datagramPacket); //接收端接收的数据是自身datagramPacket接收的数据
			/*
				从此套接字接收数据报包。当此方法返回时，DatagramPacket的缓冲区将填充接收到的数据。
			数据包数据包还包含发送者的IP地址和发件人机器上的端口号。该方法阻塞，直到接收到数据报。
			 */
		System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
			//datagramPacket.getData()获取datagramPacket中的数据
		datagramSocket.close();
		
	}
	
}
