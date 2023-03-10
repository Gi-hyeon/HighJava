package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpFileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.146.55", 7777);
//		Socket socket = new Socket("localhost", 7777);
		
		System.out.println("서버에 연결되었습니다...");	
		
		File file = new File("D:/D_Other/펭귄.jpg");
		
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
		//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		OutputStream out = socket.getOutputStream();		
		DataOutputStream dout = new DataOutputStream(out);
		
		dout.writeUTF(file.getName());
		System.out.println("서버에서 전송한 메시지 : " + file.getName());
		
		int c;
		
		while ((c = bin.read()) != -1) {
			out.write(c);
		}
		
		bin.close();
		out.close();
		socket.close();
	}
}
