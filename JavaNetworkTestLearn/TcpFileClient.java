package learn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

// 서버에 접속하면 d:/d_other/펭귄.jpg 파일로 서버로 전송한다.
public class TcpFileClient {
	public static void main(String[] args) {
		new TcpFileClient().ClientStart();
	}
	
	public void ClientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		// 전송할 파일 정보를 갖는 File 객체 생성
		File file = new File("d:/d_other/펭귄.jpg");
		String fileName = file.getName();
		
		if (!file.exists()) {
			System.out.println("전송할 " + fileName + " 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			
			System.out.println("파일 전송 시작...");
			dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(fileName);
			
			// 파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			// 서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(socket.getOutputStream());
			
			// 바이트 배열을 이용하면 빠르게 전송이 가능하다.
			byte[] temp = new byte[1024];
			int len = 0; // 실제 읽어들인 길이가 필요하다.
			
			// 파일 내용을 읽어서 서버로 전송한다.
			while ((len = bin.read(temp)) > 0) {
				bout.write(temp, 0, len);
			}
			
			bout.flush();
			
			System.out.println("파일 전송 완료...");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 전송 실패...");
			e.printStackTrace();
			
		} finally {
			if(dout != null) {
				try {dout.close();} catch (IOException e) {}
			}
			if(bout != null) {
				try {dout.close();} catch (IOException e) {}
			}
			if(bin != null) {
				try {dout.close();} catch (IOException e) {}
			}
			if(socket != null) {
				try {socket.close();} catch (IOException e) {}
			}
			
		}
		
		
	}
}
