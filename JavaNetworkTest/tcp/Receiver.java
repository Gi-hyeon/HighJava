package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// 이 쓰레드 클래스는 소켓을 통해서 메시지를 받아서 화면에 출력
public class Receiver extends Thread {
	private Socket socket;
	private DataInputStream din;
	
	public Receiver(Socket socket) {
		this.socket = socket; // Socket 초기화
		
		// 입력용 스트림객체 생성
		
		try {
			din = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (din != null) {
			try {
				//메시지를 받아서 화면에 출력하기
				System.out.println(din.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
