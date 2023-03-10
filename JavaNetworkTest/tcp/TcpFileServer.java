package kr.or.ddit.basic.tcp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 준비 중입니다...");
		Socket socket = server.accept();
		
		File file = new File("D:/D_Other/연습용/복사본_펭귄.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		//BufferedInputStream bos = new BufferedInputStream(new FileInputStream(file));
		
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		System.out.println("서버에서 온 메시지 : " + din.readUTF());
		
		int c;
		
		while ((c = in.read()) != -1) {
			bos.write(c);
		}
		
		
		bos.close();
		in.close();
		server.close();
	}
}
