package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// 이 쓰레드 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당한다.
public class Sender extends Thread {
	private Socket socket;
	private DataOutputStream dout; // 데이터를 보내기 위해서는 필요하다, 보조 스트림은 자기가 선택
	private String name;
	private Scanner scan;
	
	// 생성자
	
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.print("이름 입력 > ");
		name = scan.nextLine();
		
		// 출력용 스트림 객체 생성
		try {
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (dout != null) {
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
