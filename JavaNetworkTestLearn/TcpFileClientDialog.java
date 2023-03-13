package learn;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

// 서버에 접속하면 d:/d_other/펭귄.jpg 파일로 서버로 전송한다.
public class TcpFileClientDialog {
	public static void main(String[] args) {
		new TcpFileClient().ClientStart();
	}
	
	public void ClientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		// 전송할 파일 정보를 갖는 File 객체 생성
		// File file = new File("d:/d_other/펭귄.jpg");
		File file = showDialog("OPEN");
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
				try {bout.close();} catch (IOException e) {}
			}
			if(bin != null) {
				try {bin.close();} catch (IOException e) {}
			}
			if(socket != null) {
				try {socket.close();} catch (IOException e) {}
			}
		}
	}
	public void copyStart() {
		File file = showDialog("OPEN");
		
		if (file == null) {
			System.out.println("복사할 원본 파일의 선택에 실패했습니다.");
			System.out.println("복사 작업을 중지합니다.");
		}
		
		File targetFile = showDialog("SAVE");
		
		if (targetFile == null) {
			System.out.println("복사할 원본 파일의 선택에 실패했습니다.");
			System.out.println("복사 작업을 중지합니다.");
		}
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		
		// Byte기반, String기반 스트림을 선택할 지 정해야함
		try {
			// 원본 파일을 처리할 스트림 객체 생성
			fis = new FileInputStream(file);
			
			// 대상 파일을 처리할 스트림 객체 생성
//			fos = new FileOutputStream(outFile);
			fos = new FileOutputStream(targetFile);
			
			System.out.println("복사 시작...");
			
			int c; // 읽어올 데이터를 저장할 변수
			
			while ((c = fis.read()) != -1) {
				fos.write(c);
			}
			
			fos.flush(); 
			System.out.println("복사 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// openType은 열기용일 때는 "OPEN", 저장용일 때 "SAVE"로 지정한다.
	private File showDialog(String openType) {
		// SWING 파일 열기, 저장 창 연습

		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docs");
		FileNameExtensionFilter img = new FileNameExtensionFilter("Image Files", new String[] { "png", "jpg", "gif" });
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "text");

		// 구성한 확장자 추가
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(pdf);
		chooser.addChoosableFileFilter(txt);

		// 구성한 확장자 목록 중에서 현재 선택된 상태가 될 확장자 지정
		// -> 이것을 설정하지 않으면 첫번째로 추가한 확장자가 기본적으로 선택된다.
		chooser.setFileFilter(txt);

		// 확장자 목록에 '모든 파일'목록을 표시할 지 표시 여부
//		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAcceptAllFileFilterUsed(true); // 기본값
		
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		// Dialog창 나타내기(열기용과 저장용을 구분해서 나타낸다.)
		int result;
		
		if ("OPEN".equals(openType.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기용
		} else if ("SAVE".equals(openType.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용
		}  else {
			System.out.println("openType은 'OPEN' 또는 'SAVE'로 지정해야 합니다.");
			return null;
		}
		
		File selectFile = null;
		if (result == JFileChooser.APPROVE_OPTION) { // '저장' 또는 '열기' 버튼을 눌렀을 경우
			// 현재 선택한 파일 정보 구하기
			selectFile = chooser.getSelectedFile();

			System.out.println("선택한 파일 : " + selectFile.getAbsolutePath());
		}
		
		return selectFile;
	}
}


