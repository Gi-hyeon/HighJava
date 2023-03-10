package tea;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
 * - 추가 조건)
 * 1) '6. 전화번호 저장' 메뉴를 추가하고 구현한다.
 * 	   (저장파일명은 'phoneData.bin')
 * 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
 * 3) 프로그램을 종료할 때 Map의 데이터의 변화가 있으면
 * 	  (데이터의 추가, 수정, 삭제작업 후 저장하지 않은 상태)5 자료를 저장한 후 종료되도록 한다.
 */

public class PhoneBookTestTea {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scanner;
	private String fileName = "d:/d_other/phoneData.bin";
	// 데이터의 변화가 있었는지 여부를 저장하는 변수
	// 데이터의 변화가 있으면 이 변수값이 true가 된다.
	private boolean isDataChange = false;
	
	// 생성자
	public PhoneBookTestTea() {
//		phoneBookMap = new HashMap<>();
		phoneBookMap = load(); // 파일 내용을 읽어와 Map에 저장하기
		if (phoneBookMap == null) {
			phoneBookMap = new HashMap<>(); // 파일이 업을 때...
		}
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new PhoneBookTestTea().phoneBookStart();
	}

	private void phoneBookStart() {
		System.out.println("*****************************************");
		System.out.println("  전 화 번 호 관 리 프 로 그 램  ");
		System.out.println("*****************************************");
		System.out.println();
		
		while (true) {
			int choice = disPlayMenu();
			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				disPlayAll();
				break;
			case 6:
				save();
				break;	
			case 0:
				if (isDataChange == true) {
					System.out.println("변경된 자료가 있어서 저장 후 종료합니다.");
					save();
				}
				System.out.println("시스템을 종료합니다 감사합니다...");
				return;
			default:
				
				break;
			}
		}
	}
	
	// 파일에 저장된 전화번호 정보를 읽어 Map에 추가한 후 반환하는 메서드
	private HashMap<String, Phone> load(){
		HashMap<String, Phone> pMap = null;	// 반환값이 저장될 변수 선언
		File file = new File(fileName);
		if(!file.exists()) {	// 저장된 파일이 없으면...
			return null;
		}
		
		// 저장된 파일이 있을 때 처리되는 영역...
		ObjectInputStream oin = null;
		try {
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			// 파일에 저장된 데이터를 읽어와 Map 객체에 저장하기
			
			// '방법1'로 저장했을 때(Map 자체를 저장했을 때...)
//			pMap = (HashMap<String, Phone>) oin.readObject();
			
			// '방법2'로 저장했을 때
			Object obj = null; // 읽어올 데이터가 저장될 변수
			pMap = new HashMap<>();	// 저장할 Map 객체 생성
			while ((obj = oin.readObject()) != null) {
				if (obj instanceof Phone) {
					Phone p = (Phone) obj;
					pMap.put(p.getName(), p);
				}
			}
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			if (oin != null) {try {oin.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
		return pMap;
	}
	
	// 전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		// TODO Auto-generated method stub
		ObjectOutputStream oout = null;
		
		try {
			// 객체 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			
			// Map에 저장된 전화번호 정보를 파일로 출력한다.
			//oout.writeObject(phoneBookMap);	// Map객체 자체 저장하기 (방법1)
			
			// Map에 저장된 Phone 객체를 하나씩 꺼내서 저장하기 (방법2)
			for(String name : phoneBookMap.keySet()) {
				Phone p = phoneBookMap.get(name);
				oout.writeObject(p);
			}
			oout.writeObject(null);
			
			System.out.println("저장이 완료되었습니다.");
			isDataChange = false;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oout != null) {try {oout.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
		
	}

	// 메뉴를 출력하고 사용자가 입력한 작업 전호를 반환하는 메서드
	public int disPlayMenu() {
		System.out.println("--------------------------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------------------");
		System.out.print("번호입력 >> ");
		return Integer.parseInt(scanner .nextLine());
	}
	
	// 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 > ");
		String name = scanner.nextLine();
		
		// 이미 등록된 사람인지 여부 검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return ;
		}
		
		System.out.print("전 화 번 호 > ");
		String tel = scanner.nextLine();
		
		System.out.print("주 소 > ");
		String addr = scanner.nextLine();
		
		phoneBookMap.put(name, new Phone(name, tel, addr));			
		System.out.println(name + "씨 전화번호 정보 등록 완료!!!");
		isDataChange = true;
	}
	
	/*
	 * Scanner의 next(), nextInt(), nextDouble() 등...
	 *  -> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
	 * Scanner의 nextLine()
	 *  -> 한 줄 단위로 입력한다. 
	 *     (즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서 Enter키를 뺀 나머지를 반환한다.)
	 * 
	 * - 컴퓨터의 입력은 작업은 입력된 데이터를 입력 버퍼에 저장하고 이것을 차례로 꺼내가는
	 *   방법으로 처리된다.
	 *   그래서 next(), nextInt()등과 같은 메서드를 실행한 후에 nextLine()을 사용할 때는
	 *   입력 버퍼를 비워줘야 한다. (방법 : nextLine()을 한번 더 사용한다.)    
	 */
	
	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 > ");
		String name = scanner.nextLine();
		
		// 수정할 대상이 등록되어 있지 않으면 수정 작업을 못한다.
		if(!(phoneBookMap.containsKey(name))) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}
		System.out.println("새로운 전화번호 > ");
		String newTel = scanner.nextLine();
		
//		scanner.nextLine() -> 입력 버퍼 지우기 
		System.out.println("새로운 주소 > ");
		String newAddr = scanner.nextLine();
		
		//같은 key값에 새로운 전화번호 정보를 저장한다. -> 수정작업
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		System.out.println(name + "씨의 전화번호 정보를 변경했습니다.");
		isDataChange = true;
		
	}
	
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 > ");
		String name = scanner.nextLine();
		
		if(!(phoneBookMap.containsKey(name))) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}
		
		phoneBookMap.remove(name);
		System.out.println(name + "씨의 전화번호 정보를 삭제되었습니다.");
		isDataChange = true;
	}
	
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 > ");
		String name = scanner.nextLine();
		
		if(!(phoneBookMap.containsKey(name))) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}
		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("-----------------------------------");
		System.out.println("이 름 : " + p.getName());
		System.out.println("전 화 : " + p.getTel());
		System.out.println("주 소 : " + p.getAddr());
		System.out.println("-----------------------------------");
		System.out.println();
		
	}
	
	// 전화번호 정보 전체를 출력하는 메서드
	private void disPlayAll() {
		System.out.println("--------------------------------------");
		System.out.println("번호\t이 름\t전화번호\t주소");
		System.out.println("--------------------------------------");
		Set<String> keySet = phoneBookMap.keySet();
		if (keySet.size() == 0) {
			System.out.println(" 등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int num = 0;
			for (String name : keySet) {
				num++;
				Phone p = phoneBookMap.get(name);
				System.out.print(num + "\t  " + p.getName() + "\t  " + p.getTel() + "\t" + p.getAddr() + "\n");
			}
		}
	}
}

class Phone implements Serializable{
	private String name;
	private String tel;
	private String addr;
	
	public Phone(){};
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return String.format("Phone [name=%s, tel=%s, addr=%s]", name, tel, addr);
	}
	
}
